package model.data;

import org.jooq.DSLContext;
import org.jooq.DataType;
import org.jooq.impl.DSL;

import java.sql.Connection;

import static org.jooq.impl.DSL.*;
import static org.jooq.impl.SQLDataType.*;

import org.jooq.Record;
import org.jooq.Result;
import org.jooq.exception.DataAccessException;
public class DBGenerator {

    public static void iniciarBD(String nombreBD) throws ClassNotFoundException {
        Connection connection = DBConnector.connection("root", "");
        DSLContext create = DSL.using(connection);
        crearBaseDato(create, nombreBD);
        create = actualizarConexion(connection, nombreBD);
        crearTablaCarrera(create);
        crearTablaEstudiante(create);
        relacionarTabla(create, "Estudiante", "codigo_carrera", "Carrera");
        DBConnector.closeConnection();

    }

    public static DSLContext conectarBD(String nombre) throws ClassNotFoundException {
        Connection connection = DBConnector.connection(nombre, "root", "");
        DSLContext create = DSL.using(connection);
        return create;
    }

    private static void crearBaseDato(DSLContext create, String nombreBD) {
        create.createDatabaseIfNotExists(nombreBD).execute();
    }

    private static DSLContext actualizarConexion(Connection connection, String nombreBD) {
        DBConnector.closeConnection();
        connection = DBConnector.connection(nombreBD, "root", "");
        DSLContext create = DSL.using(connection);
        return create;
    }

    private static void crearTablaCarrera(DSLContext create) {
        create.createTableIfNotExists("Carrera").column("nombre_carrera", VARCHAR(100))
                .column("codigo", VARCHAR(50))
                .column("cantidad_semestres", INTEGER)
                .constraint(primaryKey("codigo")).execute();
    }

    private static void crearTablaEstudiante(DSLContext create) {
        create.createTableIfNotExists("Estudiante").column("rut", VARCHAR(50))
                .column("nombre", VARCHAR(100))
                .column("matricula", VARCHAR(50))
                .column("codigo_carrera", VARCHAR(50))
                .constraint(primaryKey("rut")).execute();

    }

    private static void relacionarTabla(DSLContext create, String nombreTabla, String claveForanea, String nombreTablaRelacion) {
        //  create.alterTableIfExists(nombreTabla).add(foreignKey(claveForanea).references(nombreTablaRelacion)).execute();
        create.alterTableIfExists(nombreTabla).alterConstraint(foreignKey(claveForanea).references(nombreTablaRelacion)).enforced();
    }

    private static void agregarColumnaTabla(DSLContext create, String nombreTabla, String columna, DataType tipoColumna) {
        create.alterTableIfExists(nombreTabla).addColumn(columna, tipoColumna);
    }
    public static void leerBaseDatos(String nombreBD) throws ClassNotFoundException {
        try {
            DSLContext create = conectarBD(nombreBD);
            Result<Record> result = create.select().from("Carrera").fetch();

            System.out.println("Datos de la tabla Carrera:");
            for (Record r : result) {
                String nombreCarrera = r.get("nombre_carrera", String.class);
                String codigo = r.get("codigo", String.class);
                int cantidadSemestres = r.get("cantidad_semestres", int.class);

                System.out.println("Nombre de la carrera: " + nombreCarrera);
                System.out.println("Código: " + codigo);
                System.out.println("Cantidad de semestres: " + cantidadSemestres);
                System.out.println("----------------------");
            }
            DBConnector.closeConnection();
        } catch (DataAccessException e) {
            System.err.println("Error al leer la base de datos: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String nombreBD = "test_database"; // Cambia el nombre de la base de datos según tus necesidades
        try {
            iniciarBD(nombreBD);

            leerBaseDatos(nombreBD);

            System.out.println("La base de datos se ha inicializado correctamente y los datos se han leído con éxito.");
        } catch (ClassNotFoundException e) {
            System.err.println("Error al intentar inicializar la base de datos: " + e.getMessage());
        }
    }
}

