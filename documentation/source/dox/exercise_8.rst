Übungsblatt 8 – CORBA und Datenrepräsentation
=============================================

Aufgabe 1: CORBA Theorie
------------------------

Welche Services bietet CORBA an?
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

1. Basis

     a) Events/Ereignisse
     b) Lebenszyklus
     c) Namensdienst
     d) Persistenz
     e) Objektbeziehungen

2. Erweitert (durch Services)

     a) Nebenläufigkeit
     b) Transaktion
     c) Timer
     d) Sicherheit
     e) Lizenzen

Umsetzung von ``OUT`` und ``INOUT``-Parametern in Java
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Diese werden durch ``Holder``-Klassen als Parameter übergeben. Durch Verwendung dieser Klassen kann sowohl der Server als auch der Client über Änderungen informiert werden.

https://docs.oracle.com/javase/7/docs/api/org/omg/CORBA/doc-files/generatedfiles.html#holder

CORBA als verteiltes System
^^^^^^^^^^^^^^^^^^^^^^^^^^^

Von allen bisher kennengelernten Umsetzungen (``CORBA``, ``RMI``, ``Sockets``) ist CORBA das System mit dem am ehesten verteilte Anwendungen implementiert werden können. Durch die Services werden mehr als Orts- und Zugriffstransparenz ermöglicht.

+-------------+--------------------------------------------------------------+
| Transparenz | ``CORBA``                                                    |
+=============+==============================================================+
| Zugriff     | Über ein Interface erfoglt der Zugriff auf entfernte Objekte |
+-------------+--------------------------------------------------------------+
| Ort         | Verbindungsaufbau zum ORB explizit, daher eingeschränkt      |
+-------------+--------------------------------------------------------------+

Aufgabe 2: Datenrepräsentation Theorie
--------------------------------------

Datenbeschreibende Sprachen
^^^^^^^^^^^^^^^^^^^^^^^^^^^

- XML
- JSON
- CSV/TSV..

Anwendung in verteilten Systemen
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Diese Sprachen lassen eine (De-)Serialisierung zu und liefern ein Format bzw. Syntax über das Daten unabhängig von der eingesetzten Programmiersprache ausgetauscht werden können.

Interoperation, Interworking
^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Aufgabe 3: Datenrepräsentation
------------------------------

Aufgabe 3.1 CSV
^^^^^^^^^^^^^^^

.. code-block:: text

    Die Reise von Hans und Peter, 1974, 174, Arnold und Sohn, Wuppertal
    Das wunderschöne Leben in Essen, 2001, 5, Stadt Essen, Essen

Die hier vorliegenden Daten sind Zellenweise durch Komma voneinander getrennt. Jedes Komma beendet die aktuelle Zelle und startet mit einer neuen. Pro Zeile ist ein Datensatz vorhanden.

Aufgabe 3.2 XML
^^^^^^^^^^^^^^^

.. code-block:: xml

    <CATALOG>
        <CD>
            <TITLE>Empire Burlesque</TITLE>
            <ARTIST>Bob Dylan</ARTIST>
        </CD>
    </CATALOG>

Informationen finden sich in der XML lediglich in den Blattknoten. Es werden keine Attribute verwendet.

Aufgabe 4.3
^^^^^^^^^^^

Unterschied SAX - StAX:

- SAX liest die XML als Stream ein, StAX arbeitet über Events

    + Bei SAX muss ein für die XML entsprechender Handler implementiert werden, dessen Methoden ``startElement``, ``endElement`` etc. jeweils aufgerufen werden
    + Bei StAX wird ein ``XMLEventReader`` verwendet, um schrittweise durch die Ereignisse zu gehen

- Die Handler müssen entsprechend angepasst werden
