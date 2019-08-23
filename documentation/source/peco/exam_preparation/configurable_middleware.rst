(Re)-configurable Middleware
============================

Unterschieden wird zwischen statischer Konfiguration zur Compile/Deploy-Zeit
und dynamischer Konfiguration zur Laufzeit.

Reflection
----------

.. important:: Ability of a system to reason about itself and act upon its
    reasoning <=> Fähigkeit eines Systems, über sich selbst zu urteilen und
    auf seine Argumentation zu reagieren.

Eine Beurteilung des Systems benötigt einen Weg den Gesamtstatus abzufragen.

.. hint:: Wie oft fallen Komponenten des System aus? Diese Fragestellung
    und Ermittlung der entsprechenden Daten lässt Rückschlüsse auf die
    Stabilität des Gesamtsystems zu.

Eine Reaktion kann auf dem Basislevel oder dem Metalevel erfolgen.

.. hint:: Bsp.: Ein Mailserver fällt aus. Durch den Ausfall wird eine
    Log-Datei geschrieben mit Uhrzeit und Adresse des ausgefallenen Rechners
    (Basislevel). Es werden andere Mailserver gesucht, die als Alternative
    verwendet werden können (Metalevel).

Aspekt-orientierte Programmierung
---------------------------------

Motivation ist z.B. die Verteilung von Funktionen über das gesamte Programm.
Dadurch wird eine lose Kopplung innerhalb des Programms erreicht. Ggfs.
entsteht ein hoher Zusammenhalt zwischen den Bestandteilen.

.. hint:: Bsp.: Logging, Außerhalb des bestehenden Codes zu einer Methode
    (Pointcut) wird ein Aspekt definiert, der vor Ausführung der Methode
    aufgerufen wird. Die eigentliche Implementierung bleibt davon unberührt.

Klassifizierung
---------------

Static
    Customizable
        - Compilezeit
        - Compiler flags
        - Pre-compiler Direktiven (#define)
    Configurable
        - Konfigurationsdateien
        - Kommandozeilen Parameter
Dynamic
    Tunable
        - AOP + reflection
    Mutable
        - Während der Laufzeit
        - Reflection + dynamisches AOP

Beispielanwendungen
-------------------

TAO
^^^

TAO ist eine CORBA 3 kompatible Middleware Implementierung. Support für
Realtime Systeme ist gegeben.

*Realtime-*:

- Object Adapter
- Scheduler
- ORB

Durch ein starken Einsatz von Design Pattern wird TAO flexibel. *Strategies*
um Algorithmen austauschen zu können, *(Abstract) Factory* wird zur
Konfiguration verwendet.

.. hint:: Entwickler wählt während des Anwendungsstarts das konkrete Verhalten
    des ORB

dynamicTAO
^^^^^^^^^^

*dynamicTAO* ist die reflektive Erweiterung von TAO, um Rekonfiguration zur
Laufzeit zu ermöglichen. Das Meta-Objekt-Protokoll ist als CORBA Interface
implementiert um z.B. DLL-Dateien zur Laufzeit im System zu verteilen. Das
MOP ist über das Netzwerk verfügbar.

*Probleme*:

1. Wechsel zwischen ``thread_pool`` und ``single_thread``. Wann ist es sicher
den Pool zu wechseln?

2. Wie wird der Status innerhalb eines Algorithmus gespeichert?
(Memento Pattern)

3. Wie wird ein gesamtes verteiltes System effizient rekonfiguriert?
(Rekonfigurationsregeln veröffentlichen)

BASE
^^^^

BASE ist eine Kommunikationsmiddleware, die Peer-to-peer Interaktionen
unterstützt.

1. Application layer (Proxies/Skeletons)
2. Micro-broker layer (Object/Device Registry)
3. Plugin layer

Durch die Pluginstruktur soll das System schlank und lose gekoppelt sein.
Dadurch wird eine höhere Flexibilität und Simplizität erreicht.

*Plugin-Architektur*:

Abstraktion (Semantik)
    bestehend aus z.B. Methodenaufrufen
Protokollen
    Serialisierer und Modifizierer (z.B. Verschlüsselung)
Übertragungstechnologie
    IP, Bluetooth etc.

Bei Erstellung der Anwendung wird zuerst ermittelt, wie die Plugins mit
anderen Systemen korrekt funktionieren (Negotiation). Danach werden die
Plugins der Systeme miteinander verbunden (Connection).

Eine Evaluation hat ergeben, dass BASE als monolithisches System dieselben
Latenzen besitzt wie als modulares System.

PROSE
^^^^^

Dynamisches AOP wird als primärer Mechanismus verwendet. Umsetzung in AspectJ.
Aspekte müssen zur Compilezeit bekannt sein. PROSE selbst enthält Mechanismen
zur Modifikation während der Laufzeit, um Aspekte hinzuzufügen und zu
entfernen. Vor der Laufzeit werden die einzelnen Aspekte miteinander verwoben
(weaving process).

MIDAS
^^^^^

MIDAS löst einige Fragestellungen, die sich in PROSE durch die Aspekte
ergeben.

- Wann sollen Aspekte verwendet werden?
- Wer lädt die Aspekte und kann dieser Stelle vertraut werden?

Hierzu werden zwei Rollen in das System eingeführt.

1. Extension Basis
    - Discovery
    - Versendung von Erweiterungen

2. Extension Empfänger
    - Empfängt Erweiterung
    - Führt Integritätstests und Authentizität durch
    - Installiert Erweiterungen

Zusammenfassung
---------------

TAO
    Durch die Nutzung der Design Patterns können Anwendungs bei Start
    konfiguriert werden

dynamicTAO
    Erweiterung von TAO zur Laufzeitanpassung

BASE
    Protokolländerungen möglich durch Pluginstruktur

PROSE/MIDAS
    Umgebungsspezifische Anforderungen -> dynamische Aspekte
