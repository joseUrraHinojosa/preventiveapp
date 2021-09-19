package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;
import dto.AdminSQLite;


public class SeleccionEppDAO {

    public  void ingresarSeleccionEpp(List<String> eppSeleccionado, Context c, int idDatosGenerales){

        try{

            for(String actual: eppSeleccionado){
                AdminSQLite adminSQLite = new AdminSQLite(c, "bd_preventive", null, 1);
                SQLiteDatabase bd = adminSQLite.getWritableDatabase();

                ContentValues registro = new ContentValues();
                registro.put("descripcionEpp", actual);
                registro.put("idDatosGenerales", idDatosGenerales);

                bd.insert("seleccionEpp",null,registro);
                bd.close();
            }

        }catch (Exception e){

        }
    }


    public List<String> mostrarSeleccionEpp(int id, Context c){
        List<String> eppSeleccionadosList = new ArrayList<>();

        try{

            AdminSQLite adminSQLite1 = new AdminSQLite(c, "bd_preventive", null, 1);
            SQLiteDatabase bd = adminSQLite1.getWritableDatabase();

            Cursor fila = bd.rawQuery
                    ("select * from seleccionEpp where idDatosGenerales ="+id, null);
            if (fila.moveToFirst()) {
                do {
                    eppSeleccionadosList.add(fila.getString(1));
                } while (fila.moveToNext());

            } else {

            }
            bd.close();

        } catch (Exception e){

        }
        return eppSeleccionadosList;
    }

    public int eliminarEppSeleccionados(Context c, int id ){

        int result = -1;

        try{
            AdminSQLite adminSQLite = new AdminSQLite(c, "bd_preventive", null, 1);
            SQLiteDatabase bd = adminSQLite.getWritableDatabase();

            result = bd.delete("seleccionEpp","idDatosGenerales ="+id,null);
            bd.close();
        }catch (Exception e){

        }


        return  result;
    }

}
