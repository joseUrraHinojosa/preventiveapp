package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import dto.AdminSQLite;
import dto.Usuario;


public class IdActualDAO {


    public  void ingresarIdActual(int id, Context c){
        try{
            AdminSQLite adminSQLite = new AdminSQLite(c, "bd_preventive", null, 1);
            SQLiteDatabase bd = adminSQLite.getWritableDatabase();
            ContentValues registro = new ContentValues();

            registro.put("id", id);

            bd.insert("idActual", null, registro);
            bd.close();

        }catch (Exception e){

        }
    }

    public Integer mostrarIdActual(Context c){
        int id =-1;
        try {

            AdminSQLite adminSQLite1 = new AdminSQLite(c,"bd_preventive",null,1);
            SQLiteDatabase bd = adminSQLite1.getWritableDatabase();

            Cursor fila = bd.rawQuery
                    ("select * from idActual",null);
            if(fila.moveToFirst()){
               id = (fila.getInt(0));

            }else{

            }
            bd.close();

        }catch (Exception e){

        }
        return  id;
    }


    public void eliminarId(int id,Context c){

        try {

            AdminSQLite adminSQLite1 = new AdminSQLite(c,"bd_preventive",null,1);
            SQLiteDatabase bd = adminSQLite1.getWritableDatabase();

            bd.delete("idActual",null,null);

            bd.close();

        }catch (Exception e){

        }
    }

}
