package br.net.twome.fipe.business;

public class Veiculo extends SimpleBean{

    private Marca marca;

    public Veiculo() {}

    public Veiculo(String id, String name, Marca marca) {
        this.id = id;
        this.name = name;
        this.marca = marca;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }
}