package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import dto.AdminSQLite;
import dto.DatosGenerales;

public class   DatosGeneralesDAO {


    public int recuperaidUltimoRegistro(Context c) {
        int id = -1;

        try {

            AdminSQLite adminSQLite2 = new AdminSQLite(c, "bd_preventive", null, 1);
            SQLiteDatabase bd2 = adminSQLite2.getWritableDatabase();

            Cursor fila2 = bd2.rawQuery
                    ("select * from datoGenerales  WHERE idDatosGenerales = (SELECT MAX(idDatosGenerales) FROM datoGenerales); ", null);
            if (fila2.moveToFirst()) {
                id = fila2.getInt(0);

            } else {

            }
            bd2.close();

        } catch (Exception e) {

        }

        return id;
    }


    public void ingresarDatosGenerales(DatosGenerales d, Context c) {

        AdminSQLite adminSQLite = new AdminSQLite(c, "bd_preventive", null, 1);
        SQLiteDatabase bd = adminSQLite.getWritableDatabase();

        ContentValues registro = new ContentValues();

        registro.put("nombre_empresa", d.getEmpresaMandante());
        registro.put("codigoSitio", d.getCodigoSitio());
        registro.put("tipoTrabajo", d.getTipoTrabajo());
        registro.put("duracionTrabajo", d.getDuracionTrabajo());
        registro.put("direccion", d.getDireccion());
        registro.put("ciudad", d.getCiudad());
        registro.put("comuna", d.getComuna());
        registro.put("fecha", d.getFecha());
        registro.put("hora", d.getHora());
        registro.put("supervisor", d.getSupervisor());
        registro.put("run", d.getRunSupervisor());


        bd.insert("datoGenerales", null, registro);
        bd.close();

    }

    public List<DatosGenerales> mostrarDatosGenerales(Context c) {
        List<DatosGenerales> datosGeneralesList = new ArrayList<>();

        try {

            AdminSQLite adminSQLite2 = new AdminSQLite(c, "bd_preventive", null, 1);
            SQLiteDatabase bd2 = adminSQLite2.getWritableDatabase();

            Cursor fila2 = bd2.rawQuery
                    ("select * from datoGenerales order by idDatosGenerales desc ", null);
            if (fila2.moveToFirst()) {

                do {
                    DatosGenerales d = new DatosGenerales();
                    d.setIdDatosGenerales(fila2.getInt(0));
                    d.setEmpresaMandante(fila2.getString(1));
                    d.setCodigoSitio(fila2.getString(2));
                    d.setTipoTrabajo(fila2.getString(3));
                    d.setDuracionTrabajo(fila2.getString(4));
                    d.setDireccion(fila2.getString(5));
                    d.setCiudad(fila2.getString(6));
                    d.setComuna(fila2.getString(7));
                    d.setFecha(fila2.getString(8));
                    d.setHora(fila2.getString(9));
                    d.setSupervisor(fila2.getString(10));
                    d.setRunSupervisor(fila2.getString(11));

                    datosGeneralesList.add(d);
                } while (fila2.moveToNext());

            } else {

            }
            bd2.close();


        } catch (Exception e) {

        }

        return datosGeneralesList;

    }



    public List<DatosGenerales> mostrarDatosGeneralesActual(int id, Context c){
        List<DatosGenerales> datosGeneralesList = new ArrayList<>();

        try{

            AdminSQLite adminSQLite2 = new AdminSQLite(c,"bd_preventive",null,1);
            SQLiteDatabase bd2 = adminSQLite2.getWritableDatabase();

            Cursor fila2 = bd2.rawQuery
                    ("select * from datoGenerales where idDatosGenerales ="+id,null);
            if(fila2.moveToFirst()){

                do {
                    DatosGenerales d = new DatosGenerales();
                    d.setIdDatosGenerales(fila2.getInt(0));
                    d.setEmpresaMandante(fila2.getString(1));
                    d.setCodigoSitio(fila2.getString(2));
                    d.setTipoTrabajo(fila2.getString(3));
                    d.setDuracionTrabajo(fila2.getString(4));
                    d.setDireccion(fila2.getString(5));
                    d.setCiudad(fila2.getString(6));
                    d.setComuna(fila2.getString(7));
                    d.setFecha(fila2.getString(8));
                    d.setHora(fila2.getString(9));
                    d.setSupervisor(fila2.getString(10));
                    d.setRunSupervisor(fila2.getString(11));

                    datosGeneralesList.add(d);
                } while (fila2.moveToNext());

            }else{

            }
            bd2.close();


        } catch (Exception e){

        }

        return datosGeneralesList;

    }


    public int eliminarDatosGenerales(Context c, int id ){
        int result = -1;

        try{
            AdminSQLite adminSQLite = new AdminSQLite(c, "bd_preventive", null, 1);
            SQLiteDatabase bd = adminSQLite.getWritableDatabase();

            result = bd.delete("datoGenerales","idDatosGenerales ="+id,null);
            bd.close();
        }catch (Exception e){

        }


        return  result;
    }




    public List<DatosGenerales> mostrarDatosGeneralesReenvio(Context c){
        List<DatosGenerales> datosGeneralesList = new ArrayList<>();

        try{

            AdminSQLite adminSQLite2 = new AdminSQLite(c,"bd_preventive",null,1);
            SQLiteDatabase bd2 = adminSQLite2.getWritableDatabase();

            Cursor fila2 = bd2.rawQuery
                    ("select * from datoGenerales",null);
            if(fila2.moveToFirst()){

                do {
                    DatosGenerales d = new DatosGenerales();
                    d.setIdDatosGenerales(fila2.getInt(0));
                    d.setEmpresaMandante(fila2.getString(1));
                    d.setCodigoSitio(fila2.getString(2));
                    d.setTipoTrabajo(fila2.getString(3));
                    d.setDuracionTrabajo(fila2.getString(4));
                    d.setDireccion(fila2.getString(5));
                    d.setCiudad(fila2.getString(6));
                    d.setComuna(fila2.getString(7));
                    d.setFecha(fila2.getString(8));
                    d.setHora(fila2.getString(9));
                    d.setSupervisor(fila2.getString(10));
                    d.setRunSupervisor(fila2.getString(11));

                    datosGeneralesList.add(d);
                } while (fila2.moveToNext());

            }else{

            }
            bd2.close();


        } catch (Exception e){

        }

        return datosGeneralesList;

    }


}
