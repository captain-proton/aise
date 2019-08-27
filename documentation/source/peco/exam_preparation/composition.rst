Composition and Adaption
========================

In welcher Kombination werden werden Services zu welchem Zeitpunkt ausgewählt?
Das System kann Services finden und aufrufen, aber es weiß ggfs. nicht wann
das geschehen soll.

1. Auswahl durch entwickelten Code

    - CORBA

2. Auswahl durch den Nutzer per User Interface (manuell)

    - Speakeasy
    - Gaia

3. Automatische Auswahl

    - PCOM
    - O2S

Beispielsysteme
---------------

Speakeasy
^^^^^^^^^

Speakeasy verwendet eine verbindungsorientierte Lösung. Der Nutzer wählt eine
Datenquelle und eine Datensenke aus (Bsp. Videodarstellung von DLNA Server auf
Beamer). Probleme treten auf, wenn die Senke die Protokolle/Format der Quelle
nicht versteht. Hierzu müssten Fähigkeiten der einzelnen Komponenten gesammelt
und miteinander verglichen werden, was wieder eine Anpassung der Komponenten
bedeutet.

Die Verbindung wird über eine WebUI realisiert.

*Hauptprobleme*:

    - hohe Anzahl an Komponenten
    - zu große Antwortzeiten

Gaia
^^^^

Gaia setzt auf ein Session basiertes Konzept. Diese können gestartet,
pausiert, fortgesetzt und beendet werden. Sehr nützlich sind kollaborative
Meetings in einzelnen/wenigen Räumen. Wie in Speakeasy wird eine
CORBA-konfigurierbare Middleware eingesetzt.

*Services*:

- Event Manager
- Context (Wie viele Nutzer, welche Raumtemperatur...)
- Presence
- Space Repository (z.B. verfügbare Hardware/Software)
- Context File System (Datenspeicher, -konvertierung, -abruf)

Im Gegensatz zu Speakeasy wird Gaia zunächst von einem Administrator
konfiguriert und erst später durch den Anwender genutzt.

PCOM
^^^^

PCOM verwendet BASE als Kommunikationsmiddleware und nutzt ein eigenes
Komponentensystem zur Anwendungsentwicklung. Über Verträge (Contract) werden
Verbindungen zwischen den Komponenten aufgebaut. Es wird eine bidirektionale
Verbindung unterstützt. Die Anwendungs besteht aus einem Baum von Komponenten.
Der Lebenszyklus einer Wurzelkomponente definiert den Lebenszyklus der
Anwendung.

Ein Komponentencontainer komponiert die Anwendung bei Start und adaptiert
ggfs. die Ausführung wenn Ereignisse auftreten.

O2S
^^^

Im Verlgeich zu PCOM besteht kein 1-zu-1 Mapping zwischen Hierarchie und
Komponente. Die Hierarchie wird geformt aus Zielen ("goals") die durch
Techniken ("techniques") gebunden werden. Hierdurch wird eine zusätzliche
Kommunikationsstruktur möglich (Netzaufbau möglich).

Beispiel gegeben auf Folie 39 des Skripts Composition...

Die Technik, die am "besten" zur Ausführung geeignet ist, wird verwendet.
