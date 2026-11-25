package tr.edu.sakarya.bil002.ui.stepdefs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import io.cucumber.java.tr.Diyelimki;
import io.cucumber.java.tr.Eğerki;
import io.cucumber.java.tr.Ozaman;

import tr.edu.sakarya.bil002.ui.support.TestContext;

/**
 * Sepet adımları (sepet.feature ve odeme.feature'ın hazırlık adımları).
 */
public class SepetAdimlari {

    private final TestContext baglam;

    public SepetAdimlari(TestContext baglam) {
        this.baglam = baglam;
    }

    @Diyelimki("sepete {string} ürününü ekledim")
    @Eğerki("sepete {string} ürününü eklersem")
    public void sepeteUrunEkle(String urun) {
        baglam.inventoryPage().sepeteEkle(urun);
    }

    @Eğerki("{string} ürününü ürünler sayfasından sepetten çıkarırsam")
    public void sepettenCikar(String urun) {
        baglam.inventoryPage().sepettenCikar(urun);
    }

    @Eğerki("sepet sayfasına gidersem")
    public void sepetSayfasinaGidersem() {
        baglam.inventoryPage().sepeteGit();
    }

    @Ozaman("sepet rozetinde {int} görünmeli")
    public void sepetRozetindeGorunmeli(int adet) {
        var sayfa = baglam.inventoryPage();
        sayfa.sepetRozetiOlana(adet);
        assertEquals(adet, sayfa.sepetRozetiSayisi());
    }

    @Ozaman("{string} ürününün butonu {string} olmalı")
    public void urunButonuOlmali(String urun, String metin) {
        assertEquals(metin, baglam.inventoryPage().urunButonMetni(urun));
    }

    @Ozaman("sepette şu ürünler listelenmeli:")
    public void sepetteUrunlerListelenmeli(List<String> beklenenUrunler) {
        assertEquals(beklenenUrunler, baglam.cartPage().urunAdlari());
    }
}
