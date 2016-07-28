package br.net.twome.fipe.holder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import br.net.twome.fipe.R;
import br.net.twome.fipe.business.Preco;
import im.delight.android.webview.AdvancedWebView;

public class PrecoViewHolder extends SimpleBeanViewHolder<Preco>{

    private static final String GOOGLE_IMG_URL = "https://www.google.com/search?site=&tbm=isch&q=";

    public final TextView tvPreco;
    public final TextView tvReferencia;
    public final TextView tvCodigoFipe;
    public final TextView tvMarca;
    public final TextView tvCombustivel;
    public final TextView tvAno;
    private AdvancedWebView mWebView;
    private String imgUrl;

    public PrecoViewHolder(View view) {
        super(view);
        tvPreco = (TextView) view.findViewById(R.id.tvPreco);
        tvReferencia = (TextView) view.findViewById(R.id.tvReferencia);
        tvCodigoFipe = (TextView) view.findViewById(R.id.tvCodigoFipe);
        tvCombustivel = (TextView) view.findViewById(R.id.tvCombustivel);
        tvMarca = (TextView) view.findViewById(R.id.tvMarca);
        tvAno = (TextView) view.findViewById(R.id.tvAno);
        mWebView = (AdvancedWebView) view.findViewById(R.id.webview);
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
        tvAno.setText(item.isZeroKm()?"ZERO KM":item.getAnoModelo()+"");
        imgUrl = GOOGLE_IMG_URL+item.getMarca()+" "+item.getName()+(item.isZeroKm()?"":" "+item.getAnoModelo());
        mWebView.loadUrl(imgUrl);
    }
}
