Übungsblatt 9 – XML und JSON
============================

Aufgabe 1: Theorie
------------------


1. Ordnen Sie die folgenden Begriffe einander zu und beschreiben Sie kurz, worum es sich dabei handelt: XML, SGML, DTD, XML Schema, DOM, JSON

+------------+-------------------------------------------------------------------------+
| Begriff    | Bedeutung                                                               |
+============+=========================================================================+
| XML        | Extensible Markup Language. Durch Tags in einer Baumstruktur            |
|            | gegebene Daten, die Attribute, Text und Unterknoten enthalten können.   |
+------------+-------------------------------------------------------------------------+
| SGML       | Standard der die Grundlage für andere Markupsprachen bildet             |
+------------+-------------------------------------------------------------------------+
| DTD        | Definiert eine Struktur, welche Form eine XML haben darf                |
+------------+-------------------------------------------------------------------------+
| XML-Schema | Ähnlich zu DTD, verwendet aber selbst XML zur Definition und            |
|            | enthält genauere Angaben, wie eine XML aufgebaut sein kann              |
+------------+-------------------------------------------------------------------------+
| DOM        | Document Object Model. Das DOM gibt die aktuelle Struktur einer SGML an |
+------------+-------------------------------------------------------------------------+
| JSON       | Durch Klammern (``{``, ``[``, ``]``, ``}``) verschachtelte Daten, die   |
|            | jeweils aus Schlüssel und Wertpaaren bestehen. Ähnlich zu XML, aber     |
|            | schlanker im Datenverbrauch                                             |
+------------+-------------------------------------------------------------------------+

2. Nennen und Beschreiben Sie die möglichen Ergebnisse einer XML-Validierung.

Wenn XML-Daten validiert werden, wird zunächst die Struktur geprüft. Wenn sich keine Fehler ergeben haben werden, sofern gegeben, die Daten anhand einer DTD oder eines XML Schemas validiert, um Datenvalidität zu prüfen.

- gültig
- wohlgeformt
- ungültig

3. Wofür wird XSLT verwendet?

XSLT (XML Stylesheet Language Transformation) wird verwendet um die Struktur einer XML in eine andere zu überführen.

4. Wie können Referenzen in JSON-Dokumenten realisiert werden?

Über das Schlüsselwort ``$ref`` können Knoten im Baum über einen *URI encoded JSON Pointer* referenziert werden.

6. Nehmen Sie Stellung zu folgenden Aussagen:

    a. XML ist in seiner Mächtigkeit durch fest definierte Tags beschränkt.

Falsch. Grundsätzlich gibt es keine festen Tags in XML. Sie müssen lediglich eine bestimmte Form besitzen, sind allerdings frei definierbar.

    b. DTD ist eine Sprache zum Austausch von konkreten Daten, deren Struktur in XML beschrieben wurde.

Falsch. DTD verwendet kein XML zum Aufbau. Zudem beinhaltet DTD keine Daten, sondern legt eben diese Struktur fest.

    c. XML ist eine weit verbreitet Grundlage für die Speicherung und den Austausch von Daten sowie vieler weiterer Anwendungsfälle?

Richtig.

    d. JSON-Schema und XML-Schema werden mit der gleichen Syntax, welche durch die OMG standardisiert wurde, definiert.

Falsch. XML-Schema wird vom W3C standardisiert, JSON-Schema durch die IETF. XML-Schema verwendet XML als Sprache, JSON-Schema nutzt JSON.

    e. XML-Schema definiert die Sprache von XML nicht jedoch die Sprachkonstrukte wie z.B. ``<``, ``>`` usw.

Richtig.

Aufgabe 2: DTD
--------------

Aufgabe 2.3
^^^^^^^^^^^

1. Wenn ``<link manager="person1" />`` zu ``<link manager="1" />`` ändert sich nichts beim Parsen, die Daten sind weiterhin valide.
2. Bei undefinierten Elementen tritt folgender Fehler auf: Elementtyp "[typ]" muss deklariert werden. Dasselbe passiert bei Attributen.
3. Die XML kann geparst werden, obwohl diese Fehler enthält, wenn die Definition des Schemas im Kopf der XML entfernt wird. Allerdings wird dann lediglich die Struktur beim Parsen geprüft und nicht die Daten. Sollte eine XML nicht wohl geformt sein ist diese ungültig und kann nicht geparst werden. Sollten ungültige Daten enthalten sein, kann diese ggfs. noch geparst werden, aber es können Fehler bei der Typumwandlung der Daten entstehen.

Aufgabe 3: XML Schema
---------------------

Aufgabe 3.2
^^^^^^^^^^^

+---------------------+-----+------------+
|                     | DTD | XML-Schema |
+=====================+=====+============+
| Datentypen          | -   | X          |
+---------------------+-----+------------+
