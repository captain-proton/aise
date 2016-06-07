-- base on the model of exercise 2

-- 1. Return the name and the OID of all children between 5 and 7 years of age
SELECT name.firstName, name.lastName, childrenOID
FROM Child
WHERE age() >= 5 and age() <= 7;


-- 2. Return the address of all parents who have a hobby whose description contains the word “goal”.
SELECT DISTINCT address
FROM Parent as p, UNNEST (p.addresses) as address
WHERE p->hobbies.description LIKE '%goal%'
-- WHERE DEREF(p.hobbies).description LIKE '%goal%'



-- 3. Return all children who have at least one brother or sister who goes to a kindergarten
SELECT c
FROM Child c
WHERE c.siblings->educationalInstitution->educationalInstitutionOID IS OF (KindergartenType);
