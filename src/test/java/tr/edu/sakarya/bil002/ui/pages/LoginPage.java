package tr.edu.sakarya.bil002.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Giriş sayfası (https://www.saucedemo.com/).
 */
public class LoginPage extends BasePage {

    private static final By KULLANICI_ADI = By.id("user-name");
    private static final By SIFRE = By.id("password");
    private static final By GIRIS_BUTONU = By.id("login-button");
    private static final By HATA_MESAJI = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage ac() {
        driver.get(TABAN_URL + "/");
        gorunurOlana(GIRIS_BUTONU);
        return this;
    }

    public void girisYap(String kullanici, String sifre) {
        yaz(KULLANICI_ADI, kullanici);
        yaz(SIFRE, sifre);
        tikla(GIRIS_BUTONU);
    }

    public InventoryPage basariliGiris(String kullanici, String sifre) {
        girisYap(kullanici, sifre);
        return new InventoryPage(driver).yuklenmesiniBekle();
    }

    public String hataMesaji() {
        return gorunurOlana(HATA_MESAJI).getText();
    }

    public boolean girisFormuGorunuyor() {
        return gorunurOlana(GIRIS_BUTONU).isDisplayed();
    }
}
