package tr.edu.sakarya.bil002.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Ödeme 1. adım: teslimat bilgileri (/checkout-step-one.html).
 */
public class CheckoutInfoPage extends BasePage {

    private static final By AD = By.id("first-name");
    private static final By SOYAD = By.id("last-name");
    private static final By POSTA_KODU = By.id("postal-code");
    private static final By DEVAM = By.id("continue");
    private static final By HATA_MESAJI = By.cssSelector("[data-test='error']");

    public CheckoutInfoPage(WebDriver driver) {
        super(driver);
        urlIcerene("/checkout-step-one.html");
        gorunurOlana(DEVAM);
    }

    public void bilgileriDoldur(String ad, String soyad, String postaKodu) {
        yaz(AD, ad);
        yaz(SOYAD, soyad);
        yaz(POSTA_KODU, postaKodu);
        tikla(DEVAM);
    }

    public CheckoutOverviewPage bilgileriDoldurVeDevamEt(String ad, String soyad, String postaKodu) {
        bilgileriDoldur(ad, soyad, postaKodu);
        return new CheckoutOverviewPage(driver);
    }

    public String hataMesaji() {
        return gorunurOlana(HATA_MESAJI).getText();
    }
}
