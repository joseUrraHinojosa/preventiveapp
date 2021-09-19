package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import dto.AdminSQLite;
import dto.MedidasPreventivas;

public class RiesgosMedidasPreventDAO {

        public void ingresarRiesgosMedidasPrevent(List<MedidasPreventivas> riegosMedidasList, Context c, int idDatosGenerales) {

            if(riegosMedidasList != null){
                try {
                    for (MedidasPreventivas actual : riegosMedidasList) {
                        AdminSQLite adminSQLite = new AdminSQLite(c, "bd_preventive", null, 1);
                        SQLiteDatabase bd = adminSQLite.getWritableDatabase();
                        ContentValues registro = new ContentValues();

                        registro.put("riesgo", actual.getRiesgo());
                        registro.put("medidaPreventiva", actual.getMedidaPreventivas());
                        registro.put("idDatosGenerales", idDatosGenerales);

                        bd.insert("riesgosMedidasPrevent", null, registro);
                        bd.close();
                    }

                } catch (Exception e) {

                }
            }


        }

        public List<MedidasPreventivas> mostrarRiesgosMedidasPreventivas(int id, Context c) {
            List<MedidasPreventivas> riesgosMedidasList = new ArrayList<>();
            try {

                AdminSQLite adminSQLite = new AdminSQLite(c, "bd_preventive", null, 1);
                SQLiteDatabase bd = adminSQLite.getWritableDatabase();

                Cursor fila = bd.rawQuery
                        ("select * from riesgosMedidasPrevent where idDatosGenerales = "+id, null);
                if (fila.moveToFirst()) {

                    do {

                        MedidasPreventivas md = new MedidasPreventivas();
                        md.setRiesgo(fila.getString(1));
                        md.setMedidaPreventivas(fila.getString(2));
                        riesgosMedidasList.add(md);

                    } while (fila.moveToNext());
                } else {

                }
                bd.close();


            } catch (Exception e) {

            }

            return riesgosMedidasList;
        }


    public int eliminarRiesgosMedidas(Context c, int id ){
        int result = -1;

        try{
            AdminSQLite adminSQLite = new AdminSQLite(c, "bd_preventive", null, 1);
            SQLiteDatabase bd = adminSQLite.getWritableDatabase();

            result = bd.delete("riesgosMedidasPrevent","idDatosGenerales="+id,null);
            bd.close();
        }catch (Exception e){

        }


        return  result;
    }


}