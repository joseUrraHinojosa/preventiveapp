package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import dto.AdminSQLite;
import dto.CondicionesEstructurales;
import dto.Fotografia;


public class CondicionesEsctructuralesDAO {

    public  void ingresarCondicionesEstructurales(CondicionesEstructurales estructurales, Context c,int idDatosGenerales){

        try{
            AdminSQLite adminSQLite = new AdminSQLite(c, "bd_preventive", null, 1);
            SQLiteDatabase bd = adminSQLite.getWritableDatabase();

            ContentValues registro = new ContentValues();

            registro.put("criterio1", estructurales.getCriterio1());
            registro.put("criterio2", estructurales.getCriterio2());
            registro.put("criterio3", estructurales.getCriterio3());
            registro.put("criterio4", estructurales.getCriterio4());
            registro.put("criterio5", estructurales.getCriterio5());
            registro.put("criterio6", estructurales.getCriterio6());
            registro.put("idDatosGenerales", idDatosGenerales);

            bd.insert("condicionesEstructurales", null, registro);
            bd.close();

        }catch (Exception e){

        }
    }


    public CondicionesEstructurales mostrarCondicionesEstruc(int id, Context c){

        CondicionesEstructurales estructurales = new CondicionesEstructurales();

        try{

            AdminSQLite adminSQLite = new AdminSQLite(c,"bd_preventive",null,1);
            SQLiteDatabase bd = adminSQLite.getWritableDatabase();

            Cursor fila = bd.rawQuery
                    ("select * from condicionesEstructurales where idDatosGenerales = "+id,null);
            if(fila.moveToFirst()){

                do{
                    estructurales.setIdCondEstructurales(fila.getInt(0));
                    estructurales.setCriterio1(fila.getString(1));
                    estructurales.setCriterio2(fila.getString(2));
                    estructurales.setCriterio3(fila.getString(3));
                    estructurales.setCriterio4(fila.getString(4));
                    estructurales.setCriterio5(fila.getString(5));
                    estructurales.setCriterio6(fila.getString(6));

                } while (fila.moveToNext());
            }else{

            }
            bd.close();

        } catch (Exception e){

        }
        return estructurales;
    }

    //FOTOS
    public  void ingresarCondicionesEstructuralesFOTOS(CondicionesEstructurales estructurales, Context c, int idDatosGenerales){

        try{
            for(Fotografia actual: estructurales.getFotos()){
            AdminSQLite adminSQLite = new AdminSQLite(c, "bd_preventive", null, 1);
            SQLiteDatabase bd = adminSQLite.getWritableDatabase();


                ContentValues registro = new ContentValues();
                registro.put("nombreFoto", actual.getNombreFoto());
                registro.put("rutaFoto", actual.getRutaFoto());
                registro.put("idDatosGenerales", idDatosGenerales);
                bd.insert("condicionesEstructuralesFotos", null, registro);
                bd.close();
            }


        }catch (Exception e){

        }
    }

    public List<Fotografia> mostrarCondicionesEstrucFOTOS(int id, Context c){

        List<Fotografia> listFotos = new ArrayList<>();

        try{

            AdminSQLite adminSQLite = new AdminSQLite(c,"bd_preventive",null,1);
            SQLiteDatabase bd = adminSQLite.getWritableDatabase();

            Cursor fila = bd.rawQuery
                    ("select * from condicionesEstructuralesFotos where idDatosGenerales ="+id,null);
            if(fila.moveToFirst()){

                do{

                    listFotos.add(new Fotografia(fila.getString(1),fila.getString(2)));

                } while (fila.moveToNext());


            }else{

            }
            bd.close();


        } catch (Exception e){

        }
        return listFotos;


    }


    public int eliminarConEstructurales(Context c, int id ){
        int result = -1;

        try{
            AdminSQLite adminSQLite = new AdminSQLite(c, "bd_preventive", null, 1);
            SQLiteDatabase bd = adminSQLite.getWritableDatabase();

            result = bd.delete("condicionesEstructurales","idDatosGenerales ="+id,null);
            bd.close();
        }catch (Exception e){

        }


        return  result;
    }

    public int eliminarEstructuralesFotos(Context c, int id ){
         int result = -1;
         try{
             for(Fotografia actual: new CondicionesEsctructuralesDAO().mostrarCondicionesEstrucFOTOS(id,c)){

                 File fdelete = new File(actual.getRutaFoto());
                 if (fdelete.exists()) {
                     fdelete.delete();
                 }
             }

             AdminSQLite adminSQLite = new AdminSQLite(c, "bd_preventive", null, 1);
             SQLiteDatabase bd = adminSQLite.getWritableDatabase();

             result = bd.delete("condicionesEstructuralesFotos","idDatosGenerales ="+id,null);
             bd.close();
         }catch (Exception e){

         }


        return  result;
    }

}
