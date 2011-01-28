
package nur.aini.JavaLogger;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

/**
 * Mengambil Logger dengan masukan nama file yang hendak dijadikan log.
 * Perbaikan dari kelas MyLogger. Di sini kembalian berupa <i>instance</i> dari
 * kelas <code>java.util.logging.Logger</code>.
 *
 * @author Muhammad Arif Romdhoni
 * @since Jan 29, 2011 04:35 AM (GMT+7)
 * @license GPL
 */
public class LogGetter {

	/**
	 * Hashmap yang digunakan untuk menyimpan logger yang sudah didefinisikan.
	 */
	private final static HashMap<String, Logger> logs = new HashMap<String, Logger>();

	/**
	 * @constuctor LogGetter private karena tidak dibutuhkan
	 */
	private LogGetter() {}

	/**
	 * Mengembalikan Logger yang akan melakukan logging pada file yang telah
	 * ditentukan.
	 * 
	 * @param lokasiLog Lokasi file penulisan log
	 * @return Logger yang digunakan untuk menulis log
	 * @throws IOException Apabila file handler tidak bisa digunakan.
	 */
	public static synchronized Logger get(String lokasiLog) throws IOException {
		synchronized(logs) {
			if (!logs.containsKey(lokasiLog)) {
				Logger newLogger = Logger.getLogger(lokasiLog);
				FileHandler fileHandler = new FileHandler(lokasiLog, true);
				fileHandler.setFormatter(new LogFormatter());
				newLogger.addHandler(fileHandler);
				logs.put(lokasiLog, newLogger);
			}
			return logs.get(lokasiLog);
		}
	}

	/**
	 * Contoh penggunaan.
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		Logger log1 = LogGetter.get("log1.log");
		log1.info("Program Jalan!");
		log1.warning("Peringatan!");
		log1.severe("Ada Kesalahan!");
		Logger log2 = LogGetter.get("log1.log");
		log2.info("LOG2: Program Jalan!");
		log2.warning("LOG2: Peringatan!");
		log2.severe("LOG2: Ada Kesalahan!");
	}

}
