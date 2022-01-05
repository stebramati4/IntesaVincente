package com.example.intesavincente.MODEL;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.intesavincente.ADAPTER.ListView_config;
import com.example.intesavincente.ADAPTER.ListaGruppiAdapter;
import com.example.intesavincente.Constants;
import com.example.intesavincente.DATABASE.FirebaseDatabaseHelper;
import com.example.intesavincente.MainActivity;
import com.example.intesavincente.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class ListaGruppiFragment extends Fragment {

    private RecyclerView mRecyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_lista_gruppi, container, false);

        mRecyclerView = v.findViewById(R.id.recyclerview_gruppi);
        new FirebaseDatabaseHelper().readGruppi(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(ArrayList<Gruppo> gruppi, List<String> keys) {
                new ListView_config().setConfig(mRecyclerView, getContext(), gruppi, keys);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdate() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });


       return v;
    }
}