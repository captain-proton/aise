CREATE TYPE AbteilungTyp AS (
    nummer INTEGER,
    name VARCHAR(200) NOT NULL,

    chef REF(MitarbeiterTyp),
    vertreter REF(MitarbeiterTyp),
    chefsekretaerin REF(MitarbeiterTyp),
    sekretaerinnen REF(MitarbeiterTyp) ARRAY[5],
    mitarbeiter REF(MitarbeiterTyp) MULTISET,

    projekte REF(ProjektAbteilungTyp) MULTISET,
    koennen_projekte_ausfuehren BOOLEAN,

    dienstwagen_a REF(DienstwagenTyp),
    dienstwagen_b REF(DienstwagenTyp),

    gebaeude REF(GebaeudeTyp) MULTISET,
)
REF USING INTEGER
METHOD gib_ausfuehrende_projekte RETURNS ProjektTyp,
METHOD gib_bearbeitende_projekte RETURNS ProjektTyp,
METHOD gib_interne_projekte RETURNS ProjektTyp,
METHOD gib_uebergreidende_projekte RETURNS ProjektTyp,
;

CREATE TYPE ProjektAbteilungTyp AS (

    projekt REF(ProjektTyp),
    abteilung REF(AbteilungTyp),
    art INTEGER
)
REF IS SYSTEM GENERATED;
