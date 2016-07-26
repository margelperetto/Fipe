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

import br.net.twome.fipe.business.SimpleBean;

public abstract class FipeAsyncTask <T extends SimpleBean> extends AsyncTask<Void, Void, ArrayList<T>> {

    private ServiceCallback<ArrayList<T>> callback;

    public abstract String createURL();
    public abstract T createBean(JSONObject obj) throws Exception;

    public FipeAsyncTask( ServiceCallback<ArrayList<T>> callback) {
        this.callback = callback;
    }

    @Override
    protected ArrayList<T> doInBackground(Void... params) {
        try {
            String jsonStr = getStringFromUrl(createURL());

            ArrayList<T> list = null;
            if(jsonStr.startsWith("[")) {
                JSONArray result = new JSONArray(jsonStr);
                list = new ArrayList<>(result.length());
                for (int i = 0; i < result.length(); i++) {
                    list.add(createBean(result.getJSONObject(i)));
                }
            }else{
                list = new ArrayList<>(1);
                list.add(createBean(new JSONObject(jsonStr)));
            }
            return list;
        }catch (Exception e) {
            Log.e(getClass().getSimpleName(), e.getMessage(), e);
            return null;
        }
    }

    @Override
    protected void onPostExecute(ArrayList<T> modelos) {
        callback.onSuccess(modelos);
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