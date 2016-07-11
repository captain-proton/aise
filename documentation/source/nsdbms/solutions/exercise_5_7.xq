let $doc := doc("exercise_4_sample.xml")
let $kg := $doc//kindergarten[name='Eyrie']/kindergartenID
let $parents := $doc//child[
    following-sibling::*/kindergartenIDREF = $kg 
    or preceding-sibling::*/kindergartenIDREF = $kg]
  /ancestor::family//parent[fn:year-from-date(dateOfBirth) = 1987]
return $parents