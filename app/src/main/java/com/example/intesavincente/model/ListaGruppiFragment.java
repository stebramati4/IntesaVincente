package com.example.intesavincente.model;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.intesavincente.ADAPTER.ListaGruppiAdapter;
import com.example.intesavincente.repository.gruppo.GruppoRepository;
import com.example.intesavincente.repository.partita.PartitaRepository;
import com.example.intesavincente.utils.Constants;
import com.example.intesavincente.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class ListaGruppiFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG ="ListaGruppiFragment" ;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ListaGruppiFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListaGruppiFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListaGruppiFragment newInstance(String param1, String param2) {
        ListaGruppiFragment fragment = new ListaGruppiFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    ListView listaGruppi;
    Snackbar snackbarUniscitiGruppo;
    Snackbar snackbarIsInserito;
    Snackbar snackbarGruppoPieno;
    ArrayList<Gruppo> arrayGruppi = new ArrayList<Gruppo>();

    DatabaseReference dbGruppi;
    DatabaseReference dbUtenti;
    DatabaseReference db;
    PartitaRepository mPartitaRepository =new PartitaRepository();
    GruppoRepository mGruppoRepository = new GruppoRepository();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_lista_gruppi, container, false);
        listaGruppi = v.findViewById(R.id.Gruppi_listView);

        dbGruppi = FirebaseDatabase.getInstance(Constants.FIREBASE_DATABASE_URL).getReference("gruppi");
        dbGruppi.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) { 
                arrayGruppi.clear();
                List<String> keys= new ArrayList<>();
                for(DataSnapshot keyNode : snapshot.getChildren()) {
                    keys.add(keyNode.getKey());
                    Log.d(TAG, "Chiavi gruppi " + keys.toString());
                    Log.d(TAG, "Chiavi gruppi " + keyNode);

                    Log.d(TAG, "GruppoID inside getData: " + keyNode.getKey());
                    Log.d(TAG, "Nome Gruppo: " + keyNode.child("nome").getValue());
                    Log.d(TAG, "Componenti : " + keyNode.child("componenti").getValue());

                    Log.d(TAG, "DS inside getData: " + keyNode.child(keyNode.getKey()));
                    //Gruppo gruppo1=new Gruppo(keyNode.child("nome").getValue(),keyNode.getKey(),null);
                    Log.d(TAG, "Nome Gruppo: " + keyNode.getValue());
                    Log.d(TAG, "Class: " + keyNode.child("componenti").getValue().getClass());

                    String gruppoId = keyNode.getKey();
                    String nomeGruppo = (String) keyNode.child("nome").getValue();
                    ArrayList<String> componenti = new ArrayList<String>();

                    for (int i = 0; i < 3; i++) {
                        if (keyNode.child("componenti").child(String.valueOf(i)).getValue() != null) {
                            String componente = keyNode.child("componenti").child(String.valueOf(i)).getValue().toString();
                            componenti.add(componente);
                        }
                    }
                    Gruppo gruppo = new Gruppo(gruppoId, nomeGruppo, componenti);
                    arrayGruppi.add(gruppo);
                }
                final ListaGruppiAdapter myArrayAdapter = new ListaGruppiAdapter(requireContext(), R.layout.gruppi_list_item, arrayGruppi);
                listaGruppi.setAdapter(myArrayAdapter);
                myArrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        listaGruppi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Gruppo gruppo = (Gruppo) listaGruppi.getItemAtPosition(i);
                Log.d(TAG, "gruppo00 " +gruppo.getID());

                Log.d(TAG, "Gruppo selezionato " + listaGruppi.getItemAtPosition(i).toString());
                Log.d(TAG, "Tipo " + listaGruppi.getItemAtPosition(i).getClass());

                db = FirebaseDatabase.getInstance(Constants.FIREBASE_DATABASE_URL).getReference();
                dbUtenti = FirebaseDatabase.getInstance(Constants.FIREBASE_DATABASE_URL).getReference("utenti");
                dbUtenti.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        List<String> keysUtenti = new ArrayList<>();
                        for (DataSnapshot keyNode : snapshot.getChildren()) {
                            Log.d(TAG, "KeyNode " + keyNode);
                            keysUtenti.add(keyNode.getKey());
                            if (keyNode.child("idUtente").getValue().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
                                keysUtenti.add(keyNode.getKey());

                                //Utente utente = (Utente) keyNode.getValue(Utente.class);
                                //Log.d(TAG, "Utente " + utente.toString1());
                               // Log.d(TAG, "Reference " + dbGruppi.getRef());
                                dbGruppi.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        ArrayList<String> listaComponenti = new ArrayList();
                                        List<String> keysGruppi = new ArrayList<>();
                                        for (DataSnapshot keyNodeGruppi : dataSnapshot.getChildren()) {
                                            keysGruppi.add(keyNodeGruppi.getKey());
                                            Log.d(TAG, "KeyNode " + keyNodeGruppi);
                                            Log.d(TAG, "Class KeyNode " + keyNodeGruppi.getValue().getClass());
                                            //Log.d(TAG, "GruppoID " + keyNodeGruppi.child("componenti").getValue().getClass());
                                            if(gruppo.getID().equals(keyNodeGruppi.getKey())){
                                                Boolean isInserito = false;

                                                for (int i = 0; i < 3; i++) {
                                                    if (keyNodeGruppi.child("componenti").child(String.valueOf(i)).getValue(String.class) != null) {
                                                        String componente = keyNodeGruppi.child("componenti").child(String.valueOf(i)).getValue(String.class);
                                                        listaComponenti.add(componente);

                                                    }
                                                }

                                                for(int i = 0; i < listaComponenti.size(); i++){
                                                   // Log.d(TAG, "Utente " + utente.getNickname());
                                                    Log.d(TAG, "Componente " + listaComponenti.get(i));
                                                    if(keyNode.child("idUtente").getValue().equals(listaComponenti.get(i))){
                                                        isInserito = true;
                                                    }
                                                }
                                                if(isInserito){
                                                    snackbarIsInserito = Snackbar.make(v, "UTENTE GIA' PRESENTE NEL GRUPPO", Snackbar.LENGTH_SHORT);
                                                    snackbarIsInserito.show();
                                                }
                                                else {
                                                    if (listaComponenti.size() < 3) {
                                                        dbGruppi.child(gruppo.getID()).child("componenti").child(String.valueOf(listaComponenti.size())).setValue(FirebaseAuth.getInstance().getCurrentUser().getUid());
                                                        //snackbarUniscitiGruppo = Snackbar.make(v, "UTENTE " + utente.getNickname() + " INSERITO", Snackbar.LENGTH_SHORT);
                                                        snackbarUniscitiGruppo = Snackbar.make(v, "UTENTE  INSERITO", Snackbar.LENGTH_SHORT);
                                                        snackbarUniscitiGruppo.show();
                                                        mPartitaRepository.inserisciPartitaInUtente(gruppo.getID());
                                                        Navigation.findNavController(v).navigate(R.id.action_ListaGruppiFragment_to_scegliRuoloFragment);


                                                    }
                                                    else{
                                                        snackbarGruppoPieno = Snackbar.make(v, "GRUPPO GIA' COMPLETO", Snackbar.LENGTH_SHORT);
                                                        snackbarGruppoPieno.show();
                                                    }
                                                }
                                            }
                                            Log.d(TAG, "GruppoID " + listaComponenti);
                                        }


                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });

                            } else {
                                // Toast.makeText(this, "Devi inserire un nome", Toast.LENGTH_LONG).show();
                            }

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });


        return v;
    }

}

// piccola modifica inutile 2