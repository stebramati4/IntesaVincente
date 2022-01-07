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

       // final ListaGruppiAdapter myArrayAdapter = new ListaGruppiAdapter(requireContext(), android.R.layout.activity_list_item, arrayGruppi);
        //listaGruppi = v.findViewById(R.id.Gruppi_listView);
        //listaGruppi.setAdapter(myArrayAdapter);
       // final ListaGruppiAdapter myArrayAdapter = new ListaGruppiAdapter(requireContext(), R.layout.gruppi_list_item, arrayGruppi);

        //listaGruppi = v.findViewById(R.id.Gruppi_listView);
       // listaGruppi.setAdapter(myArrayAdapter);
        //db = FirebaseDatabase.getInstance(Constants.FIREBASE_DATABASE_URL).getReference("gruppi").child("gruppoID");
        db = FirebaseDatabase.getInstance(Constants.FIREBASE_DATABASE_URL).getReference("gruppi");

        /*     db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Gruppo value = snapshot.getValue(Gruppo.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

*//*
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("gruppi");
        //DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("gruppi/gruppoID");
        Query usersQuery = usersRef.orderByChild("nome");

        usersQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    Log.d(TAG, "GruppoID inside getData: "+userSnapshot.getKey());
                    Log.d(TAG, "Gruppo Name inside getData: "+userSnapshot.child("nome").getValue());
                    Log.d(TAG, "DS inside getData: "+userSnapshot.child(userSnapshot.getKey()));
                   // System.out.println(userSnapshot.getKey() + ": " + userSnapshot.getChild("nome").getValue(String.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                throw databaseError.toException();
            }
*//*

       db.addChildEventListener(new ChildEventListener() {

           @Override
           public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
               Gruppo value = snapshot.getValue(Gruppo.class);
               arrayGruppi.add(value);
               Log.d(TAG," valore"+ value);

               myArrayAdapter.notifyDataSetChanged();



               //ListaGruppiAdapter adapter = new ListaGruppiAdapter(ListaGruppiFragment.this, arrayGruppi);
               //listaGruppi.setAdapter(myArrayAdapter);
           }

           @Override
           public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
               arrayGruppi.clear();
               List<String> keys= new ArrayList<>();
               for(DataSnapshot keyNode : snapshot.getChildren()){

                       keys.add(keyNode.getKey());
                       Gruppo gruppo = (Gruppo) keyNode.getValue(Gruppo.class);
                       Log.d(TAG, "GruppoID inside getData: " + keyNode.getKey());
                       Log.d(TAG, "Gruppo Name inside getData: " + keyNode.child("nome").getValue());
                       Log.d(TAG, "DS inside getData: " + keyNode.child(keyNode.getKey()));
                       //Gruppo gruppo1=new Gruppo(keyNode.child("nome").getValue(),keyNode.getKey(),null);
                       arrayGruppi.add(gruppo);
                       myArrayAdapter.notifyDataSetChanged();

               }
           }

           @Override
           public void onChildRemoved(@NonNull DataSnapshot snapshot) {

           }

           @Override
           public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }


       });
   */
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayGruppi.clear();
                List<String> keys= new ArrayList<>();
                for(DataSnapshot keyNode : snapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Gruppo gruppo=(Gruppo)keyNode.getValue(Gruppo.class);
                    Log.d(TAG, "GruppoID inside getData: "+keyNode.getKey());
                    Log.d(TAG, "Gruppo Name inside getData: "+keyNode.child("nome").getValue());
                    Log.d(TAG, "DS inside getData: "+keyNode.child(keyNode.getKey()));
                    //Gruppo gruppo1=new Gruppo(keyNode.child("nome").getValue(),keyNode.getKey(),null);
                    arrayGruppi.add(gruppo);
                    final ListaGruppiAdapter myArrayAdapter = new ListaGruppiAdapter(requireContext(), R.layout.gruppi_list_item, arrayGruppi);

                    listaGruppi = v.findViewById(R.id.Gruppi_listView);
                    listaGruppi.setAdapter(myArrayAdapter);
                    myArrayAdapter.notifyDataSetChanged();
                    Log.d(TAG," valore"+ gruppo.getNome()+"  "+ gruppo.getComponenti()+ "  "+gruppo.toString());
                }

                    for(int i=0;i<keys.size();i++){
                        Log.d(TAG," chiaviArray"+ keys.get(i));
                    }


                //Log.d(TAG," valore"+ gruppo);
                for(int i=0;i<arrayGruppi.size();i++){
                    Log.d(TAG," valoreArray"+ arrayGruppi.get(i));
                }
               // dataStatus.Data
                //myArrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        return v;
    }

}