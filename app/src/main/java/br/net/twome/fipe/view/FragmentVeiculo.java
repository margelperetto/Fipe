package br.net.twome.fipe.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.net.twome.fipe.R;
import br.net.twome.fipe.adapter.SimpleBeanRecyclerViewAdapter;
import br.net.twome.fipe.adapter.holder.SimpleBeanViewHolder;
import br.net.twome.fipe.business.Marca;
import br.net.twome.fipe.business.Veiculo;
import br.net.twome.fipe.service.FipeService;
import br.net.twome.fipe.service.ServiceCallback;

public class FragmentVeiculo extends AbstractFragment implements SimpleBeanViewHolder.HolderClickListener<Veiculo>{

    private Marca marca;

    public static FragmentVeiculo getInstance(Marca marca){
        FragmentVeiculo fragment = new FragmentVeiculo();
        Bundle args = new Bundle();
        args.putSerializable(PARAMETER, marca);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            marca = (Marca)getArguments().getSerializable(PARAMETER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_simple_bean, container, false);

        Context context = view.getContext();
        recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        new FipeService().getVeiculos(marca, new ServiceCallback<List<Veiculo>>() {
            @Override
            public void onSuccess(List<Veiculo> data) {
                adapter = new SimpleBeanRecyclerViewAdapter<>(data, FragmentVeiculo.this);
                recyclerView.setAdapter(adapter);
            }
        });
        return view;
    }

    @Override
    public String createTitle() {
        return "Veículos";
    }

    @Override
    public String createSubTitle() {
        return marca.getTipo()+" > "+marca.getName();
    }

    @Override
    public AbstractFragment fragmentAnterior() {
        return FragmentMarca.getInstance(marca.getTipo());
    }

    @Override
    public void onClick(Veiculo obj) {
        if(obj==null){
            return;
        }
        ((MainActivity)getActivity()).showFragment(FragmentModelo.getInstance(obj));
    }
}
