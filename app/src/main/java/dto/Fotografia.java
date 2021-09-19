package dto;

public class Fotografia {

    private String nombreFoto;
    private String rutaFoto;

    public Fotografia(String nombreFoto, String rutaFoto) {
        this.nombreFoto = nombreFoto;
        this.rutaFoto = rutaFoto;
    }

    public Fotografia() {
    }

    public String getNombreFoto() {
        return nombreFoto;
    }

    public void setNombreFoto(String nombreFoto) {
        this.nombreFoto = nombreFoto;
    }

    public String getRutaFoto() {
        return rutaFoto;
    }

    public void setRutaFoto(String rutaFoto) {
        this.rutaFoto = rutaFoto;
    }

    @Override
    public String toString() {
        return "Fotografia{" +
                "nombreFoto='" + nombreFoto + '\'' +
                ", rutaFoto='" + rutaFoto + '\'' +
                '}';
    }
}
