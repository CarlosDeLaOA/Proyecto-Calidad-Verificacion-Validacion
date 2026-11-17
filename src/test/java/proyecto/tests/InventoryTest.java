package proyecto.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import proyecto.base.BaseTest;
import proyecto.pages.InventoryPage;
import proyecto.pages.LoginPage;

class InventoryTest extends BaseTest {
    private InventoryPage inventario;

    @BeforeEach
    void iniciarSesion() {
        inventario = new LoginPage(driver).iniciarSesionComo("standard_user");
    }

    @Test
    @DisplayName("TC13 Ordenar productos por nombre de A a Z")
    void ordenarNombreAscendente() {
        inventario.ordenarPor("az");
        List<String> nombres = inventario.obtenerNombres();
        assertEquals(nombres.stream().sorted().toList(), nombres);
    }

    @Test
    @DisplayName("TC14 Ordenar productos por nombre de Z a A")
    void ordenarNombreDescendente() {
        inventario.ordenarPor("za");
        List<String> nombres = inventario.obtenerNombres();
        assertEquals(nombres.stream().sorted(Comparator.reverseOrder()).toList(), nombres);
    }

    @Test
    @DisplayName("TC15 Ordenar productos por precio de menor a mayor")
    void ordenarPrecioAscendente() {
        inventario.ordenarPor("lohi");
        List<Double> precios = inventario.obtenerPrecios();
        assertEquals(precios.stream().sorted().toList(), precios);
    }

    @Test
    @DisplayName("TC16 Ordenar productos por precio de mayor a menor")
    void ordenarPrecioDescendente() {
        inventario.ordenarPor("hilo");
        List<Double> precios = inventario.obtenerPrecios();
        assertEquals(precios.stream().sorted(Comparator.reverseOrder()).toList(), precios);
    }
}