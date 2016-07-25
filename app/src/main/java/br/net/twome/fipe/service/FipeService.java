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

import br.net.twome.fipe.business.Marca;
import br.net.twome.fipe.business.Modelo;
import br.net.twome.fipe.business.Preco;
import br.net.twome.fipe.business.Tipo;
import br.net.twome.fipe.business.Veiculo;

public class FipeService {

    private static final String TAG = "FIPE";
    private static final String BASE_URL = "http://fipeapi.appspot.com/api/1/";

    public void getMarcas(final Tipo tipo, final ServiceCallback<ArrayList<Marca>> callback) {
        new AsyncTask<Void, Void, ArrayList<Marca>>() {
            //GET: http://fipeapi.appspot.com/api/1/carros/marcas.json
            @Override
            protected ArrayList<Marca> doInBackground(Void... params) {
                try {
                    JSONArray result = new JSONArray(getStringFromUrl(BASE_URL + tipo.getTipo()+"/marcas.json"));
                    JSONObject obj;
                    ArrayList<Marca> marcas = new ArrayList<>(result.length());
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
            protected void onPostExecute(ArrayList<Marca> marcas) {
                callback.onSuccess(marcas);
            }
        }.execute();
    }

    public void getVeiculos(final Marca marca, final ServiceCallback<ArrayList<Veiculo>> callback) {
        new AsyncTask<Void, Void, ArrayList<Veiculo>>() {
            //GET: http://fipeapi.appspot.com/api/1/carros/veiculos/21.json
            @Override
            protected ArrayList<Veiculo> doInBackground(Void... params) {
                try {
                    JSONArray result = new JSONArray(getStringFromUrl(BASE_URL + "/"+marca.getTipo().getTipo()+"/veiculos/"+marca.getId()+".json"));
                    JSONObject obj;
                    ArrayList<Veiculo> veiculos = new ArrayList<>(result.length());
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
            protected void onPostExecute(ArrayList<Veiculo> veiculos) {
                callback.onSuccess(veiculos);
            }
        }.execute();
    }

    public void getModelos(final Veiculo veiculo, final ServiceCallback<ArrayList<Modelo>> callback) {
        new AsyncTask<Void, Void, ArrayList<Modelo>>() {
            //GET: http://fipeapi.appspot.com/api/1/carros/veiculo/21/4828.json
            @Override
            protected ArrayList<Modelo> doInBackground(Void... params) {
                try {
                    JSONArray result = new JSONArray(getStringFromUrl(BASE_URL + "/"+veiculo.getMarca().getTipo().getTipo()+"/veiculo/"+
                            veiculo.getMarca().getId()+"/"+veiculo.getId()+".json"));
                    JSONObject obj;
                    ArrayList<Modelo> modelos = new ArrayList<>(result.length());
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
            protected void onPostExecute(ArrayList<Modelo> modelos) {
                callback.onSuccess(modelos);
            }
        }.execute();
    }

    public void getPreco(final Modelo modelo, final ServiceCallback<ArrayList<Preco>> callback) {
        new AsyncTask<Void, Void, ArrayList<Preco>>() {
            //GET: http://fipeapi.appspot.com/api/1/carros/veiculo/21/4828/2013-1.json
            @Override
            protected ArrayList<Preco> doInBackground(Void... params) {
                try {
                    JSONObject obj = new JSONObject(getStringFromUrl(BASE_URL + "/"+modelo.getVeiculo().getMarca().getTipo().getTipo()+
                            "/veiculo/"+modelo.getVeiculo().getMarca().getId()+"/"+modelo.getVeiculo().getId()+"/"+modelo.getId()+".json"));

                    ArrayList<Preco> precos = new ArrayList<>(1);

                    Preco preco = new Preco();
                    preco.setModelo(modelo);

                    preco.setId(obj.getString("id"));
                    preco.setName(obj.getString("preco"));

                    precos.add(preco);

                    return precos;
                }catch (Exception e) {
                    Log.e(TAG, e.getMessage(), e);
                    return null;
                }
            }

            @Override
            protected void onPostExecute(ArrayList<Preco> precos) {
                callback.onSuccess(precos);
            }
        }.execute();
    }

    private String getStringFromUrl(String urlStr) throws Exception {

        URL url = new URL(urlStr);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("GET");

        StringBuilder result = new StringBuilder();
        BufferedReader rd = null;
        try {
            rd = new BufferedReader(new InputStreamReader(http.getInputStream(), Charset.forName("utf-8")));
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