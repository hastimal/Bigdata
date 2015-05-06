package sTarun;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Example {

	/**
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub

		SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss");
		String dateInString = "Sat May 02 18:00:25 CDT 2015";
		
		dateInString = dateInString.substring(0, 19);
	 
		System.out.println(dateInString);
	 
			Date date = formatter.parse(dateInString);
			System.out.println(date.getDate());
			System.out.println(date.getHours());
			System.out.println(formatter.format(date));
	}

}
