package com.example.intesavincente.DATABASE;

import com.example.intesavincente.MODEL.Gruppo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

public class FirebaseDatabaseHelper {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceGruppi;
    private ArrayList<Gruppo> gruppi = new ArrayList<>();

    public interface DataStatus {
        void DataIsLoaded(ArrayList<Gruppo> gruppi, List<String> keys);
        void DataIsInserted();
        void DataIsUpdate();
        void DataIsDeleted();
    }

    public FirebaseDatabaseHelper() {
        mDatabase = FirebaseDatabase.getInstance();
        mReferenceGruppi = mDatabase.getReference("gruppi");
    }

    public void readGruppi(final DataStatus dataStatus) {
        mReferenceGruppi.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                gruppi.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode : snapshot.getChildren()) {
                    keys.add(keyNode.getKey());
                    Gruppo gruppo = keyNode.getValue(Gruppo.class);
                    gruppi.add(gruppo);
                }
                dataStatus.DataIsLoaded(gruppi, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
