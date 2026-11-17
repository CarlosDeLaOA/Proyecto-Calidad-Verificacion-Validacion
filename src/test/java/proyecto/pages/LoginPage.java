package proyecto.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import proyecto.base.Config;

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

    // Atajo: entra con la clave de config.properties y devuelve la siguiente pantalla
    public InventoryPage iniciarSesionComo(String usuario) {
        iniciarSesion(usuario, Config.get("password"));
        return new InventoryPage(driver);
    }

    public String obtenerError() {
        return textoDe(mensajeError);
    }

    public boolean estaMostrandoLogin() {
        return estaVisible(botonLogin);
    }
}