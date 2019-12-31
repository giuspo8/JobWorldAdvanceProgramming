package jobworld.utils;

import java.time.LocalDate;

public class UtilityForController {
	
	public static String localdatetostringdate(LocalDate date) {
		int month=date.getMonthValue();
		int year=date.getYear();
		int day=date.getDayOfMonth();
		String date_c = day + "/" + month + "/" + year;
		return date_c;
	}
}
