package br.net.twome.fipe.business;

public class Preco extends SimpleBean{

    private Modelo modelo;
    private int anoModelo;
    private String marca;
    private String preco;
    private String combustivel;
    private String referencia;
    private String fipeCodigo;
    private String key;

    public Preco() {}

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public int getAnoModelo() {
        return anoModelo;
    }

    public void setAnoModelo(int anoModelo) {
        this.anoModelo = anoModelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public String getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(String combustivel) {
        this.combustivel = combustivel;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getFipeCodigo() {
        return fipeCodigo;
    }

    public void setFipeCodigo(String fipeCodigo) {
        this.fipeCodigo = fipeCodigo;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public boolean isZeroKm() {
        return anoModelo>10000;
    }
}