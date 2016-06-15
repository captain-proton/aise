Objektrelationales SQL
======================

Aufgabe 1
^^^^^^^^^

*Aussage*: Return the name and the OID of all children between 5 and 7 years of age

.. code-block:: SQL

    SELECT name.firstname, name.lastname, childrenOID
    FROM Child
    WHERE age() >= 5 and age() <= 7;

Aufgabe 2
^^^^^^^^^

*Aussage*: Return the address of all parents who have a hobby whose description contains the word "goal".

.. code-block:: SQL

    SELECT DISTINCT DEREF(address)
    FROM Parent AS p, UNNEST (p.addresses) AS address
    WHERE UNNEST(p.hobbies).hobby->description LIKE '%goal%'

- ``addresses`` und ``hobbies`` sind jeweils referenzierte Objekte und müssen entsprechend über ``UNNEST`` und ``DEREF`` behandelt werden
- In der ``FROM``-Klausel ist ein Cross-Join enthalten, der bei entsprechender Datenmenge sehr groß wird, ist also mit Vorsicht zu genießen

Aufgabe 3
^^^^^^^^^

*Aussage*: Return all children who have at least one brother or sister who goes to a kindergarten

.. code-block:: SQL

    SELECT DISTINCT c
    FROM Child c
    WHERE EXISTS (
        SELECT *
        FROM UNNEST(c.siblings) AS sibling
        WHERE sibling->kindergarten IS NOT NULL
    );

- ``DISTINCT`` wird verwendet, da bei z.B. bei drei Geschwistern die im Kindergarten sind jedes Kind jeweils zwei mal in der Ergebnismenge auftaucht
- Sollte der Fehlerfall auftreten, dass ein Kind sein eigenes Geschwisterteil ist, wird dieser Fall hier ignoriert

    + Der Fehlerfall wird hier nicht durch eine Integritätsbedingung geprüft, da eine Korrektur der Daten erfolgen muss

Aufgabe 4
^^^^^^^^^

*Aussage*: Return name and the age of the spouse of each parent.

- *spouse* ist ungeschlechtlich

    + Die Ergebnismenge enthält jeden verheirateten Elternteil von Kindern

.. code-block:: SQL

    SELECT p.name.firstname, p.name.lastname, p.age()
    FROM Parent AS p
    WHERE p.spouse IS NOT NULL
        AND count(p.children) > 0

- Hier ist die Integritätsbedingung auf den Ehepartner gewählt, um alleinstehende Elternteile auszuschließen

Aufgabe 5
^^^^^^^^^

*Aussage*: Return the name of all those children who have the same first name as their parent.

.. code-block:: SQL

    SELECT name.firstname, name.lastname
    FROM Child AS c
    WHERE c.name.firstname = c.mother->name.firstname
        OR c.name.firstname = c.father->name.firstname


Aufgabe 6
^^^^^^^^^

*Aussage*: Create a view that contains the name and address of all parents and all children who live in *Christchurch*.

.. code-block:: SQL

    CREATE VIEW PeopleChristchurch OF PersonType (
        SELECT p.*
        FROM ONLY(Person) AS p
        WHERE (DEREF(p.personOID) IS OF TYPE(Parent)
            OR DEREF(p.personOID) IS OF TYPE(Child))
            AND EXISTS (
                SELECT *
                FROM UNNEST(p.addresses) AS address
                WHERE address->city = 'Christchurch'
    )


Aufgabe 7
^^^^^^^^^

*Aussage*: Return the first and last name of all parents from children who have at least one sibling who goes to the 'Flower-City' kindergarten who were born in 1987.

- Müssen beide Elternteile 1987 geboren sein?

.. code-block:: SQL

    SELECT p.name.firstname, p.name.lastname
    FROM Parent AS p
    WHERE p.dateOfBirth >= TIMESTAMP '1987-01-01 00:00:00' AND p.dateOfBirth <= TIMESTAMP '1987-12-31 23:59:59'
        AND p->spouse.dateOfBirth >= TIMESTAMP '1987-01-01 00:00:00' AND p->spouse.dateOfBirth <= TIMESTAMP '1987-12-31 23:59:59'
        AND EXISTS(
            SELECT child
            FROM UNNEST(p.children) AS child
            WHERE EXISTS(
                SELECT sibling
                FROM UNNEST(child->siblings) as sibling
                WHERE sibling->kindergarten->name = 'Flower-City'
            )
        )

- Wenn nur ein Elternteil 1987 geboren sein muss kann die Prüfung auf den Ehepartner entfallen
