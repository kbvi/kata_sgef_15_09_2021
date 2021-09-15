Ce kata à l'adresse https://kata-log.rocks/string-calculator-kata a été implémenté ici avec:
- un jdk 8 comme langage et librairie principale
- maven 3.3.9 comme moteur de dépendance et de production
- des dépendances tierces orientées test comme junit et junit quickcheck

Par ce kata incrémental, j'ai compris que c'était incontournable de bien appliquer le TDD *step* par *step*. Celles ci sont toutes répertoriées par classe de test et chaque partie de l'énoncé n'a pas été laissé au hasard et reprise dans les tests les plus atomiques pour pouvoir coller au mieux au besoin.

Les tests font foi du travail réussi et leur execution déclenche le rapport suivant à la racine de ce projet sur `Test Results - AddAbleImplTest.html`.

Avec le recul et plus de temps, j'aurais pu mieux factoriser mon code en fonctions. Pour les mêmes raison, j'aurais pu proposer pour l'étape 2 une interprétation plus PBT (inspirée par le "unknown amount of numbers") avec junit-quickcheck-generators que j'ai tenté de mettre en oeuvre de manière inédite.

Devant certaines contraintes liés à junit-quickcheck, j'ai exploré les étapes supérieures à l'étape 5, ce qui est facilité par mon experience récurrente avec les regex.

Mais j'ai conscience que l'important ici, comme bien souvent est de se contenter d'un MVP, et que ce contentement permet de mieux envisager les améliorations techniques par les problématiques affrontées.
