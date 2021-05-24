package com.crisdev.shaiofonodx;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridLayout;
import androidx.appcompat.app.AppCompatActivity;



public class menu_principal extends AppCompatActivity
{
    private Dialog escojeJuego;

    //private FirebaseAnalytics mFirebaseAnalytics;

    GridLayout mainGrid;
    Button TEORIA, CREDITOS,SPECTROGRAMA;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        //CODIGO PARA ESCONDER EL STATUS BAR
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //CODIGO PARA ESCONDER EL STATUS BAR

        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_principal);

        TEORIA =  findViewById(R.id.teoria);
        CREDITOS =  findViewById(R.id.creditos);
        SPECTROGRAMA =  findViewById(R.id.spectrograma);

        TEORIA.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent (getApplicationContext(), integrantes.class);
                startActivity(intent);
            }
        });

        CREDITOS.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent (getApplicationContext(), integrantes.class);
                startActivity(intent);
            }
        });

        SPECTROGRAMA.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent (getApplicationContext(), integrantes.class);
                startActivity(intent);
            }
        });

    }

    public void onResume(){
        super.onResume();

    }
}
