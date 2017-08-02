Übungsblatt 4 – Middleware-Prinzipien
=====================================

Aufgabe 1: Theorie
------------------

1. Wie werden entfernte Operationsaufrufe mit direkter Nutzung des Netzwerks implementiert?

Einigung für das Protokoll, Nachrichtenformat. Hier spielt das OSI-7-Schichtenmodell eine maßgebliche Rolle. Sender und Empfänger serialisieren und deserialisieren die Nachrichten.

2. Was sind die Nachteile der direkten Nutzung des Netzwerks ohne Middleware?

- manuell (de-)serialisieren
- Schnittstellen müssen bei Migration angepasst werden

3. Was sind die Aufgaben einer Middleware?

- Die Middleware kümmert sich um die Transparenzen, bzw. soll sie weitestgehend unterstützen
- Kapselung gegen die tatsächliche Implementierung, Bsp. Anfrage gegen ein C oder Java-Programm ist für den Aufrufer irrelevant

4. Wozu werden Stubs benötigt? Welche Aufgabe wird beim Marshalling/Unmarshalling durchgeführt?

- Stub sorgt für die Zugriffstransparenz, serialisiert und deserialisiert den Aufruf
- Marshalling bezeichnet den Prozess Daten für die Übertragung zu serialisieren
- Unmarshalling ist die Deserialisierung der Rohdaten in für das Programm verwertbare Objekte

5. Wie wird durch den Einsatz von Middleware Zugriffstransparenz hergestellt?

Durch eine Middleware wird der Zugriff auf eine Resource gekapselt und ist für den Nutzer immer gleich, egal ob z.B. ein HTTP Dienst die Anfrage beantwortet oder ein lokal ausgeführtes Programm

6. Wie wird durch den Einsatz von Middleware Ortstransparenz hergestellt?

In einer Registrierung wird festgehalten unter welcher Adresse ein Name antwortet.

Aufgabe 2: CalculatorServer
---------------------------

Application
^^^^^^^^^^^

.. literalinclude:: ../../../../dox/exercise_4_2/src/main/java/de/hindenbug/dox/calculator/CalculatorApplication.java
    :language: java

Client
^^^^^^

.. literalinclude:: ../../../../dox/exercise_4_2/src/main/java/de/hindenbug/dox/calculator/CalculatorClient.java
    :language: java

Server
^^^^^^

.. literalinclude:: ../../../../dox/exercise_4_2/src/main/java/de/hindenbug/dox/calculator/CalculatorServer.java
    :language: java


Aufgabe 3: Sockets und Objekte
------------------------------

Application
^^^^^^^^^^^

.. literalinclude:: ../../../../dox/exercise_4_2/src/main/java/de/hindenbug/dox/object/CalculatorApplication.java
    :language: java


Client
^^^^^^

.. literalinclude:: ../../../../dox/exercise_4_2/src/main/java/de/hindenbug/dox/object/CalculatorClient.java
    :language: java

Server
^^^^^^

.. literalinclude:: ../../../../dox/exercise_4_2/src/main/java/de/hindenbug/dox/object/CalculatorServer.java
    :language: java
