*********
Vorlesung
*********

Struktur
--------

- Modellierung
- Objektrelationale Datenbanken
- XML-Schema
- XQuery
- (NoSQL)

Literatur
---------

Datenbanken im Einsatz : Analyse, Modellbildung und Umsetzung. (2015). Berlin [u.a.]: De Gruyter Oldenbourg.
`TWY9580 <http://primo.ub.uni-due.de/UDE:LocalUDE:UDEALEPH018376700>`_

Part02 Limitations
------------------

Folie 8
^^^^^^^

- schnell am Limit auf Grund der Normalisierungsregeln (Tabellen > 100)

Folie 9
^^^^^^^

- Redundanzen eliminieren
- Primarschlüssel
    + verhindert Annomalien
    + Wert um ein Tupel eindeutig zu identifizierung
    + darf sich nicht verändern
- Objektrelationale Datenbanken unterstützen keine Identitäten sondern nur Gleichheit
    + Identitäten werden durch den Nutzer sichergestellt und nicht durch das DBMS

Folie 13
^^^^^^^^

- Typ einer Beziehung ist dem DBMS grundsätzlich nicht bekannt <-> eine Beziehung ist lediglich existend

Folie 14-16
^^^^^^^^^^^

- horizontale oder vertikale Beziehung
- vertikal
    + jedes Attribut was in der Personen Tabelle ist, ist nicht in der Studententabelle
    + Foreign Key Beziehung
- horizontale
    + jedes Attribut ist auch in der refezierenden Tabelle
    + Identität ist nicht vorhanden
    + Person wird zu Student -> Kopieren der Daten

Folie 23
^^^^^^^^

- Objekte beschreiben durch ihre Methoden ihr Verhalten <- nicht darstellbar durch DBMS

Folie 28
^^^^^^^^

- Layered Architecture zur Kapselung von Komplexitäten
- Datenarchitektur: Wort -> Sektor -> ...
- OSI-Schichten-Modell

Folie 29
^^^^^^^^

- select queries -> views auf tabellen
- Updates auf Views problematisch


Folie 32
^^^^^^^^

- Zugriff auf Sekundärspeicher ist die teuerste Operation auf dem Rechner


Vorlesung Part03 Data (Base) Modeling
-------------------------------------

Folie 2
^^^^^^^

- Identifizierung der Objekte die zur Anwendung gehören
    + beschreiben der Objekte durch Attribute


Folie 6
^^^^^^^

- wird übersprungen


Folie 9
^^^^^^^

Unterschied zwischen Klasse und Typ wird hier in der Vorlesung nicht gemacht. In der Literatur ist keine einheitliche Trennung vorhanden.

- case 1: gültig
- case 2: gültig
- case 3: nicht gültig, keine explizite Typumwandlung bei der Zuweisung S := W + 5

Ein Rechner ist immer der Endlichkeit unterworfen!

2^(64 - 1) - 1 ist der Wertebereich für Integer auf 64 Bit Systemen

_Fallstrick_:
2^64 - 1 x 2^64 - 1 / 2^64 - 1 != 2^64 - 1

Es kommt auf die Reihenfolge der Operation an.


Folie 10
^^^^^^^^

Bei Definition von unterschiedlichen Datentypen in objektorientierten Programmiersprachen ist eine Überführung per se nicht möglich wie bei primitiven Datentypen.

Integer, Real: implizite Typumwandlung möglich
AnzahlRaeder, Alter: Typumwandlung nicht möglich


Folie 16
^^^^^^^^

- Version 2 der Definition wird ausgeklammert


Folie 18
^^^^^^^^

- tuples: DS
- set of tuples: Tabelle
- set of set of tuples: Schema


Folie 22
^^^^^^^^

- Reference counting bei Objekten. Findet in der Garbage collection Anwendung.

Folie 25
^^^^^^^^

- Adresse = Verweis auf ein zusätzliches Objekt
- Zugriff auf den Hauptspeicher ist immer wahlfrei
    + Die Ablage der Objekte in den Speicher spielt keine Rolle

- set of tuple = dependant/abhängig
    + Typdeklaration implizit festgelegt
- set of object = (in)dependant

- Objektorientierung != Objektrelational

    + Objektorientierung -> Festlegung von Verweisen

- Anmerkungen
    + address = dependant, exclusive; nicht dependant, shared
    + exactLocation = dependant, exclusive
    + wird aktualisiert!

Vorlesung Part04 Object relational SQL
--------------------------------------

Folie 8
^^^^^^^

- Es gibt einen eklatanten Unterschied zwischen dem Datentyp und einem Wertebereich

Folie 13
^^^^^^^^

- Hohe Redundanz bei Definition des suppliers
- Unterscheidung zwischen (un-)abhängig und exklusiv/gemeinsam bei der Modellierung wichtig (vor allem für die Klausur)

Folie 14
^^^^^^^^

- Verweise sind typisiert!

Folie 16
^^^^^^^^

- wird weggelassen

Folie 19
^^^^^^^^

- Set enthält keine Duplikate, ein Multiset schon

Folie 20
^^^^^^^^

- dependant/shared findet in der Java Garbage collection statt

Folie 22
^^^^^^^^

- Datenbanksystem und Kapselung ein Widerspruch insich, da durch die Kapselung Daten verschleichert werden

Folie 23
^^^^^^^^

- Trennung von der traditionellen Datenmodellierung und der objektorientierten
- Verbindung von traditioneller Modellierung zur objektrelationalen Modellierung nicht erwünscht/erlaubt

    + Entweder A oder B!


Folie 24
^^^^^^^^

- Create table:

    + Spezifizierung des Datentyps
    + Allozierung von Speicher/Container

Folie 25
^^^^^^^^

- Row Definition = Datendefinition
- Mit einem Typ gibt man grundsätzlich nur Methoden nach außen und keine Attribute
- Integritätsbedingungen können sowohl auf Tabellen als auch auf Typen spezifiert werden

    + Unterscheidung in der Vorlesung nicht relevant

Folie 27
^^^^^^^^

- Verwendung desselben Typs nicht des gleichen!

Folie 28
^^^^^^^^

- Spezifizierung: Definition eines Typs immer mit dem Suffix *Type*
- Beispiel:

    + CREATE TYPE ProductType AS ...
    + CREATE TABLE Product OF ProductType AS ...

Folie 29
^^^^^^^^

- OID zunächst eine Hauptspeicheradresse

+----------------------------+----------------------------+
| Primarschlüssel            | OID                        |
+============================+============================+
| Attribut in der Tabelle    |                            |
+----------------------------+----------------------------+
| Eindeutige Identifizierung | Eindeutige Identifizierung |
+----------------------------+----------------------------+

- Unterscheidung zwischen OID und Primarschlüssel wichtig


Folie 32
^^^^^^^^

+-------------------------+-------------------------------------+
| Definition              | Kommentar                           |
+=========================+=====================================+
| REF IS SYSTEM GENERATED | automatische Generierung vom System |
+-------------------------+-------------------------------------+
| REF USING <dataType>    | basiert auf der "alten" Definition  |
|                         | des Primarschlüssels und wird vom   |
|                         | Nutzer definiert                    |
+-------------------------+-------------------------------------+
| REF FROM                | wird im allgemeinen simuliert,      |
|                         | wie automatische Generierung des    |
|                         | Primarschlüssels                    |
+-------------------------+-------------------------------------+

- präferiert wird die erste Methode der automatischen Generierung durch das System

Folie 33
^^^^^^^^

- Eine OID ist eindeutig!!!
- Tabelle muss vom Typ X sein (REF(ProductType)) bei Verwendung von REF
- Schema.Typ.Attribut eindeutig
- *USER GENERATED* zwingend notwendig, obwohl es eine Wiederholung darstellt

Folie 34
^^^^^^^^

- im objektrelationalen lässt sich kein Primarschlüssel mehr definieren

Folie 37
^^^^^^^^

- Entgegen der Vermutung ist shallow equal schwergewichtiger als deep equal

    + bei shallow equal sind die Unterobjekte gleicher Identität (R3 und R4)

Folie 40
^^^^^^^^

- von Interesse sind *Methods*, Prozeduren und Funktionen werden sekundär behandelt
- mit row type ist NICHT der Typkonstruktor gemeint

Folie 42
^^^^^^^^

- Beispiel:

    + ``STATIC METHOD...`` ist die Methodendefinition
    + ``CREATE METHOD...`` enthält die Implementierung der Methode

Folie 44
^^^^^^^^

- Mit jeder Definition eines Attributs kommen implizit zwei Methoden, die den Wert des Attributs liefern und den Wert setzen (Property)

Folie 45
^^^^^^^^

- übersprungen
