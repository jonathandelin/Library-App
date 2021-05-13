package library;

/** This class defines the properties of a Date object. A date object contains
* a month, day, and year. The methods used in this class are
* isValid(), getYear(), setYear(), getMonth(), setMonth(), getDate(), 
* and setDate().
* @author Jasmin Kaur, Jonathan Delin
*/

import java.util.StringTokenizer;
import java.util.Calendar;

public class Date {
	private int year;
	private int month;
	private int day;
	
  /**
   * Class of useful constants that will be used for comparison and arithmetic.
  */
	public final class DateConstants {
		
		public static final int DATE_MAX_LEN = 10;
		public static final int DATE_MIN_LEN = 5;
		
		public static final int VALID_YEAR = 1900;
		
		public static final int JAN = 1;
		public static final int FEB = 2;
		public static final int MAR = 3;
		public static final int APR = 4;
		public static final int MAY = 5;
		public static final int JUN = 6;
		public static final int JUL = 7;
		public static final int AUG = 8;
		public static final int SEP = 9;
		public static final int OCT = 10;
		public static final int NOV = 11;
		public static final int DEC = 12;
		
		public static final int DAYS_31 = 31;
		public static final int DAYS_30 = 30;
		public static final int FEB_DEFAULT_MAX = 28;
		public static final int FEB_LEAP_MAX = 29;
		
		public static final int QUADRENNIAL = 4;
		public static final int CENTENNIAL = 100;
		public static final int QUATERCENTENNIAL = 400;
		

	}

   /**
	 * Getter method to retrieve year of a date
	 * @return year field of the Date object
   */
	public int getYear() {
		return year;
	}
  
  /**
	 * Setter method to set year of a date
	 * @param year, int representing a year
	 */
	public void setYear(int year) {
		this.year = year;
	}
  
  /**
	 * Getter method to retrieve year of a date
	 * @return year field of the Date object
	 */
	public int getMonth() {
		return month;
	}
  
  /**
	 * Setter method to set month of a date
	 * @param month, int representing a month
	 */
	public void setMonth(int month) {
		this.month = month;
	}
  
  /**
	 * Getter method to retrieve year of a date
	 * @return year field of the Date object
	 */
	public int getDay() {
		return day;
	}
  
  /**
	 * Setter method to set day of a date
	 * @param day, int representing a day
	 */
	public void setDay(int day) {
		this.day = day;
	}
  
  /**
   * Creates an object using date string parameter.
   * Tokenizes date string parameter using "/" to get date object parameters to create Date object.
   * @param date string, raw date string in the format mm/dd/yyyy 
  */
	public Date(String date) { 

       StringTokenizer tok = 
      			new StringTokenizer(date, "/");
       	
      	this.month = Integer.parseInt(tok.nextToken());
      	this.day = Integer.parseInt(tok.nextToken());
      	this.year = Integer.parseInt(tok.nextToken());
       		
	}
	
  /**
   * Creates an object with today's date.
  */
	public Date() { 
		Calendar todays_date = Calendar.getInstance();
		
		this.day = todays_date.get(Calendar.DATE);
		this.month = 1 + todays_date.get(Calendar.MONTH);
		this.year = todays_date.get(Calendar.YEAR);	
	}

	/**
  * Checks if date is valid.
  * @return true if date meets all requirements, returns false otherwise 
  */
	public boolean isValid() {
		Date current_date = new Date();
		int current_day = current_date.getDay();
		int current_month = current_date.getMonth();
		int current_year = current_date.getYear();
		
		//Year condition checks
		if(this.year < 1900) {
			return false;
		}

		if(this.year > current_year) {
			return false;
			
		} else if (this.year == current_year) { 
			if (this.month > current_month) {
				return false;
				
			} else if (this.month == current_month) {
				if (this.day > current_day) {
					return false;
					
				} 
			}
			
		} 
		
		//Check if year is a leap year
		boolean LeapYearCheck;
		
		if (this.year % DateConstants.QUADRENNIAL == 0) {
			
			if (this.year % DateConstants.CENTENNIAL == 0) {
				
				if (this.year % DateConstants.QUATERCENTENNIAL == 0) {
					
					LeapYearCheck = true;
					
				} else {
					
					LeapYearCheck = false;
				}
				
			} else {
				LeapYearCheck = true;
			}
				
		} else {
			LeapYearCheck = false;
		}
		
		//Month condition checks
		if (this.month == DateConstants.JAN || this.month == DateConstants.MAR || this.month == DateConstants.MAY || this.month == DateConstants.JUL || this.month == DateConstants.AUG || this.month == DateConstants.OCT || this.month == DateConstants.DEC) {
			if (this.day <= DateConstants.DAYS_31 && this.day >= 1) {
				return true;
			} else {
				return false;
			}
		}
		
		if (this.month == DateConstants.APR || this.month == DateConstants.JUN || this.month == DateConstants.SEP || this.month == DateConstants.NOV) {
			if (this.day <= DateConstants.DAYS_30 && this.day >= 1) {
				return true;
			} else {
				return false;
			}
		}
		
		//Verify how many days in February are in this year
		if (this.month == DateConstants.FEB) {
			
			if (LeapYearCheck) {
				if (this.day <= DateConstants.FEB_LEAP_MAX && this.day >= 1) {
					return true;
				} else {
					return false;
				}
			} else {
				if (this.day <= DateConstants.FEB_DEFAULT_MAX && this.day >= 1) {
					return true;
				} else {
					return false;
				}
				
			}
			
		}	
		return false;	
	}
	
  /**
  Main method that runs the testbed main cases that tests the isValid() method.
  20 instantiated date objects will be used to test the isValid() method.
  @param args, not used
  */
  
	public static void main(String[] args)
   {
		//FALSE
		Date publishedDate0 = new Date("15/7/2004");
       System.out.println("0: " + publishedDate0.isValid());
       
       Date publishedDate1 = new Date("09/31/2010");
       System.out.println("1: " + publishedDate1.isValid());
       
       Date publishedDate2 = new Date("05/6/2021");
       System.out.println("2: " + publishedDate2.isValid());
       
       Date publishedDate3 = new Date("02/30/2000");
       System.out.println("3: " + publishedDate3.isValid());
       
       Date publishedDate4 = new Date("10/12/1854");
       System.out.println("4: " + publishedDate4.isValid());
       
       Date publishedDate5 = new Date("18/3/2020");
       System.out.println("5: " + publishedDate5.isValid());
       
       Date publishedDate6 = new Date("06/19/2022");
       System.out.println("6: " + publishedDate6.isValid());
       
       Date publishedDate7 = new Date("02/29/2003");
       System.out.println("7: " + publishedDate7.isValid());
       
       Date publishedDate8 = new Date("01/36/2015");
       System.out.println("8: " + publishedDate8.isValid());
       
       Date publishedDate9 = new Date("02/29/2017");
       System.out.println("9: " + publishedDate9.isValid());
       
       //TRUE
       Date publishedDate10 = new Date("02/29/2020");
       System.out.println("10: " + publishedDate10.isValid());
       
       Date publishedDate11 = new Date("01/31/2016");
       System.out.println("11: " + publishedDate11.isValid());
       
       Date publishedDate12 = new Date("09/19/1999");
       System.out.println("12: " + publishedDate12.isValid());
       
       Date publishedDate13 = new Date("06/30/2011");
       System.out.println("13: " + publishedDate13.isValid());
       
       Date publishedDate14 = new Date("09/30/2004");
       System.out.println("14: " + publishedDate14.isValid());
       
       Date publishedDate15 = new Date("11/10/1974");
       System.out.println("15: " + publishedDate15.isValid());
       
       Date publishedDate16 = new Date("02/28/1993");
       System.out.println("16: " + publishedDate16.isValid());
       
       Date publishedDate17 = new Date("08/31/1944");
       System.out.println("17: " + publishedDate17.isValid());
       
       Date publishedDate18 = new Date("12/31/1997");
       System.out.println("18: " + publishedDate18.isValid());
       
       Date publishedDate19 = new Date("02/29/1932");
       System.out.println("19: " + publishedDate19.isValid());
		
      
   }


}
