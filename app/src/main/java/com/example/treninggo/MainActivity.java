package com.example.treninggo;


        import android.app.Activity;
        import android.app.FragmentTransaction;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;


//Zaimportowanie interfejsu WorkoutLiostListener
public class MainActivity extends Activity
        implements WorkoutListFragment.WorkoutListListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void itemClicked(long id) {

        View fragmentContainer = findViewById(R.id.fragment_container);
        //Pobranie referencji do układu typu FrameLayout.Uruchomienie na dużym ekranie
        if (fragmentContainer != null) {
            WorkoutDetailFragment workoutDetailFragment = new WorkoutDetailFragment();
            //To wywołanie rozpoczyna transakcje fragmentu
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            workoutDetailFragment.setWorkout(id);
            //Zastepujemy fragment i dodajemy go do stosu cofnięć
            fragmentTransaction.replace(R.id.fragment_container, workoutDetailFragment);
            fragmentTransaction.addToBackStack(null);
            //Wygaszamy stary i wyświetlamy nowy fragment
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            fragmentTransaction.commit(); //Zatwierdzamy transakcje


        } else {
            //uruchomienie na małym ekranie.
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_WORKOUT_ID, (int) id);
            startActivity(intent);
        }
    }
}