"""
================================================================================
           AUTOMATED MINECRAFT TREE ROTATION FACTORY (MINESCRIPT + PYAUTOGUI)
================================================================================

WHAT THIS SCRIPT DOES:
This script automates the rotation of .nbt structure files. It spawns trees
using Minecraft's native engine, meaning fences, stairs, and logs rotate 
perfectly without the "splitting" or "exploding" bugs found in external scripts.

PREREQUISITES:
1. Minescript installed in your Minecraft instance.
2. Python libraries installed: 'pip install nbtlib pyautogui'.
3. Put this script in your minecraft folder (e.g., .minecraft or Modrinth profile folder) in the minescript directory.
4. Place the nbt files you want to rotate in the generated/minecraft/structures folder. (if this folder doesn't exist, create it and add your .nbt files there, maintaining any subfolder structure).
5. Mod Environment: Ensure 'Oh The Trees You'll Grow' is active.

HOW TO USE:
1. Update the 'directory' variable below to your source structure folder.
2. In Minecraft, type '\rotate_factory' (or your filename) in chat.
3. CALIBRATION:
   - A structure block will appear beneath you. Right-click it immediately.
   - Hover your mouse exactly over the 'SAVE' button. 
   - DO NOT MOVE THE MOUSE. The script will record these pixels in 10 seconds.
4. AUTOMATION:
   - The script will teleport you, spawn the tree, and click 'SAVE' for you.
   - CLICK NOTHING. Move nothing. Let the script finish.
   - ESCAPE ROUTE: Slam your mouse into any corner of your monitor to abort.

5. After completion, check your 'generated' folder for the new rotated .nbt files. They should be in a folder named oh_the_trees_youll_grow with the same subfolder structure as your source.
================================================================================
"""

import os
import time
import nbtlib
import pyautogui
import minescript as m
from minescript import execute

# UPDATE THIS PATH: Use the 'r' before the quotes to handle backslashes correctly
directory = r'C:\Users\Boull\AppData\Roaming\ModrinthApp\profiles\midgard test stuff\saves\New World\generated\minecraft\structures'

# Failsafe: Move mouse to any corner of the screen to stop the script immediately
pyautogui.FAILSAFE = True 

def main():
    start_x = 0
    start_y = 200
    start_z = 0
    
    current_x = start_x
    current_z = start_z
    
    tasks = []
    
    # 1. Fetch original NBT files recursively
    for root_dir, _, files in os.walk(directory):
        for f in files:
            # Skip files that are already rotated (_90, _180, _270)
            if f.endswith('.nbt') and not any(r in f for r in ['_90', '_180', '_270']):
                if not f.startswith('blank_trunk'):
                    full_path = os.path.join(root_dir, f)
                    rel_path = os.path.relpath(full_path, directory)
                    # Convert folder path to Minecraft namespace format
                    namespace_path = rel_path.replace('\\', '/').replace('.nbt', '')
                    tasks.append((full_path, namespace_path))
                
    m.echo(f"Preparing to process {len(tasks) * 3} rotated trees...")

    # 2. CALIBRATION PHASE
    # Set up a dummy block to find the 'SAVE' button coordinates on your screen
    execute(f'setblock 0 199 0 structure_block')
    execute(f'tp @p 0.5 200 -0.5 0 0')
    m.player_look_at(0.5, 199.5, 0.5)
    
    m.echo("--- CALIBRATION REQUIRED ---")
    m.echo("1. Right-click the block below you.")
    m.echo("2. Hover mouse over the 'SAVE' button.")
    m.echo("3. DO NOT MOVE IT. Locking coordinates in 10 seconds...")
    time.sleep(10)
    
    # Capture the exact mouse pixel location
    save_x, save_y = pyautogui.position()
    m.echo(f"Coordinates locked at X:{save_x} Y:{save_y}!")
    
    # Close the dummy GUI
    pyautogui.press('esc')
    time.sleep(1)
    execute(f'setblock 0 199 0 air')

    # 3. UNIFIED PLACEMENT & SAVE LOOP
    for filepath, namespace_path in tasks:
        nbt_data = nbtlib.load(filepath)
        sx, sy, sz = [int(i) for i in nbt_data['size']]
        
        ox, oy, oz = 0, 0, 0
        if 'offset' in nbt_data:
            ox, oy, oz = [int(i) for i in nbt_data['offset']]
            
        rotations = [
            (90, "clockwise_90"),
            (180, "180"),
            (270, "counterclockwise_90")
        ]
        
        for angle, rot_str in rotations:
            steps = (angle // 90) % 4
            
            # Recalculate dimensions for rotated box
            if steps % 2 == 0:
                nsx, nsy, nsz = sx, sy, sz
            else:
                nsx, nsy, nsz = sz, sy, sx
                
            # Recalculate offset coordinates based on rotation steps
            if steps == 1:
                nox, noz = 1 - sz - oz, ox
            elif steps == 2:
                nox, noz = 1 - sx - ox, 1 - sz - oz
            elif steps == 3:
                nox, noz = oz, 1 - sx - ox
            else:
                nox, noz = ox, oz
            
            loc_x = current_x
            loc_y = start_y - 5
            loc_z = current_z
            
            # CHUNK LOADING: Move player to the target area first
            execute(f'tp @p {loc_x + 0.5} {loc_y} {loc_z - 1.5} 0 0')
            execute(f'title @p actionbar {{"text":"Processing: {namespace_path}_{angle}","color":"gold"}}')
            time.sleep(0.8) # Wait for singleplayer chunks to load
            
            # Use game engine to place the rotated template
            execute(f'place template minecraft:{namespace_path} {current_x} {start_y} {current_z} {rot_str}')
            
            # Place and configure the Save Structure Block
            adj_oy = oy + 5 
            block_data = (
                f'mode:"SAVE",'
                f'name:"ohthetreesyoullgrow:{namespace_path}_{angle}",'
                f'posX:{nox}, posY:{adj_oy}, posZ:{noz},'
                f'sizeX:{nsx}, sizeY:{nsy}, sizeZ:{nsz}'
            )
            execute(f'setblock {loc_x} {loc_y} {loc_z} structure_block{{{block_data}}}')
            time.sleep(0.2)
            
            # AUTOMATION: Click the Save button
            m.player_look_at(loc_x + 0.5, loc_y + 0.5, loc_z + 0.5)
            time.sleep(0.1)
            
            # Open the GUI
            m.player_press_use(True)
            time.sleep(0.1)
            m.player_press_use(False)
            
            time.sleep(0.5) # Wait for GUI to render
            
            # Move mouse to recorded button position and click
            pyautogui.moveTo(save_x, save_y)
            time.sleep(0.1)
            pyautogui.click(duration=0.1) 
            
            # Wait for save processing (Clicking SAVE auto-closes the GUI)
            time.sleep(0.5) 
            
            # Increment X for next rotation
            current_x += nsx + 10 
            
        # Increment Z for next tree type
        current_z += sz + 15 
        current_x = start_x 

    m.echo('Automation Complete! You can touch your mouse again.')

if __name__ == '__main__':
    main()