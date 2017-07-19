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

5. Erläutern Sie die Transparenzen in verteilten Systemen und deren Beziehungen untereinander (Abbildung Folie 28 im Skript):
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Unter der Transparenz versteht man wie sichtbar bzw. verborgen unterschiedliche Aspekte des Systems gegenüber Nutzern ist.

:Zugriffstransparenz: Art und Weise wie auf eine Komponente zugegriffen wird, z.B. immer HTTP GET mit bestimmten Parametern

Anwender müssen die Komponente einsehen können, um sie migrieren und/oder replizieren zu können

:Ortstransparenz: Zugriffe auf Resourcen und Objekte unabhängig der Kenntnis des Ortes

Zur Migration/Replikation müssen Spezifika existieren, wie die Komponente adressiert werden kann.

:Nebenläufigkeitstransparenz: Eine Komponente muss parallel zu anderen Komponenten ausgeführt werden können egal in welchem Zustand sich das System befindet. In Programmierungsprachen üblicherweise durch Synchronisation durchgeführt

Sobald Fehler in einer oder mehreren Komponenten entstehen müssen diese protokolliert werden (Bsp. Ticketbuchung bei bereits vergebenen Tickets)

:Replikationstransparenz: Eine Komponente muss unabhängig vom Gesamtsystem auf andere Rechner geklont werden können.

Ort und Name dürfen sich z.B. nicht ändern wenn auf das System zugegriffen wird, google.de muss also immer ein gleiches Verhalten liefern unabhängig vom Ort.

:Fehlertransparenz: Fehlerzustände werden im System versteckt, wenn sie ausschließlich Systeminterna betreffen. Bsp. Eine Komponente fällt aus und der Nutzer wird auf eine andere Komponente weitergeleitet. Der Nutzer muss dazu nicht informiert werden.

:Migrationstransparenz: Objekte sollen an andere Orte bewegt werden können.

Sollte eine Migration durchgeführt werden muss ersichtlich sein, ob die Skalierung des Systems gewährleistet ist. Die Leistung darf nicht beeinflusst werden.

:Leistungstransparenz: Lastverteilung im System soll gewährleistet werden


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
