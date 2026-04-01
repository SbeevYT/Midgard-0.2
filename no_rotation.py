import os
import json

folder_path = 'src/main/resources/data/midgard/worldgen/configured_feature/yellow_maple'

for filename in os.listdir(folder_path):
    if filename.endswith('.json'):
        file_path = os.path.join(folder_path, filename)
        
        with open(file_path, 'r+') as f:
            data = json.load(f)
            # Check of het de juiste type feature is
            if "config" in data:
                data["config"]["random_rotation"] = False
                
            f.seek(0)
            json.dump(data, f, indent=4)
            f.truncate()