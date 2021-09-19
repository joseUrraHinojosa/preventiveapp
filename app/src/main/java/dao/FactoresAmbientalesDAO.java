package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import dto.AdminSQLite;
import dto.FactoresAmbientales;
import dto.Fotografia;

public class FactoresAmbientalesDAO {

    public  void ingresarFacAmbientales(FactoresAmbientales ambientales, Context c, int idDatosGenerales){

        try{
            AdminSQLite adminSQLite = new AdminSQLite(c, "bd_preventive", null, 1);
            SQLiteDatabase bd = adminSQLite.getWritableDatabase();

            ContentValues registro = new ContentValues();

            registro.put("criterio1", ambientales.getCriterio1());
            registro.put("criterio2", ambientales.getCriterio2());
            registro.put("criterio3", ambientales.getCriterio3());
            registro.put("criterio4", ambientales.getCriterio4());
            registro.put("criterio5", ambientales.getCriterio5());
            registro.put("criterio6", ambientales.getCriterio6());
            registro.put("criterio7", ambientales.getCriterio7());
            registro.put("criterio8", ambientales.getCriterio8());
            registro.put("criterio9", ambientales.getCriterio9());
            registro.put("criterio10", ambientales.getCriterio10());
            registro.put("idDatosGenerales", idDatosGenerales);

            bd.insert("factoresAmbientales", null, registro);
            bd.close();

        }catch (Exception e){

        }
    }


    public FactoresAmbientales mostrarFacAmbientales(int id, Context c){
        FactoresAmbientales ambientales = new FactoresAmbientales();

        try{

            AdminSQLite adminSQLite = new AdminSQLite(c,"bd_preventive",null,1);
            SQLiteDatabase bd = adminSQLite.getWritableDatabase();

            Cursor fila = bd.rawQuery
                    ("select * from factoresAmbientales where idDatosGenerales = "+id,null);
            if(fila.moveToFirst()){

                do{
                    ambientales.setIdFacAmbientales(fila.getInt(0));
                    ambientales.setCriterio1(fila.getString(1));
                    ambientales.setCriterio2(fila.getString(2));
                    ambientales.setCriterio3(fila.getString(3));
                    ambientales.setCriterio4(fila.getString(4));
                    ambientales.setCriterio5(fila.getString(5));
                    ambientales.setCriterio6(fila.getString(6));
                    ambientales.setCriterio7(fila.getString(7));
                    ambientales.setCriterio8(fila.getString(8));
                    ambientales.setCriterio9(fila.getString(9));
                    ambientales.setCriterio10(fila.getString(10));

                } while (fila.moveToNext());
            }else{

            }
            bd.close();

        } catch (Exception e){

        }

        return ambientales;
    }

    //FOTOS
    public  void ingresarFacAmbientalesFOTOS(FactoresAmbientales ambientales, Context c, int idDatosGenerales){

        try{
            for(Fotografia actual: ambientales.getFotos()){
                AdminSQLite adminSQLite = new AdminSQLite(c, "bd_preventive", null, 1);
                SQLiteDatabase bd = adminSQLite.getWritableDatabase();

                ContentValues registro = new ContentValues();
                registro.put("nombreFoto",actual.getNombreFoto());
                registro.put("rutaFoto", actual.getRutaFoto());
                registro.put("idDatosGenerales", idDatosGenerales);
                bd.insert("factoresAmbientalesFotos", null, registro);
                bd.close();
            }

        }catch (Exception e){

        }
    }

    public List<Fotografia> mostrarFacAmbientalesFOTOS(int id, Context c){
        List<Fotografia> listFotos = new ArrayList<>();

        try{

            AdminSQLite adminSQLite = new AdminSQLite(c,"bd_preventive",null,1);
            SQLiteDatabase bd = adminSQLite.getWritableDatabase();

            Cursor fila = bd.rawQuery
                    ("select * from factoresAmbientalesFotos where idDatosGenerales = "+id,null);
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

    public int eliminarAmbientales(Context c, int id ){
        int result = -1;

        try{
            AdminSQLite adminSQLite = new AdminSQLite(c, "bd_preventive", null, 1);
            SQLiteDatabase bd = adminSQLite.getWritableDatabase();

            result = bd.delete("factoresAmbientales","idDatosGenerales ="+id,null);
            bd.close();
        }catch (Exception e){

        }


        return  result;
    }

    public int eliminarAmbientalesFotos(Context c, int id ){
        int result = -1;
        try{
            for(Fotografia actual: new FactoresAmbientalesDAO().mostrarFacAmbientalesFOTOS(id,c)){

                File fdelete = new File(actual.getRutaFoto());
                if (fdelete.exists()) {
                    fdelete.delete();
                }
            }

            AdminSQLite adminSQLite = new AdminSQLite(c, "bd_preventive", null, 1);
            SQLiteDatabase bd = adminSQLite.getWritableDatabase();

            result = bd.delete("factoresAmbientalesFotos","idDatosGenerales ="+id,null);
            bd.close();
        }catch (Exception e){

        }

        return  result;
    }
}
