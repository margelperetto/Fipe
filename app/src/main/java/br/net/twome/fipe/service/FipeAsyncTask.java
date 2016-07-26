package br.net.twome.fipe.service;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
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
import br.net.twome.fipe.view.MainActivity;

public abstract class FipeAsyncTask <T extends SimpleBean> extends AsyncTask<Void, Void, ArrayList<T>> {

    private ServiceCallback<ArrayList<T>> callback;
    private ProgressDialog dialog;
    private MainActivity activity;

    public abstract String createURL();
    public abstract T createBean(JSONObject obj) throws Exception;

    public FipeAsyncTask(MainActivity activity, ServiceCallback<ArrayList<T>> callback) {
        this.callback = callback;
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        dialog = ProgressDialog.show(activity, "", "Carregando. Aguarde...", true);
    }

    @Override
    protected ArrayList<T> doInBackground(Void... params) {
        String jsonStr = "";
        try {
            jsonStr = getStringFromUrl(createURL());

            ArrayList<T> list;
            if(jsonStr.trim().startsWith("[")) {
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
        }catch (Throwable e) {
            Log.e(getClass().getSimpleName(), e.getMessage(), e);
            Log.d(getClass().getSimpleName(), "JSON: "+jsonStr);
            return null;
        }
    }

    @Override
    protected void onPostExecute(ArrayList<T> modelos) {
        dialog.cancel();
        if(modelos==null){
            AlertDialog.Builder builder = new AlertDialog.Builder(activity)
                    .setTitle("Ops...")
                    .setMessage("Não foi possível conectar. Tudo certo com a sua internet?")
                    .setCancelable(true);

            AlertDialog alert = builder.create();
            alert.show();
            modelos = new ArrayList<>();
            activity.onBackPressed();
        }
        callback.onSuccess(modelos);
    }

    private String getStringFromUrl(String urlStr) throws Throwable {

        URL url = new URL(urlStr);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setConnectTimeout(15000);
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