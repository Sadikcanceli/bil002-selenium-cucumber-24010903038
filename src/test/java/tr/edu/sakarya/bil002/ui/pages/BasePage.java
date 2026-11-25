package tr.edu.sakarya.bil002.ui.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Tüm sayfaların ortak temeli. Bütün beklemeler burada WebDriverWait + ExpectedConditions ile yapılır;
 * Thread.sleep hiç kullanılmaz.
 */
public abstract class BasePage {

    protected static final String TABAN_URL = "https://www.saucedemo.com";
    private static final Duration ZAMAN_ASIMI = Duration.ofSeconds(10);

    // Tüm iç sayfalarda bulunan üst menü ve sepet bağlantısı
    private static final By SEPET_ROZETI = By.cssSelector("[data-test='shopping-cart-badge']");
    private static final By SEPET_BAGLANTISI = By.cssSelector("[data-test='shopping-cart-link']");
    private static final By MENU_BUTONU = By.id("react-burger-menu-btn");
    private static final By CIKIS_BAGLANTISI = By.id("logout_sidebar_link");
    private static final By SAYFA_BASLIGI = By.cssSelector("[data-test='title']");

    protected final WebDriver driver;
    protected final WebDriverWait wait;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, ZAMAN_ASIMI);
    }

    // ------------------------------------------------------------ bekleme yardımcıları

    protected WebElement gorunurOlana(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected List<WebElement> hepsiGorunurOlana(By locator) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    protected void tikla(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected void yaz(By locator, String metin) {
        WebElement alan = gorunurOlana(locator);
        alan.clear();
        if (metin != null && !metin.isEmpty()) {
            alan.sendKeys(metin);
        }
    }

    protected void urlIcerene(String parca) {
        wait.until(ExpectedConditions.urlContains(parca));
    }

    // ------------------------------------------------------------ ortak eylemler

    public String sayfaBasligi() {
        return gorunurOlana(SAYFA_BASLIGI).getText();
    }

    public String mevcutUrl() {
        return driver.getCurrentUrl();
    }

    /** Sepet rozetindeki sayı; sepet boşsa rozet DOM'da olmaz ve 0 döner. */
    public int sepetRozetiSayisi() {
        List<WebElement> rozet = driver.findElements(SEPET_ROZETI);
        return rozet.isEmpty() ? 0 : Integer.parseInt(rozet.get(0).getText().trim());
    }

    /** Rozet sayısı beklenen değere gelene kadar bekler (React güncellemesi anlık olmayabilir). */
    public void sepetRozetiOlana(int beklenen) {
        wait.until(d -> sepetRozetiSayisi() == beklenen);
    }

    public CartPage sepeteGit() {
        tikla(SEPET_BAGLANTISI);
        return new CartPage(driver);
    }

    public LoginPage cikisYap() {
        tikla(MENU_BUTONU);
        tikla(CIKIS_BAGLANTISI);
        return new LoginPage(driver);
    }
}
