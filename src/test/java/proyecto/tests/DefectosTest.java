package proyecto.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import proyecto.base.BaseTest;
import proyecto.pages.CartPage;
import proyecto.pages.CheckoutPage;
import proyecto.pages.InventoryPage;
import proyecto.pages.LoginPage;

/**
 * Detección automática de los defectos de problem_user y error_user

 */
@Tag("defectos")
class DefectosTest extends BaseTest {

    private static final String MOCHILA = "Sauce Labs Backpack";

    @Test
    @DisplayName("DEF01 problem_user: cada producto debe tener su propia imagen")
    void imagenesDistintas() {
        InventoryPage inventario = new LoginPage(driver).iniciarSesionComo("problem_user");
        List<String> imagenes = inventario.obtenerImagenes();

        assertEquals(6, new HashSet<>(imagenes).size(),
                "DEF01: los productos comparten imagen");
    }

    @Test
    @DisplayName("DEF02 problem_user: ordenar por precio de mayor a menor debe cambiar el orden")
    void ordenamientoFunciona() {
        InventoryPage inventario = new LoginPage(driver).iniciarSesionComo("problem_user");
        inventario.ordenarPor("hilo");
        List<Double> precios = inventario.obtenerPrecios();

        List<Double> esperado = precios.stream().sorted(Comparator.reverseOrder()).toList();
        assertEquals(esperado, precios, "DEF02: el orden de los productos no cambia");
    }

    @ParameterizedTest(name = "{1} {0}: agregar los 6 productos debe dejar el contador en 6")
    @CsvSource({"problem_user,DEF03", "error_user,DEF08"})
    @DisplayName("Agregar todos los productos al carrito")
    void agregarTodosLosProductos(String usuario, String defecto) {
        InventoryPage inventario = new LoginPage(driver).iniciarSesionComo(usuario);

        for (String producto : inventario.obtenerNombres()) {
            inventario.agregarProducto(producto);
        }

        assertEquals(6, inventario.obtenerCantidadCarrito(),
                defecto + ": algunos botones Add to cart no funcionan");
    }

    @Test
    @DisplayName("DEF04 problem_user: Remove debe bajar el contador del carrito")
    void quitarProductoFunciona() {
        InventoryPage inventario = new LoginPage(driver).iniciarSesionComo("problem_user");
        inventario.agregarProducto(MOCHILA);
        inventario.quitarProducto(MOCHILA);

        assertEquals(0, inventario.obtenerCantidadCarrito(),
                "DEF04: el botón Remove no quita el producto");
    }

    @ParameterizedTest(name = "{1} {0}: el campo apellido debe guardar el texto")
    @CsvSource({"problem_user,DEF06", "error_user,DEF09"})
    @DisplayName("Campo apellido del checkout")
    void apellidoSeGuarda(String usuario, String defecto) {
        CheckoutPage checkout = irAlFormularioDeCompra(usuario);

        checkout.completarDatos("Carlos", "Perez", "10101");

        assertEquals("Perez", checkout.obtenerApellido(),
                defecto + ": el apellido no se guarda en el formulario");
    }

    @Test
    @DisplayName("DEF10 error_user: Finish debe completar la compra")
    void finalizarCompra() {
        CheckoutPage checkout = irAlFormularioDeCompra("error_user");

        checkout.completarDatos("Carlos", "Perez", "10101");
        checkout.continuar();
        checkout.finalizar();

        assertEquals("Thank you for your order!", checkout.obtenerMensajeConfirmacion(),
                "DEF10: el botón Finish no completa la compra");
    }

    // Inicia sesión, agrega la mochila y abre el formulario de datos del comprador
    private CheckoutPage irAlFormularioDeCompra(String usuario) {
        InventoryPage inventario = new LoginPage(driver).iniciarSesionComo(usuario);
        inventario.agregarProducto(MOCHILA);
        CartPage carrito = inventario.irAlCarrito();
        return carrito.irAPagar();
    }
}