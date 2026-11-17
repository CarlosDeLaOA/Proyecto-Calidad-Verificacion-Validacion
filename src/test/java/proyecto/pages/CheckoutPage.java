package proyecto.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {
    private final By campoNombre = By.cssSelector("[data-test='firstName']");
    private final By campoApellido = By.cssSelector("[data-test='lastName']");
    private final By campoCodigoPostal = By.cssSelector("[data-test='postalCode']");
    private final By botonContinuar = By.cssSelector("[data-test='continue']");
    private final By botonFinalizar = By.cssSelector("[data-test='finish']");
    private final By mensajeError = By.cssSelector("[data-test='error']");
    private final By subtotal = By.cssSelector("[data-test='subtotal-label']");
    private final By impuesto = By.cssSelector("[data-test='tax-label']");
    private final By total = By.cssSelector("[data-test='total-label']");
    private final By encabezadoConfirmacion = By.cssSelector("[data-test='complete-header']");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    // Paso 1: datos del comprador
    public void completarDatos(String nombre, String apellido, String codigoPostal) {
        escribir(campoNombre, nombre);
        escribir(campoApellido, apellido);
        escribir(campoCodigoPostal, codigoPostal);
    }

    public void continuar() {
        clic(botonContinuar);
    }

    public String obtenerError() {
        return textoDe(mensajeError);
    }

    // Paso 2: resumen (devuelven el texto tal cual, por ejemplo "Item total: $29.99")
    public String obtenerSubtotal() {
        return textoDe(subtotal);
    }

    public String obtenerImpuesto() {
        return textoDe(impuesto);
    }

    public String obtenerTotal() {
        return textoDe(total);
    }

    public void finalizar() {
        clic(botonFinalizar);
    }

    // Paso 3: confirmación
    public String obtenerMensajeConfirmacion() {
        return textoDe(encabezadoConfirmacion);
    }
}