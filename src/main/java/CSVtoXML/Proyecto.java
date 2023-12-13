package CSVtoXML;

public class Proyecto {

    private String coordinadorCentro, tituloProyecto, autorizacion, continuidad, coordinacion, contacto, centrosAnexion;

    public Proyecto(String coordinadorCentro, String tituloProyecto, String autorizacion, String continuidad, String coordinacion, String contacto, String centrosAnexion) {
        this.coordinadorCentro = coordinadorCentro;
        this.tituloProyecto = tituloProyecto;
        this.autorizacion = autorizacion;
        this.continuidad = continuidad;
        this.coordinacion = coordinacion;
        this.contacto = contacto;
        this.centrosAnexion = centrosAnexion;
    }

    public Proyecto() {}

    public String getCoordinadorCentro() {
        return coordinadorCentro;
    }

    public void setCoordinadorCentro(String coordinadorCentro) {
        this.coordinadorCentro = coordinadorCentro;
    }

    public String getTituloProyecto() {
        return tituloProyecto;
    }

    public void setTituloProyecto(String tituloProyecto) {
        this.tituloProyecto = tituloProyecto;
    }

    public String getAutorizacion() {
        return autorizacion;
    }

    public void setAutorizacion(String autorizacion) {
        this.autorizacion = autorizacion;
    }

    public String getContinuidad() {
        return continuidad;
    }

    public void setContinuidad(String continuidad) {
        this.continuidad = continuidad;
    }

    public String getCoordinacion() {
        return coordinacion;
    }

    public void setCoordinacion(String coordinacion) {
        this.coordinacion = coordinacion;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getCentrosAnexion() {
        return centrosAnexion;
    }

    public void setCentrosAnexion(String centrosAnexion) {
        this.centrosAnexion = centrosAnexion;
    }

    @Override
    public String toString() {
        return "Proyecto{" +
                "coordinadorCentro='" + coordinadorCentro + '\'' +
                ", tituloProyecto='" + tituloProyecto + '\'' +
                ", autorizacion='" + autorizacion + '\'' +
                ", continuidad='" + continuidad + '\'' +
                ", coordinacion='" + coordinacion + '\'' +
                ", contacto='" + contacto + '\'' +
                ", centrosAnexion='" + centrosAnexion + '\'' +
                '}';
    }
}
