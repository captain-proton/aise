Übung 3
=======

3.1 Bitcoin in Praxis
---------------------

3.1.1
^^^^^

- Bitcoinadresse des Empfängers
- Betrag
- Label (optional)

Der Empfänger muss der Transaktion nicht zustimmen.

3.1.2
^^^^^

Bei Bitcoin kann man zunächst nicht konkret von Guthaben im eigentlichen Sinn sprechen. Man hat eine gewisse Zahl an eingehenden Transaktionen, die man widerum ausgeben kann. Bei der Überweisung von Bitcoin kann man eine Gebühr für die Überweisung verlangen, die an denjenigen gezahlt werden muss, der die Transaktion durchführt (der Miner).

3.1.3
^^^^^

Eine Transaktion ist durch einen Block in der Blockchain enthalten. Der Block kann zwar an die Blockchain angehangen sein, wurde aber vom Netzwerk noch nicht akzeptiert. Erst bei Annahme durch das Netzwerk gilt die Transaktion als durchgeführt.

3.1.4
^^^^^

Es wird versucht innerhalb von 10 Minuten einen neuen Block an die Kette zu hängen. Grundsätzlich kann man die Bitcoins sofort ausgeben, aber bis die Transaktion akzeptiert wurde vergeht eben diese Zeit.

3.1.5
^^^^^

Bitcoin kann in einem Testmodus betrieben werden. Zu diesem Zweck wird von einem Server eine Test Blockchain ausgeliefert.

3.1.6
^^^^^

Die verwendeten Bitcoins haben keinerlei Wert. Die Schwierigkeit des Testnetzes beträgt die Hälte der echten Netzwerks.

3.2 Bitcoin in Theorie
----------------------

3.2.1
^^^^^

Theoretisch ist die Anzahl der Blöcke nicht begrenzt, praktisch ist der Aufwand einen neuen Block zu generieren irgendwann zu groß.

3.2.2
^^^^^

Bei ``Proof-of-Work`` muss der Erzeuger eines Blocks einen gewissen Aufwand betreiben, damit der Block angenommen wird. Hierzu wird eine Challenge übertragen, die der Erzeuger lösen muss. Bsp. Der Hashcode eines Blocks muss so modifiziert werden, dass durch das Anfügen einer Ziffer der Hash des Block inkl. der Ziffer in den ersten n Stellen eine null enthält.

Bei ``Proof-of-Stake`` wird das ``Proof-of-Work`` so modifiziert, dass es für den Client einfacher gemacht wird den Hash zu generieren.

3.2.3
^^^^^

Jeder erzeugte Block fügt 50 neue Bitcoins in das System ein. Diese Anzahl wird alle 210000 Blöcke halbiert.

Für die ersten 210000 Blöcke bedeutet das z.B.

:math:`210000 * 50`

Für die ersten 420000 Blöcke:

:math:`210000 * 50 + 210000 * 50 / 2`

Für die ersten 630000 Blöcke:

:math:`210000 * 50 + \dfrac{210000 * 50}{2} + \dfrac{210000 * 50}{4}`

Umgeformt ergibt das:

:math:`\dfrac{210000 * 50}{2^0} + \dfrac{210000 * 50}{2^1} + \dfrac{210000 * 50}{2^2}`

Daraus folgt:

:math:`\sum_{n=0}^\infty \dfrac{210000 * 50}{2^n}`

3.2.4
^^^^^

Der erste Block der Blockchain ist im Falle von Bitcoin hart kodiert. Alle Nachfolger beinhalten den Hash des vorigen Blocks und einen definierte Menge Daten. Im Falle von Bitcoin ist das die

- durchgeführten Transaktionen
- den ``Merkle-root`` aller! Transaktionen
- die Schwierigkeit zur Erstellung des Blocks
- und die dazugehörige Nummer zum ``Proof-of-Work``

Jeder Block erhöht den verwendeten Speicher der Blockchain, was irgendwann zu Problemen beim Erzeugen von neuen Blöcken führt. Die Skalierung ist beim gegebenen Stand also nicht gegeben.

3.2.5
^^^^^

Der ``Merkle-tree`` enthält in einer Baumform die Hashes aller Transaktionen des Blocks. Aus den beiden Transaktionen ``0`` und ``1`` wird der ``Merkle-root`` ``01`` gebildet. Er ist also ein Baum von Hashes von Hashes usw. Ein ``Merkle-root`` des Blocks ist gleich mit dem Vorgänger, so dass eine Kette an Blocks entsteht wodurch jede Transaktion geprüft werden kann.
