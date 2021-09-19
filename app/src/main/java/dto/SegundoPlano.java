package dto;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

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

import dao.AsistentesCharlaDAO;
import dao.CondicionesEsctructuralesDAO;
import dao.DatosGeneralesDAO;
import dao.EquiposHerramientasOtrosDAO;
import dao.EstadoEppDAO;
import dao.EtapasTareaDAO;
import dao.FactoresAmbientalesDAO;
import dao.ObservacionesDAO;
import dao.RiesgosMedidasPreventDAO;
import dao.SeleccionEppDAO;
import dao.TemasCharlaDAO;
import dao.TemasTratadosCharlaDAO;
import dao.TrabajadoresDAO;

public class SegundoPlano extends Worker {

    private  String fotos_estructurales ="";
    private  String fotos_facAmbientales ="";
    private  String fotos_equiposHerra ="";

    public SegundoPlano(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {

        reenviarDatos();
        return Result.success();
    }


    public void  reenviarDatos(){

        int TotalRegistros = new DatosGeneralesDAO().mostrarDatosGeneralesReenvio(getApplicationContext()).size();
        if(TotalRegistros > 0){

            for(DatosGenerales actual1: new DatosGeneralesDAO().mostrarDatosGeneralesReenvio(getApplicationContext())){

                int id = actual1.getIdDatosGenerales();
                //CONSULTAS DAO
                Gson gson = new Gson();
                String datosGenerales = gson.toJson(new DatosGeneralesDAO().mostrarDatosGeneralesActual(id, getApplicationContext()).get(0));
                String estructurales = gson.toJson(new CondicionesEsctructuralesDAO().mostrarCondicionesEstruc(id, getApplicationContext()));

                // Se pasa los path de fotos a base64 y se guarda en lista de string
                int totalEstruc = new CondicionesEsctructuralesDAO().mostrarCondicionesEstrucFOTOS(id, getApplicationContext()).size();
                List<Fotografia> fotosEstruc = new ArrayList<>();
                for (Fotografia actual : new CondicionesEsctructuralesDAO().mostrarCondicionesEstrucFOTOS(id, getApplicationContext())) {

                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inSampleSize = 4;
                    Bitmap bitmap = BitmapFactory.decodeFile(actual.getRutaFoto(), options);
                    String imagen = convertiraString(bitmap);
                    fotosEstruc.add(new Fotografia(actual.getNombreFoto(),imagen));
                }

                if(totalEstruc >0){
                    fotos_estructurales = gson.toJson(fotosEstruc);
                }

                //fotos ambientales ******************************************************************************************************

                int totalAmb = new FactoresAmbientalesDAO().mostrarFacAmbientalesFOTOS(id, getApplicationContext()).size();

                String facAmbientales = gson.toJson(new FactoresAmbientalesDAO().mostrarFacAmbientales(id, getApplicationContext()));
                List<Fotografia> fotosAmb = new ArrayList<>();
                for (Fotografia actual : new FactoresAmbientalesDAO().mostrarFacAmbientalesFOTOS(id, getApplicationContext())) {

                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inSampleSize = 4;
                    Bitmap bitmap = BitmapFactory.decodeFile(actual.getRutaFoto(), options);
                    String imagen = convertiraString(bitmap);
                    fotosAmb.add(new Fotografia(actual.getNombreFoto(),imagen));
                }

                if(totalAmb >0){
                    fotos_facAmbientales = gson.toJson(fotosAmb);
                }else{

                }

                //***************************************************************************************************************

                String  equiposHerramientas = gson.toJson(new EquiposHerramientasOtrosDAO().mostrarEquiposHerraOtros(id,getApplicationContext()));

                int totalEherra = new EquiposHerramientasOtrosDAO().mostrarEquiposHerraFOTOS(id, getApplicationContext()).size();
                List<Fotografia> fotosEherra = new ArrayList<>();
                for (Fotografia actual : new EquiposHerramientasOtrosDAO().mostrarEquiposHerraFOTOS(id, getApplicationContext())) {

                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inSampleSize = 4;
                    Bitmap bitmap = BitmapFactory.decodeFile(actual.getRutaFoto(), options);
                    String imagen = convertiraString(bitmap);
                    fotosEherra.add(new Fotografia(actual.getNombreFoto(),imagen));
                }

                if(totalEherra >0){
                    fotos_equiposHerra = gson.toJson(fotosEherra);
                }

                String etapasTarea = gson.toJson(new EtapasTareaDAO().mostrarEtapasTarea(id,getApplicationContext()));

                String riesgosMedidas = gson.toJson(new RiesgosMedidasPreventDAO().mostrarRiesgosMedidasPreventivas(id,getApplicationContext()));

                String epps =gson.toJson(new SeleccionEppDAO().mostrarSeleccionEpp(id,getApplicationContext()));

                String trabajadores = gson.toJson(new TrabajadoresDAO().mostrarTrabajadoresActual(id,getApplicationContext()));

                String temasCharla = gson.toJson(new TemasCharlaDAO().mostrarTemasCharla(id,getApplicationContext()));

                String tratadosCharla = gson.toJson(new TemasTratadosCharlaDAO().mostrarTemasTratados(id,getApplicationContext()));

                String asistentesCharla = gson.toJson(new AsistentesCharlaDAO().mostrarAsistentes(id,getApplicationContext()));

                String estadoEpp = gson.toJson(new EstadoEppDAO().mostrarEstadosEpp(id,getApplicationContext()));

                String observaciones = gson.toJson(new ObservacionesDAO().mostrarIdActual(id,getApplicationContext()));



                //VOLLEY
                //String url ="http://192.168.0.17:80/preventiveapp/servicedatamovil.php";
                String url ="https://preventivo3.000webhostapp.com/Servicedatamovil.php";

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        eliminarRegistros(id);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.toString(), Toast.LENGTH_LONG).show();
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
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);

            }

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

        new CondicionesEsctructuralesDAO().eliminarConEstructurales(getApplicationContext(),id);
        new CondicionesEsctructuralesDAO().eliminarEstructuralesFotos(getApplicationContext(),id);
        new FactoresAmbientalesDAO().eliminarAmbientales(getApplicationContext(),id);
        new FactoresAmbientalesDAO().eliminarAmbientalesFotos(getApplicationContext(),id);
        new EquiposHerramientasOtrosDAO().eliminarEquiposHerra(getApplicationContext(),id);
        new EquiposHerramientasOtrosDAO().eliminarEquiposHerraFotos(getApplicationContext(),id);
        new SeleccionEppDAO().eliminarEppSeleccionados(getApplicationContext(),id);
        new TrabajadoresDAO().eliminarTrabajadores(getApplicationContext(),id);
        new  EtapasTareaDAO().eliminarEtapasTarea(getApplicationContext(),id);
        new RiesgosMedidasPreventDAO().eliminarRiesgosMedidas(getApplicationContext(),id);
        new ObservacionesDAO().eliminarObs(getApplicationContext(),id);
        new TemasCharlaDAO().eliminarTemasCharla(getApplicationContext(),id);
        new TemasTratadosCharlaDAO().eliminarTemasTratados(getApplicationContext(),id);
        new AsistentesCharlaDAO().eliminarAsistentes(getApplicationContext(),id);
        new EstadoEppDAO().eliminarEstadoEpp(getApplicationContext(),id);
        new DatosGeneralesDAO().eliminarDatosGenerales(getApplicationContext(),id);


    }

}
