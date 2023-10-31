package model.Personas;

import java.sql.Time;
import java.time.LocalTime;

public class Empleado extends Persona {
    public String estado;
    public int salario;
    private Time horaEntrada;
    private Time HoraSalida;

    public Empleado(String nombre, String apellido, String rut, int telefono, String rol, String estado, int salario, Time horaEntrada, Time horaSalida) {
        super(nombre, apellido, rut, telefono, rol);
        this.estado = estado;
        this.salario = salario;
        this.horaEntrada = horaEntrada;
        HoraSalida = horaSalida;
    }

    public String isEstado() {
        return estado;
    }

    public String getEstado() {
        return estado;
    }


    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Time getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(Time horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public Time getHoraSalida() {
        return HoraSalida;
    }

    public void setHoraSalida(Time horaSalida) {
        HoraSalida = horaSalida;
    }

    public String[] getDatos(Empleado empleado) {
        String[] datos = new String[9]; //arrglo para almacenar y obtener los datos de cada trabajador
        datos[0] = empleado.getNombre();
        datos[1] = empleado.getApellido();
        datos[2] = empleado.getRut();
        datos[3] = Integer.toString(empleado.getTelefono());
        datos[4] = empleado.getRol();
        datos[5] = Integer.toString(empleado.getSalario());
        datos[6] = empleado.estado;
        datos[7] = Integer.toString(convertirHoraAMinutos(empleado.getHoraEntrada()));
        datos[8] = Integer.toString(convertirHoraAMinutos(empleado.getHoraSalida()));

        return datos;
    }

    private int convertirHoraAMinutos(Time hora) {
        int minutos = 0;
        if (hora != null) {
            // Divide la hora en horas y minutos
            String[] partesHora = hora.toString().split(":");
            if (partesHora.length == 2) {
                int horas = Integer.parseInt(partesHora[0]);
                int mins = Integer.parseInt(partesHora[1]);
                minutos = horas * 60 + mins;
            }
        }
        return minutos;
    }

}