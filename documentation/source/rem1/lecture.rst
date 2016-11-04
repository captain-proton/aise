*********
Vorlesung
*********

**Moodlezugangsschlüssel**: ``WiSe16REM1``

L01 - Introduction and Fundamentals
===================================

Tip: In der ersten Vorlesung ist nur grob auf den genauen Inhalt der Folien/Seiten eingegangen worden. In jedem Fall ist es sinnvoll die Folien zu lesen.

Seite 8
^^^^^^^

Fragekarte zu den drei Dimensionen des Requirements Engineering erstellen. Die drei Dimensionen sollten im Detail bekannt sein.

- Content

    + Wie weit ist das System verstanden
    + Ergebnis: Am Ende sollen alle Anforderungen erfasst und verstanden sein

- Documentation

    + Am Anfang wage gehalten, z.B. als Notizen
    + Am Ende bis ins letzte Detail festgehalten in den erforderlichen Spezifikationen (UML etc.)


- Agreement

    + Am Anfang bestehen unterschiedliche Ansichten und Meinungen auf das System
    + Am Ende müssen alle Beteiligten (Stakeholder) den Anforderungen zustimmen

Seite 12
^^^^^^^^

Klausurfrage zur ersten Folie. Bei einem Fortschritt in einer Dimension kann es durchaus zu einem Rückschritt in einer anderen Dimension kommen.

Seite 13
^^^^^^^^

Erste Folie übersetzen und verstehen!

**Anforderungstypen**:

1. Funktion
2. Qualität
3. Rahmenbedingungen

Nicht funktionale Anforderungen sind noch sehr abstrakt und können nicht im System definiert werden. In sonstiger Literatur wird oft lediglich zwischen funktionalen und nicht funktionalen Anforderungen unterschieden.

*funktional* = was
*qualität* = wie

Seite 17
^^^^^^^^

*Functional suitability*: Abdeckung der Anforderung durch die tatsächlich implementierte Lösung.

Seite 18
^^^^^^^^

*R-12* (keine gute Qualitätsanforderung)

- Es gibt ein Problem bei der Definition von *einfach*
- Definition des Hausbesitzers

Seite 19
^^^^^^^^

*Rahmenbedingungen*

- Gesetze
- Deadline
- Nachbarsysteme (Bsp.: Grundlage sind die Daten einer PostgreSQL Datenbank)

Fragekarte der Typen von Folie 2 erstellen.

Unterschied Anforderung - Rahmenbedingung: Rahmenbedingung sind zunächst nicht änderbar, Anforderungen schon.

Seite 22
^^^^^^^^

Ein Overlay muss seine Knoten und deren Struktur kennen.

Beispiel: Der Kurs im Sitzplan repräsentiert die Buchstaben A-Z. Eine Simulation ist der Versand einer Nachricht von C bis K.

Seite 23
^^^^^^^^

Ein Overlay ist eine Abstraktion der phsysischen Schicht.

Seite 24
^^^^^^^^

IP Multicast hat z.B. den Nachteil, dass es kein Provider aus zu hohen Kosten nicht unterstützt.

Seite 25
^^^^^^^^

Organisation des Overlays muss gewährleistet werden:

- Wegfallen der Knoten
- Hinzufügen von Knoten


Seite 27
^^^^^^^^

- "Vergiftung" des Overlays
- Physikalische Struktur wird nicht durch das Overlay abgedeckt

    + z.B. Geschwindigkeit wird nicht berücksichtig

- Energie (Mobilfunkgeräte)

    + z.B. während der Kommunikation brechen Knoten weg

Seite 28
^^^^^^^^

Hier ist ein Distributed Hash Overlay dargestellt.

Seite 35
^^^^^^^^

Die unterschiedlichen Farben repräsentieren die öffentlichen Schlüssel der einzelnen Onionrouter.


L02 - Introduction and Fundamentals
===================================

Seite 14
^^^^^^^^

``Essence``: Der Kern eines Systems enthält **alle** Anforderungen die ein System benötigt, um seinen Zweck erfüllen zu können.
``Incarnation``: Die Verköperung bezeichnet alle *Dinge* (Personen, Geräte etc.) die daran teilhaben den Kern des Systems umzusetzen.
