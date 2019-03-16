package com.example.treninggo;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
//Fragment musi implementować interfejs ViewOnClickListener
//To zmienia fragment w obiekt mogący obsługiwać kliknięcie
public class StopwatchFragment extends Fragment implements View.OnClickListener{


    public StopwatchFragment() {
        // Required empty public constructor
    }
    //Liczba sekund wyświetlona przez stoper
    private int seconds = 0;
    //Czy stoper działa
    //Zmienna running informuje czy stoper aklualnie działa czy nie
    private boolean running;
    //Zmienna wasRunning informuje czy przed wstrzymaniem aktywności stoper działał czy nie
    private boolean wasRunning;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Odtwarzanie wartości zmiennej zapisanej
        // w parametrze saveedInstanteState typu Bundle
        if (savedInstanceState != null ){
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
            if (wasRunning){
                running = true;
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Metoda określa układ fragmentui wywołuje metodę runTimer(),
        // przekazując do niej obiekt układu
        View layout = inflater.inflate(R.layout.fragment_stopwatch, container, false);
        runTimer(layout);
        //Popbieramy referencję do przycisku
        Button startButton = (Button)layout.findViewById(R.id.start_button);
        //Dołączamy do przycisku  obiekt nascłuchujący
        startButton.setOnClickListener(this);
        Button stopButton = (Button)layout.findViewById(R.id.stop_button);
        stopButton.setOnClickListener(this);
        Button resetButton = (Button)layout.findViewById(R.id.reset_button);
        resetButton.setOnClickListener(this);

        return layout;
    }
   //Widok kliknięty przez użytkownika
    @Override
    public void onClick(View v) {
        switch (v.getId()){ //Sprawdzamy który widok został kliknięty
            case R.id.start_button:
            onClickStart(v);
            break;
            case R.id.stop_button:
                onClickStop(v);
                break;
            case R.id.reset_button:
                onClickReset(v);
                break;
        }
    }

    //Jeśli działanie fragmentu zostało wstrzymane,
     // zapisujemy  czy stoper był włączony po czym go zatrzymujemy
    @Override
    public void onPause() {
        super.onPause();
        wasRunning =running;
        running = false;

    }
    //Jeśli stoper działa, zanim został wstrzymany,
    //to tutaj go ponownie uruchamiamy
    @Override
    public void onResume() {
        super.onResume();
        if (wasRunning){
            running = true;
        }
    }
    //Metoda zapisuje wartości zmiennych w obiekcie Bundle przed usunięciem aktywności.
    //Sa one używane w przypadku gdy użutkownik zmieni orientacje urządzenia
    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
        savedInstanceState.putBoolean("wasRunning",wasRunning);
    }
    //Metoda wykonana kiedy użytkownik kliknie przycisk Start
    public  void onClickStart(View view){
        running = true;

    }
    //Metoda zatrzymania stopera
    public void onClickStop(View view){
        running = false;
    }
    //Metoda wykonan po kliknięciu Kasuj
    public void onClickReset(View view){
        running = false;
        seconds = 0;
    }

    private  void runTimer(View view){
        final TextView timerView = (TextView) view.findViewById(R.id.time_view);
       //Umieszczenie kodu w obiekcie Handler oznacza że będzie on mógł być
        //wykonany w wątku działającym w tle
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;
                String time = String.format("%d:%02d:%02d",hours,minutes,secs);
                //Wyświetlamy liczbę sekund zmierzonych przez stoper
                timerView.setText(time);
                if (running){
                    seconds ++; //Inkrementuje liczbę zmierzonych sekund
                }
                handler.postDelayed(this,1000);//Wykonywany co sekunde
            }
        });
    }


}
