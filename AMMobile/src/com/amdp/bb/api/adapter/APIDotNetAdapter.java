package com.amdp.bb.api.adapter;

import java.util.Date;

public class APIDotNetAdapter {
	public static Date getDate(String apiDate){
		Date date = new Date();
		
		String rawTime = apiDate;
		//The Dot Net API returns "/Date(1315451534000)/"
		
		rawTime = rawTime.replace('/', ' ');
		rawTime = rawTime.replace('D', ' ');
		rawTime = rawTime.replace('a', ' ');
		rawTime = rawTime.replace('t', ' ');
		rawTime = rawTime.replace('e', ' ');
		rawTime = rawTime.replace('(', ' ');
		rawTime = rawTime.replace(')', ' ');

		rawTime = rawTime.trim();
		
		try{
			date.setTime(Long.parseLong(rawTime));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return date;
	}
}
