package br.net.twome.fipe.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;

import br.net.twome.fipe.R;
import br.net.twome.fipe.adapter.TipoRecyclerViewAdapter;
import br.net.twome.fipe.business.Tipo;

public class FragmentTipo extends AbstractFragment {

    private TipoRecyclerViewAdapter adapter;
    private RecyclerView recyclerView;

    public static FragmentTipo newInstance() {
        FragmentTipo fragment = new FragmentTipo();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_tipo, container, false);

        Context context = view.getContext();
        recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new TipoRecyclerViewAdapter(Arrays.asList(Tipo.values()), (MainActivity) getActivity());
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public String createTitle() {
        return "Tipo de veículo";
    }

    @Override
    public String createSubTitle() {
        return null;
    }

    @Override
    public AbstractFragment fragmentAnterior() {
        return null;
    }

    @Override
    public void ordenarLista(String query) {
        recyclerView.scrollToPosition(0);
        if(adapter != null){
            adapter.orderBy(query);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
