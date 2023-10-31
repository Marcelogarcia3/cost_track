package controller;
import model.Empleado; // Asegúrate de tener una clase Empleado que represente a los empleados
import model.data.dao.EmpleadoDAO; // Asegúrate de tener una clase EmpleadoDAO o similar
import model.data.DBConnector;
import model.data.DBGenerator;
import org.jooq.DSLContext;

public class EmpleadoController {
    public static boolean añadirEmpleado(String nombre, String apellido, String rut, String rol, int telefono, int salario, String estado) throws ClassNotFoundException {
        DSLContext query = DBGenerator.conectarBD("TuProyecto"); // Reemplaza "TuProyecto" por el nombre de tu base de datos
        // Verifica si ya existe un empleado con el mismo rut antes de agregarlo
        if (!EmpleadoDAO.validarExistenciaEmpleado(query, "rut", rut)) {
            Empleado empleado = new Empleado(nombre, apellido, rut, rol, telefono, salario, estado);
            EmpleadoDAO.agregarEmpleado(query, empleado);
            DBConnector.closeConnection();
            return true;
        } else {
            return false; // Empleado con el mismo rut ya existe
        }
    }

    public static String[] getRoles() throws ClassNotFoundException {
        // Si tienes una tabla de roles, puedes modificar esto para obtener los roles desde la base de datos
        String[] roles = {"Admin", "Empleado", "Otro"};
        return roles;
    }
}



