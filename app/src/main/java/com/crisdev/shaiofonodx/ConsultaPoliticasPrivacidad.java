        package com.crisdev.shaiofonodx;

        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.net.Uri;
        import android.os.Bundle;
        import android.view.View;
        import android.view.WindowManager;
        import android.widget.Button;
        import android.widget.RadioButton;
        import android.widget.Toast;

        import androidx.appcompat.app.AppCompatActivity;

        public class ConsultaPoliticasPrivacidad extends AppCompatActivity implements View.OnClickListener{


            static Button VERWEB;

            static Button CONTINUAR;
            static RadioButton ACEPTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consulta_politicas_privacidad);

        //CODIGO PARA ESCONDER EL STATUS BAR
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //CODIGO PARA ESCONDER EL STATUS BAR

        ACEPTO = findViewById(R.id.acepto);

        CONTINUAR = findViewById(R.id.continuar);
        CONTINUAR.setOnClickListener(this);


        VERWEB= findViewById(R.id.verweb);
        VERWEB.setOnClickListener(new View.OnClickListener()
                                  {

                                      @Override
                                      public void onClick(View v)
                                      {

                                          // TODO Auto-generated method stub
                                       /*   Intent in=new Intent(Intent.ACTION_VIEW, Uri.parse("https://sites.google.com/view/cepesorp/p%C3%A1gina-principal"));
                                          startActivity(in);*/

                                      }
                                  }
        );

    }



    @Override
    public void onClick(View v)
    {

        switch (v.getId())

        {
            case R.id.continuar:

                if     ( !ACEPTO.isChecked()  )

                {
                    Toast.makeText(getApplicationContext(), "Lea la Política de Privacidad y de Click en Aceptar solo si esta de acuerdo", Toast.LENGTH_SHORT).show();
                }

                else
                {

                    //  E D I T A R   S H A R E D   P R E F E R E N C E S
                    SharedPreferences primeravez = getSharedPreferences("primeravez",MODE_PRIVATE);
                    SharedPreferences.Editor editor = primeravez.edit();
                    editor.putInt("primeravez", 200);
                    editor.apply();
                    //  E D I T A R   S H A R E D   P R E F E R E N C E S


                    //  E D I T A R   S H A R E D   P R E F E R E N C E S
                    SharedPreferences privacidad = getSharedPreferences("privacidad",MODE_PRIVATE);
                    SharedPreferences.Editor edito = privacidad.edit();
                    edito.putInt("privacidad", 200);
                    edito.apply();
                    //  E D I T A R   S H A R E D   P R E F E R E N C E S

                    //  E D I T A R   S H A R E D   P R E F E R E N C E S
                    SharedPreferences privacidadcambiada = getSharedPreferences("privacidadcambiada",MODE_PRIVATE);
                    SharedPreferences.Editor edit = privacidadcambiada.edit();
                    edit.putInt("privacidadcambiada", 200);
                    edit.apply();
                    //  E D I T A R   S H A R E D   P R E F E R E N C E S

                    Intent i= new  Intent(getApplicationContext(), Preview_Logica.class);
                    startActivity(i);
                }
                break;
            default:
                break;
        }
    }
}
