let $doc := doc("exercise_4_sample.xml")
let $hobbyIDs := $doc/humanity/hobbies/hobby[contains(description, 'goal')]/hobbyID
let $parents := $doc//parent[contains($hobbyIDs, hobbies/hobby/hobbyIDREF)]
let $addressIDs := distinct-values($parents/addressIDREFS)
for $addressID in $addressIDs
return $doc/humanity/addresses/address[addressID = $addressID]