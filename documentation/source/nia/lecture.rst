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

