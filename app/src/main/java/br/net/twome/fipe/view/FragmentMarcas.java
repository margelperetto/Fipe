package br.net.twome.fipe.view;

import android.os.Bundle;
import java.util.ArrayList;
import br.net.twome.fipe.adapter.SimpleBeanRecyclerViewAdapter;
import br.net.twome.fipe.business.Marca;
import br.net.twome.fipe.business.Tipo;
import br.net.twome.fipe.service.FipeService;
import br.net.twome.fipe.service.ServiceCallback;

public class FragmentMarcas extends AbstractFragment<Marca,Tipo> {

    public static FragmentMarcas getInstance(Tipo tipo){
        FragmentMarcas fragment = new FragmentMarcas();
        Bundle args = new Bundle();
        args.putSerializable(PARAMETER, tipo);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public String createTitle() {
        return "Marcas";
    }

    @Override
    public String createSubTitle() {
        return parameter.getName();
    }

    @Override
    public void createData(final SimpleBeanRecyclerViewAdapter<Marca> adapter) {
        new FipeService((MainActivity)getActivity()).getMarcas(parameter, new ServiceCallback<ArrayList<Marca>>() {
            @Override
            public void onSuccess(ArrayList<Marca> data) {
                adapter.setData(data);
            }
        });
    }

    @Override
    public void onClick(Marca obj) {
        ((MainActivity)getActivity()).showFragment(FragmentVeiculos.getInstance(obj));
    }

}
