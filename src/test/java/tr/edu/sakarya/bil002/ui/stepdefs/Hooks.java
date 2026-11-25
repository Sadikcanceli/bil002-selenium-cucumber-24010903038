package tr.edu.sakarya.bil002.ui.stepdefs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import tr.edu.sakarya.bil002.ui.support.TestContext;

/**
 * Her senaryodan önce tarayıcıyı açar, sonra kapatır. Başarısız senaryoda ekran görüntüsü alıp hem
 * Cucumber raporuna ekler hem de target/ekran-goruntuleri/ altına kaydeder.
 */
public class Hooks {

    private final TestContext baglam;

    public Hooks(TestContext baglam) {
        this.baglam = baglam;
    }

    @Before
    public void tarayiciyiAc() {
        baglam.baslat();
    }

    @After
    public void tarayiciyiKapat(Scenario senaryo) throws IOException {
        try {
            if (senaryo.isFailed() && baglam.driver() != null) {
                byte[] png = ((TakesScreenshot) baglam.driver()).getScreenshotAs(OutputType.BYTES);
                senaryo.attach(png, "image/png", senaryo.getName());
                Path klasor = Path.of("target", "ekran-goruntuleri");
                Files.createDirectories(klasor);
                String dosyaAdi = senaryo.getName().replaceAll("[^a-zA-Z0-9çğıöşüÇĞİÖŞÜ_-]+", "_") + ".png";
                Files.write(klasor.resolve(dosyaAdi), png);
            }
        } finally {
            baglam.kapat();
        }
    }
}
