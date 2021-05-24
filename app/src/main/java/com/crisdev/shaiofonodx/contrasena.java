package com.crisdev.shaiofonodx;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tapadoo.alerter.Alerter;


public class contrasena extends AppCompatActivity

{

    private EditText CLAVE;
    static Button VALIDAR;
    static Toast m;

    final int DEFAULT = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState)
        {
            //CODIGO PARA ESCONDER EL STATUS BAR
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            //CODIGO PARA ESCONDER EL STATUS BAR

            super.onCreate(savedInstanceState);
            setContentView(R.layout.contrasena);

            CLAVE=findViewById(R.id.clave);
            VALIDAR=findViewById(R.id.validar);


            VALIDAR.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    LoginUsuario();
                }
            });

        }

    @Override
    public void onBackPressed()
    {

    }

    private void LoginUsuario()
        {

            final String password = CLAVE.getText().toString();


                if   ( password.equals("Shaio") )
                               {
                                  Intent i=new  Intent(getApplicationContext(), ConsultaPoliticasPrivacidad.class);
                                  startActivity(i);

                                   //  E D I T A R   S H A R E D   P R E F E R E N C E S
                                   SharedPreferences primeravezcambiada = getSharedPreferences("primeravezcambiada",MODE_PRIVATE);
                                   SharedPreferences.Editor editor = primeravezcambiada.edit();
                                   editor.putInt("primeravezcambiada", 200);
                                   editor.apply();
                                   //  E D I T A R   S H A R E D   P R E F E R E N C E S


                               }

                else if ( password.equals("Maria") )
                {

                    Intent i=new  Intent(getApplicationContext(), menu_principal.class);
                    startActivity(i);

                    //  E D I T A R   S H A R E D   P R E F E R E N C E S
                    SharedPreferences primeravezcambiada = getSharedPreferences("primeravezcambiada",MODE_PRIVATE);
                    SharedPreferences.Editor editor = primeravezcambiada.edit();
                    editor.putInt("primeravezcambiada", 200);
                    editor.apply();
                    //  E D I T A R   S H A R E D   P R E F E R E N C E S


                    //  E D I T A R   S H A R E D   P R E F E R E N C E S
                    SharedPreferences privacidadcambiada = getSharedPreferences("privacidadcambiada",MODE_PRIVATE);
                    SharedPreferences.Editor edito = privacidadcambiada.edit();
                    edito.putInt("privacidadcambiada", 200);
                    edito.apply();
                    //  E D I T A R   S H A R E D   P R E F E R E N C E S
                }

                 else {

                    // ALERT
                    if (Alerter.isShowing()) {   Alerter.hide(); } Alerter.create(this)
                            .setTitle("¡Clave incorrecta!")
                            .setText("Comunícate con la encargada del Proyecto:\n Maria Lucía Arango.")
                            .setBackgroundColorRes(R.color.coloralert)
                            .setDuration(6000)
                            .show();
                    // ALERT

                         /* Toast m = Toast.makeText(getApplicationContext(), "                     Contraseña incorrecta.\n Comunícate con la encargada del Proyecto.\n Maria Inés Mantilla", Toast.LENGTH_LONG);
                          m.show();*/
                      }

        }

}




