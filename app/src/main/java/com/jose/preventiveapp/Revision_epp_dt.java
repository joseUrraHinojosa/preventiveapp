package com.jose.preventiveapp;

import java.util.List;

import dto.EstadoEpp;

public interface Revision_epp_dt {

    public void trabajadorChecado(Boolean aBoolean);
    public void revisionEppList(List<EstadoEpp> eppRevisadosList);

}
