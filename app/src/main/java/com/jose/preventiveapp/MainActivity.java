package com.jose.preventiveapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import dto.AdminSQLite;

public class MainActivity extends AppCompatActivity {
    private static int splash = 2000;
    private static Activity activity = new Menu();

    private Boolean usuario_registrado=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        try{
            //consulta usuario está registrado o no
            AdminSQLite adminSQLite1 = new AdminSQLite(this,"bd_preventive",null,1);
            SQLiteDatabase bd = adminSQLite1.getWritableDatabase();

            Cursor fila = bd.rawQuery
                    ("select * from usuario",null);
            if(fila.moveToFirst()){
                activity = new Login();
            }else{
                usuario_registrado=false;



            }
            bd.close();
        }catch (Exception e){

        }


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, activity.getClass());
                startActivity(intent);
                finish();
            }
        } ,splash);
    }



}