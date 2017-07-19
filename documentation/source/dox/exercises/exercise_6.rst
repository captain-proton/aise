Übungsblatt 6 – RMI II
======================

Aufgabe 1: Theorie
------------------

1. -
2. Sockets und Protokolle
3. Registry, man braucht aber die Adresse der Registry. mehr Ortstransparenz ist nicht möglich.
4. Ortstransparenz und Zugrifftstransparenz, alle anderen Tranzparenzen können nicht unterstützt werden. Nur mit Java kompatibel.
5. Man kann nicht sicher sein, ob die Objekte als Thread gestartet werden oder nicht. Daher immer thread-safe.
6. (Funktionalität eines Stubs beschreiben)

Aufgabe 2: RMI-Programmierung II
--------------------------------

.. literalinclude:: ../../../../dox/exercise_6_2/src/main/java/de/hindenbug/dox/rmi/PersonInterface.java
    :language: java

.. literalinclude:: ../../../../dox/exercise_6_2/src/main/java/de/paluno/dox/rmiobjects/Person.java
    :language: java

.. literalinclude:: ../../../../dox/exercise_6_2/src/main/java/de/paluno/dox/rmiobjects/Server.java
    :language: java

.. literalinclude:: ../../../../dox/exercise_6_2/src/main/java/de/paluno/dox/rmiobjects/Client.java
    :language: java

.. literalinclude:: ../../../../dox/exercise_6_2/src/main/java/de/paluno/dox/rmiobjects/MyServer.java
    :language: java

