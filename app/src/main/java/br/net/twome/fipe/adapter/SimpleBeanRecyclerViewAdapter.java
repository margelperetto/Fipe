package br.net.twome.fipe.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.net.twome.fipe.adapter.holder.SimpleBeanViewHolder;
import br.net.twome.fipe.business.SimpleBean;
import br.net.twome.fipe.utils.StringUtils;

public class SimpleBeanRecyclerViewAdapter<T extends SimpleBean> extends RecyclerView.Adapter<SimpleBeanViewHolder> {

    private List<T> data;
    private SimpleBeanViewHolder.HolderClickListener<T> listener;

    public SimpleBeanRecyclerViewAdapter(List<T> items,SimpleBeanViewHolder.HolderClickListener<T> listener) {
        data = items;
        this.listener = listener;
    }

    @Override
    public SimpleBeanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SimpleBeanViewHolder(LayoutInflater.from(parent.getContext()),parent);
    }

    @Override
    public void onBindViewHolder(final SimpleBeanViewHolder holder, int position) {
        holder.onBindViewHolder(data.get(position));
        holder.setListener(listener);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public List<T> getData(){
        return data;
    }

    public void orderBy(final String query){
        Collections.sort(data, new Comparator<SimpleBean>() {
            @Override
            public int compare(SimpleBean t1, SimpleBean t2) {

                String qLC =  StringUtils.retirarAcentos(query).toLowerCase();
                String t1LC = StringUtils.retirarAcentos(t1.getName()).toLowerCase();
                String t2LC = StringUtils.retirarAcentos(t2.getName()).toLowerCase();

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
