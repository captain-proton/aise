import module namespace functx = 'http://www.functx.com';

let $doc := doc("exercise_4_sample.xml")
let $childrenA := $doc//child[kindergartenIDREF]/following-sibling::*
let $childrenB := $doc//child[kindergartenIDREF]/preceding-sibling::*
return functx:distinct-nodes(($childrenA, $childrenB))