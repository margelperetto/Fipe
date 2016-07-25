package br.net.twome.fipe.view;

import android.os.Bundle;
import java.util.ArrayList;
import br.net.twome.fipe.adapter.SimpleBeanRecyclerViewAdapter;
import br.net.twome.fipe.business.Modelo;
import br.net.twome.fipe.business.Veiculo;
import br.net.twome.fipe.service.FipeService;
import br.net.twome.fipe.service.ServiceCallback;

public class FragmentModelo extends AbstractFragment<Modelo,Veiculo>{

    public static FragmentModelo getInstance(Veiculo veiculo){
        FragmentModelo fragment = new FragmentModelo();
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
        new FipeService().getModelos(parameter, new ServiceCallback<ArrayList<Modelo>>() {
            @Override
            public void onSuccess(ArrayList<Modelo> data) {
                adapter.setData(data);
            }
        });
    }

    @Override
    public void onClick(Modelo obj) {
        ((MainActivity)getActivity()).showFragment(FragmentPreco.getInstance(obj));
    }
}
