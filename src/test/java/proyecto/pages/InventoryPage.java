package proyecto.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class InventoryPage extends BasePage {
    private final By titulo = By.cssSelector("[data-test='title']");
    private final By insigniaCarrito = By.cssSelector("[data-test='shopping-cart-badge']");
    private final By enlaceCarrito = By.cssSelector("[data-test='shopping-cart-link']");
    private final By selectorOrden = By.cssSelector("[data-test='product-sort-container']");
    private final By nombres = By.cssSelector("[data-test='inventory-item-name']");
    private final By precios = By.cssSelector("[data-test='inventory-item-price']");
    private final By imagenes = By.cssSelector("img.inventory_item_img");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public String obtenerTitulo() {
        return textoDe(titulo);
    }

    public void agregarProducto(String producto) {
        clic(By.cssSelector("[data-test='add-to-cart-" + aSlug(producto) + "']"));
    }

    public void quitarProducto(String producto) {
        clic(By.cssSelector("[data-test='remove-" + aSlug(producto) + "']"));
    }

    // Si no hay insignia en el carrito, la cantidad es 0
    public int obtenerCantidadCarrito() {
        wait.until(ExpectedConditions.urlContains("inventory.html"));
        return wait.ignoring(StaleElementReferenceException.class).until(d -> {
            List<WebElement> insignia = d.findElements(insigniaCarrito);
            return insignia.isEmpty() ? 0 : Integer.parseInt(insignia.get(0).getText());
        });
    }

    // Valores válidos: "az", "za", "lohi", "hilo"
    public void ordenarPor(String valor) {
        new Select(esperarVisible(selectorOrden)).selectByValue(valor);
    }

    public List<String> obtenerNombres() {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(nombres));
        return textosDe(nombres);
    }

    public List<Double> obtenerPrecios() {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(precios));
        return textosDe(precios).stream()
                .map(texto -> Double.parseDouble(texto.replace("$", "")))
                .toList();
    }

    // Devuelve la dirección (src) de la imagen de cada producto
    public List<String> obtenerImagenes() {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(imagenes));
        return driver.findElements(imagenes).stream()
                .map(imagen -> imagen.getAttribute("src"))
                .toList();
    }

    public CartPage irAlCarrito() {
        clic(enlaceCarrito);
        return new CartPage(driver);
    }
    // Espera a que desaparezca la insignia del carrito; true si no hay productos
    public boolean carritoEstaVacio() {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(insigniaCarrito));
    }
}