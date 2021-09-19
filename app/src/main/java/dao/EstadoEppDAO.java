package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import dto.AdminSQLite;
import dto.EstadoEpp;

public class EstadoEppDAO {

    public  void ingresarEstdoEpp(List<EstadoEpp> eppList, Context c, int idDatosGenerales){

        try{

            for(EstadoEpp actual: eppList){
                AdminSQLite adminSQLite = new AdminSQLite(c, "bd_preventive", null, 1);
                SQLiteDatabase bd = adminSQLite.getWritableDatabase();

                ContentValues registro = new ContentValues();

                registro.put("nombreTra", actual.getNombreTrabajador() );
                registro.put("nomEpp", actual.getNombreEpp() );
                registro.put("estadoEpp", actual.getEstado() );
                registro.put("idDatosGenerales", idDatosGenerales);

                bd.insert("estadoEpp", null, registro);
                bd.close();
            }

        }catch (Exception e){

        }
    }


    public List<EstadoEpp> mostrarEstadosEpp(int idDatosGenerales, Context c) {
        List<EstadoEpp> epps = new ArrayList<>();
        try{

            AdminSQLite adminSQLite = new AdminSQLite(c,"bd_preventive",null,1);
            SQLiteDatabase bd = adminSQLite.getWritableDatabase();

            Cursor fila = bd.rawQuery
                    ("select * from estadoEpp where idDatosGenerales = "+idDatosGenerales,null);
            if(fila.moveToFirst()){

                do{
                    EstadoEpp estadoEpp = new EstadoEpp();
                    estadoEpp.setNombreTrabajador(fila.getString(1));
                    estadoEpp.setNombreEpp(fila.getString(2));
                    estadoEpp.setEstado(fila.getString(3));
                    epps.add(estadoEpp);


                } while (fila.moveToNext());
            }else{

            }
            bd.close();


        } catch (Exception e){

        }

        return epps;
    }

    public int eliminarEstadoEpp(Context c, int id ){
        int result = -1;
        try{

            AdminSQLite adminSQLite = new AdminSQLite(c, "bd_preventive", null, 1);
            SQLiteDatabase bd = adminSQLite.getWritableDatabase();

            result = bd.delete("estadoEpp","idDatosGenerales ="+id,null);
            bd.close();
        }catch (Exception e){

        }

        return  result;
    }

}
