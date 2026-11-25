package tr.edu.sakarya.bil002.ui.stepdefs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.cucumber.java.tr.Diyelimki;
import io.cucumber.java.tr.Eğerki;
import io.cucumber.java.tr.Ozaman;

import tr.edu.sakarya.bil002.ui.support.TestContext;

/**
 * Giriş ve çıkış adımları (giris.feature). Adımlar yalnızca page nesnelerinin metotlarını çağırır.
 */
public class GirisAdimlari {

    private final TestContext baglam;

    public GirisAdimlari(TestContext baglam) {
        this.baglam = baglam;
    }

    @Diyelimki("giriş sayfasındayım")
    public void girisSayfasindayim() {
        baglam.loginPage().ac();
    }

    @Diyelimki("{string} kullanıcısıyla giriş yapmış durumdayım")
    public void girisYapmisDurumdayim(String kullanici) {
        baglam.loginPage().ac().basariliGiris(kullanici, "secret_sauce");
    }

    @Eğerki("{string} kullanıcı adı ve {string} şifresiyle giriş yaparsam")
    public void girisYaparsam(String kullanici, String sifre) {
        baglam.loginPage().girisYap(kullanici, sifre);
    }

    @Eğerki("menüden çıkış yaparsam")
    public void menudenCikisYaparsam() {
        baglam.inventoryPage().cikisYap();
    }

    @Ozaman("ürünler sayfasını görmeliyim")
    public void urunlerSayfasiniGormeliyim() {
        var sayfa = baglam.inventoryPage().yuklenmesiniBekle();
        assertEquals("Products", sayfa.sayfaBasligi());
        assertTrue(sayfa.mevcutUrl().endsWith("/inventory.html"), sayfa.mevcutUrl());
    }

    @Ozaman("{string} hata mesajını görmeliyim")
    public void hataMesajiniGormeliyim(String beklenen) {
        assertEquals(beklenen, baglam.loginPage().hataMesaji());
    }

    @Ozaman("giriş sayfasına geri dönmeliyim")
    public void girisSayfasinaDonmeliyim() {
        var sayfa = baglam.loginPage();
        assertTrue(sayfa.girisFormuGorunuyor());
        assertEquals("https://www.saucedemo.com/", sayfa.mevcutUrl());
    }
}
