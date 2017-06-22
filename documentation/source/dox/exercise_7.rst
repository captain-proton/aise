Übungsblatt 7 – CORBA
=====================

Aufgabe 1: Theorie
------------------

1. Wann ist CORBA, RMI bzw. Sockets sinnvoll?

- Sockets bei Daten, die keine Objektstruktur verlangen, z.B. zur reinen Dateiübertragung oder (Audio/Video-)Streaming.
- RMI kann genutzt werden wenn sowohl Client als auch Server in Java geschrieben sind und lediglich Orts- und ...-Transparenz wichtig ist
- Für alle anderen Fälle ist CORBA geeignet, da es vor allem auch sprachunabhängig ist.

Bemerkung: Unterschiedliche Transparenzen werden in CORBA nur durch zusätzliche Services zur Verfügung gestellt.

2. Hierarchie in CORBA IDL?

.. code-block:: text

    module hindenbug {
        module HelloApp {
            ...
        };
    };

3. Komplexe Typen in CORBA?

- ``sequence``
- ``struct``
- ``union``
- ``enum``

``struct`` ähnelt dabei am ehesten einer Java-Klasse

4. Mapping zu den Typen

+--------------+-----------------------------------+
| Typ          | Mapping                           |
+==============+===================================+
| ``struct``   | ``class``                         |
+--------------+-----------------------------------+
| ``enum``     | ``Enumeration``                   |
+--------------+-----------------------------------+
| ``sequence`` | ``List<T>``                       |
+--------------+-----------------------------------+
| ``union``    | In C ``switch``, komplexes Layout |
+--------------+-----------------------------------+

Aufgabe 2.1: CORBA-IDL I
------------------------

