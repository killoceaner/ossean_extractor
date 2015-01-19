package net.trustie.core;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateTest {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Date now = new Date(70, 01, 01);
//		System.out.println(now);
		Date now = new Date();
		System.out.println(now);
		Calendar ca = new GregorianCalendar();
		ca.setTime(now);
		System.out.println(ca.getTime());
//		int i = 1;
//		System.out.println(i*(-1));
//		Calendar ca = new GregorianCalendar();
//		ca.add(Calendar.DAY_OF_WEEK,1);
//		System.out.println(ca.getTime());
//		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM");
//		Date time = null;
//		try {
//			time = sdf.parse("2014-12");
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(time);
	}

}
