package br.net.twome.fipe.view;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import java.util.List;
import br.net.twome.fipe.adapter.SimpleBeanRecyclerViewAdapter;
import br.net.twome.fipe.business.Marca;
import br.net.twome.fipe.business.Tipo;
import br.net.twome.fipe.service.FipeService;
import br.net.twome.fipe.service.ServiceCallback;

public class FragmentMarca extends AbstractFragment<Marca,Tipo> {

    public static FragmentMarca getInstance(Tipo tipo){
        FragmentMarca fragment = new FragmentMarca();
        Bundle args = new Bundle();
        args.putSerializable(PARAMETER, tipo);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void createAdapter(final RecyclerView recyclerView) {
        new FipeService().getMarcas(parameter, new ServiceCallback<List<Marca>>() {
            @Override
            public void onSuccess(List<Marca> data) {
                adapter = new SimpleBeanRecyclerViewAdapter<>(data, FragmentMarca.this);
                recyclerView.setAdapter(adapter);
            }
        });
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
    public AbstractFragment fragmentAnterior() {
        return FragmentTipo.newInstance();
    }

    @Override
    public void onClick(Marca obj) {
        ((MainActivity)getActivity()).showFragment(FragmentVeiculo.getInstance(obj));
    }
}
