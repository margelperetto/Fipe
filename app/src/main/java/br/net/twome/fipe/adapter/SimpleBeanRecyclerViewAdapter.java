package br.net.twome.fipe.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import br.net.twome.fipe.holder.SimpleBeanViewHolder;
import br.net.twome.fipe.business.SimpleBean;
import br.net.twome.fipe.utils.StringUtils;

public class SimpleBeanRecyclerViewAdapter<T extends SimpleBean> extends RecyclerView.Adapter<SimpleBeanViewHolder> {

    private ArrayList<T> data;
    private SimpleBeanViewHolder.HolderClickListener<T> listener;

    public SimpleBeanRecyclerViewAdapter(ArrayList<T> data,SimpleBeanViewHolder.HolderClickListener<T> listener) {
        this.data = data;
        this.listener = listener;
    }

    @Override
    public SimpleBeanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        SimpleBeanViewHolder holder = createViewHolder(LayoutInflater.from(parent.getContext()),parent);
        holder.setListener(listener);
        return holder;
    }

    public SimpleBeanViewHolder createViewHolder(LayoutInflater inflater, ViewGroup parent){
        return new SimpleBeanViewHolder(inflater,parent);
    }

    @Override
    public void onBindViewHolder(final SimpleBeanViewHolder holder, int position) {
        holder.onBindViewHolder(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public ArrayList<T> getData(){
        return data;
    }

    public void orderBy(final String query){
        Collections.sort(data, new Comparator<SimpleBean>() {
            @Override
            public int compare(SimpleBean t1, SimpleBean t2) {

                String qLC =  StringUtils.retirarAcentos(query).toLowerCase();
                String t1LC = StringUtils.retirarAcentos(t1.getName()).toLowerCase();
                String t2LC = StringUtils.retirarAcentos(t2.getName()).toLowerCase();

                boolean t1Start = t1LC.startsWith(qLC);
                boolean t2Start = t2LC.startsWith(qLC);

                if( t1Start && t2Start ){
                    return t1LC.compareTo(t2LC);
                }
                if( t1Start ){
                    return -1;
                }
                if( t2Start ){
                    return 1;
                }

                qLC = StringUtils.quoteSpaces(qLC);
                boolean t1Match = t1LC.matches(qLC);
                boolean t2Match = t2LC.matches(qLC);

                if( t1Match && t2Match ){
                    return t1LC.compareTo(t2LC);
                }
                if( t1Match ){
                    return -1;
                }
                if( t2Match ){
                    return 1;
                }
                return 0;
            }
        });
        notifyDataSetChanged();
    }

    public void setData(ArrayList<T> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

}