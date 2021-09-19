package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.airbnb.lottie.animation.content.Content;

import java.util.ArrayList;
import java.util.List;

import dto.AdminSQLite;
import dto.Trabajadores;

public class TrabajadoresDAO {


    public  void ingresarTrabajadoresActual(List<Trabajadores> trabajadoreslist, Context c, int idDatosGenerales){
        try{

            for(Trabajadores actual: trabajadoreslist){
                AdminSQLite adminSQLite = new AdminSQLite(c, "bd_preventive", null, 1);
                SQLiteDatabase bd = adminSQLite.getWritableDatabase();

                ContentValues registro = new ContentValues();
                registro.put("nombre", actual.getNombre());
                registro.put("run", actual.getRun());
                registro.put("pasaporte", actual.getPasaporte());
                registro.put("idDatosGenerales", idDatosGenerales);

                bd.insert("trabajadoresActual",null,registro);
                bd.close();
            }

        }catch (Exception e){

        }
    }


    public List<Trabajadores> mostrarTrabajadoresActual(int id, Context c){

        List<Trabajadores> trabajadoreslist = new ArrayList<>();
        try{

            AdminSQLite adminSQLite1 = new AdminSQLite(c, "bd_preventive", null, 1);
            SQLiteDatabase bd = adminSQLite1.getWritableDatabase();

            Cursor fila = bd.rawQuery
                    ("select * from trabajadoresActual where idDatosGenerales ="+id, null);
            if (fila.moveToFirst()) {
                do {
                    Trabajadores tra = new Trabajadores();
                    tra.setNombre(fila.getString(1));
                    tra.setRun(fila.getString(2));
                    tra.setPasaporte(fila.getString(3));
                    trabajadoreslist.add(tra);
                } while (fila.moveToNext());

            } else {

            }
            bd.close();

        } catch (Exception e){

        }
        return trabajadoreslist;
    }


    public int eliminarTrabajadores(Context c, int id ){
        int result =-1;
        try{
            AdminSQLite adminSQLite = new AdminSQLite(c, "bd_preventive", null, 1);
            SQLiteDatabase bd = adminSQLite.getWritableDatabase();

             result = bd.delete("trabajadoresActual","idDatosGenerales ="+id,null);
            bd.close();

        }catch (Exception e){

        }
        return  result;


    }

}
