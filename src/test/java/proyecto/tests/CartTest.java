package proyecto.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import proyecto.base.BaseTest;
import proyecto.pages.CartPage;
import proyecto.pages.InventoryPage;
import proyecto.pages.LoginPage;

class CartTest extends BaseTest {
    private static final String MOCHILA = "Sauce Labs Backpack";
    private static final String LUZ = "Sauce Labs Bike Light";

    private InventoryPage inventario;

    @BeforeEach
    void iniciarSesion() {
        inventario = new LoginPage(driver).iniciarSesionComo("standard_user");
    }

    @Test
    @DisplayName("TC19 Agregar dos productos actualiza el contador del carrito")
    void contadorAlAgregar() {
        inventario.agregarProducto(MOCHILA);
        inventario.agregarProducto(LUZ);
        assertEquals(2, inventario.obtenerCantidadCarrito());
    }

    @Test
    @DisplayName("TC20 Quitar un producto desde el inventario reduce el contador")
    void contadorAlQuitar() {
        inventario.agregarProducto(MOCHILA);
        inventario.agregarProducto(LUZ);
        inventario.quitarProducto(MOCHILA);
        assertEquals(1, inventario.obtenerCantidadCarrito());
    }

    @Test
    @DisplayName("TC21 El carrito muestra los productos agregados")
    void carritoMuestraProductos() {
        inventario.agregarProducto(MOCHILA);
        inventario.agregarProducto(LUZ);
        CartPage carrito = inventario.irAlCarrito();
        assertEquals(List.of(MOCHILA, LUZ), carrito.obtenerNombresProductos());
    }

    @Test
    @DisplayName("TC22 Quitar un producto desde el carrito lo elimina de la lista")
    void quitarDesdeCarrito() {
        inventario.agregarProducto(MOCHILA);
        inventario.agregarProducto(LUZ);
        CartPage carrito = inventario.irAlCarrito();
        carrito.quitarProducto(MOCHILA);
        assertEquals(List.of(LUZ), carrito.obtenerNombresProductos());
    }

    @Test
    @DisplayName("TC23 Continue Shopping regresa al catálogo sin perder los productos")
    void seguirComprando() {
        inventario.agregarProducto(MOCHILA);
        CartPage carrito = inventario.irAlCarrito();
        InventoryPage catalogo = carrito.seguirComprando();
        assertEquals(1, catalogo.obtenerCantidadCarrito());
    }
}