package proyecto.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends BasePage {
    private final By nombres = By.cssSelector("[data-test='inventory-item-name']");
    private final By listaCarrito = By.cssSelector("[data-test='cart-list']");
    private final By botonPagar = By.cssSelector("[data-test='checkout']");
    private final By seguirComprando = By.cssSelector("[data-test='continue-shopping']");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public List<String> obtenerNombresProductos() {
        wait.until(ExpectedConditions.urlContains("cart.html"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(listaCarrito));
        return textosDe(nombres);
    }

    public void quitarProducto(String producto) {
        clic(By.cssSelector("[data-test='remove-" + aSlug(producto) + "']"));
    }

    public InventoryPage seguirComprando() {
        clic(seguirComprando);
        return new InventoryPage(driver);
    }

    public CheckoutPage irAPagar() {
        clic(botonPagar);
        return new CheckoutPage(driver);
    }
}