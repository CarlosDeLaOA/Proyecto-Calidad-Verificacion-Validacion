package proyecto.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MenuPage extends BasePage {
    private final By botonMenu = By.id("react-burger-menu-btn");
    private final By enlaceCerrarSesion = By.cssSelector("[data-test='logout-sidebar-link']");
    private final By enlaceReiniciar = By.cssSelector("[data-test='reset-sidebar-link']");

    public MenuPage(WebDriver driver) {
        super(driver);
    }

    public void abrirMenu() {
        clic(botonMenu);
    }

    public LoginPage cerrarSesion() {
        abrirMenu();
        clic(enlaceCerrarSesion);
        return new LoginPage(driver);
    }

    public void reiniciarEstadoAplicacion() {
        abrirMenu();
        clic(enlaceReiniciar);
    }
}