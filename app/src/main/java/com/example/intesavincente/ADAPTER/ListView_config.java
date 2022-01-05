package com.example.intesavincente.ADAPTER;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.intesavincente.MODEL.Gruppo;
import com.example.intesavincente.R;

import java.util.List;

public class ListView_config {

    private Context mContext;
    private GruppiAdapter gruppiAdapter;
    public void setConfig(RecyclerView recyclerView, Context context, List<Gruppo> gruppi, List<String> keys){
        mContext=context;
        gruppiAdapter=new GruppiAdapter(gruppi,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(gruppiAdapter);
    }

    class GruppiItemView extends RecyclerView.ViewHolder{
        private TextView nomegruppo;
        private TextView componenti;
        private String key;

        public GruppiItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).inflate(R.layout.gruppi_list_item, parent ,false));
            nomegruppo=(TextView) itemView.findViewById(R.id.nome_gruppo);
            componenti=(TextView) itemView.findViewById(R.id.componenti);
        }
        public void bind(Gruppo gruppo, String key){
            nomegruppo.setText(gruppo.getNome());
            // String s= ((gruppo.getComponenti()).getNickname());
            componenti.setText((gruppo.getUtente()));
            this.key=key;
        }
    }
    class GruppiAdapter extends RecyclerView.Adapter<GruppiItemView>{
        private List <Gruppo> arrayGruppi;
        private List <String> keys;
        public GruppiAdapter(List<Gruppo> arrayGruppi, List<String> keys){
            this.arrayGruppi=arrayGruppi;
            this.keys=keys;
        }

        @NonNull
        @Override
        public GruppiItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new GruppiItemView((parent));
        }

        @Override
        public void onBindViewHolder(@NonNull GruppiItemView holder, int position) {
            holder.bind(arrayGruppi.get(position),keys.get(position));
        }

        @Override
        public int getItemCount() {
            return arrayGruppi.size();
        }
    }
}
