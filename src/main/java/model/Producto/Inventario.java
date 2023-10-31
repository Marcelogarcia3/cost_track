package model.Producto;

import java.util.HashMap;
import java.util.Map;

public class Inventario {
    private Map<Producto, Integer> stock;

    public Inventario() {
        this.stock = new HashMap<>();
    }

    public void agregarProducto(Producto producto, int cantidad) {
        int actualCantidad = stock.getOrDefault(producto, 0);
        stock.put(producto, actualCantidad + cantidad);
    }

    public void quitarProducto(Producto producto, int cantidad) {
        if (stock.containsKey(producto)) {
            int actualCantidad = stock.get(producto);
            stock.put(producto, Math.max(actualCantidad - cantidad, 0));
        }
    }

    public int obtenerCantidad(Producto producto) {
        return stock.getOrDefault(producto, 0);
    }
}


