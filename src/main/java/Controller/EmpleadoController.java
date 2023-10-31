package Controller;

import model.Personas.Empleado;

import java.sql.Time;
import java.time.LocalTime;

public class EmpleadoController {
    private Empleado empleado;

    public EmpleadoController(Empleado empleado) {
        this.empleado = empleado;
    }

    public void confirmarHoraLlegada() {
        // Get the current time
        LocalTime horaEntrada = LocalTime.now();

        // Set the arrival time for the employee
        empleado.setHoraEntrada(Time.valueOf(horaEntrada));

        System.out.println("Hora de llegada confirmada: " + horaEntrada);
    }

    public void confirmarHoraSalida() {
        // Get the current time
        LocalTime horaSalida = LocalTime.now();

        // Set the departure time for the employee
        empleado.setHoraSalida(Time.valueOf(horaSalida));

        System.out.println("Hora de salida confirmada: " + horaSalida);
    }
}
