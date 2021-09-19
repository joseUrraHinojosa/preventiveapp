package dto;

import java.util.List;

public class DatosGenerales {
    private int idDatosGenerales;
    private String empresaMandante;
    private String codigoSitio;
    private String tipoTrabajo;
    private String duracionTrabajo;
    private String direccion;
    private String ciudad;
    private String comuna;
    private String fecha;
    private String hora;
    private String supervisor;
    private String runSupervisor;


    public DatosGenerales() {

    }

    public DatosGenerales(int idDatosGenerales, String empresaMandante, String codigoSitio, String tipoTrabajo, String duracionTrabajo, String direccion, String ciudad, String comuna, String fecha, String hora, String supervisor, String runSupervisor) {
        this.idDatosGenerales = idDatosGenerales;
        this.empresaMandante = empresaMandante;
        this.codigoSitio = codigoSitio;
        this.tipoTrabajo = tipoTrabajo;
        this.duracionTrabajo = duracionTrabajo;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.comuna = comuna;
        this.fecha = fecha;
        this.hora = hora;
        this.supervisor = supervisor;
        this.runSupervisor = runSupervisor;
    }

    public int getIdDatosGenerales() {
        return idDatosGenerales;
    }

    public void setIdDatosGenerales(int idDatosGenerales) {
        this.idDatosGenerales = idDatosGenerales;
    }

    public String getEmpresaMandante() {
        return empresaMandante;
    }

    public void setEmpresaMandante(String empresaMandante) {
        this.empresaMandante = empresaMandante;
    }

    public String getCodigoSitio() {
        return codigoSitio;
    }

    public void setCodigoSitio(String codigoSitio) {
        this.codigoSitio = codigoSitio;
    }

    public String getTipoTrabajo() {
        return tipoTrabajo;
    }

    public void setTipoTrabajo(String tipoTrabajo) {
        this.tipoTrabajo = tipoTrabajo;
    }

    public String getDuracionTrabajo() {
        return duracionTrabajo;
    }

    public void setDuracionTrabajo(String duracionTrabajo) {
        this.duracionTrabajo = duracionTrabajo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getComuna() {
        return comuna;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public String getRunSupervisor() {
        return runSupervisor;
    }

    public void setRunSupervisor(String runSupervisor) {
        this.runSupervisor = runSupervisor;
    }

    @Override
    public String toString() {
        return "DatosGenerales{" +
                "idDatosGenerales=" + idDatosGenerales +
                ", empresaMandante='" + empresaMandante + '\'' +
                ", codigoSitio='" + codigoSitio + '\'' +
                ", tipoTrabajo='" + tipoTrabajo + '\'' +
                ", duracionTrabajo='" + duracionTrabajo + '\'' +
                ", direccion='" + direccion + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", comuna='" + comuna + '\'' +
                ", fecha='" + fecha + '\'' +
                ", hora='" + hora + '\'' +
                ", supervisor='" + supervisor + '\'' +
                ", runSupervisor='" + runSupervisor + '\'' +
                '}';
    }
}
