package nur.aini.FileFilter;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Program untuk menyaring file berdasar ekstensi
 * @author Nur Aini Rakhmawati
 * @author Muhammad Arif Romdhoni
 * @since 31 January 2011
 * @license : GPL
 */
public class ExtFilenameFilter implements FilenameFilter {

	/**
	 * Ekstensi
	 */
	private String suffix;
	/**
	 * Menyatakan apakah pencarian berdasarkan ekstensi berlaku
	 * <i>case-sensitive</i> atau tidak
	 */
	private boolean caseSensitive;

	/**
	 * Konstruktor, di mana ekstensi yang digunakan dianggap
	 * <i>case-insensitive</i> (misalkan: "pdf" dianggap sama dengan
	 * "PDF" atau "Pdf")
	 * @param suffix Ekstensi
	 */
	public ExtFilenameFilter(final String suffix) {
		this.caseSensitive = false;
		this.suffix = suffix.toLowerCase();
	}

	/**
	 * Konstruktor
	 * @param suffix Ekstensi
	 * @param isCaseSensitive Menyatakan apakah <i>case-sensitive</i> atau tidak
	 */
	public ExtFilenameFilter(final String suffix, final boolean isCaseSensitive) {
		this.caseSensitive = isCaseSensitive;
		this.suffix = isCaseSensitive ? suffix : suffix.toLowerCase();
	}

	/**
	 * Override dari fungsi di FilenameFilter, merupakan fungsi utama dari filter
	 * ini.
	 * @param dir Direktori
	 * @param name Nama file
	 * @return Bernilai TRUE iff name memiliki ekstensi yang diharapkan
	 * 
	 */
	@Override
	public boolean accept(final File dir, final String name) {
		return (new File(dir.getAbsolutePath() + File.separator + name)).isFile()
						&& (this.caseSensitive
						? name.endsWith('.' + this.suffix)
						: name.toLowerCase().endsWith('.' + this.suffix));
	}

	/**
	 * @param args[0] : lokasi direktori
	 */

	public static void main(String[] args) {
		final File inputDir = new File(args[0]);
		if (inputDir.isDirectory()) {
			// mencari file berekstensi pdf secara case-insensitive
			System.out.println("Pencarian daftar file berekstensi pdf secara case-insensitive:");
			File[] inputFiles = inputDir.listFiles(new ExtFilenameFilter("pdf"));
			for (File f : inputFiles) { System.out.println(f.getName()); }
			System.out.println();
			// mencari file berekstensi pdf secara case sensitive
			System.out.println("Pencarian daftar file berekstensi pdf secara case-sensitive:");
			inputFiles = inputDir.listFiles(new ExtFilenameFilter("pdf", true));
			for (File f : inputFiles) { System.out.println(f.getName()); }
		} else {
			System.err.println("Direktori " + args[0] + " tidak ditemukan!");
		}

	}
}
