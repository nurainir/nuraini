package nur.aini.hadoop;
/**
 * Class untuk melakukan penggabungan beberapa file dalam satu direktori dengan format regex tertentu
 * @author Nur Aini Rakhmawati
 * @since 13 February 2011
 * @license GPL
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.io.IOUtils;

public class CopyMergeRegexToLocal {

	private static FileSystem hdfs = null;
	private final  	Configuration conf=new Configuration();

	public CopyMergeRegexToLocal(String hadoopPath)
	{

		  if (hdfs == null) {
	            try {

	            	conf.addResource(new Path(hadoopPath+"/conf/core-site.xml"));
	            	conf.addResource(new Path(hadoopPath+"/conf/hdfs-site.xml"));
	                hdfs = FileSystem.get(conf);
	            } catch (IOException ex) {
	                System.err.print(ex.getMessage());
	            }
	        }
	}

	 public void run(String srcf, String dst)  {

		 final Path srcPath = new Path("./"+srcf);
		 final Path desPath = new Path(dst);
		 try {
		 Path [] srcs = FileUtil.stat2Paths(hdfs.globStatus(srcPath), srcPath);
		 OutputStream out = FileSystem.getLocal(conf).create(desPath);
	      for( int i=0; i<srcs.length; i++ ) {
	    	  System.out.println(srcs[i]);
	    	  InputStream in = hdfs.open(srcs[i]);

	              IOUtils.copyBytes(in, out, conf, false);
	              in.close();

	      }
	    out.close();

		 }catch (IOException ex) {
			 System.err.print(ex.getMessage());
        }
	    }


	public static void main(String[] args) {
		CopyMergeRegexToLocal cp = new CopyMergeRegexToLocal("/home/iin/hadoop-20.2/");
		cp.run("dirtxt/*.txt","/home/iin/kumpulantxt");

	}

}
