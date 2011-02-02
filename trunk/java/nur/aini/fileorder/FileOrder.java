package nur.aini.fileorder;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Kelas FileOrder digunakan untuk mengurutkan daftar file.
 * Berdasarkan ide dari Ibu Nur Aini Rakhmawati.
 * Ide kode komparasi dikembangkan dari
 * <a href="http://stackoverflow.com/questions/203030/best-way-to-list-files-in-java-sorted-by-date-modified">sini</a>.
 * 
 * @author Muhammad Arif Romdhoni
 * @since Feb 02, 2011 11:42 PM
 * @license GPL v3
 */
public class FileOrder {

	/**
	 * Mengurutkan berdasarkan nama
	 * @param arrFiles Daftar file
	 */
	public static void byName(File[] arrFiles) {
		Arrays.sort(arrFiles, new Comparator<File>() {
			@Override
			public int compare(File f1, File f2) {
				return Integer.valueOf(f1.getName().compareTo(f2.getName()));
			}
		});
	}

	/**
	 * Mengurutkan berdasarkan absolute path
	 * @param arrFiles Daftar file
	 */
	public static void byAbsolutePath(File[] arrFiles) {
		Arrays.sort(arrFiles, new Comparator<File>() {
			@Override
			public int compare(File f1, File f2) {
				return Integer.valueOf(f1.getAbsolutePath().compareTo(f2.getAbsolutePath()));
			}
		});
	}

	/**
	 * Mengurutkan berdasarkan canonical path
	 * @deprecated bermasalah
	 * @param arrFiles Daftar file
	 */
	public static void byCanonicalPath(File[] arrFiles) {
		Arrays.sort(arrFiles, new Comparator<File>() {
			@Override
			public int compare(File f1, File f2) {
				try {
					return Integer.valueOf(f1.getCanonicalPath().compareTo(f2.getCanonicalPath()));
				} catch (IOException ex) {
					return 1;
				}
			}
		});
	}

	/**
	 * Mengurutkan berdasarkan path
	 * @param arrFiles Daftar file
	 */
	public static void byPath(File[] arrFiles) {
		Arrays.sort(arrFiles, new Comparator<File>() {
			@Override
			public int compare(File f1, File f2) {
				return Integer.valueOf(f1.getPath().compareTo(f2.getPath()));
			}
		});
	}

	/**
	 * Mengurutkan berdasarkan tanggal terakhir dimodifikasi
	 * @param arrFiles Daftar file
	 */
	public static void byLastModified(File[] arrFiles) {
		Arrays.sort(arrFiles, new Comparator<File>() {
			@Override
			public int compare(File f1, File f2) {
				return Long.valueOf(f1.lastModified()).compareTo(f2.lastModified());
			}
		});
	}

	/**
	 * Mengurutkan berdasarkan ukuran file
	 * @param arrFiles Daftar file
	 */
	public static void byLength(File[] arrFiles) {
		Arrays.sort(arrFiles, new Comparator<File>() {
			@Override
			public int compare(File f1, File f2) {
				return Long.valueOf(f1.length()).compareTo(f2.length());
			}
		});
	}

	/**
	 *
	 * @param args[0] Nama direktori
	 */
	public static void main(String[] args) {
		final File inputDir = new File(args[0]);
		File[] lists = inputDir.listFiles();
		System.out.println("---------------- Default Order");
		for (File f : lists) {
			System.out.println(f.getName());
		}
		System.out.println("---------------- By Name");
		FileOrder.byName(lists);
		for (File f : lists) {
			System.out.println(f.getName());
		}
		System.out.println("---------------- By Absolute Path");
		FileOrder.byAbsolutePath(lists);
		for (File f : lists) {
			System.out.println(f.getName() + ":" + f.getAbsolutePath());
		}
		/*
		System.out.println("---------------- By Canonical Path");
		FileOrder.byCanonicalPath(lists);
		for (File f : lists) {
			System.out.println(f.getName() + ":" + f.getCanonicalPath());
		}
		 */
		System.out.println("---------------- By Path");
		FileOrder.byPath(lists);
		for (File f : lists) {
			System.out.println(f.getName() + ":" + f.getPath());
		}
		System.out.println("---------------- By LastModified");
		FileOrder.byLastModified(lists);
		for (File f : lists) {
			System.out.println(f.getName() + ":" + f.lastModified());
		}
		System.out.println("---------------- By Length");
		FileOrder.byLength(lists);
		for (File f : lists) {
			System.out.println(f.getName() + ":" + f.length());
		}
	}
	
}