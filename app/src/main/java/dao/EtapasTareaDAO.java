package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import dto.AdminSQLite;


public class EtapasTareaDAO {

    public  void ingresarEtapas(List<String> etapas, Context c, int idDatosGenerales){

        try{

            for(String actual: etapas){
                AdminSQLite adminSQLite = new AdminSQLite(c, "bd_preventive", null, 1);
                SQLiteDatabase bd = adminSQLite.getWritableDatabase();

                ContentValues registro = new ContentValues();

                registro.put("descripcionEtapa", actual );
                registro.put("idDatosGenerales", idDatosGenerales);

                bd.insert("etapasTarea", null, registro);
                bd.close();
            }

        }catch (Exception e){

        }
    }


    public List<String> mostrarEtapasTarea(int id, Context c) {
        List<String> etapas = new ArrayList<>();
        try{

            AdminSQLite adminSQLite = new AdminSQLite(c,"bd_preventive",null,1);
            SQLiteDatabase bd = adminSQLite.getWritableDatabase();

            Cursor fila = bd.rawQuery
                    ("select * from etapasTarea where idDatosGenerales ="+id,null);
            if(fila.moveToFirst()){

                do{

                    etapas.add(fila.getString(1));

                } while (fila.moveToNext());
            }else{

            }
            bd.close();


        } catch (Exception e){

        }

        return etapas;
    }

    public int eliminarEtapasTarea(Context c, int id ){
        int result = -1;
        try{
            AdminSQLite adminSQLite = new AdminSQLite(c, "bd_preventive", null, 1);
            SQLiteDatabase bd = adminSQLite.getWritableDatabase();

            result = bd.delete("etapasTarea","idDatosGenerales ="+id,null);
            bd.close();
        }catch (Exception e){

        }

        return  result;
    }
}
