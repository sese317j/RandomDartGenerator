# RandomDartGenerator

Dies ist ein Generator für zufällige Dart Spiel verlaufs Daten in SQL.
Dieses Programm dient nur zum erstellen von Beispieldaten für eine Dart Spiel Webanwendung.

Momentan unterstützte Argumente:
- PathToDir: Pfad zum Speicherort der generierten SQL Dateien
- punkte_initial: Anzahl der Punkte zu beginn, normalerweise 501 oder 301
- multiplikator_out: Wahl des "Spielmodus" 1 -> Straight out, 2 -> Double Out, 3 -> Master Out
- debug: Wenn true werden die gesamten erstellten Daten in einer gut formatierten und nachvollziehbaren Art und Weise auf der Komandozeile ausgegeben
- spieler_ids: Int Array mit Spieler ID's, wenn z.b. Spieler 1, 2 und 5 teilnehmen sollen dann {1,2,5}
- anzahl_sets: Anzahl der Sets zum generieren
- anzahl_legs: Anzahl der Legs pro Set zum generieren (bei 2 Sets und drei Legs werden insgesammt 6 Legs generiert)
- spiel_string: Name von dem Generierten Spiel, wird benötigt für die Sets.sql Datei als referenz auf das Spiel
- wurf_offset: Offset für die ID's von den Würfen, wenn z.b. schon ids in der Wurftabelle vergeben sind
- zug_offset: Gleiches Prinzip wie bei Würfe, nur eben mit Zügen
- leg_offset: Gleiches Prinzip wie bei Würfe, nur eben mit Legs
- set_offset: Gleiches Prinzip wie bei Würfe, nur eben mit Sets
