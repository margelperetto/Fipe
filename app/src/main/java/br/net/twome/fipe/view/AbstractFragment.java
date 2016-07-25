package br.net.twome.fipe.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import br.net.twome.fipe.R;
import br.net.twome.fipe.adapter.SimpleBeanRecyclerViewAdapter;
import br.net.twome.fipe.adapter.holder.SimpleBeanViewHolder;
import br.net.twome.fipe.business.SimpleBean;

public abstract class AbstractFragment<T extends SimpleBean, P> extends Fragment implements SimpleBeanViewHolder.HolderClickListener<T>{

    public static final String PARAMETER = "parameter";
    protected RecyclerView recyclerView;
    protected SimpleBeanRecyclerViewAdapter<? extends SimpleBean> adapter;

    public abstract String createTitle();
    public abstract String createSubTitle();
    public abstract AbstractFragment fragmentAnterior();
    public abstract void createAdapter(RecyclerView recyclerView);

    protected P parameter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            parameter = (P)getArguments().getSerializable(PARAMETER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_simple_bean, container, false);

        Context context = view.getContext();
        recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        createAdapter(recyclerView);

        return view;
    }

    public void orderList(String query) {
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
