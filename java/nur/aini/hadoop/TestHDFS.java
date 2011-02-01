package nur.aini.hadoop;

/*
 * Program untuk melakukan testing apakah HDFS running, sekaligus contoh membuat dan menghapus file
 * @author : Nur Aini Rakhmawati
 * @since 1 Febuary 2011
 * @license : GPL
 */

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.Configuration;
import java.io.IOException;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;

public class TestHDFS{

    private static FileSystem hdfs = null;


    public  static FileSystem getHdfs(String hadooppath) {
        if (hdfs == null) {
            try {
            	Configuration conf=new Configuration();
            	conf.addResource(new Path(hadooppath+"/conf/core-site.xml"));
                hdfs = FileSystem.get(conf);
            } catch (IOException ex) {
                System.err.print(ex.getMessage());
            }
        }
        return hdfs;
    }

    public static boolean exists(String path) {
        try {
            return hdfs.exists(new Path(path));
        } catch (IOException ex) {
        	 System.err.print(ex.getMessage());
        }
        return false;
    }

	/**
	 * @param args[0] nama file
	 * usage : TestHadoop namafile
	 * requirement : hadoop-versi-core.jar
	 */

    public static void main(String[] args) throws IOException {

        Path path = new Path("/user/"+System.getProperty("user.name")+'/'+args[1]);
        FileSystem HDFS = TestHDFS.getHdfs(args[0]);
        boolean hadoop = false;
        if(!TestHDFS.exists(path.toString()))
        {
	        FSDataOutputStream dos = HDFS.create(path);
	        dos.writeUTF("hadoop");
	        dos.close();
        }

        if(TestHDFS.exists(path.toString()))
        {
        	FSDataInputStream dis = HDFS.open(path);
        	if(dis.readUTF().equalsIgnoreCase("hadoop"))
        		hadoop=true;
        	dis.close();
        	HDFS.delete(path);
        }

        if(hadoop)
        	System.out.print("OK");
        else
        	System.out.print("ERR");

    }
}
