package com.example.intesavincente.ADAPTER;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.intesavincente.model.Gruppo;
import com.example.intesavincente.R;
import com.example.intesavincente.repository.utente.UtenteRepository;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * * Custom Adapter that extends ArrayAdapter to show the groups in a ListView.
 */
public class ListaGruppiAdapter extends ArrayAdapter<Gruppo> {

    private static final String TAG ="ListaGruppiAdapter" ;

    private ArrayList<Gruppo> mArrayGruppi;
    private ArrayList<String> mArrayIdComponenti;
    private ArrayList<String> mArrayUtenti;
    private UtenteRepository mUtenteRepository = new UtenteRepository();
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
        textViewNomeGruppo.setText(mArrayGruppi.get(position).getNome());
        Log.d(TAG, "Nome Gruppo : "+mArrayGruppi.get(position).getNome());

        mArrayIdComponenti = mArrayGruppi.get(position).getComponenti();
        Log.d(TAG, "ArrayIdComponenti : "+mArrayIdComponenti.toString());
        mArrayUtenti = mUtenteRepository.getListaUtenti(mArrayIdComponenti);

        TextView textViewComponenti = convertView.findViewById(R.id.componenti);
        textViewComponenti.setText(mArrayUtenti.toString());
        Log.d(TAG, "Nome componente : "+mArrayUtenti.toString());

        return convertView;
    }
}

