package model.data.dao;

import model.Personas.Administrador;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Result;
import org.jooq.Table;

import static org.jooq.impl.DSL.*;

public class AdministradorDAO {

    public static void agregarAdministrador(DSLContext query, Administrador administrador) {
        Table tablaAdministrador = table(name("Administrador"));
        Field[] columnas = tablaAdministrador.fields("rut", "nombre", "apellido", "telefono", "rol");

        query.insertInto(tablaAdministrador, columnas[0], columnas[1], columnas[2], columnas[3], columnas[4])
                .values(administrador.getRut(), administrador.getNombre(), administrador.getApellido(),
                        administrador.getTelefono(), administrador.getRol())
                .execute();
    }

    public static void modificarAdministrador(DSLContext query, String rut, String columnaTabla, Object dato) {
        query.update(table(name("Administrador")))
                .set(field(columnaTabla), dato)
                .where(field("rut").eq(rut))
                .execute();
    }

    public static void eliminarAdministrador(DSLContext query, String rut) {
        query.delete(table(name("Administrador")))
                .where(field("rut").eq(rut))
                .execute();
    }

    public static boolean validarExistenciaAdministrador(DSLContext query, String columnaTabla, Object dato) {
        Result resultados = query.select().from(table(name("Administrador")))
                .where(field(columnaTabla).eq(dato))
                .fetch();
        return resultados.size() >= 1;
    }
}
