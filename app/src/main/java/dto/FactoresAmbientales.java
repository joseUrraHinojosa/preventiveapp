package dto;

import java.util.List;

public class FactoresAmbientales {

    private int idFacAmbientales;
    private String criterio1;
    private String criterio2;
    private String criterio3;
    private String criterio4;
    private String criterio5;
    private String criterio6;
    private String criterio7;
    private String criterio8;
    private String criterio9;
    private String criterio10;
    private List<Fotografia> fotos;

    public FactoresAmbientales() {
    }

    public FactoresAmbientales(int idFacAmbientales, String criterio1, String criterio2, String criterio3, String criterio4, String criterio5, String criterio6, String criterio7, String criterio8, String criterio9, String criterio10, List<Fotografia> fotos) {
        this.idFacAmbientales = idFacAmbientales;
        this.criterio1 = criterio1;
        this.criterio2 = criterio2;
        this.criterio3 = criterio3;
        this.criterio4 = criterio4;
        this.criterio5 = criterio5;
        this.criterio6 = criterio6;
        this.criterio7 = criterio7;
        this.criterio8 = criterio8;
        this.criterio9 = criterio9;
        this.criterio10 = criterio10;
        this.fotos = null;
    }

    public int getIdFacAmbientales() {
        return idFacAmbientales;
    }

    public void setIdFacAmbientales(int idFacAmbientales) {
        this.idFacAmbientales = idFacAmbientales;
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

    public String getCriterio7() {
        return criterio7;
    }

    public void setCriterio7(String criterio7) {
        this.criterio7 = criterio7;
    }

    public String getCriterio8() {
        return criterio8;
    }

    public void setCriterio8(String criterio8) {
        this.criterio8 = criterio8;
    }

    public String getCriterio9() {
        return criterio9;
    }

    public void setCriterio9(String criterio9) {
        this.criterio9 = criterio9;
    }

    public String getCriterio10() {
        return criterio10;
    }

    public void setCriterio10(String criterio10) {
        this.criterio10 = criterio10;
    }

    public List<Fotografia> getFotos() {
        return fotos;
    }

    public void setFotos(List<Fotografia> fotos) {
        this.fotos = fotos;
    }


}
