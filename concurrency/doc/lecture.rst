Automatenkopplung
-----------------

- UPPAAL unterstützt keine asynchrone Kommunikation

Folie 48
^^^^^^^^

- bei synchronen Automaten kommen häufiger Deadlocks vor als bei asynchronen
- race conditions sind anfälliger für asynchrone Automaten

Folie 77
^^^^^^^^

- Deadlock
    + kein Folgezustand kann aus einem Zustand erreicht werden
    + lokaler Deadlock: bestimmte Automaten sind noch in der Lage zu kommunizieren
    + globaler Deadlock: nix geht mehr!

Folie 106
^^^^^^^^^

- Unterscheidung zwischen syntaktischen und semantischen Fehlern

Folie 109/110
^^^^^^^^^^^^^

- In der Vorlesung wurde eine Zustandsraumexploration durchgeführt

Folie 113
^^^^^^^^^

- unerwarteter Empfang = Nachricht kann im aktuellen Zustand nicht verarbeitet werden

Folie 118
^^^^^^^^^

- beide Darstellungsformen der Automaten sind korrekt

    + bei der in grau dargestellten Art muss hinzu notiert werden, dass eine asynchrone Kommunikation besteht


Folie 121
^^^^^^^^^

- Zustand 3 kann nie erreicht werden

Folie 122
^^^^^^^^^

- Notation erfolgt auf Basis der Definition eines synchronen Automaten

Folie 128
^^^^^^^^^

- wichtiger Begriff: Prädikat!


Zeitbehaftete synchrone Automaten
---------------------------------

Folie 10
^^^^^^^^

- bei urgend können noch andere Zustände erreicht werden, aber es vergeht keine Zeit
- bei committed muss der Zustand sofort verlassen werden

Folie 11
^^^^^^^^

- zur Veranschaulichung in UPPAAL modellieren!
