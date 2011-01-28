<?php

/**
 * @author Muhammad Arif Romdhoni
 * @license GPL v3
 * @since Jan 29, 2011 02:19 AM (GMT+7)
 *
 * Kelas UserPassword memiliki fungsi pembuatan <i>salt</i> (garam) dan password
 * secara sederhana dan menggunakan <i>library</i> yang ada di PHP. Untuk menggunakan
 * kelas ini (khususnya fungsi getPassword), dibutuhkan PHP5 >= 5.1.2. Untuk keterangan
 * lebih lanjut mengenai fungsi hash yang digunakan di kelas ini, dapat dibaca di
 * manual PHP http://php.net/manual/en/function.hash.php
 * 
 * Contoh penggunaan:
 * <code>
 * $username = "MyUsername";
 * $password = "MyPassword";
 *
 * $salt = UserPassword::genSalt(25);
 * $cipher = UserPassword::getPassword($password, $salt, "sha512");
 *
 * echo "password: {$password}\n"	. "salt: {$salt}\n"	. "cipher: {$cipher}\n";
 * </code>
 */
class UserPassword {

	/**
	 * Memperoleh salt secara acak.
	 * @param int $length Panjang salt yang diinginkan
	 * @return string Salt yang diperoleh
	 */
	public static function genSalt($length=20) {
		// let salt is just contained by letter and number
		$tmp = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ01234567890";
		$salt = ""; $max = strlen($tmp) - 1;
		for ($i=0; $i<$length; $i++) { $salt .= $tmp[rand(0,$max)]; }
		return $salt;
	}

	/**
	 * Memperoleh chipertext dari sebuah password plaintext dan salt tertentu
	 * dengan menggunakan algoritma tertentu yang telah terdefinisikan oleh PHP.
	 * Untuk saat ini, algoritma yang sering digunakan adalah md5, sha1, sha256,
	 * atau sha512.
	 *
	 * Untuk algoritma yang sudah didefinisikan PHP dapat dilihat dengan fungsi
	 * <code>hash_algos</code>. Keterangan lebih lanjut, baca di manual PHP
	 * http://www.php.net/manual/en/function.hash-algos.php
	 *
	 * @param string $password Password yang hendak diambil hashnya (Plaintext).
	 * @param string $salt Salt yang digunakan.
	 * @param string $algo Algoritma yang digunakan untuk hash.
	 * @return string Password yang telah dihash (Ciphertext).
	 */
	public static function getPassword($password, $salt, $algo="sha512") {
		return hash($algo, $password.$salt);
	}

}
