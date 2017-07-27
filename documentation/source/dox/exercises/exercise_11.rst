Übungsblatt 11 – REST und Komponentenbasierte Anwendungen
=========================================================

Aufgabe 1: REST
---------------

1.1
^^^

`Konzepte <https://de.wikipedia.org/wiki/Representational_State_Transfer>`_ :

:Client-Server: Es sollen alle Eigenschaften der Client-Server-Architektur gelten
:Zustandlos: Jede Nachricht enthält alle Informationen, die für Client bzw. Server nötig sind, um die Nachricht verarbeiten zu können
:Client Caches: Verwendung von `HTTP Caching <https://de.wikipedia.org/wiki/HTTP_Caching>`_
:Gleichförmige Schnittstellen: - Jeder REST-konforme Dienst hat eine eindeutige Adresse (URL). Jede Informationen, die über diese Adresse kenntlich gemacht wurde wird als Resource bezeichnet
    - Zugängliche Informationen können unterschiedliche Repräsentationen haben (HTML, JSON, XML..), die Veränderung soll nur über eine Repräsentation erfolgen
    - REST-Nachrichten sollen selbstbeschreibend sein.
    - *HATEOAS*: Navigation wird ausschließlich durch URLs realisiert, die vom Server bereit gestellt werden (``href``, ``src``)
:Schichtenorientiert: Mehrschichtiger Aufbau. Dem Anwender wird lediglich eine Schnittstelle angeboten
:Code-on-demand: Im Bedarfsfall wird Code an den Client zur Ausführung übertragen (ähnlich ``JSONP``)

1.2
^^^

:Resource identifier: Adresse, die auf die Resource zeigt
:Representation identifier: Medientypen (Mimetype)
:Representation: Inhalt einer Resource

1.3
^^^

REST <-> WebServices

- Bei einem SOAP-Webservice ist man an XML gebunden. JSON z.B. ist nur umständlich möglich.
- Durch Verwendung eines Proxy sind die Schnittstellen sehr gut skalierbar
- Bessere Ablaufunterstützung durch HATEOAS
- Weniger Last durch REST (kein SOAP-Envelope/Header)
- Besser testbar da lediglich ein HTTP-Client benötigt wird
- WebServices bieten mehr Standards z.B. zur Authentifizierung
- Datentypen in WebServices möglich, in REST nicht

Aufgabe 2: Komponentenbasierte Anwendungen
------------------------------------------

2.1
^^^

Ein Application Server bettet eine oder mehrere Komponenten ein in unterschiedliche Container ein, die diese dann ausführen:

1. Persistenz
2. Geschäftslogik
3. UI

Anwendungen kommunizieren hierbei lediglich mit der UI oder der Geschäftslogik. Der App. Server sorgt für eine Skalierung, Verteilung und Ausführung der Komponenten.

2.2
^^^

Ziel des Application Servers ist möglichst viel Komplexität vor den Entwicklern bzw. Systembetreuern zu verstecken.

2.3
^^^

Application Server Instanzen werden auf verschiedenen physikalischen Maschinen eingesetzt (Cluster), wodurch je nach Bedarf Komponenten verteilt und ausgeführt werden können. Hierzu muss eine Komponente zur Lastverteilung genutzt werden.

2.4 & 2.5 & 2.6
^^^^^^^^^^^^^^^

Die einzelnen Container ermöglichen Kommunikation der Komponenten untereinander und die Kommunikation mit Anwendungen. Die UI stellt überlicherweise HTML-Seiten zur Verfügung, die in Browsern dargestellt werden. Thin-Client Anwendungen kommunizieren direkt mit der Geschäftslogik. Die Persistenz übernimmt die Speicherung der Daten und wird im Normalfall nicht nach außen kommuniziert.


