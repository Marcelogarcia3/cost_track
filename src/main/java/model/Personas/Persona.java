package model.Personas;

public class Persona {
    protected String nombre; //super clases que se utilizan en una subclase
    protected String apellido;
    protected String rut;
    protected int telefono;
    public String rol;

    public Persona(String nombre, String apellido, String rut, int telefono, String rol) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.rut = rut;
        this.telefono = telefono;
        this.rol = rol;
    }
}
