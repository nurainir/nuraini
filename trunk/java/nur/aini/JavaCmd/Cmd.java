package nur.aini.JavaCmd;

/**
 * @author : Nur Aini Rakhmawati
 * @since 2011
 * @license : GPL
 */
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import joptsimple.OptionException;

public class Cmd {

	private final String HELP = "help";
	private final String SATU = "s";
	private final String DUA = "dua";
	private final OptionParser parser;

	public Cmd() {
		parser = new OptionParser();
		parser.accepts(HELP, "print usage information");
		parser.accepts(SATU, "[OPTIONAL] opsi satu").withRequiredArg().ofType(Integer.class);
		parser.accepts(DUA, "[REQUIRED] opsi dua").withRequiredArg().ofType(String.class);
	}

	public void run(final String[] opsi) {

		final OptionSet options = parser.parse(opsi);
		//cetak help
		if (options.has(HELP)) {
			parser.printHelpOn(System.out);
			System.exit(0);
		}
		// proses opsi DUA
		if (!options.has(DUA))  {
			System.err.println("Error opsi dua harus ada");
			parser.printHelpOn(System.out);
			System.exit(0);
		}
		//proses opsi SATU
		if (options.has(SATU)) {
			System.out.println("Opsi satu bernilai " + (Integer) options.valueOf(SATU));
			System.exit(0);
		}
	}

	/*
	 * Running aplikasi
	 * contoh :
	 * Cmd -s 10 --dua nuraini
	 * Cmd -s 100
	 * Cmd --help
	 */
	public static void main(String[] args) throws Exception {
		final Cmd cmd = new Cmd();
		cmd.run(args);
	}
