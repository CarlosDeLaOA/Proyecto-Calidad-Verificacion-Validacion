package proyecto.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import proyecto.base.BaseTest;
import proyecto.pages.InventoryPage;
import proyecto.pages.LoginPage;
import proyecto.pages.MenuPage;

class SessionTest extends BaseTest {
    private InventoryPage inventario;
    private MenuPage menu;

    @BeforeEach
    void iniciarSesion() {
        inventario = new LoginPage(driver).iniciarSesionComo("standard_user");
        menu = new MenuPage(driver);
    }

    @Test
    @DisplayName("TC39 Cerrar sesión regresa a la pantalla de login")
    void cerrarSesion() {
        LoginPage login = menu.cerrarSesion();
        assertTrue(login.estaMostrandoLogin());
    }

    @Test
    @DisplayName("TC25 Reiniciar el estado de la aplicación vacía el carrito")
    void reiniciarEstado() {
        inventario.agregarProducto("Sauce Labs Backpack");
        menu.reiniciarEstadoAplicacion();
        assertTrue(inventario.carritoEstaVacio());
    }
}