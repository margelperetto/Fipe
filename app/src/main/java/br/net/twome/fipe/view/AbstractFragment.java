package br.net.twome.fipe.view;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;

import br.net.twome.fipe.adapter.SimpleBeanRecyclerViewAdapter;
import br.net.twome.fipe.business.SimpleBean;

public abstract class AbstractFragment extends Fragment{

    public static final String PARAMETER = "parameter";
    protected RecyclerView recyclerView;
    protected SimpleBeanRecyclerViewAdapter<? extends SimpleBean> adapter;

    public abstract String createTitle();
    public abstract String createSubTitle();
    public abstract AbstractFragment fragmentAnterior();

    public void ordenarLista(String query) {
        recyclerView.scrollToPosition(0);
        if(adapter != null){
            adapter.orderBy(query);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity)getActivity()).getSupportActionBar().setTitle(createTitle());
        ((MainActivity)getActivity()).getSupportActionBar().setSubtitle(createSubTitle());
    }
}
