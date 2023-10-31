package model.data.dao;

import model.Carrera;
import model.Empresa.Construccion;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Result;
import org.jooq.Table;
import org.jooq.impl.DSL;

import javax.xml.transform.Result;
import java.util.List;

import static org.jooq.impl.DSL.name;
import static org.jooq.impl.DSL.table;

public class ConstruccionDAO {
    public static void agregarConstruccion(DSLContext query, Construccion construccion){
        Table tablaConstruccion= table(name("Construccion"));
        Field[] columnas = tablaConstruccion.fields("tipo_construccion","construccion-adicional","cantidad_productos");
        query.insertInto(tablaConstruccion, columnas[0], columnas[1],columnas[2])
                .values(construccion.getTipoConstruccion(),construccion.getConstruccionesAdicionales(),construccion.getCantidadProductos())
                .execute();
    }
    public static boolean validarExistenciaConstruccion(DSLContext query,String columnaTabla, Object dato){
        Result resultados = query.select().from(DSL.table("Construccion")).where(DSL.field(columnaTabla).eq(dato)).fetch();
        if(resultados.size()>=1){
            return true;
        }
        else{
            return false;
        }
    }
    public static Construccion buscarConstruccion(DSLContext query, Object dato){
        Result resultados= (Result) buscarConstruccion(query,"tipo-construccion",dato);
        String tipoConstruccion = (String) resultados.getValue(0,"tipo-construccion");
        String construccionAdicional= (String) resultados.getValue(0,"construccion-adicional");
        int cantidadProductos = (int) resultados.getValue(0,"cantidad_productos");
        return new Construccion(tipoConstruccion, construccionAdicional, cantidadProductos);
    }

    public static List buscarConstruccion(DSLContext query, String columnaTabla, Object dato){
        Result resultados = query.select().from(DSL.table("Construccion")).where(DSL.field(columnaTabla).eq(dato)).fetch();
        return (List) resultados;
    }
    public static Object[] getTipoConstruccion(DSLContext query){
        Table construccion= DSL.table("Construccion");
        Result resultados = query.select(carrera.field("tipo-construccion")).from(construccion).fetch();
        if(resultados.size()==0){
            return new String[]{"Error no existen construcciones"};
        }
        else {
            return resultados.getValues("tipo-construccion").toArray();
        }
    }

}

