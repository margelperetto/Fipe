package br.net.twome.fipe.view;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import java.util.List;
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
    public void createAdapter(final RecyclerView recyclerView) {
        new FipeService().getPreco(parameter, new ServiceCallback<List<Preco>>() {
            @Override
            public void onSuccess(List<Preco> data) {
                adapter = new SimpleBeanRecyclerViewAdapter<>(data, FragmentPreco.this);
                recyclerView.setAdapter(adapter);
            }
        });
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
    public AbstractFragment fragmentAnterior() {
        return FragmentModelo.getInstance(parameter.getVeiculo());
    }

    @Override
    public void onClick(Preco obj) {
        //TODO
        ((MainActivity)getActivity()).fecharPesquisa();
    }
}
