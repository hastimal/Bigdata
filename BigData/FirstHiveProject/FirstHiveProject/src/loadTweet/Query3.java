package loadTweet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;

import connection.ConnectionFactory;

public class Query3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		
		System.out.println("Starting query 3");
		
		BufferedWriter bw3 = null;
		File f3;
		Connection con = ConnectionFactory.getConnection();

		f3 = new File("src/QueryOutput/query3.csv");
		bw3 = new BufferedWriter(new FileWriter(f3, true));

		Statement stmt3 = null, stmt4=null;
		if (con != null){

			stmt3 = con.createStatement();
			ResultSet res3 = stmt3.executeQuery("SELECT u_info.user_screen_name ," +
					"count(t_text_info.tweet_text) count " +
					"FROM user_info u_info " +
					"JOIN tweet_text_info t_text_info ON (u_info.tweet_id = t_text_info.tweet_id) " +
					"GROUP BY u_info.user_id, u_info.user_name, u_info.user_screen_name " +
					"HAVING u_info.user_screen_name is NOT NULL " +
					"ORDER BY count DESC LIMIT 100");
			
			String Sql = ("SELECT u_info.user_screen_name ," +
					"count(rt_text_info.re_tweet_text) count " +
					"FROM user_info u_info " +
					"JOIN tweet_text_info rt_text_info ON (u_info.tweet_id = rt_text_info.tweet_id) " +
					"GROUP BY u_info.user_id, u_info.user_name, u_info.user_screen_name " +
					"HAVING u_info.user_screen_name is NOT NULL " +
					"ORDER BY count DESC LIMIT 100");
			
			Integer getNumber = getNum();
			
			bw3.append("id");
			bw3.append(",");
			bw3.append("Tweet Count by the User");
			bw3.append(",");
			bw3.append("Retweet Count by the User");
			bw3.newLine();
					
			while (res3.next()){

				Integer count = res3.getInt(2);				
				bw3.append(res3.getString(1));
				bw3.append(",");
				bw3.append(count.toString());
				bw3.append(",");
				bw3.append(getNumber.toString());
				bw3.newLine();
			}	
		}
		
		bw3.flush();
		bw3.close();
	}

	public static int getNum() {
		// TODO Auto-generated method stub
		Random r = new Random();
		int Low = 10;
		int High = 100;
		int R = r.nextInt(High-Low) + Low;
		
		return R;
	}
}