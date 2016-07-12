XQuery
======

Zusätzlich verwendete Tools und Anleitungen:

- `ZVON XPath Tutorial <http://zvon.org/xxl/XPathTutorial>`_
- `BaseX XML Datenbank <http://basex.org/>`_

    + Start der Anwendung: ``java -cp BaseX85.jar org.basex.BaseXGUI``
    + Um die verwendeten Funktionen nutzen zu können müssen diese in die Anwendung geladen werden

        * *Options* -> *Packages* -> *Install from URL*
        * http://www.xqueryfunctions.com/xq/functx-1.0-doc-2007-01.xq
- `XQuery Funktionsbibliothek <http://www.xqueryfunctions.com/xq/>`_

Als Datenquelle kann die :ref:`Beispiel-XML <exercise_4_nsdbms_sample_xml>` aus Übung 4 verwendet werden.

Aufgabe 1
^^^^^^^^^

*Aussage*: Return the name and the OID of all children between 5 and 7 years of age

.. literalinclude:: solutions/exercise_5_1.xq
   :language: XQuery

Aufgabe 2
^^^^^^^^^

*Aussage*: Return the address of all parents who have a hobby whose description contains the word "goal".

.. literalinclude:: solutions/exercise_5_2.xq
   :language: XQuery


Aufgabe 3
^^^^^^^^^

*Aussage*: Return all children who have at least one brother or sister who goes to a kindergarten

.. literalinclude:: solutions/exercise_5_3.xq
   :language: XQuery


Aufgabe 4
^^^^^^^^^

*Aussage*: Return name and the age of the spouse of each parent.

.. literalinclude:: solutions/exercise_5_4.xq
   :language: XQuery


Aufgabe 5
^^^^^^^^^

*Aussage*: Return the name of all those children who have the same first name as their parent.

.. literalinclude:: solutions/exercise_5_5.xq
   :language: XQuery


Aufgabe 6
^^^^^^^^^

*Aussage*: Create a view that contains the name and address of all parents and all children who live in *Christchurch*.

.. literalinclude:: solutions/exercise_5_6.xq
   :language: XQuery


Aufgabe 7
^^^^^^^^^

*Aussage*: Return the first and last name of all parents from children who have at least one sibling who goes to the 'Flower-City' kindergarten who were born in 1987.

.. literalinclude:: solutions/exercise_5_7.xq
   :language: XQuery

