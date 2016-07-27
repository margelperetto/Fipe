package br.net.twome.fipe.view;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.Collections;

import br.net.twome.fipe.adapter.SimpleBeanRecyclerViewAdapter;
import br.net.twome.fipe.business.Modelo;
import br.net.twome.fipe.business.Veiculo;
import br.net.twome.fipe.service.FipeService;
import br.net.twome.fipe.service.ServiceCallback;

public class FragmentModelos extends AbstractFragment<Modelo,Veiculo>{

    public static FragmentModelos getInstance(Veiculo veiculo){
        FragmentModelos fragment = new FragmentModelos();
        Bundle args = new Bundle();
        args.putSerializable(PARAMETER, veiculo);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public String createTitle() {
        return "Modelos";
    }

    @Override
    public String createSubTitle() {
        return parameter.getName();
    }

    @Override
    public void createData(final SimpleBeanRecyclerViewAdapter<Modelo> adapter) {
        new FipeService((MainActivity)getActivity()).getModelos(parameter, new ServiceCallback<ArrayList<Modelo>>() {
            @Override
            public void onSuccess(ArrayList<Modelo> data) {
                Collections.reverse(data);
                adapter.setData(data);
            }
        });
    }

    @Override
    public void onClick(Modelo obj) {
        ((MainActivity)getActivity()).showFragment(FragmentPreco.getInstance(obj));
    }
}
