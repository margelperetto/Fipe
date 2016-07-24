package br.net.twome.fipe.service;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import br.net.twome.fipe.business.Marca;
import br.net.twome.fipe.business.Modelo;
import br.net.twome.fipe.business.Preco;
import br.net.twome.fipe.business.Tipo;
import br.net.twome.fipe.business.Veiculo;

public class FipeService {

    private static final String TAG = "FIPE";
    private static final String BASE_URL = "http://fipeapi.appspot.com/api/1/";

    public void getMarcas(final Tipo tipo, final ServiceCallback<List<Marca>> callback) {
        new AsyncTask<Void, Void, List<Marca>>() {
            //GET: http://fipeapi.appspot.com/api/1/carros/marcas.json
            @Override
            protected List<Marca> doInBackground(Void... params) {
                try {
                    JSONArray result = new JSONArray(getStringFromUrl(BASE_URL + tipo.getTipo()+"/marcas.json"));
                    JSONObject obj;
                    List<Marca> marcas = new ArrayList<Marca>(result.length());
                    for (int i=0; i<result.length(); i++) {
                        obj = result.getJSONObject(i);
                        marcas.add(new Marca(obj.getString("id"), obj.getString("name"), tipo));
                    }
                    return marcas;
                }catch (Exception e) {
                    Log.e(TAG, e.getMessage(), e);
                    return null;
                }
            }

            @Override
            protected void onPostExecute(List<Marca> marcas) {
                callback.onSuccess(marcas);
            }
        }.execute();
    }

    public void getVeiculos(final Marca marca, final ServiceCallback<List<Veiculo>> callback) {
        new AsyncTask<Void, Void, List<Veiculo>>() {
            //GET: http://fipeapi.appspot.com/api/1/carros/veiculos/21.json
            @Override
            protected List<Veiculo> doInBackground(Void... params) {
                try {
                    JSONArray result = new JSONArray(getStringFromUrl(BASE_URL + "/"+marca.getTipo().getTipo()+"/veiculos/"+marca.getId()+".json"));
                    JSONObject obj;
                    List<Veiculo> veiculos = new ArrayList<Veiculo>(result.length());
                    for (int i=0; i<result.length(); i++) {
                        obj = result.getJSONObject(i);
                        veiculos.add(new Veiculo(obj.getString("id"), obj.getString("name"), marca));
                    }
                    return veiculos;
                }catch (Exception e) {
                    Log.e(TAG, e.getMessage(), e);
                    return null;
                }
            }

            @Override
            protected void onPostExecute(List<Veiculo> veiculos) {
                callback.onSuccess(veiculos);
            }
        }.execute();
    }

    public void getModelos(final Veiculo veiculo, final ServiceCallback<List<Modelo>> callback) {
        new AsyncTask<Void, Void, List<Modelo>>() {
            //GET: http://fipeapi.appspot.com/api/1/carros/veiculo/21/4828.json
            @Override
            protected List<Modelo> doInBackground(Void... params) {
                try {
                    JSONArray result = new JSONArray(getStringFromUrl(BASE_URL + "/"+veiculo.getMarca().getTipo().getTipo()+"/veiculo/"+
                            veiculo.getMarca().getId()+"/"+veiculo.getId()+".json"));
                    JSONObject obj;
                    List<Modelo> modelos = new ArrayList<Modelo>(result.length());
                    for (int i=0; i<result.length(); i++) {
                        obj = result.getJSONObject(i);
                        modelos.add(new Modelo(obj.getString("id"), obj.getString("name"), veiculo));
                    }
                    return modelos;
                }catch (Exception e) {
                    Log.e(TAG, e.getMessage(), e);
                    return null;
                }
            }

            @Override
            protected void onPostExecute(List<Modelo> modelos) {
                callback.onSuccess(modelos);
            }
        }.execute();
    }

    public void getPreco(final Modelo modelo, final ServiceCallback<List<Preco>> callback) {
        new AsyncTask<Void, Void, List<Preco>>() {
            //GET: http://fipeapi.appspot.com/api/1/carros/veiculo/21/4828/2013-1.json
            @Override
            protected List<Preco> doInBackground(Void... params) {
                try {
                    JSONObject obj = new JSONObject(getStringFromUrl(BASE_URL + "/"+modelo.getVeiculo().getMarca().getTipo().getTipo()+
                            "/veiculo/"+modelo.getVeiculo().getMarca().getId()+"/"+modelo.getVeiculo().getId()+"/"+modelo.getId()+".json"));

                    Preco preco = null;
                    List<Preco> precos = new ArrayList<>(1);
                    if (obj!=null) {
                        preco = new Preco();
                        preco.setModelo(modelo);

                        preco.setId(obj.getString("id"));
                        preco.setName(obj.getString("preco"));

                        precos.add(preco);
                    }
                    return precos;
                }catch (Exception e) {
                    Log.e(TAG, e.getMessage(), e);
                    return null;
                }
            }

            @Override
            protected void onPostExecute(List<Preco> precos) {
                callback.onSuccess(precos);
            }
        }.execute();
    }

    private String getStringFromUrl(String urlStr) throws Exception {

        URL url = new URL(urlStr);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("GET");

        StringBuilder result = new StringBuilder();
        BufferedReader rd = new BufferedReader(new InputStreamReader(http.getInputStream(), Charset.forName("utf-8")));
        try {
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
        }finally {
            if (rd != null) {
                rd.close();
            }
        }
        return result.toString();
    }
}