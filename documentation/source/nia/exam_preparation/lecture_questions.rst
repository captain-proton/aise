Kahoot Fragen aus der Vorlesung
===============================

Welche Aussage ist falsch?
^^^^^^^^^^^^^^^^^^^^^^^^^^

Wählen Sie eine oder mehrere Antworten:

+----------------------------------------------------------+----------+
| Frage                                                    | korrekt? |
+==========================================================+==========+
| WebRTC erlaubt direkte Kommunikation zwischen Webclients |          |
+----------------------------------------------------------+----------+
| WebRTC ermöglicht NAT-Traversal                          | X        |
+----------------------------------------------------------+----------+
| WebRTC stellt eine API für Web-Entwickler bereit         |          |
+----------------------------------------------------------+----------+
| WebRTC benötigt ein Browser-Plugin                       | X        |
+----------------------------------------------------------+----------+

.. epigraph::

    "*WebRTC is a free, open project that provides browsers and mobile applications with Real-Time Communications (RTC) capabilities via simple APIs.*"

    --`WebRTC <https://webrtc.org/>`_

`WebRTC Wikipedia <https://en.wikipedia.org/wiki/WebRTC>`_

----

Welche Eigenschaften haben Small World Netze?
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^


Wählen Sie eine oder mehrere Antworten:

+-------------------------------------+----------+
| Frage                               | korrekt? |
+=====================================+==========+
| Geringer Durchmesser                | X        |
+-------------------------------------+----------+
| Kleine Betweenness Centrality       |          |
+-------------------------------------+----------+
| Knotengrad folgt einem Potenzgesetz | X        |
+-------------------------------------+----------+
| Hoher Clustering Coefficient        | X        |
+-------------------------------------+----------+

Eine Aussage über die Betweenness Centrality kann man schwer treffen. Tendenziell ist aber auf Grund einer niedrigen Anzahl an Kanten die Betweenness Centrality eher niedrig.

----

Wie kann Videostreaming durch das angegebene Protokoll nicht beeinträchtigt werden?
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^


Wählen Sie eine oder mehrere Antworten:

+--------------------+----------+
| Frage              | korrekt? |
+====================+==========+
| TCP: Bildartefakte | X        |
+--------------------+----------+
| UDP: Bildartefakte |          |
+--------------------+----------+
| TCP: Stalling      |          |
+--------------------+----------+
| UDP: Stalling      |          |
+--------------------+----------+

Bildartefakte können nicht entstehen, da bei TCP garantiert wird, dass alle Daten korrekt übertragen werden.

----

Können strukturierte Overlays mit CDNs benutzt werden?
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Wählen Sie eine oder mehrere Antworten:

+-------+----------+
| Frage | korrekt? |
+=======+==========+
| Nein  |          |
+-------+----------+
| Ja    | X        |
+-------+----------+

Beispiel DNS.

----

Was ist die Hauptaufgabe von strukturierten Overlays?
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^


Wählen Sie eine oder mehrere Antworten:

+----------------------------------------+----------+
| Frage                                  | korrekt? |
+========================================+==========+
| Filesharing                            |          |
+----------------------------------------+----------+
| Suche von Daten                        | X        |
+----------------------------------------+----------+
| Unstrukturierte Netze zu strukturieren |          |
+----------------------------------------+----------+
| Hashing von Daten                      |          |
+----------------------------------------+----------+

Das Hashing wird zwar in großem Maße durchgeführt, aber es ist keine Aufgabe von strukturierten Overlays. Mit Hilfe des Hashings sollen sowohl Knoten, als auch Inhalte gefunden werden.

----

Was ist der Unterschied zwischen ICN und CDN?
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^


Wählen Sie eine oder mehrere Antworten:

+-------------------------------------------------+----------+
| Frage                                           | korrekt? |
+=================================================+==========+
| CDN benutzt kein Caching - ICN schon            |          |
+-------------------------------------------------+----------+
| ICN verändert Internetarchitektur (z.B. Router) | X        |
+-------------------------------------------------+----------+
| Kein Unterschied: ICN ist eine Form von CDN     |          |
+-------------------------------------------------+----------+
| CDN benutzt IP Routing - ICN nicht              |          |
+-------------------------------------------------+----------+

.. epigraph::

    Information-centric networking (ICN) is an approach to evolve the Internet infrastructure to directly support this use by introducing uniquely named data as a core Internet principle. Data becomes independent from location, application, storage, and means of transportation, enabling in-network caching and replication.

    -- `Information-Centric Networking Research Group <https://irtf.org/icnrg>`_

----

Was ist der Unterschied zwischen ICN und P2P?
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^


Wählen Sie eine oder mehrere Antworten:

+-------------------------------------------------------+----------+
| Frage                                                 | korrekt? |
+=======================================================+==========+
| P2P wird auf Layer 7 implementiert - ICN nicht        |          |
+-------------------------------------------------------+----------+
| Routing nach Inhalten mit ICN - nicht möglich mit P2P |          |
+-------------------------------------------------------+----------+
| P2P benutzt Resourcen der Anwender - ICN nicht        |          |
+-------------------------------------------------------+----------+
| ICN benötigt keinen Quellserver für Videos            | X        |
+-------------------------------------------------------+----------+


----

Der Zuständigkeitsbereich eines Peers in Chord...
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Wählen Sie eine oder mehrere Antworten:

+----------------------------------------+----------+
| Frage                                  | korrekt? |
+========================================+==========+
| hängt von der Anzahl der Dokumente ab. |          |
+----------------------------------------+----------+
| wird durch die Finger bestimmt.        | X        |
+----------------------------------------+----------+
| hat eine zufällige Größe.              |          |
+----------------------------------------+----------+
| ist gleich groß für alle Peers.        |          |
+----------------------------------------+----------+


----

In welcher Richtung wird mehr Verkehr erzeugt?
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^


Wählen Sie eine oder mehrere Antworten:

+------------+----------+
| Frage      | korrekt? |
+============+==========+
| Downstream | X        |
+------------+----------+
| Upstream   |          |
+------------+----------+

----

P2P-Anwendungen implementieren Overlays auf welcher Schicht?
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^


Wählen Sie eine oder mehrere Antworten:

+-------------------+----------+
| Frage             | korrekt? |
+===================+==========+
| Anwendungsschicht | X        |
+-------------------+----------+
| Im Endgerät       |          |
+-------------------+----------+
| Netzwerkschicht   |          |
+-------------------+----------+
| HTTP über TCP/IP  |          |
+-------------------+----------+

----

Was beschreibt die Betweenness Centrality?
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^


Wählen Sie eine oder mehrere Antworten:

+----------------------------------------------+----------+
| Frage                                        | korrekt? |
+==============================================+==========+
| Stärke des zentralen Clusters                |          |
+----------------------------------------------+----------+
| Wichtigkeit bei Knotenausfall                | X        |
+----------------------------------------------+----------+
| Wichtigkeit eines Knotens für kürzeste Pfade | X        |
+----------------------------------------------+----------+
| Mittlere Distanz zwischen Knoten             |          |
+----------------------------------------------+----------+

----

Welche Funktionalität wird von Overlays immer angeboten?
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Wählen Sie eine oder mehrere Antworten:

+-------------------------------+----------+
| Frage                         | korrekt? |
+===============================+==========+
| Konnektivität zwischen Knoten | X        |
+-------------------------------+----------+
| Verteilen von Ressourcen      | X        |
+-------------------------------+----------+
| Strukturiertes Netz           |          |
+-------------------------------+----------+
| Multicast                     |          |
+-------------------------------+----------+

----

Auf welcher Schicht wird das P2P-Paradigma heutzutage im Internet implementiert?
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^


Wählen Sie eine oder mehrere Antworten:

+-------------------+----------+
| Frage             | korrekt? |
+===================+==========+
| Transport Layer   |          |
+-------------------+----------+
| Session Layer     |          |
+-------------------+----------+
| Network Layer     |          |
+-------------------+----------+
| Application Layer | X        |
+-------------------+----------+

----

Was ist der Unterschied zwischen Overlays und P2P?
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Wählen Sie eine oder mehrere Antworten:

+----------------------------------------------------+----------+
| Frage                                              | korrekt? |
+====================================================+==========+
| P2P ist unstrukturiert; Overlays sind strukturiert |          |
+----------------------------------------------------+----------+
| P2P benutzt Overlays                               | X        |
+----------------------------------------------------+----------+
| Overlays benutzt man zur Suche, P2P für Resourcen  | X        |
+----------------------------------------------------+----------+
| kein Unterschied                                   |          |
+----------------------------------------------------+----------+


----

Welche der Aufgaben übernimmt TCP nicht?
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^


Wählen Sie eine oder mehrere Antworten:

+--------------------------------------+----------+
| Frage                                | korrekt? |
+======================================+==========+
| Paketumleitung (Stau im Netz)        | X        |
+--------------------------------------+----------+
| Flusskontrolle (langsamer Empfänger) | X        |
+--------------------------------------+----------+
| Überlastkontrolle (langsames Netz)   | X        |
+--------------------------------------+----------+
| Paketwiederholung (Verlust im Netz)  |          |
+--------------------------------------+----------+


----

Was ist der Nutzen von Overlays?
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Wählen Sie eine oder mehrere Antworten:

+------------------------------------------------------+----------+
| Frage                                                | korrekt? |
+======================================================+==========+
| Technische Grenzen des Internet umgehen              |          |
+------------------------------------------------------+----------+
| Sind immer schneller als das Underlay                |          |
+------------------------------------------------------+----------+
| Erlauben Unabhängigkeit von Dienstanbietern und ISPs | X        |
+------------------------------------------------------+----------+
| Kosten nichts                                        |          |
+------------------------------------------------------+----------+


----

Welche Aussage ist korrekt?
^^^^^^^^^^^^^^^^^^^^^^^^^^^


Wählen Sie eine oder mehrere Antworten:

+---------------------------------------------------+----------+
| Frage                                             | korrekt? |
+===================================================+==========+
| HTTP besitzt kein eigenes Nachrichtenformat       |          |
+---------------------------------------------------+----------+
| HTTP ist zustandsbehaftet                         |          |
+---------------------------------------------------+----------+
| HTTP benutzt TCP                                  | X        |
+---------------------------------------------------+----------+
| HTTP sendet maximal ein Objekt pro TCP Verbindung |          |
+---------------------------------------------------+----------+


----

Wie erreicht man logarithmischen Suchaufwand im Chord-Ring?
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Wählen Sie eine oder mehrere Antworten:

+------------------------+----------+
| Frage                  | korrekt? |
+========================+==========+
| Direkte Nachfolger     |          |
+------------------------+----------+
| Finger Table           | X        |
+------------------------+----------+
| Hash-Berechnung        |          |
+------------------------+----------+
| Parallele Suchanfragen |          |
+------------------------+----------+


----

Was ist bei strukturierten und unstrukturierten Overlays unterschiedlich?
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Wählen Sie eine oder mehrere Antworten:

+--------------------------------------------------+----------+
| Frage                                            | korrekt? |
+==================================================+==========+
| Bekanntheit der Topologie und Berechenvorschrift | X        |
+--------------------------------------------------+----------+
| Erfolg einer Suchanfrage                         |          |
+--------------------------------------------------+----------+
| Implementierung der Suche                        | X        |
+--------------------------------------------------+----------+
| Ob Suchanfrage determiniert                      |          |
+--------------------------------------------------+----------+


----

Wie wird Routing im Internet realisiert?
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Wählen Sie eine oder mehrere Antworten:

+--------------------------------------------+----------+
| Frage                                      | korrekt? |
+============================================+==========+
| Durch Struktur des Overlays                |          |
+--------------------------------------------+----------+
| Auf IP-Schicht                             | X        |
+--------------------------------------------+----------+
| Inhaltsbasiert anhand "Named Data Objects" |          |
+--------------------------------------------+----------+
| Oft auf Anwendungsschicht                  |          |
+--------------------------------------------+----------+


----

Zeigt der theoretische Chord-Finger :math:`id+2^{i-1}` immer genau auf einen Peer?
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^


Wählen Sie eine oder mehrere Antworten:

+-------+----------+
| Frage | korrekt? |
+=======+==========+
| Ja    |          |
+-------+----------+
| Nein  | X        |
+-------+----------+


----

Welche Daten werden in strukturierten Overlays wie Chord ausgetauscht?
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Wählen Sie eine oder mehrere Antworten:

+-------------------------------------------------------+----------+
| Frage                                                 | korrekt? |
+=======================================================+==========+
| Keine: stellt nur Kommunikationsoverlay zur Verfügung |          |
+-------------------------------------------------------+----------+
| Nutzdaten                                             |          |
+-------------------------------------------------------+----------+
| Beides: Nutz- und Metadaten                           |          |
+-------------------------------------------------------+----------+
| Metadaten                                             | X        |
+-------------------------------------------------------+----------+


----

Wie baut man am besten einen "Application-Layer Multicast Tree" für Live-Videostreaming?
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Wählen Sie eine oder mehrere Antworten:

+----------------------------------------------+----------+
| Frage                                        | korrekt? |
+==============================================+==========+
| Zuverlässige Knoten als Blätter              |          |
+----------------------------------------------+----------+
| Egal - Overlay passt sich selbstständig an   |          |
+----------------------------------------------+----------+
| Maximale Tiefe                               |          |
+----------------------------------------------+----------+
| Viele Kinder für Knoten mit hoher Bandbreite | X        |
+----------------------------------------------+----------+

----

Welcher Dienst erzeugt den meisten Verkehr (Downstream)?
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Wählen Sie eine oder mehrere Antworten:

+------------+----------+
| Frage      | korrekt? |
+============+==========+
| YouTube    | X        |
+------------+----------+
| Google     |          |
+------------+----------+
| BitTorrent |          |
+------------+----------+


----

Bei welchem Dienst ist das Verhältnis von Upstream- und Downstream-Verkehr in etwa gleich?
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^


Wählen Sie eine oder mehrere Antworten:

+------------+----------+
| Frage      | korrekt? |
+============+==========+
| Skype      | X        |
+------------+----------+
| BitTorrent |          |
+------------+----------+
| YouTube    |          |
+------------+----------+

----

Welche Komponenten enthält eine URL?
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^


Wählen Sie eine oder mehrere Antworten:

+-------------------------+----------+
| Frage                   | korrekt? |
+=========================+==========+
| Hostname und IP-Adresse |          |
+-------------------------+----------+
| HTTP-Request            |          |
+-------------------------+----------+
| Dateityp                |          |
+-------------------------+----------+
| Anwendungsprotokoll     | X        |
+-------------------------+----------+

----

Wodurch entstehen unstrukturierte Overlays?
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Wählen Sie eine oder mehrere Antworten:

+---------------------------------+----------+
| Frage                           | korrekt? |
+=================================+==========+
| Physikalisches Netzwerk         |          |
+---------------------------------+----------+
| Implementierung der Algorithmen | X        |
+---------------------------------+----------+
| Präferenz der Nutzer            | X        |
+---------------------------------+----------+
| Routing im Internet             |          |
+---------------------------------+----------+

----

Wie kann man in hierarchischen Overlays die Superpeers miteinander verknüpfen?
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Wählen Sie eine oder mehrere Antworten:

+-----------------------------------------------------+----------+
| Frage                                               | korrekt? |
+=====================================================+==========+
| Mit strukturierten Overlays                         | X        |
+-----------------------------------------------------+----------+
| Mit unstrukturierten Overlays                       | X        |
+-----------------------------------------------------+----------+
| Nicht nötig: Superpeers haben komplette Information |          |
+-----------------------------------------------------+----------+
| Implementierung der Algorithmen                     | X        |
+-----------------------------------------------------+----------+
| Vollvermascht                                       | X        |
+-----------------------------------------------------+----------+

----

Was passiert mit einem TCP-Segment, wenn es an die Netzwerkschicht weitergegeben wird?
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Wählen Sie eine oder mehrere Antworten:

+---------------------------------------------------+----------+
| Frage                                             | korrekt? |
+===================================================+==========+
| Header wird entfernt (Transportschicht-Header)    |          |
+---------------------------------------------------+----------+
| Header wird hinzugefügt (Transportschicht-Header) | X        |
+---------------------------------------------------+----------+
| Header wird entfernt (Netzwerkschicht-Header)     |          |
+---------------------------------------------------+----------+
| Header wird hinzugefügt (Netzwerkschicht-Header)  |          |
+---------------------------------------------------+----------+

----

Welche Applikationsklasse hat aktuell den größten Verkehrsanteil im Internet?
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^


Wählen Sie eine oder mehrere Antworten:

+----------------+----------+
| Frage          | korrekt? |
+================+==========+
| Internet Video | X        |
+----------------+----------+
| File Sharing   |          |
+----------------+----------+
| Gaming         |          |
+----------------+----------+
| Email          |          |
+----------------+----------+

----

Was wird von P2P-Anwendungen implementiert?
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Wählen Sie eine oder mehrere Antworten:

+--------------------------------------------+----------+
| Frage                                      | korrekt? |
+============================================+==========+
| Resource Mediation (Resourcenverwaltung)   | X        |
+--------------------------------------------+----------+
| Transport Control Protocol (TCP)           |          |
+--------------------------------------------+----------+
| Resource Access Control (Resourcenzugriff) | X        |
+--------------------------------------------+----------+
| Content Delivery                           | X        |
+--------------------------------------------+----------+

----

Warum bzw. wann setzt man das unzuverlässige UDP-Protokoll ein?
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^


Wählen Sie eine oder mehrere Antworten:

+---------------------------------------------+----------+
| Frage                                       | korrekt? |
+=============================================+==========+
| Datenverluste unwichtig                     | X        |
+---------------------------------------------+----------+
| Einfache Antwort von Server in 1 Paket      |          |
+---------------------------------------------+----------+
| Schnelle Übertragung von großen Datenmengen |          |
+---------------------------------------------+----------+
| Heartbeats                                  |          |
+---------------------------------------------+----------+

----

Welche Aussage ist korrekt?
^^^^^^^^^^^^^^^^^^^^^^^^^^^


Wählen Sie eine oder mehrere Antworten:

+-----------------------------------------------------------+----------+
| Frage                                                     | korrekt? |
+===========================================================+==========+
| Strukturierte Overlays kann man nicht fluten              |          |
+-----------------------------------------------------------+----------+
| Unstrukturierte haben mehr Overhead als strukturierte     |          |
+-----------------------------------------------------------+----------+
| Unstrukturierte Overlays haben keine Shortcuts            | X        |
+-----------------------------------------------------------+----------+
| Strukturierte Overlays sind schneller als unstrukturierte |          |
+-----------------------------------------------------------+----------+

----

Welches sind P2P Anwendungen?
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Wählen Sie eine oder mehrere Antworten:

+--------------------------------------+----------+
| Frage                                | korrekt? |
+======================================+==========+
| BitTorrent                           | X        |
+--------------------------------------+----------+
| Content Distribution Networks (CDN)  | X        |
+--------------------------------------+----------+
| Tor                                  |          |
+--------------------------------------+----------+
| Information-Centric Networking (ICN) |          |
+--------------------------------------+----------+

----

Wozu wird ein unstrukturiertes Overlay genutzt?
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^


Wählen Sie eine oder mehrere Antworten:

+------------------------------------------------+----------+
| Frage                                          | korrekt? |
+================================================+==========+
| Resource Mediation                             | X        |
+------------------------------------------------+----------+
| Resource Access Control                        | X        |
+------------------------------------------------+----------+
| Herstellen der Physikalische Konnektivität     |          |
+------------------------------------------------+----------+
| Resource Mediation und Resource Access Control | X        |
+------------------------------------------------+----------+

----

Von welchem Parameter hängt die tatsächliche Anzahl der Finger-Peers im Chord-Ring ab?
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Wählen Sie eine oder mehrere Antworten:

+------------------------------------+----------+
| Frage                              | korrekt? |
+====================================+==========+
| Wertebereich der Hash-Funktion: 2m | X        |
+------------------------------------+----------+
| Redundanzfaktor: r                 | X        |
+------------------------------------+----------+
| Anzahl der Peers im System: n      | X        |
+------------------------------------+----------+
| Anzahl der Dokumente: d            |          |
+------------------------------------+----------+

----

Ist P2P zentral oder dezentral?
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Wählen Sie eine oder mehrere Antworten:

+----------------------------------------+----------+
| Frage                                  | korrekt? |
+========================================+==========+
| Immer dezentral                        |          |
+----------------------------------------+----------+
| Immer zentral                          |          |
+----------------------------------------+----------+
| Kann nie ausschließlich dezentral sein | X        |
+----------------------------------------+----------+
| Kann nie ausschließlich zentral sein   | X        |
+----------------------------------------+----------+
