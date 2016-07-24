package br.net.twome.fipe.business;

import java.io.Serializable;

import br.net.twome.fipe.R;

public enum Tipo implements Serializable{

    CARRO("Carros", "carros", R.drawable.ic_directions_car_black_48dp),
    MOTO("Motos", "motos",R.drawable.ic_motorcycle_black_48dp),
    CAMINHAO("Caminhões", "caminhoes",R.drawable.ic_local_shipping_black_48dp)
    ;

    private String descricao;
    private String tipo;
    private int icon;

    private Tipo(String descricao, String tipo, int icon) {
        this.descricao = descricao;
        this.tipo = tipo;
        this.icon = icon;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getIcon() {
        return icon;
    }

    @Override
    public String toString() {
        return descricao;
    }
}