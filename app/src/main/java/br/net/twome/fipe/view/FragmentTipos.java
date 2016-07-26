package br.net.twome.fipe.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Arrays;
import br.net.twome.fipe.adapter.SimpleBeanRecyclerViewAdapter;
import br.net.twome.fipe.holder.SimpleBeanViewHolder;
import br.net.twome.fipe.holder.TipoViewHolder;
import br.net.twome.fipe.business.Tipo;

public class FragmentTipos extends AbstractFragment<Tipo, Tipo> {

    public static FragmentTipos newInstance() {
        return new FragmentTipos();
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
    public void createData(SimpleBeanRecyclerViewAdapter<Tipo> adapter) {
        adapter.setData(new ArrayList<>(Arrays.asList(Tipo.TIPOS)));
    }

    @Override
    protected SimpleBeanRecyclerViewAdapter<Tipo> createEmptyAdapter() {
        return new SimpleBeanRecyclerViewAdapter<Tipo>(new ArrayList<>(Arrays.asList(Tipo.TIPOS)), this){
            @Override
            public SimpleBeanViewHolder createViewHolder(LayoutInflater inflater, ViewGroup parent) {
                return new TipoViewHolder(inflater, parent);
            }
        };
    }

    @Override
    public void onClick(Tipo obj) {
        ((MainActivity)getActivity()).showFragment(FragmentMarcas.getInstance(obj));
    }

    @Override
    protected boolean enableBack() {
        return false;
    }
}