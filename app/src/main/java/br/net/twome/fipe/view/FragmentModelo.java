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
import br.net.twome.fipe.business.Veiculo;
import br.net.twome.fipe.service.FipeService;
import br.net.twome.fipe.service.ServiceCallback;

public class FragmentModelo extends AbstractFragment implements SimpleBeanViewHolder.HolderClickListener<Modelo>{

    private Veiculo veiculo;

    public static FragmentModelo getInstance(Veiculo veiculo){
        FragmentModelo fragment = new FragmentModelo();
        Bundle args = new Bundle();
        args.putSerializable(PARAMETER, veiculo);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            veiculo = (Veiculo)getArguments().getSerializable(PARAMETER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_simple_bean, container, false);

        Context context = view.getContext();
        recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        new FipeService().getModelos(veiculo, new ServiceCallback<List<Modelo>>() {
            @Override
            public void onSuccess(List<Modelo> data) {
                adapter = new SimpleBeanRecyclerViewAdapter<>(data, FragmentModelo.this);
                recyclerView.setAdapter(adapter);
            }
        });
        return view;
    }

    @Override
    public String createTitle() {
        return "Modelos";
    }

    @Override
    public String createSubTitle() {
        return veiculo.getName();
    }

    @Override
    public AbstractFragment fragmentAnterior() {
        return FragmentVeiculo.getInstance(veiculo.getMarca());
    }

    @Override
    public void onClick(Modelo obj) {
        if(obj==null){
            return;
        }
        ((MainActivity)getActivity()).showFragment(FragmentPreco.getInstance(obj));
    }
}
