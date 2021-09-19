package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import dto.AdminSQLite;

public class TemasCharlaDAO {

    public void ingresarTemasCharla(List<String> temascharlaList, Context c, int idDatosGenerales) {

        if(temascharlaList != null){
            try {
                for (String actual : temascharlaList) {
                    AdminSQLite adminSQLite = new AdminSQLite(c, "bd_preventive", null, 1);
                    SQLiteDatabase bd = adminSQLite.getWritableDatabase();

                    ContentValues registro = new ContentValues();
                    registro.put("descripcionTema", actual);
                    registro.put("idDatosGenerales", idDatosGenerales);

                    bd.insert("temasCharla", null, registro);
                    bd.close();
                }

            } catch (Exception e) {

            }
        }
    }


    public List<String> mostrarTemasCharla(int id, Context c) {
        List<String> temasCharla= new ArrayList<>();
        try {

            AdminSQLite adminSQLite = new AdminSQLite(c, "bd_preventive", null, 1);
            SQLiteDatabase bd = adminSQLite.getWritableDatabase();

            Cursor fila = bd.rawQuery
                    ("select * from temasCharla where idDatosGenerales = "+id, null);
            if (fila.moveToFirst()) {
                do {
                    temasCharla.add(fila.getString(1));

                } while (fila.moveToNext());
            } else {

            }
            bd.close();


        } catch (Exception e) {

        }

        return temasCharla;
    }

    public int eliminarTemasCharla(Context c, int id ){
        int result = -1;
        try{
            AdminSQLite adminSQLite = new AdminSQLite(c, "bd_preventive", null, 1);
            SQLiteDatabase bd = adminSQLite.getWritableDatabase();

            result = bd.delete("temasCharla","idDatosGenerales ="+id,null);
            bd.close();
        }catch (Exception e){

        }


        return  result;
    }

}
