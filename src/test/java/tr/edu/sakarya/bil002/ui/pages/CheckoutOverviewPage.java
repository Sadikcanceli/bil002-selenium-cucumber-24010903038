package tr.edu.sakarya.bil002.ui.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Ödeme 2. adım: sipariş özeti (/checkout-step-two.html).
 */
public class CheckoutOverviewPage extends BasePage {

    private static final By URUN_FIYATI = By.cssSelector("[data-test='inventory-item-price']");
    private static final By ARA_TOPLAM = By.cssSelector("[data-test='subtotal-label']");
    private static final By VERGI = By.cssSelector("[data-test='tax-label']");
    private static final By TOPLAM = By.cssSelector("[data-test='total-label']");
    private static final By BITIR = By.id("finish");

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
        urlIcerene("/checkout-step-two.html");
        gorunurOlana(BITIR);
    }

    /** "Item total: $39.98" gibi etiketlerden sayıyı alır. */
    private double tutarOku(By locator) {
        String metin = gorunurOlana(locator).getText();
        return Double.parseDouble(metin.substring(metin.indexOf('$') + 1).trim());
    }

    public List<Double> urunFiyatlari() {
        return hepsiGorunurOlana(URUN_FIYATI).stream()
                .map(WebElement::getText)
                .map(t -> Double.parseDouble(t.replace("$", "").trim()))
                .toList();
    }

    public double araToplam() {
        return tutarOku(ARA_TOPLAM);
    }

    public double vergi() {
        return tutarOku(VERGI);
    }

    public double toplam() {
        return tutarOku(TOPLAM);
    }

    public CheckoutCompletePage siparisiTamamla() {
        tikla(BITIR);
        return new CheckoutCompletePage(driver);
    }
}
