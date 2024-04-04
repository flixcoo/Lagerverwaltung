# Lagerverwaltung

Dies ist mein Prüfungsprojekt für das Modul Anwendungsorientierte Programmierung an der Hochschule für Technik, Wirtschaft und Kultur (HTWK) Leipzig.
Es stellt eine Lagerverwaltung dar, mit welcher auf die verschiedenen Fächer zugegriffen werden kann, Artikel entfernt oder hinzugefügt werden können bzw. alle Artikel des Lager angezeigt werden können.

## Lager

Das Lager besteht aus insgesamt 8 Regalen `A - G`. Diese Regale wiederrum bestehen aus 10x10 Regalfächern mit x- und y-Koordinate [`0-9`,`0-9`]. Jedes Regalfach hat eine Kapazität von `8 Grundeinheiten`. Wenn ein Item die Größe dieser 8 GE überschreiten, kann es nicht hinzugefügt werden. 

## Item

Ein `Item` (Artikel) hat die `partNumber`- eine Nummer die den Artikel eindeutig identifiziert, `articleName` - eine Bezeichnung für diesen Artikel,`size` - eine Größenbeschreibung für einen Artikel oder ein Artikelvorkommen und `amount` - die Anzahl in welcher der Artikel vorhanden ist.

Des Weiteren besitzt ein Artikel eine `shelf`-, `xCoordinate`- und `yCoordinate`-Variable, in welcher das Regal und das Fach angegeben wird, in welchem sich der Artikel befindet. Das Regal wird durch den Regalbuchstaben `A-G` und das Fach durch die x- und y-Koordinate des Faches mit den Zahlen `0-9` angegeben.

## Prozesse

### Item abfragen

Ein Artikel kann abgefragt werden, indem ein Regal und die Regalfach-Koordinaten angegeben werden. Sollte es einen Artikel in dem Regalfach geben, so wird dieser eingeblendet. Ansonsten erscheint ein Pop-Up Fenster, welches den Benutzer auf das leere Fach hinweist.

//ToDo
