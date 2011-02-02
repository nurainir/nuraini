<?php

/**
 *
 * @author Muhammad Arif Romdhoni
 * @license GPL v3
 * @since Feb 2, 2011 09:07 PM
 *
 * Kelas FilenameSearch memiliki fungsi statik untuk mencari file dalam sebuah
 * direktori tertentu. Pencarian dapat dilakukan berdasarkan ekstensi atau
 * bagian dari nama file. Pencarian dapat pula dilakukan secara rekursif ke
 * dalam sub-sub direktori dari parameter direktori input.
 *
 * Contoh penggunaan:
 * <code>
 * // Mencari daftar file yang memiliki ekstensi php (case insensitive) dari
 * // direktori file ini secara rekursif
 * $arrFilesPhp = FilenameSearch::byExtension(dirname(__FILE__), "php", true);
 *
 * // Mencari daftar file yang memiliki bagian nama index (case sensitive) dari
 * // direktori file ini secara rekursif
 * $arrFilesIndex = FilenameSearch::byName(dirname(__FILE__), "index", true, true);
 * </code>
 */
class FilenameSearch {

	/**
	 * Mencari daftar file dalam sebuah folder tertentu menggunakan filter ekstensi file.
	 * @param string $strDirectory Folder untuk melakukan pencarian.
	 * @param string $strExtension Ekstensi file yang dicari (<i>case insensitive</i>).
	 * @param boolean $boolRecursive Menyatakan apakah dilakukan pencarian secara
	 * rekursif hingga ke dalam sub-sub direktori (deep search).
	 * @return array Daftar file hasil pencarian.
	 */
	public static function byExtension($strDirectory, $strExtension, $boolRecursive = false) {
		$arrFiles = array();
		$strDirectory = (substr($strDirectory, -1) == DIRECTORY_SEPARATOR ? $strDirectory: $strDirectory . DIRECTORY_SEPARATOR);
		$strExt = (substr($strExtension, 0, 1) == '.' ? $strExtension : ".{$strExtension}");
		$intLength = strlen($strExt);
		if (($objHandler = opendir($strDirectory)) !== false) {
			while(($strFilename = readdir($objHandler)) !== false) {
				$strLongFilename = $strDirectory . $strFilename;
				if (($strFilename == ".") || ($strFilename == "..")) {
					// nothing to do
				}
				else if ($boolRecursive && is_dir($strLongFilename)) {
					$arrTmpFiles = FilenameSearch::byExtension($strLongFilename . DIRECTORY_SEPARATOR, $strExtension, $boolRecursive);
					foreach($arrTmpFiles as $strLongFilename) {
						$arrFiles[] = $strLongFilename;
					}
					unset($arrTmpFiles);
				}
				else if (is_file($strLongFilename) && (substr($strFilename, -$intLength) == $strExt)) {
					$arrFiles[] = $strLongFilename;
				}
			}
			closedir($objHandler);
		}
		return $arrFiles;
	}

	/**
	 * Mencari daftar file dalam sebuah folder tertentu menggunakan filter nama file.
	 * @param string $strDirectory Folder untuk melakukan pencarian.
	 * @param string $strText Bagian nama file yang dicari.
	 * @param boolean $boolCaseSensitive Menyatakan apakah pencarian menggunakan
	 * <i>case-sensitive</i> (persis sama besar-kecilnya) atau tidak.
	 * @param boolean $boolRecursive Menyatakan apakah dilakukan pencarian secara
	 * rekursif hingga ke dalam sub-sub direktori (deep search).
	 * @return array Daftar file hasil pencarian.
	 */
	public static function byName($strDirectory, $strText, $boolCaseSensitive = true, $boolRecursive = false) {
		$arrFiles = array();
		if (!$boolCaseSensitive) $strText = strtolower ($strText);
		if (substr($strDirectory, -1) != DIRECTORY_SEPARATOR) $strDirectory .= DIRECTORY_SEPARATOR;
		$strExt = (substr($strText, 0, 1) == '.' ? $strText : ".{$strText}");
		$intLength = strlen($strExt);
		if (($objHandler = opendir($strDirectory)) !== false) {
			while(($strFilename = readdir($objHandler)) !== false) {
				$strLongFilename = $strDirectory . $strFilename;
				if (($strFilename == ".") || ($strFilename == "..")) {
					// nothing to do
				}
				else if ($boolRecursive && is_dir($strLongFilename)) {
					$arrTmpFiles = FilenameSearch::byName($strLongFilename . DIRECTORY_SEPARATOR, $strText, $boolCaseSensitive, $boolRecursive);
					foreach($arrTmpFiles as $strLongFilename) {
						$arrFiles[] = $strLongFilename;
					}
					unset($arrTmpFiles);
				}
				else if (is_file($strLongFilename)) {
					// $arrFiles[] = $strLongFilename;
					if (!$boolCaseSensitive) {
						$strFilename = strtolower($strFilename);
					}
					if (strpos($strFilename, $strText) !== false) {
						$arrFiles[] = $strLongFilename;
					}
				}
			}
			closedir($objHandler);
		}
		return $arrFiles;
	}

}
