package com.example.treninggo;

//Kazdy trenning - obiekt Workout -zawiera nazwe i opis
public class Workout {
    private String name;
    private  String description;

    //workouts to tablica czterech obiektów Workout
    public  static  final Workout[] workouts = {
            new Workout("Rozciąganie mieśni i stawów",
                    "5 pompek w staniu na rękach, \n10 przysiadów na jednej nodze, \n15 podciągnieć na drążku."),
            new Workout("Trenning power",
                    "100 podciągnięć, \n100 brzuszków, \n100 przysiadów."),
            new Workout("Dla mieczaków",
                    "5 podciągnięć, \n10 pompek, \n15 przysiadów."),
            new Workout("Siła i dystans",
                    "Bieg na 500 metrów, \n21 wydżwignięć  ciężarka, \n21podciągnięć.")
    };
  //Każdy trening (Workout) zawiera nazwe i opis - Konstruktor
    public Workout(String name, String description) {
        this.name = name;
        this.description = description;
    }
    // Metody get, pobierające prywatne dane obiektów
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

   //Łańcuchowa reprezentująca obiektu Workout jest jego nazwa
    public String toString() {
        return this.name;
    }
}
