package proyecto.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import proyecto.base.Config;

public abstract class BasePage {
    protected final WebDriver driver;
    protected final WebDriverWait wait;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Config.getInt("timeout.seconds")));
    }

    protected WebElement esperarVisible(By localizador) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(localizador));
    }


    protected void clic(By localizador) {
        wait.ignoring(StaleElementReferenceException.class, ElementClickInterceptedException.class)
                .ignoring(ElementNotInteractableException.class)
                .until(d -> {
                    d.findElement(localizador).click();
                    return true;
                });
    }

    protected void escribir(By localizador, String texto) {
        WebElement elemento = esperarVisible(localizador);
        elemento.clear();
        elemento.sendKeys(texto);
    }


    protected boolean estaVisible(By localizador) {
        try {
            return esperarVisible(localizador).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    // Convierte "Sauce Labs Backpack" en "sauce-labs-backpack", como lo usa data-test
    protected String aSlug(String texto) {
        return texto.toLowerCase().replace(" ", "-");
    }

    // Devuelve el texto de todos los elementos que coinciden (lista vacía si no hay)
    protected List<String> textosDe(By localizador) {
        return wait.ignoring(StaleElementReferenceException.class)
                .until(d -> d.findElements(localizador).stream()
                        .map(WebElement::getText)
                        .toList());
    }
    protected String textoDe(By localizador) {
        return wait.ignoring(StaleElementReferenceException.class)
                .until(d -> d.findElement(localizador).getText());
    }

}