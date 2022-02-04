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
import com.example.intesavincente.utils.FirebaseCallback;

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

        TextView textViewComponenti = convertView.findViewById(R.id.componenti);

        mUtenteRepository.getListaUtenti(new FirebaseCallback() {
            @Override
            public void onResponse(ArrayList<String> listaNomi) {
                textViewComponenti.setText(stampaNomeComponenti(listaNomi));
                Log.d(TAG, "Nome componente : "+listaNomi.toString());
            }
        });

        return convertView;
    }

    public String stampaNomeComponenti(ArrayList<String> listaNomi){
        String stampa = "";
        System.out.println("Tipo: " + listaNomi.getClass());
        System.out.println("Size: " + listaNomi.size());
        for(int i=0;i<listaNomi.size();i++){
            String nomeUtente = listaNomi.get(i);
            System.out.println("Utente: " + listaNomi.get(i));
            System.out.println("Condizione: " + (listaNomi.get(i) != null));
            if(listaNomi.get(i) != null){
                if(i == 0) {
                    System.out.println("Nickname utente if: ");
                    stampa = listaNomi.get(i);

                }
                else {
                    System.out.println("Nickname utente else: ");
                    stampa = stampa + ", " + listaNomi.get(i);

                }
            }
            else {
                System.out.println("Else del primo If");
                return stampa;
            }
        }
        return stampa;
    }

}

