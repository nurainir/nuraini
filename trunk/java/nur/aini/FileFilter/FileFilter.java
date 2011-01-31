package nur.aini;

import java.io.File;
import java.io.FilenameFilter;


/*
 * Program untuk menyaring file berdasar ekstensi dalam satu direktori
 * @author : Nur Aini Rakhmawati
 * @since 31 January 2011
 * @license : GPL
 */

public class FileFilter implements FilenameFilter {

	private String suffix;

	public FileFilter(final String suffix)
	{
		this.suffix=suffix;
	}

	@Override
	public boolean accept(final File dir, final String name) {
      	if (name.endsWith('.'+this.suffix))
        	return true;
      	else
      		return false;
    	}

	/**
	 * @param args[0] : lokasi direktori
	 */

	public static void main(String[] args) {
		final File[] inputFiles;
		final File inputDir = new File (args[0]);
		//mencari file berekstensi pdf
		 inputFiles = inputDir.listFiles(new RankFileFilter("pdf"));
		for (File f : inputFiles)
			System.out.println(f.getName());

	}

}
