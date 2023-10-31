package model.Personas;

import model.Personas.Persona;

public class Administrador extends Persona {


    public Administrador (String nombre, String apellido, String rut, int telefono){
        super(nombre, apellido, rut, telefono,"Administrador");
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getRut() {
        return rut;
    }

    public int getTelefono() {
        return telefono;
    }

    public String getRol() {
        return rol;
    }
}
