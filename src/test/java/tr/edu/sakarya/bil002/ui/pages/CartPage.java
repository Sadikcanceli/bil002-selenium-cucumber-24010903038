package tr.edu.sakarya.bil002.ui.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Sepet sayfası (/cart.html).
 */
public class CartPage extends BasePage {

    private static final By SEPET_SATIRI = By.cssSelector("[data-test='inventory-item']");
    private static final By URUN_ADI = By.cssSelector("[data-test='inventory-item-name']");
    private static final By ODEME_BUTONU = By.id("checkout");
    private static final By ALISVERISE_DEVAM = By.id("continue-shopping");

    public CartPage(WebDriver driver) {
        super(driver);
        urlIcerene("/cart.html");
        gorunurOlana(ODEME_BUTONU);
    }

    public List<String> urunAdlari() {
        return driver.findElements(SEPET_SATIRI).isEmpty()
                ? List.of()
                : hepsiGorunurOlana(URUN_ADI).stream().map(WebElement::getText).toList();
    }

    public CheckoutInfoPage odemeyeGec() {
        tikla(ODEME_BUTONU);
        return new CheckoutInfoPage(driver);
    }

    public InventoryPage alisveriseDevamEt() {
        tikla(ALISVERISE_DEVAM);
        return new InventoryPage(driver).yuklenmesiniBekle();
    }
}
