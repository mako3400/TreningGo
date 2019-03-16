package com.example.treninggo;


import android.app.Activity;
import android.os.Bundle;
import android.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class WorkoutListFragment extends ListFragment {
     //Dodaj do fragmentu interfejs obiektu nasłuchujacego i sam obiekt

     interface WorkoutListListener{
        void itemClicked(long id);

     };
      private WorkoutListListener listener;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Tworzymy tablicę łańcuchów znaków z nazwami treningów
        String[] names = new String[Workout.workouts.length];
        for (int i =0; i < names.length; i++){
            names[i]= Workout.workouts[i].getName();
        }
        //Tworzymy adapter ArrayAdapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
             inflater.getContext(),android.R.layout.simple_list_item_1,names);
        //Przekazujemy adapter do widoku listy
        setListAdapter(adapter);
        return super.onCreateView(inflater, container,savedInstanceState);
    }
     //Metoda wywoływana w momencie dołączenia fragmentu do aktywności
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.listener =(WorkoutListListener)activity;
    }
    //Metoda przekazuje objektowi nasłuchującemu informację o kliknięciu
    //jednego z elementów ListView
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        if (listener !=null){
            listener.itemClicked(id);
        }
    }
}
