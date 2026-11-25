package tr.edu.sakarya.bil002.ui.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

/**
 * Ürünler sayfası (/inventory.html): listeleme, sıralama ve sepete ekleme/çıkarma.
 */
public class InventoryPage extends BasePage {

    private static final By URUN_KARTI = By.cssSelector("[data-test='inventory-item']");
    private static final By URUN_ADI = By.cssSelector("[data-test='inventory-item-name']");
    private static final By URUN_FIYATI = By.cssSelector("[data-test='inventory-item-price']");
    private static final By SIRALAMA_KUTUSU = By.cssSelector("[data-test='product-sort-container']");
    private static final By KART_BUTONU = By.tagName("button");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public InventoryPage yuklenmesiniBekle() {
        urlIcerene("/inventory.html");
        hepsiGorunurOlana(URUN_KARTI);
        return this;
    }

    public List<String> urunAdlari() {
        return hepsiGorunurOlana(URUN_ADI).stream().map(WebElement::getText).toList();
    }

    /** "$29.99" biçimindeki fiyatları sayıya çevirir. */
    public List<Double> urunFiyatlari() {
        return hepsiGorunurOlana(URUN_FIYATI).stream()
                .map(e -> Double.parseDouble(e.getText().replace("$", "").trim()))
                .toList();
    }

    /**
     * Sıralamayı görünen metne göre seçer: "Name (A to Z)", "Name (Z to A)",
     * "Price (low to high)", "Price (high to low)".
     */
    public InventoryPage siralamaSec(String gorunenMetin) {
        String ilkUrun = urunAdlari().get(0);
        new Select(gorunurOlana(SIRALAMA_KUTUSU)).selectByVisibleText(gorunenMetin);
        // Sıralama yeniden çizim yapar; liste değişene veya seçim uygulanana kadar bekle.
        wait.until(d -> !urunAdlari().get(0).equals(ilkUrun) || seciliSiralama().equals(gorunenMetin));
        return this;
    }

    public String seciliSiralama() {
        return new Select(gorunurOlana(SIRALAMA_KUTUSU)).getFirstSelectedOption().getText();
    }

    private WebElement urunKarti(String urunAdi) {
        By kart = By.xpath("//div[@data-test='inventory-item'][.//div[@data-test='inventory-item-name' and "
                + "normalize-space(.)='" + urunAdi + "']]");
        return gorunurOlana(kart);
    }

    public InventoryPage sepeteEkle(String urunAdi) {
        butonaTiklaVeMetniBekle(urunAdi, "Add to cart", "Remove");
        return this;
    }

    public InventoryPage sepettenCikar(String urunAdi) {
        butonaTiklaVeMetniBekle(urunAdi, "Remove", "Add to cart");
        return this;
    }

    /**
     * SauceDemo (React) tıklamadan sonra butonu DOM'dan kaldırıp yerine yenisini çizer; eski WebElement
     * referansı bayatlar (StaleElementReference). Bu yüzden buton her kontrolde ürün kartından yeniden bulunur.
     */
    private void butonaTiklaVeMetniBekle(String urunAdi, String onceki, String sonraki) {
        wait.until(d -> onceki.equals(urunButonMetni(urunAdi)));
        WebElement buton = urunKarti(urunAdi).findElement(KART_BUTONU);
        wait.until(ExpectedConditions.elementToBeClickable(buton)).click();
        wait.ignoring(StaleElementReferenceException.class).until(d -> sonraki.equals(urunButonMetni(urunAdi)));
    }

    public String urunButonMetni(String urunAdi) {
        return urunKarti(urunAdi).findElement(KART_BUTONU).getText();
    }
}
