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


Part03 Data (Base) Modeling
---------------------------

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

Part04 Object relational SQL
----------------------------

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

Folie 56
^^^^^^^^

- ``EmployeeType`` muss nicht zwingend zusätzliche Methoden implementieren oder überschreiben, sondern wird zur Strukturierung der Tabellen erstellt

    + Bei Views spielt dieser Typ allerdings eine größere Rolle
    + Bei Sichten werden Untertabellen bei der Betrachtung mit einbezogen

- Siehe auch Folie 57 hierzu

Folie 59
^^^^^^^^

- Views befinden sich nicht im Sekundärspeicher, sondern nur im Hauptspeicher
- Eine Sicht muss! einen bestimmten Typ enthalten
- Der Grund warum ``SYSTEM GENERATED`` nicht erlaubt ist, ist genau geklärt
- **keine Datenbank setzt den objektrelationalen Standard nach Definition um**

Folie 61
^^^^^^^^

- in die Statements ist ein wenig Semantik eingefügt

    + mit *Euro(10000)* wird normalerweise lediglich die Spalte angesprochen

- ``ONLY`` betrachtet nur die Wurzel, alle Subtypen werden ausgelassen
- Wenn eine Einschränkung auf die Spalten erfolgt wird ein neuer Typ erstellt, der zuvor definiert werden muss

    + Die Verwendung von ``SELECT *`` ist hier stark empfohlen
    + Es darf lediglich genau eine Tabelle adressiert werden! Verbundoperationen (Joins) sind nicht erlaubt

- Eine View kann mit hoher Wahrscheinlichkeit nicht in die bestehende Typhierarchie eingepflegt werden, da eine View in den meisten Fällen nur eine bestimmte Auswahl der Attribute enthält

- ``CARDINALITY`` zählt die Verweise auf die Referenzen
- Verbundoperationen nur über Referenzen zu realisieren
- Wenn Verbundoperationen (bei ORM) eingesetzt werden müssen sind Fehler in der Modellierung vorhanden

Folie 64
^^^^^^^^

- streng genommen findet keine Mengenerweiterung statt, da ausschließlich Einschränkungen vorgenommen werden können

Folie 69
^^^^^^^^

Einerseits Schema-Modelleriung andererseits Anfragen auf den Datenbestand. Grundsätzlich gesehen 2 unterschiedliche Aufgaben. Besser wäre eine einheitliche Sprache, was aber so nicht sein muss.

Folie 70
^^^^^^^^

2 Features, die im Umfeld des Objektrelationalen SQL gekommen sind. Sind aber auch schon in vielen Dialekten vorhanden.

1. Eigene ID generieren wollen -> Identity columns ... man kann angeben, wie man sich das grundsätzlich vorstellt. Start with, increment by ... etc.

Vorteile: Man kann Kriterien angeben bzw. man kann es noch genauer definieren ... bei welchem Wert die generated Id starten soll, welche Schritte dieser gehen soll etc.

2. Man kann Werte aus anderen Spalten kombinieren (wie hier addieren)

Folie 72
^^^^^^^^

mit only bezieht man sich nur auf das Wurzelelement ... andere werden nicht berücksichtigt

Folie 73
^^^^^^^^

Some tables. Man kann damit bestimmen, welche Typen von Personen man zurückgegeben haben will (so hab ich es verstanden)

Folie 74
^^^^^^^^

übersprungen ..

Folie 75
^^^^^^^^

alternative Version zu Folie 73

Folie 76
^^^^^^^^

Man kann beliebige Unterteile eines Baumes auswählen. Zwei Möglichkeiten darauf zuzugreifen .. DEREF-Variante kann immer verwendet werden.

Folie 77
^^^^^^^^

Tabelle ist eingebettet in ein Objekt (AdressType)

Man muss unterscheiden, ob man sich auf eine Referenz bezieht oder auf eine Einbettung

Folie 78
^^^^^^^^

Direct embedded ... abhängig und exklusiv

als ROW-Datentyp direkt eingebunden

Wir binden hier keinen abstrakten Datentyp ein sondern eine Datenstruktur. Objekt hat keine Identität.

Folie 79
^^^^^^^^

Erst Typ deklarieren und in einer anderen Tabelle nutzen. Objekte mit eigener OID und den üblichen Methoden.

Folie 80
^^^^^^^^

Aus Sicht der Anfrage ist es so, dass Sie gleichbehandelt werden .. wenn man darauf zugreifen will müssen wir einsteigen und einen Pfad definieren um zu so einem Objekt hinzukommen. Das machen wir mit der Punktnotation (addresses.city) ... mehrere Punkte erlaubt (z.B. addresses.city.name) beliebig viele Verschachtelung möglich.

Folie 81
^^^^^^^^

Array von Referenzen ..

Folie 82
^^^^^^^^

Statt dem Punkt ein Pfeil.

Folie 83
^^^^^^^^

Steigen bei Empolyee ein machen aber ein DEREF auf manager. Alle Daten vom Typ manager.

Folie 84
^^^^^^^^

Wann sind zwei Verweise kompatible? Sie müssen vom selben Typ sein .. nur dann!
Wenn Sie auf Objekte vom selben Datentyp verweisen.

Folie 85
^^^^^^^^

Zu viel zum mitschreiben. Er ist abgeschweift.

Folie 87
^^^^^^^^

Unnest funktioniert nur auf der obersten Ebene.


Folie 88
^^^^^^^^

Was macht das unnest?

Wir klopfen nur auf der obersten Ebene flach. Sollte ein Objekt in der obersten Ebene komplex sein, bleibt es komplex.

Folie 89
^^^^^^^^

Es wird nicht unterschieden, ob wir es mit der independent exclusive Variante zu tun haben oder mit der dependent exclusive.


Part05 XML
----------

Einführung
^^^^^^^^^^

**Semistruktutierter Inhalt**:

.. code-block:: xml

    <helloworld>
    Hier ist mixed content in einer bestimmten <form>Form<form> enthalten.
    </helloworld>

**Stark strukturierter Text**:

.. code-block:: xml

    <helloworld>
        <asdf>inhalt</asdf>
        <qwer>aber immer</qwer>
    </helloworld>

**Ungültig** (nicht wohl geformt):

.. code-block:: xml

    <source>
        <code>
    </source>

DTD (data type definition)
^^^^^^^^^^^^^^^^^^^^^^^^^^

- **ALT!!**: besser ist die Verwendung von XML-Schema
- alle gängigen Browser sind XML Prozessoren, können somit gültige XML darstellen und Fehler annotieren

.. code-block:: text

    <!ELEMENT source (#PCDATA)>

``#PCDATA``: parsed character data

- DTD legt fest was minimal vorhanden sein muss
- selbst wenn Elemente weggelassen werden stellen Browser gültige XML-Daten dar

Folie 10
^^^^^^^^

- ein oder (``|``) ist ein exklusives oder, beide Elemente dürfen nicht vorkommen

Folie 13
^^^^^^^^

- ``CDATA``: character data, Erklärung folgt
- zur Angabe von Attributwerten sind lediglich \" erlaubt

    + *Gültig*: ``<el attr="value"/>``
    + *Ungültig*: ``<el attr=`value`/>``

Folie 16
^^^^^^^^

- ``#REQUIRED``: benötigt
- ``#IMPLIED``: optional
- ``#FIXED "value"``: konstanter Inhalt, allerdings optional

Folie 17
^^^^^^^^

- ``IDREF``: Referenz auf eine ``ID`` im Dokument, der Typ ist irrelevant, kann also in jedem beliebigem Tag enthalten sein

Folie 20
^^^^^^^^

- Über ``ENTITY`` können Textersetzungen vorgenommen werden

    + Bei ``PCDATA`` ist ein einzelnes ``&`` nicht erlaubt

.. code-block:: xml

    <!ENTITY writer "Jan Egil Refsnes.">
    <!ENTITY copyright "Copyright XML101.">

    <in>
        <author>&writer; &copyright;</author>
    </in>

    <out>
        <author>Jan Egil Refsnes. Copyright XML101.</author>
    </out>

Das P in PCDATA bedeutet, dass der Inhalt vom XML-Parser durchaus analysiert wird. Für die Praxis bedeutet dies, dass im Inhalt eines so definierten Elements die `Regeln für Zeichen und Zeichenkodierungen <https://wiki.selfhtml.org/wiki/XML/Regeln/Zeichen>`_ zu beachten sind.

Siehe `Elementtypen mit Zeicheninhalt definieren <https://wiki.selfhtml.org/wiki/XML/DTD/Elemente_und_Verschachtelungsregeln>`_

Folie 23
^^^^^^^^

- ``NDATA``: nicht geparste Daten, z.B. Binärdaten

Folie 30
^^^^^^^^

- Wohlgeformt ...:

    + Ein einziges Wurzelelement
    + Keine Verschachtelung von Tags (``<a><b></a></b>``)
    + Bei doppelten Attributen wird mehr als eine Syntaxprüfung vorgenommen

Folie 34
^^^^^^^^

- Ob eine Eigenschaft als innerer Tag oder als Attribut deklariert wird ist nicht ganz klar

    + Aus Sich der Modellierung ist beides korrekt

Beispiel:

.. code-block:: xml

    <Book ISBN="42"></Book>

.. code-block:: xml

    <Book>
        <ISBN>42</ISBN>
    </Book>

Folie 35
^^^^^^^^

- Probleme bei zyklischen Verwendungen unter der Verwendung IDs

    + IDs sind eindeutig im gesamten Baum

Beispiel:

.. code-block:: xml

    <!ELEMENT publications (book)+>
    <!ELEMENT book (references)>
    <!ATTLIST book isbn ID #REQUIRED>
    <!ELEMENT references (publications)>

    <publications>
        <book isbn="42">
            <references>
                <publications>
                    <!-- nicht erlaubt -->
                    <book isbn="42">
                    </book>
                </publications>
            </references>
        </book>
    </publications>

Folie 41
^^^^^^^^

- `XLink Beschreiung <https://wiki.selfhtml.org/wiki/XML/XLink>`_

Folie 45
^^^^^^^^

- ``xlink:href="band3.xml#//eintrag@stichwort='Informationstheorie'"``

    + ``//``: irgendein Tag
    + ``eintrag``: mit dem name eintrag
    + ``@stichwort``: mit dem Attribut *stichwort*
    + ``='Informationstheorie'``: und dem Wert Informationstheorie

XLink / XPointer
^^^^^^^^^^^^^^^^

XPointer oder XML Pointer Language ist eine vom World Wide Web Consortium (W3C) entwickelte Anfragesprache, um Teile eines XML-Dokumentes zu adressieren. Es handelt sich dabei um eine Erweiterung der `XPath <https://de.wikipedia.org/wiki/XPath>`_-Spezifikation, mit der sich ebenfalls Teile eines XML-Dokumentes adressieren lassen.

Folie 18
********

- Bei der Verwendung von Referenzen auf andere Dokumente können Probleme z.B. bei IDs entstehen, die in beiden Dokumenten verwendet werden


Part08 XML-Schema
-----------------

Folie 5
^^^^^^^

- HTML

    + erdacht zur Darstellung von Inhalten am Bildschirm im Gegensatz zu XML

- XML

    + beschränkt sich zunächst auf die Strukturbeschreibung

Folie 9
^^^^^^^

- In Datenbanken ist der Namensraum gegeben durch das Schema der DB + Name der Tabelle

    + Bsp: <dbname>.<schema>.<tablename> -> UDE.Lectures.Course
    + Eindeutigkeit garantiert


- Widerverwendbarkeit bei XML auf hoher Ebene
- Eindeutigkeit in XML wird durch Namensräume festgelegt
- In der Klausur wird auf den Header in XML-Schema nicht genauer eingegangen, es müssen keine genauen Angaben zu den Namensraumspezifika erstellt werden

    + ``<xsd:schema>`` genügt

Folie 10
^^^^^^^^

- Elemente beschreiben eher Inhalte
- Attribute beschreiben eher wie Inhalte dargestellt werden
- In Datenbanken ist eine Definition eine Schemadefinition

    + Bsp Gehalt: In der DB definiert durch das Schema, in der XML beschrieben durch ein Attribut (``USD`` | ``EUR``)

- In XML muss durch die Programmlogik die Unterscheidung beachtet werden
- Eine Definition des Datentyps in XML-Schema ist nicht zwingend notwendig

Folie 17
^^^^^^^^

- ``xsd:sequence``: die Ordnung ist vorgegeben

Folie 32
^^^^^^^^

- Integritätsbedingungen sind in Grundlagen für die Klausur wichtig

    + Bsp.: ``<xsd:attribute name="lang" type="xsd:string" use="required"/>``

Folie 33

- kein ``min`` bzw. ``max`` lediglich

    + ``minInclusive`` und ``minExclusive``
    + ``maxInclusive`` und ``maxExclusive``

Folie 45-47
^^^^^^^^^^^

+----------------+------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
| ``whiteSpace`` | Definition                                                                                                                                                                                                             |
+================+========================================================================================================================================================================================================================+
| preserve       | XML processor WILL NOT remove any white space characters                                                                                                                                                               |
+----------------+------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
| replace        | XML processor WILL REPLACE all white space characters (line feeds, tabs, spaces, and carriage returns) with spaces                                                                                                     |
+----------------+------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
| collapse       | XML processor WILL REMOVE all white space characters (line feeds, tabs, spaces, carriage returns are replaced with spaces, leading and trailing spaces are removed, and multiple spaces are reduced to a single space) |
+----------------+------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+

Folie 55
^^^^^^^^

- Polymorphismus spielt in XML keine Rolle, da keine Methoden deklariert werden!
- Verweise zu anderen Typen sind überall hin möglich, d.h. ein komplexer Datentyp kann in der Hierarchie wieder auftauchen

Folie 72
^^^^^^^^

- bei ``maxOccurs`` ist nicht zwingend eine Ziffer benötigt, es kann auch ``unbounded`` enthalten sein, um beliebig viele Elemente definieren zu können

Folie 77
^^^^^^^^

- Gruppen (``xs:group``) können einfache Gruppierungen von Elementen festlegen

    + Im Gegensatz zu komplexen Typen können diese nicht als ``type`` von anderen Elementen verwendet werden


Part09-01 XML & Datenbanken - XPath und XQuery
----------------------------------------------

Folie 4
^^^^^^^

- In der Vorlesung wird davon ausgegangen, dass jede besprochene Operation auf einem einzelnen XML-Dokument durchgeführt wird

Folie 9
^^^^^^^

- In SQL keine Ordnung, bei XML schon (``preceding``...)

Folie 15
^^^^^^^^

- Im Gegensatz zu SQL sind strukturbedingte Abfragen möglich

Folie 25-41
^^^^^^^^^^^

- wird weggelassen


Part09-02 XML & Datenbanken - XPath und XQuery
----------------------------------------------

Folie 4
^^^^^^^

- ``RETURN`` liefert reinen Text ohne Verwendung von Methodenaurufen

Folie 17
^^^^^^^^

- Die ``LET``-Klausel bringt Mengenorientierung in die Abfrage

    + Es wird direkt eine Menge von Elementen geliefert

Folie 19
^^^^^^^^

- Eine ID ist im Sinne von XML immer ein Attribut!
- IDREF ist **nicht typisiert** im Gegensatz zu SQL

    + es kann ein Verweis auf jeden beliebigen Knoten im Dokument sein

Folie 21
^^^^^^^^

- Einführende ``LET``-Klausel holt nur das Dokument

    + Für die Klausur kann auch direkt in der ``FOR``-Klausel das Dokument adressiert werden

- ``LET`` und ``FOR`` können beliebig oft und in beliebiger Reihenfolge vorkommen
- ``$joe`` beinhaltet die dereferenzierte ``BAR``
- in der ``WHERE``-Klausel kann bei Zugriff auf eine Menge (``$joePrice``) auf einen bestimmten Wert verglichen werden
- für die Klausur ist die Syntax gerade für XML nicht so wichtig wie in SQL

Folie 22
^^^^^^^^

- Die zweite ``LET``-Klausel ist optional, die Abfrage könnte auch in einer Abfrage erfolgen

Folie 32
^^^^^^^^

- ``UNION`` in XML ist wie in SQL ``DISTINCT``

Folie 44
^^^^^^^^

- ``.FTCONTAINS "adsf"`` soll als Funktion genutzt werden in der Syntax ``contains()``
