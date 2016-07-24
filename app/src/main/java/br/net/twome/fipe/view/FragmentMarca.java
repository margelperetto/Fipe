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
import br.net.twome.fipe.business.Tipo;
import br.net.twome.fipe.service.FipeService;
import br.net.twome.fipe.service.ServiceCallback;

public class FragmentMarca extends AbstractFragment implements SimpleBeanViewHolder.HolderClickListener<Marca>{

    private Tipo tipo;

    public static FragmentMarca getInstance(Tipo tipo){
        FragmentMarca fragment = new FragmentMarca();
        Bundle args = new Bundle();
        args.putSerializable(PARAMETER, tipo);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            tipo = (Tipo)getArguments().getSerializable(PARAMETER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_simple_bean, container, false);

        Context context = view.getContext();
        recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        new FipeService().getMarcas(tipo, new ServiceCallback<List<Marca>>() {
            @Override
            public void onSuccess(List<Marca> data) {
                adapter = new SimpleBeanRecyclerViewAdapter<>(data, FragmentMarca.this);
                recyclerView.setAdapter(adapter);
            }
        });
        return view;
    }

    @Override
    public String createTitle() {
        return "Marcas";
    }

    @Override
    public String createSubTitle() {
        return tipo.getDescricao();
    }

    @Override
    public AbstractFragment fragmentAnterior() {
        return FragmentTipo.newInstance();
    }

    @Override
    public void onClick(Marca obj) {
        if(obj == null){
            return;
        }
        ((MainActivity)getActivity()).showFragment(FragmentVeiculo.getInstance(obj));
    }
}
