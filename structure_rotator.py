import nbtlib
from nbtlib.tag import String, Compound, Int, List
import os
import copy
from multiprocessing import Pool

directory = 'src/main/resources/data/ohthetreesyoullgrow/structures/features/trees'

def rotate_block_state(state):
    directions = ['north', 'east', 'south', 'west']
    # Maak een diepe kopie van de properties om bleeding te voorkomen
    props = {k: v for k, v in state.items()}
    
    # 1. Facing
    if 'facing' in props:
        val = str(props['facing']).strip("'\"")
        if val in directions:
            props['facing'] = String(directions[(directions.index(val) + 1) % 4])
    
    # 2. Axis
    if 'axis' in props:
        val = str(props['axis']).strip("'\"")
        if val == 'x': props['axis'] = String('z')
        elif val == 'z': props['axis'] = String('x')

    # 3. Connections (Fences, Walls, Leaves)
    # Belangrijk: we lezen uit 'state' en schrijven naar 'props'
    for i, d in enumerate(directions):
        if d in state:
            new_dir = directions[(i + 1) % 4]
            props[new_dir] = state[d]
            
    return Compound(props)

def rotate_structure(nbt_data):
    # We halen de data op
    old_size = nbt_data['size']
    old_x, old_y, old_z = int(old_size[0]), int(old_size[1]), int(old_size[2])
    
    # 1. Palette veilig roteren
    # We maken een compleet nieuwe lijst om ID-verwarring te voorkomen
    new_palette = List[Compound]()
    for entry in nbt_data['palette']:
        new_entry = Compound({'Name': entry['Name']})
        if 'Properties' in entry:
            new_entry['Properties'] = rotate_block_state(entry['Properties'])
        new_palette.append(new_entry)

    # 2. Blokken roteren
    new_blocks = List[Compound]()
    for block in nbt_data['blocks']:
        bx, by, bz = map(int, block['pos'])
        
        # Rotatie formule
        new_pos = List[Int]([Int((old_z - 1) - bz), Int(by), Int(bx)])
        
        new_block = Compound({
            'pos': new_pos,
            'state': block['state'] # De index blijft hetzelfde
        })
        if 'nbt' in block:
            new_block['nbt'] = copy.deepcopy(block['nbt'])
        new_blocks.append(new_block)

    # Bouw een schone nieuwe root op
    new_root = Compound({
        'size': List[Int]([Int(old_z), Int(old_y), Int(old_x)]),
        'palette': new_palette,
        'blocks': new_blocks,
        'DataVersion': nbt_data.get('DataVersion', Int(2584))
    })
    return new_root

def process_file(file_info):
    root_dir, file = file_info
    path = os.path.join(root_dir, file)
    try:
        # Laad het bestand
        nbt_file = nbtlib.load(path)
        
        # De 'nbt_file' zelf is vaak al de root compound.
        # We pakken de data en zorgen dat het een Compound is voor onze rotate functie.
        current_root = Compound(nbt_file)
        
        for angle in [90, 180, 270]:
            # Roteer de data
            current_root = rotate_structure(current_root)
            
            new_name = file.replace('.nbt', f'_{angle}.nbt')
            new_path = os.path.join(root_dir, new_name)
            
            # Opslaan: We maken een nieuwe File wrapper om de Compound heen.
            # We gebruiken gzipped=True omdat Minecraft structures dit vereisen.
            output_file = nbtlib.File(current_root, gzipped=True)
            output_file.save(new_path)
            
        return f"✅ Succes: {file}"
    except Exception as e:
        return f"❌ Fout bij {file}: {e}"

if __name__ == '__main__':
    tasks = []
    for root_dir, _, files in os.walk(directory):
        for f in files:
            if f.endswith('.nbt') and not any(x in f for x in ['_90', '_180', '_270'])and not f.startswith('blank_trunk.nbt'):
                tasks.append((root_dir, f))

    print(f"Start rotatie van {len(tasks)} bomen...")
    with Pool() as pool:
        results = pool.map(process_file, tasks)
        for r in results: print(r)