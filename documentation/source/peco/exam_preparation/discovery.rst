Discovery
=========

*Beispiele*:

- IEEE 802.11 (WLAN)
- ARP
- Bluetooth Device and Service Discovery
- Service Location Protocol
- Bonjour

*Forschung*:

- Energie-Effizienz
- Privatsphäre
- Vertrauen

Organisation
------------

Organisation entweder über Mediatoren oder Peer basiert. Kombinationen beider
Systeme meistens am besten, da die Nachteile beider (Single point of failure
und Komplexität) minimiert werden können. Verteilung entweder Proaktiv (push)
durch wiederholte Mitteilung der Erreichbarkeit (skaliert nicht) oder bei
Anfrage (pull). Pull kann im schlechten Fall eine sehr hohen Datenverkehr
verursachen.

*Modelle*:

- Unicast
- Broadcast
- Hybride

*Query Modelle*:

One-Time Query
    Request/Response basiert. Eine Query wird einmal ausgeführt, eine Antwort
    wird einmal gesendet. Es können hier auch mehrere Antworten beim
    Anfragenden erscheinen, wenn eine Broadcast-Nachricht gesendet wurde.

Continuous Query
    Ähnlich zum Observer-Muster. Eine Query wird einmal gesendet bzw.
    registriert. Antworten werden in regelmäßigen Abständen zum Sender
    gesendet. Schwieriger zu implementieren, erlaubt aber z.B. das Erkennen
    von Netzwerkfehlern.

IEEE 802.11
-----------

.. important:: Grundsätzliche Problemstellung bei Funk ist, dass der Empfänger
    immer nur auf einem Kanal hören kann.

Bei passiver Discovery sendet die Basisstation regelmäßig einen Beacon Frame
in dem das Netzwerk kommuniziert wird. Der Empfänger schaltet die
unterschiedlichen Frequenzen durch und hört auf den Beacon Frame.

.. hint:: SSID versteckt = passive Discovery nicht möglich

Bei aktiver Discovery wird ein *probe request frame* gesendet und
Basisstationen können mit einem *probe response frame* antworten. Kann ggfs.
schneller sein als die passive Discovery. Min und max probe response time
setzen die Limits, wie lange der Client wartet.

ARP
---

ARP mappt die MAC-Adresse (OSI 2) auf eine IP-Adresse (OSI 3). Rechner
speichern Assoziationen in einer lokalen ARP-Tabelle.

Bluetooth
---------

Master-Slave Kommunikationsmodell. Der Master kann mehrere gleichzeitige
Verbindungen zu Slaves etablieren (Piconet).

Device Discovery
^^^^^^^^^^^^^^^^

Bluetooth nutzt das Frequency-hopping-spread-spectrum (`FHSS
<https://www.youtube.com/watch?v=CkhA7s5GIGc>`_) auf 79 Kanälen, um Daten zu
übertragen. Jede Frequenz operiert auf einem 1MHz Abschnitt. Dabei wird 1600x
in der Sekunde die Frequenz zufällig gewechselt. Die Reihenfolge ist nur
Sender und Empfänger bekannt. Für die Discovery werden lediglich 32 Kanäle
verwendet, um die Zeitspanne der Auffindung zu verkürzen. Der Master springt
dabei 2x so schnell durch das Band um Slaves zu finden.

Service Discovery
^^^^^^^^^^^^^^^^^

.. important:: Um alle verfügbaren Services anfragen zu können wird oft die
    Service Id 0 verwendet, da die Ids ansonsten nicht bekannt sind.

Service Location Protocol
-------------------------

*Teilnehmer*:

User agent
    Führt Discovery für den Client durch

Service agent
    Veröffentlicht Ort und Attribute der Services

Directory agent
    Aggregiert Services in einem Repositorie

DA wird automatisch aufgefunden durch Multicast aktiv (pull) / passiv (push).
Wenn ein DA verfügbar ist wird ein Mediator basierter Modus verwendet,
ansonsten ein Peer basierter.

Bonjour
-------

*Komponenten*:

- Selbst assoziierte IP-Adresse (*169.254/16*)
- Multicast DNS Auflösung
- DNS basierte Service Discovery

mDNS
^^^^

Idee ist, dass jeder Teilnehmer am Netzwerk seinen eigenen DNS server
betreibt. mDNS verwendet *.local* als Top Level Domain. Zusätzlich werden
reverse lookups an *254.169.in-addr.arpa.* gesendet. Doppelte Namen werden
in mDNS erlaubt. Erkennung von Duplikaten wird bei betreten des Netzwerks
und bei erstellen von One-Time-Queries oder Continuous Queries durchgeführt.

Service Discovery
^^^^^^^^^^^^^^^^^

DNS basiert und spezifiziert durch RFC 6763. Unterstützung von IP-Services
durch `SRV` record Typ. `PTR` für Namensdienste.

.. note:: Struktur eines Namens: *<Instance>.<Service>.<Domain>*

*Beispiel*:

.. code-block:: shell

    # Find root to browse (= search for b._dns-sd._udp.mydomain)

    nslookup -type=ptr b._dns-sd._udp.dns-sd.org

Forschung
---------

Energieeffizienz
^^^^^^^^^^^^^^^^

1. Synchronisation
    Es wird Geräten erlaubt eine bestimmte Zeit zu schlafen. Beispiel: Idle
    server schlafen für x Sekunden und wachen für y Sekunden wieder auf.
    Problem... Hohe Latenz, hohe Bandbreite.

2. Clustering
    Eine bestimmte Zahl an Geräten ist stabil und dauerhaft erreichbar. Diese
    Geräte werden als Mediatoren verwendet. Problem könnte durch Überlastung
    dieser Geräte entstehen

Privatsphäre
^^^^^^^^^^^^

Access Control könnte verwendet werden, um veröffentlichte Services nur für
bestimmte Geräte zur Verfügung zu stellen. Wie wird aber die Access Control
umgesetzt? Hier kann wieder ein Mediator basiertes Schema zum  Einsatz kommen.

Vertrauen
^^^^^^^^^

Ist ein automatisch gefundenen Service sicher? Bekanntes Beispiel ist der
Amazon Marketplace. Jeder Käufer ist in der Lage Ratings zu verteilen.
Wie wird ermittelt ob die Wahrheit geschrieben wurde?
