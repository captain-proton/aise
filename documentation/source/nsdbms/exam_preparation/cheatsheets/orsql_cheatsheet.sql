-- TYPE commands

CREATE TYPE <Name> [UNDER <OtherType>] AS (
    <columnName> <columnType> [NOT NULL],
    -- Examples
    personNo INTEGER,
    birthDay DATE NOT NULL,
    budget INTEGER,
    job JobType,
    -- inline constraints
    firstLanguage VARCHAR(200) NOT NULL,
    noEmployeed SMALLINT CHECK (VALUE >= 0)

    -- References

    -- 1:1 (dependant, exclusive)
    name ROW(
             firstname VARCHAR(200),
             lastname VARCHAR(200),
             )

    -- 1:1 (independant, shared)
    address REF(AddressType),

    -- n:1 (independant, shared)
    -- may be pointing to the same product therefore n:1
    bought REF(ProductType),

    -- 1:n fixed length (ARRAY) (independant, shared)
    -- n:m undefined length (MULTISET) (independant, shared)
    cars REF(CarType) (ARRAY[3] | MULTISET)

    -- 1:n with fixed length (ARRAY) (dependant, exclusive)
    -- 1:n undefined length (MULTISET) (independant, exclusive)
    bicycles ROW(
                 producer VARCHAR(200)
                 model VARCHAR(200)
                 ) (ARRAY[5] | MULTISET)
)

-- Constraints
CONSTRAINT <name>
    CHECK <columnName> <definition>,
-- Examples
CONSTRAINT budgetLimits
    CHECK budget BETWEEN 1000 AND 2000,

-- OID generation --

REF IS SYSTEM GENERATED,
-- for example INTEGER
REF USING <dataType>,
-- for example REF FROM (customerNo)
REF FROM (<column>),

-- optional
[NOT] INSTANTIATABLE,
[NOT] FINAL,

-- methods
[OVERRIDING] [INSTANCE | STATIC] METHOD age() RETURNS [(MULTISET | ARRAY) OF] <Type>
;


-- TABLE commands

CREATE TABLE <Name> OF <Type> [UNDER <OtherTable>] (
    -- every column except references are available with the dot notation
    -- and must not be declared

    -- references must be declared with an options scope to the table (not type)
    -- on subtables references must defined in the same way!
    cars WITH OPTIONS SCOPE(Car),
    address WITH OPTIONS SCOPE(Address),
    bought WITH OPTIONS SCOPE(Product)

    -- no structural information derived from supertable/view!! only from type
)
-- OID Usage
REF IS personNo SYSTEM GENERATED,
-- USING <dataType>
-- must be set by the user! not set by the system
REF IS personNo USER GENERATED,
REF IS personNo DERIVED, -- USING FROM (<column>)
;


-- VIEW commands

CREATE VIEW <Name> OF <Type> [UNDER <OtherView>] AS (
    -- the query can be of any form a sql query can look like

    SELECT *
    -- Table and view type must be the same
    FROM <Table>
    -- select only from table ignoring sub- and superviews
    FROM ONLY(<Table>)

    -- with a union of super and subview all additional attributes from the
    -- subview are discarded
    [UNION ALL CORRESPONDING ...]

    -- Examples
    SELECT *
    FROM Bicycle
    WHERE manufacturer = 'Vanmoof'

    -- use cardinality to count referenced items on 1:n and n:m relations
    SELECT *
    FROM ONLY(Bicycle)
    WHERE CARDINALITY(versions) > 1
)
;

-- when a view is inheriting a super view, the super view then comprises
-- all data from the sub view
-- A (all data from A, B and C -> may be excluded in query by using ONLY)
-- |\
-- B C


-- OR Statements

-- ROW and simple type ()
WHERE address.city = 'Winterfell'
-- ROW/[ARRAY|MULTISET]
WHERE addresses[0].city = 'Winterfell'
-- REF
WHERE address->city = 'Winterfell'
-- REF/ARRAY
WHERE addresses[0]->city = 'Winterfell'
-- REF/MULTISET
FROM Person, UNNEST(addresses) as address
WHERE address.city = 'Winterfell'

-- direct access
WHERE bicycle.name = 'Hodor'
-- UNNEST
FROM Manufacturer, UNNEST(bicycles) as bicycle
-- now bicycle is flattened as can be used as a retrieved object
-- references to the object are already resolved
-- UNNEST is only allowed inside the FROM clause
-- can be used with MULTISET and ARRAY
-- DEREF
WHERE bicycle.name = 'Hodor'
WHERE bicycle.master->name = 'Bran'
WHERE DEREF(bicycle.master).name = 'Hodor'

-- can be in hierarchies to retrieve object of specific tables
FROM Person EXCEPT CORRESPONDING TABLE Person, Manager

-- use to check if a object belongs to a specific table
DEREF (bicycleOID) IS OF (<Table> [OR <OtherTable>])
