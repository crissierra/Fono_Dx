package com.crisdev.shaiofonodx;

import android.app.Activity;
import android.view.LayoutInflater;

import androidx.appcompat.app.AlertDialog;


public class LoadingDialog
{

    private Activity activity;
    private AlertDialog dialog;

    LoadingDialog(Activity myActivity)
    {
        activity = myActivity;
    }


    void Inicia_Progress_Mesage()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.loading_mesage, null));
        builder.setCancelable(true);

        dialog = builder.create();
        dialog.show();
    }

    void Terminar_Progress_Mesage()
    {
        dialog.dismiss();
    }

}
