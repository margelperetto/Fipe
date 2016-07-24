package br.net.twome.fipe.business;

public class Marca extends SimpleBean{

    private Tipo tipo;

    public Marca() {}

    public Marca(String id, String name, Tipo tipo) {
        this.id = id;
        this.name = name;
        this.tipo = tipo;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
}