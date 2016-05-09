Die hier gelisteten Lösungen wurden mit Hilfe unterschiedlicher Werkzeuge realisiert.

+--------+-------------+--------+
| Ordner | Erweiterung | Tool   |
+========+=============+========+
| umlet  | uxf         | Umlet  |
+--------+-------------+--------+
| yed    | graphml     | yEd    |
+--------+-------------+--------+
| uppaal | xml         | UPPAAL |
+--------+-------------+--------+


UPPAAL Tutorial
===============

- Zeiteinheiten in UPPAAL werden in Clocks gemessen

Notizen
-------

- Assignment = Update
- sync = gleichzeitiges ausführen
- guard = bedingung
- urgend = zustand muss dringend verlassen werden
- committed = zustand muss sofort verlassen werden


Übungsblatt 1
=============


Problem 1.1 Mealey-Automaten
----------------------------

1.1.1
^^^^^

Antwort: 2012

1.1.2
^^^^^

a) Ja: JAAA
b) Nein: Die Eingabe zwei aufeinander folgender Einsen ist nicht möglich


1.1.3
^^^^^

.. image:: ../concurrency/doc/solutions/Blatt1_Aufgabe_1.1.3.png


Problem 1.2 Gekoppelte Systeme
------------------------------

1.2.1
^^^^^

Wenn der Eingabepuffer des Systems größer eins ist kann eine Eingabe von zwei
mal a erfolgen. Alternativ kann eine Synchronisation nach der ersten Eingabe
erfolgen wodurch unterschiedliche Zustandsfolgen erreicht werden.

1.2.2
^^^^^

Nach Eingabe der Zeichenfolge a,b,a,b befindet sich Automat A2 im Zustand 2 und kann nur noch durch die Eingabe von b wieder in den Zustand 0 überführt werden.
Hier wird ein Deadlock erreicht, da von A2 zunächst die Verarbeitung von b? erwartet wird.

1.3.1
^^^^^

.. image:: ../concurrency/doc/solutions/yed/Blatt_1_Aufgabe_1.3.1.png

1.3.2
^^^^^

.. image:: ../concurrency/doc/solutions/yed/Blatt_1_Aufgabe_1.3.2.png


Übungsblatt 2
=============

Problem 2.1 Fehlerklassen
-------------------------

2.1.1 Deadlock
^^^^^^^^^^^^^^

.. image:: ../concurrency/doc/solutions/yed/Blatt_2_Aufgabe_2.1.1.png

2.1.2 Nicht spezifizierter Empfang
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

.. image:: ../concurrency/doc/solutions/yed/Blatt_2_Aufgabe_2.1.2.png

2.1.3 Pufferüberlauf
^^^^^^^^^^^^^^^^^^^^

.. image:: ../concurrency/doc/solutions/yed/Blatt_2_Aufgabe_2.1.3.png

2.1.4 Toter Code
^^^^^^^^^^^^^^^^

.. image:: ../concurrency/doc/solutions/yed/Blatt_2_Aufgabe_2.1.4.png

Problem 2.2 Automatenentwurf
----------------------------

.. image:: ../concurrency/doc/solutions/yed/Blatt_2_Aufgabe_2.2.png



Übungsblatt 2 Zusatz
====================

Erstellen Sie ein synchron gekoppeltes System aus zwei Automaten. Der erste ist der Getränkeautomat, der zweite der Kunde.

Der Kunde hat folgende Wahlmöglichkeiten:

Cola, Fanta, Sprite, diese 3 Sorten jeweils in groß, mittel und klein.

Getränkeauswahl, bezahlen sowie Getränk entnehmen sollen durch Nachrichten dargestellt werden.

Während das Getränk im Automaten zubereitet wird soll gespeichert werden (z.B. über boolean- oder int-Variablen) welches Getränk zubereitet wird. Dieser Wert soll erst beim Entnehmen wieder auf 0 gesetzt werden.


Zusatzübung 1
=============

Folieninformationen
-------------------

Folie 7
^^^^^^^

Bei asychroner Kommunikation ist die Linie von System 1 nach 2 und umgekehrt immer durchgezogen.

Folie 9
^^^^^^^

- Grundsätzlich ist die Reihenfolge egal, es muss lediglich die Nachricht m4 vor m5 versendet werden
- m3 darf nicht ohne m4 gesendet werden

Folie 19
^^^^^^^^

- bei Google nicht nach Bit-State suchen sondern nach Supertrace-Algorithmus

Problem 1.1 Bit-State-Algorithmus
---------------------------------

.. image:: ../concurrency/doc/solutions/yed/Zusatzblatt_1_Aufgabe_1.1.png


+------+-----+--------------+
| h(z) | 0/1 | Kommentar    |
+======+=====+==============+
| 0    | 1   | (0, 0, e, e) |
+------+-----+--------------+
| 1    | 0   |              |
+------+-----+--------------+
| 2    | 1   | (1, 0, a, e) |
+------+-----+--------------+
| 3    | 1   | (2, 0, b, e) |
+------+-----+--------------+
| 4    | 0   |              |
+------+-----+--------------+
| 5    | 0   |              |
+------+-----+--------------+
| 6    | 1   | (2, 2, e, e) |
+------+-----+--------------+
| 7    | 0   |              |
+------+-----+--------------+
| 8    | 0   |              |
+------+-----+--------------+
| 9    | 0   |              |
+------+-----+--------------+

Problem 1.2
-----------

Sequenzdiagramm
^^^^^^^^^^^^^^^

.. image:: ../concurrency/doc/solutions/umlet/Zusatzblatt_1_Aufgabe_1.2.sequenz.png

Aktivitätsdiagramm
^^^^^^^^^^^^^^^^^^

.. image:: ../concurrency/doc/solutions/umlet/Zusatzblatt_1_Aufgabe_1.2.interaktivitaet.png

Zustandsdiagramm
^^^^^^^^^^^^^^^^

.. image:: ../concurrency/doc/solutions/umlet/Zusatzblatt_1_Aufgabe_1.2.zustand.png

Problem 1.3
-----------

1.3.1 Vorteil- und Nachteile
^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Vorteile:

- The goal of the bitstate hashing technique is to minimize the loss and maximize the coverage, for as large a range of relative values for M and N as possible.

Nachteile:

- Es ist möglich, dass bestimmte Globalzustände nicht erreicht und Fehler nicht gefunden werden

1.3.2 Hashing vs. Baumorientiert
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Bei Hashingverfahren ist der Aufwand um Fehlerfälle zu finden um ein vielfaches niedriger als die Untersuchung eines kompletten Baums.

1.3.3 *fair progress*
^^^^^^^^^^^^^^^^^^^^^

Bei asynchroner Kommunikation ist es möglich, dass bestimmte Teile des Ablaufs häufiger durchlaufen werden als andere. Es kann dazu kommen, dass ein Prozess andere Prozesse blockt. Durch fair progress soll vermieden werden, dass diese Prozesse nicht übergangen werden.


Übungsblatt 3
=============

Notizen
-------

Die Anzahl der möglichen Globalzustände beträgt:

Anzahl Zustände der Automaten \* Anzahl der möglichen Variablenbelegungen

Bsp:

Problem 3.1 UPPAAL: wechselseitiger Ausschluss
----------------------------------------------

3.1.1 UPPAAL Konstruktion
^^^^^^^^^^^^^^^^^^^^^^^^^

Declarations:

 .. code-block:: c

     int turn = 0;

Template:

.. image:: ../concurrency/doc/solutions/uppaal/blatt_3.1.1.png

System declarations:

.. code-block:: c

    // Place template instantiations here.
    worker_1 = Mutex(1);
    worker_2 = Mutex(2);
    // List one or more processes to be composed into a system.
    system worker_1, worker_2;

3.1.2 Message Sequence Chart
^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Message Sequence Chart:

.. image:: ../concurrency/doc/solutions/uppaal/blatt_3.1.2_msc.png


Als UML Sequenzdiagramm:

.. image:: ../concurrency/doc/solutions/umlet/Blatt_3_Aufgabe_3.1.2.png

3.1.3 Anzahl möglicher Globalzustände
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Die Zustände der Automaten bilden sich aus:

- Z1 = {idle, want, crit}
- Z2 = {idle, want, crit}

Die Anzahl der Übergänge beträgt drei, da ``turn`` drei unterschiedliche Werte erhalten kann.

Somit bildet sich der Globalzustand aus:

G Teilmenge aus Z1 x Z2 x A1 x A2. Die Anzahl der möglichen Globalzustände ist somit 27.


Problem 3.2 Automatenentwurf
----------------------------

3.2.1 - 3.2.3 UPPAAL Konstruktion
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Declarations:

.. code-block:: c

    chan coffee,tea,cola,fanta;
    chan abort;
    int output;

Template *Customer*:

.. image:: ../concurrency/doc/solutions/uppaal/blatt_3.2.1.customer.png

Template *Automaton*:

.. image:: ../concurrency/doc/solutions/uppaal/blatt_3.2.1.automaton.png

System declarations:

.. code-block:: c

    customer_1 = Customer();
    customer_2 = Customer();
    automaton = Automaton();
    // List one or more processes to be composed into a system.
    system customer_1,customer_2,automaton;

3.2.4 Anzahl der Zustände
^^^^^^^^^^^^^^^^^^^^^^^^^

- Anzahl Zustände Kunde: 5
- Anzahl Zustände Automat: 8
- Anzahl Übergänge Kunde: 10
- Anzahl Übergänge Automat: 13

Anzahl der Gesamtzustände: 5 \* 8 \* 10 \* 13 = 5200
Alternative ohne Epsilon: 5 \* 8 \* 10 \* 9 = 3600
