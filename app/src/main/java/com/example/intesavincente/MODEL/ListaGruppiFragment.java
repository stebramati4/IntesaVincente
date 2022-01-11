package com.example.intesavincente.MODEL;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.intesavincente.ADAPTER.ListaGruppiAdapter;
import com.example.intesavincente.Constants;
import com.example.intesavincente.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
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
    ArrayList<Gruppo> arrayGruppi = new ArrayList<Gruppo>();

    DatabaseReference db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_lista_gruppi, container, false);

        db = FirebaseDatabase.getInstance(Constants.FIREBASE_DATABASE_URL).getReference("gruppi");
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) { 
                arrayGruppi.clear();
                List<String> keys= new ArrayList<>();
                for(DataSnapshot keyNode : snapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Log.d(TAG, "Chiavi gruppi "+ keys.toString());
                    Log.d(TAG, "Chiavi gruppi "+ keyNode);

                    Log.d(TAG, "GruppoID inside getData: "+keyNode.getKey());
                    Log.d(TAG, "Nome Gruppo: "+keyNode.child("nome").getValue());
                    Log.d(TAG, "Componenti : "+keyNode.child("componenti").getValue());

                    Log.d(TAG, "DS inside getData: "+keyNode.child(keyNode.getKey()));
                    //Gruppo gruppo1=new Gruppo(keyNode.child("nome").getValue(),keyNode.getKey(),null);
                    Log.d(TAG, "Nome Gruppo: "+keyNode.getValue());
                    Log.d(TAG, "Class: "+keyNode.child("componenti").getValue().getClass());
                    Log.d(TAG, "Tipo componente: "+keyNode.child("componenti").child("0").getValue(Utente.class).getClass());
                    Log.d(TAG, "Componente: "+keyNode.child("componenti").getValue(ArrayList.class));
                    Log.d(TAG, "Componente: "+keyNode.child("componenti").child("0").getKey());
                    String gruppoId = keyNode.getKey();
                    String nomeGruppo = (String) keyNode.child("nome").getValue();
                    ArrayList<Utente> componenti = new ArrayList<Utente>();
                    for(int i=0; i<3; i++){
                        if(keyNode.child("componenti").child(String.valueOf(i)).getValue() != null) {
                            componenti.add((Utente) keyNode.child("componenti").child(String.valueOf(i)).getValue());
                        }
                    }
                    Gruppo gruppo = new Gruppo(gruppoId, nomeGruppo, componenti);
                    arrayGruppi.add(gruppo);
                    for(int i=0; i<arrayGruppi.size(); i++){
                        Log.d(TAG, "Nome Gruppo: "+arrayGruppi.get(i).getNome());
                        Log.d(TAG, "Nome Gruppo: "+arrayGruppi.get(i).getComponenti());
                    }

                    final ListaGruppiAdapter myArrayAdapter = new ListaGruppiAdapter(requireContext(), R.layout.gruppi_list_item, arrayGruppi);

                    listaGruppi = v.findViewById(R.id.Gruppi_listView);
                    listaGruppi.setAdapter(myArrayAdapter);
                    myArrayAdapter.notifyDataSetChanged();
                    Log.d(TAG," valore"+ gruppo.getNome()+"  "+ gruppo.getComponenti());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        return v;
    }

}

// piccola modifica inutile 2