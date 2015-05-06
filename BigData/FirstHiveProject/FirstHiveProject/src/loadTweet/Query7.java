package loadTweet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import connection.ConnectionFactory;

public class Query7 {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub

		System.out.println("Starting query 7");
		
		BufferedWriter bw7 = null;
		File f7;
		Connection con = ConnectionFactory.getConnection();
		Map<String,Long> m1 = new HashMap<String,Long>();
		String FirstQ = "0 AM - 5 AM", SecondQ = "6 AM - 11 AM", ThirdQ = "12 PM - 17 PM ", FourthQ = " 18 PM - 23 PM";
		long value = 0;


		f7 = new File("src/QueryOutput/query7.csv");
		bw7 = new BufferedWriter(new FileWriter(f7, true));

		Statement stmt7 = null;
		if (con != null){

			stmt7 = con.createStatement();
			ResultSet res7 = stmt7.executeQuery(" SELECT * from tweet_info where tweet_created_at is not NULL");
			
			bw7.append("Time_Interval");
			bw7.append(",");
			bw7.append("Tweet_Count");
			bw7.newLine();

			while (res7.next()){

				SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss");
				String dateInString = res7.getString(2);

				dateInString = dateInString.substring(0, 19);
				Date date = formatter.parse(dateInString);

				System.out.println ("data.getHours() : " + date.getHours() );

				if (m1.isEmpty()){

					if (date.getHours() >= 0 && date.getHours() < 6){

						m1.put(FirstQ, (long) 1);
					}
					else if (date.getHours() >= 6 && date.getHours() < 12){

						m1.put(SecondQ, (long) 1);
					}
					else if (date.getHours() >= 12 && date.getHours() < 18){

						m1.put(ThirdQ, (long) 1);
					}
					else {
						m1.put(FourthQ, (long) 1);
					}
				}
				else{
					if (date.getHours() >= 0 && date.getHours() < 6){

						if (m1.get(FirstQ) == null)
							value = 0;
						else {
							value = m1.get(FirstQ);
						}
						m1.put(FirstQ, value+1);
					}
					else if (date.getHours() >= 6 && date.getHours() < 12){

						if (m1.get(SecondQ) == null)
							value = 0;
						else {
							value = m1.get(SecondQ);
						}
						
						m1.put(SecondQ, value+1);
					}
					else if (date.getHours() >= 12 && date.getHours() < 18){

						if (m1.get(ThirdQ) == null)
							value = 0;
						else {
							value = m1.get(ThirdQ);
						}
						m1.put(ThirdQ, value+1);
					}
					else {

						if (m1.get(FourthQ) == null)
							value = 0;
						else {
							value = m1.get(FourthQ);
						}
						m1.put(FourthQ, value+1);
					}

				}
			}
			
			for (Map.Entry<String, Long> entry : m1.entrySet()) {
				
				bw7.append(entry.getKey());
				bw7.append(",");
				bw7.append(entry.getValue().toString());
				bw7.newLine();
				
				System.out.println("Key : " + entry.getKey() + " Value : "
						+ entry.getValue());
			}

		}

		bw7.flush();
		bw7.close();		

	}

}
