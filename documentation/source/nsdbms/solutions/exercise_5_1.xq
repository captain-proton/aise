import module namespace functx = 'http://www.functx.com';

for $child in doc("exercise_4_sample.xml")//children/child
let $now := fn:current-date()
let $age := fn:year-from-date($now) - fn:year-from-date($child/dateOfBirth)
where functx:between-inclusive($age, 5, 7)
return $child
