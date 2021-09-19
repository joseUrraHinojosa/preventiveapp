package com.jose.preventiveapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dao.CondicionesEsctructuralesDAO;
import dao.DatosGeneralesDAO;
import dao.RiesgosMedidasPreventDAO;
import dto.AdapterRegistros;
import dto.CondicionesEstructurales;
import dto.DatosGenerales;

public class Registros_ extends AppCompatActivity {


    private List<DatosGenerales> datosGeneralesList;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private AdapterRegistros adapterRegistros;
    private ImageView ivAtras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registros);
        recyclerView =(RecyclerView)findViewById(R.id.recyclerRegistros);
        ivAtras =(ImageView)findViewById(R.id.iv_atras_dg);

         datosGeneralesList = new DatosGeneralesDAO().mostrarDatosGenerales(getBaseContext());
         layoutManager = new LinearLayoutManager(getBaseContext());
         adapterRegistros = new AdapterRegistros(datosGeneralesList,getBaseContext());
         recyclerView.setLayoutManager(layoutManager);
         recyclerView.setAdapter(adapterRegistros);

        ivAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), Menu.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP));
                finish();
            }
        });

    }


    @Override
    public void onBackPressed(){
        startActivity(new Intent(getBaseContext(), Menu.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP));
        finish();

    }

}