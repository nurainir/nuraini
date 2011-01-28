package nur.aini.JavaLogger;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

/*
 * @author : Nur Aini Rakhmawati
 * @since 2011
 * @license : GPL
 */

public class MyLogger {

		private final static Logger logfile = Logger.getLogger("mylogger");

 public MyLogger(String lokasilogger) throws  IOException {
	 FileHandler fh = new FileHandler(lokasilogger, true);
     fh.setFormatter(new LogFormatter());
     logfile.addHandler(fh);
}

 public void run()
 {
	 logfile.info("program jalan");
	 logfile.warning("peringatan!");
	 logfile.severe("ada kesalahan");
 }

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		MyLogger log =new  MyLogger();
		log.run();
	}

}