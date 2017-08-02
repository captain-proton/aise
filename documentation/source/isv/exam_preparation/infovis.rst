.. role:: quote
    :class: quote

.. |winkingsmiley| raw:: html

    &#12485;

.. |rightarrow| raw:: html

    &#x2192;

.. |leftarrow| raw:: html

    &#x2190;

.. |middlearrow| raw:: html

    &#x2194;

Informationsvisualisierung
==========================

Multivariate Daten und Zeitreihen
---------------------------------

Definition Metapher
^^^^^^^^^^^^^^^^^^^

:quote:`Eine Metapher ist eine rhetorische Figur, bei der ein Wort nicht in seiner wörtlichen, sondern in einer übertragenen Bedeutung gebraucht wird, und zwar so, dass zwischen der wörtlich bezeichneten Sache und der übertragenen gemeinten eine Beziehung der Ähnlichkeit besteht.`

Beispiel: Hat der Hausdrache heute schon wieder die übliche Verwarnung ausgesprochen.

Der Hausdrache ist hier die Metapher |winkingsmiley|

*Metaphern in der Informatik:*

- Bäume
- Städte
- Federn

Symbole oder Piktogramme eignen sich gut zur Darstellung von Metaphern (Bsp. Papierkorb). DIe Bildsprache Isotype wird z.B. häufig zur Vermittlung von statistischen Informationen verwendet.

Multivariate Daten
^^^^^^^^^^^^^^^^^^

Multivariate Daten enthalten mehrere Variablen pro Objekt. Sind als Tabelle einfach darzustellen. Beispiel Datensatz ``mtcars``

+----------------+------+-----+-------+-----+------+-------+-------+----+----+------+------+
|                | mpg  | cyl | disp  | hp  | drat | wt    | qsec  | vs | am | gear | carb |
+================+======+=====+=======+=====+======+=======+=======+====+====+======+======+
| Mazda RX4      | 21.0 | 6   | 160.0 | 110 | 3.90 | 2.620 | 16.46 | 0  | 1  | 4    | 4    |
+----------------+------+-----+-------+-----+------+-------+-------+----+----+------+------+
| Mazda RX4  Wag | 21.0 | 6   | 160.0 | 110 | 3.90 | 2.875 | 17.02 | 0  | 1  | 4    | 4    |
+----------------+------+-----+-------+-----+------+-------+-------+----+----+------+------+
| Datsun 710     | 22.8 | 4   | 108.0 | 93  | 3.85 | 2.320 | 18.61 | 1  | 1  | 4    | 1    |
+----------------+------+-----+-------+-----+------+-------+-------+----+----+------+------+

Zeilen enthalten die Objekte, Spalten die Variablen. Eine zusätzliche Dimension wäre denkbar, wenn man Versionen der Objekte hinzu nimmt.

Deskriptive Statistik
^^^^^^^^^^^^^^^^^^^^^

:Mittelwert: Durchschnitt
:Median: mittlerer Wert
:Quartil: wie viele Werte liegen kleiner gleich X, z.B. beim 25% Quartil liegen 25% der Werte kleiner gleich der Gesamtheit
:Modus: häufigste Wert der Stichprobe
:Standardabweichung: Streuung um den Mittelwert bei Normalverteilung
:Standardfehler: Wurzel der Varianz

Diagramme
^^^^^^^^^

- Boxplot
- Histogramm
- Streudiagramm (Scatterplot)
- Star Plot
- Chernoff Faces
- Parallele Koordinaten


Zeitreihen
^^^^^^^^^^

Darstellung von Variablen mit zeitlicher Dimension eher in ``time-to-space`` (Zeitleiste) oder in ``time-to-time`` (Animation).

- Lineare Zeitachsen

    + Liniendiagramme
    + Gestapelte Flächendiagramme (ThemeRiver)
    + Small Mutiples
    + Horizon Graphs (platzsparend)

        * Einfärbung der negativen Werte
        * Mirror oder Offset der negativen Variablen
        * Wiederholung der Prozedur je nach Anzahl der Mirror

- Sparklines

    + Einfache, datenintensive wortgroße Grafiken. Häufig verwendet in Tabellen als Linien- oder Balkendiagramm.

- Zyklische Zeitachsen
- Trajektorien

    + Ort über die Zeit aufgezeichnet
    + 2D-Raum + 1D Zeit als Linie

Mengenstrukturen und Hierarchien
--------------------------------

Durch die Abstraktion von multivariaten Daten lassen sich diese auf spezielle Eigenschaften besser test/darstellen und interpretieren. Beispiel: Darstellung der Objekte in einem Koordinatensystem als Punkte.

Algorithmen:

- Hauptkomponentenanalyse
- t-distributed Stochastic Neighborhood Embedding (t-SNE)

Clustering
^^^^^^^^^^

- Gruppierung ähnlicher Objekte zu Clustern (Mengen)
- Partitionierung, z.B. ``kmeans``

    1. Setzen von n zufälligen Mittelpunkten im n-dimensionalen Raum
    2. Zuordnung der Punkte zum nächst gelegenen Mittelpunkt
    3. Position der Mittelpunkte verschieben auf den berechneten Mittelpunkt der zugeordneten Punkte
    4. Solange sich Änderungen ergeben zurück zu 2.


Mengenstruktur
^^^^^^^^^^^^^^

- Eine Mengenstruktur ist eine Gruppe von Mengen
- Eine Partition ist nicht leere Teilmenge, so dass jedes Objekt genau einer Partition zugewiesen wird

Diagramme:

- Euler-Diagramm
- Venn-Diagramm

Mengenstrukturen können gut als Overlay/Überlagerung genutzt werden.

Darstellung als Graph denkbar, Partition als Knoten mit mehreren Verbindungen zu einer neuen Partition.

Hierarchische Daten
^^^^^^^^^^^^^^^^^^^

:quote:`Hierarchie = Baum`

Baumelemente:

- Wurzel (Eingangsgrad = 0)
- innere Knoten (Ausgangsgrad > 0)
- Blätter (Ausgangsgrad = 0)

Beispiele:

- Stammbäume
- Unternehmenshierarchien
- Taxonomien
- Dateistruktur
- Vererbung

Visualisierungen
^^^^^^^^^^^^^^^^

- Knoten-Kanten-Diagramm (Node-Link)

    + Positionierung der Knoten frei wählbar
    + `Dendrogramm <https://bl.ocks.org/mbostock/4063570>`_

- Einrückung (Dateistruktur)
- Icicle Plot
- Treemap

Vergleich von Hierarchien
^^^^^^^^^^^^^^^^^^^^^^^^^

- Einfärbung

    + Brushing: Einfärbung nach Selektion mit der Maus
    + Linking: Verhalten von Punkten in jedem Diagramm hervorheben (z.B. Größenänderung und Positionierung)
    + `Linking and Brushing <http://www.infovis-wiki.net/index.php?title=Linking_and_Brushing>`_, vor allem genutzt zum Vergleich zweier Visualisierungen

- Verbindungskanten
- Animation
- Matrix
- Fusion (auch wieder Farben)
