package com.example.intesavincente.ADAPTER;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.intesavincente.MODEL.Gruppo;
import com.example.intesavincente.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * * Custom Adapter that extends ArrayAdapter to show the groups in a ListView.
 */
public class ListaGruppiAdapter extends ArrayAdapter<Gruppo> {

    private ArrayList<Gruppo> mArrayGruppi;
    private int mLayout;


    public ListaGruppiAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Gruppo> objects) {
        super(context, resource, objects);
        this.mArrayGruppi = objects;
        this.mLayout = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(mLayout, parent, false);
        }

        TextView textViewNomeGruppo = convertView.findViewById(R.id.nome_gruppo);
        //TextView textViewSourceTitle = convertView.findViewById(R.id.news_source);

        textViewNomeGruppo.setText(mArrayGruppi.get(position).getNome());
        //textViewSourceTitle.setText(mArrayNews[position].getNewsSource().getName());

        return convertView;
    }
}

