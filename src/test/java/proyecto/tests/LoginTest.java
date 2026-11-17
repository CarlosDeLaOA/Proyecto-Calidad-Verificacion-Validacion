package proyecto.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

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

    @ParameterizedTest(name = "{0} {1}")
    @CsvFileSource(resources = "/login_cases.csv", numLinesToSkip = 1, delimiter = ';')
    @DisplayName("Login rechazado según tabla de decisión")
    void loginRechazado(String id, String descripcion, String usuario,
                        String clave, String mensajeEsperado) {
        LoginPage login = new LoginPage(driver);
        login.iniciarSesion(usuario, clave);

        assertEquals(mensajeEsperado, login.obtenerError());
    }
}