package br.net.twome.fipe.view;

import android.os.Bundle;
import java.util.ArrayList;
import br.net.twome.fipe.adapter.SimpleBeanRecyclerViewAdapter;
import br.net.twome.fipe.business.Modelo;
import br.net.twome.fipe.business.Preco;
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
        return parameter.getVeiculo().getName();
    }

    @Override
    public String createSubTitle() {
        return parameter.getName();
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
    public void onClick(Preco obj) {
        //TODO
        ((MainActivity)getActivity()).closeSearch();
    }
}
