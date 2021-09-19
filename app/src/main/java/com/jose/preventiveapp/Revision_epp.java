package com.jose.preventiveapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.UnicodeSetSpanner;
import android.os.Bundle;
import android.text.Html;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import dao.AsistentesCharlaDAO;
import dao.CondicionesEsctructuralesDAO;
import dao.DatosGeneralesDAO;
import dao.EquiposHerramientasOtrosDAO;
import dao.EstadoEppDAO;
import dao.EtapasTareaDAO;
import dao.FactoresAmbientalesDAO;
import dao.IdActualDAO;
import dao.ObservacionesDAO;
import dao.RiesgosMedidasPreventDAO;
import dao.SeleccionEppDAO;
import dao.TemasCharlaDAO;
import dao.TemasTratadosCharlaDAO;
import dao.TrabajadoresDAO;
import dto.EstadoEpp;
import dto.Fotografia;
import dto.SegundoPlano;
import dto.Trabajadores;
import fragment_revision_epp.Estado_epp;

public class Revision_epp extends AppCompatActivity implements Revision_epp_dt  {
    private ImageView ivAtras;
    private List<EstadoEpp> estadoEppGloblList = new ArrayList<>();
    private TextView tvNom, tvDocumento, tvDocu;
    private List<Trabajadores> trabajadoresList = new ArrayList<>();
    private int indiceTra=0;
    private List<String> fotosBase64List = new ArrayList<>();
    private String imagen;
    private  String fotos_estructurales ="";
    private  String fotos_facAmbientales ="";
    private  String fotos_equiposHerra ="";
    private boolean enServidor = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revision_epp);
        ivAtras=(ImageView)findViewById(R.id.iv_atras_ce);
        tvNom =(TextView)findViewById(R.id.tvNomTrab) ;
        tvDocumento= (TextView)findViewById(R.id.tvDocumento);
        tvDocu= (TextView)findViewById(R.id.tvDocu);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.ct_revisonEpp, new Estado_epp());
        fragmentTransaction.commit();


        ivAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                msg();
            }
        });


       try{
            int id = new IdActualDAO().mostrarIdActual(this);
            for(Trabajadores actual: new TrabajadoresDAO().mostrarTrabajadoresActual(id,this)){
                trabajadoresList.add(actual);
            }

           tvNom.setText(trabajadoresList.get(0).getNombre());

            if(trabajadoresList.get(0).getRun() ==null){
                tvDocu.setText("Pasaporte: ");
                tvDocumento.setText(trabajadoresList.get(0).getPasaporte());
            }else{
                tvDocumento.setText(trabajadoresList.get(0).getRun());
                tvDocu.setText("Run: ");
            }

       }catch (Exception e){

       }


    }


    @Override
    public void onBackPressed(){
        msg();
    }

    // Mensaje para comfirmar la salida de módulo
    public void msg(){

        AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
        alertbox.setMessage("¿Está seguro que desea interrumpir el registro?");
        alertbox.setCancelable(false);
        alertbox.setTitle(Html.fromHtml("<font color='#0DCF5E'>Atención</font>"));
        alertbox.setIcon(R.drawable.ic_help_100);

        alertbox.setPositiveButton(Html.fromHtml("<font color='#0DCF5E'>si</font>"), new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface arg0, int arg1) {
                startActivity(new Intent(getBaseContext(), Menu.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP));
                finish();
            }
        });

        alertbox.setNegativeButton(Html.fromHtml("<font color='#0DCF5E'>no</font>"), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertbox.show();
    }

    //metodo de interface revision_epp_dt recibe una lista de epp que se agrega a otra lista de epps con el nombre del trabajador
    @Override
    public void revisionEppList(List<EstadoEpp> eppRevisadosList){
        for(EstadoEpp actual: eppRevisadosList){
            estadoEppGloblList.add(new EstadoEpp(tvNom.getText().toString().trim(),actual.getNombreEpp(),actual.getEstado()));

        }
    }

    //metodo recibe un true si se chequea los epps del trabajador de la lista de trabajadores, si es así indiceTra incrementa en uno
    //para pasar al trabajador y que se imprima en pantalla su nombre runo o si pasaporte
    @Override
    public void trabajadorChecado(Boolean aBoolean) {
        try{
            if(indiceTra < trabajadoresList.size()){
                indiceTra ++;
                tvNom.setText(trabajadoresList.get(indiceTra).getNombre());
                if(trabajadoresList.get(indiceTra).getRun() ==null){
                    tvDocu.setText("Pasaporte: ");
                    tvDocumento.setText(trabajadoresList.get(indiceTra).getPasaporte());
                }else{
                    tvDocumento.setText(trabajadoresList.get(indiceTra).getRun());
                    tvDocu.setText("Run: ");
                }
            }
        }catch (Exception e){

        }


        if(indiceTra==trabajadoresList.size()) {
            //FIN DEL REGISTRO

            int id = new IdActualDAO().mostrarIdActual(getBaseContext());
            new EstadoEppDAO().ingresarEstdoEpp(estadoEppGloblList, this, id);

            //CONSULTAS DAO
            Gson gson = new Gson();
            String datosGenerales = gson.toJson(new DatosGeneralesDAO().mostrarDatosGeneralesActual(id, getBaseContext()).get(0));
            String estructurales = gson.toJson(new CondicionesEsctructuralesDAO().mostrarCondicionesEstruc(id, getBaseContext()));

            // Se pasa los path de fotos a base64 y se guarda en lista de string

            int totalEstruc = new CondicionesEsctructuralesDAO().mostrarCondicionesEstrucFOTOS(id, getBaseContext()).size();
            List<Fotografia> fotosEstruc = new ArrayList<>();
            for (Fotografia actual : new CondicionesEsctructuralesDAO().mostrarCondicionesEstrucFOTOS(id, getBaseContext())) {

                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 4;
                Bitmap bitmap = BitmapFactory.decodeFile(actual.getRutaFoto(), options);
                imagen = convertiraString(bitmap);
                fotosEstruc.add(new Fotografia(actual.getNombreFoto(),imagen));
            }

            if(totalEstruc >0){
                fotos_estructurales = gson.toJson(fotosEstruc);
            }


            //fotos ambientales ******************************************************************************************************

            int totalAmb = new FactoresAmbientalesDAO().mostrarFacAmbientalesFOTOS(id, getBaseContext()).size();

            String facAmbientales = gson.toJson(new FactoresAmbientalesDAO().mostrarFacAmbientales(id, getBaseContext()));
            List<Fotografia> fotosAmb = new ArrayList<>();
            for (Fotografia actual : new FactoresAmbientalesDAO().mostrarFacAmbientalesFOTOS(id, getBaseContext())) {

                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 4;
                Bitmap bitmap = BitmapFactory.decodeFile(actual.getRutaFoto(), options);
                imagen = convertiraString(bitmap);
                fotosAmb.add(new Fotografia(actual.getNombreFoto(),imagen));
            }

            if(totalAmb >0){
                 fotos_facAmbientales = gson.toJson(fotosAmb);
            }else{

            }

            //***************************************************************************************************************

            String  equiposHerramientas = gson.toJson(new EquiposHerramientasOtrosDAO().mostrarEquiposHerraOtros(id,getBaseContext()));

            int totalEherra = new EquiposHerramientasOtrosDAO().mostrarEquiposHerraFOTOS(id, getBaseContext()).size();
            List<Fotografia> fotosEherra = new ArrayList<>();
            for (Fotografia actual : new EquiposHerramientasOtrosDAO().mostrarEquiposHerraFOTOS(id, getBaseContext())) {

                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 4;
                Bitmap bitmap = BitmapFactory.decodeFile(actual.getRutaFoto(), options);
                imagen = convertiraString(bitmap);
                fotosEherra.add(new Fotografia(actual.getNombreFoto(),imagen));
            }

            if(totalEherra >0){
                fotos_equiposHerra = gson.toJson(fotosEherra);
            }

            String etapasTarea = gson.toJson(new EtapasTareaDAO().mostrarEtapasTarea(id,getBaseContext()));

            String riesgosMedidas = gson.toJson(new RiesgosMedidasPreventDAO().mostrarRiesgosMedidasPreventivas(id,getBaseContext()));

            String epps =gson.toJson(new SeleccionEppDAO().mostrarSeleccionEpp(id,getBaseContext()));

            String trabajadores = gson.toJson(new TrabajadoresDAO().mostrarTrabajadoresActual(id,getBaseContext()));

            String temasCharla = gson.toJson(new TemasCharlaDAO().mostrarTemasCharla(id,getBaseContext()));

            String tratadosCharla = gson.toJson(new TemasTratadosCharlaDAO().mostrarTemasTratados(id,getBaseContext()));

            String asistentesCharla = gson.toJson(new AsistentesCharlaDAO().mostrarAsistentes(id,getBaseContext()));

            String estadoEpp = gson.toJson(new EstadoEppDAO().mostrarEstadosEpp(id,getBaseContext()));

            String observaciones = gson.toJson(new ObservacionesDAO().mostrarIdActual(id,getBaseContext()));


           //VOLLEY
            String url ="http://192.168.0.17:80/preventiveapp/servicedatamovil.php";
            //String url ="https://preventivo3.000webhostapp.com/Servicedatamovil.php";

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    eliminarRegistros(id);


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    PeriodicWorkRequest request = new PeriodicWorkRequest.Builder(SegundoPlano.class,1, TimeUnit.MINUTES)
                            .setInitialDelay(30,TimeUnit.MINUTES)
                            .build();
                    WorkManager.getInstance().enqueueUniquePeriodicWork("PERIODIC", ExistingPeriodicWorkPolicy.REPLACE,request);

                    Toast.makeText(getApplicationContext(), "REGISTRO DE FORMA LOCAL, REENVIO A SERVIDOR EN 1 HORA ", Toast.LENGTH_LONG).show();
                    enServidor=false;
                }

            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> parametros = new HashMap<String,String>();

                     parametros.put("dgenerales",datosGenerales);
                     parametros.put("estructurales",estructurales);
                     parametros.put("fotos_estructurales",fotos_estructurales);
                     parametros.put("facAmbientales",facAmbientales);
                     parametros.put("fotos_facAmbientales",fotos_facAmbientales);
                     parametros.put("equiposHerramientas",equiposHerramientas);
                     parametros.put("fotos_equiposHerra",fotos_equiposHerra);
                     parametros.put("etapasTarea",etapasTarea);
                     parametros.put("riesgosmedidas",riesgosMedidas);
                     parametros.put("epps",epps);
                     parametros.put("trabajadores",trabajadores);
                     parametros.put("temasCharla",temasCharla);
                     parametros.put("tratadosCharla",tratadosCharla);
                     parametros.put("asistentesCharla",asistentesCharla);
                     parametros.put("estadoEpp",estadoEpp);
                     parametros.put("observaciones",observaciones);

                    return parametros;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);

            /*
            if(enServidor==true){
                eliminarRegistros(id);
                new IdActualDAO().eliminarId(id,getBaseContext());
                Intent intent = new Intent(this,FinRegistro_revisionEpp.class);
                startActivity(intent);
                finish();
            }

             */


            new IdActualDAO().eliminarId(id,getBaseContext());
            Intent intent = new Intent(getApplicationContext(),FinRegistro_revisionEpp.class);
            startActivity(intent);
            finish();


        }

    }


    private String convertiraString(Bitmap bitmap){
        ByteArrayOutputStream array = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,array);
        byte[] imageByte = array.toByteArray();
        String imagenSting = Base64.encodeToString(imageByte,Base64.DEFAULT);
        return imagenSting;

    }

    public void eliminarRegistros(int id){

        new CondicionesEsctructuralesDAO().eliminarConEstructurales(getBaseContext(),id);
        new CondicionesEsctructuralesDAO().eliminarEstructuralesFotos(getBaseContext(),id);
        new FactoresAmbientalesDAO().eliminarAmbientales(getBaseContext(),id);
        new FactoresAmbientalesDAO().eliminarAmbientalesFotos(getBaseContext(),id);
        new EquiposHerramientasOtrosDAO().eliminarEquiposHerra(getBaseContext(),id);
        new EquiposHerramientasOtrosDAO().eliminarEquiposHerraFotos(getBaseContext(),id);
        new SeleccionEppDAO().eliminarEppSeleccionados(getBaseContext(),id);
        new TrabajadoresDAO().eliminarTrabajadores(getBaseContext(),id);
        new EtapasTareaDAO().eliminarEtapasTarea(getBaseContext(),id);
        new RiesgosMedidasPreventDAO().eliminarRiesgosMedidas(getBaseContext(),id);
        new ObservacionesDAO().eliminarObs(getBaseContext(),id);
        new TemasCharlaDAO().eliminarTemasCharla(getBaseContext(),id);
        new TemasTratadosCharlaDAO().eliminarTemasTratados(getBaseContext(),id);
        new AsistentesCharlaDAO().eliminarAsistentes(getBaseContext(),id);
        new EstadoEppDAO().eliminarEstadoEpp(getBaseContext(),id);
        new DatosGeneralesDAO().eliminarDatosGenerales(getBaseContext(),id);

    }


}