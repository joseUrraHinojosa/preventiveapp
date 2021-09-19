package dto;

import java.util.ArrayList;
import java.util.List;

public class MedidasPreventivas {

    private String riesgo;
    private String medidaPreventivas;


    public MedidasPreventivas() {
    }

    public MedidasPreventivas(String riesgo, String medidaPreventivas) {
        this.riesgo = riesgo;
        this.medidaPreventivas = medidaPreventivas;
    }

    public String getRiesgo() {
        return riesgo;
    }

    public void setRiesgo(String riesgo) {
        this.riesgo = riesgo;
    }

    public String getMedidaPreventivas() {
        return medidaPreventivas;
    }

    public void setMedidaPreventivas(String medidaPreventivas) {
        this.medidaPreventivas = medidaPreventivas;
    }

    @Override
    public String toString() {
        return "MedidasPreventivas{" +
                "riesgo='" + riesgo + '\'' +
                ", medidaPreventivas='" + medidaPreventivas + '\'' +
                '}';
    }
}
