package proyecto.base;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public abstract class BaseTest {
    protected WebDriver driver;

    @BeforeEach
    protected void abrirNavegador() {
        driver = crearDriver();
        driver.manage().window().maximize();
        driver.get(Config.get("base.url"));
    }

    @AfterEach
    protected void cerrarNavegador() {
        if (driver != null) {
            driver.quit();
        }
    }

    private WebDriver crearDriver() {
        boolean headless = Boolean.parseBoolean(Config.get("headless"));
        switch (Config.get("browser").toLowerCase()) {
            case "firefox": {
                FirefoxOptions opciones = new FirefoxOptions();
                if (headless) opciones.addArguments("-headless");
                return new FirefoxDriver(opciones);
            }
            case "edge": {
                EdgeOptions opciones = new EdgeOptions();
                if (headless) opciones.addArguments("--headless=new");
                return new EdgeDriver(opciones);
            }
            default: {
                ChromeOptions opciones = new ChromeOptions();
                // Evita el aviso de "cambiar contraseña" de Chrome, que tapa la página
                Map<String, Object> prefs = new HashMap<>();
                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);
                prefs.put("profile.password_manager_leak_detection", false);
                opciones.setExperimentalOption("prefs", prefs);
                opciones.addArguments("--incognito");
                if (headless) opciones.addArguments("--headless=new");
                return new ChromeDriver(opciones);
            }
        }
    }
}