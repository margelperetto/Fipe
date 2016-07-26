package br.net.twome.fipe.holder;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.net.twome.fipe.R;
import br.net.twome.fipe.business.SimpleBean;

public class SimpleBeanViewHolder<T extends SimpleBean> extends RecyclerView.ViewHolder {

    public final View mView;
    public final TextView mContentView;
    public T mItem;
    private HolderClickListener<T> listener;

    public SimpleBeanViewHolder(View view) {
        super(view);
        mView = view;
        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener!=null){
                    listener.onClick(mItem);
                }
            }
        });
        mContentView = (TextView) view.findViewById(R.id.content);
    }

    public SimpleBeanViewHolder(LayoutInflater inflater, ViewGroup parent) {
        this(inflater.inflate(R.layout.item_simple_bean, parent, false));
    }

    public void onBindViewHolder(T item){
        mItem = item;
        mContentView.setText(item.getName());
    }

    public T getSimpleBean() {
        return mItem;
    }

    public HolderClickListener<T> getListener() {
        return listener;
    }

    public void setListener(HolderClickListener<T> listener) {
        this.listener = listener;
    }

    public static interface HolderClickListener<T extends  SimpleBean>{
        public void onClick(T obj);
    }
}
