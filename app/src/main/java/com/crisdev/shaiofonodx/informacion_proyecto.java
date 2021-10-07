package com.crisdev.shaiofonodx;


import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class informacion_proyecto extends AppCompatActivity implements View.OnClickListener

{
    static Button CV1,CV2,CV3;

    static Button FORMULA, BACK;
    static Button CERRAR, INFO;
    static Dialog MyDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        //CODIGO PARA ESCONDER EL STATUS BAR
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //CODIGO PARA ESCONDER EL STATUS BAR

        super.onCreate(savedInstanceState);
        setContentView(R.layout.informacion_proyecto);





    }





    @Override
    public void onClick(View v)
    {
        switch (v.getId())

        {

/*
            case R.id.cv1:

                Toast.makeText(getApplicationContext(), "Redirigiendo a enlace Colciencias", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("http://scienti.colciencias.gov.co:8081/cvlac/visualizador/generarCurriculoCv.do?cod_rh=0001347072"));
                startActivity(i);
                break;

            case R.id.cv2:

                Toast.makeText(getApplicationContext(), "Redirigiendo a enlace Colciencias", Toast.LENGTH_SHORT).show();
                Intent in=new Intent(Intent.ACTION_VIEW, Uri.parse("http://scienti.colciencias.gov.co:8081/cvlac/visualizador/generarCurriculoCv.do?cod_rh=0000201014"));
                startActivity(in);
                break;

            case R.id.cv3:

                Toast.makeText(getApplicationContext(), "Redirigiendo a enlace Colciencias", Toast.LENGTH_SHORT).show();
                Intent ipp=new Intent(Intent.ACTION_VIEW, Uri.parse("https://scienti.minciencias.gov.co/cvlac/visualizador/generarCurriculoCv.do?cod_rh=0001506981"));
                startActivity(ipp);
                break;
*/



            default:
                break;
                        }
    }
}
