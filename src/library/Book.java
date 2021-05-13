package library;

/**
 * This is the container class that defines the abstract data type for a
 * Book and its operations. A Book contains a name, serial number, a date it was published
 * and a boolean of if the item is checked out. The class contains methods equals(),
 * toString(), dateToString(), getDate(), getNumber(), getCheckedOut(), setCheckedOut() and getName().
 *
 * @author Jasmin Kaur, Jonathan Delin
 *
 */

public class Book {
	private String number; // a 5-digit serial number unique to the book
	
	/**
	 * The name of the book
	 */
	private String name;
	
	/**
	 * Is the Book checked out or not
	 */
	private boolean checkedOut;
	
	/**
	 * The date the book was published
	 */
	private Date datePublished;
	
	/**
	 * @param number	Serial number of the Book
	 * @param name    Name of the Book
	 * @param checkedOut   If the Book is checked out
	 * @param datePublished When the Book was published
	 */
	public Book(String number, String name, boolean checkedOut, Date datePublished) {
		this.number = number;
		this.name = name;
		this.checkedOut = checkedOut;
		this.datePublished = datePublished;
	}
	
	/**
	 * Override equals method. Checks if all parameters are true for both objects
	 * and boolean
	 * 
	 * @param obj Object we are checking for equality
	 */
	public boolean equals(Object obj) {
		if (obj instanceof Book) {
			Book temp = (Book) obj;
			if (this.number.equals(temp.number)) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	
   /**
   * Method to convert the Date object parameters back into mm/dd/yyyy format.
   * @param date, Date object that will be converted into a string
   * @return the date object that was converted into a string
   */
   public String dateToString(Date date) {
        return(date.getMonth() + "/" + date.getDay() + "/" + date.getYear());
   }

	
	/**
	 * Overrides the toString method to properly print the object
	 */
	public String toString() {
		String availability;
		if (this.checkedOut) {
			availability = "is checked out.";
		} else {
			availability = "is available.";
		}
		
		return "Book" + "#" + this.number + "::" + this.name + "::" + dateToString(datePublished) + "::" + availability;
	}
	
	/**
	 * Getter method to retrieve book serial number
	 * @return serial number field of Book object
	 */
	public String getNumber() {
		return number;
	}
	
   /**
	 * Getter method to retrieve book name 
	 * @return name field of the Book object
	 */
	public String getName() {
		return name;
	}
   
   /**
	 * Getter method to get check out status of a book.
	 * @return true or false depending on if a book is checked out or not
	 */
	public boolean getCheckedOut() {
		return checkedOut;
	}
	
   /**
   * Method to set the checked out status of a book to a boolean value.
   * @param temp, boolean to set checked out status of a book
   */
	public void setCheckedOut(boolean temp) {
		this.checkedOut = temp;
	}
	
   /**
	 * Getter method to get publish date of a book
	 * @return datePublished field of the Book object
	 */
	public Date getDatePublished() {
		return datePublished;
	}

	
}