*****
Übung
*****

Themen
======

- Adaptive Cruise Control
- Electronic Gear Control
- Electronic Stability Programme
- Lane keeping Assistant
- Parking Assistant


Parking Assistant
=================

Anforderungen an einen Parkassistent (Brainstorming)

- Manuell

    + Parkvorgang übernimmt der Anwender
    + Optische und akustische Unterstützung durch den Assistenten
    + Möglichkeit der (De-)aktivierung über einen Knopf (An/Aus)

        * grün LED am Knopf

    + Akustisch: Schnelleres Piepen je näher dem Hindernis
    + Optisch: Siehe Mercedes System (7 LEDs pro Seite - je näher desto mehr Lichter an)
    + Sensorik vorne und hinten

        * Definition des Winkels je nach Wendekreis des Autos
        * Reichweite der Sensoren
        * Nachteil: Sensoren vorne immer aktiv (wenn ein Gang eingelegt ist)
        * Unterschiedliche Töne für vorne und hinten

    + (Audiosignal entweder vorne oder hinten, nie beides)
    + Funktionsprüfung über das System bei Zündung des Autos

        * Optisches System leuchtet drei mal rot
        * Optional: Fehlermeldung an das Bordsystem

- Halbautomatisch

    + Bremsen bei drohendem Kontakt
    + Vermessen der Parklücke

- Rahmenbedingung

    + Körperliche und geistige Verfassung der Nutzer

- Die Sensoren an den Stoßstangen des Systems sollen ausgetauscht werden können
- Was impliziert die Anforderung?
- Es soll keine anderen Autos berühren (Rahmenbedingung)
- Das System soll auf Veränderungen innerhalb von 10ms reagieren (Qualität)
- Das System soll beim Einparkvorgang die Lautstärke des Radios reduzieren

- Der Ausfall eines Sensors soll zur kompletten Deaktivierung des Systems führen
- Die Software des Systems soll nur durch den Hersteller und spezifizierte Werkstätten gewartet werden


Funktionelle Anforderungen (Functional requirements)
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

- Das System soll den Nutzer bei einem Parkvorgang unterstützen
- Das System soll in den Parkvorgang nicht eingreifen
- Der Assistent soll vom Nutzer an- und ausgeschaltet werden können
- Bei Zündung des Fahrzeugs wird eine Funktionsprüfung durchgeführt
- Das System soll bei Einlegen des Rückwärtsgangs, ersten Gangs und Aktivierung des Zündung eine Fehlerdiagnose durchführen und bei Bedarf eine Fehlermeldung an den Nutzer ausgegeben
- Hindernisse müssen durch den Assistent erkannt und dem Nutzer gemeldet werden
- Der Erfassungsbereich von Hindernissen liegt sowohl in der horizontale, als auch der Vertikale der Sensorik
- Das System soll beim Einparkvorgang die Lautstärke des Radios reduzieren (Nils)
- Die Sensorik des Systems soll auf Ultraschall basieren
- Das System soll sich bei einer Geschwindigkeit von >= 12km/h mit akustischem Signal deaktivieren (Jrakli)
- Bei Schalten in den Rückwärtsgang aktiviert das System die Sensoren auf der Rückseite des Fahrzeugs (Simon)
- Das System gibt beim Einlegen des Rückwärtsgangs und Erkennen eines Hindernisses am Heck des Fahrzeugs einen Signalton von 400Hz aus (Holger)
- Das System gibt beim Einlegen des ersten Gangs und Erkennen eines Hindernisses am Bug des Fahrzeugs einen Signalton von 600Hz aus (Holger)
- Das System gibt den Signalton des näheren Hindernisses aus, wenn sowohl vorne als auch hinten ein Hindernis erkannt wird (Holger)
- Das System soll einen Signalton ausgeben, wenn ein Kontakt mit einem anderen Objekt stattgefunden hat(irakli)

Qualitätsanforderungen (Quality requirements)
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

- Das System soll auf Veränderungen innerhalb von 10ms reagieren (Nils)
- (Das System soll bei Kontakt mit anderen Objekten funktionsfähig bleiben)(irakli) ‘2265988’
- Das System soll bei einer drohenden Kollision selbsttätig in die Bremsen bzw. Lenkung eingreifen (Security) [Duclos].
- Das System soll Energieeffizient (möglichst wenig Energie verbrauchen) sein. (Efficiency)[Duclos]
- Das System soll Fahrzeug- bzw. Markenunabhängig sein. (Portability) [Duclos]

Rahmenbedingungen (Constraints)
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

- Das System soll bei einer Umgebungstemperatur von -25°C bis 50°C nicht beeinträchtigt werden (Simon)


Zusatz-Übungsblatt 1
====================

Aufgabe 1
^^^^^^^^^

- Content
- Agreement
- Documentation

**Ziel**:

Aufgabe 2
^^^^^^^^^

Die Formulierung *Das System* ist fast zwingend notwendig, da **immer** Anforderungen an das System formuliert werden und nicht deren Teile.

- Kühe sollen identifiziert werden können (Funktion)
- Das System stellt sicher, dass eine Kuh sich ausreichend ernährt (Qualität)
- Das System soll gemäß den Hygienerichtlinien des Landes NRW arbeiten
- Das System soll drei Kühe gleichzeitig melken können (Funktion)
- Das System soll erweiterbar sein in Bezug auf die Melkstationen (Qualität - Skript S. 17)
- Der Melkvorgang an einer Kuh muss in 10 Minuten abgeschlossen sein (Qualität)

Aufgabe 4
^^^^^^^^^

Ohne Requirements Engineering geht nix!

- Funktionen sind den Beteiligten nicht bekannt
- Die Kosten können nicht genau beziffert werden
- Es wird ohne Dokumentation gearbeitet
