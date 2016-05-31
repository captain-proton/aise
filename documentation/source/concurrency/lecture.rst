*********
Vorlesung
*********

Teil 1 (Automaten)
==================

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

- bei urgend können noch andere Zustände erreicht werden, aber es vergeht bzw. darf keine Zeit vergehen
- bei committed muss der Zustand sofort verlassen werden

Folie 11
^^^^^^^^

- zur Veranschaulichung in UPPAAL modellieren!

Folie 13
^^^^^^^^

- Im unteren Bild darf nicht geschaltet werden, da die Automaten synchronisiert sind

Folie 14
^^^^^^^^

- Beispiel 1: c wird zuerst gesendet, nicht d
- Beispiel 2: hier findet nicht deterministisches Verhalten statt, es ist nicht klar welcher Automat zuerst schaltet

Folie 19
^^^^^^^^

- Kein Deadlock möglich, da jeder Philosoph immer eine Gabel die er aufgenommen hat auch wieder ablegen

Folie 24
^^^^^^^^

- Es wird zufällig ein Automat gewählt der c? ausführen kann

Folie 25
^^^^^^^^

- Bei Broadcasts müssen nicht an alle Empfänger das Signal auch empfangen

Folie 27/28
^^^^^^^^^^^

- sind zur zeit leer
- hungrige Philosophen die ungerecht sind enthalten Deadlocks

Folie 46
^^^^^^^^

1. Der Automat schaltet so lange in L1, bis in L2 geschaltet werden kann. Die Schaltung nach L2 ist nicht deterministisches Verhalten
2. Der Automat wartet sechs Zeiteinheiten und dann nicht mehr in L1, nur noch in L2
3. Der Automat wartet 10 Zeiteinheiten und ist in L0 "gefangen"

- Die Schleife L0 -> L1 -> L0 kann unendlich oft durchgeführt werden

Folie 50
^^^^^^^^

- A3 und B3 kann erreicht werden, muss aber nicht wenn lange genug gewartet wird ohne dass A a! ausführt
- Wenn B in B2 schaltet kann A nicht mehr in A3 schalten

Folie 56
^^^^^^^^

1. chan a: Bei Transition von L0 -> L1 darf keine Zeit vergehen, da L0 implizit eine Uhr x = 0 und die Invariante x <= 0 erhält
2. urgend chan a: Jede Transition findet ohne Zeitverzögerung statt

Folie 58
^^^^^^^^

- x = 0 ist hier kein Guard, sondern die Zurücksetzung der Uhr, also ein Update

Folie 60
^^^^^^^^

- Die Zuweisung x\:=0 und x == 0 sind unnötig, da L2 als urgent deklariert ist


Teil 2 (Nebenläufiges Rechnen)
==============================

Ablaufmodell
------------

Folie 5
^^^^^^^

- Mathematische Formalitäten müssen nicht auswendig gelernt werden

Folie 22
^^^^^^^^

- Beispielfrage für die Klausur: Wo sehen Sie Konflikte im schreibenden Zugriff?

Folie 23
^^^^^^^^

- Bei der Alternative wird garantiert nur eine der Möglichkeiten ausgeführt, niemals beide
- der untere Zweig wird zwingend ausgeführt, da alle Vorgänger hier enden


Folie 24
^^^^^^^^

- die Anzahl der Ausgangsmarken ist für die Darstellung einer Alternative nicht relevant, es können zwei oder drei Marken für das Beispiel verwendet werden
- die Anzahl der Eingangsmarken verhindert ggfs. einen korrekten Programmablauf und ist daher genau zu wählen
