package com.example.intesavincente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.intesavincente.model.Partita;
import com.example.intesavincente.model.WordsResponse;
import com.example.intesavincente.repository.partita.PartitaRepository;
import com.example.intesavincente.repository.partita.PartitaResponse;
import com.example.intesavincente.repository.words.IWordsRepository;
import com.example.intesavincente.repository.words.WordsRepository;
import com.example.intesavincente.utils.ResponseCallback;

import java.util.ArrayList;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Response;

public class Indovinatore extends AppCompatActivity implements ResponseCallback, PartitaResponse {

    private String parola;
    private IWordsRepository mIWordsRepository;
    private PartitaRepository mPartitaRepository;
    public Partita partita=new Partita();

    private static final long START_TIME_IN_MILLIS = 600000;
    private TextView timer;
    private Button buzz;
    private Button passo;
    private CountDownTimer countDownTimer;
    private boolean timerRunning;
    private long timeLeftMillis = START_TIME_IN_MILLIS;
    public ArrayList<Partita> p= new ArrayList<>();
    private int npasso;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indovinatore);
        mPartitaRepository = new PartitaRepository(this.getApplication(), this);
        mPartitaRepository.trovaPartita();

        System.out.println("partita123"+ partita.toString());
        System.out.println("partita1234"+ p.toString());
        mIWordsRepository = new WordsRepository(this.getApplication(), this);
        TextView parolaDaIndovinare = findViewById(R.id.parolaDaIndovinare);
        buzz = findViewById(R.id.buzz);
        timer = findViewById(R.id.timer);
        buzz.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View v) {
                //partita= new Partita();
                if(timerRunning) {
                    pauseTimer();
                    Intent i = new Intent(Indovinatore.this, InserisciParola.class);
                    startActivity(i);
                } else {
                    startTimer();
                    mIWordsRepository.fetchWords();
                   // parolaDaIndovinare.setText();
                }

                //parolaDaIndovinare.setText();
            }
        });

        passo = findViewById(R.id.button_passo);
        passo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(timerRunning) {
                    pauseTimer();
                    npasso = partita.getPasso();
                    partita.setPasso(npasso--);
                }
            }
        });

    }

    public Indovinatore(){}

    @Override
    public void onResponse(String parola) {
        System.out.println("par223 "+parola);
        TextView parolaDaIndovinare = findViewById(R.id.parolaDaIndovinare);
        parolaDaIndovinare.setText(parola);

    }

    @Override
    public void onFailure(String errorMessage) {

    }

    public void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftMillis, 1000) {
            @Override
            public void onTick(long l) {
                timeLeftMillis = l;
                updateCountDowText();
            }

            @Override
            public void onFinish() {
                timerRunning = false;
            }
        }.start();
        timerRunning = true;
    }

    public void pauseTimer() {
        countDownTimer.cancel();
        timerRunning = false;
    }

    public void updateCountDowText() {
        int seconds = (int) (timeLeftMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d", seconds);

        timer.setText(timeLeftFormatted);

        if(timeLeftFormatted.equals("00")){
            pauseTimer();
            System.out.println("PARTITA FINITA");
        }
    }

    @Override
    public Partita onDataFound(Partita partitaTrue) {
        System.out.println("par1221"+partitaTrue);
        this.partita=partitaTrue;
        p.add(partitaTrue);
        System.out.println("par12"+p.toString());
        return partita;
    }
}
