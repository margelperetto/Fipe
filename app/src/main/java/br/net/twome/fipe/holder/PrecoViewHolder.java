package br.net.twome.fipe.holder;

import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import br.net.twome.fipe.R;
import br.net.twome.fipe.business.Preco;
import br.net.twome.fipe.business.Tipo;

public class PrecoViewHolder extends SimpleBeanViewHolder<Preco>{

    public final TextView tvPreco;
    public final TextView tvReferencia;
    public final TextView tvCodigoFipe;
    public final TextView tvMarca;
    public final TextView tvCombustivel;
    public final TextView tvAno;

    public PrecoViewHolder(View view) {
        super(view);
        tvPreco = (TextView) view.findViewById(R.id.tvPreco);
        tvReferencia = (TextView) view.findViewById(R.id.tvReferencia);
        tvCodigoFipe = (TextView) view.findViewById(R.id.tvCodigoFipe);
        tvCombustivel = (TextView) view.findViewById(R.id.tvCombustivel);
        tvMarca = (TextView) view.findViewById(R.id.tvMarca);
        tvAno = (TextView) view.findViewById(R.id.tvAno);
    }

    public PrecoViewHolder(LayoutInflater inflater, ViewGroup parent) {
        this(inflater.inflate(R.layout.item_preco, parent, false));
    }

    @Override
    public void onBindViewHolder(Preco item) {
        super.onBindViewHolder(item);
        tvPreco.setText(item.getPreco());
        tvReferencia.setText(item.getReferencia());
        tvCodigoFipe.setText(item.getFipeCodigo());
        tvMarca.setText(item.getMarca());
        tvCombustivel.setText(item.getCombustivel());
        tvAno.setText(item.getAnoModelo()>10000?"ZERO KM":item.getAnoModelo()+"");
    }
}
