package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import dto.AdminSQLite;
import dto.Observaciones;

public class ObservacionesDAO {


    public  void ingresarObservacion(List<Observaciones> obslist, Context c, int idDatosGenerales){

        try{

            for(Observaciones actual: obslist){
                AdminSQLite adminSQLite = new AdminSQLite(c, "bd_preventive", null, 1);
                SQLiteDatabase bd = adminSQLite.getWritableDatabase();
                ContentValues registro = new ContentValues();

                registro.put("titulo", actual.getTitulo());
                registro.put("descripcion", actual.getDescripcion());
                registro.put("idDatosGenerales", idDatosGenerales);


                bd.insert("observaciones", null, registro);
                bd.close();
            }

        }catch (Exception e){

        }
    }


    public List<Observaciones> mostrarIdActual(int id, Context c){
        List<Observaciones> obsList = new ArrayList<>();
        try {

            AdminSQLite adminSQLite1 = new AdminSQLite(c,"bd_preventive",null,1);
            SQLiteDatabase bd = adminSQLite1.getWritableDatabase();

            Cursor fila = bd.rawQuery
                    ("select * from observaciones where idDatosGenerales ="+id,null);
            if(fila.moveToFirst()){

                do{
                    obsList.add(new Observaciones(fila.getString(1),fila.getString(2)));
                } while (fila.moveToNext());


            }else{

            }
            bd.close();

        }catch (Exception e){

        }
        return  obsList;
    }

    public int eliminarObs(Context c, int id ){
        int result = -1;
        try{
            AdminSQLite adminSQLite = new AdminSQLite(c, "bd_preventive", null, 1);
            SQLiteDatabase bd = adminSQLite.getWritableDatabase();

            result = bd.delete("Observaciones","idDatosGenerales ="+id,null);
            bd.close();
        }catch (Exception e){

        }


        return  result;
    }
}
