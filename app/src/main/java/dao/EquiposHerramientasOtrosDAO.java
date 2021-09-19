package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import dto.AdminSQLite;
import dto.EquiposHerramientasOtros;
import dto.FactoresAmbientales;
import dto.Fotografia;

public class EquiposHerramientasOtrosDAO {

    public  void ingresarEquiposHerraOtros(EquiposHerramientasOtros herramientasOtros, Context c, int idDatosGenerales){

        try{
            AdminSQLite adminSQLite = new AdminSQLite(c, "bd_preventive", null, 1);
            SQLiteDatabase bd = adminSQLite.getWritableDatabase();

            ContentValues registro = new ContentValues();

            registro.put("criterio1", herramientasOtros.getCriterio1());
            registro.put("criterio2", herramientasOtros.getCriterio2());
            registro.put("criterio3", herramientasOtros.getCriterio3());
            registro.put("criterio4", herramientasOtros.getCriterio4());
            registro.put("idDatosGenerales", idDatosGenerales);

            bd.insert("equiposHerramientas", null, registro);
            bd.close();

        }catch (Exception e){

        }
    }


    public EquiposHerramientasOtros mostrarEquiposHerraOtros(int id, Context c){
        EquiposHerramientasOtros herramientasOtros = new EquiposHerramientasOtros();

        try{

            AdminSQLite adminSQLite = new AdminSQLite(c,"bd_preventive",null,1);
            SQLiteDatabase bd = adminSQLite.getWritableDatabase();

            Cursor fila = bd.rawQuery
                    ("select * from equiposHerramientas where idDatosGenerales = "+id,null);
            if(fila.moveToFirst()){

                do{
                    herramientasOtros.setIdEquposHerramientas(fila.getInt(0));
                    herramientasOtros.setCriterio1(fila.getString(1));
                    herramientasOtros.setCriterio2(fila.getString(2));
                    herramientasOtros.setCriterio3(fila.getString(3));
                    herramientasOtros.setCriterio4(fila.getString(4));

                } while (fila.moveToNext());
            }else{

            }
            bd.close();

        } catch (Exception e){

        }
        return herramientasOtros;
    }


    //FOTOS
    public  void ingresarEquiposHerraFOTOS(EquiposHerramientasOtros herramientasOtros, Context c, int idDatosGenerales){

        try{
            for(Fotografia actual: herramientasOtros.getFotos()){
                AdminSQLite adminSQLite = new AdminSQLite(c, "bd_preventive", null, 1);
                SQLiteDatabase bd = adminSQLite.getWritableDatabase();

                ContentValues registro = new ContentValues();
                registro.put("nombreFoto", actual.getNombreFoto());
                registro.put("rutaFoto", actual.getRutaFoto());
                registro.put("idDatosGenerales", idDatosGenerales);
                bd.insert("equiposHerramientasFotos", null, registro);
                bd.close();
            }

        }catch (Exception e){

        }
    }

    public List<Fotografia> mostrarEquiposHerraFOTOS(int id, Context c){

        List<Fotografia> listFotos = new ArrayList<>();

        try{
            AdminSQLite adminSQLite = new AdminSQLite(c,"bd_preventive",null,1);
            SQLiteDatabase bd = adminSQLite.getWritableDatabase();

            Cursor fila = bd.rawQuery
                    ("select * from equiposHerramientasFotos where idDatosGenerales = "+id,null);
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


    public int eliminarEquiposHerra(Context c, int id ){
        int result = -1;

        try{
            AdminSQLite adminSQLite = new AdminSQLite(c, "bd_preventive", null, 1);
            SQLiteDatabase bd = adminSQLite.getWritableDatabase();

            result = bd.delete("equiposHerramientas","idDatosGenerales ="+id,null);
            bd.close();
        }catch (Exception e){

        }



        return  result;
    }

    public int eliminarEquiposHerraFotos(Context c, int id ){

        int result = -1;

        try{
            for(Fotografia actual: new EquiposHerramientasOtrosDAO().mostrarEquiposHerraFOTOS(id,c)){

                File fdelete = new File(actual.getRutaFoto());
                if (fdelete.exists()) {
                    fdelete.delete();
                }
            }

            AdminSQLite adminSQLite = new AdminSQLite(c, "bd_preventive", null, 1);
            SQLiteDatabase bd = adminSQLite.getWritableDatabase();

            result = bd.delete("equiposHerramientasFotos","idDatosGenerales ="+id,null);
            bd.close();
        }catch (Exception e){

        }


        return  result;
    }

}
