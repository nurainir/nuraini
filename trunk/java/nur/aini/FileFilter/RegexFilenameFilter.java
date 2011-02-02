package nur.aini.FileFilter;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

/**
 * Program untuk menyaring file berdasar regular expression.
 * Ide dari ibu Nur Aini Rakhmawati dengan sedikit perubahan dari kelas
 * <code>ExtFilenameFilter</code>
 * @author Muhammad Arif Romdhoni
 * @since Feb 02, 2011 10:49 PM
 * @license GPL v3
 */
public class RegexFilenameFilter implements FilenameFilter {

	/**
	 * Regular expression yang digunakan
	 */
	private final String regex;

	/**
	 * Apakah case sensitive atau tidak
	 */
	private final boolean caseSensitive;

	/**
	 * Konstruktor di mana regex yang digunakan dianggap <i>case-insensitive</i>
	 * @param regex Regular expression yang digunakan
	 */
	public RegexFilenameFilter(final String regex) {
		this.caseSensitive = false;
		this.regex = regex.toLowerCase();
	}

	/**
	 * Konstruktor
	 * @param regex Regular expression yang digunakan
	 * @param isCaseSensitive Apakah case sensitive atau tidak
	 */
	public RegexFilenameFilter(final String regex, final boolean isCaseSensitive) {
		this.caseSensitive = isCaseSensitive;
		this.regex = isCaseSensitive ? regex : regex.toLowerCase();
	}

	/**
	 * Override dari fungsi di FilenameFilter, merupakan fungsi utama dari filter
	 * ini.
	 * @param dir Direktori
	 * @param name Nama file
	 * @return Bernilai TRUE iff name memiliki pattern yang diharapkan
	 *
	 */
	@Override
	public boolean accept(File dir, String name) {
		return (new File(dir.getAbsolutePath() + File.separator + name)).isFile()
						&& (this.caseSensitive
						? Pattern.matches(this.regex, name)
						: Pattern.matches(this.regex, name.toLowerCase()));
	}

	/**
	 * @param args[0] : lokasi direktori
	 */
	public static void main(String[] args) {
		final File inputDir = new File(args[0]);
		if (inputDir.isDirectory()) {
			// mencari file berekstensi pdf secara case-insensitive
			System.out.println("Pencarian daftar file dengan pattern [a-zA-Z]*.pdf secara case-insensitive:");
			File[] inputFiles = inputDir.listFiles(new RegexFilenameFilter("[a-zA-Z]*.pdf"));
			for (File f : inputFiles) { System.out.println(f.getName()); }
			System.out.println();
			// mencari file berekstensi pdf secara case sensitive
			System.out.println("Pencarian daftar file dengan pattern [a-zA-Z]*.pdf secara case-sensitive:");
			inputFiles = inputDir.listFiles(new RegexFilenameFilter("[a-zA-Z]*.pdf", true));
			for (File f : inputFiles) { System.out.println(f.getName()); }
		} else {
			System.err.println("Direktori " + args[0] + " tidak ditemukan!");
		}
	}
}
