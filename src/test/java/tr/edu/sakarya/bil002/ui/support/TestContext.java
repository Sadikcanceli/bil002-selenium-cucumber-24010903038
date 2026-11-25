package tr.edu.sakarya.bil002.ui.support;

import org.openqa.selenium.WebDriver;

import tr.edu.sakarya.bil002.ui.pages.CartPage;
import tr.edu.sakarya.bil002.ui.pages.CheckoutCompletePage;
import tr.edu.sakarya.bil002.ui.pages.CheckoutInfoPage;
import tr.edu.sakarya.bil002.ui.pages.CheckoutOverviewPage;
import tr.edu.sakarya.bil002.ui.pages.InventoryPage;
import tr.edu.sakarya.bil002.ui.pages.LoginPage;

/**
 * Senaryo boyunca paylaşılan nesneler. PicoContainer her senaryoda yeni bir TestContext oluşturur ve
 * bunu tüm adım sınıflarına ve Hooks'a enjekte eder; böylece aynı tarayıcı ve page nesneleri kullanılır.
 */
public class TestContext {

    private WebDriver driver;

    public void baslat() {
        driver = DriverFactory.olustur();
    }

    public void kapat() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public WebDriver driver() {
        return driver;
    }

    public LoginPage loginPage() {
        return new LoginPage(driver);
    }

    public InventoryPage inventoryPage() {
        return new InventoryPage(driver);
    }

    public CartPage cartPage() {
        return new CartPage(driver);
    }

    public CheckoutInfoPage checkoutInfoPage() {
        return new CheckoutInfoPage(driver);
    }

    public CheckoutOverviewPage checkoutOverviewPage() {
        return new CheckoutOverviewPage(driver);
    }

    public CheckoutCompletePage checkoutCompletePage() {
        return new CheckoutCompletePage(driver);
    }
}
