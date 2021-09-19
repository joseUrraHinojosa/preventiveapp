package com.jose.preventiveapp;

import java.util.List;

import dto.Trabajadores;

public interface Charla_dt {

    public void temasCharla (List<String> temasCharlaList);
    public void otrosTemasCharla (List<String> otrosTemasList);
    public void temasTratdos (List<String> temasTratadosCharlaList);
    public void asistentesCharla (List<Trabajadores> asistentesList);
    public void registroTerminado(Boolean aBoolean);
}
