package tr.edu.sakarya.bil002.ui.stepdefs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.cucumber.java.tr.Diyelimki;
import io.cucumber.java.tr.Eğerki;
import io.cucumber.java.tr.Ozaman;

import tr.edu.sakarya.bil002.ui.support.TestContext;

/**
 * Ödeme akışı adımları (odeme.feature).
 */
public class OdemeAdimlari {

    private static final double KURUS = 0.001;

    private final TestContext baglam;

    public OdemeAdimlari(TestContext baglam) {
        this.baglam = baglam;
    }

    @Diyelimki("sepetten ödeme adımına geçtim")
    public void odemeAdiminaGectim() {
        baglam.inventoryPage().sepeteGit().odemeyeGec();
    }

    @Eğerki("teslimat bilgilerini {string}, {string}, {string} olarak girersem")
    public void teslimatBilgileriniGirersem(String ad, String soyad, String postaKodu) {
        baglam.checkoutInfoPage().bilgileriDoldur(ad, soyad, postaKodu);
    }

    @Eğerki("siparişi tamamlarsam")
    public void siparisiTamamlarsam() {
        baglam.checkoutOverviewPage().siparisiTamamla();
    }

    @Ozaman("ödeme formunda {string} uyarısını görmeliyim")
    public void odemeUyarisiGormeliyim(String uyari) {
        assertEquals(uyari, baglam.checkoutInfoPage().hataMesaji());
    }

    @Ozaman("{string} onay mesajını görmeliyim")
    public void onayMesajiGormeliyim(String mesaj) {
        assertEquals(mesaj, baglam.checkoutCompletePage().tamamlanmaMesaji());
    }

    @Ozaman("sepet boşalmış olmalı")
    public void sepetBosalmisOlmali() {
        assertEquals(0, baglam.checkoutCompletePage().sepetRozetiSayisi());
    }

    @Ozaman("özet sayfasındaki ara toplam ürün fiyatlarının toplamına eşit olmalı")
    public void araToplamDogru() {
        var ozet = baglam.checkoutOverviewPage();
        double fiyatToplami = ozet.urunFiyatlari().stream().mapToDouble(Double::doubleValue).sum();
        assertEquals(fiyatToplami, ozet.araToplam(), KURUS);
    }

    @Ozaman("ara toplam {string} ve genel toplam ara toplam ile verginin toplamı olmalı")
    public void genelToplamDogru(String araToplam) {
        var ozet = baglam.checkoutOverviewPage();
        assertEquals(Double.parseDouble(araToplam.replace("$", "")), ozet.araToplam(), KURUS);
        assertEquals(ozet.araToplam() + ozet.vergi(), ozet.toplam(), KURUS);
    }
}
