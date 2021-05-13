package library;

/**
 * This is the container class that defines the abstract data type Library
 * to hold library catalog and its operations. A library holds an array of Books,
 * and this class implements several methods to add, remove, checkout, return,
 * and display the list of Books by the date published or by the Book's
 * serial number
 *
 * @author Jasmin Kaur, Jonathan Delin
 *
 */

public class Library {
	
   static final int INVALID_RETURN_VALUE = -1;
   static final int INCREASE_CAPACITY = 4;
   
	/**
	 * An array of Book objects initiated to an array of size 4
	 */
	private Book[] books = new Book[4]; 
	
	/**
	 *the number of books currently in the bag
	 */
	private int numBooks = 0; 
	
	/**
	 *default constructor to create empty bag
	 */
	public Library() {
	} 
	
	/**
	 * Finds the array location where the book is being stored
	 * 
	 * @param book Book that is to be foundinvalid index value if not found
	 * -1 if not found
	 */
	private int find(Book book) {
		int i = 0;
		while (i < numBooks) {
			if (books[i].equals(book)) {
				return i;
			}
			i++;
		}
		return INVALID_RETURN_VALUE; //returns value not found
	}
	
   /**
   * Finds the index of a given serial number.
   * Returns the index value of the book with that serial number as a string.
   * Otherwise, returns invalid index value of -1 as a string.
   * @param serial, is the serial number that is being searched for
   * @return index as string if found, return invalid index value if not found.
   */
	public String findSerial(String serial) {
		int i = 0;
		while (i < numBooks) {
			if(Integer.parseInt(books[i].getNumber()) == Integer.parseInt(serial)) {
				String index = String.valueOf(i);
				return index;
			}
			i++;
		}
		return String.valueOf(INVALID_RETURN_VALUE);
	}
	
	/**
	 * Increases book array capacity by 4 
	 */
	private void grow() {
		Book[] newBooks = new Book[books.length + INCREASE_CAPACITY];
		for (int i = 0; i < books.length; i++) {
			newBooks[i] = books[i];
		}

		books = newBooks;
	} 
	
	/**
	 * Adds Book to the book bag
	 * 
	 * @param book Book that is to be added
	 */
	public void add(Book book) {
		int i = 0;
		while (true) {

			if (books[i] == null) {
				books[i] = book;
				numBooks++;

				if (numBooks == books.length) {
					grow();
				}
				break;
			}
			i++;
		}
	}
	
	
	/**
	 * Removes the book from the book bag (array)
	 * 
	 * @param book Book that is to be removed
	 * @return true if found and removed, false otherwise
	 */
	public boolean remove(Book book) {
		
		if (find(book) != INVALID_RETURN_VALUE) {
			
			int j = 0;
			for(int i = 0; i < books.length; i++) {
				if (books[i] != book) {
					books[j++] = books[i];
				}
			}
			
			while (j < books.length) {
				books[j++] = null;
			}
			
			numBooks--;
			

			return true;
		}

		return false;
	}
	

    /**
     * Checks if the book has been checked out by a user or not.
     * @param book, is the book that will be checked if it has been checked out or not
     * @return true if checked out or if book does not exist, false if not
    */
	public boolean checkOut(Book book) { 
		if (find(book) != INVALID_RETURN_VALUE) {
				if(books[find(book)].getCheckedOut()) { 
					return true; 
				} else { 
					books[find(book)].setCheckedOut(true);
					return false; 
				}
		}

		return true; //default return true, if book not found
	}
   
   /**
    * Allows user to return a book that they have rented.
    * @param book, is the book that is being returned
    * @return true if the book exists and has been checked out
   */
	public boolean returns(Book book) { 
		if (find(book) != INVALID_RETURN_VALUE) {
			if(books[find(book)].getCheckedOut()) { //if book checked out is true
				books[find(book)].setCheckedOut(false);
				return true; //book is returned
			} else {
				return false; //book is not checked out so cant return
			
			}
		}
		return false; //book does not exist

	}
	
   /**
    * Sort method for arranging books in ascending order by their serial number.
   */
	public void sortByNumber() {
		//variables for comparison
		int currentNum;
		int nextNum;
		Book tempBook;
		
		for (int i = 1; i < numBooks; i++) {
			nextNum = Integer.parseInt(books[i].getNumber());
			tempBook = books[i];
			currentNum = i;
			
			while (currentNum > 0 && Integer.parseInt(books[currentNum - 1].getNumber()) > nextNum) { //swap if current index serial number is greater than the next index's
				books[currentNum] = books[currentNum - 1];
				currentNum = currentNum - 1;
			}
			
			books[currentNum] = tempBook;
		}
		
		
	}
	
   /**
    * Sort method for arranging books in ascending order by their publish date.
   */
	public void sortByDate() {
		//variables for comparison operations
		int currentNum;
		int nextNum;
		int nextNumMonth;
		int nextNumDay;
		String nextNumName;
		Book tempBook;
		
		for (int i = 1; i < numBooks; i++) {
			
			nextNum = books[i].getDatePublished().getYear();
			nextNumMonth = books[i].getDatePublished().getMonth();
			nextNumDay = books[i].getDatePublished().getDay();
			nextNumName = books[i].getName();
			
			tempBook = books[i];
			currentNum = i;
			
			while (currentNum > 0 && books[currentNum - 1].getDatePublished().getYear() >= nextNum) {
				
				if (books[currentNum - 1].getDatePublished().getYear() == nextNum) { //if same publish year, check month, else continue with swap
					
					if (books[currentNum - 1].getDatePublished().getMonth() == nextNumMonth) { //if same publish month, check day, else continue with swap
						
						if (books[currentNum - 1].getDatePublished().getDay() == nextNumDay) { //if same publish day, check serial number, else continue with swap
							
							if (books[currentNum - 1].getName().compareTo(nextNumName) > 0) { //if same publish day, sort lexicographically
								
								books[currentNum] = books[currentNum - 1];
								currentNum = currentNum - 1;
								break;
								
							} else {
								break;
							}
		
							
						} else if (books[currentNum - 1].getDatePublished().getDay() > nextNumDay) { //swap based on day value
	
							books[currentNum] = books[currentNum - 1];
							currentNum = currentNum - 1;
							
						} else {
							break;
						}
						
					} else if (books[currentNum - 1].getDatePublished().getMonth() > nextNumMonth) { //swap based on month value
								
						books[currentNum] = books[currentNum - 1];
						currentNum = currentNum - 1;
						
					} else {
						break;
					}
				} else if (books[currentNum - 1].getDatePublished().getYear() > nextNum) { //swap based on year value
					
					books[currentNum] = books[currentNum - 1];
					currentNum = currentNum - 1;
				} else {
					break;
				}
					
			}
			
			books[currentNum] = tempBook;
		}
		
		
	}
   
	/**
     * Method to retrieve the books array.
     * @return books, the array of books otherwise known as the bag containing all the books
    */
	public Book[] getBooks() {
		return books;
	}

   /**
    * Method to set the books array equal to a value.
    * @param books, the books array otherwise known as the bag of books
   */
	public void setBooks(Book[] books) {
		this.books = books;
	}
   
   /**
    * Method to retrieve the amount of books in the bag.
    * @return numBooks, the amount of books currently in the bag as an int value
   */
	public int getNumBooks() {
		return numBooks;
	}
   
   /**
    * Method to set the number of books equal to a value of one's choosing.
    * @param numBooks, the number of books in the bag
   */
	public void setNumBooks(int numBooks) {
		this.numBooks = numBooks;
	}
   
   /**
    * Default print method that prints the list of books in the bag in its present order.
   */
	public void print() { 
		
		//first check if bag is empty
		boolean empty = true;
		for (int i=0; i<books.length; i++) {
		  if (books[i] != null) {
		    empty = false;
		    break;
		  }
		}
		if(empty == true) {
			System.out.println("Library catalog is empty!");
		} else {
		System.out.println("**List of books in the library.");
		for (int i = 0; i < numBooks; i++) {
			System.out.println(books[i]);
		}
		System.out.println("**End of list");
		}
	} 
	
   /**
    * Print method that prints the list of books in the bag in ascending order by date published.
    * Calls a method that sorts the books by date published before printing.
   */
	public void printByDate() {
		
		//first check if bag is empty
		boolean empty = true;
		for (int i=0; i<books.length; i++) {
		  if (books[i] != null) {
		    empty = false;
		    break;
		  }
		}
		if(empty == true) {
			System.out.println("Library catalog is empty!");
		} else {
		System.out.println("**List of books by the dates published.");
		
		//sort by date, then print the bag
		sortByDate();
		for (int i = 0; i < numBooks; i++) {
			System.out.println(books[i]);
		}
		System.out.println("**End of list");
		}
	} 
	
   /**
    * Print method that prints the list of books in the bag in ascending order by serial number.
    * Calls a method that sorts the books by serial number before printing.
   */
	public void printByNumber() { //print the list of books by number (ascending)
		
		//first check if bag is empty
		boolean empty = true;
		for (int i=0; i<books.length; i++) {
		  if (books[i] != null) {
		    empty = false;
		    break;
		  }
		}
		if(empty == true) {
			System.out.println("Library catalog is empty!");
		} else {
		System.out.println("**List of books by the book numbers.");
		
		//sort by serial number, then print the bag
		sortByNumber();
		for (int i = 0; i < numBooks; i++) {
			System.out.println(books[i]);
	
		}
		System.out.println("**End of list");
		}
	} 
	

	
}