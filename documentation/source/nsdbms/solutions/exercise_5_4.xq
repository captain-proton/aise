let $doc := doc("exercise_4_sample.xml")
let $year := fn:year-from-date(fn:current-date())
for $parent in $doc//parent
let $yearOfBirth := fn:year-from-date($parent/dateOfBirth)
return 
  <parent>
    <firstname>{$parent/firstname/text()}</firstname>
    <lastname>{$parent/lastname/text()}</lastname>
    <age>{$year - $yearOfBirth}</age>
  </parent>
  