Wiederholungs-Übungsblatt 2
===========================

Aufgabe 1
---------

.. image:: solutions/wiederholungsblatt_2_aufgabe_1.svg

Aufgabe 2
---------

.. image:: solutions/wiederholungsblatt_2_aufgabe_2_state_chart.svg

.. image:: solutions/wiederholungsblatt_2_aufgabe_2_dfd.svg

Aufgabe 3
---------

.. image:: solutions/wiederholungsblatt_2_aufgabe_3.svg

Aufgabe 4
---------

Teilaufgabe a
^^^^^^^^^^^^^

+-----------------------------+---------------------------------------------------------------------------------------+
| Methode                     | Erläuterung                                                                           |
+=============================+=======================================================================================+
| visible balancing           | visuelle Überprüfung der Datenflüsse (Input und Output) in einzelnen Hierarchien      |
+-----------------------------+---------------------------------------------------------------------------------------+
| visual data store balancing | visuelle Prüfung, ob alle Zugriffe auf Datenquellen in den Hierarchien definiert sind |
+-----------------------------+---------------------------------------------------------------------------------------+
| data dictionary balancing   | Prüfung auf Konsistenz des DD bis hin zu Buchstabe und Ziffer                         |
+-----------------------------+---------------------------------------------------------------------------------------+

Teilaufgabe b
^^^^^^^^^^^^^

1. Der Kunde gibt zusätzlich Kundendaten ein
2. Umbenennung von ``Produktdaten`` in ``Versanddaten``
3. Umbenennung von ``Logistik`` in ````Logistikdienstleister``
4. zusätzliche Quelle ``Warenausgang`` mit dem lesen von Bestelldaten
5. zusätzliche Quelle ``Kreditinstitut`` mit den Datenflüssen ``Transaktionen`` und ``Kontodaten``
6. Umbenennung ``Finanzverwaltungsdaten`` in ``Steuerdaten``
7. Umbenennung ``Lieferant`` in ``Zulieferer``
8. Streichen des Datenflusses ``Lieferinformationen``
9. Neue Datenflüsse ``Abrechnungsdaten`` und ``Produktdaten`` vom ``Zulieferer`` an das System
10. Umbenennung des Datenflusses ``Preise`` in ``Verwaltungswünsche``

Aufgabe 5
---------

Teilaufgabe a
^^^^^^^^^^^^^

.. image:: solutions/wiederholungsblatt_2_aufgabe_5.svg

Teilaufgabe b
^^^^^^^^^^^^^

+--------------------+----------------------------------------------------------+
| Name               | Details                                                  |
+====================+==========================================================+
| Produkte           | {Produkt}                                                |
+--------------------+----------------------------------------------------------+
| Produkt            | Name + Preis + Währung                                   |
+--------------------+----------------------------------------------------------+
| Warenkorb          | {Produkt + Menge}                                        |
+--------------------+----------------------------------------------------------+
| Menge              | Ziffer                                                   |
+--------------------+----------------------------------------------------------+
| Währung            | ["EUR" / "Bitcoin"]                                      |
+--------------------+----------------------------------------------------------+
| Versandmethode     | ["Standard" / "Express"]                                 |
+--------------------+----------------------------------------------------------+
| Zahlungsmethode    | ["Lastschrift" / "Nachnahme" / "Paypal" / "Rechnung"]    |
+--------------------+----------------------------------------------------------+
| Kundenbestelldaten | Warenkorb + Adresse                                      |
+--------------------+----------------------------------------------------------+
| Adresse            | Straße + Hausnummer + PLZ + Ort                          |
+--------------------+----------------------------------------------------------+
| PLZ                | 5{Ziffer}5                                               |
+--------------------+----------------------------------------------------------+
| Ort                | {Buchstabe}                                              |
+--------------------+----------------------------------------------------------+
| Versanddaten       | Kundenbestelldaten + Versandmethode + Zahlungsmethode    |
+--------------------+----------------------------------------------------------+
| Kundendaten        | Vorname + Nachname + Benutzername + Passwort + {Adresse} |
+--------------------+----------------------------------------------------------+
| ...                |                                                          |
+--------------------+----------------------------------------------------------+

Aufgabe 6
---------

Teilaufgabe a
^^^^^^^^^^^^^

.. image:: solutions/wiederholungsblatt_2_aufgabe_6.svg

+--------------------------+------------------------------------------------+
| Name                     | Details                                        |
+==========================+================================================+
| Bonitätsprüfungsergebnis | ["positiv" / "negativ"]                        |
+--------------------------+------------------------------------------------+
| Kundenprüfungsdaten      | Vorname + Nachname + Adresse                   |
+--------------------------+------------------------------------------------+
| Bonitätsdaten            | Kundenprüfungsdaten + Bonitätsprüfungsergebnis |
+--------------------------+------------------------------------------------+
