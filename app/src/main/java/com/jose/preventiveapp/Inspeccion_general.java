package com.jose.preventiveapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dao.CondicionesEsctructuralesDAO;
import dao.EquiposHerramientasOtrosDAO;
import dao.EtapasTareaDAO;
import dao.FactoresAmbientalesDAO;
import dao.IdActualDAO;
import dao.ObservacionesDAO;
import dao.RiesgosMedidasPreventDAO;
import dao.SeleccionEppDAO;
import dao.TrabajadoresDAO;
import dao.UsuarioDAO;
import dto.CondicionesEstructurales;
import dto.DatosGenerales;
import dao.DatosGeneralesDAO;
import dto.EquiposHerramientasOtros;
import dto.FactoresAmbientales;
import dto.MedidasPreventivas;
import dto.Observaciones;
import dto.Trabajadores;
import fragment_inspeccion_general.Condiciones_estructurales;
import fragment_inspeccion_general.Datos_generales;
import fragment_inspeccion_general.Nomina_trabajadores;
import fragment_inspeccion_general.Riesgos_;
import fragment_inspeccion_general.Seleccion_epp;
import fragment_revision_epp.Estado_epp;


public class Inspeccion_general extends AppCompatActivity implements Inspeccion_general_dt {

    private List<DatosGenerales> dGenerales = new ArrayList<>();
    private List<CondicionesEstructurales> list_condicionesEstruc = new ArrayList<>();

    private List<FactoresAmbientales> list_facAmbientales = new ArrayList<>();
    private List<String> list_facAmbientalesFotos = new ArrayList<>();

    private List<EquiposHerramientasOtros> list_EquiposHerraOtros = new ArrayList<>();
    private List<String> list_EquiposHerraOtrosFotos = new ArrayList<>();

    private List<String> list_etapasTarea = new ArrayList<>();
    private List<MedidasPreventivas> list_riesgosMedidas = new ArrayList<>();

    private List<String> list_eppSeleccionados = new ArrayList<>();
    private List<Trabajadores> list_trabajadores = new ArrayList<>();

    private List<Observaciones> observList = new ArrayList<>();
    private  Observaciones obsEstructurales = new Observaciones();
    private  Observaciones obsAmbientales = new Observaciones();
    private  Observaciones obsEquiposHerramientas = new Observaciones();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspeccion_general);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.ct_inspeccion_general, new Datos_generales());
        fragmentTransaction.commit();

    }

    //control de boton de retroceso
    @Override
    public void onBackPressed(){
        msg();

    }
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


    // se reciben los registros de los fragment a traves de la interfaz inspeccion_general_dt
    //***************************************************************************************
    @Override
    public void datosGenerales(DatosGenerales d) {
        dGenerales.clear();
        dGenerales.add(d);
    }


    @Override
    public void condicionesEstructurales(CondicionesEstructurales estructurales) {
      list_condicionesEstruc.clear();
      list_condicionesEstruc.add(estructurales);

    }


    @Override
    public void factoresAmbientales(FactoresAmbientales ambientales) {
        list_facAmbientales.clear();
        list_facAmbientales.add(ambientales);
    }

    @Override
    public void equiposHerraOtos(EquiposHerramientasOtros herramientasOtros) {
        list_EquiposHerraOtros.clear();
        list_EquiposHerraOtros.add(herramientasOtros);
    }

    @Override
    public void etapasTarea(List<String> etapas) {
        list_etapasTarea.clear();
        for(String actual: etapas){
            list_etapasTarea.add(actual);
        }
    }

    @Override
    public void riesgosMedidaPrevent(List<MedidasPreventivas> riesgosMedidasList) {
        list_riesgosMedidas.clear();
        for(MedidasPreventivas actual: riesgosMedidasList){
            list_riesgosMedidas.add(actual);
        }

    }


    @Override
    public void seleccionEPP(List<String> eppSeleccionadosList) {
        list_eppSeleccionados.clear();
        for(String actual: eppSeleccionadosList){
            list_eppSeleccionados.add(actual);
        }
    }

    @Override
    public void seleccionOtrosEPP(List<String> otrosEppList) {
        for(String actual: otrosEppList){

            list_eppSeleccionados.add(actual);
        }

    }

    @Override
    public void trabajadoresInterface(List<Trabajadores> trabajadores) {
        list_trabajadores.clear();

        for(Trabajadores actual: trabajadores){
            list_trabajadores.add(actual);
        }
    }

    @Override
    public void obsEstructurales(Observaciones obs) {
        obsEstructurales = obs;

    }

    @Override
    public void obsAmbientales(Observaciones obs) {
        obsAmbientales = obs;
    }

    @Override
    public void obsEquiposHerramientas(Observaciones obs) {
        obsEquiposHerramientas = obs;
    }


    //se recibe un true para guardar los registros en la base datos sqlite a traves de DAO
    @Override
    public void registroTerminado(Boolean aBoolean) {
        if(aBoolean == true){

                new DatosGeneralesDAO().ingresarDatosGenerales(dGenerales.get(0), getBaseContext());
                int idDatosGenerales =  new DatosGeneralesDAO().recuperaidUltimoRegistro(getBaseContext());
                new IdActualDAO().ingresarIdActual(idDatosGenerales,getBaseContext());

                new CondicionesEsctructuralesDAO().ingresarCondicionesEstructurales(list_condicionesEstruc.get(0),getBaseContext(),idDatosGenerales);
                new CondicionesEsctructuralesDAO().ingresarCondicionesEstructuralesFOTOS(list_condicionesEstruc.get(0),getBaseContext(),idDatosGenerales);
                new FactoresAmbientalesDAO().ingresarFacAmbientales(list_facAmbientales.get(0),getBaseContext(),idDatosGenerales);
                new FactoresAmbientalesDAO().ingresarFacAmbientalesFOTOS(list_facAmbientales.get(0),getBaseContext(),idDatosGenerales);
                new EquiposHerramientasOtrosDAO().ingresarEquiposHerraOtros(list_EquiposHerraOtros.get(0),getBaseContext(),idDatosGenerales);
                new EquiposHerramientasOtrosDAO().ingresarEquiposHerraFOTOS(list_EquiposHerraOtros.get(0),getBaseContext(),idDatosGenerales);
                new SeleccionEppDAO().ingresarSeleccionEpp(list_eppSeleccionados,getBaseContext(),idDatosGenerales);
                new TrabajadoresDAO().ingresarTrabajadoresActual(list_trabajadores,getBaseContext(),idDatosGenerales);
                new EtapasTareaDAO().ingresarEtapas(list_etapasTarea,getBaseContext(),idDatosGenerales);
                new RiesgosMedidasPreventDAO().ingresarRiesgosMedidasPrevent(list_riesgosMedidas,getBaseContext(),idDatosGenerales);

                //valida observaciones no sean nulll para insertara a traves de DAO
                if(obsEstructurales.getDescripcion() != null ){
                    observList.add(obsEstructurales);
                }
                if(obsAmbientales.getDescripcion() != null ){
                    observList.add(obsAmbientales);
                }
                if(obsEquiposHerramientas.getDescripcion() != null){
                    observList.add(obsEquiposHerramientas);
                }


                if(observList.size() >0){
                    new ObservacionesDAO().ingresarObservacion(observList,getBaseContext(),idDatosGenerales);
                }

        }
    }
}