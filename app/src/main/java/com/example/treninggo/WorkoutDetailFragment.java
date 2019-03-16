package com.example.treninggo;


import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class WorkoutDetailFragment extends Fragment {

    //Identyfikator treningu wybranego przez użytkownika
    private long workoutId;


    public WorkoutDetailFragment() {
        // Required empty public constructor
    }

    //Metoda onCreateView. System wywołuje ją gdy potrzebuje układu fragmentu
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Ustawiamy wartość zmiennej workoutId
        if (savedInstanceState != null){
            workoutId = savedInstanceState.getLong("workoutId");
        }else {
            //Używamy transakcji, by dodać fragment stopera do układu FrameLayout
            FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
            StopwatchFragment stopwatchFragment = new StopwatchFragment();
            fragmentTransaction.replace(R.id.stopwatch_container, stopwatchFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            fragmentTransaction.commit();
        }
        // Wywołanie informuje system którego układu używa ten fragment
        return inflater.inflate(R.layout.fragment_workout_detail, container, false);


    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        if (view!= null){
            TextView title =(TextView) view.findViewById(R.id.textTitle);
            Workout workout = Workout.workouts[(int) workoutId];
            title.setText(workout.getName());
            TextView description = (TextView) view.findViewById(R.id.textDescription);
            description.setText(workout.getDescription());
        }
    }
    //Przed usunięciem fragmentu zapisujemy watośc zmiennej workoutId
    //w obiekcie saveInstanceState typu Bundle.
    //Wartość tą odtworzymy w metodzie onCreateview().


    @Override
    public void onSaveInstanceState(Bundle saveInstanceState) {
        saveInstanceState.putLong("workoutId", workoutId);
    }

    //Metoda do ustawienia identyfikatora treningu
    //Aktywność będzie jej używac do określenia  identyfikatora treningu
    public void setWorkout(long id){
        this.workoutId = id;
    }

}
