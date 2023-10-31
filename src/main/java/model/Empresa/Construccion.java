package model.Empresa;

public class Construccion {
    private String tipoConstruccion;
    private String construccionesAdicionales;
    private int cantidadProductos;

    public Construccion(String tipoConstruccion, String construccionesAdicionales, int cantidadProductos) {
        this.tipoConstruccion = tipoConstruccion;
        this.construccionesAdicionales = construccionesAdicionales;
        this.cantidadProductos = cantidadProductos;
    }

    public String getTipoConstruccion() {
        return tipoConstruccion;
    }

    public void setTipoConstruccion(String tipoConstruccion) {
        this.tipoConstruccion = tipoConstruccion;
    }

    public String getConstruccionesAdicionales() {
        return construccionesAdicionales;
    }

    public void setConstruccionesAdicionales(String construccionesAdicionales) {
        this.construccionesAdicionales = construccionesAdicionales;
    }

    public int getCantidadProductos() {
        return cantidadProductos;
    }

    public void setCantidadProductos(int cantidadProductos) {
        this.cantidadProductos = cantidadProductos;
    }
}
