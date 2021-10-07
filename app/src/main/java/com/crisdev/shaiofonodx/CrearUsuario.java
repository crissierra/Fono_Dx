package com.crisdev.shaiofonodx;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.tapadoo.alerter.Alerter;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;


public class CrearUsuario extends AppCompatActivity implements View.OnClickListener {

    private EditText mName, mApellido, mCelular, mEmail, mPassword, mOtro ;
    static Button REGISTRAR, ASISTENCIA;
    static RadioButton AUXILIAR,ENFERMERIA, FISIO, MEDICO, INGE, ESTUDIANTE, COLOMBIA, ECUADOR, PERU, MEXICO, ARGENTINA, CHILE, EEUU, RADIO_OTRO_PAIS;
    static Spinner SpinnerPaises;


    private String name;
    private String apellido ;
    public  String nombrecompleto= name +apellido ;
    public  String otro;
    public TextInputLayout TextInputLayoutOtro;


    private String celular;
    private String email;
    private String password ;

    public String tipousuario ;
    public String pais ;


    boolean suscrip;
    Context context;

    SharedPreferences preferencias;
    static final String Name ="namekey";
    static final String Apellido ="apellidkey";



    FirebaseAuth mAuth;
    DatabaseReference HemodyDataBaseRef;
    FirebaseFirestore mFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crear_usuario);

        //CODIGO PARA ESCONDER EL STATUS BAR
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //CODIGO PARA ESCONDER EL STATUS BAR

        final LoadingDialog loadingDialog = new LoadingDialog(CrearUsuario.this);

        mAuth = FirebaseAuth.getInstance();

        HemodyDataBaseRef = FirebaseDatabase.getInstance().getReference();
        mFirestore = FirebaseFirestore.getInstance();

        mName = findViewById(R.id.name);
        mApellido = findViewById(R.id.apellido);
        mCelular = findViewById(R.id.celular);
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        mOtro = findViewById(R.id.otro);

        TextInputLayoutOtro = findViewById(R.id.textimputlayout);

        //SpinnerPaises = findViewById(R.id.spinner);

        //Radiobuttons

            ENFERMERIA = findViewById(R.id.enfermera);
            FISIO = findViewById(R.id.fisio);
            ESTUDIANTE = findViewById(R.id.estudiante);

            COLOMBIA = findViewById(R.id.colombia);
                COLOMBIA.setOnClickListener(new View.OnClickListener()
                {   @Override
                    public void onClick(View v)
                    {TextInputLayoutOtro.setVisibility(View.GONE);}});
            ECUADOR = findViewById(R.id.ecuador);
                ECUADOR.setOnClickListener(new View.OnClickListener()
                {   @Override
                public void onClick(View v)
                {TextInputLayoutOtro.setVisibility(View.GONE);}});
            PERU = findViewById(R.id.peru);
                PERU.setOnClickListener(new View.OnClickListener()
                {   @Override
                public void onClick(View v)
                {TextInputLayoutOtro.setVisibility(View.GONE);}});
            MEXICO = findViewById(R.id.mexico);
                MEXICO.setOnClickListener(new View.OnClickListener()
                {   @Override
                public void onClick(View v)
                {TextInputLayoutOtro.setVisibility(View.GONE);}});
            ARGENTINA = findViewById(R.id.argentina);
                ARGENTINA.setOnClickListener(new View.OnClickListener()
                {   @Override
                public void onClick(View v)
                {TextInputLayoutOtro.setVisibility(View.GONE);}});
            CHILE = findViewById(R.id.chile);
                CHILE.setOnClickListener(new View.OnClickListener()
                {   @Override
                public void onClick(View v)
                {TextInputLayoutOtro.setVisibility(View.GONE);}});
            EEUU = findViewById(R.id.eeuu);
                EEUU.setOnClickListener(new View.OnClickListener()
                {   @Override
                public void onClick(View v)
                {TextInputLayoutOtro.setVisibility(View.GONE);}});

            RADIO_OTRO_PAIS =findViewById(R.id.radioOtros);
            RADIO_OTRO_PAIS.setOnClickListener(new View.OnClickListener()
                {   @Override
                public void onClick(View v)
                    {TextInputLayoutOtro.setVisibility(View.VISIBLE); }});

        //Radiobuttons

        REGISTRAR = findViewById(R.id.registrar);
        REGISTRAR.setOnClickListener(this);

        ASISTENCIA = findViewById(R.id.whatsapp);
        ASISTENCIA.setOnClickListener(this);


        //IMPLEMENTACION FUNCIONAL DE SPINNER
        /*ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.paises,android.R.layout.simple_spinner_item);
        SpinnerPaises.setAdapter(adapter);

        SpinnerPaises.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String PaisCapturado= parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
            }

        });*/


        REGISTRAR.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                name = mName.getText().toString().trim();
                apellido = mApellido.getText().toString().trim();
                celular = mCelular.getText().toString().trim();
                email = mEmail.getText().toString().trim();
                password = mPassword.getText().toString().trim();
                otro = mOtro.getText().toString().trim();

                if (mName.getText().length()==0 )
                {
                    // ALERT PARA TALLA ERRADA
                    if (Alerter.isShowing()) {   Alerter.hide(); } Alerter.create(CrearUsuario.this)
                        .setTitle("¡Falta nombre!")
                        .setText("Por favor, ingresa tu nombre.")
                        .setBackgroundColorRes(R.color.coloralert)
                        .setDuration(6000)
                        .show();
                    // ALERT PARA TALLA ERRADA >>
                }

                else if   ( mApellido.getText().length()==0)
                {
                    // ALERT PARA TALLA ERRADA
                    if (Alerter.isShowing()) {   Alerter.hide(); } Alerter.create(CrearUsuario.this)
                        .setTitle("¡Falta apellido!")
                        .setText("Por favor, ingresa tu apellido.")
                        .setBackgroundColorRes(R.color.coloralert)
                        .setDuration(6000)
                        .show();
                    // ALERT PARA TALLA ERRADA >>
                }
                else if   ( mCelular.getText().length()==0)
                {
                    // ALERT PARA TALLA ERRADA
                    if (Alerter.isShowing()) {   Alerter.hide(); } Alerter.create(CrearUsuario.this)
                        .setTitle("¡Falta celular!")
                        .setText("Por favor, ingresa tu número de celular.")
                        .setBackgroundColorRes(R.color.coloralert)
                        .setDuration(6000)
                        .show();
                    // ALERT PARA TALLA ERRADA >>
                }
                else if   ( mCelular.getText().length() < 8 )
                {
                    // ALERT PARA TALLA ERRADA
                    if (Alerter.isShowing()) {   Alerter.hide(); } Alerter.create(CrearUsuario.this)
                        .setTitle("¡Celular inválido!")
                        .setText("Por favor, ingresa tu número de celular completo.")
                        .setBackgroundColorRes(R.color.coloralert)
                        .setDuration(6000)
                        .show();
                    // ALERT PARA TALLA ERRADA >>
                }
                else if   ( mEmail.getText().length()==0)
                {
                    // ALERT PARA TALLA ERRADA
                    if (Alerter.isShowing()) {   Alerter.hide(); } Alerter.create(CrearUsuario.this)
                        .setTitle("¡Falta email!")
                        .setText("Por favor, ingresa un correo electrónico")
                        .setBackgroundColorRes(R.color.coloralert)
                        .setDuration(6000)
                        .show();
                    // ALERT PARA TALLA ERRADA >>
                }
                else if   ( mPassword.getText().length()==0)
                {
                    // ALERT PARA TALLA ERRADA
                    if (Alerter.isShowing()) {   Alerter.hide(); } Alerter.create(CrearUsuario.this)
                        .setTitle("¡Falta contraseña!")
                        .setText("Por favor, ingresa una contraseña con 6 caracteres mínimo.")
                        .setBackgroundColorRes(R.color.coloralert)
                        .setDuration(6000)
                        .show();
                    // ALERT PARA TALLA ERRADA >>
                }

                else if (!AUXILIAR.isChecked() & !ENFERMERIA.isChecked() & !FISIO.isChecked() & !MEDICO.isChecked() & !INGE.isChecked() & !ESTUDIANTE.isChecked()  )
                {
                    // ALERT PARA TALLA ERRADA
                    if (Alerter.isShowing()) {   Alerter.hide(); } Alerter.create(CrearUsuario.this)
                        .setTitle("¡Falta información!")
                        .setText("Por favor, ingresa el tipo de usuario o profesión.")
                        .setBackgroundColorRes(R.color.coloralert)
                        .setDuration(6000)
                        .show();
                    // ALERT PARA TALLA ERRADA >>
                }
                else if (!COLOMBIA.isChecked() & !ECUADOR.isChecked() & !PERU.isChecked() & !MEXICO.isChecked() & !ARGENTINA.isChecked() &  !CHILE.isChecked()  & !EEUU.isChecked() &   mOtro.getText().length()==0  )
                {
                    // ALERT PARA TALLA ERRADA
                    if (Alerter.isShowing()) {   Alerter.hide(); } Alerter.create(CrearUsuario.this)
                        .setTitle("¡Falta información!")
                        .setText("Por favor, ingresa el país de conexión.")
                        .setBackgroundColorRes(R.color.coloralert)
                        .setDuration(6000)
                        .show();
                    // ALERT PARA TALLA ERRADA >>
                }
                else if (password.length() < 6 )
                {
                    // ALERT PARA TALLA ERRADA
                    if (Alerter.isShowing()) {   Alerter.hide(); } Alerter.create(CrearUsuario.this)
                        .setTitle("¡Contraseña muy corta!")
                        .setText("La contraseña debe ser mayor o igual a 6 caracteres.")
                        .setBackgroundColorRes(R.color.coloralert)
                        .setDuration(6000)
                        .show();
                    // ALERT PARA TALLA ERRADA >>
                }
                else
                    {
                        loadingDialog.Inicia_Progress_Mesage();
                        registrarUsuario(loadingDialog);
                    }
            }
        });
    }



    @Override
    protected void onStart()
    {
        super.onStart();

   /*   if (mAuth.getCurrentUser() != null)
         {
           startActivity(new Intent(CrearUsuarioFirebase.this, analitics.class));
           Toast.makeText(CrearUsuarioFirebase.this, "Pasó pot On Start", Toast.LENGTH_LONG).show();
            finish();
         }
            else
                {
                 Toast.makeText(CrearUsuarioFirebase.this, "Quedese en esta activity para capturar los datos", Toast.LENGTH_LONG).show();
                }

        super.onStart();*/

/*
      PROBAR A VER SI SIRVE ESTE PARA VER SI ESTA ACTIVO O NO LA SUSCRIPCION DE FIREBASE

        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
*/
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.whatsapp:

                EnviarMensajeWhatsapp();

                break;
            default:
          break;
        }
    }

    private void EditarSharedPRef()
    {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myDatosUsuarios",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("VariableDatos",true);
        editor.commit();
    }

    private void registrarUsuario(final LoadingDialog loadingDialog)
    {
        //mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>()
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if (task.isSuccessful())
                {
                    // CÓDIGO PARA CAPTURAR FECHA Y HORA
                    Time today=new Time(Time.getCurrentTimezone());
                    today.setToNow();
                    int diaactual = today.monthDay;
                    int mesactual = today.month+1;
                    int anoactual = today.year;
                    int hora      = today.hour;
                    int minuto    = today.minute;
                    int segundo   = today.second;

                    String mescompletado;
                    String diacompletado;
                    String horacompletado;
                    String minutocompletado;
                    String segundocompletado;

                    String datoano = Integer.toString(anoactual);

                    String datomes = Integer.toString(mesactual);
                    if ( datomes.equals("0") || datomes.equals("1") || datomes.equals("2") || datomes.equals("3") || datomes.equals("4") || datomes.equals("5") || datomes.equals("6") || datomes.equals("7") || datomes.equals("8") | datomes.equals("9") )
                    { mescompletado = "0" + datomes; }
                    else
                    { mescompletado = datomes; }

                    String datodia = Integer.toString(diaactual);
                    if ( datodia.equals("0") || datodia.equals("1") || datodia.equals("2") || datodia.equals("3") || datodia.equals("4") || datodia.equals("5") || datodia.equals("6") || datodia.equals("7") || datodia.equals("8") | datodia.equals("9") )
                    { diacompletado = "0" + datodia; }
                    else
                    { diacompletado = datodia; }

                    String datohora = Integer.toString(hora);
                    if ( datohora.equals("0") || datohora.equals("1") || datohora.equals("2") || datohora.equals("3") || datohora.equals("4") || datohora.equals("5") || datohora.equals("6") || datohora.equals("7") || datohora.equals("8") | datohora.equals("9") )
                    { horacompletado = "0" + datohora; }
                    else
                    { horacompletado = datohora; }

                    String datominuto = Integer.toString(minuto);
                    if ( datominuto.equals("0") || datominuto.equals("1") || datominuto.equals("2") || datominuto.equals("3") || datominuto.equals("4") || datominuto.equals("5") || datominuto.equals("6") || datominuto.equals("7") || datominuto.equals("8") | datominuto.equals("9") )
                    { minutocompletado = "0" + datominuto; }
                    else
                    { minutocompletado = datominuto; }

                    String datosegundo = Integer.toString(segundo);
                    if ( datosegundo.equals("0") || datosegundo.equals("1") || datosegundo.equals("2") || datosegundo.equals("3") || datosegundo.equals("4") || datosegundo.equals("5") || datosegundo.equals("6") || datosegundo.equals("7") || datosegundo.equals("8") | datosegundo.equals("9") )
                    { segundocompletado = "0" + datosegundo; }
                    else
                    { segundocompletado = datosegundo; }

                    //String datofecha = (datodia + "/" + datomes +"/" + datoano + "  Hora: "+datohora+":"+datominuto + " ");
                    String datofecha = (datodia + "/" + datomes +"/" + datoano );

                    // CODIGO PARA TIPO DE USUARIO:MEDICO- ENFERMERA-FISIO
                        String TIPO_USUARIO = "";
                        if(AUXILIAR.isChecked())          { TIPO_USUARIO= "Auxiliar Enfermería";  }
                        else if (MEDICO.isChecked())      { TIPO_USUARIO= "Médico";  }
                        else if (ESTUDIANTE.isChecked())  { TIPO_USUARIO= "Estudiante UMB";  }
                        else if (ENFERMERIA.isChecked())  { TIPO_USUARIO= "Jefe Enfermería"; }
                        else if (INGE.isChecked())        { TIPO_USUARIO= "Ingeniero Biomédico"; }
                        else                              {  TIPO_USUARIO= "Fisioterapeuta";     }
                    // CODIGO PARA TIPO DE USUARIO:MEDICO- ENFERMERA-FISIO


                    String PAIS = "";

                        // CODIGO PARA TIPO DE USUARIO:MEDICO- ENFERMERA-FISIO
                        if(COLOMBIA.isChecked())          { PAIS= "Colombia";  }
                        else if (ECUADOR.isChecked())     { PAIS= "Ecuador";  }
                        else if (PERU.isChecked())        { PAIS= "Perú"; }
                        else if (EEUU.isChecked())        { PAIS= "EE.UU"; }
                        else if (CHILE.isChecked())       { PAIS= "Chile"; }
                        else if (MEXICO.isChecked())      { PAIS= "México"; }
                        else if (ARGENTINA.isChecked())   { PAIS= "Argentina"; }
                        else if (mOtro.getText().length() !=0)
                            {
                                otro = mOtro.getText().toString().trim();
                                PAIS= otro;
                            }


                    name = mName.getText().toString().trim();
                    apellido = mApellido.getText().toString().trim();

                    String name = mName.getText().toString().trim();
                    String apellido = mApellido.getText().toString().trim();
                    String nombrecompleto = ( name + " " + apellido);

                    String tipousuario = TIPO_USUARIO;
                    String paises = PAIS;
                    String id = mAuth.getCurrentUser().getUid();



                            // * * * * IDENTIFICADOR DE USUARIO * * * *

                                String NombreConIdparaFirebase = name + " " + apellido + " "+ id;

                            // * * * * IDENTIFICADOR DE USUARIO * * * *



                    //GUARDADO DE SHARED PREFERENCES
                            SharedPreferences preferences = getSharedPreferences
                                    ("Credenciales", Context.MODE_PRIVATE);
                            String St_nombre = mName.getText().toString().trim();
                            String St_apellido = mApellido.getText().toString().trim();
                            String St_celular = mCelular.getText().toString().trim();
                            String St_mail = mEmail.getText().toString().trim();

                                SharedPreferences.Editor edito =preferences.edit();
                                edito.putString("key_nombrename",St_nombre);
                                edito.putString("key_apellido",St_apellido);
                                edito.putString("key_tipo_usuario",tipousuario);
                                edito.putString("key_celular",St_celular);
                                edito.putString("key_mail",St_mail);
                                edito.putString("key_fecha_registro",datofecha);
                                edito.putString("key_pais",PAIS);

                            edito.commit();
                    //GUARDADO DE SHARED PREFERENCES


                    //GUARDADO DE CLOUD FIRESTORE
                            Map<String, Object> user = new HashMap<>();

                                user.put("Nombre completo", nombrecompleto);
                                user.put("Roll", tipousuario);
                                user.put("Celular", celular);
                                user.put("Email", email);
                                user.put("Password", password);
                                user.put("Fecha de Registro", datofecha);
                                user.put("Tipo de usuario", tipousuario);
                                user.put("País", PAIS);
                                user.put("Suscripcion", "Activo");
                                user.put("Compra de suscripcion","");

                            mFirestore.collection("Usuarios").document(nombrecompleto +" "+id).set(user);
                    //GUARDADO DE CLOUD FIRESTORE




                    //GUARDADO DE REAL TIME DATABASE
                        Map<String, Object> maps = new HashMap<>();

                            maps.put("Nombre completo", nombrecompleto);
                            maps.put("Celular", celular);
                            maps.put("Email", email);
                            maps.put("Password", password);
                            maps.put("Fecha de Registro", datofecha);
                            maps.put("Tipo de usuario", tipousuario);
                            maps.put("País", PAIS);
                            maps.put("Suscripcion", "Activo");
                            maps.put("Compra de suscripción", "");

                        HemodyDataBaseRef.child("Usuarios").child(NombreConIdparaFirebase).setValue(maps).addOnCompleteListener(new OnCompleteListener<Void>()
                        {

                            @Override
                            public void onComplete(@NonNull Task<Void> task2)
                            {

                                if (task2.isSuccessful())
                                {
                                    startActivity(new Intent(CrearUsuario.this, ConsultaPoliticasPrivacidad.class));
                                    //EDITO DE UNA VEZ EL SHARED DE...¿ USUARIO YA CREADO??
                                    EditarSharedPRef();
                                    //EDITO DE UNA VEZ EL SHARED DE...¿ USUARIO YA CREADO??
                                    loadingDialog.Terminar_Progress_Mesage();

                                    new SendRequest().execute();

                                    finish();
                                }
                                else
                                    {

                                    }
                            }
                        });
               }
                 // SI LA CREACIÓN DEL USUARIO EB FIREBASE NO ES EXITOSA.. HAGA ESTO
                else
                    {
                     Toast.makeText(CrearUsuario.this, "Registro fallido. Es posible que este correo ya esté registrado", Toast.LENGTH_LONG).show();
                     loadingDialog.Terminar_Progress_Mesage();
                    }
            }

        });
    }

    private void EnviarMensajeWhatsapp()
    {
        try
            {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=" + "573102433506"
                        + "&text=" + "Hola, requiero soporte sobre la captura de información con FonoDx."));
                startActivity(intent);
            }
        catch (Exception e)
            {
                Toast.makeText(this, "Whatsapp error", Toast.LENGTH_SHORT).show();
            }
    }

    public class SendRequest extends AsyncTask<String, Void, String>
    {   protected void onPreExecute(){}
        protected String doInBackground(String... arg0)
        { try{
            URL url = new URL("https://script.google.com/macros/s/AKfycbz2YmK7q5MsI6UDTdsI2BP8YXyeCfGTFodkVkWW/exec");

            JSONObject postDataParams = new JSONObject();

            String id= "164p2CfEPwdjsqzCvSD5KtnlxWMdlkrUnTeTEpQX6e3A";
            postDataParams.put("id",id);

            //AJUSTES PARA FECHAS
            Date anotherCurDate = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat(" hh:mm a ");
            String formattedDateString = formatter.format(anotherCurDate);

            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat mdformat = new SimpleDateFormat("dd / MM / yyyy ");
            //SimpleDateFormat mdformat = new SimpleDateFormat("yyyy / MM / dd ");

            String strDate =  mdformat.format(calendar.getTime());

            //LLAMAD DE DATOS DEL SHARED USUARIOS
            SharedPreferences preferences = getSharedPreferences
                    ("Credenciales", Context.MODE_PRIVATE);

            String string_nombre     = preferences.getString("key_nombrename","Empty_nombre");
            String string_apellido = preferences.getString("key_apellido","Empty_apellido");
            String string_nombrecompletos = string_nombre +" "+ string_apellido;
            String string_tipo_usuario = preferences.getString("key_tipo_usuario","Empty_roll");
            String string_pais = preferences.getString("key_pais","Empty_pais");

            //AJUSTES PARA FECHAS

            postDataParams.put("dato1",string_nombrecompletos);
            postDataParams.put("dato2",string_tipo_usuario);
            postDataParams.put("dato3",celular);
            postDataParams.put("dato4",string_pais);
            postDataParams.put("dato5",strDate);
            postDataParams.put("dato6",password);
            postDataParams.put("dato7",email);


            Log.e("params",postDataParams.toString());

            // CODIGO DE CONEXION A INTERNET
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000 );
            conn.setConnectTimeout(15000 );
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(getPostDataString(postDataParams));

            writer.flush();
            writer.close();
            os.close();

            int responseCode=conn.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {

                BufferedReader in=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuffer sb = new StringBuffer("");
                String line="";
                while((line = in.readLine()) != null) {

                    sb.append(line);
                    break;  }
                in.close();
                return sb.toString(); }
            else {  return new String("false : "+responseCode); } }
        catch(Exception e){
            return new String("Exception: " + e.getMessage());  } }
        // CODIGO DE CONEXION A INTERNET

        @Override
        protected void onPostExecute(String result)
        {
            //Toast.makeText(antropometricos_captura.this, "Se grabó en Google Sheets", Toast.LENGTH_SHORT).show();
        }
    }

    public String getPostDataString(JSONObject params) throws Exception
    {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        Iterator<String> itr = params.keys();
        while(itr.hasNext()){
            String key= itr.next();
            Object value = params.get(key);
            if (first)
                first = false;
            else
                result.append("&");
            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));
        }
        return result.toString();
    }
    //HASTA ACA METODO PARA GUARDAR EN GOOGLE SHEETS

}

