package br.net.twome.fipe.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.io.Serializable;
import java.util.ArrayList;
import br.net.twome.fipe.R;
import br.net.twome.fipe.adapter.SimpleBeanRecyclerViewAdapter;
import br.net.twome.fipe.holder.SimpleBeanViewHolder;
import br.net.twome.fipe.business.SimpleBean;

public abstract class AbstractFragment<T extends SimpleBean, P extends Serializable> extends Fragment implements SimpleBeanViewHolder.HolderClickListener<T>{

    public static final String PARAMETER = "parameter";
    public static final String DATA = "data";

    private RecyclerView recyclerView;
    private SimpleBeanRecyclerViewAdapter<T> adapter;

    protected P parameter;
    private ArrayList<T> data;

    public abstract String createTitle();
    public abstract String createSubTitle();
    public abstract void createData(SimpleBeanRecyclerViewAdapter<T> adapter);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState!=null){
            parameter = (P)savedInstanceState.getSerializable(PARAMETER);
            data = (ArrayList<T>) savedInstanceState.getSerializable(DATA);

            Log.d(getClass().getSimpleName()," RESTORED SAVED INSTANCE - Parameter: "+parameter+" | DATA: "+(data==null?"NULL":data.size()));
        }else if (getArguments() != null) {
            parameter = (P)getArguments().getSerializable(PARAMETER);

            Log.d(getClass().getSimpleName()," ARGUMENTS - Parameter: "+parameter);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable(PARAMETER, parameter);
        outState.putSerializable(DATA, data);

        Log.d(getClass().getSimpleName()," INSTANCE SAVED - Parameter: "+parameter+" | DATA: "+(data==null?"NULL":data.size()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(enableBack());
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(enableBack());

        if(recyclerView!=null && adapter!=null){
            Log.d(getClass().getSimpleName()," onCreateView with components instances");
            return recyclerView;
        }
        Log.d(getClass().getSimpleName()," Creating components in onCreateView ");

        recyclerView = (RecyclerView) inflater.inflate(R.layout.recycler_simple_bean, container, false);;
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));

        adapter = createEmptyAdapter();
        recyclerView.setAdapter(adapter);

        if(data == null){
            createData(adapter);
        }else{
            adapter.setData(data);
        }

        return recyclerView;
    }

    protected boolean enableBack() {
        return true;
    }

    protected SimpleBeanRecyclerViewAdapter<T> createEmptyAdapter(){
        return new SimpleBeanRecyclerViewAdapter<T>(new ArrayList<T>(), this){
            @Override
            public void setData(ArrayList data) {
                super.setData(data);
                AbstractFragment.this.data = data;
            }
        };
    }

    public void orderList(String query) {
        recyclerView.scrollToPosition(0);
        if(adapter != null){
            adapter.orderBy(query);
            this.data = adapter.getData();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity)getActivity()).getSupportActionBar().setTitle(createTitle());
        ((MainActivity)getActivity()).getSupportActionBar().setSubtitle(createSubTitle());
    }

}
