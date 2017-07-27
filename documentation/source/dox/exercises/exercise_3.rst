Übungsblatt 3 – Verteilung
==========================

Aufgabe 1: Theorie
------------------

1. Geben Sie einige Beispiele für Ihnen bekannte verteilte Systeme.
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

- Googles Suche
- Akamai CDN
- Amazon WS
- Banksystem
- Bahnticketsystem

Nicht nur Softwaresystem als Kontext verwenden.

2. Nennen Sie Eigenschaften verteilter Systeme.
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

- Ansammlung autonomer (virtueller) Rechner
- Verbunden durch ein Netzwerk
- Gemeinsame Resourcen
- Komponenten können ausfallen
- Komponenten werden parallel ausgeführt

3. Welche Vor- und Nachteile haben verteilte Systeme?
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

*Vorteile*:

- Aufteilung von Kapazitäten wodurch eine Lastverteilung betrieben wird
- Single-Point-of-Failure eher unwahrscheinlich
- Schnellere Antwortzeiten bei guter Verteilung
- Größere Leistung

*Nachteile*:

- Größerer Pflege- und Entwicklungsaufwand

    + Skalierung
    + Nebenläufigkeit

- Kostenintensiver

4. Welche Anforderungen führen i. d. R. zu verteilten Systemen?
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

- Sicherheit von Komponenten
- Erreichbarkeit von Komponenten
- Kosten (Netzwerk Inter-Domain-Communication)

Die Anforderungen an ein verteiltes System sind:

- Gemeinsame Nutzung der Betriebsmittel
- Offenheit
- Nebenläufigkeit
- Skalierbarkeit
- Fehlertolleranz

5. Erläutern Sie die Transparenzen in verteilten Systemen und deren Beziehungen untereinander (Abbildung Folie 28 im Skript):
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Unter der Transparenz versteht man wie sichtbar bzw. verborgen unterschiedliche Aspekte des Systems gegenüber Nutzern ist.

:Zugriffstransparenz: Der Zugriff auf eine Komponente erfolgt immer auf dieselbe Art und Weise egal ob die Resource lokal oder entfernt liegt, z.B. immer HTTP GET mit bestimmten Parametern

:Ortstransparenz: Der Ort an dem eine Resource liegt ist dem Benutzer verborgen, der Zugriff erfolgt immer über einen bestimmten Namen

:Nebenläufigkeitstransparenz: Es ist möglich, dass Nutzer gleichzeitig auf Resourcen zugreifen. Eine Synchronisation wird vor dem Benutzer versteckt.

:Replikationstransparenz: Es können mehrere Kopien einer Resource im System existieren, ohne dass dem Nutzer bewusst ist, dass es sich ggfs. um eine Kopie handelt. Bsp. Caches

Die Replikationstransparenz hängt von der Zugriffstransparenz und der Ortstransparenz ab, da Resourcen ggfs. auf unterschiedlichen physikalischen Maschinen auf unterschiedliche Weise vorhanden und adressiert werden können, was dem Nutzer nicht bekannt sein soll.

:Fehlertransparenz: Fehlerzustände werden im System versteckt, wenn sie ausschließlich Systeminterna betreffen. Bsp. Eine angefragte Resource ist in einer Komponente nicht verfügbar, da diese zur Zeit belegt ist und der Nutzer wird auf eine andere Komponente weitergeleitet, damit diese adressiert werden kann.

Die Fehlertransparenz ist von der Nebenläufigkeitstransparenz und der Replikationstransparenz abhängig, da auftretende Fehler durch den Einsatz von nebenläufig eingesetzten Systemen versteckt werden. Zu diesem Zweck müssen Resourcen mehrfach vorhanden sein.

:Migrationstransparenz: Das Verschieben von Objekten soll ohne die Kenntnis des Nutzers erfolgen.

Wie Replikationstransparenz, nur dass Objekte nicht kopiert sondern nur bewegt werden.

:Leistungstransparenz: Dem Nutzer steht die gesamte Leistung des Systems zur Verfügung.

Abhängig von Zugriffs- und Replikationstransparenz, da Objekte an unterschiedlichen Orten im System verfügbar sind. ... Warum keine Abhängigkeit zur Nebenläufigkeit?

:Skalierbarkeitstransparenz: Erweiterungen oder Austausch von Komponenten soll ohne Ausfall des Systems möglich sein.

Abhängig von Migration- und Replikationstransparenz, da wie in der Leistungstransparenz Objekte bzw. Resourcen bewegt oder kopiert werden müssen.

Aufgabe 2: Socket-Programmierung I
----------------------------------

Aufgabe 2.2: Programm schreiben
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

.. literalinclude:: ../../../../dox/exercise_3_2_2/src/main/java/de/hindenbug/dox/socket/SocketTest.java

Ausgabe
"""""""

::

    HTTP/1.1 200 OK
    Cache-Control: max-age=604800
    Content-Type: text/html
    Date: Tue, 16 May 2017 09:33:33 GMT
    Etag: "359670651+ident"
    Expires: Tue, 23 May 2017 09:33:33 GMT
    Last-Modified: Fri, 09 Aug 2013 23:54:35 GMT
    Server: ECS (iad/182A)
    Vary: Accept-Encoding
    X-Cache: HIT
    Content-Length: 1270

    <!doctype html>
    <html>
    <head>
        <title>Example Domain</title>

        <meta charset="utf-8" />
        <meta http-equiv="Content-type" content="text/html; charset=utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <style type="text/css">
        body {
            background-color: #f0f0f2;
            margin: 0;
            padding: 0;
            font-family: "Open Sans", "Helvetica Neue", Helvetica, Arial, sans-serif;

        }
        div {
            width: 600px;
            margin: 5em auto;
            padding: 50px;
            background-color: #fff;
            border-radius: 1em;
        }
        a:link, a:visited {
            color: #38488f;
            text-decoration: none;
        }
        @media (max-width: 700px) {
            body {
                background-color: #fff;
            }
            div {
                width: auto;
                margin: 0 auto;
                border-radius: 0;
                padding: 1em;
            }
        }
        </style>
    </head>

    <body>
    <div>
        <h1>Example Domain</h1>
        <p>This domain is established to be used for illustrative examples in documents. You may use this
        domain in examples without prior coordination or asking for permission.</p>
        <p><a href="http://www.iana.org/domains/example">More information...</a></p>
    </div>
    </body>
    </html>
