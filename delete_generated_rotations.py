import os

def verwijder_rotatie_bestanden(doel_map):
    # De suffixes waar we naar zoeken
    targets = ("_90", "_180", "_270")
    verwijderd_count = 0

    # os.walk gaat door alle submappen heen (recursief)
    for root, dirs, files in os.walk(doel_map):
        for file in files:
            # Splits de extensie van de bestandsnaam (bijv. 'foto_90' en '.jpg')
            name, ext = os.path.splitext(file)
            
            # Controleer of de bestandsnaam eindigt op een van de targets
            if name.endswith(targets):
                file_path = os.path.join(root, file)
                try:
                    os.remove(file_path)
                    print(f"Verwijderd: {file_path}")
                    verwijderd_count += 1
                except Exception as e:
                    print(f"Fout bij verwijderen van {file_path}: {e}")

    print(f"\nKlaar! Totaal aantal bestanden verwijderd: {verwijderd_count}")

# GEBRUIK: Vul hier het volledige pad naar je map in
# Gebruik voor Windows een 'r' voor het pad: r"C:\Foto's\Project"
mijn_map = "src/main/resources/data/ohthetreesyoullgrow/structures/features/trees" 

# Voer de functie uit
verwijder_rotatie_bestanden(mijn_map) # Haal het # weg om het echt uit te voeren
#print("Script staat in test-modus. Haal het laatste hekje weg om het uit te voeren.")