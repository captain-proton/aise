(:
Axes in XPath:
+--------------------+-----------------------------------------------------+-------------------------+
| Name               | Result                                              | Sample                  |
+====================+=====================================================+=========================+
| ancestor           | All parent nodes of the current node                | parent/ancestor::family |
+--------------------+-----------------------------------------------------+-------------------------+
| ancestor-or-self   | As ``ancestor`` incl. the current                   |                         |
+--------------------+-----------------------------------------------------+-------------------------+
| child              | All chilren (normally directly used)                | bicycle/wheel           |
|                    |                                                     | bicycle/child::wheel    |
+--------------------+-----------------------------------------------------+-------------------------+
| descendant         | All sub nodes of current node                       | descendant::part        |
+--------------------+-----------------------------------------------------+-------------------------+
| descendant-or-self | As ``descendant`` incl. current node                |                         |
+--------------------+-----------------------------------------------------+-------------------------+
| following          | All nodes after the closing tag of the current node | following::bicycle      |
+--------------------+-----------------------------------------------------+-------------------------+
| following-sibling  | As ``following`` but with the same parent           |                         |
+--------------------+-----------------------------------------------------+-------------------------+
| preceding          | All nodes that are closed before the current node   | preceding::bicycle      |
+--------------------+-----------------------------------------------------+-------------------------+
| preceding-sibling  | As ``preceding`` but with the same parent           |                         |
+--------------------+-----------------------------------------------------+-------------------------+
:)

(: sql like expression :)
for $book in doc("books.xml")/bookstore/book
where $book/price > 30
return $book/title;
(: result: <title>some title</title>... :)

(: equivalent, more xml xpath style :)
for $book in doc("books.xml")/bookstore/book[price > 30]
return $book/title;

(: contains function :)
for $book in doc("books.xml")/bookstore/book
where contains($book/title, "Spiel")
return $book;

(: define the values of that each top level node :)
let $values := doc("tree.xml")/tree/node/value
(: for each value inside that node :)
for $value in $values
(: that has a text length greater than zero :)
where string-length($value/text()) > 0
(: order by its id :)
order by $value/@id
(: and return the value :)
return $value;

(: define simple tuple in let clauses :)
let $values := (1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
for $value in $values
where $value / 2 = 0
(: when returning text/xml be sure to enclose values with {} :)
return <value>{$value}</value>;

(:
Important functions:
+--------------------------+---------------------------+-------------------------+
| Name (fn:<func>)         | Description               | Call                    |
+==========================+===========================+=========================+
| avg                      | average                   | avg(set)                |
+--------------------------+---------------------------+-------------------------+
| min                      | minimum                   | min(set)                |
+--------------------------+---------------------------+-------------------------+
| max                      | maximum                   | max(set)                |
+--------------------------+---------------------------+-------------------------+
| sum                      | sum                       | sum(set)                |
+--------------------------+---------------------------+-------------------------+
| concat                   | concat of (nodes) text    | concat(set)             |
+--------------------------+---------------------------+-------------------------+
| substring                | substring of (nodes) text | substring(set)          |
+--------------------------+---------------------------+-------------------------+
| count                    | count of elements         | count(set)              |
+--------------------------+---------------------------+-------------------------+
| exists                   | exists an element         | exists(set, value)      |
+--------------------------+---------------------------+-------------------------+
| exactly-one              |                           |                         |
+--------------------------+---------------------------+-------------------------+
| zero                     |                           |                         |
+--------------------------+---------------------------+-------------------------+
| distinct-values          |                           | distinct-values(set)    |
+--------------------------+---------------------------+-------------------------+
| current-date             | current date without time | current-date()          |
+--------------------------+---------------------------+-------------------------+
| year-from-date           |                           | year-from-date(date)    |
+--------------------------+---------------------------+-------------------------+
| functx:between-inclusive |                           | between-inclusive(1, 5) |
+--------------------------+---------------------------+-------------------------+
| functx:distinct-nodes    |                           | distinct-nodes(set)     |
+--------------------------+---------------------------+-------------------------+

:)
