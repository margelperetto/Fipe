package br.net.twome.fipe.view;

import android.os.Bundle;
import java.util.ArrayList;
import br.net.twome.fipe.adapter.SimpleBeanRecyclerViewAdapter;
import br.net.twome.fipe.business.Marca;
import br.net.twome.fipe.business.Veiculo;
import br.net.twome.fipe.service.FipeService;
import br.net.twome.fipe.service.ServiceCallback;

public class FragmentVeiculo extends AbstractFragment<Veiculo,Marca>{

    public static FragmentVeiculo getInstance(Marca marca){
        FragmentVeiculo fragment = new FragmentVeiculo();
        Bundle args = new Bundle();
        args.putSerializable(PARAMETER, marca);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public String createTitle() {
        return "Veículos";
    }

    @Override
    public String createSubTitle() {
        return parameter.getTipo()+" > "+parameter.getName();
    }

    @Override
    public void createData(final SimpleBeanRecyclerViewAdapter<Veiculo> adapter) {
        new FipeService().getVeiculos(parameter, new ServiceCallback<ArrayList<Veiculo>>() {
            @Override
            public void onSuccess(ArrayList<Veiculo> data) {
                adapter.setData(data);
            }
        });
    }

    @Override
    public void onClick(Veiculo obj) {
        ((MainActivity)getActivity()).showFragment(FragmentModelo.getInstance(obj));
    }
}
