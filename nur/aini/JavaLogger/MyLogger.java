package nur.aini;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

/*
 * @author : Nur Aini Rakhmawati
 * @since 2011
 * @license : GPL
 */

public class MyLogger {

		private static Logger logfile;

 public MyLogger() throws  IOException {
	 FileHandler fh = new FileHandler("log", true);
     fh.setFormatter(new LogFormatter());
     logfile = Logger.getLogger("mylogger");
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