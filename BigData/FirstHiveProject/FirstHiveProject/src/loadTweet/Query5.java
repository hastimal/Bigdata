package loadTweet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import connection.ConnectionFactory;

public class Query5 {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		System.out.println("Starting query 5");
		
		BufferedWriter bw5 = null;
		File f5;
		Connection con = ConnectionFactory.getConnection();

		f5 = new File("src/QueryOutput/query5.csv");
		bw5 = new BufferedWriter(new FileWriter(f5, true));

		Statement stmt5 = null;
		if (con != null){

			stmt5 = con.createStatement();

			ResultSet res5 = stmt5.executeQuery("select tweet_place_ctry_code, count(*) count from tweet_info " +
					" where  tweet_place_ctry_code != \"null\" " +
					" group by tweet_place_ctry_code" +
					" having tweet_place_ctry_code is not null" +
					" order by count DESC");

			bw5.append("Country");
			bw5.append(",");
			bw5.append("Count");
			bw5.newLine();
			
			for (int i=0;i<10;i++){

				if (res5.next()) {

					Integer count = res5.getInt(2);

					if (res5.getString(1).length() < 3) {
						bw5.append(res5.getString(1));
						bw5.append(",");					
						bw5.append(count.toString());
						bw5.newLine();
					}
				}
			}
			System.out.println("Writing fifth analytical query to file");
		}
		
		bw5.flush();
		bw5.close();

	}
}