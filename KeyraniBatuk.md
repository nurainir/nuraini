# Pendahuluan #

KeryaniBatuk adalah program twitter klien berbasis perl agar mempermudah kerja admin twitter dalam mengupdate status. Mengapa namanya **KeyraniBatuk** ? karena program ini dibuat saat Keyrani, anak saya sedang sakit batuk :) Batuk disini berarti batuk-batuk mengeluarkan status ditwitter sehingga follower anda terjangkit virus :)

# Fitur #
  1. **KBsendiri** Melakukan serangkain twit dalam jeda waktu yang ditentukan
  1. **KBrss** Mengambil berita berbagai media via RSS, melakuakn filter berdasarkan regex dan melakukan twit berita tersebut

# Download #
silahkan download disini http://code.google.com/p/nuraini/downloads/detail?name=KeyraniBatuk-1.0.zip

# Kebutuhan #
  1. Mempunyai akun aplikasi di http://dev.twitter.com agar mendapatkan
    * consumer\_key
    * consumer\_secret
    * access\_token
    * access\_token\_secret
  1. Install modul perl untuk rss dan twitter
    * apt-get install libnet-twitter-lite-perl
    * apt-get install libxml-rsslite-perl

# Penggunaan #
  1. Simpan seluruh consumer\_key, consumer\_secret, access\_token access\_token\_secret dalam sebuah file dengan contoh sbb :
```
consumer_key=AAA
consumer_secret=BBB
access_token=CCC
access_token_secret=DDD
```
  1. **KBsendiri** membutuhkan data twit yang harus disimpan dalam sebuah file. Satu baris - satu twit. Contoh :
```
twitsatu
twitdua
```
  1. **KBrss** membutuhkan data rss yang juga disimpan dalam file dengan format :
`[tag] urlRSS statusterakhir filter jedawaktu`
> keterangan
    * data tsb dipisahkan dengan tabulasi atau \t.
    * Kita bisa memasukkan berapapun data rss.
    * `[tag]` merupakan tag yang kita pilih saat update status misal `[berita]`, `[humor]`. Jika anda tidak menggunakannya cukup beri tanda minus (**-**)
    * urlRSS merupakan url RSS media yang andapilih
    * statusterakhir : merupakan judul berita terakhir terupdate, jika anda tidak tahu tulis saja sembarang.
    * filter adalah perintah regex jika anda tidak melakukan filter cukup tulis `.*` , misal anda ingin hanya linux atau blankon yang masuk maka cukup ketikkan `linux|blankon`
    * jedawaktu : adalah angka berapa detik jeda antar twit. Jika 10 berarti jeda waktu 10 detik.
contoh :
```
-	http://us.detikinet.com/index.php/detik.feed/	Siapa Paling Bersalah dalam Kasus Pencurian Pulsa?	blankon|android|linux|open source|oss|ubuntu	10
[kompas]	http://www.kompas.com/getrss/tekno	Inilah 11 Prediksi Tren Teknologi 2012	blankon|android|linux|open source|oss|ubuntu	30
-	http://www.republika.co.id/rss	Palestina Lanjutkan Upaya Jadi Anggota Penuh PBB	.*	20
```
> # Cara menjalankan #
    * ekstrak KeyraniBatuk-1.0.zip
    * siapkan data twitter key yang ada diatas, data twit atau data rss.
    * jalankan dengan command line :
      * perl KBrss.pl lokasifiletwitterkey lokasidatarss
      * perl KBsendiri.pl lokasifiletwitterkey lokasidatatwit