*****
Übung
*****

Übung 1
=======

.. toctree::
    :caption: Neue Internetanwendungen - Übung

    jupyter_nb/nia_r_tutorial
    jupyter_nb/exercise_1.1


1.1.4
^^^^^

Frage 1
"""""""


Frage 2
"""""""

- Verfügbare Bandbreite
- Anzahl der Verbindungen zu anderen Knoten

Frage 3
"""""""

- Regelung der Bandbreite
- Ermittlung von neuen Supernodes

Frage 4
"""""""

Supernodes bilden ein Overlay, da Sie den Datenverkehr an die Klienten regeln und anderen Supernode bekannt sind. Erst durch Supernodes wird das eigentlich Netz realisiert.


ECDF-Beispiel
^^^^^^^^^^^^^

Siehe `Wikipedia Empirische Verteilungsfunktion <https://de.wikipedia.org/wiki/Verteilungsfunktion#Empirische_Verteilungsfunktion>`_

Ausgangsdaten:
6, 2, 7, 12, 1, 11, 1, 1, 2, 3

sort:
1, 1, 1, 2, 2, 3, 6, 7, 11, 12

table:

1  2  3  6  7  11  12
3  2  1  1  1  1   1

cumsum   3  5  6  7  8  9   10
/10      .3 .5 .6 .7 .8 .9  .10

