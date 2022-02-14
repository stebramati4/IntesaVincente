package com.example.intesavincente.ADAPTER;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.intesavincente.R;
import com.example.intesavincente.model.Gruppo;
import com.example.intesavincente.model.Partita;
import com.example.intesavincente.model.Utente;
import com.example.intesavincente.utils.Constants;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ListaStatisticheAdapter extends ArrayAdapter<Partita> {
    private static final String TAG ="ListaGruppiAdapter" ;

    private ArrayList<Partita> mArrayPartite;
    private int mLayout;

    private TextView textViewNomeGruppo;
    private TextView textViewParoleIndovinate;
    private TextView textViewNumPasso;

    private String idGruppo;
    private int nParoleIndovinate;
    private int nPassoUsati;

    private DatabaseReference dbGruppi;


    public ListaStatisticheAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Partita> objects) {
        super(context, resource, objects);
        this.mArrayPartite = objects;
        this.mLayout = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(mLayout, parent, false);
        }

        idGruppo = mArrayPartite.get(position).getGruppoID();
        nParoleIndovinate = mArrayPartite.get(position).getParole_indovinate();
        nPassoUsati = mArrayPartite.get(position).getPasso();

        //trovaNomeGruppo(idGruppo);

        textViewParoleIndovinate = convertView.findViewById(R.id.parole_indovinate);
        textViewParoleIndovinate.setText("NUMERO PAROLE INDOVINATE: " + String.valueOf(nParoleIndovinate));
        Log.d(TAG, "Numero parole indovinate : "+nParoleIndovinate);
        textViewNumPasso = convertView.findViewById(R.id.nPasso_usati);
        textViewNumPasso.setText("NUMERO PASSO RIMASTI: " + String.valueOf(nPassoUsati));
        Log.d(TAG, "Componenti : "+nPassoUsati);

        return convertView;
    }

    public void trovaNomeGruppo(String idGruppo){
        dbGruppi = FirebaseDatabase.getInstance(Constants.FIREBASE_DATABASE_URL).getReference("gruppi");
        dbGruppi.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<String> keys= new ArrayList<>();
                for(DataSnapshot keyNode : snapshot.getChildren()) {
                    if(keyNode.getKey().equals(idGruppo)){
                        String nomeGruppo = keyNode.child(keyNode.getKey()).child("nome").getValue(String.class);
                        textViewNomeGruppo.setText(nomeGruppo);
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
