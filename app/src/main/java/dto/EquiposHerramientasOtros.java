package dto;

import java.util.List;

public class EquiposHerramientasOtros {

    private int idEquposHerramientas;
    private String criterio1;
    private String criterio2;
    private String criterio3;
    private String criterio4;
    private List<Fotografia> fotos;

    public EquiposHerramientasOtros() {
    }

    public EquiposHerramientasOtros(int idEquposHerramientas, String criterio1, String criterio2, String criterio3, String criterio4, List<Fotografia> fotos) {
        this.idEquposHerramientas = idEquposHerramientas;
        this.criterio1 = criterio1;
        this.criterio2 = criterio2;
        this.criterio3 = criterio3;
        this.criterio4 = criterio4;
        this.fotos = null;
    }

    public int getIdEquposHerramientas() {
        return idEquposHerramientas;
    }

    public void setIdEquposHerramientas(int idEquposHerramientas) {
        this.idEquposHerramientas = idEquposHerramientas;
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

    public List<Fotografia> getFotos() {
        return fotos;
    }

    public void setFotos(List<Fotografia> fotos) {
        this.fotos = fotos;
    }


}
