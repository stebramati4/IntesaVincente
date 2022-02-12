package com.example.intesavincente;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.intesavincente.model.Partita;
import com.example.intesavincente.model.Utente;
import com.example.intesavincente.repository.partita.PartitaRepository;
import com.example.intesavincente.repository.partita.PartitaResponse;
import com.example.intesavincente.repository.words.IWordsRepository;
import com.example.intesavincente.repository.words.WordsRepository;
import com.example.intesavincente.utils.Constants;
import com.example.intesavincente.utils.ResponseCallback;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Indovinatore extends AppCompatActivity implements PartitaResponse, ResponseCallback, IndovinatoreRepository.Listener {


    //private final Application mApplication;
    private String parola;
    private IWordsRepository mIWordsRepository;
    private PartitaRepository mPartitaRepository;
    private ResponseCallback mResponseCallback;
    private IndovinatoreResponse mIndovinatoreResponse;
    private IIndovinatore mIIndovinatoreRepository;



    private static final long START_TIME_IN_MILLIS = 600000;
    private TextView timer;
    private Button buzz;
    private Button passo;
    private CountDownTimer countDownTimer;
    private boolean timerRunning;
    private long timeLeftMillis = START_TIME_IN_MILLIS;
    //public ArrayList<Partita> p= new ArrayList<>();
    private int npasso;
    //private IndovinatoreRepository mIndovinatoreRepository=new IndovinatoreRepository((Application) MyApplication.getAppContext(), mIndovinatoreResponse);
    private IndovinatoreRepository mIndovinatoreRepository;
            //=new IndovinatoreRepository((Application) MyApplication.getAppContext(),this.mIndovinatoreResponse);
    private TextView parolaDaIndovinare;
    String TAG ="Indovinatore" ;
    private SharedPreferences pref = MyApplication.getAppContext().getSharedPreferences("MyPref", MODE_PRIVATE);
    private SharedPreferences.Editor editor = pref.edit();

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    private String parolaParola;
    /*public Indovinatore(Application mApplication, IndovinatoreResponse indovinatoreResponse) {
        this.mApplication = mApplication;
        this.mIndovinatoreResponse = indovinatoreResponse;
    }
*/

    public Indovinatore() {

    }
    @Override
    public void takePartita(Partita partita) {
        System.out.println("par45"+partita);
        System.out.println("par451"+partita.getIdPartita());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indovinatore);
        mIWordsRepository = new WordsRepository((Application) MyApplication.getAppContext(), this);

        mPartitaRepository = new PartitaRepository(this.getApplication(), this);
        mPartitaRepository.trovaPartita();
        String idPartita=pref.getString("idpartita", null);
        Log.d(TAG, "idpartita56"+idPartita);
        String idGruppo=pref.getString("idgruppo", null);
        int parolaInd=pref.getInt("parola_indovinate", 0);
        int passo1=pref.getInt("numeroPasso", 0);
        Partita partita=new Partita(idGruppo,passo1,parolaInd,idPartita);
        buzz = findViewById(R.id.buzz);
        timer = findViewById(R.id.timer);
        buzz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(timerRunning) {
                    pauseTimer();
                    //mIWordsRepository.fetchWords();
                    String parola=pref.getString("name", null);
                    Log.d(TAG, "parolaEstratta"+parola);
                    Intent i = new Intent(Indovinatore.this, InserisciParola.class);
                    i.putExtra("parola",getParola());
                    i.putExtra("partita",partita.getIdPartita());
                    startActivity(i);
                } else {
                    startTimer();
                    mIWordsRepository.fetchWords();
                    String parola=pref.getString("name", null);
                    setParola(parola);
                    Log.d(TAG, "parolaEstratta"+parola);
                    caricaParola(getParola(),partita.getIdPartita());
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
    public void onDataFound(Partita partita) {
        System.out.println("par89"+partita);
        System.out.println("par891"+partita.getIdPartita());
        editor.putString("idpartita", partita.getIdPartita());
        editor.putString("idgruppo", partita.getGruppoID());
        editor.putInt("parola_indovinate", partita.getParole_indovinate());
        editor.putInt("numeroPasso", partita.getPasso());
        editor.apply();
    }

    @Override
    public void onResponse(String parola) {
        System.out.println("dentroResponse"+parola);
        editor.putString("name", parola);
        editor.apply();
    }

    @Override
    public void onFailure(String errorMessage) {

    }

    public void caricaParola(String parola, String idPartita){
        DatabaseReference db = FirebaseDatabase.getInstance(Constants.FIREBASE_DATABASE_URL).getReference("partite");
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode : snapshot.getChildren()) {
                    keys.add(keyNode.getKey());
                    if (keyNode.child("idPartita").getValue().equals(idPartita))
                        db.child(keyNode.getKey()).child("parola").setValue(parola);
                }}

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
