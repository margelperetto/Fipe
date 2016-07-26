package br.net.twome.fipe.service;

import org.json.JSONObject;
import java.util.ArrayList;
import br.net.twome.fipe.business.Marca;
import br.net.twome.fipe.business.Modelo;
import br.net.twome.fipe.business.Preco;
import br.net.twome.fipe.business.Tipo;
import br.net.twome.fipe.business.Veiculo;

public class FipeService {

    private static final String BASE_URL = "http://fipeapi.appspot.com/api/1/";

    public void getMarcas(final Tipo tipo, final ServiceCallback<ArrayList<Marca>> callback) {

        new FipeAsyncTask<Marca>(callback){
            @Override
            public String createURL() {
                return BASE_URL + tipo.getTipo()+"/marcas.json";
            }
            @Override
            public Marca createBean(JSONObject obj) throws Exception {
                return new Marca(obj.getString("id"), obj.getString("name"), tipo);
            }
        }.execute();
    }

    public void getVeiculos(final Marca marca, final ServiceCallback<ArrayList<Veiculo>> callback) {

        new FipeAsyncTask<Veiculo>(callback){
            @Override
            public String createURL() {
                return BASE_URL + "/"+marca.getTipo().getTipo()+"/veiculos/"+marca.getId()+".json";
            }
            @Override
            public Veiculo createBean(JSONObject obj) throws Exception {
                return new Veiculo(obj.getString("id"), obj.getString("name"), marca);
            }
        }.execute();
    }

    public void getModelos(final Veiculo veiculo, final ServiceCallback<ArrayList<Modelo>> callback) {

        new FipeAsyncTask<Modelo>(callback){
            @Override
            public String createURL() {
                return BASE_URL + "/"+veiculo.getMarca().getTipo().getTipo()+"/veiculo/"+
                        veiculo.getMarca().getId()+"/"+veiculo.getId()+".json";
            }
            @Override
            public Modelo createBean(JSONObject obj) throws Exception{
                return new Modelo(obj.getString("id"), obj.getString("name"), veiculo);
            }
        }.execute();
    }

    public void getPreco(final Modelo modelo, final ServiceCallback<ArrayList<Preco>> callback) {

        new FipeAsyncTask<Preco>(callback){
            @Override
            public String createURL() {
                return BASE_URL + "/"+modelo.getVeiculo().getMarca().getTipo().getTipo()+"/veiculo/"+
                        modelo.getVeiculo().getMarca().getId()+"/"+modelo.getVeiculo().getId()+"/"+modelo.getId()+".json";
            }
            @Override
            public Preco createBean(JSONObject obj) throws Exception {
                Preco preco = new Preco();
                preco.setModelo(modelo);

                preco.setId(obj.getString("id"));
                preco.setName(obj.getString("preco"));
                return preco;
            }
        }.execute();
    }

}