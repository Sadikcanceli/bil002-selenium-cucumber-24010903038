package tr.edu.sakarya.bil002.ui.support;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

/**
 * Tarayıcıyı sistem özelliklerine göre oluşturur.
 * -Dtarayici=chrome|edge|firefox (varsayılan chrome), -Dheadless=true|false (varsayılan true).
 * Sürücü (chromedriver/msedgedriver/geckodriver) Selenium Manager tarafından otomatik indirilir.
 */
public final class DriverFactory {

    private DriverFactory() {
    }

    public static WebDriver olustur() {
        String tarayici = System.getProperty("tarayici", "chrome").toLowerCase();
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "true"));

        WebDriver driver = switch (tarayici) {
            case "edge" -> new EdgeDriver(edgeAyarlari(headless));
            case "firefox" -> new FirefoxDriver(firefoxAyarlari(headless));
            default -> new ChromeDriver(chromeAyarlari(headless));
        };
        // Örtük bekleme kapalı: tüm beklemeler BasePage'deki WebDriverWait ile açıkça yapılır.
        driver.manage().timeouts().implicitlyWait(Duration.ZERO);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        return driver;
    }

    /** SauceDemo'daki zayıf demo parolası tarayıcının "parola sızdırıldı" penceresini açmasın. */
    private static Map<String, Object> parolaYoneticisiKapali() {
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);
        return prefs;
    }

    private static ChromeOptions chromeAyarlari(boolean headless) {
        ChromeOptions o = new ChromeOptions();
        if (headless) {
            o.addArguments("--headless=new");
        }
        o.addArguments("--window-size=1366,900", "--disable-notifications", "--no-sandbox",
                "--disable-dev-shm-usage", "--guest");
        o.setExperimentalOption("prefs", parolaYoneticisiKapali());
        return o;
    }

    private static EdgeOptions edgeAyarlari(boolean headless) {
        EdgeOptions o = new EdgeOptions();
        if (headless) {
            o.addArguments("--headless=new");
        }
        o.addArguments("--window-size=1366,900", "--disable-notifications", "--inprivate");
        o.setExperimentalOption("prefs", parolaYoneticisiKapali());
        return o;
    }

    private static FirefoxOptions firefoxAyarlari(boolean headless) {
        FirefoxOptions o = new FirefoxOptions();
        if (headless) {
            o.addArguments("-headless");
        }
        o.addArguments("--width=1366", "--height=900");
        return o;
    }
}
