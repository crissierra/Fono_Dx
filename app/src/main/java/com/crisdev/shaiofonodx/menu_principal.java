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
    Button INFO_PROYECTO,SPECTROGRAMA, CREAR_USUARIO, CONTACTAR;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        //CODIGO PARA ESCONDER EL STATUS BAR
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //CODIGO PARA ESCONDER EL STATUS BAR

        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_principal);

        INFO_PROYECTO =  findViewById(R.id.informacion_proyecto);
        SPECTROGRAMA =  findViewById(R.id.spectrograma);
        CREAR_USUARIO =  findViewById(R.id.crear_usuario);
        CONTACTAR =  findViewById(R.id.contactar);

        INFO_PROYECTO.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent (getApplicationContext(), informacion_proyecto.class);
                startActivity(intent);
            }
        });


        SPECTROGRAMA.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent (getApplicationContext(), Spectro_MainActivity.class);
                startActivity(intent);
            }
        });
        CREAR_USUARIO.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent (getApplicationContext(), CrearUsuario.class);
                startActivity(intent);
            }
        });

        CONTACTAR.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent (getApplicationContext(), CrearUsuario.class);
                startActivity(intent);
            }
        });
    }

    public void onResume()
    {
        super.onResume();
    }
}
