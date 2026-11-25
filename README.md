# BIL 002 — Kısım 2: Selenium + Cucumber ile Web Otomasyonu

[![UI Testleri](https://github.com/Sadikcanceli/bil002-selenium-cucumber-24010903038/actions/workflows/ui-testleri.yml/badge.svg)](https://github.com/Sadikcanceli/bil002-selenium-cucumber-24010903038/actions/workflows/ui-testleri.yml)

Sakarya Uygulamalı Bilimler Üniversitesi · BIL 002 Yazılım Doğrulama ve Sınama dönem projesi, Kısım 2.
Kısım 1 (BankAccount API testleri): https://github.com/Sadikcanceli/bil002-bankaccount-24010903038

## Test edilen uygulama: SauceDemo

**https://www.saucedemo.com** — Sauce Labs'in test otomasyonu eğitimi için yayınladığı demo e-ticaret sitesi.

**Neden seçildi?**
- Otomasyon için tasarlanmış, herkese açık ve kararlı bir site; CAPTCHA, 2FA veya gerçek ödeme yok.
- Giriş → ürün listesi → sepet → ödeme → onay şeklinde **birden çok sayfaya yayılan** gerçekçi bir uçtan uca akış sunuyor.
- Elemanlarda `data-test` öznitelikleri olduğu için kararlı locator'lar yazılabiliyor.
- Yalnızca sitenin sayfada yayınladığı demo hesapları (`standard_user`, `locked_out_user` / `secret_sauce`) kullanıldı.

## Test edilen akışlar (4 sayfa / işlev)

| Feature | Sayfa(lar) | Ne test ediliyor |
|---|---|---|
| `giris.feature` | Giriş, Ürünler | Başarılı giriş, kilitli kullanıcı / yanlış şifre / boş alan uyarıları, çıkış |
| `urunler.feature` | Ürünler | Katalogdaki 6 ürün ve fiyatları, 4 farklı sıralama seçeneği |
| `sepet.feature` | Ürünler, Sepet | Sepete ekleme/çıkarma, rozet sayısı, sepet içeriği |
| `odeme.feature` | Sepet, Ödeme bilgileri, Özet, Onay | Uçtan uca sipariş, eksik bilgi uyarıları, ara toplam + vergi = toplam hesabı |

Toplam **4 feature, 19 senaryo** (3 Scenario Outline / `Senaryo taslağı`, 11 örnek satırı). Senaryolar
`# language: tr` ile Türkçe Gherkin olarak yazıldı.

## Teknolojiler

Java 17 · Maven · Selenium WebDriver 4.27 (sürücüler Selenium Manager ile otomatik) · Cucumber-JVM 7.18
(`cucumber-java`, `cucumber-junit-platform-engine`, `cucumber-picocontainer`) · JUnit 5 Platform Suite

## Çalıştırma

```bash
mvn test                                   # Chrome, headless (varsayılan)
mvn test -Dtarayici=edge                   # Microsoft Edge, headless
mvn test -Dtarayici=firefox                # Firefox, headless
mvn test -Dheadless=false                  # tarayıcıyı görerek çalıştır
```

- Chrome/Edge/Firefox'tan biri kurulu olmalıdır; sürücüyü indirmeye gerek yoktur.
- HTML raporu: `target/cucumber-report.html` (son koşunun kopyası: [`docs/rapor/cucumber-report.html`](docs/rapor/cucumber-report.html))
- Başarısız senaryoların ekran görüntüleri: `target/ekran-goruntuleri/` ve HTML raporun içinde.
- GitHub Actions her push'ta senaryoları headless Chrome ile çalıştırır ve raporu artifact olarak yükler.

## Proje yapısı

```
src/test/java/tr/edu/sakarya/bil002/ui/
├── pages/        Page Object sınıfları (locator + eylem + bekleme yalnızca burada)
│   ├── BasePage.java              ortak WebDriverWait yardımcıları, üst menü, sepet rozeti
│   ├── LoginPage.java             giriş formu ve hata mesajı
│   ├── InventoryPage.java         ürün listesi, sıralama, sepete ekle/çıkar
│   ├── CartPage.java              sepet içeriği, ödemeye geçiş
│   ├── CheckoutInfoPage.java      teslimat bilgileri formu ve uyarıları
│   ├── CheckoutOverviewPage.java  sipariş özeti: ara toplam, vergi, toplam
│   └── CheckoutCompletePage.java  onay mesajı
├── stepdefs/     Gherkin adım tanımları (yalnızca page metotlarını çağırır) + Hooks
├── runner/       CucumberTestRunner (@Suite + @SelectClasspathResource("features"))
└── support/      DriverFactory (tarayıcı/headless seçimi), TestContext (PicoContainer ile paylaşım)
src/test/resources/features/   giris, urunler, sepet, odeme (.feature)
```

## Tasarım kararları

- **Page Object Model:** Adım tanımlarında hiç `By`/`findElement` yoktur; her sayfanın locator'ları ve
  eylemleri kendi sınıfındadır.
- **Açık bekleme:** Örtük bekleme kapalıdır (`implicitlyWait(0)`); tüm beklemeler `WebDriverWait` +
  `ExpectedConditions` (veya lambda koşulları) ile yapılır. `Thread.sleep` kullanılmamıştır.
- **Bağımsız senaryolar:** Her senaryo kendi tarayıcısını açar ve kapatır (`Hooks`); senaryolar birbirinin
  durumuna bağlı değildir.
- **Saygılı otomasyon:** Siteye yük bindirilmez; her senaryo birkaç sayfa ziyareti yapar.
