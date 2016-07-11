let $doc := doc("exercise_4_sample.xml")
let $christchurch := $doc//address[city = 'Winterfell']
for $person in $doc//family/*[name()='parents' or name()='children']
    /*[contains(addressIDREFS, $christchurch/addressID)]
return 
  <person>
    <meta>{$person/*[name()!='addressIDREFS']}</meta>
    <address>{$christchurch}</address>
  </person>