let $doc := doc("exercise_4_sample.xml")
let $families := $doc//family
for $family in $families
let $children := $family//child[firstname = (ancestor::family)//parent/firstname]
for $child in $children
return 
  <child>
    <firstname>{$child/firstname/text()}</firstname>
    <lastname>{$child/lastname/text()}</lastname>
  </child>