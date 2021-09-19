package com.jose.preventiveapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputLayout;
import dto.AdminSQLite;


public class Login extends AppCompatActivity {

    private  Button btn;
    private TextInputLayout pass, login_nombre ;
    private String pass_bd="", nombre,paterno,materno;
    private boolean inicia_sesion =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        btn = findViewById(R.id.btn_login);
        pass =findViewById(R.id.login_password);
        login_nombre = findViewById(R.id.login_nom);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        //consulta
        AdminSQLite adminSQLite1 = new AdminSQLite(this,"bd_preventive",null,1);
        SQLiteDatabase bd = adminSQLite1.getWritableDatabase();

        Cursor fila = bd.rawQuery
                ("select * from usuario",null);
        if(fila.moveToFirst()){

           nombre  = fila.getString(1);
           paterno = fila.getString(2);
           materno = fila.getString(3);
           pass_bd = fila.getString(5);
           inicia_sesion=true;

          login_nombre.getEditText().setText(nombre+" "+paterno+" "+materno);
          login_nombre.setEnabled(false);
        }else{

        }
        bd.close();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String password = pass.getEditText().getText().toString().trim();
                if(password.equals(pass_bd)){

                    startActivity(new Intent(getBaseContext(), Menu.class)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP));
                     finish();

                }else{
                    Toast.makeText(getApplicationContext(),"Pssword incorrecta, vuelva a intentar",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    //control de boton de retroceso
    @Override
    public void onBackPressed(){
        msg();
    }
    public void msg(){

        AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
        alertbox.setMessage("¿Desea salir de la aplicación??");
        alertbox.setCancelable(false);
        alertbox.setTitle(Html.fromHtml("<font color='#0DCF5E'>Atención</font>"));
        alertbox.setIcon(R.drawable.ic_help_100);

        alertbox.setPositiveButton(Html.fromHtml("<font color='#03AE61'>si</font>"), new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface arg0, int arg1) {
               // startActivity(new Intent(getBaseContext(), Menu.class)
                       // .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP));

                finish();

            }
        });

        alertbox.setNegativeButton(Html.fromHtml("<font color='#03AE61'>no</font>"), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertbox.show();
    }





}