Parking Assistant - Context
===========================

.. role:: underline
    :class: underline

Systemkontextobjekte
--------------------

1. Frei verfügbare Werkzeuge
^^^^^^^^^^^^^^^^^^^^^^^^^^^^

:Facette: *Usage*
:Eigenschaften: - Passform
:Erläuterung: Objekte mit denen es nicht möglich sein soll das System zu reparieren/anzupassen.

2. Fahrer
^^^^^^^^^

:Facette: *Usage*
:Eigenschaften: - Fahrzeugführung (alternativ: Führt Einparkvorgang durch)
:Erläuterung: Der oder die Fahrer/-in ist der Benutzer der innerhalb des Parkvorgangs unterstützt wird

3. Zündungscontroller
^^^^^^^^^^^^^^^^^^^^^

:Facette: *IT-System*
:Eigenschaften: - Starten
    - Aus
:Erläuterung: Die Zündung liefert das Ereignis, was die Fehleranalyse im Parkassistenten startet

4. Fahrzeug
^^^^^^^^^^^

:Facette: *Subject*
:Eigenschaften: - Typ (*PKW*, *LKW* etc.)
    - Hersteller
    - Gewicht
    - Baujahr
    - Schnittstellen (Typ, Beschreibung)
    - Fehlerlog
    - LEDs (Farbe, Typ, ID)
    - Geschwindigkeit
    - Ultraschallsensoren (Abstand zum Hindernis, Frequenz, Schnittstellenbeschreibung)
:Erläuterung: Daten des Fahrzeuges werden im System abgebildet

5. Zentrale Steuereinheit
^^^^^^^^^^^^^^^^^^^^^^^^^

:Facette: *Subject*
:Eigenschaften: - Funktionen
    - Einbauposition im Fahrzeug
    - Stromanschluss
    - Speicher
:Erläuterung: Die zentrale Steuereinheit stellt das Kernsystem des Parkassistenten dar in dem Signale der Ultraschallsensoren verarbeitet und der Benutzer über die unterschiedlichen Signale benachrichtigt werden.

6. Gangschaltung
^^^^^^^^^^^^^^^^

:Facette: *IT-System*
:Eigenschaften: - Geschwindigkeit
    - Schaltposition
    - Sicherheitsmechanismus zum Einlegen des Rückwärtsganges
:Erläuterung: Durch Bedienung der Gangschaltung wird ggfs. der Parkassistent (de-)aktiviert.

7. Audioausgabe (akustische Signale)
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

:Facette: *IT-System*
:Eigenschaften: - Lautstärke
    - Lautsprecherboxen
:Erläuterung: Das im Fahrzeug verbaute System zur Audioausgabe, das zur Benachrichtigung über das Erkennen von Hindernissen vom Parkassistenten genutzt wird.


8. Hindernisse
^^^^^^^^^^^^^^^

:Facette: *Subject*
:Eigenschaften: - Entfernung
    - Position (Vorne, Hinten)
:Erläuterung: Objekte die vom System erkannt werden müssen und nicht platt gefahren werden sollen

9. Multimediasystem des Fahrzeugs
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

:Facette: *IT-System*
:Eigenschaften: - Typ
    - Hersteller
:Erläuterung: Ganzheitliches System zur Aufnahme und Wiedergabe von Audio- und Videodaten.

10. Wetterverhältnisse
^^^^^^^^^^^^^^^^^^^^^^

:Facette: *Subject*
:Eigenschaften: - Temperatur
    - Niederschlag
    - Luftdruck

11. USB-Schnittstelle
^^^^^^^^^^^^^^^^^^^^^

:Facette: *IT-System*
:Eigenschaften: - Verbindungsport
:Erläuterung: Über die USB-Schnittstelle werden die Fehler-Log-Einträge gelesen bzw. übertragen. Außerdem kann über die USB-Schnittstelle ein Update der Firmware übertragen werden.


12. Bordcomputer
^^^^^^^^^^^^^^^^

:Facette: *IT-System*
:Eigenschaften: - Berechnungs
    - Wiedergabe von Informationen
:Erläuterung: Der Bordcomputer enthält das Betriebssystem des Fahrzeugs, was z.B. Resourcenzuweisen handhabt und die Eingaben des Nutzers verarbeitet.

13. An/Aus-Knopf
^^^^^^^^^^^^^^^^

:Facette: *IT-System*
:Eigenschaften: - An/Aus (Boolean)
:Erläuterung: Schalter, der die manuelle Einschaltung oder Ausschaltung des Systems ermöglicht.

14. CAN bus
^^^^^^^^^^^

:Facette: *IT-System*
:Eigenschaften: - Bus-System
    - Schnittstelle
:Erläuterung: Bus-System zur Verbindung aller relevanten Bestandteile des Systems

15. Länderverordnungen
^^^^^^^^^^^^^^^^^^^^^^

:Facette: *Subject*
:Eigenschaften: - Land
:Erläuterung: Gesetzliche Regeln für den Fahrer zur Benutzung des Fahrzeugs im Straßenverkehr, werden im System abgebildet

16. zertifizierte Werkstatt
^^^^^^^^^^^^^^^^^^^^^^^^^^^

:Facette: *Usage*
:Eigenschaften: - Zertifizierungsdatum
    - Name
    - Anschrift
:Erläuterung: Durch den Produzent des Parkassistenten zur Reparatur zertifizierte Werkstatt

17. Autobatterie
^^^^^^^^^^^^^^^^

:Facette: *IT-System*
:Eigenschaften: - Kapazität
    - max. Ausgangsleistung
:Erläuterung: Der Parkassistent verwendet als Stromquelle die Autobatterie

18. Kamera
^^^^^^^^^^

:Facette: *Subject*
:Eigenschaften: - Auflösung
:Erläuterung: Im System hinterlegte Kameras, die Aufnahmen hinter und vor dem Fahrzeug erstellen.

19. Videoausgabe (optische Signale)
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

:Facette: *IT-System*
:Eigenschaften: - Displaygröße
:Erläuterung: Das im Fahrzeug verbaute System zur Videoausgabe, dass das Kamerabild der im Fahrzeug verbauten Kameras darstellt. Der Parkassistent fügt zum Kamerabild Abstandsinformationen hinzu.

20. Parkraum
^^^^^^^^^^^^

:Facette: *Subject*
:Eigenschaften: - Länge
:Erläuterung: Vor einem Parkvorgang wird möglicher Raum vermessen, um zu ermitteln ob der Parkvorgang durchgeführt werden kann.


Entwicklungskontextobjekte
--------------------------

+-----+---------------------------+------------------------------------+
| Nr. | Objekt                    | Erläuterung                        |
+=====+===========================+====================================+
| 1   | Entwicklungsmethode       | SCRUM                              |
+-----+---------------------------+------------------------------------+
| 2   | Max. Entwicklungszeit     | 8 Monate                           |
+-----+---------------------------+------------------------------------+
| 3   | Budget                    | 400.000€                           |
+-----+---------------------------+------------------------------------+
| 4   | Qualitätssicherung        | Entsprechend der Norm ISO/TS 16949 |
+-----+---------------------------+------------------------------------+
| 5   | Mitarbeiter               | - 2 Architekten                    |
|     |                           | - 2 Requirements Ingenieure        |
|     |                           | - 2 Entwickler                     |
|     |                           | - 1 Tester                         |
+-----+---------------------------+------------------------------------+
| 6   | Entwicklungssprache       | C/C++                              |
+-----+---------------------------+------------------------------------+
| 7   | Entwicklungsstandard      | C++14                              |
+-----+---------------------------+------------------------------------+
| 8   | Entwicklungsumgebung      | Netbeans IDE                       |
+-----+---------------------------+------------------------------------+
| 9   | Versionsverwaltung        | Git                                |
+-----+---------------------------+------------------------------------+
| 10  | Projektmanagementsoftware | Redmine                            |
+-----+---------------------------+------------------------------------+

Requirements Engineering Kontextobjekte
---------------------------------------

+-----+---------------------------+----------------------------------------------------------------------------------------------------------------+
| Nr. | Objekt                    | Erläuterung                                                                                                    |
+=====+===========================+================================================================================================================+
| 1   | Konkurrenzsystem          | Parkassistenzsysteme von BMW ConnectedDrive                                                                    |
+-----+---------------------------+----------------------------------------------------------------------------------------------------------------+
| 2   | Domain-Experten           | - 1 Automotiv-Experte                                                                                          |
|     |                           | - 1 Anforderungs-Experte                                                                                       |
|     |                           | - 1 Sales & Marketing-Experte                                                                                  |
+-----+---------------------------+----------------------------------------------------------------------------------------------------------------+
| 3   | Marktforschung            | Usability - Aspekte bei Parkassistenten                                                                        |
+-----+---------------------------+----------------------------------------------------------------------------------------------------------------+
| 4   | Pflichtenheft             | Das Pflichtenheft beschreibt wie der Auftragnehmer die Anforderungen des Auftraggebers zu lösen gedenkt        |
+-----+---------------------------+----------------------------------------------------------------------------------------------------------------+
| 5   | Anwenderbefragungen       | Durchführung von Interviews zur Ermittlung des Bedarfs der Nutzer                                              |
+-----+---------------------------+----------------------------------------------------------------------------------------------------------------+
| 6   | Prüfende Aufsichtsbehörde | Die Behörde, die den Betrieb des Parkassistenten innerhalb des Fahrzeugs erlaubt. In Deutschland z.B. der TÜV. |
+-----+---------------------------+----------------------------------------------------------------------------------------------------------------+


Gruppe
------

:Termin: Mittwoch 14.00 - 16:00 Uhr


+-------------------+----------------+
| Gruppenmitglieder |                |
+===================+================+
| Name              | Matrikelnummer |
+-------------------+----------------+
| Ferhat Lale       | 2280534        |
+-------------------+----------------+
| Serdar Nurgün     | 3045462        |
+-------------------+----------------+
| Duclos Ngassa     | 3045801        |
+-------------------+----------------+
| Holger Bartosch   | 1286878        |
+-------------------+----------------+
| Simon Müller      | 3015176        |
+-------------------+----------------+
| Benjamin Wirtz    | 3015849        |
+-------------------+----------------+
| Nils Verheyen     | 3043171        |
+-------------------+----------------+
