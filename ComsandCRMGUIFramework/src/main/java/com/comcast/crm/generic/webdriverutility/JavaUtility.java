package com.comcast.crm.generic.webdriverutility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {
	
	public int getRandomNumber() {
		Random random = new Random();
		int randomNumber = random.nextInt(5000);
		return randomNumber;
	}
	
	public String getSystemDateyyyyMMdd() {
		Date dateobj = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(dateobj);
		return date;
	}
	
	public String getRequiredDtaeyyyyMMdd(int days) {
		
		 Date dobj = new Date();
		 SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		 sim.format(dobj);
		 Calendar cal = sim.getCalendar();
		 cal.add(Calendar.DAY_OF_MONTH, days);
		 String reqDate = sim.format(cal.getTime());
		return reqDate;
	}

}
