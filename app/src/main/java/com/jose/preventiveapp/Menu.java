package com.jose.preventiveapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import dao.IdActualDAO;
import dao.TemasCharlaDAO;
import dto.AdminSQLite;

public class Menu extends AppCompatActivity implements View.OnClickListener {

    ConstraintLayout user, inspeccion,charla,epp,registros,salir;
    boolean usuario_registrado =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        user = findViewById(R.id.constraint_user);
        inspeccion = findViewById(R.id.constraint_inspeccion);
        charla = findViewById(R.id.constraint_charla);
        epp = findViewById(R.id.constraint_epp);
        registros = findViewById(R.id.constraint_registro);
        salir = findViewById(R.id.constraint_salir);

        user.setOnClickListener(this);
        inspeccion.setOnClickListener(this);
        charla.setOnClickListener(this);
        epp.setOnClickListener(this);
        registros.setOnClickListener(this);
        salir.setOnClickListener(this);

      //  Window w = getWindow();
      //  w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        //consulta existencia registro de usuario
        try{
            AdminSQLite adminSQLite1 = new AdminSQLite(this,"bd_preventive",null,1);
            SQLiteDatabase bd = adminSQLite1.getWritableDatabase();

            Cursor fila = bd.rawQuery
                    ("select * from usuario",null);

            if(fila.moveToFirst()){
                usuario_registrado=true;
            }
            bd.close();
        }catch (Exception e){

        }

    }



    public void siguiente(Activity activity) {
        startActivity(new Intent(getBaseContext(), activity.getClass())
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP));
         finish();
    }


    //direcciona a sisguiente activity y valida usuario que este el usuario registrado antes de usar la app
    @Override
    public void onClick(View v) {
        int idActualDg = new IdActualDAO().mostrarIdActual(getBaseContext());
        int resul = new TemasCharlaDAO().mostrarTemasCharla(idActualDg,getBaseContext()).size();

        switch (v.getId()) {
            case R.id.constraint_user:
                siguiente(new Usuario_());
                break;

            case R.id.constraint_salir: finish();
            break;
        }

        if(usuario_registrado) {
            switch (v.getId()) {
                case R.id.constraint_inspeccion:

                    if(idActualDg == -1){
                        siguiente(new Inspeccion_general());
                    }else{
                        Toast.makeText(this,"Antes de realizar una nueva inspección general debe completar charla de cinco minutos y revision de epp " ,Toast.LENGTH_LONG).show();
                    }
                    break;

                case R.id.constraint_charla:

                    if(idActualDg > -1 && resul==0){
                        siguiente(new Charla());
                    }else if(resul>0){
                        Toast.makeText(this,"Antes de realizar el registro de la charla de cinco minutos debe completar revision de epp" +
                                "",Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(this,"Antes de realizar el registro de la charla de cinco minutos debe completar el registro de inspección general" +
                                "",Toast.LENGTH_LONG).show();
                    }
                    break;

                case R.id.constraint_epp:

                    if(idActualDg !=(-1) && resul >0 ){
                        siguiente(new Revision_epp());
                    }else if(resul ==0){
                        Toast.makeText(this,"Antes de realizar revision de epp debe completar charla de cinco minutos" +
                                "",Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(this,"Antes de realizar revision de epp debe completar  inspección general" +
                                "",Toast.LENGTH_LONG).show();

                    }
                break;

                case R.id.constraint_registro: siguiente(new Registros_());
                    break;

                default:
                    break;
            }
        }else if(v.getId() != R.id.constraint_user && v.getId() != R.id.constraint_salir){
            Toast.makeText(this,"Debe registrarse como usuario antes de hacer uso de la aplicación.",Toast.LENGTH_SHORT).show();

        }
    }

    public void onBackPressed(){
       // inactivo
    }
}