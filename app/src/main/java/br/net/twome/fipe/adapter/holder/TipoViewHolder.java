package br.net.twome.fipe.adapter.holder;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import br.net.twome.fipe.R;
import br.net.twome.fipe.business.Tipo;
import br.net.twome.fipe.view.FragmentMarca;
import br.net.twome.fipe.view.MainActivity;

public class TipoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private MainActivity activity;
    public final View mView;
    public final ImageView ivTipo;
    public final TextView mContentView;
    public Tipo mItem;

    public TipoViewHolder(View view, MainActivity activity) {
        super(view);

        mView = view;
        this.activity = activity;
        mView.setOnClickListener(this);

        ivTipo = (ImageView) view.findViewById(R.id.iv_tipo);
        mContentView = (TextView) view.findViewById(R.id.content);
    }

    public TipoViewHolder(LayoutInflater inflater,ViewGroup parent, MainActivity activity) {
        this(inflater.inflate(R.layout.item_tipo, parent, false),activity);
    }

    public void onBindViewHolder(Tipo item){
        mItem = item;
        ivTipo.setImageDrawable(ContextCompat.getDrawable(mView.getContext(), item.getIcon()));
        mContentView.setText(item.getDescricao());
    }

    @Override
    public void onClick(View view) {
        if(mItem == null){
            return;
        }
        activity.showFragment(FragmentMarca.getInstance(mItem));
    }
}
