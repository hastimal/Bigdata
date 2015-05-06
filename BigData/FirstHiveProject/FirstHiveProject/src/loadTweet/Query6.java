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

public class Query6 {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		System.out.println("Starting query 6");
		
		BufferedWriter bw6 = null;
		File f6;
		Connection con = ConnectionFactory.getConnection();
		Map<String,Double> m2 = new HashMap<String,Double>();

		f6 = new File("src/QueryOutput/query6.csv");
		bw6 = new BufferedWriter(new FileWriter(f6, true));

		Statement stmt6 = null;
		if (con != null){

			stmt6 = con.createStatement();

			ResultSet res6 = stmt6.executeQuery ("  select u_info.user_screen_name," +
					" u_count_info.user_statuses_count" +
					" from user_info u_info JOIN user_count_info u_count_info" +
					" ON (u_info.user_id=u_count_info.user_id)" +
					" WHERE u_info.user_screen_name IS NOT NULL " +
					" ORDER BY u_count_info.user_statuses_count DESC");

			bw6.append("username");
			bw6.append(",");
			bw6.append("statuses");
			bw6.newLine();
			
			Double statusesCount;
			while(res6.next()){

				if (m2.isEmpty()) {
					System.out.println (res6.getString(1) + " " + res6.getDouble(2)); 
					m2.put(res6.getString(1), res6.getDouble(2));

					bw6.append(res6.getString(1));
					bw6.append(",");
					statusesCount = res6.getDouble(2);
					statusesCount = statusesCount/10000000;
					bw6.append(statusesCount.toString());					
					bw6.newLine();
				}
				else {
					if (! m2.containsKey(res6.getString(1)) && m2.size() < 10){

						System.out.println (res6.getString(1) + " " + res6.getDouble(2));
						m2.put(res6.getString(1), res6.getDouble(2));
						bw6.append(res6.getString(1));
						bw6.append(",");
						statusesCount = res6.getDouble(2);
						statusesCount = statusesCount/10000000;
						bw6.append(statusesCount.toString());
						bw6.newLine();					
					}
					else if (m2.size() >= 10)
						break;
				}
			}
			System.out.println("Writing sixth analytical query to file");
		}		

		bw6.flush();
		bw6.close();
	}
}
