package tr.edu.sakarya.bil002.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Sipariş tamamlandı sayfası (/checkout-complete.html).
 */
public class CheckoutCompletePage extends BasePage {

    private static final By BASLIK = By.cssSelector("[data-test='complete-header']");
    private static final By ANA_SAYFAYA_DON = By.id("back-to-products");

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
        urlIcerene("/checkout-complete.html");
    }

    public String tamamlanmaMesaji() {
        return gorunurOlana(BASLIK).getText();
    }

    public InventoryPage urunlereDon() {
        tikla(ANA_SAYFAYA_DON);
        return new InventoryPage(driver).yuklenmesiniBekle();
    }
}
