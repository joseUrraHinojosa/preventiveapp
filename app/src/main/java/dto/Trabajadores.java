package dto;

public class Trabajadores {


    private String nombre;
    private String run;
    private String pasaporte;


    public Trabajadores(){

    }

    public Trabajadores(String nombre, String run, String pasaporte) {
        this.nombre = nombre;
        this.run = run;
        this.pasaporte = pasaporte;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRun() {
        return run;
    }

    public void setRun(String run) {
        this.run = run;
    }

    public String getPasaporte() {
        return pasaporte;
    }

    public void setPasaporte(String pasaporte) {
        this.pasaporte = pasaporte;
    }

    @Override
    public String toString() {
        return "Trabajadores{" +
                "nombre='" + nombre + '\'' +
                ", run='" + run + '\'' +
                ", pasaporte='" + pasaporte + '\'' +
                '}';
    }
}
