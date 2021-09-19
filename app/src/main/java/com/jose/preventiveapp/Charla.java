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

import dao.AsistentesCharlaDAO;
import dao.IdActualDAO;
import dao.TemasCharlaDAO;
import dao.TemasTratadosCharlaDAO;
import dto.Trabajadores;
import fragment_charla.Clasificacion_temas_charla;


public class Charla extends AppCompatActivity implements Charla_dt {

    private List<String> listTemasCharla= new ArrayList<>();
    private List<String> listTemasTratadosCharla= new ArrayList<>();
    private List<Trabajadores> listAsistentes= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charla);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.ct_charla,new Clasificacion_temas_charla());
        fragmentTransaction.commit();
    }

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

    @Override
    public void temasCharla(List<String> temasCharlaList) {
        listTemasCharla.clear();
        for(String actual: temasCharlaList){
            listTemasCharla.add(actual);
        }

    }

    @Override
    public void otrosTemasCharla(List<String> otrosTemasList) {
        for(String actual: otrosTemasList){
            listTemasCharla.add(actual);
        }

    }

    @Override
    public void temasTratdos(List<String> temasTratadosCharlaList) {
        listTemasTratadosCharla.clear();
        for(String actual: temasTratadosCharlaList){
            listTemasTratadosCharla.add(actual);
        }

    }

    //reutilizo la clase trabajadores para ingresar asistentes
    @Override
    public void asistentesCharla(List<Trabajadores> asistentesList) {
        listAsistentes.clear();
        for(Trabajadores actual: asistentesList){
            listAsistentes.add(actual);
        }
    }

    @Override
    public void registroTerminado(Boolean aBoolean) {
        if(aBoolean==true){

            int idDatosGactual = new IdActualDAO().mostrarIdActual(getBaseContext());

            new TemasCharlaDAO().ingresarTemasCharla(listTemasCharla,getBaseContext(),idDatosGactual);
            new TemasTratadosCharlaDAO().ingresarTemasTratados(listTemasTratadosCharla,getBaseContext(),idDatosGactual);
            new AsistentesCharlaDAO().ingresarAsistentes(listAsistentes,getBaseContext(),idDatosGactual);



        }
    }
}