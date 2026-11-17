package proyecto.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import proyecto.base.BaseTest;
import proyecto.pages.CartPage;
import proyecto.pages.CheckoutPage;
import proyecto.pages.InventoryPage;
import proyecto.pages.LoginPage;

class CheckoutTest extends BaseTest {

    @Test
    @DisplayName("TC09 Compra completa con un producto")
    void compraExitosa() {
        InventoryPage inventario = new LoginPage(driver).iniciarSesionComo("standard_user");
        inventario.agregarProducto("Sauce Labs Backpack");

        CartPage carrito = inventario.irAlCarrito();
        assertEquals(List.of("Sauce Labs Backpack"), carrito.obtenerNombresProductos());

        CheckoutPage compra = carrito.irAPagar();
        compra.completarDatos("Ana", "Mora", "10101");
        compra.continuar();
        compra.finalizar();

        assertEquals("Thank you for your order!", compra.obtenerMensajeConfirmacion());
    }
}