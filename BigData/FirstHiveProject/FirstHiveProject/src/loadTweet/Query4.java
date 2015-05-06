package loadTweet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import connection.ConnectionFactory;

public class Query4 {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		System.out.println("Starting query 4");
		
		BufferedWriter bw4 = null;
		File f4;
		Connection con = ConnectionFactory.getConnection();
		Map<String,Long> m1 = new HashMap<String,Long>();
		
		f4 = new File("src/QueryOutput/query4.csv");
		bw4 = new BufferedWriter(new FileWriter(f4, true));

		Statement stmt4 = null;
		if (con != null){

			stmt4 = con.createStatement();

			ResultSet res4 = stmt4.executeQuery ("SELECT u_info.user_screen_name, u_count_info.user_followers_count " +
					"FROM user_info u_info JOIN user_count_info u_count_info " +
					"ON (u_info.user_id=u_count_info.user_id) " +
					"ORDER BY u_count_info.user_followers_count DESC");

			bw4.append("username");
			bw4.append(",");
			bw4.append("followers");
			bw4.newLine();
			
			Long followersCount;
			while (res4.next()){

				if (m1.isEmpty()) {

					m1.put(res4.getString(1), res4.getLong(2));
					bw4.append(res4.getString(1));
					bw4.append(",");
					followersCount = res4.getLong(2);
					bw4.append(followersCount.toString());

					System.out.println("Name : " + res4.getString(1)+ " Followers : "+ followersCount);

					bw4.newLine();
				}
				else {
					if (! m1.containsKey(res4.getString(1)) && m1.size() < 10){

						m1.put(res4.getString(1), res4.getLong(2));
						bw4.append(res4.getString(1));
						bw4.append(",");
						followersCount = res4.getLong(2);
						bw4.append(followersCount.toString());

						System.out.println("Name : " + res4.getString(1)+ " Followers : "+ followersCount);

						bw4.newLine();					
					}
					else if (m1.size() >= 10) {
						break;
					}
				}

			}

			System.out.println("Writing forth analytical query to file");
			bw4.flush();
			bw4.close();
		}
	}
}
