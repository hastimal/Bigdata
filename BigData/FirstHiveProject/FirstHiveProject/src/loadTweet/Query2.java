package loadTweet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import connection.ConnectionFactory;

public class Query2 {

	/**
	 * @param args
	 * @throws SQLException 
	 * @throws IOException 
	 */
	public String function() throws SQLException, IOException {
		// TODO Auto-generated method stub

		System.out.println("Query 2 called");
		
		BufferedWriter bw2 = null;
		File f2;

		Connection con = ConnectionFactory.getConnection();
		
		System.out.println("File OPEN");
		f2 = new File("src/QueryOutput/query2.csv");
		bw2 = new BufferedWriter(new FileWriter(f2, true));

		Statement stmt2 = null;
		if (con != null){

			System.out.println("create statement");
			stmt2 = con.createStatement();

			ResultSet res2 = stmt2.executeQuery("  SELECT t_info.tweet_lang, count(*) count from tweet_info t_info" +
					" GROUP BY t_info.tweet_lang " +
					" HAVING t_info.tweet_lang is not null " +
					" ORDER BY count DESC");
			
			bw2.append("Language");
			bw2.append(",");
			bw2.append("Count");
			bw2.newLine();
			
			for (int i=0;i<10;i++){

				if (res2.next()) {			
					
					bw2.append(res2.getString(1));
					bw2.append(",");
					Double count = res2.getDouble(2);
					count = count / 100000;
					bw2.append(count.toString());				
					bw2.newLine();				
				}
			}
		}
		
		System.out.println("wrtitng to file");
		bw2.flush();
		bw2.close();
		
		return "SUCCESS";
	}

	public static void main (String[] args) throws Exception{
		
		Query2 q2 = new Query2();
		System.out.println(q2.function());
	}
	
}
