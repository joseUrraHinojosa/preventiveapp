package dto;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class AdminSQLite extends  SQLiteOpenHelper{
    public AdminSQLite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase bd_preventive) {

        bd_preventive.execSQL("create table usuario(run text primary key, nombre text, paterno text, materno text, email text, password integer)");

        //ID validadores
        bd_preventive.execSQL("create table idActual( id integer)");
        

        //DATOS GENERALES
        bd_preventive.execSQL("create table datoGenerales( idDatosGenerales integer primary key autoincrement, " +
                "nombre_empresa text, " +
                "codigoSitio text, " +
                "tipoTrabajo text," +
                "duracionTrabajo text," +
                "direccion text," +
                "ciudad text," +
                "comuna text," +
                "fecha text," +
                "hora text," +
                "supervisor text," +
                "run text)" );
               // "run text, FOREIGN KEY (run) REFERENCES usuario (run))


        //CONDICIONES ESTRUCTURALES
        bd_preventive.execSQL("create table condicionesEstructurales(" +
                "idEstructurales integer primary key autoincrement," +
                "criterio1 text, " +
                "criterio2 text," +
                "criterio3 text," +
                "criterio4 text," +
                "criterio5 text," +
                "criterio6 text," +
                "idDatosGenerales  INTEGER NOT NULL,  FOREIGN KEY (idDatosGenerales) REFERENCES datosGenerales (idDatosGenerales))");

        bd_preventive.execSQL("create table condicionesEstructuralesFotos(idCestructuralesFotos integer primary key autoincrement, " +
                "nombreFoto text, rutaFoto text, idDatosGenerales  INTEGER NOT NULL,  FOREIGN KEY (idDatosGenerales) REFERENCES datosGenerales (idDatosGenerales))");

        //FACTORES AMBIENTALES
        bd_preventive.execSQL("create table factoresAmbientales(" +
                "idFacAmbientales integer primary key autoincrement," +
                "criterio1 text, criterio2 text, criterio3 text, criterio4 text, criterio5 text, criterio6 text," +
                "criterio7 text, criterio8 text, criterio9 text, criterio10 text,"+
                "idDatosGenerales  INTEGER NOT NULL,  FOREIGN KEY (idDatosGenerales) REFERENCES datosGenerales (idDatosGenerales))");

        bd_preventive.execSQL("create table factoresAmbientalesFotos(idFacAmbientalesFoto integer primary key autoincrement, " +
                " nombreFoto text ,rutaFoto text, idDatosGenerales  INTEGER NOT NULL,  FOREIGN KEY (idDatosGenerales) REFERENCES datosGenerales (idDatosGenerales))");

        //EQUIPOS HERRAMIENTAS OTROS
        bd_preventive.execSQL("create table equiposHerramientas(" +
                "idEquiposHerra integer primary key autoincrement," +
                "criterio1 text, criterio2 text, criterio3 text, criterio4 text," +
                "idDatosGenerales  INTEGER NOT NULL,  FOREIGN KEY (idDatosGenerales) REFERENCES datosGenerales (idDatosGenerales))");

        bd_preventive.execSQL("create table equiposHerramientasFotos(idEquipoHerraFoto integer primary key autoincrement, " +
                "nombreFoto text, rutaFoto text, idDatosGenerales  INTEGER NOT NULL,  FOREIGN KEY (idDatosGenerales) REFERENCES datosGenerales (idDatosGenerales))");


        //ETAPAS TAREA
        bd_preventive.execSQL("create table etapasTarea(idEtapasTarea integer primary key autoincrement, " +
                "descripcionEtapa text, idDatosGenerales  INTEGER NOT NULL,  FOREIGN KEY (idDatosGenerales) REFERENCES datosGenerales (idDatosGenerales))");

        //RIESGOS MEDIDAS PREVENTIVAS
        bd_preventive.execSQL("create table riesgosMedidasPrevent(idRiesgosMedidas integer primary key autoincrement, " +
                "riesgo text, medidaPreventiva text, idDatosGenerales  INTEGER NOT NULL,  FOREIGN KEY (idDatosGenerales) REFERENCES datosGenerales (idDatosGenerales))");


        //SELECCION DE EPP
        bd_preventive.execSQL("create table seleccionEpp(idSelecionEpp integer primary key autoincrement, descripcionEpp text," +
                        "idDatosGenerales  INTEGER NOT NULL,  FOREIGN KEY (idDatosGenerales) REFERENCES datosGenerales (idDatosGenerales))");

        //TRABAJADORES PERMANENTES
        bd_preventive.execSQL("create table trabajadores(idTrabajador integer primary key autoincrement, nombre text, run text, pasaporte text," +
                "idDatosGenerales  INTEGER NOT NULL,  FOREIGN KEY (idDatosGenerales) REFERENCES datosGenerales (idDatosGenerales)  )");

        //TRABAJADOR ACTUAL
        bd_preventive.execSQL("create table trabajadoresActual(idTraActual integer primary key autoincrement, nombre text, run text, pasaporte text, " +
                "idDatosGenerales  INTEGER NOT NULL,  FOREIGN KEY (idDatosGenerales) REFERENCES datosGenerales (idDatosGenerales))");



        //NOMINA ASISTENTES CHARLA 5 MINUTOS
        bd_preventive.execSQL("create table asistentesCharla(idAsistentesCharla integer primary key autoincrement, nombre text, run text, pasaporte text, " +
                "idDatosGenerales  INTEGER NOT NULL,  FOREIGN KEY (idDatosGenerales) REFERENCES datosGenerales (idDatosGenerales))");

        //ESTADO EPP

      //  bd_preventive.execSQL("create table estadoEpp(idEstadoEpp integer primary key autoincrement, nombreEpp text, estado text, " +
      //          "idTraActual   INTEGER NOT NULL,  FOREIGN KEY (idTraActual ) REFERENCES trabajadoresActual (idTraActual ))");


        //TEMAS A TRATAR CHARLA
        bd_preventive.execSQL("create table temasCharla(idTemasCharla integer primary key autoincrement, descripcionTema text, " +
                "idDatosGenerales  INTEGER NOT NULL,  FOREIGN KEY (idDatosGenerales) REFERENCES datosGenerales (idDatosGenerales))");

        //TEMAS TRATADOS EN LA  CHARLA
        bd_preventive.execSQL("create table temasTratadosCharla(idTemasTratados integer primary key autoincrement, descripTemaTratado text, " +
                "idDatosGenerales  INTEGER NOT NULL,  FOREIGN KEY (idDatosGenerales) REFERENCES datosGenerales (idDatosGenerales))");


        //ESTADO EPP
        bd_preventive.execSQL("create table estadoEpp(idEestadoEpp integer primary key autoincrement,  nombreTra text, nomEpp text, estadoEpp text," +
                "idDatosGenerales  INTEGER NOT NULL,  FOREIGN KEY (idDatosGenerales) REFERENCES datosGenerales (idDatosGenerales))");


        //OBSERVACIONES
        bd_preventive.execSQL("create table observaciones(idObs integer primary key autoincrement,  titulo text, descripcion text, " +
                "idDatosGenerales  INTEGER NOT NULL,  FOREIGN KEY (idDatosGenerales) REFERENCES datosGenerales (idDatosGenerales))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
