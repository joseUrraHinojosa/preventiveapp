package com.jose.preventiveapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import dao.UsuarioDAO;
import dto.Usuario;
import dto.CommonFn;

public class Usuario_ extends AppCompatActivity {

 private  TextInputLayout nombre,paterno,materno,email,run,pass1,pass2;
 private String nom,pater,mater,correo,rut,clave1,clave2;
 private boolean usuario_registrado=false;
 private String run_bd,nombre_bd,paterno_bd,materno_bd,email_bd,pass_bd;
 private Button btn_guardar;
 private ImageView iv_atras;
 private boolean ok =false;
 CommonFn v = new CommonFn();

    @SuppressLint({"UseCompatLoadingForDrawables", "WrongConstant"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

       nombre = findViewById(R.id.nombre);
       paterno=findViewById(R.id.paterno);
       materno =findViewById(R.id.materno);
       email =findViewById(R.id.email);
       run =findViewById(R.id.run);
       pass1 =findViewById(R.id.password1);
       pass2 =findViewById(R.id.password2);
       iv_atras =findViewById(R.id.iv_atras);
       btn_guardar =findViewById(R.id.btn_guardar);



       //consulta existencia usuario y setea si existe
        Usuario usu =  new UsuarioDAO().mostrarUsuario(getBaseContext());

        try{
            if(!usu.getRun().isEmpty()){

                nombre.getEditText().setText(usu.getNombre());
                paterno.getEditText().setText(usu.getPaterno());
                materno.getEditText().setText(usu.getMaterno());
                email.getEditText().setText(usu.getEmail());
                run.getEditText().setText(usu.getRun());

                run.setEnabled(false);
                btn_guardar.setText("Actualizar");
                usuario_registrado=true;
            }else{

            }
        }catch (Exception e){

        }


        iv_atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), Menu.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP));
                finish();

            }
        });


        btn_guardar.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
                //si el metodo validar_formulario es true se realiza los metodos de guardar o de actualizar
              if(validar_formulario()==true){

                  if(usuario_registrado==false){
                      guardarDatos();
                  }else {
                      actualizar();
                  }
              }

          }
       });


    }



    public boolean validar_formulario(){

        nom = nombre.getEditText().getText().toString().trim();
        pater = paterno.getEditText().getText().toString().trim();
        mater = materno.getEditText().getText().toString().trim();
        correo = email.getEditText().getText().toString().trim();
        rut= run.getEditText().getText().toString().trim();
        clave1 = pass1.getEditText().getText().toString().trim();
        clave2 = pass2.getEditText().getText().toString().trim();

       boolean validado = true;

       nombre.setErrorEnabled(false);
       paterno.setErrorEnabled(false);
       materno.setErrorEnabled(false);
       email.setErrorEnabled(false);
       run.setErrorEnabled(false);
       pass1.setErrorEnabled(false);
       pass2.setErrorEnabled(false);

       if(nom.isEmpty()){

          nombre.setError( "Debe ingresar su nombre!" );
          nombre.setErrorEnabled(true);
          validado=false;

       }

       if(pater.isEmpty()){
          validado=false;
          paterno.setError( "Debe ingresar su apellido paterno!");
          paterno.setErrorEnabled(true);

       }

       if(mater.isEmpty()){
          validado=false;
          materno.setError( "Debe ingresar su apellido materno!");
          materno.setErrorEnabled(true);
       }

       if(correo.isEmpty()){
          validado=false;
          email.setError( "Debe ingresar un email!");
          email.setErrorEnabled(true);
       }


       if(rut.isEmpty()){
          validado=false;
          run.setError( "Debe ingresar su run!");
          run.setErrorEnabled(true);
       }else if(v.validaRut(rut)==false){
           validado=false;
           run.setError( "Run inválido!");
           run.setErrorEnabled(true);

       }

        if(clave1.isEmpty()){

            validado=false;
            pass1.setError( "Debe ingresar una password!");
            pass1.setErrorEnabled(true);
        }

        if(clave2.isEmpty()){

            validado=false;
            pass2.setError( "Debe escribir nuevamente su password!");
            pass2.setErrorEnabled(true);
        }


        if(clave1.length() < 4 && ! clave1.isEmpty()){
            validado=false;
            pass1.setError("Debe ingresar cuatro dígitos");
            pass1.setEnabled(true);
        }
        if(clave2.length() < 4 && !clave2.isEmpty()) {
            validado=false;
            pass2.setError("Debe ingresar cuatro dígitos");
            pass2.setEnabled(true);
        }
        else
        //VALIDACION CLAVES IGUALES
        if(!clave1.equals(clave2) && !clave1.isEmpty() && !clave2.isEmpty() ){
            validado=false;
            pass2.setError( "Las password no coinciden!");
            pass2.setErrorEnabled(true);
        }






        return validado;
    }

    public void actualizar(){

        nom = nombre.getEditText().getText().toString().trim();
        pater = paterno.getEditText().getText().toString().trim();
        mater = materno.getEditText().getText().toString().trim();
        correo = email.getEditText().getText().toString().trim();
        rut = run.getEditText().getText().toString().trim();
        clave1 = pass1.getEditText().getText().toString().trim();
        clave2 = pass2.getEditText().getText().toString().trim();

        Usuario usu = new Usuario();
        usu.setRun(rut);
        usu.setNombre(nom);
        usu.setPaterno(pater);
        usu.setMaterno(mater);
        usu.setEmail(correo);
        usu.setPassword(Integer.parseInt(clave1));

        new UsuarioDAO().actualizarUsuario(usu,getBaseContext());
        Toast.makeText(this, "Usuario actualizado con éxito", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, Login.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP));
        finish();

    }


    public void guardarDatos(){

            nom = nombre.getEditText().getText().toString().trim();
            pater = paterno.getEditText().getText().toString().trim();
            mater = materno.getEditText().getText().toString().trim();
            correo = email.getEditText().getText().toString().trim();
            rut = run.getEditText().getText().toString().trim();
            clave1 = pass1.getEditText().getText().toString().trim();

            Usuario usu = new Usuario();
            usu.setRun(rut);
            usu.setNombre(nom);
            usu.setPaterno(pater);
            usu.setMaterno(mater);
            usu.setEmail(correo);
            usu.setPassword(Integer.parseInt(clave1));
            new UsuarioDAO().ingresarUsuario(usu,getBaseContext());

            Toast.makeText(this, "Usuario registrado con éxito", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, Login.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP));
            finish();

    }

    @Override
    public void onBackPressed(){

        startActivity(new Intent(getBaseContext(), Menu.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP));
        finish();
    }




}