# language: tr
Özellik: Ürün listesi ve sıralama
  Alışveriş yapan kullanıcı olarak ürünleri ada veya fiyata göre sıralayabilmeliyim
  ki aradığım ürünü kolayca bulayım.

  Geçmiş:
    Diyelim ki "standard_user" kullanıcısıyla giriş yapmış durumdayım

  Senaryo: Ürün kataloğu eksiksiz listelenir
    O zaman listede 6 ürün görmeliyim ve her ürünün fiyatı sıfırdan büyük olmalı

  Senaryo taslağı: Ürünler seçilen ölçüte göre sıralanır
    Eğer ki ürünleri "<secenek>" seçeneğine göre sıralarsam
    O zaman ürünler "<olcut>" şeklinde sıralanmış olmalı
    Ve listedeki ilk ürün "<ilk_urun>" olmalı

    Örnekler:
      | secenek             | olcut              | ilk_urun                          |
      | Name (A to Z)       | ada göre artan     | Sauce Labs Backpack               |
      | Name (Z to A)       | ada göre azalan    | Test.allTheThings() T-Shirt (Red) |
      | Price (low to high) | fiyata göre artan  | Sauce Labs Onesie                 |
      | Price (high to low) | fiyata göre azalan | Sauce Labs Fleece Jacket          |
