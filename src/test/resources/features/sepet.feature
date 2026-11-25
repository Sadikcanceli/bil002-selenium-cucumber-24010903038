# language: tr
Özellik: Alışveriş sepeti
  Kullanıcı olarak ürünleri sepete ekleyip çıkarabilmeli ve sepet rozetinde
  kaç ürün olduğunu her an görebilmeliyim.

  Geçmiş:
    Diyelim ki "standard_user" kullanıcısıyla giriş yapmış durumdayım

  Senaryo: Sepete eklenen her ürün rozeti artırır
    Eğer ki sepete "Sauce Labs Backpack" ürününü eklersem
    Ve sepete "Sauce Labs Bike Light" ürününü eklersem
    O zaman sepet rozetinde 2 görünmeli
    Ve "Sauce Labs Backpack" ürününün butonu "Remove" olmalı

  Senaryo: Ürün sepetten çıkarılınca rozet azalır
    Diyelim ki sepete "Sauce Labs Onesie" ürününü ekledim
    Ve sepete "Sauce Labs Fleece Jacket" ürününü ekledim
    Eğer ki "Sauce Labs Onesie" ürününü ürünler sayfasından sepetten çıkarırsam
    O zaman sepet rozetinde 1 görünmeli
    Ve "Sauce Labs Onesie" ürününün butonu "Add to cart" olmalı

  Senaryo: Sepet sayfası yalnızca eklenen ürünleri listeler
    Diyelim ki sepete "Sauce Labs Bolt T-Shirt" ürününü ekledim
    Ve sepete "Test.allTheThings() T-Shirt (Red)" ürününü ekledim
    Eğer ki sepet sayfasına gidersem
    O zaman sepette şu ürünler listelenmeli:
      | Sauce Labs Bolt T-Shirt           |
      | Test.allTheThings() T-Shirt (Red) |
