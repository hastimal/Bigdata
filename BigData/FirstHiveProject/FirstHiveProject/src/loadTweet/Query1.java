package loadTweet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import connection.ConnectionFactory;

public class Query1 {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		System.out.println("Starting query 1");
		
		BufferedWriter bw1 = null;
		File f1;

		Connection con = ConnectionFactory.getConnection();

		f1 = new File("src/QueryOutput/query1.csv");
		bw1 = new BufferedWriter(new FileWriter(f1, true));

		Statement stmt1 = null;
		if (con != null){

			stmt1 = con.createStatement();

			ResultSet res1 = stmt1.executeQuery(" SELECT u_info.user_name," +
					" t_info.tweet_place_name,t_info.tweet_place_country," +
					" t_info.tweet_geo_lat,t_info.tweet_geo_long" +
					" FROM tweet_info t_info JOIN user_info u_info" +
					" ON (t_info.tweet_id = u_info.tweet_id)" +
					" WHERE t_info.tweet_geo_lat is not null and t_info.tweet_geo_long is not null");
			
			
			
			while(res1.next()){

				bw1.append(res1.getString(1));
				bw1.append(",");
				bw1.append(res1.getString(2));
				bw1.append(",");
				bw1.append(res1.getString(3));
				bw1.append(",");
				bw1.append(res1.getString(4));
				bw1.append(",");
				bw1.append(res1.getString(5));
				bw1.newLine();
			}
		}
		bw1.flush();
		bw1.close();
	}

}
