package dto;

public class EstadoEpp {
   private String nombreTrabajador;
   private String nombreEpp;
   private String estado;

   public EstadoEpp(){

   }

    public EstadoEpp(String nombreTrabajador, String nombreEpp, String estado) {
        this.nombreTrabajador = nombreTrabajador;
        this.nombreEpp = nombreEpp;
        this.estado = estado;
    }

    public String getNombreTrabajador() {
        return nombreTrabajador;
    }

    public void setNombreTrabajador(String nombreTrabajador) {
        this.nombreTrabajador = nombreTrabajador;
    }

    public String getNombreEpp() {
        return nombreEpp;
    }

    public void setNombreEpp(String nombreEpp) {
        this.nombreEpp = nombreEpp;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "EstadoEpp{" +
                "nombreTrabajador='" + nombreTrabajador + '\'' +
                ", nombreEpp='" + nombreEpp + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}

