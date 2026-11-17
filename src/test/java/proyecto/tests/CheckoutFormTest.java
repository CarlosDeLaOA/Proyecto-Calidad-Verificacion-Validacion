package proyecto.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import proyecto.base.BaseTest;
import proyecto.pages.CartPage;
import proyecto.pages.CheckoutPage;
import proyecto.pages.InventoryPage;
import proyecto.pages.LoginPage;

class CheckoutFormTest extends BaseTest {

    @ParameterizedTest(name = "{0} {1}")
    @CsvFileSource(resources = "/checkout_cases.csv", numLinesToSkip = 1, delimiter = ';')
    @DisplayName("Formulario de compra rechazado según tabla de decisión")
    void formularioRechazado(String id, String descripcion, String nombre,
                             String apellido, String postal, String mensajeEsperado) {
        InventoryPage inventario = new LoginPage(driver).iniciarSesionComo("standard_user");
        inventario.agregarProducto("sauce-labs-backpack");
        CartPage carrito = inventario.irAlCarrito();
        CheckoutPage checkout = carrito.irAPagar();

        checkout.completarDatos(nombre, apellido, postal);
        checkout.continuar();

        assertEquals(mensajeEsperado, checkout.obtenerError());
    }
}