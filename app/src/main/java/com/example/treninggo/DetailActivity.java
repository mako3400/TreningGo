package com.example.treninggo;

import android.app.Activity;
import android.os.Bundle;

public class DetailActivity extends Activity {
    public static final String EXTRA_WORKOUT_ID ="id";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        //Pobieramy referencje do fragmentu
        WorkoutDetailFragment workoutDetailFragment =(WorkoutDetailFragment)
                getFragmentManager().findFragmentById(R.id.detail_frag);
        //Pobieramy z intencji identyfikator treningu klikniet przez użytkownika
        int workoutId = (int) getIntent().getExtras().get(EXTRA_WORKOUT_ID);
        //Przekazujemy do fragmenu identyfikator treningu
        workoutDetailFragment.setWorkout(workoutId);
    }
}
