-- TYPE DEFINITIONS

-- address
CREATE TYPE AddressType AS (
    street VARCHAR(200),
    streetNo VARCHAR(20),
    city VARCHAR(200),
    zip INTEGER
) REF IS SYSTEM GENERATED;

-- hobby
CREATE TYPE HobbyType AS (
    name VARCHAR(100),
    description VARCHAR(400),
    since_when DATE
) REF IS SYSTEM GENERATED;

-- person with name address, age, date of birth, hobbies
CREATE TYPE PersonType AS (
    firstname VARCHAR(200) NOT NULL,
    lastname VARCHAR(200) NOT NULL,
    dateOfBirth TIMESTAMP,
    addresses REF(AddressType) MULTISET,
    hobbies REF(HobbyType) MULTISET,
    sex INTEGER,

    -- see https://en.wikipedia.org/wiki/ISO/IEC_5218
    CONSTRAINT valid_sex CHECK (sex IN (0, 1, 2, 9))
)
REF IS SYSTEM GENERATED,
NOT INSTANTIATABLE,
NOT FINAL,
METHOD age() RETURNS INTEGER;

CREATE METHOD age FOR PersonType RETURNS INTEGER
    BEGIN
        RETURN EXTRACT (YEAR FROM SYSDATE - dateOfBirth)
    END;

CREATE TYPE ParentType UNDER PersonType AS (
    spouse REF(ParentType),
    children REF(ChildType) MULTISET
)
REF IS SYSTEM GENERATED,
INSTANTIATABLE;

CREATE TYPE ChildType UNDER PersonType AS (
    father REF(ParentType),
    mother REF(ParentType),
    siblings REF(ChildType) MULTISET
)
REF IS SYSTEM GENERATED,
INSTANTIATABLE,
METHOD brothers() RETURNS MULTISET OF ChildType,
METHOD sisters() RETURNS MULTISET OF ChildType;


-- TABLES
CREATE TABLE Address OF AddressType ()
REF IS addressOID SYSTEM GENERATED;

CREATE TABLE Hobby OF HobbyType ()
REF IS hobbyOID SYSTEM GENERATED;

CREATE TABLE Person OF PersonType (
    addresses REF AddressType,
    hobbies REF HobbyType
)
REF IS personOID SYSTEM GENERATED;

CREATE TABLE Parent OF ParentType UNDER Person (
    addresses WITH OPTIONS SCOPE (Address),
    hobbies WITH OPTIONS SCOPE (Hobby),
    children REF ChildType
)
REF IS parentOID SYSTEM GENERATED;

CREATE TABLE Child OF ChildType UNDER Person (
    addresses WITH OPTIONS SCOPE (Address),
    hobbies WITH OPTIONS SCOPE (Hobby),
    mother REF ParentType,
    father REF ParentType,
    siblings REF ChildType
)
REF IS childrenOID SYSTEM GENERATED;
