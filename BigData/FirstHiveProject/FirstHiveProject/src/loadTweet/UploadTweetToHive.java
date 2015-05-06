package loadTweet;

import connection.ConnectionFactory;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import twitter4j.*; 

public class UploadTweetToHive {

	public static void main(String[] args) throws Exception {

		Connection con = ConnectionFactory.getConnection();
		Statement stmt = null;
		if (con != null){
			
			stmt = con.createStatement();
			//stmt.executeUpdate("load data local inpath '/home/biadmin/HiveData/user_count_info.csv' overwrite into table dummy_user_count_info");

			/*stmt.executeUpdate("load data local inpath '/home/biadmin/HiveData/user_info.csv' overwrite into table dummy_user_info");

			stmt.executeUpdate("load data local inpath '/home/biadmin/HiveData/re_tweet_text_info.csv' overwrite into table dummy_re_tweet_text_info");

			stmt.executeUpdate("load data local inpath '/home/biadmin/HiveData/tweet_text_info.csv' overwrite into table dummy_tweet_text_info");

			stmt.executeUpdate("load data local inpath '/home/biadmin/HiveData/tweet_info.csv' overwrite into table dummy_tweet_info");

			stmt.executeUpdate("load data local inpath '/home/biadmin/HiveData/re_tweet_info.csv' overwrite into table dummy_re_tweet_info");					
		*/}
		
		ResultSet res = stmt.executeQuery("select count(*) from user_count_info");
		if (res.next()){
			System.out.println("Number of rows user Count Info : " + res.getInt(1));
		}
		res = stmt.executeQuery("select count(*) from user_info");
		if (res.next()){
			System.out.println("Number of rows user Info : " + res.getInt(1));
		}
		res = stmt.executeQuery("select count(*) from tweet_info");
		if (res.next()){
			System.out.println("Number of rows tweet Info : " + res.getInt(1));
		}
		res = stmt.executeQuery("select count(*) from tweet_text_info");
		if (res.next()){
			System.out.println("Number of rows user tweet_text : " + res.getInt(1));
		}
		res = stmt.executeQuery("select count(*) from re_tweet_text_info");
		if (res.next()){
			System.out.println("Number of rows re_tweet_text_info : " + res.getInt(1));
		}
		
		stmt.executeUpdate("insert into table user_count_info select * from dummy_user_count_info");

		stmt.executeUpdate("insert into table user_info select * from dummy_user_info");

		stmt.executeUpdate("insert into table re_tweet_text_info select * from dummy_re_tweet_text_info");

		stmt.executeUpdate("insert into table tweet_text_info select * from dummy_tweet_text_info");

		stmt.executeUpdate("insert into table tweet_info select * from dummy_tweet_info");

		stmt.executeUpdate("insert into table re_tweet_info select * from dummy_re_tweet_info");
		
		res = stmt.executeQuery("select count(*) from user_count_info");
		if (res.next()){
			System.out.println("Number of rows user Count Info : " + res.getInt(1));
		}
		res = stmt.executeQuery("select count(*) from user_info");
		if (res.next()){
			System.out.println("Number of rows user Info : " + res.getInt(1));
		}
		res = stmt.executeQuery("select count(*) from tweet_info");
		if (res.next()){
			System.out.println("Number of rows tweet Info : " + res.getInt(1));
		}
		res = stmt.executeQuery("select count(*) from tweet_text_info");
		if (res.next()){
			System.out.println("Number of rows user tweet_text : " + res.getInt(1));
		}
		res = stmt.executeQuery("select count(*) from re_tweet_text_info");
		if (res.next()){
			System.out.println("Number of rows re_tweet_text_info : " + res.getInt(1));
		}
	}
}
