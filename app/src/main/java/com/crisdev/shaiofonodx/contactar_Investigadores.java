package com.crisdev.shaiofonodx;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

public class contactar_Investigadores extends AppCompatActivity {

    Button CONTACTAR;

    @Override
    protected void onCreate(Bundle savedInstanceState)

    {
        //CODIGO PARA ESCONDER EL STATUS BAR
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //CODIGO PARA ESCONDER EL STATUS BAR

        super.onCreate(savedInstanceState);
        setContentView(R.layout.contactar_investigadores);



        CONTACTAR     =  findViewById(R.id.contactar);
        CONTACTAR.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                EnviarMensajeWhatsapp();
            }
        });
    }

    private void EnviarMensajeWhatsapp()
    {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=" + "573115929176"
                    + "&text=" + "Hola, requiero información acerca de FonoDx."));
            startActivity(intent);
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Whatsapp error", Toast.LENGTH_SHORT).show();
        }
    }
}
