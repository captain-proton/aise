*******************
Klausurvorbereitung
*******************

Fragen Teil 1 "Einführung"
==========================

    **Welcher Begriff von "Parallelität" und "Nebenläufigkeit" ist der allgemeinere? (S.9)**:

Parallelität bezeichnet das gleichzeitige Ausführen von Aktionen, während Nebenläufigkeit lediglich die Möglichkeit der Parallelität angibt. Nebenläufigkeit ist also der allgemeinere Begriff. Bei Nebenläufigkeit können Aktionen

- unabhängig laufen
- es besteht keine kausale Abhängigkeit
- die Aktionen sind symmetrisch


Fragen/Übungen Teil 2 "Automatenkopplung"
=========================================

    **Entwurf eines erweiterten endlichen Automaten (S.21)**:

*Aufgabenstellung*: Ein Prozess sendet Pakete, die er mit Sequenznummern 0, 1, 2, ..., 127 durchnummeriert, wobei die Zählung mit Paket Nr. 128 wieder von 0 beginnt. Auf Paket n folgt also Paket Nr. n+1 modulo 27.

*Lösung*:

.. image:: yed/automaton_page_21.png

----

    **Beschreiben Sie eine Ampel als Transitionssystem**:

*Zustandsmenge*:

- rot
- rot/gelb
- gelb
- grün

*Aktionen*:

- bereit machen
- fahren
- bremsen
- anhalten

----

    **UML Sequenzdiagramm parallele Ausführung (S.55)**:

.. image:: umlet/uml_sequence_page_55.png

*Welche sechs verschiedenen Nachrichtensequenzen sind möglich?*:

1. m3 -> m4 -> m5 -> m6
2. m3 -> m5 -> m6 -> m4
3. m3 -> m5 -> m4 -> m6
4. m5 -> m6 -> m3 -> m4
5. m5 -> m3 -> m4 -> m6
6. m5 -> m3 -> m6 -> m4

----

    **Erläutern Sie das Konzept des Mealy-Automaten an, indem Sie einen Automatengraphen zeichnen und alle Bestandteile benennen.**:

    **Was ist ein Transitionssystem? Geben Sie zwei Beispiele an!**:

Ein Transitionssystem enhält eine definierte Anzahl an Zuständen und eine Anzahl an Übergängen, um zwischen den Zuständen wechseln zu können.

1. Das gezeigte Ampelsystem
2. reguläre Ausdrücke in Programmiersprachen

    **Welche Erweiterungen für Automatenmodelle kennen Sie?**:

- endliche deterministische Automaten
- endliche nicht deterministische Automaten
- Kellerautomaten

    **Was sind "Spontanübergänge"? Geben Sie ein Zustandsübergangsdiagramm für einen indeterministischen Automaten an!**:

Spontanübergänge sind Übergange zwischen zwei Zuständen, die an keine Bedingung geknüpft sind und keine Anweisungen enthalten. Diese Übergange bezeichnet man auch als :math:`\epsilon`-Übergange.

    **Was ist synchrone Kopplung? Was ist asynchrone Kopplung? Welche alternativen Begriffe kennen Sie für "synchron" bzw. "asynchron"?**:

Bei einer synchronen Kopplung kann ein Übergang zwischen zwei Automaten nur bei gleichzeitigem Senden und Empfangen von Nachrichten erfolgen. Bei asynchroner Kopplung muss eine gesendete Nachricht nicht sofort verarbeitet werden. DIe Automaten werden nicht blockiert.

``synchron``: direkte Kopplung, Übereinkunftskopplung, Rendez-Vous-Kopplung
``asynchron``: speichernde Kopplung, Übertragungskopplung

    **Erläutern Sie das Prinzip der Erreichbarkeitsanalyse? Benutzen und erläutern Sie dazu die Begriffe Lokalzustand, Globalzustand, Transition, Wurzelknoten, Terminalknoten, .... !**:

Innerhalb einer Erreichbarkeitsanalyse werden die Lokalzustände ermittelt, die ein Automat mit Hilfe von Ein- und Ausgaben bzw. Transitionen erreichen kann. Gängig bei diesem Vorgang ist, den Baum aus den Wurzelknoten 0ee0 starten zu lassen (vorausgesetzt das System besteht aus zwei Automaten). Globalzustände innerhalb des Systems bezeichnen Kombinationen aus den einzelnen Lokalzuständen der Automaten und beinhalten den Globalzustand des Systems.

    **Wie formen Sie einen Erreichbarkeitsgraphen in einen Erreichbarkeitsbaum um?**:

Graphen besitzen keine doppelte Knoten, sondern verweisen wieder zurück auf bereits explorierte Zustände. Die Blätter eines Baumes sind Zustände, die während der Analyse bereits aufgetreten sind.

    **Welche Fehlertypen sind bei einer Zustandsraumexploration von Modellen für Kommunikationsprotokollen relevant?**:

- Syntaktische Fehler

    + Statischer bzw. Globaler Deadlock
    + Nicht spezifizierter Empfang
    + Protokollüberlauf
    + Nicht erreichbarer Code

    **Welche Vor- und Nachteile besitzt das sog. bit-state-Verfahren im Gegensatz zu einem vollständigen Explorationsverfahren?**:

Meist muss nur geprüft werden ob überhaupt ein Fehler innerhalb eines Modells auftritt. Zu diesem Zweck genügt das bit-state-Verfahren. Eine vollständige Exploration ist meist sehr teuer und aufwendig. Durch das Hashing werden bei Kollisionen des Hashwertes bestimmte Teilbäume ausgelassen. Hierdurch können aber tendenziell schneller Fehler gefunden werden, da überflüssige Teilbäumen verworfen werden.

    **Was ist Hashing? Wie ist eine Hashfunktion aufgebaut? Erläutern Sie "Kollisionsauflösung"! Was ist der Vorteil von Hashing gegenüber baumorientierten Verfahren? (Steht nicht explizit im Skript, aber gehört zur Allgemeinbildung!)**:

Hashing bezeichnet man als mathematische Einwegfunktionen. Ermittelte Werte aus diesen Funktionen können nicht auf den ursprünglichen Wert zurückgerechnet werden.
