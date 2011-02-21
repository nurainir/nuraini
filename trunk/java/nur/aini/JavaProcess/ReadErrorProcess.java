package nur.aini.JavaProcess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * Class untuk membaca error jika menjalankan JAR
 * jika terjadi error maka akan dimabil baris pertama error
 * @author Nur Aini Rakhmawati
 * @since 21 February 2011
 * @license GPl
 *
 */
public class ReadErrorProcess {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String command ="java -jar program.jar";
        Process process;
        int returnCode = 0;

        if(args.length>0)
        	command ="java -jar "+args[0];

        try {
			process = Runtime.getRuntime().exec(command);
			 returnCode = process.waitFor();
			 if(returnCode!=0) // terjadi error
				 System.out.print(readProcess(process));
		} catch (IOException e) {

			e.printStackTrace();
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

    /**
     * Reading buffer stdin error
     * @param p	process Id
     * @return	the value of error message
     */
	static String readProcess (final Process p) {
    	String message=null;
    	final BufferedReader in =new BufferedReader ( new InputStreamReader(p.getErrorStream()));
    	String line;
    	try {
			while((line = in.readLine()) != null)
			{
				message =line;
				if((line = in.readLine()) != null && line.startsWith("\tat"))
					break;
			}
				message =message.substring(message.indexOf(':')+2);
		} catch (IOException e) {
			message = e.getMessage();
		}
		return message;
    }
}
