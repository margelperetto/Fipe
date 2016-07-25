package br.net.twome.fipe.view;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.Arrays;
import br.net.twome.fipe.adapter.SimpleBeanRecyclerViewAdapter;
import br.net.twome.fipe.adapter.holder.SimpleBeanViewHolder;
import br.net.twome.fipe.adapter.holder.TipoViewHolder;
import br.net.twome.fipe.business.Tipo;

public class FragmentTipo extends AbstractFragment<Tipo, Void> {

    public static FragmentTipo newInstance() {
        FragmentTipo fragment = new FragmentTipo();
        Bundle args = new Bundle();
        args.putSerializable(PARAMETER,null);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public String createTitle() {
        return "Tipo de veículo";
    }

    @Override
    public String createSubTitle() {
        return null;
    }

    @Override
    public AbstractFragment fragmentAnterior() {
        return null;
    }

    @Override
    public void createAdapter(RecyclerView recyclerView) {
        adapter = new SimpleBeanRecyclerViewAdapter<Tipo>(Arrays.asList(Tipo.TIPOS), this){
            @Override
            public SimpleBeanViewHolder createViewHolder(LayoutInflater inflater, ViewGroup parent) {
                return new TipoViewHolder(inflater, parent);
            }
        };
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(Tipo obj) {
        ((MainActivity)getActivity()).showFragment(FragmentMarca.getInstance(obj));
    }
}