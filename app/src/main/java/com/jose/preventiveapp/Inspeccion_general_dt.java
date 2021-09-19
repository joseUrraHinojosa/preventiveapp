package com.jose.preventiveapp;

import android.content.Context;

import java.util.List;


import dto.CondicionesEstructurales;
import dto.DatosGenerales;
import dto.EquiposHerramientasOtros;
import dto.FactoresAmbientales;
import dto.MedidasPreventivas;
import dto.Observaciones;
import dto.Trabajadores;

public interface Inspeccion_general_dt {


    public void datosGenerales(DatosGenerales d);
    public void condicionesEstructurales(CondicionesEstructurales estructurales);
    public void factoresAmbientales(FactoresAmbientales ambientales);
    public void equiposHerraOtos(EquiposHerramientasOtros herramientasOtros);
    public void etapasTarea(List<String> etapas);
    public void riesgosMedidaPrevent(List<MedidasPreventivas> riesgosMedidasList);
    public void seleccionEPP(List<String> eppSeleccionadosList);
    public void seleccionOtrosEPP(List<String> otrosEppList);

    public void trabajadoresInterface(List<Trabajadores> trabajadores);

    public void obsEstructurales(Observaciones obs);
    public void obsAmbientales(Observaciones obs);
    public void obsEquiposHerramientas(Observaciones obs);



    public void registroTerminado(Boolean aBoolean);




}
