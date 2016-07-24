package br.net.twome.fipe.utils;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;

public class Android {

    public static void finishDialog(final Activity activity) {
        Log.d("END","Chamou finishDialog");
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Sair")
                .setMessage("Tem certeza que deseja sair?")
                .setCancelable(false)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        activity.finish();
                    }
                })
                .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
