*******************
Klausurvorbereitung
*******************

OR-SQL Cheatsheet
=================

.. literalinclude:: exam_preparation/cheatsheets/orsql_cheatsheet.sql
   :language: SQL

Übersicht möglicher Relationen
==============================

+----------------+-------------------+------------+------------+--------------+
|                | independant/      | dependant/ | dependant/ | independant/ |
|                | shared            | exclusive  | shared     | exclusive    |
+================+===================+============+============+==============+
| ROW            | -                 | 1:1        | -          | -            |
+----------------+-------------------+------------+------------+--------------+
| REF            | 1:1               | -          | -          | -            |
|                | 1:n (from n-side) |            |            |              |
+----------------+-------------------+------------+------------+--------------+
| ARRAY          | 1:n REF!          | 1:n ROW!   | -          | -            |
| (fixed length) | n:m REF!          |            |            |              |
+----------------+-------------------+------------+------------+--------------+
| MULTISET       | 1:n REF!          | 1:n ROW!   | -          | -            |
|                | n:m REF!          |            |            |              |
+----------------+-------------------+------------+------------+--------------+

DTD Cheatsheet
==============

.. literalinclude:: exam_preparation/cheatsheets/dtd_cheatsheet.dtd

XML-Schema Cheatsheet
=====================

.. literalinclude:: exam_preparation/cheatsheets/xsd_cheatsheet.xml
   :language: XML

XQuery Cheatsheet
=================

.. literalinclude:: exam_preparation/cheatsheets/xquery_cheatsheet.xq
   :language: XQuery

Probeklausuren
==============

.. toctree::

    exam_preparation/abteilungen_sose_2009/abteilungen
    exam_preparation/auktionshaus_sose_2008/auktionshaus
    exam_preparation/kursangebot_sose_2015/kursangebot
