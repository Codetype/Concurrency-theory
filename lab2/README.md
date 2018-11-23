pakiet semaphoresTasks:

1.
Semafor binarny zaimplementowany w klasie Semafor, licznik zaimplementowany w klasie Counter, wyścig
zaimplementowany w klasie Race.


2. 
Jeśli w klasie Semafor zamienię pętle while na warunek if, to przy odpowiednio dużej ilości iteracji w klasie
Race otrzymuję błędny wynik końcowy. Dzieję się tak, gdyż jeśli użyjemy if'a, nie pomoże to rozwiązać problemu
powstającego przy wywołaniu notify, gdyż wartość może być błędna, przez to, że inny wątek ją zmienił.

3.
Implementacja semafora zliczającego znajduję się w klasie CountingSemafor.
Na pytanie: "Czy semafor binarny jest szczególnym przypadkiem semafora ogólnego?", odpowiedź brzmi tak.
Jeśli zredukujemy odpowiednio liczbę wątków to semafor zliczający staję się semaforem binarnym, a więc jest
szczególnym przypadkiem semafora ogólnego.


pakiet philosophers:

4.
Naszym Mainem jest klasa DiningRoom,
1) Rozwiązanie naiwne: DeadlockSolution
2) Rozwiązanie z kelnerem: WaiterSolution
3) Rozwiązanie "zachłanne": FastSolution
4) Rozwiązanie z hierarchią zasobów: HierarchySolution

5.
ad. 1)
Rozwiązanie to z pewnością doprowadzi przy odpowiednich warunkach do zakleszczenia.

ad. 2)
To rozwiązanie przypomina znanego z systemów operacyjnych centranego zarządce zasobów.
Kelner obsługując filozofów wręcza przed posiłkiem (i zabiera po skończonym jedzeniu) sztućce parami, co
ostatecznie zapobiega zakleszczeniom.

ad. 3)
Filozof, gdy przyjdzie jego kolej, próbuję najpierw wziąć tak jakby lewy widelec, a potem drugi. W tym algorytmie zachłannym może powstać zakleszczenie, ale losowanie czasu do sleepa, ma rozwiązać ten problem.

ad. 4)
Natomiast w tym rozwiązaniu narzucamy od razu z góry hierarchię zasobów, co ma również zapobiec ewentualnym zakleszczeniom.