package br.net.twome.fipe.business;

public class Modelo extends SimpleBean{

    private Veiculo veiculo;

    public Modelo() {}

    public Modelo(String id, String name, Veiculo veiculo) {
        this.id = id;
        this.name = name;
        this.veiculo = veiculo;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
}