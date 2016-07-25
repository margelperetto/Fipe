package br.net.twome.fipe.business;

import br.net.twome.fipe.R;

public class Tipo extends SimpleBean{

    public static final Tipo[] TIPOS = {
            new Tipo("Carros", "carros", R.drawable.ic_directions_car_black_48dp),
            new Tipo("Motos", "motos", R.drawable.ic_motorcycle_black_48dp),
            new Tipo("Caminhões", "caminhoes", R.drawable.ic_local_shipping_black_48dp)
    };

    private String tipo;
    private int icon;

    private Tipo(String name, String tipo, int icon) {
        this.name = name;
        this.tipo = tipo;
        this.icon = icon;
    }

    public String getTipo() {
        return tipo;
    }

    public int getIcon() {
        return icon;
    }
}