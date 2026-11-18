package proyecto.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import proyecto.base.BaseTest;
import proyecto.base.Config;
import proyecto.pages.InventoryPage;
import proyecto.pages.LoginPage;

class RendimientoTest extends BaseTest {

    private long medirLoginEnMs(String usuario) {
        LoginPage login = new LoginPage(driver);
        long inicio = System.nanoTime();
        login.iniciarSesion(usuario, Config.get("password"));
        String titulo = new InventoryPage(driver).obtenerTitulo();
        long ms = (System.nanoTime() - inicio) / 1_000_000;
        System.out.println("Tiempo de login de " + usuario + ": " + ms + " ms");
        assertEquals("Products", titulo);
        return ms;
    }

    @Test
    @DisplayName("NF01 El login de standard_user responde en menos de 3 segundos")
    void loginEstandarRapido() {
        long ms = medirLoginEnMs("standard_user");
        assertTrue(ms < 3000, "Tardó " + ms + " ms");
    }

    @Test
    @DisplayName("NF02 El login de performance_glitch_user responde en menos de 10 segundos")
    void loginConDemoraAceptable() {
        long ms = medirLoginEnMs("performance_glitch_user");
        assertTrue(ms < 10000, "Tardó " + ms + " ms");
    }
}