package nur.aini.hadoop.hbase;
/*
 * Program untuk melakukan testing apakah HBASE running
 * @author : Nur Aini Rakhmawati
 * @since 1 Febuary 2011
 * @license : GPL
 */

import org.apache.hadoop.hbase.client.HConnection;
import org.apache.hadoop.hbase.client.HConnectionManager;
import org.apache.hadoop.hbase.HBaseConfiguration;

public class TestHbase {

	/**
	 * @param args[0] lokasi hbase-default.xml
	 * args[1] lokasi hbase-site.xml
	 * usage : TestHbase hbase-default.xml hbase-site.xml
	 * requirement : hadoop-versi-core.jar, hbase-versi.jar, zookeeper-versi.jar
	 */
	public static void main(String[] args) {

		final HBaseConfiguration conf = new HBaseConfiguration();
		// lokasi hbase-default.xml
		conf.addResource(args[0]);
		// lokasi hbase-site.xml
		conf.addResource(args[1]);
		final HConnection connection = HConnectionManager.getConnection(conf);

	    if (connection.isMasterRunning())
	    	System.out.print("running");
	    else
	    	System.out.print("down");
	}

}
