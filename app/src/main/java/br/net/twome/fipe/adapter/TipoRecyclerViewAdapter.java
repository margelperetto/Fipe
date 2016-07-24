package br.net.twome.fipe.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import br.net.twome.fipe.adapter.holder.TipoViewHolder;
import br.net.twome.fipe.business.Tipo;
import br.net.twome.fipe.utils.StringUtils;
import br.net.twome.fipe.view.MainActivity;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TipoRecyclerViewAdapter extends RecyclerView.Adapter<TipoViewHolder> {

    private List<Tipo> data;
    private MainActivity activity;

    public TipoRecyclerViewAdapter(List<Tipo> items, MainActivity activity) {
        data = items;
        this.activity = activity;
    }

    @Override
    public TipoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TipoViewHolder(LayoutInflater.from(parent.getContext()),parent, activity);
    }

    @Override
    public void onBindViewHolder(final TipoViewHolder holder, int position) {
        holder.onBindViewHolder(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public List<Tipo> getData(){
        return data;
    }

    public void setData(List<Tipo> data){
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public void orderBy(final String query){
        Collections.sort(data, new Comparator<Tipo>() {
            @Override
            public int compare(Tipo t1, Tipo t2) {

                String qLC =  StringUtils.retirarAcentos(query).toLowerCase();
                String t1LC = StringUtils.retirarAcentos(t1.getDescricao()).toLowerCase();
                String t2LC = StringUtils.retirarAcentos(t2.getDescricao()).toLowerCase();

                if( t1LC.startsWith(qLC) && t2LC.startsWith(qLC) ){
                    return t2LC.compareTo(t1LC);
                }
                if( t1LC.startsWith(qLC) ){
                    return -1;
                }
                if( t2LC.startsWith(qLC) ){
                    return 1;
                }
                return 0;
            }
        });
        notifyDataSetChanged();
    }
}
