package proyecto.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
        wait.until(ExpectedConditions.elementToBeClickable(localizador)).click();
    }

    protected void escribir(By localizador, String texto) {
        WebElement elemento = esperarVisible(localizador);
        elemento.clear();
        elemento.sendKeys(texto);
    }

    protected String textoDe(By localizador) {
        return esperarVisible(localizador).getText();
    }

    protected boolean estaVisible(By localizador) {
        try {
            return esperarVisible(localizador).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
}