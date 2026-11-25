package tr.edu.sakarya.bil002.ui.stepdefs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import io.cucumber.java.tr.Eğerki;
import io.cucumber.java.tr.Ozaman;

import tr.edu.sakarya.bil002.ui.support.TestContext;

/**
 * Ürün listeleme ve sıralama adımları (urunler.feature).
 */
public class UrunAdimlari {

    private final TestContext baglam;

    public UrunAdimlari(TestContext baglam) {
        this.baglam = baglam;
    }

    @Eğerki("ürünleri {string} seçeneğine göre sıralarsam")
    public void urunleriSiralarsam(String secenek) {
        baglam.inventoryPage().siralamaSec(secenek);
    }

    @Ozaman("ürünler {string} şeklinde sıralanmış olmalı")
    public void urunlerSiralanmisOlmali(String olcut) {
        var sayfa = baglam.inventoryPage();
        switch (olcut) {
            case "ada göre artan" -> {
                List<String> adlar = sayfa.urunAdlari();
                List<String> beklenen = new ArrayList<>(adlar);
                beklenen.sort(Comparator.naturalOrder());
                assertEquals(beklenen, adlar);
            }
            case "ada göre azalan" -> {
                List<String> adlar = sayfa.urunAdlari();
                List<String> beklenen = new ArrayList<>(adlar);
                beklenen.sort(Comparator.reverseOrder());
                assertEquals(beklenen, adlar);
            }
            case "fiyata göre artan" -> {
                List<Double> fiyatlar = sayfa.urunFiyatlari();
                List<Double> beklenen = new ArrayList<>(fiyatlar);
                beklenen.sort(Comparator.naturalOrder());
                assertEquals(beklenen, fiyatlar);
            }
            case "fiyata göre azalan" -> {
                List<Double> fiyatlar = sayfa.urunFiyatlari();
                List<Double> beklenen = new ArrayList<>(fiyatlar);
                beklenen.sort(Comparator.reverseOrder());
                assertEquals(beklenen, fiyatlar);
            }
            default -> throw new IllegalArgumentException("Bilinmeyen sıralama ölçütü: " + olcut);
        }
    }

    @Ozaman("listedeki ilk ürün {string} olmalı")
    public void ilkUrunOlmali(String urun) {
        assertEquals(urun, baglam.inventoryPage().urunAdlari().get(0));
    }

    @Ozaman("listede {int} ürün görmeliyim ve her ürünün fiyatı sıfırdan büyük olmalı")
    public void listedeUrunGormeliyim(int adet) {
        List<Double> fiyatlar = baglam.inventoryPage().urunFiyatlari();
        assertEquals(adet, fiyatlar.size());
        assertTrue(fiyatlar.stream().allMatch(f -> f > 0), fiyatlar.toString());
    }
}
