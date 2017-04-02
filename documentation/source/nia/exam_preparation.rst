********************
Prüfungsvorbereitung
********************

.. toctree::

    exam_preparation/lecture_questions
    exam_preparation/exam_2016
    exam_preparation/formulary

Prüfungsfragen und -aufgaben
============================

Nicht dran kommt
^^^^^^^^^^^^^^^^

DHTs:

- Pastry
- Tapestry
- Kademlia

Verteilungen (Normal-, Erlang-, Binomial- ...)

One-Hop DHT: Was ist die mittlere Anzahl interner Hops?
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Wahrscheinlichkeit 0 hops: :math:`\frac{1}{n}`

Wahrscheinlichkeit 1 hop: :math:`\frac{n - 1}{n}`

Erwartungswert: :math:`\sum_{i = 0}^{1} i * P(X=i)`

= :math:`0 * \dfrac{1}{n} + 1 * (1 - \dfrac{1}{n})`

Lokalen Clustering Koeffizienten bestimmen
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Mean Value Analysis
^^^^^^^^^^^^^^^^^^^

siehe Tutorial was in der Vorlesung besprochen wurden (:math:`\lambda \mu`)

Was ist die durchschnittliche Stallingrate?

End-to-End Delay: Replikationsgrad
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Bei einer Paketwahrscheinlichkeitverlustrate, was ist die effektive Verlustwahrscheinlichkeit eines Sprachpakets:

:math:`p_{voice} = p_{loss}^R`

CDF
^^^

1. größerer Datendurchsatz
2. höhere Varianz
3. wie ist die CCDF

Locality Awareness
^^^^^^^^^^^^^^^^^^

Unstrukturiert: Wie kann man z.B. AS-Zugehörigkeit bei Filesharing berücksichtigen um Interdomain-Traffic zu sparen

Strukturiert: Wie kann man RTT in strukturierten Netzen berücksichtigen?

DHT
^^^

Chord ring ID berechnen und Finger aufzeigen

Erster Klausurtermin 2017
=========================

1. CCDF Ablesen

    a) welches system niedrigere latenz
    b) niedrigere varianz
    c) median und 90% quantil bestimmen

2. Chord

    a) One-Hop DHT mittlere Anzahl an Hops
    b) mittlere Verarbeitungsdauer einer Anfrage in One-Hop DHT
    c) Chord-Ring Peer mit Modulo-Hashfkt errechnen

3. Bitcoin

    a) Proof-Of-Work
    b) Inverse Hashfkt berechnen
    c) Anzahl BitCoins
    d) Dauer bis alle Bitcoins

4. Video Streaming

    a) Was passiert wenn Bandbreite kleiner Videobitrate? (Unterscheiden sie hierbei 3 Streamingansätze)
    b) mittlere Stallingdauer berechnen (Grafik wie F 3.77)

5. Graphen

    a) Graph gegeben: Degree, Clustering Coefficient, Degree Centrality, Globaler Clustering Coefficient bestimmen

Anki Lernkarten
===============

`Software <https://apps.ankiweb.net/>`_

:download:`Formelsammlung <exam_preparation/NIA-Formeln.apkg>`
