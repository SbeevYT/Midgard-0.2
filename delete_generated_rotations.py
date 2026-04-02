"""
================================================================================
           MINECRAFT STRUCTURE CLEANUP SCRIPT (ROTATION FILE REMOVER)
================================================================================

WHAT THIS SCRIPT DOES:
This script scans a folder (and all its subfolders) and permanently deletes 
any files that end with '_90', '_180', or '_270'. 

WHY USE THIS?
If your previous rotation attempts failed or created "exploded" trees, you 
need to clean out those broken files before running the rotation factory again. 
This prevents the factory from trying to rotate already-rotated files.

HOW TO USE:
1. Update the 'my_directory' variable below to your structures folder.
2. Run the script using Python (e.g., 'python cleanup.py').
3. The script will list every file it deletes and give a total count at the end.

WARNING:
This script DELETES files. Make sure your path is correct before running!
================================================================================
"""

import os

def remove_rotation_files(target_directory):
    # The specific suffixes created by the rotation script
    targets = ("_90", "_180", "_270")
    deleted_count = 0

    # os.walk scans the main folder and all subfolders (recursive)
    for root, dirs, files in os.walk(target_directory):
        for file in files:
            # Separate the file name from the extension (e.g., 'birch_90' and '.nbt')
            name, ext = os.path.splitext(file)
            
            # Check if the name ends with one of our target suffixes
            if name.endswith(targets):
                file_path = os.path.join(root, file)
                try:
                    os.remove(file_path)
                    print(f"Deleted: {file_path}")
                    deleted_count += 1
                except Exception as e:
                    print(f"Error deleting {file_path}: {e}")

    print(f"\nFinished! Total files deleted: {deleted_count}")

# UPDATE THIS PATH: Use the 'r' before the quotes for Windows paths
my_directory = r"src/main/resources/data/ohthetreesyoullgrow/structures/features/trees" 

# Execute the function
if __name__ == "__main__":
    remove_rotation_files(my_directory)