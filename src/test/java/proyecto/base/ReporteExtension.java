package proyecto.base;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.Optional;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ReporteExtension implements AfterTestExecutionCallback {

    private static final Path CARPETA = Path.of("target", "reporte");
    private static final ExtentReports REPORTE = crearReporte();

    private static ExtentReports crearReporte() {
        ExtentSparkReporter spark = new ExtentSparkReporter(CARPETA.resolve("reporte.html").toString());
        spark.config().setDocumentTitle("Reporte BISOFT-32");
        spark.config().setReportName("Pruebas automatizadas Swag Labs");

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("URL", Config.get("base.url"));
        extent.setSystemInfo("Navegador", Config.get("browser"));
        Runtime.getRuntime().addShutdownHook(new Thread(extent::flush));
        return extent;
    }

    @Override
    public void afterTestExecution(ExtensionContext context) {
        ExtentTest test = REPORTE.createTest(context.getDisplayName());
        Optional<Throwable> error = context.getExecutionException();

        if (error.isPresent()) {
            test.fail(error.get());
            capturar(context, test);
        } else {
            test.pass("El test pasó");
        }
    }

    private void capturar(ExtensionContext context, ExtentTest test) {
        try {
            Object instancia = context.getRequiredTestInstance();
            if (instancia instanceof BaseTest base && base.driver != null) {
                byte[] png = ((TakesScreenshot) base.driver).getScreenshotAs(OutputType.BYTES);

                String nombre = context.getDisplayName().replaceAll("[^a-zA-Z0-9_-]", "_") + ".png";
                Path capturas = CARPETA.resolve("capturas");
                Files.createDirectories(capturas);
                Files.write(capturas.resolve(nombre), png);

                test.addScreenCaptureFromBase64String(
                        Base64.getEncoder().encodeToString(png), "Captura al fallar");
            }
        } catch (Exception e) {
            test.warning("No se pudo tomar la captura: " + e.getMessage());
        }
    }
}