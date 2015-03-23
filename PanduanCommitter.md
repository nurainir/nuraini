# Checkout SVN #
svn checkout https://nuraini.googlecode.com/svn/trunk/ nuraini --username alamat@gmail.com
atau lihat [disini](http://code.google.com/p/nuraini/source/checkout)

# Struktur Direktori #
  * Direktori disusun berdasarkan bahasa pemrograman
  * Setiap direktori mempunyai file daftar.txt yang berisi daftar program yang ada.**daftar.txt** ini **harus** diupdate jika anda menambah program baru.


# Commit #
Gunakanlah komentar commit yang sering dipakai oleh developer dunia.
  * Menambah
> > contoh : + added program xxx
  * Menghapus
> > contoh : - deleted program xxx
  * Mengubah
> > contoh : #fixed program xxx
Anda pun juga dapat memberikan ucapan terima kasih saat commit
> > contoh : # fixed program, thanks to Mama

# Penjelasan Program #
Penjelasan program ini **wajib** hukumnya. Mulai dari author, lisensi, library yang diperlukan, cara mengeksekusi program dll
contoh :
```
/*
 * Program untuk melakukan testing apakah HBASE running
 * @author : Nur Aini Rakhmawati
 * @since 1 Febuary 2011
 * @license : GPL
 */
```
```
/**
	 * @param args[0] lokasi hbase-default.xml
	 * args[1] lokasi hbase-site.xml
	 * usage : TestHbase hbase-default.xml hbase-site.xml
	 * requirement : hadoop-versi-core.jar, hbase-versi.jar, zookeeper-versi.jar
	 */
```
# Larangan #
  * Jangan melakukan commit program orang lain dengan menggati nama pembuatnya (author)