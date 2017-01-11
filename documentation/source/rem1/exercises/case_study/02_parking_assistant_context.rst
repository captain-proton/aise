Parking Assistant - Context
===========================

.. role:: underline
    :class: underline

Systemkontextobjekte
--------------------

Die Kontextobjekte sind sortiert nach ``Usage`` -> ``Subject`` -> ``IT-System``.

1. Ultraschallsensoren
^^^^^^^^^^^^^^^^^^^^^^

:Facette: *Usage* ``f``
:Eigenschaften: - Abstand zum Hindernis
    - Funktionsbereitschaft
    - Frequenz
    - Schnittstellenbeschreibung
:Erläuterung: Wird von der zentralen Steuereinheit benutzt

Kommentar: Repräsentiert die Ultraschallsensoren während der Nutzung, daher Usage-Facette

fällt weg

2. Fahrzeug ``f``
^^^^^^^^^^^^^^^^^

:Facette: *Usage*
:Eigenschaften: - Geschwindigkeit
    - Lenkwinkel
:Erläuterung: Bei Verwendung des Assistenten wird z.B. die Geschwindigkeit direkt an das System weitergeleitet

Kommentar: Das Fahrzeug beeinflusst oder profitiert nicht vom Parkassistenten, fällt weg

3. Frei verfügbare Werkzeuge ``?``
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

:Facette: *Usage*
:Eigenschaften: - Passform
:Erläuterung: Objekte mit denen es nicht möglich sein soll das System zu reparieren/anzupassen.

4. Fahrer
^^^^^^^^^

:Facette: *Usage*
:Eigenschaften: - Fahrzeugführung (alternativ: Führt Einparkvorgang durch)
:Erläuterung: Der oder die Fahrer/-in ist der Benutzer der innerhalb des Parkvorgangs unterstützt wird

5. Zündung ``f``
^^^^^^^^^^^^^^^^

:Facette: *Usage*
:Eigenschaften: - Starten
    - Aus
:Erläuterung: Mit der Zündung wird das System gestartet u. Fehlerdiagnosen durchgeführt

Kommentar: Die Zündung liefert das Ereignis, was die Fehleranalyse im Parkassistenten startet, ggfs. eher *IT-System*
Begriff umformulieren (ggfs. Mikrocontroller)

6. Ultraschallsensor ``f``
^^^^^^^^^^^^^^^^^^^^^^^^^^

:Facette: *Subject*
:Eigenschaften: - vertikaler Winkel
    - horizontaler Winkel
    - Frequenz
    - ID
:Erläuterung: Einzelnes Objekt im System, was als Instanz angesprochen wird

Kommentar: Ein Sensor innerhalb der Stoßstange, fällt weg

7. Fahrzeug
^^^^^^^^^^^

:Facette: *Subject*
:Eigenschaften: - Typ (*PKW*, *LKW* etc.)
    - Hersteller
    - Gewicht
    - Baujahr
    - Schnittstellen (Typ, Beschreibung)
:Erläuterung: Mögliches Objekt in dem das System installiert werden kann ``f``
daten des fahrzeuges werden im system abgebildet

8. Zentrale Steuereinheit ``f``
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

:Facette: *Subject*
:Eigenschaften: - Funktionen
    - Einbauposition im Fahrzeug
    - Stromanschluss
    - Speicher
:Erläuterung: Objekt, das die Steuerung des Systems ermöglicht.

Kommentar: Die zentrale Steuereinheit stellt das Kernsystem des Parkassistenten dar in dem Signale der Ultraschallsensoren verarbeitet und der Benutzer über die unterschiedlichen Signale benachrichtigt werden.
kein teil des kontext

9. Gangschaltung
^^^^^^^^^^^^^^^^

:Facette: *IT-System*
:Eigenschaften: - Geschwindigkeit
    - Schaltposition
    - Sicherheitsmechanismus zum Einlegen des Rückwärtsganges
:Erläuterung: Objekt, das vom System benutzt wird beim Parkvorgang.
:Erläuterung 2: Durch Bedienung der Gangschaltung wird ggfs. der Parkassistent (de-)aktiviert.

10. Geschwindigkeitsmesser ``f``
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

:Facette: *IT-System*
:Eigenschaften: - Geschwindigkeit
    - Frequenz in der die Messung (alle 20 ms)
:Erläuterung: Sensor, der die Geschwindigkeit des Autos misst und vom Parkassistenten abgefragt werden kann.

Kommentar: Titel ändern in Geschwindigkeitssensor, fällt weg

11. Fehleranalyse ``Kontext ?``
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

:Facette: *Subject*
:Eigenschaften: - Dokumentation von Systemzuständen
    - Fehler erkennen
    - Fehler melden
    - Fehler an das Fehlerlog weiterleiten
:Erläuterung: Die Fehleranalyse ist ein Subsystem der zentralen Steuereinheit. Mit Hilfe der Fehleranalyse werden systematische Fehler erkannt und dokumentiert.

Kommentar: Streichen, da es sich lediglich um eine logische Umsetzung innerhalb des Systems handelt. Programm zur Fehleranalyse.

12. Audioausgabe (akustische Signale)
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

:Facette: *IT-System*
:Eigenschaften: - Lautstärke
    - Lautsprecherboxen
:Erläuterung: Audiosignale der einzelnen Systeme des Fahrzeugs werden ausgegeben ``f``
:Erläuterung 2: Das im Fahrzeug verbaute System zur Audioausgabe, das zur Benachrichtigung über das Erkennen von Hindernissen vom Parkassistenten genutzt wird.


13. Hindernisse
^^^^^^^^^^^^^^^

:Facette: *Subject*
:Eigenschaften: - Entfernung
    - Position (Vorne, Hinten)
:Erläuterung: Objekte die vom System erkannt werden müssen und nicht platt gefahren werden sollen

14. LED ``Kontext ?``
^^^^^^^^^^^^^^^^^^^^^

:Facette: *Subject*
:Eigenschaften: - Farbe (gelb und rot)
    - Typ (Bauform, Stromverbrauch, Lichtintensität, usw.)
    - ID (setzt sich zusammen aus Position und Warnstufe)
:Erläuterung: Die Kombination aus mehreren LEDs stellt den Abstand zum nächsten Hindernis dar

Teil des system, fällt weg

15. Multimediasystem des Fahrzeugs
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

:Facette: *IT-System* ``f``
:Eigenschaften: - Typ
    - Hersteller
:Erläuterung: "Autoradio" mit unterschiedlichen Multimediafunktionen ( Radio, Navi, CD-Player usw.)
:Erläuterung 2: Ganzheitliches System zur Aufnahme und Wiedergabe von Audio- und Videodaten.

Kommentar: *IT-System*

16. Wetterverhältnisse
^^^^^^^^^^^^^^^^^^^^^^

:Facette: *Subject*
:Eigenschaften: - Temperatur
    - Niederschlag
    - Luftdruck

17. Fehler-Log ``Kontext``
^^^^^^^^^^^^^^^^^^^^^^^^^^

:Facette: *IT-System*
:Eigenschaften: - Format (.xml, verschlüsselt)
    - Dateipfad
    - Zeit + Datum
:Erläuterungen: Speichert Fehlermeldungen des Parksystems.
:Erläuterungen 2: Eine Datei die die Daten der Fehleranalyse über alle Fehler des Parkassistenten enthält.

Teil des Fahrzeugs

18. USB-Schnittstelle
^^^^^^^^^^^^^^^^^^^^^

:Facette: *IT-System*
:Eigenschaften: - Verbindungsport
:Erläuterung: Über die USB-Schnittstelle werden die Fehler-Log-Einträge gelesen bzw. übertragen. Außerdem kann über die USB-Schnittstelle ein Update der Firmware übertragen werden.


19. Bordcomputer
^^^^^^^^^^^^^^^^

:Facette: *IT-System*
:Eigenschaften: - Berechnungs
    - Wiedergabe von Informationen
:Erläuterung 2: Der Bordcomputer enthält das Betriebssystem des Fahrzeugs, was z.B. Resourcenzuweisen handhabt und die Eingaben des Nutzers verarbeitet.

20. Optische Signaleinstellung ``?``
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

:Facette: *IT-System*
:Eigenschaften: - Typ
    - Id

Kommentar: Kann wegfallen, das es sich lediglich um Eigenschaften der zentralen Steuereinheit handelt.

21. Akustische Signaleinstellung  ``?``
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

:Facette: *IT-System*
:Eigenschaften: - Frequenz
    - Lautstärke

Kommentar: Kann wegfallen, das es sich lediglich um Eigenschaften der zentralen Steuereinheit handelt.

22. An/Aus-Knopf
^^^^^^^^^^^^^^^^

:Facette: *IT-System*
:Eigenschaften: - An/Aus (Boolean)
:Erläuterung: Schalter, der die manuelle Einschaltung oder Ausschaltung des Systems ermöglicht.

23. CAN bus
^^^^^^^^^^^

:Facette: *IT-System*
:Eigenschaften: - Bus-System
    - Schnittstelle
:Erläuterung: Bus-System zur Verbindung aller relevanten Bestandteile des Systems

24. Länderverordnungen
^^^^^^^^^^^^^^^^^^^^^^

:Facette: *Subject*
:Eigenschaften: - Land
:Erläuterung: Gesetzliche Regeln für den Fahrer zur Benutzung des Fahrzeugs im Straßenverkehr, werden im System abgebildet

25. zertifizierte Werkstatt
^^^^^^^^^^^^^^^^^^^^^^^^^^^

:Facette: *Usage*
:Eigenschaften: - Zertifizierungsdatum
    - Name
    - Anschrift
:Erläuterung: Durch den Produzent des Parkassistenten zur Reparatur zertifizierte Werkstatt

26. Fahrzeughersteller
^^^^^^^^^^^^^^^^^^^^^^

:Facette: *Usage*
:Eigenschaften: - Name
    - Hauptsitz
:Erläuterung: Das Unternehmen, welches den Parkassistenten in seinen Fahrzeugen verbaut

Eher entfernen

26. Prüfende Aufsichtsbehörde
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

:Facette: *Usage*
:Eigenschaften: - Name
:Erläuterung: Die Behörde, die den Betrieb des Parkassistenten innerhalb des Fahrzeugs erlaubt. In Deutschland z.B. der TÜV

RE-Kontext

Entwicklungskontextobjekte
--------------------------

+-----+---------------------------+------------------------------------+
| Nr. | Objekt                    | Erläuterung                        |
+=====+===========================+====================================+
| 1   | Entwicklungsmethode       | SCRUM                              |
+-----+---------------------------+------------------------------------+
| 2   | Max. Entwicklungszeit     | :underline:`8 Monate`              |
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

+-----+------------------------------+---------------------------------------------------------------------------------------------------------+
| Nr. | Objekt                       | Erläuterung                                                                                             |
+=====+==============================+=========================================================================================================+
| 1   | Konkurrenzsystem             | Parkassistenzsysteme von BMW ConnectedDrive                                                             |
+-----+------------------------------+---------------------------------------------------------------------------------------------------------+
| 2   | Domain-Experten              | - 1 Automotiv-Experte                                                                                   |
|     |                              | - 1 Anforderungs-Experte                                                                                |
|     |                              | - 1 Sales & Marketing-Experte                                                                           |
+-----+------------------------------+---------------------------------------------------------------------------------------------------------+
| 3   | Marktforschung               | Usability - Aspekte bei Parkassistenten                                                                 |
+-----+------------------------------+---------------------------------------------------------------------------------------------------------+
| 4   | Pflichtenheft                | Das Pflichtenheft beschreibt wie der Auftragnehmer die Anforderungen des Auftraggebers zu lösen gedenkt |
+-----+------------------------------+---------------------------------------------------------------------------------------------------------+
| 5   | Anwenderbefragungen          | Durchführung von Interviews zur Ermittlung des Bedarfs der Nutzer                                       |
+-----+------------------------------+---------------------------------------------------------------------------------------------------------+
| 6   | Straßenverkehrsordnung ``f`` | Regeln bestimmt durch den Gesetzgeber                                                                   |
+-----+------------------------------+---------------------------------------------------------------------------------------------------------+


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
