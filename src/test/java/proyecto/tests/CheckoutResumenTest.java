package proyecto.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import proyecto.base.BaseTest;
import proyecto.pages.CartPage;
import proyecto.pages.CheckoutPage;
import proyecto.pages.InventoryPage;
import proyecto.pages.LoginPage;

class CheckoutResumenTest extends BaseTest {

    private static final double TASA_IMPUESTO = 0.08;
    private static final double TOLERANCIA = 0.005;

    @Test
    @DisplayName("TC36 El resumen calcula subtotal, impuesto y total correctamente")
    void resumenCalculaMontos() {
        InventoryPage inventario = new LoginPage(driver).iniciarSesionComo("standard_user");
        inventario.agregarProducto("sauce-labs-backpack");      // 29.99
        inventario.agregarProducto("sauce-labs-bike-light");    // 9.99
        CartPage carrito = inventario.irAlCarrito();
        CheckoutPage checkout = carrito.irAPagar();

        checkout.completarDatos("Carlos", "Perez", "10101");
        checkout.continuar();

        double subtotalEsperado = 29.99 + 9.99;
        double subtotal = monto(checkout.obtenerSubtotal());
        double impuesto = monto(checkout.obtenerImpuesto());
        double total = monto(checkout.obtenerTotal());

        assertEquals(subtotalEsperado, subtotal, TOLERANCIA, "Subtotal");
        assertEquals(subtotal * TASA_IMPUESTO, impuesto, TOLERANCIA, "Impuesto 8%");
        assertEquals(subtotal + impuesto, total, TOLERANCIA, "Total");
    }
    private static double monto(String texto) {
        return Double.parseDouble(texto.replaceAll("[^0-9.]", ""));
    }
}