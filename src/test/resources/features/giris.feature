# language: tr
Özellik: Giriş ve çıkış
  Mağaza kullanıcısı olarak geçerli demo hesabımla giriş yapabilmeli,
  hatalı bilgilerde ne yapmam gerektiğini söyleyen bir uyarı görmeli ve oturumumu kapatabilmeliyim.

  Senaryo: Standart kullanıcı başarıyla giriş yapar
    Diyelim ki giriş sayfasındayım
    Eğer ki "standard_user" kullanıcı adı ve "secret_sauce" şifresiyle giriş yaparsam
    O zaman ürünler sayfasını görmeliyim

  Senaryo taslağı: Hatalı veya eksik bilgiyle giriş reddedilir
    Diyelim ki giriş sayfasındayım
    Eğer ki "<kullanici>" kullanıcı adı ve "<sifre>" şifresiyle giriş yaparsam
    O zaman "<mesaj>" hata mesajını görmeliyim

    Örnekler:
      | kullanici       | sifre        | mesaj                                                                     |
      | locked_out_user | secret_sauce | Epic sadface: Sorry, this user has been locked out.                       |
      | standard_user   | yanlis_sifre | Epic sadface: Username and password do not match any user in this service |
      |                 | secret_sauce | Epic sadface: Username is required                                        |
      | standard_user   |              | Epic sadface: Password is required                                        |

  Senaryo: Kullanıcı menüden oturumu kapatır
    Diyelim ki "standard_user" kullanıcısıyla giriş yapmış durumdayım
    Eğer ki menüden çıkış yaparsam
    O zaman giriş sayfasına geri dönmeliyim
