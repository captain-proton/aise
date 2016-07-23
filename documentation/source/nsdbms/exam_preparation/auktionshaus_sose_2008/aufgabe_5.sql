CREATE TYPE ArtikelTyp AS (
    bild BLOB,
    beschreibung VARCHAR(1000),
    names VARCHAR(200) ARRAY[3],
    ROW preise (
        waehrung VARCHAR(20),
        wert FLOAT,
    ) ARRAY[6],
    farbe INTEGER,
    gewicht INTEGER
)
REF USING INTEGER
;

CREATE TYPE ArtikelPaketTyp AS (
    artikel REF(ArtikelTyp) MULTISET,
    artikelPakete REF(ArtikelPaketTyp) MULTISET
)
REF IS SYSTEM GENERATED
;

CREATE TYPE AuktionTyp AS (
    anfangszeit TIMESTAMP,
    endzeit TIMESTAMP,
    ROW anfangspreis (
        waehrung VARCHAR(20),
        wert FLOAT,
    )
    gebote REF(GebotTyp) MULTISET
)
REF IS SYSTEM GENERATED
;

CREATE TYPE GebotTyp AS (
    bieter REF(BieterTyp) NOT NULL,
    ROW preis (
        waehrung VARCHAR(20),
        wert FLOAT,
    ) NOT NULL,
    auktion REF(AuktionTyp),
    artikel REF(ArtikelTyp),
    artikelpaket REF(ArtikelPaketTyp),
)
REF IS SYSTEM GENERATED
;

CREATE TYPE BieterTyp AS (
    ROW name (
        vorname VARCHAR(200),
        nachname VARCHAR(200)
    ),
    bietername VARCHAR(200) MULTISET,
    ROW konto(
        ktonr VARCHAR(100),
        blz VARCHAR(100)
    ),
    ROW address(
        zip CHAR(5),
        city VARCHAR(200),
        street VARCHAR(200),
        streetNumber VARCHAR(10)
    )
);

CREATE TABLE Artikel OF ArtikelTyp AS (
    artikelID INTEGER PRIMARY KEY NOT NULL
)
REF USING (artikelID)
;

CREATE TABLE ArtikelPaket OF ArtikelPaketTyp AS (
    artikel WITH OPTION SCOPE(Artikel),
    artikelpakete WITH OPTION SCOPE(ArtikelPaket)
)
REF IS artikelOID SYSTEM GENERATED
;

CREATE TABLE Auktion OF AuktionTyp AS (
    gebote WITH OPTION SCOPE(Gebot)
)
REF IS auktionOID SYSTEM GENERATED
;

CREATE TABLE Gebot OF GebotTyp AS (
    bieter WITH OPTION SCOPE(Bieter),
    artikel WITH OPTION SCOPE(Artikel),
    artikelpaket WITH OPTION SCOPE(ArtikelPaket),
    auktion WITH OPTION SCOPE(Auktion)
)
REF IS gebotOID SYSTEM GENERATED
;

CREATE TABLE Bieter OF BieterTyp AS (
)
REF IS bieterOID SYSTEM GENERATED
;
