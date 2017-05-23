Übungsblatt 2 – Sichten und Modelle
===================================

Aufgabe 1: Theorie
------------------

Teilaufgabe 1.1
^^^^^^^^^^^^^^^

Eine Sicht kann mehrere Ausprägungen haben. Z.B. die Gesamtstruktur auf oberster Ebene. Durch Einsatz unterschiedlicher Sprachen und Werkzeuge wie z.B. UML kann diese Sicht erstellt werden. Eine Sicht kann aber auch den Ablauf einer bestimmten Funktion innerhalb des Systems darstellen.

- Abstraktion von Details
- Dokumentation des Systems

    + Entwickler
    + (neue) Anwender

Teilaufgabe 1.2
^^^^^^^^^^^^^^^

Viele der Darstellungen können durch UML-Diagramme dargestellt werden. Siehe hierzu `uml-diagrams.org <http://www.uml-diagrams.org>`_.

+--------------------------------+--------------------------------------------------------------------------------------------------+
| Darstellung                    | Modelltyp                                                                                        |
+================================+==================================================================================================+
| Komponenten und Schnittstellen | UML Composite Structure                                                                          |
+--------------------------------+--------------------------------------------------------------------------------------------------+
| Verteilung von Komponenten     | UML Deployment Diagram                                                                           |
+--------------------------------+--------------------------------------------------------------------------------------------------+
| Zustände und Zustandswechsel   | UML State machine                                                                                |
+--------------------------------+--------------------------------------------------------------------------------------------------+
| Prozesse                       | `BPMN <https://en.wikipedia.org/wiki/Business_Process_Model_and_Notation>`_, Aktivitätsdiagramme |
+--------------------------------+--------------------------------------------------------------------------------------------------+
| Daten                          | Data Flow Diagram, ER-Diagram, Ontologien                                                        |
+--------------------------------+--------------------------------------------------------------------------------------------------+
| Interaktionen                  | UML communication diagram, Sequenzdiagramm                                                       |
+--------------------------------+--------------------------------------------------------------------------------------------------+
| Organisatorische Hierarchien   | Organigramm, `oganizational chart <https://en.wikipedia.org/wiki/Organizational_chart>`_         |
+--------------------------------+--------------------------------------------------------------------------------------------------+
| Zeit                           | Sequenzdiagramm, UML timing diagram                                                              |
+--------------------------------+--------------------------------------------------------------------------------------------------+
| Anforderungen                  | UML use case                                                                                     |
+--------------------------------+--------------------------------------------------------------------------------------------------+

Teilaufgabe 1.3
^^^^^^^^^^^^^^^

*statisch*:

- Klassendiagramm

*dynamisch*:

- Sequenzdiagramm
- Objektdiagramm


Teilaufgabe 1.4
^^^^^^^^^^^^^^^

Modelle erleichtern das Verständnis des Systems für den Anwender. Zudem können Zusicherungen betreffend der Typsicherheit innerhalb einer Anwendung gemacht werden. Das schränkt das System allerdings auch direkt auf dieses Modell ein. Dynamische Ansätze können hier eher nicht verfolgt werden. Durch Abstraktion verliefert man aber auch Spezifika des Systems, die ggfs. wichtig sind.

Aufgabe 2: Code-Generierung
---------------------------

Aufgabe 2.1: Screencast
^^^^^^^^^^^^^^^^^^^^^^^

.. image:: codeGenerationExample.png


Aufgabe 2.2: Modellierung
^^^^^^^^^^^^^^^^^^^^^^^^^

.. image:: exercise_2_2.png
