import nbtlib
from nbtlib.tag import String, Compound, Int, List
import os
import copy
from multiprocessing import Pool

directory = 'src/main/resources/data/ohthetreesyoullgrow/structures/features/trees'

def rotate_block_state(state, angle):
    directions = ['north', 'east', 'south', 'west']
    props = dict(state) # Maak een kopie
    steps = (angle // 90) % 4

    # 1. Rotate 'facing' (stairs, observers, chests, etc.)
    if 'facing' in props:
        val = str(props['facing']).strip("'\"")
        if val in directions:
            props['facing'] = String(directions[(directions.index(val) + steps) % 4])

    # 2. Rotate 'axis' (logs, quartz pillars)
    if 'axis' in props and steps % 2 != 0:
        val = str(props['axis']).strip("'\"")
        if val == 'x': props['axis'] = String('z')
        elif val == 'z': props['axis'] = String('x')

    # 3. Rotate connections (fences, walls, glass panes)
    # Maak een tijdelijke map om botsingen te voorkomen
    connection_changes = {}
    found_any = False
    for d in directions:
        if d in props:
            found_any = True
            new_dir = directions[(directions.index(d) + steps) % 4]
            connection_changes[new_dir] = props[d]
    
    if found_any:
        # Verwijder oude richtingen
        for d in directions:
            props.pop(d, None)
        # Voeg nieuwe toe
        props.update(connection_changes)

    return Compound(props)

def rotate_structure(nbt_data, angle):
    old_size = nbt_data['size']
    sx, sy, sz = int(old_size[0]), int(old_size[1]), int(old_size[2])
    steps = (angle // 90) % 4

    # 1. Rotate Palette
    new_palette = List[Compound]()
    for entry in nbt_data['palette']:
        new_entry = Compound({'Name': entry['Name']})
        if 'Properties' in entry:
            new_entry['Properties'] = rotate_block_state(entry['Properties'], angle)
        new_palette.append(new_entry)

    # 2. Rotate Blocks & Size
    new_blocks = List[Compound]()
    
    # Bepaal nieuwe size
    if steps % 2 == 0:
        nsx, nsy, nsz = sx, sy, sz
    else:
        nsx, nsy, nsz = sz, sy, sx

    for block in nbt_data['blocks']:
        x, y, z = map(int, block['pos'])
        
        if steps == 1: # 90 degrees clockwise
            nx, nz = (sz - 1) - z, x
        elif steps == 2: # 180 degrees
            nx, nz = (sx - 1) - x, (sz - 1) - z
        elif steps == 3: # 270 degrees clockwise
            nx, nz = z, (sx - 1) - x
        else:
            nx, nz = x, z

        new_block = Compound({
            'pos': List[Int]([Int(nx), Int(y), Int(nz)]),
            'state': block['state']
        })
        if 'nbt' in block:
            new_block['nbt'] = copy.deepcopy(block['nbt'])
        new_blocks.append(new_block)

    return Compound({
        'size': List[Int]([Int(nsx), Int(nsy), Int(nsz)]),
        'palette': new_palette,
        'blocks': new_blocks,
        'DataVersion': nbt_data.get('DataVersion', Int(3465))
    })

def process_file(file_info):
    root_dir, file = file_info
    path = os.path.join(root_dir, file)
    try:
        # Laad het bronbestand één keer
        nbt_file = nbtlib.load(path)
        base_root = Compound(nbt_file)
        
        for angle in [90, 180, 270]:
            rotated_root = rotate_structure(base_root, angle)
            
            new_name = file.replace('.nbt', f'_{angle}.nbt')
            new_path = os.path.join(root_dir, new_name)
            
            output_file = nbtlib.File(rotated_root, gzipped=True)
            output_file.save(new_path)
            
        return f"✅ Succes: {file}"
    except Exception as e:
        return f"❌ Fout bij {file}: {e}"

if __name__ == '__main__':
    tasks = []
    for root_dir, _, files in os.walk(directory):
        for f in files:
            # Pak alleen originele bestanden
            if f.endswith('.nbt') and not any(x in f for x in ['_90', '_180', '_270']):
                if not f.startswith('blank_trunk'):
                    tasks.append((root_dir, f))

    print(f"Start rotatie van {len(tasks)} bomen...")
    with Pool() as pool:
        results = pool.map(process_file, tasks)
        # Optioneel: alleen fouten printen om de console schoon te houden
        for r in results:
            if "❌" in r: print(r)
    print("Klaar!")