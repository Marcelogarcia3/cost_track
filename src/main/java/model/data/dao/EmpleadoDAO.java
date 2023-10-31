package model.data.dao;



import model.Personas.Empleado;
import org.jooq.*;
import org.jooq.impl.DSL;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import static org.jooq.impl.DSL.*;
import static org.jooq.impl.SQLDataType.VARCHAR;

public class EmpleadoDAO {

    public static void agregarEmpleado(DSLContext query, Empleado empleado) {
        Table tablaEmpleado = table(name("Empleado"));
        Field[] columnas = tablaEmpleado.fields("rut", "nombre", "rol", "telefono", "salario", "estado");
        query.insertInto(tablaEmpleado, columnas[0], columnas[1], columnas[2], columnas[3], columnas[4], columnas[5])
                .values(empleado.getRut(), empleado.getNombre(), empleado.getRol(), empleado.getTelefono(),
                        empleado.getSalario(), empleado.getEstado())
                .execute();
    }

    public void modificarEmpleado(DSLContext query, String rut, String columnaTabla, Object dato) {
        query.update(DSL.table("Empleado")).set(DSL.field(columnaTabla), dato)
                .where(DSL.field("rut").eq(rut)).execute();
    }

    public List obtenerEmpleado(DSLContext query, String columnaTabla, Object dato) {
        Result resultados = query.select().from(DSL.table("Empleado")).where(DSL.field(columnaTabla).eq(dato)).fetch();
        return obtenerListaEmpleados(resultados);
    }

    public void eliminarEmpleado(DSLContext query, String rut) {
        Table tablaEmpleado = table(name("Empleado"));
        query.delete(DSL.table("Empleado")).where(DSL.field("rut").eq(rut)).execute();
    }

    private List<Empleado> obtenerListaEmpleados(Result resultados) {
        List<Empleado> empleados = new ArrayList<>();
        for (int fila = 0; fila < resultados.size(); fila++) {
            String rut = (String) resultados.getValue(fila, "rut");
            String nombre = (String) resultados.getValue(fila, "nombre");
            String cargo = (String) resultados.getValue(fila, "cargo");
            int telefono = (int) resultados.getValue(fila, "telefono");
            int salario = (int) resultados.getValue(fila, "salario");
            String estado = (String) resultados.getValue(fila, "estado");

            // You need to provide the correct values for horaEntrada and horaSalida.
            Time horaEntrada = Time.valueOf("08:00:00");
            Time horaSalida = Time.valueOf("17:00:00");

            empleados.add(new Empleado(nombre, "", rut, telefono, cargo, estado, salario, horaEntrada, horaSalida));
        }
        return empleados;
    }


    public static String[][] obtenerEmpleadosPorRol(DSLContext query, String cargo) {
        Result resultados = query.select().from(DSL.table("Empleado"))
                .where(DSL.field("cargo").eq(cargo))
                .fetch();
        return exportarDatos(resultados);
    }

    public static String[][] obtenerEmpleadosPorEstado(DSLContext query, String estado) {
        Result resultados = query.select().from(DSL.table("Empleado"))
                .where(DSL.field("estado").eq(estado))
                .fetch();
        return exportarDatos(resultados);
    }

    private static String[][] exportarDatos(Result resultados) {
        String[][] datosResultado = new String[resultados.size()][6];
        for (int registro = 0; registro < resultados.size(); registro++) {
            datosResultado[registro][0] = (String) resultados.getValue(registro, "rut");
            datosResultado[registro][1] = (String) resultados.getValue(registro, "nombre");
            datosResultado[registro][2] = (String) resultados.getValue(registro, "cargo");
            datosResultado[registro][3] = Integer.toString((int) resultados.getValue(registro, "telefono"));
            datosResultado[registro][4] = Integer.toString((int) resultados.getValue(registro, "salario"));
            datosResultado[registro][5] = (String) resultados.getValue(registro, "estado");
        }
        return datosResultado;
    }

    public static boolean validarExistenciaEmpleado(DSLContext query, String columnaTabla, Object dato) {
        Result resultados = query.select().from(DSL.table("Empleado")).where(DSL.field(columnaTabla).eq(dato)).fetch();
        if (resultados.size() >= 1) {
            return true;
        } else {
            return false;
        }
    }
}
