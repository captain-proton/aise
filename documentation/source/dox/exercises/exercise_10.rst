Übungsblatt 10 – WebServices
============================

Aufgabe 1: Theorie
------------------

*WebService*
^^^^^^^^^^^^

Ein WebService ist ein über das Internet adressierbare URL, über die Resourcen in einen gewählten Format erstellt, abgefragt, geändert und gelöscht werden können. Als Austauschformate dienen dazu standardisierte Sprachen wie XML und/oder JSON.

Fehlende Stellen im Diagramm
^^^^^^^^^^^^^^^^^^^^^^^^^^^^

:a: WSDL
:b: UDDI
:c: WSDL
:d: SOAP

Erläuterungen
^^^^^^^^^^^^^

:WSDL: WebService Definition Language. Enthält in XML die Beschreibung welche Dienste ein Provider anbietet und wie diese aufgerufen werden
:UDDI: Schnittstelle, die der Verzeichnisdienst anbietet, um Informationen über die einzelnen Provider abzufragen.
:SOAP: Enthält die eigentlichen Anfragen, die zwischen Client und Provider ausgetauscht werden. Fest definiertes XML Format, bestehend aus ``Envelope``, ``Header`` und ``Body``.

Adressierung eines externen Dienstes
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

1. Suche nach passendem Provider (mittlerweile nicht mehr über UDDI)
2. Anfrage einer WSDL
3. Integration in den Client

Aufgabe 2: SOAP
---------------

.. literalinclude:: ../../../../dox/exercise_10/src/main/java/de/paluno/calculator/client/Client.java
    :language: java

.. literalinclude:: ../../../../dox/exercise_10/src/main/java/de/paluno/calculator/server/Calculator.java
    :language: java

.. literalinclude:: ../../../../dox/exercise_10/src/main/java/de/paluno/calculator/server/CalculatorServer.java
    :language: java

