package model.data.dao;

import model.Producto.Producto;

import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
    private List<Producto> productos; // In-memory list to store products

    public ProductoDAO() {
        // Initialize the list of products (you can replace this with database or file operations)
        productos = new ArrayList<>();
    }

    public List<Producto> obtenerProductosDisponibles() {
        return productos; // Return the list of all products
    }

    public boolean agregarProducto(String nombre, double precio, String categoria) {
        // Check if a product with the same name already exists
        for (Producto existingProducto : productos) {
            if (existingProducto.getNombre().equals(nombre)) {
                return false; // Product with the same name already exists
            }
        }

        // Create a new Producto using the provided constructor
        Producto nuevoProducto = new Producto(nombre, precio, categoria);

        // Add the product to the list (you may want to add validation and error handling)
        productos.add(nuevoProducto);
        return true;
    }

    public boolean eliminarProducto(String nombre) {
        // Find and remove the product with the specified name (you may want to add error handling)
        Producto productToRemove = null;
        for (Producto producto : productos) {
            if (producto.getNombre().equals(nombre)) {
                productToRemove = producto;
                break;
            }
        }

        if (productToRemove != null) {
            productos.remove(productToRemove);
            return true;
        } else {
            return false; // Product not found
        }
    }
}

