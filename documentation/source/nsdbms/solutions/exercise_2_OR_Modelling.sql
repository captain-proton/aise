-- TYPE DEFINITIONS

-- address
CREATE TYPE AddressType AS (
    street VARCHAR(200),
    streetNo VARCHAR(20),
    city VARCHAR(200),
    zip INTEGER
) REF IS SYSTEM GENERATED;
-- alt. REF USING <Type>

-- hobby
CREATE TYPE HobbyType AS (
    name VARCHAR(100) NOT NULL,
    description VARCHAR(400),
    since_when DATE
) REF IS SYSTEM GENERATED;

-- person with name address, age, date of birth, hobbies
CREATE TYPE PersonType AS (
    name ROW(
            firstname VARCHAR(200) NOT NULL,
            lastname VARCHAR(200) NOT NULL
         ),
    dateOfBirth TIMESTAMP,
    addresses REF(AddressType) MULTISET, -- alt. ARRAY[X]
    hobbies REF(HobbyType) MULTISET,
    sex INTEGER,

    -- see https://en.wikipedia.org/wiki/ISO/IEC_5218
    CONSTRAINT valid_sex CHECK (sex IN (0, 1, 2, 9))
)
REF IS SYSTEM GENERATED,
INSTANTIATABLE,
NOT FINAL,
METHOD age() RETURNS INTEGER;

CREATE METHOD age FOR PersonType RETURNS INTEGER
    BEGIN
        RETURN EXTRACT (YEAR FROM SYSDATE - YEAR FROM dateOfBirth)
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
    siblings REF(ChildType) MULTISET,
    educationalInstitution REF(EducationionalInstitutionType)
)
REF IS SYSTEM GENERATED,
INSTANTIATABLE,
METHOD brothers() RETURNS MULTISET OF ChildType,
METHOD sisters() RETURNS MULTISET OF ChildType;

CREATE TYPE EducationionalInstitutionType AS (
    name VARCHAR(200) NOT NULL,
    addresses REF(AddressType) MULTISET,
    children REF(ChildType) MULTISET
)
REF IS SYSTEM GENERATED,
NOT INSTANTIATABLE;

CREATE TYPE KindergartenType UNDER EducationionalInstitutionType AS (
)
REF IS SYSTEM GENERATED,
INSTANTIATABLE;

CREATE TYPE SchoolType UNDER EducationionalInstitutionType AS (
    graduationName VARCHAR(200) NOT NULL
)
REF IS SYSTEM GENERATED,
INSTANTIATABLE;


-- TABLES
CREATE TABLE Address OF AddressType (
    REF IS addressOID SYSTEM GENERATED
);
-- use USER GENERATED if REF USING <type> is used

CREATE TABLE Hobby OF HobbyType (
    REF IS hobbyOID SYSTEM GENERATED
);

CREATE TABLE Person OF PersonType (
    addresses WITH OPTIONS SCOPE (Address),
    hobbies WITH OPTIONS SCOPE (Hobby),

    REF IS personOID SYSTEM GENERATED
);

CREATE TABLE Parent OF ParentType UNDER Person (
    addresses WITH OPTIONS SCOPE (Address),
    hobbies WITH OPTIONS SCOPE (Hobby),
    children WITH OPTIONS SCOPE (Child),

    REF IS parentOID SYSTEM GENERATED
);

CREATE TABLE Child OF ChildType UNDER Person (
    addresses WITH OPTIONS SCOPE (Address),
    hobbies WITH OPTIONS SCOPE (Hobby),
    mother WITH OPTIONS SCOPE (Parent),
    father WITH OPTIONS SCOPE (Parent),
    siblings WITH OPTIONS SCOPE (Child),
    educationalInstitution WITH OPTIONS SCOPE (EducationalInstitution),

    REF IS childrenOID SYSTEM GENERATED
);

CREATE TABLE EducationalInstitution OF EducationionalInstitutionType (
    addresses WITH OPTIONS SCOPE(Address),
    children WITH OPTIONS SCOPE(Child),

    REF IS educationalInstitutionOID SYSTEM GENERATED
);

CREATE TABLE Kindergarten OF KindergartenType UNDER EducationalInstitution (
    address WITH OPTIONS SCOPE(Address),
    children WITH OPTIONS SCOPE(Child),

    REF IS kindergartenOID SYSTEM GENERATED
);

CREATE TABLE School OF SchoolType UNDER EducationalInstitution (
    address WITH OPTIONS SCOPE(Address),
    children WITH OPTIONS SCOPE(Child),

    REF IS schoolOID SYSTEM GENERATED
);
