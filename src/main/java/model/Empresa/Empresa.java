package model.Empresa;

import model.Personas.Empleado;
import model.Producto.Inventario;

import java.util.ArrayList;
import java.util.List;

public class Empresa {
    private String nombre;
    private List<Empleado> empleados;
    private Inventario inventario;

    public Empresa(String nombre) {
        this.nombre = nombre;
        this.empleados = new ArrayList<>();
        this.inventario = new Inventario();
    }

    public void agregarEmpleado(Empleado empleado) {
        this.empleados.add(empleado);
    }

    public void quitarEmpleado(Empleado empleado) {
        this.empleados.remove(empleado);
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public Inventario getInventario() {
        return inventario;
    }
}
