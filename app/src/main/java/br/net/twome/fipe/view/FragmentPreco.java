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
import br.net.twome.fipe.business.Modelo;
import br.net.twome.fipe.business.Preco;
import br.net.twome.fipe.business.Veiculo;
import br.net.twome.fipe.service.FipeService;
import br.net.twome.fipe.service.ServiceCallback;

public class FragmentPreco extends AbstractFragment implements SimpleBeanViewHolder.HolderClickListener<Preco>{

    private Modelo modelo;

    public static FragmentPreco getInstance(Modelo modelo){
        FragmentPreco fragment = new FragmentPreco();
        Bundle args = new Bundle();
        args.putSerializable(PARAMETER, modelo);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            modelo = (Modelo) getArguments().getSerializable(PARAMETER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_simple_bean, container, false);

        Context context = view.getContext();
        recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        new FipeService().getPreco(modelo, new ServiceCallback<List<Preco>>() {
            @Override
            public void onSuccess(List<Preco> data) {
                adapter = new SimpleBeanRecyclerViewAdapter<>(data, FragmentPreco.this);
                recyclerView.setAdapter(adapter);
            }
        });
        return view;
    }

    @Override
    public String createTitle() {
        return modelo.getVeiculo().getName();
    }

    @Override
    public String createSubTitle() {
        return modelo.getName();
    }

    @Override
    public AbstractFragment fragmentAnterior() {
        return FragmentModelo.getInstance(modelo.getVeiculo());
    }

    @Override
    public void onClick(Preco obj) {
        //TODO
        ((MainActivity)getActivity()).fecharPesquisa();
    }
}
