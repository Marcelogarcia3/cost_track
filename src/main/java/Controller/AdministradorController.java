package Controller;

import model.Personas.Administrador;
import model.Producto.Producto;
import model.data.dao.ProductoDAO;

import java.util.List;

public class AdministradorController {
    private Administrador administrador;

    public AdministradorController(Administrador administrador) {
        this.administrador = administrador;
    }

    public boolean verStockProductos() {
        ProductoDAO productoDAO = new ProductoDAO();
        List<Producto> productos = productoDAO.obtenerProductosDisponibles();

        if (productos.isEmpty()) {
            System.out.println("No hay productos disponibles en el stock.");
            return false;
        }

        System.out.println("Stock de Productos:");
        for (Producto producto : productos) {
            System.out.println("Nombre: " + producto.getNombre() + ", Precio: " + producto.getPrecio() + ", Categoría: " + producto.getCategoria());
        }

        return true;
    }

    public boolean agregarProductoStock(String nombre, double precio, String categoria) {
        ProductoDAO productoDAO = new ProductoDAO();
        boolean productoAgregado = productoDAO.agregarProducto(nombre, precio, categoria);

        if (productoAgregado) {
            System.out.println("Producto agregado al stock exitosamente.");
        } else {
            System.out.println("No se pudo agregar el producto al stock (ya existe un producto con el mismo nombre).");
        }

        return productoAgregado;
    }

    public boolean eliminarProductoStock(String nombre) {
        ProductoDAO productoDAO = new ProductoDAO();
        boolean productoEliminado = productoDAO.eliminarProducto(nombre);

        if (productoEliminado) {
            System.out.println("Producto eliminado del stock exitosamente.");
        } else {
            System.out.println("No se pudo eliminar el producto del stock (producto no encontrado).");
        }

        return productoEliminado;
    }
}
