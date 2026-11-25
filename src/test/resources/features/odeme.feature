# language: tr
Özellik: Ödeme (checkout)
  Müşteri olarak teslimat bilgilerimi girip siparişimi tamamlayabilmeli,
  eksik bilgi girersem uyarılmalı ve özet sayfasında tutarları doğru görmeliyim.

  Geçmiş:
    Diyelim ki "standard_user" kullanıcısıyla giriş yapmış durumdayım
    Ve sepete "Sauce Labs Backpack" ürününü ekledim
    Ve sepete "Sauce Labs Bike Light" ürününü ekledim
    Ve sepetten ödeme adımına geçtim

  Senaryo: Sipariş baştan sona başarıyla tamamlanır
    Eğer ki teslimat bilgilerini "Ayşe", "Yılmaz", "54050" olarak girersem
    Ve siparişi tamamlarsam
    O zaman "Thank you for your order!" onay mesajını görmeliyim
    Ve sepet boşalmış olmalı

  Senaryo: Özet sayfasındaki tutarlar tutarlıdır
    Eğer ki teslimat bilgilerini "Mehmet", "Demir", "34000" olarak girersem
    O zaman özet sayfasındaki ara toplam ürün fiyatlarının toplamına eşit olmalı
    Ve ara toplam "$39.98" ve genel toplam ara toplam ile verginin toplamı olmalı

  Senaryo taslağı: Eksik teslimat bilgisiyle devam edilemez
    Eğer ki teslimat bilgilerini "<ad>", "<soyad>", "<posta_kodu>" olarak girersem
    O zaman ödeme formunda "<uyari>" uyarısını görmeliyim

    Örnekler:
      | ad   | soyad  | posta_kodu | uyari                          |
      |      | Yılmaz | 54050      | Error: First Name is required  |
      | Ayşe |        | 54050      | Error: Last Name is required   |
      | Ayşe | Yılmaz |            | Error: Postal Code is required |
