package proyecto.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import proyecto.base.BaseTest;
import proyecto.pages.InventoryPage;
import proyecto.pages.LoginPage;

class LoginTest extends BaseTest {

    @Test
    @DisplayName("TC01 Login exitoso con usuario estándar")
    void loginExitoso() {
        LoginPage login = new LoginPage(driver);
        login.iniciarSesion("standard_user", "secret_sauce");

        InventoryPage inventario = new InventoryPage(driver);
        assertEquals("Products", inventario.obtenerTitulo());
    }

    @Test
    @DisplayName("TC02 Usuario bloqueado no puede iniciar sesión")
    void usuarioBloqueado() {
        LoginPage login = new LoginPage(driver);
        login.iniciarSesion("locked_out_user", "secret_sauce");

        assertEquals("Epic sadface: Sorry, this user has been locked out.", login.obtenerError());
    }
}