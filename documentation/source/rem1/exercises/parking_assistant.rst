Parking Assistant - Requirements
================================

+--------+--------------------------------------------------------------------------------+------------+
| Nummer | Beschreibung                                                                   | Typ        |
+========+================================================================================+============+
| 1      | Das System soll jeweils vier Ultraschallsensoren in der Heck-                  | Functional |
|        | und Frontstoßstange besitzen.                                                  |            |
+--------+--------------------------------------------------------------------------------+------------+
| 1.1    | Bei Zündung des Fahrzeugs wird eine Systemprüfung, in der die                  | Functional |
|        | angebundenen Sensoren geprüft werden, durchgeführt.                            |            |
+--------+--------------------------------------------------------------------------------+------------+
| 1.2    | Der Erfassungsbereich von Hindernissen liegt sowohl in der                     | Functional |
|        | Horizontale (120°), als auch in der Vertikale (45°) der Sensorik.              |            |
+--------+--------------------------------------------------------------------------------+------------+
| 1.3    | Das System verwendet für jeden Sensor unterschiedliche                         | Functional |
|        | Ultraschallfrequenzen.                                                         |            |
+--------+--------------------------------------------------------------------------------+------------+
| 1.4    | Die Sensoren des Systems dürfen nicht über im freien                           | Constraint |
|        | Handel verfügbare Werkzeuge entnommen werden.                                  |            |
+--------+--------------------------------------------------------------------------------+------------+
| 1.5    | Die Sensoren sollen bei einer Umgebungstemperatur                              | Constraint |
|        | von -25°C bis 50°C in Ihrer Funktion nicht beeinträchtigt werden.              |            |
+--------+--------------------------------------------------------------------------------+------------+
| 1.6    | Das System ist so modular aufgebaut, dass einzelne defekte                     | Quality    |
|        | Sensoren ausgetauscht werden können, ohne das ganze                            |            |
|        | System austauschen zu müssen.                                                  |            |
+--------+--------------------------------------------------------------------------------+------------+
| 2      | Das System verfügt über eine zentrale Steuereinheit, welche                    | Functional |
|        | alle eingehenden und ausgehenden Signale verarbeitet.                          |            |
+--------+--------------------------------------------------------------------------------+------------+
| 2.1    | Das System soll vom Nutzer an- und ausgeschaltet werden können.                | Functional |
+--------+--------------------------------------------------------------------------------+------------+
| 2.2    | Das System soll bei Einlegen des Rückwärtsgangs und ersten Gangs eine          | Functional |
|        | Fehlerdiagnose durchführen und bei Bedarf eine Fehlermeldung an den            |            |
|        | Nutzer ausgegeben und in das Systemlog eintragen.                              |            |
+--------+--------------------------------------------------------------------------------+------------+
| 2.3    | Das Log des Systems muss über eine integrierte USB-Schnittstelle               | Quality    |
|        | abgerufen werden können.                                                       |            |
+--------+--------------------------------------------------------------------------------+------------+
| 2.4    | Das System soll bei jeglichen Schäden oder Defekten an den Sensoren,           | Functional |
|        | die durch die Fehleranalyse festgestellt wurden, dies dem Fahrer               |            |
|        | über den Bordcomputer mitteilen.                                               |            |
+--------+--------------------------------------------------------------------------------+------------+
| 2.5    | Das System soll dem Benutzer, durch akustische Signale in Form von             | Functional |
|        | gleichbleibenden Tönen in unterschiedlich starker Lautstärke,                  |            |
|        | Hindernisse mitteilen.                                                         |            |
+--------+--------------------------------------------------------------------------------+------------+
| 2.6    | Das System bietet die Möglichkeit 3 Lautstärkestufen des Signaltons zu wählen. | Functional |
+--------+--------------------------------------------------------------------------------+------------+
| 2.7    | Das System soll über jeweils sieben zweifarbige LEDs verfügen, die den         | Functional |
|        | Abstand links und rechts zu Hindernissen anzeigen.                             |            |
+--------+--------------------------------------------------------------------------------+------------+
| 2.7.1  | Diese LEDs werden proportional zum Abstand zu einem Hindernis                  | Functional |
|        | vom System eingeschaltet. Die ersten fünf leuchten gelb,                       |            |
|        | die letzten beiden rot.                                                        |            |
+--------+--------------------------------------------------------------------------------+------------+
| 2.7.2  | Das System lässt mehr LEDs leuchten, je näher das Hindernis                    | Functional |
|        | ist (alle 20 cm Abstand weniger leuchtet eine weitere Lampe auf,               |            |
|        | angefangen bei der ersten gelben).                                             |            |
+--------+--------------------------------------------------------------------------------+------------+
| 2.8    | Die optische und akustische Signaleinstellung muss konfigurierbar sein.        | Functional |
+--------+--------------------------------------------------------------------------------+------------+
| 2.9    | Hindernisse müssen durch das System erkannt und dem Nutzer akustisch           | Functional |
|        | gemeldet und visuell dargestellt werden.                                       |            |
+--------+--------------------------------------------------------------------------------+------------+
| 2.10   | Das System gibt nach Einlegen des ersten Gangs und Erkennen eines              | Functional |
|        | Hindernisses am Bug des Fahrzeugs einen Signalton von 420 Hz aus               |            |
+--------+--------------------------------------------------------------------------------+------------+
| 2.11   | Das System gibt beim Einlegen des Rückwärtsgangs und Erkennen                  | Functional |
|        | eines Hindernisses am Heck des Fahrzeugs einen Signalton von 347 Hz aus        |            |
+--------+--------------------------------------------------------------------------------+------------+
| 2.12   | Das System gibt einen Signalton zwei mal in der Sekunde aus,                   | Functional |
|        | wenn ein erkanntes Hindernis noch 1,40 m entfernt ist                          |            |
|        | (maximale Reichweite)                                                          |            |
+--------+--------------------------------------------------------------------------------+------------+
| 2.13   | Der Abstand in dem das System ein Signal sendet beträgt bei                    | Functional |
|        | einem Abstand von 1,20 m 1Hz, bei einem Abstand von 31 cm 20 Hz,               |            |
|        | linear steigend in den Abständen                                               |            |
+--------+--------------------------------------------------------------------------------+------------+
| 2.14   | Das System gibt einen Dauerwarnton aus, wenn das Hindernis 30 cm               | Functional |
|        | oder näher ist                                                                 |            |
+--------+--------------------------------------------------------------------------------+------------+
| 2.15   | Das System soll bei erfolgreichem Start des Einparkvorgangs ein                | Functional |
|        | Signal zum Multimediasystem senden zur Reduktion der Lautstärke                |            |
|        | auf 10% der maximalen Lautstärke.                                              |            |
+--------+--------------------------------------------------------------------------------+------------+
| 2.16   | Das System soll sich bei einer Geschwindigkeit von > 10km/h                    | Functional |
|        | automatisch abschalten.                                                        |            |
+--------+--------------------------------------------------------------------------------+------------+
| 2.17   | Beim Schalten in den ersten Gang und einer Geschwindigkeit                     | Functional |
|        | von <= 10 km/h aktiviert das System die Sensoren in der                        |            |
|        | Frontstoßstange des Fahrzeugs                                                  |            |
+--------+--------------------------------------------------------------------------------+------------+
| 2.18   | Beim Schalten in den Rückwärtsgang aktiviert das System die                    | Functional |
|        | Sensoren in der Heckstoßstange des Fahrzeugs.                                  |            |
+--------+--------------------------------------------------------------------------------+------------+
| 2.19   | Das System gibt den Signalton des näheren Hindernisses aus,                    | Functional |
|        | wenn sowohl vorne als auch hinten ein Hindernis erkannt wird                   |            |
+--------+--------------------------------------------------------------------------------+------------+
| 2.20   | Das System soll auf Veränderungen innerhalb von 10ms reagieren.                | Quality    |
+--------+--------------------------------------------------------------------------------+------------+
| 2.21   | Das System soll Fahrzeug- bzw. Markenunabhängig einsetzbar sein.               | Quality    |
+--------+--------------------------------------------------------------------------------+------------+
| 2.22   | Das System soll 0.8 Sekunden nach der Zündung funktionsfähig sein.             | Quality    |
+--------+--------------------------------------------------------------------------------+------------+
| 2.23   | Das Steuergerät des Systems muss über einen CAN bus                            | Quality    |
|        | angesprochen werden können                                                     |            |
+--------+--------------------------------------------------------------------------------+------------+
| 2.24   | Das System ist so modular aufgebaut, dass das Steuergerät                      | Quality    |
|        | ausgetauscht werden kann, ohne das ganze System                                |            |
|        | austauschen zu müssen.                                                         |            |
+--------+--------------------------------------------------------------------------------+------------+
| 2.25   | Das Steuergerät des Systems soll unabhängig von                                | Quality    |
|        | einem bestimmten Typ Sensoren sein.                                            |            |
+--------+--------------------------------------------------------------------------------+------------+
| 2.26   | Die Software des Steuergerätes muss ersetzbar sein.                            | Quality    |
+--------+--------------------------------------------------------------------------------+------------+
| 2.27   | Das System soll eine Schnittstelle für Erweiterungen zur                       | Quality    |
|        | Verfügung stellen.                                                             |            |
+--------+--------------------------------------------------------------------------------+------------+
| 2.28   | Das Steuergerät soll bei einer Umgebungstemperatur von                         | Constraint |
|        | -25°C bis 50°C in seiner Funktion nicht beeinträchtigt werden.                 |            |
+--------+--------------------------------------------------------------------------------+------------+
| 2.29   | Das System soll durch die elektrische Anlage des Fahrzeugs                     | Constraint |
|        | mit Energie (12V, max 1A) versorgt werden.                                     |            |
+--------+--------------------------------------------------------------------------------+------------+
| 3      | Das System sollte unter einer agilen Vorgehensweise nach                       | Constraint |
|        | Scrum entwickelt werden                                                        |            |
+--------+--------------------------------------------------------------------------------+------------+
| 4      | Die Benutzung des Systems ist auf eigene Gefahr. Für jegliche Schäden,         | Constraint |
|        | die während der Nutzung des Systems durch den Benutzer entstanden              |            |
|        | sind, haftet der Benutzer selbst.                                              |            |
+--------+--------------------------------------------------------------------------------+------------+
| 5      | Das System soll durch Wetterverhältnisse nicht beeinflusst werden.             | Constraint |
+--------+--------------------------------------------------------------------------------+------------+

