package com.crisdev.shaiofonodx;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;


public class SplashScreenPrincipal extends AppCompatActivity
{
    ImageView object;
    Animation anim;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreenprincipal);

        //CODIGO PARA ESCONDER EL STATUS BAR
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //CODIGO PARA ESCONDER EL STATUS BAR


        object = (ImageView) findViewById(R.id.object);

        anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_jump_from_right);
        object.startAnimation(anim);


        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {

                // CREACION DE VARIABLE SHARED PARA PASAR A CONTRASEÑA O A MENÚ
                SharedPreferences inicio =  getSharedPreferences("primeravezcambiada", Context.MODE_PRIVATE);
                int primeravezcambiada = inicio.getInt("primeravezcambiada",0);
                // RECORDAR QUE SPREF INICIA EN 0 PORQUE SE LE DA LA GANA... ASÍ LO AJUSTE EN OTRO NUMERO


                // CREACION DE VARIABLE SHARED PARA PASAR A CONTRASEÑA O A MENÚ
                SharedPreferences politicaprivacidadcambiada =  getSharedPreferences("privacidadcambiada", Context.MODE_PRIVATE);
                int privacidadcambiada = politicaprivacidadcambiada.getInt("privacidadcambiada",0);
                // RECORDAR QUE SPREF INICIA EN 0 PORQUE SE LE DA LA GANA... ASÍ LO AJUSTE EN OTRO NUMERO


                //AQUI PONER LOS CONDICIONALES PARA CONTRASEÑA O PARA ENTRAR DE UNA AL MAIN

                if (primeravezcambiada >= 150)
                {
                    if (privacidadcambiada <= 150)
                    {
                        startActivity(new Intent(getApplicationContext(), ConsultaPoliticasPrivacidad.class));
                    }
                      else
                    {
                        startActivity(new Intent(getApplicationContext(), menu_principal.class));
                    }
                }
                else{
                    startActivity(new Intent(SplashScreenPrincipal.this,contrasena.class));
                    finish();
                }
            }

        },2500);
    }
}


//1. Módulo de edición de sonido (Cortar y que no se solape la info)
//2. Solo del médico podrá ver el id del paciente.
//3. Roll de médico poner la opción para "hacer seguimiento" o no. Si hace seguimiento guardar el memoria.
