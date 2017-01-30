Introduction and Fundamentals I
===============================

How are the terms "requirements engineering" and "requirements" defined?
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

*Requirements engineering* ist definiert durch die drei Dimensionen *Content*, *Documentation* und *Agreement*. Es ist der Prozess, der während der Durchführung des Projektes statt finden. Alle Beteiligten legen die umzusetzenden Inhalte fest. Es muss eine Übereinstimmung und darauf folgende Dokumentation der der Inhalte erfolgen.

Ein *Requirement* (Anforderung) ist eine Bedingung oder Fähigkeit, die ein System erfüllen muss, um das Problem eines Nutzers lösen zu können. Eine Anforderung muss dokumentiert und durch die Nutzer akzeptiert sein.

Name the three goals of requirements engineering!
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

- ``Content``
- ``Agreement``
- ``Documentation``

How are the three dimensions interrelated?
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Die drei Dimensionen stehen jeweils senkrecht zueinander und beeinflussen sich gegenseitig. So kann ein Wachstum in der Agreement Dimension zu geringerer Dokumentation führen.

What is the goal of each dimension?
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

:Agreement: Einigung der Nutzer auf Anforderungen
:Documentation: Festhalten, der durch die Nutzer festgelegten Anforderungen
:Content: Das Verständnis der Nutzer des Systems

Which types of requirements do you know?
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

- ``Functional``
- ``Quality``
- ``Constraint``

Introduction and Fundamentals II
================================

What is a non-functional requirement?
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Eine nicht funktionale Anforderung ist meistens durch die Nutzer nicht ausreichend verstanden und auch nicht ausreichend dokumentiert, können aber als *nicht-funktional* dennoch akzeptiert werden.

Which types of constraints do you know?
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

:Kulturell: z.B. müssen bestimmte Symbole ausgeschlossen werden
:Organisatorisch: z.B. Projektabgabe
:Physisch: z.B. durch Witterung bedingte Anforderungen (-10°C - 50°C)
:Recht: z.B. Festlegungen durch die Straßenverkehrsordnung

Name the key interactions between requirements engineering and organizational processes.
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

- ``Marketing``
- ``Product Management``
- ``Customer Relationship Management``

What are the key interactions between requirements engineering and other development activities?
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

- ``Design``
- ``Quality Assurance``
- ``(Project Management)``
- ``(System Maintenance)``

What is the main goal of systems analysis?
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Das Hauptziel ist das Verständnis und die Definition von Anforderungen von bestehenden Systemen oder Prozessen in Bezug auf deren Funktion, Daten und Verhalten.

What are the shortcomings of requirements engineering as an early development phase?
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

- Keine Kontinuität beim Prozess des Requirement engineering
- Analyse der Ist-Situation
- Keine Widerverwendung der bestehenden Anforderungen
- Zu enger focus auf das Gesamtbild

Describe the concept of requirements engineering as a cross-lifecycle activity.
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Zwischen der Entwicklung des Systems und der Anforderungserhebung finden ständig Interaktionen statt. Man wechselt kontinuierlich zwischen beiden Aktivitäten. Zu diesem Zweck können z.B. Systeme wie ``Scrum`` oder ``Extreme Programming`` verwendet werden.

What are the advantages of establishing requirements engineering as a distinct organisational process?
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

- Änderungen können schneller erfasst und ggfs. umgesetzt werden
- Die Konsistenz des Systems auf einem aktuellen Stand und kann in der Dokumentation nachgelesen werden
- Das System kann variabler entwickelt werden

Framework for Requirements
==========================

What are the characteristics of a vision?
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

- Definiert die Absicht einer Änderung
- Kurz und prägnant
- Anleitung zur Entwicklung der Anforderungen
- Was steht im Vordergrund, nicht wie

What is the purpose of the framework for requirements engineering?
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

- Strukturierung der Anforderungserhebung
- Referenz für Kunden, aber auch für Entwickler

    + Training der Beteiligten
    + Analyse der Stärken und Schwächen in der Erhebung

How is the requirements engineering context structured?
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

- ``System Context``

    + ``Subject Facet``
    + ``Usage Facet``
    + ``IT System Facet``

- ``Development Context``

Name and describe the core activities of requirements engineering.
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

:Documentation: Formulierung von Anforderungen in einem festgelegten Format
:Elicitation: Erhebung von neuen und bereits bestehenden und relevanten Anforderungen
:Negotiation: Identifizierung von Konflikten, deren Analyse, Auflösung und Dokumentation

Name and describe the types of requirements artefacts used during requirements engineering.
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

:Goals: Abstrakte Form einer Eigenschaft, die im System realisiert werden soll
:Scenarios: Konkrete Beispiel, wann einer abstrakte Eigenschaft zutrifft oder fehl schlägt
:Solution Oriented Requirements: Genaue Formulierung der Scenarios in Form von in Daten, Funktion und Verhalten

What are the goals of the cross-sectional activities (i.e.,validation and management)?
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

- ``Validation``

    + Validierung der Anforderungsartefakte (``requirement artefacts``)
    + Validierung der Kernaktivitäten (``core activities``)
    + Validierung des Kontext (``context consideration``)

- ``Management``

    + Management der Anforderungsartefakte
    + Management der Kernaktivitäten
    + Management des Kontext

Context I
=========

Can a requirement be defined without knowing the context the system is embedded/operating in?
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Eine Anforderung ist immer in einem bestimmten Kontext, für das ein System entwickelt wird, definiert.

What influence does the context has?
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Der Kontext beeinflusst die Anforderungen, die ein System erfüllen muss

What are system context objects?
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Systemkontextobjekte sind materielle und immaterielle Objekte, die sich im Systemkontext befinden.

Describe typical examples of material and immaterial context objects.
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

- materiell

    + Stakeholder
    + Dokumentationen
    + Hardware

- immateriell

    + Organisationen
    + Prozesse
    + Daten

How can the system context be structured?
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Der Systemkontext kann in die drei Facetten ``Usage``, ``Subject`` und ``IT System`` unterteilt werden.

What are the relations in this structure?
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

In der Benutzungsfacette (``Usage``) befinden sich immaterielle Repräsentationen der verwendeten Objekte. In der Subjektfacette (``Subject``) sind die materiellen Objekte der Repräsentationen. In der IT-System-Facette befinden sich alle Objekte, die zur Datenverarbeitung der materiellen Objekte vorhanden sein müssen.

In der Übung anders definiert!

Why should properties and relationships of context objects be considered?
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Eigenschaften und Beziehungen von Objekten können sich während der Entwicklung ändern, daher müssen diese zu den Objekten erfasst werden. Zudem sind sie wesentliche Bestandteile der Objekte und sind zwingend zur vollständigen Dokumentation erforderlich.
