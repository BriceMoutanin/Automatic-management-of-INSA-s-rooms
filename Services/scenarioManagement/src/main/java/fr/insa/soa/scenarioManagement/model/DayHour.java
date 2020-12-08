package fr.insa.soa.scenarioManagement.model;

import java.util.Calendar;

public class DayHour {
	
	
	public static boolean isItWorkDay(){
		Calendar cal = Calendar.getInstance();
		int dayOfWeek= cal.get(Calendar.DAY_OF_WEEK);
		// HORAIRES OU LE GEI EST OUVERT 
		if(dayOfWeek!=1 && dayOfWeek!=7){
			return true;
		}else{
			return false;
		}
		
	}
	
	public static boolean isItWorkHours(){
		Calendar cal = Calendar.getInstance();
		int hourOfDay= cal.get(Calendar.HOUR_OF_DAY);
		// HORAIRES OU LE GEI EST OUVERT 
		if(hourOfDay>7 && hourOfDay<20){
			return true;
		}else{
			return false;
		}
	}
	public static boolean isItDark(){
		Calendar cal = Calendar.getInstance();
		int hourOfDay= cal.get(Calendar.HOUR_OF_DAY);
		if(hourOfDay>17 && hourOfDay<22){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isItForbidden(){
		Calendar cal = Calendar.getInstance();
		int hourOfDay= cal.get(Calendar.HOUR_OF_DAY);
		if(hourOfDay>22 || hourOfDay<6){
			return true;
		}else{
			return false;
		}
	}


}
