package br.net.twome.fipe.view;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import java.util.List;
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
    public void createAdapter(final RecyclerView recyclerView) {
        new FipeService().getModelos(parameter, new ServiceCallback<List<Modelo>>() {
            @Override
            public void onSuccess(List<Modelo> data) {
                adapter = new SimpleBeanRecyclerViewAdapter<>(data, FragmentModelo.this);
                recyclerView.setAdapter(adapter);
            }
        });
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
    public AbstractFragment fragmentAnterior() {
        return FragmentVeiculo.getInstance(parameter.getMarca());
    }

    @Override
    public void onClick(Modelo obj) {
        ((MainActivity)getActivity()).showFragment(FragmentPreco.getInstance(obj));
    }
}
