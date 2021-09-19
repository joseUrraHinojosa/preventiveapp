package dto;

import java.util.List;

public class CondicionesEstructurales {

    private int idCondEstructurales;
    private String criterio1;
    private String criterio2;
    private String criterio3;
    private String criterio4;
    private String criterio5;
    private String criterio6;
    private List<Fotografia> fotos;

    public CondicionesEstructurales() {
    }

    public CondicionesEstructurales(int idCondEstructurales, String criterio1, String criterio2, String criterio3, String criterio4, String criterio5, String criterio6, List<Fotografia> fotos) {
        this.idCondEstructurales = idCondEstructurales;
        this.criterio1 = criterio1;
        this.criterio2 = criterio2;
        this.criterio3 = criterio3;
        this.criterio4 = criterio4;
        this.criterio5 = criterio5;
        this.criterio6 = criterio6;
        this.fotos = null;
    }

    public int getIdCondEstructurales() {
        return idCondEstructurales;
    }

    public void setIdCondEstructurales(int idCondEstructurales) {
        this.idCondEstructurales = idCondEstructurales;
    }

    public String getCriterio1() {
        return criterio1;
    }

    public void setCriterio1(String criterio1) {
        this.criterio1 = criterio1;
    }

    public String getCriterio2() {
        return criterio2;
    }

    public void setCriterio2(String criterio2) {
        this.criterio2 = criterio2;
    }

    public String getCriterio3() {
        return criterio3;
    }

    public void setCriterio3(String criterio3) {
        this.criterio3 = criterio3;
    }

    public String getCriterio4() {
        return criterio4;
    }

    public void setCriterio4(String criterio4) {
        this.criterio4 = criterio4;
    }

    public String getCriterio5() {
        return criterio5;
    }

    public void setCriterio5(String criterio5) {
        this.criterio5 = criterio5;
    }

    public String getCriterio6() {
        return criterio6;
    }

    public void setCriterio6(String criterio6) {
        this.criterio6 = criterio6;
    }

    public List<Fotografia> getFotos() {
        return fotos;
    }

    public void setFotos(List<Fotografia> fotos) {
        this.fotos = fotos;
    }


}
