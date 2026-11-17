package proyecto.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    private final By campoUsuario = By.cssSelector("[data-test='username']");
    private final By campoClave = By.cssSelector("[data-test='password']");
    private final By botonLogin = By.cssSelector("[data-test='login-button']");
    private final By mensajeError = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void iniciarSesion(String usuario, String clave) {
        escribir(campoUsuario, usuario);
        escribir(campoClave, clave);
        clic(botonLogin);
    }

    public String obtenerError() {
        return textoDe(mensajeError);
    }
}