package connection;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

	private static String driverName = "org.apache.hive.jdbc.HiveDriver";

	public static Connection getConnection() throws SQLException {
		try {    	

			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
		Connection con = null;
		con = DriverManager.getConnection("jdbc:hive2://bivm.ibm.com:10000/tweetingdatabase","biadmin","biadmin");

		return con;
	}
}