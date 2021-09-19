package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import dto.AdminSQLite;
import dto.Usuario;

public class UsuarioDAO {

    public  void ingresarUsuario(Usuario usu, Context c){

        try{
            AdminSQLite adminSQLite = new AdminSQLite(c, "bd_preventive", null, 1);
            SQLiteDatabase bd = adminSQLite.getWritableDatabase();

            ContentValues registro = new ContentValues();

            registro.put("run", usu.getRun());
            registro.put("nombre", usu.getNombre());
            registro.put("paterno", usu.getPaterno());
            registro.put("materno", usu.getMaterno());
            registro.put("email", usu.getEmail());
            registro.put("password", usu.getPassword());

            bd.insert("usuario", null, registro);
            bd.close();

        }catch (Exception e){

        }
    }

    public Usuario mostrarUsuario(Context c){
        Usuario usu = new Usuario();
        try {

            AdminSQLite adminSQLite1 = new AdminSQLite(c,"bd_preventive",null,1);
            SQLiteDatabase bd = adminSQLite1.getWritableDatabase();

            Cursor fila = bd.rawQuery
                    ("select * from usuario",null);
            if(fila.moveToFirst()){

                usu.setRun(fila.getString(0));
                usu.setNombre(fila.getString(1));
                usu.setPaterno(fila.getString(2));
                usu.setMaterno(fila.getString(3));
                usu.setEmail(fila.getString(4));
                usu.setPassword(fila.getInt(5));

            }else{
                //no existe usuario
            }
            bd.close();

        }catch (Exception e){

        }
        return  usu;
    }


    public void actualizarUsuario(Usuario usu , Context c){
        try {

            AdminSQLite adminSQLite = new AdminSQLite(c, "bd_preventive", null, 1);
            SQLiteDatabase bd = adminSQLite.getWritableDatabase();

            ContentValues registro = new ContentValues();
            // registro.put("run", usu);
            registro.put("nombre", usu.getNombre());
            registro.put("paterno", usu.getPaterno());
            registro.put("materno", usu.getMaterno());
            registro.put("email", usu.getEmail());
            registro.put("password", usu.getPassword());

            bd.update("usuario", registro, "run = '"+ usu.getRun()+"'", null);
            bd.close();


        }catch (Exception e){

        }


    }

}
