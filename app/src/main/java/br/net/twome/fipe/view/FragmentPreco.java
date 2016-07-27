package br.net.twome.fipe.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import br.net.twome.fipe.adapter.SimpleBeanRecyclerViewAdapter;
import br.net.twome.fipe.business.Modelo;
import br.net.twome.fipe.business.Preco;
import br.net.twome.fipe.holder.PrecoViewHolder;
import br.net.twome.fipe.holder.SimpleBeanViewHolder;
import br.net.twome.fipe.service.FipeService;
import br.net.twome.fipe.service.ServiceCallback;

public class FragmentPreco extends AbstractFragment<Preco, Modelo>{

    public static FragmentPreco getInstance(Modelo modelo){
        FragmentPreco fragment = new FragmentPreco();
        Bundle args = new Bundle();
        args.putSerializable(PARAMETER, modelo);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public String createTitle() {
        return "Consulta FIPE";
    }

    @Override
    public String createSubTitle() {
        return null;
    }

    @Override
    public void createData(final SimpleBeanRecyclerViewAdapter<Preco> adapter) {
        new FipeService((MainActivity)getActivity()).getPreco(parameter, new ServiceCallback<ArrayList<Preco>>() {
            @Override
            public void onSuccess(ArrayList<Preco> data) {
                adapter.setData(data);
            }
        });
    }

    @Override
    protected SimpleBeanViewHolder createHolder(LayoutInflater inflater, ViewGroup parent) {
        return new PrecoViewHolder(inflater, parent);
    }

    @Override
    protected boolean enableSearch() {
        return false;
    }

    @Override
    public void onClick(Preco obj) {
        //TODO
        ((MainActivity)getActivity()).closeSearch();
    }
}
