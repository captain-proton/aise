*********
Vorlesung
*********

Organisation
============

Folie 4
^^^^^^^

Innerhalb der Vorlesung wird vor allem auf die Anwendungsschicht geschaut, weniger auf die niedrigen OSI-Schichten.

Folie 5
^^^^^^^

*software defined networking*: Abhebung der Logik von Netzwerken von den niedrigen Schichten in die Anwendungsebene.

*Overlay*: Innerhalb des Netzes sieht es so aus als wären Teilnehmer unterschiedlicher Netze in einem zusammengefasst (Folie 6).

Folie 7
^^^^^^^

*rote Punkte*: Anwender


Einleitung
==========

Folie 9
^^^^^^^

**Probleme P2P**:

- Single Point of Failure
- Ausfallsicherheit
- Kontrolle
- Suche nach Datenfragmenten (wer besitzt den Teil der Daten der gesucht wird)
- Plötzlicher Wegfall von Knoten

Folie 16
^^^^^^^^

Der Replikationsgrad hat eine maximalen Grad der Effizienz. Bestimmte Pakete müssen irgendwann nicht mehr übertragen werden.

Folie 51
^^^^^^^^

Implementierung von Semantik, Herstellung des Overlays. Hier wird nur die Vermittlung realisiert wo Resourcen zu erhalten sind.

Folie 52
^^^^^^^^

P2P != Overlay, P2P nutzen Overlays, hier z.B. Suche über das Overlays

Beispiele Overlay ohne P2P:

- IPv4
- YouTube
- Akamai

Bei P2P sind Peers gleichberechtigt, zudem bieten Peers Resourcen an. In Overlays ist nicht jeder Knoten gleichberechtigt, und es werden keine Resourcen verteilt.

Folie 54
^^^^^^^^

Unstrukturiert
""""""""""""""

Wichtiger Punkt *preferential attachment*: Es gibt Präferenzen wonach sich Knoten verbinden. Klassisches Beispiel ist Facebook (Nutzer).

Problem ist z.B. die Suche, da nicht bekannt ist wer wen kennt und wer was zur Verfügung stellt.

Strukuriert
"""""""""""

Realisierung einer effizienten Suche, da bekannt ist wo zu suchen ist.

Information Centric Networking
==============================

Folie 60
^^^^^^^^

Anlage von Kopien zur effizienten Verteilung im traditionellen networking. Man möchte von Was und wo zu Inhalt und IP.

Folie 61
^^^^^^^^

Speicherung von Daten im Netzwerk und nicht nur in den Klienten.

Folie 62
^^^^^^^^

Hier fehlt zwischen den beiden *Access Network* Wolken eine URL mit dem Inhalt der angefragt wird. Bei CDNs wird versucht den Clienten an einen Knoten zu leiten, der möglichst in der Nähe ist.

Folie 63
^^^^^^^^

``Pending Interest Table``: Realisierung eines Caches, je nachdem wie viele Clienten eine Resource laden möchten. Alle Knoten speichern die Daten sollten diese häufig angefragt werden.

Caches auf Basis von

- LRU (last recently used)

Folie 64
^^^^^^^^

Probleme

- Vergiftung von Inhalten
- Privatsphäre (Auswertung von Interessen)

Folie 65
^^^^^^^^

``FIB`` = Forwarding Information Base


Hintergrund zu Internettechnologien
===================================

Folie 5
^^^^^^^

Ohne das Internet sind bestimmte Geschäftmodelle nicht möglich.

Folie 9
^^^^^^^

Autonomen Systemen *gehört* ein bestimmter Teil des Netzes. Ein AS befindet sich auf Tier 1-3.

Folie 12
^^^^^^^^

Dargestellt sind die *Points of Presence*.

Folie 16
^^^^^^^^

Probleme hier, z.B. ein schlechtes Routing. Die Datenübertragung von Tier zu Tier kostet Geld! Im Overlay könnten die Rechner unter Tier 3 verbunden sein. Im CDN könnten Caches zur Lösung führen.

Folie 22
^^^^^^^^

Auf Layer 5 und 6 kann bei Bedarf verzichtet werden.

Folie 25
^^^^^^^^

Data plane ist zur Weiterleitung der Pakete verantwortlich (``local forwarding table``). Hier fehlt zwischen der ``routing algorithm`` und der ``local forwarding table`` eine gestrichelte Linie zur Unterteilung den ``control plane`` (oben) und der ``data plane`` (unten).

Folie 29
^^^^^^^^

Durch Application-layer routing wird die Adresse des Zielhosts geändert, da ggfs die Anwendung in der Lage ist eine bessere Route zum eigentlichen Ziel zu finden, als das Routing protocol. Es wird zwischen den (min. 3 Hosts) ein Overlay aufgebaut.

Folie 32
^^^^^^^^

Problem Persistent HTTP: Block von Inhalten, da Daten sequentiell geladen werden

Folie 34
^^^^^^^^

- ``UDP``: Echtzeitvideo
- ``TCP``: Übertragung bereits gespeicherter Videos

Content Distribution Networks
=============================

Folie 7
^^^^^^^

Flaschenhälse z.B. durch Positionierung und/oder Anbindung der Server.

Folie 11
^^^^^^^^

Typische Geschäftsbeziehung zwischen dem CDN-Anbieter und dem Provider der Art, dass der CDN-Anbieter gerne das CDN anbietet, dafür aber auch die Daten annimmt.

Folie 13
^^^^^^^^

Nicht nur ``Cache placement`` spielt eine Rolle, sondern auch ein ``Cache replacement`` spielt eine Rolle, Bsp. ``LRU (Least recently used)``

Folie 22
^^^^^^^^

Autorität im Sinn von Vergabe und Verwaltung von Namen (Punkt 2).

Folie 24
^^^^^^^^

DNS ist auf dem OSI 7 Schichten Modell auf Ebene 7.

Folie 26
^^^^^^^^

Auf den autoritativen Servern findet man *immer* die richtigen Adressen.

Folie 40
^^^^^^^^

Gutes Beispiel für die Verwendung in der Klausur.

HTTP Streaming
==============

Folie 3
^^^^^^^

RTP (Real Time Transport Protocol)
    Das Real-Time Transport Protocol (RTP) ist ein Protokoll zur kontinuierlichen Übertragung von audiovisuellen Daten (Streams) über IP-basierte Netzwerke.
RTCP (Real Time Control Protocol)
    Das RealTime Control Protocol dient der Aushandlung und Einhaltung von Quality-of-Service-Parametern durch den periodischen Austausch von Steuernachrichten zwischen Sender und Empfänger.
RTSP (Real Time Streaming Protocol)
    Das RealTime Streaming Protocol ist ein Netzwerkprotokoll zur Steuerung der kontinuierlichen Übertragung von audiovisuellen Daten oder Software über IP-basierte Netzwerke.

Internet Video Streaming
========================

Folie 10
^^^^^^^^

I-Frame enthält das komplette Bild, B- und P-Frames basieren auf den Daten.

Folie 11
^^^^^^^^

RTSP/RTP arbeiten auf den Schichten 5/6 des OSI Schichten Modells

Folie 12
^^^^^^^^

Jitter = Unregelmäßigkeiten beim Empfang der Pakete

Folie 16
^^^^^^^^

``simple bulk transfer`` ist Geldverschwendung, da zu viele Daten geladen, aber ggfs. gar nicht angesehen werden

Im ``paced transfer`` stopt ggfs. der Playback, da Daten des Videos nicht geladen wurden.

Mobile Geräte haben Probleme mit dem ``paced block transfer``, da zwischen den Zuständen des Gerätes gewechselt wird ((nicht) verbunden, Off-/Online). Beim Wechsel von Offline zu Online wird die Verbindung neu aufgebaut.

Bei der ``request segmentation`` können Kontrolldaten vom Client, z.B. Verbindungsgeschwindigkeit, an den Server gesendet werden. Dadurch kann z.B. die Größe geändert werden.

Folie 19
^^^^^^^^

Wenn der Inhalt komplett beim Provider liegt können Caching Mechanismen eingesetzt werden.

Folie 25
^^^^^^^^

Bei Spezialfällen und uninteressanten Übertragungen werden anstelle von z.B. DVB-H einfaches Unicast eingesetzt, da die Umsetzung der Protokolle zu teuer ist.

Folie 35
^^^^^^^^

Inhaltsabhängigkeit. Bei QoE gehört z.B. auch Ton dazu. QoE bezieht sich immer auf eine bestimmte Anwendung.

Folie 37
^^^^^^^^

Stalling bei Zeitpunkt ca. 40

Folie 38
^^^^^^^^

Der untere Schwellwert kommt sehr wahrscheinlich auf Grund der unterschiedlichen Frames (I-/B-/P-) zustande.

Folie 40
^^^^^^^^

Staling geschieht bei Begrenzung der Bandbreite, da es lediglich eine durchschnittliche Bitrate ist. Bei bestimmten Zeitpunkten wird aber mehr Bildinformation übertragen.

Folie 44
^^^^^^^^

Verzerrung des Bildes und Vergleich miteinander. Bei einem Vergleichswert von 1 ist eine hohe Übereinstimmung gefunden (Beispiel Mean Opinion Score)

Folie 45
^^^^^^^^

MOS ermittelt den Mittelwert. Probleme entstehen beim Testen der Qualität. Dazu wird das Video zunächst zum Nutzer übertragen und Fehler lokal simuliert. Bei großen Videos werden zunächst Daten vom Nutzer abgefragt, damit dieser den Test nicht abbricht.

Die andere Möglichkeit der Messung ist keine vorige Übertragung, sondern das Feststellen der Probleme im Nachhinein, um ggfs. eine schlechte Bewertung interpretieren zu können.

Folie 48
^^^^^^^^

Das ``Quantil`` ist ein Punkt in einer Verteilungsfunktion, z.B. wie viel Prozent der Nutzer haben einen Wert von x abgegeben (y-Achse 0-1, x-Achse z.B. MOS).

Konfidenzinterval nachlesen!!

Probleme entstehen, wenn man den Nutzer den Grund der Befragung nennt und im Nachhinein danach befragt. Es findet bereits dadurch eine Beeinflussung statt.

Folie 50
^^^^^^^^

Zweite Formel = Ableitung der QoE Funktion

Structured Overlays: Chord
==========================

Folie 4
^^^^^^^

Information in einem Peer genügt um eine globale Suche durchzuführen. Das ganze System skaliert!

Folie 5
^^^^^^^

Hauptaufgabe eines strukturierten Overlays ist die Suche nach Resourcen.

Folie 6/7
^^^^^^^^^

Vier Aufgaben:

:join: Ausführung der Suche/Teilnahme am Netzwerk (Unten links = Darstellung des gesamten Adressraums)
:leave: Verlassen des Netzwerks
:insert: key = Hash der Daten
:retrieve: Durch Shortcuts wird *log(n)* möglich, ansonsten ist der Aufwand der Suche linear

Folie 10
^^^^^^^^

Worst case bei verketteten Listen = O(n)
Bei :math:`2^i` ist der Aufwand logarithmisch

Folie 29
^^^^^^^^

Bei Amazon werden ständig verfügbare Server mittels DHT verwaltet, da das System ansich selbstorganisierend ist. Ansonsten wäre die DHT nicht nötig, da kein Churn, also wegfallende Peers, vorhanden ist.

Folie 31
^^^^^^^^

Ein *Shortcut* in Chord nennt sich *Finger*.

Folie 35
^^^^^^^^

Wenn z.B. der Knoten 1 mit Knoten 13 kommunizieren möchte geht er über den Knoten, der ihm bekannt dem Knoten 13 am nächsten liegt, also Knoten 9.

Folie 36
^^^^^^^^

Finger bleiben bei nicht komplett besetzten Netzen bestehen!

Unterschied theoretischer/echter Finger:

:echt: gespeichert in der Tabelle in vorhandener Peer
:theoretisch: auf Basis der Berechnungsvorschrift

Folie 93
^^^^^^^^

CAN Overlay ist mehrdimensional. In der Vorlesung wird zur Vereinfachung von einem zweidimensionalen Raum ausgegangen.

Folie 94
^^^^^^^^

Ein Peer hat eine direkte Kante zu seinen Nachbarn.


Unstructured Overlays
=====================

Einfacher zu implementieren als strukturierte Overlays.

Folie 4
^^^^^^^

Man kann suchen nach *Video\** anstatt den genauen Identifier zu verwenden. In strukturierten Overlays erst einmal nicht möglich.

Stichwort:

- Breitensuche
- Tiefensuche

Folie 13
^^^^^^^^

links: bei einer Wahrscheinlichkeit von P(k) haben alle Knoten einer Knotengrad von k

Folie 19
^^^^^^^^

Clustering Coefficient wichtig für die Klausur!

Folie 27
^^^^^^^^

Wichtig ist der letzte Rest auf der rechten Seite. Es gibt eine geringe Menge an Knoten, die eine sehr hohe Betweenness Centrality besitzen (beachte die logarithmische Skala).

Folie 39
^^^^^^^^

Um die Suchperformance zu erhöhen könnte man parallele Anfragen stellen, was allerdings die Last insgesamt erhöht.

Folie 42 - Ende fällt weg, da Anwendungsspezifisch


File Sharing Overlays
=====================

Folie 6
^^^^^^^

Performance parameter:

- Uploadbandbreite
- Churnrate/Zeit in der Clients verfügbar sind
- Nutzerverhalten
- Verhältnis Download/Upload

Folie 7
^^^^^^^

:Chunk: eDonkey
:Piece: BitTorrent

Folie 16
^^^^^^^^

Durch die Peer selection wird die Kapazität des Netzwerks optimal ausgenutzt.

Für Videostreaming uninteressant, da die Reihenfolge der Chunks relevant ist. ``Peer selection`` und ``Chunk/Piece selection`` merken!!! Hier findet Resource Access Control statt, keine Resource Mediation.

Folie 19
^^^^^^^^

Free rider sind Peers, die nur herunterladen wollen, aber nicht verteilen.

:eDonkey: es wird über den gesamten Client eine Rate ermittelt
:BitTorrent: die Rate wird über jede Datei eines Clients bestimmt

Um Cross-ISP-Traffic möglichst günstig zu gestalten, stellt der ISP performante Maschinen auf, die eine Verteilung übernehmen können und nicht jeder Client das einzeln erledigen muss.

Folie 23
^^^^^^^^

Es scheint, dass 4 parallele Uploads langsamer sind, aber durch Free rider ist der einzelne Upload wieder die schlechtere Idee.
