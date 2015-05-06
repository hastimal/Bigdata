package loadTweet;

import java.applet.Applet;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import connection.ConnectionFactory;

//import com.ibm.jsse2.t;

public class SplashPage extends Applet implements ActionListener
{
	private static final long serialVersionUID = 1L;
	Button b1, b2, b3, b4, b5, b6,b7;
	TextField t1;
	public void init()
	{
	
		Color k=new Color(100, 200, 150);
		setBackground(k);
		FlowLayout gl=new FlowLayout(PROPERTIES);
		setLayout(gl);
		b1 = new Button("query1");
		b2 = new Button("query2");
		b3 = new Button("query3");
		b4 = new Button("query4");
		b5 = new Button("query5");
		b6 = new Button("query6");
		b7 = new Button("query7");
		
		add(b1);
		add(b2);
		add(b3);
		add(b4);
		add(b5);
		add(b6);
		add(b7);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b6.addActionListener(this);
		b7.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	
		String [] args = null;
		if(e.getSource()==b1){
			try {
				Query1.main(args);
			}
			catch (Exception ex){}
			
		}
		if(e.getSource()==b2){
			try {
				//Query2 q2 = new Query2();
				//String s = q2.function();
				//System.out.println(s);
				
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

					for (int i=0;i<10;i++){

						if (res2.next()) {			
							System.out.println("For loop");
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
				

			}
			catch (Exception ex){}
			
		}
		if(e.getSource()==b3){
			try {
				Query3.main(args);
			}
			catch (Exception ex){}
		}
		if(e.getSource()==b4){
			try {
				Query4.main(args);
			}
			catch (Exception ex){}
		}
		if(e.getSource()==b5){
			try {
				Query5.main(args);
			}
			catch (Exception ex){}
		}
		if(e.getSource()==b6){
			try {
				Query6.main(args);
			}
			catch (Exception ex){}
		}
		if(e.getSource()==b7){
			try {
				Query7.main(args);
			}
			catch (Exception ex){}
		}
	}
}

