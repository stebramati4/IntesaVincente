package com.example.intesavincente.MODEL;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.intesavincente.ADAPTER.ListaGruppiAdapter;
import com.example.intesavincente.Constants;
import com.example.intesavincente.MainActivity;
import com.example.intesavincente.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ListaGruppiFragment extends Fragment {

    private static final String TAG = "ListaGruppiFragment";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

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

        final ListaGruppiAdapter myArrayAdapter = new ListaGruppiAdapter(requireContext(), R.layout.gruppi_list_item, arrayGruppi);
        listaGruppi = v.findViewById(R.id.Gruppi_listView);
        listaGruppi.setAdapter(myArrayAdapter);

        db = FirebaseDatabase.getInstance(Constants.FIREBASE_DATABASE_URL).getReference("gruppi");

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Gruppo value = snapshot.getValue(Gruppo.class);
                Log.d(TAG, "Valore: " + value);
                arrayGruppi.add(value);
                myArrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

       db.addChildEventListener(new ChildEventListener() {
           @Override
           public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

           }

           @Override
           public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

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

       return v;
    }
}