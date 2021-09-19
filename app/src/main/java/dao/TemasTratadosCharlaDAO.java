package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import dto.AdminSQLite;

public class TemasTratadosCharlaDAO {

    public void ingresarTemasTratados(List<String> temasTratadosList, Context c, int idDatosGenerales) {

        if(temasTratadosList != null){
            try {
                for (String actual : temasTratadosList) {
                    AdminSQLite adminSQLite = new AdminSQLite(c, "bd_preventive", null, 1);
                    SQLiteDatabase bd = adminSQLite.getWritableDatabase();

                    ContentValues registro = new ContentValues();
                    registro.put("descripTemaTratado", actual);
                    registro.put("idDatosGenerales", idDatosGenerales);

                    bd.insert("temasTratadosCharla", null, registro);
                    bd.close();
                }

            } catch (Exception e) {

            }
        }
    }

    public List<String> mostrarTemasTratados(int id, Context c) {
        List<String> temasTratados= new ArrayList<>();
        try {

            AdminSQLite adminSQLite = new AdminSQLite(c, "bd_preventive", null, 1);
            SQLiteDatabase bd = adminSQLite.getWritableDatabase();

            Cursor fila = bd.rawQuery
                    ("select * from temasTratadosCharla where idDatosGenerales = "+id, null);
            if (fila.moveToFirst()) {
                do {
                    temasTratados.add(fila.getString(1));

                } while (fila.moveToNext());
            } else {

            }
            bd.close();


        } catch (Exception e) {

        }

        return temasTratados;
    }

    public int eliminarTemasTratados(Context c, int id ){
        int result= -1;
        try{
            AdminSQLite adminSQLite = new AdminSQLite(c, "bd_preventive", null, 1);
            SQLiteDatabase bd = adminSQLite.getWritableDatabase();

            result = bd.delete("temasTratadosCharla","idDatosGenerales ="+id,null);
            bd.close();
        }catch (Exception e){

        }

        return  result;
    }

}
