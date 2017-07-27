Übungsblatt 5 – RMI
===================

Aufgabe 1: Theorie
------------------

5.1.1
^^^^^

:IDL: Interface Definition Language
    Enthält eine abstrakte Form wie die Schnittstelle adressiert und aufgerufen wird. Datentypen werden innerhalb der IDL definiert.

5.1.2
^^^^^

1. Festlegung der Anwendungsfälle
2. Defintion der Methoden anhand der Anwendungsfälle
3. Generierung des Stubs um (Un-)Marshaling zu realisieren
4. Implementierung der Server und Clienten
5. Während der Laufzeit wird der Server in der Registry der entsprechenden Maschine hinterlegt

5.2.2
^^^^^

Welche Vorteile bietet die RMI-Implementierung gegenüber der Socket-Implementierung?

- Bei RMI kann auf Java-Interfaces zur Vereinbarung eines Protokolls zurückgegriffen werden
- Zur Adressierung kann eine Registry verwendet werden

Welche Transparenzen bieten Sockets, welche RMI?

+---------+---------------------+
| Sockets | RMI                 |
+=========+=====================+
| -       | Zugriffstransparenz |
+---------+---------------------+
| -       | Ort (eingeschränkt) |
+---------+---------------------+

Aufgabe 2: RMI-Programmierung I
-------------------------------

.. literalinclude:: ../../../../dox/exercise_5_2/src/main/java/de/hindenbug/dox/rmi/client/CalculatorClient.java
    :language: java

.. literalinclude:: ../../../../dox/exercise_5_2/src/main/java/de/hindenbug/dox/rmi/server/CalculatorServer.java
    :language: java

.. literalinclude:: ../../../../dox/exercise_5_2/src/main/java/de/hindenbug/dox/rmi/server/Calculator.java
    :language: java

.. literalinclude:: ../../../../dox/exercise_5_2/src/test/java/de/hindenbug/dox/rmi/CalculatorTest.java
    :language: java





