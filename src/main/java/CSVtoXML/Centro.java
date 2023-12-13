package CSVtoXML;

public class Centro {

    private int distancia, codigo, telefono, codigoPostal;
    private String provincia, localidad, denomCorta, nombre, direccion, denominacion, naturaleza, correo, web;

    public Centro(int distancia, int codigo, int telefono, int codigoPostal, String provincia, String localidad, String denomCorta, String nombre, String direccion, String denominacion, String naturaleza, String correo, String web) {
        this.distancia = distancia;
        this.codigo = codigo;
        this.telefono = telefono;
        this.codigoPostal = codigoPostal;
        this.provincia = provincia;
        this.localidad = localidad;
        this.denomCorta = denomCorta;
        this.nombre = nombre;
        this.direccion = direccion;
        this.denominacion = denominacion;
        this.naturaleza = naturaleza;
        this.correo = correo;
        this.web = web;
    }

    public Centro() {}

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getDenomCorta() {
        return denomCorta;
    }

    public void setDenomCorta(String denomCorta) {
        this.denomCorta = denomCorta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public String getNaturaleza() {
        return naturaleza;
    }

    public void setNaturaleza(String naturaleza) {
        this.naturaleza = naturaleza;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    @Override
    public String toString() {
        return "Centro{" +
                "distancia=" + distancia +
                ", codigo=" + codigo +
                ", telefono=" + telefono +
                ", codigoPostal=" + codigoPostal +
                ", provincia='" + provincia + '\'' +
                ", localidad='" + localidad + '\'' +
                ", denomCorta='" + denomCorta + '\'' +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", denominacion='" + denominacion + '\'' +
                ", naturaleza='" + naturaleza + '\'' +
                ", correo='" + correo + '\'' +
                ", web='" + web + '\'' +
                '}';
    }
}
