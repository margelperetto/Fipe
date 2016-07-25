package br.net.twome.fipe.view;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import java.util.List;
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
    public void createAdapter(final RecyclerView recyclerView) {
        new FipeService().getVeiculos(parameter, new ServiceCallback<List<Veiculo>>() {
            @Override
            public void onSuccess(List<Veiculo> data) {
                adapter = new SimpleBeanRecyclerViewAdapter<>(data, FragmentVeiculo.this);
                recyclerView.setAdapter(adapter);
            }
        });
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
    public AbstractFragment fragmentAnterior() {
        return FragmentMarca.getInstance(parameter.getTipo());
    }

    @Override
    public void onClick(Veiculo obj) {
        ((MainActivity)getActivity()).showFragment(FragmentModelo.getInstance(obj));
    }
}
