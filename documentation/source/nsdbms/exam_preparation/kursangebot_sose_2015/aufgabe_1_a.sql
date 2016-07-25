CREATE TYPE KursTyp AS (
    name VARCHAR(200),
    beschreibung VARCHAR(1000),
    treffpunkt ROW(
                   strasse VARCHAR(200),
                   hausnummer VARCHAR(200),
                   plz INTEGER,
                   stadt VARCHAR(200),
                   ),
    anfangszeit TIMESTAMP,
    -- dauer in minuten
    dauer INTEGER,
    teilnehmer REF(TeilnehmerTyp) MULTISET,
    REF IS SYSTEM GENERATED
);

CREATE TYPE EntspannungKursTyp UNDER KursTyp AS (
    bedarf VARCHAR(200) MULTISET,
    REF IS SYSTEM GENERATED
);

CREATE TYPE BewegungKursTyp UNDER KursTyp AS (
    -- hier entsteht eine ueberschneidung zwischen den teilnehmern
    -- des abstrakten kurses und dieses kurses. muss in anwendungslogik
    -- behandelt werden. es wird davon ausgegangen dass das level daten
    -- enthaelt wie "ausreichend", "zu gering", "gut", "sehr gut" etc.
    fitnesslevel ROW(
        level VARCHAR(100),
        teilnehmer REF(TeilnehmerTyp)
        ) MULTISET,
    aktivitaetswuensche REF(SportartTyp) ARRAY[3],
    regenalternative REF(SportartTyp) ARRAY[3],
    REF IS SYSTEM GENERATED
);

CREATE TYPE AusflugKursTyp UNDER EntspannungKursTyp AS (
    kleidung VARCHAR(200) ARRAY[10],
    REF IS SYSTEM GENERATED
);

CREATE TYPE SportartTyp AS (
    name VARCHAR(200),
    benoetigter_leistungsstand INTEGER,
    REF IS SYSTEM GENERATED
);

CREATE TYPE TeilnehmerTyp AS (
    name ROW(
             vorname VARCHAR(200),
             nachname VARCHAR(200)
             ),
    telefonnummern ROW(
            -- fuehrende nullen muessen erlaubt werden
            -- handynummern meist 4
            vorwahl VARCHAR(5),
            durchwahl VARCHAR(10)
        ) ARRAY[5],
    email VARCHAR(200) NULL,
    webseite VARCHAR(200) NULL,
    entspannungskurse REF(EntspannungKursTyp) MULTISET,
    bewegungskurse REF(BewegungKursTyp) MULTISET,
    ausflugskurse REF(AusflugKursTyp) MULTISET,
    METHOD gibAlleKurse() RETURNS KursTyp MULTISET,
    REF IS SYSTEM GENERATED
);

CREATE TABLE Kurs OF KursTyp AS (
    teilnehmer WITH OPTION SCOPE(Teilnehmer),
    REF IS kursOID SYSTEM GENERATED
);

CREATE TABLE EntspannungKurs OF EntspannungKursTyp UNDER Kurs AS (
    teilnehmer WITH OPTION SCOPE(Teilnehmer),
    REF IS entspannungKursOID SYSTEM GENERATED
);

CREATE TABLE BewegungKurs OF BewegungKursTyp UNDER Kurs AS (
    teilnehmer WITH OPTION SCOPE(Teilnehmer),
    REF IS bewegungKursOID SYSTEM GENERATED
);

CREATE TABLE AusflugKurs OF AusflugKursTyp UNDER Kurs AS (
    teilnehmer WITH OPTION SCOPE(Teilnehmer),
    REF IS ausflugKursOID SYSTEM GENERATED
);

CREATE TABLE Teilnehmer OF TeilnehmerTyp UNDER Kurs AS (
    kurse WITH OPTION SCOPE(Kurs),
    REF IS teilnehmerOID SYSTEM GENERATED
);
