package library;

/** Kiosk is the running class, where input is read from the command line
* using scanner. Inputs have the following formats:
* 
* -To add a book to the library: "A, Programming in Java, 12/30/2019" 
* -To remove a book from the library: R,10005 where '10005' is the serial number
* -Serial numbers are generated when an instance of a book is added to the library
* -To checkout a book: C,10005  where '10005' is the serial number
* -To return a book: I, 10005  where '10005' is the serial number
* -To print the books in the library: 
* PA to print in the order they were added
* PD to print by date published
* PN to print by serial number order
*-To quit and stop running: 'Q'
* 
* Will print an error message is input is not valid.
* Output will be printed in console.
* 
* 
* @author Jasmin Kaur, Jonathan Delin
* 
*
*/

import java.util.Scanner;
import java.util.StringTokenizer;

public class Kiosk {
  static final int SERIAL_NUMBER = 10000;
  static final int INVALID_INDEX = -1;
  static final int ADD_PARAMETERS = 3;
  static final int REMOVE_CHECKOUT_RETURN_PARAM = 2;
	
  /**
  *Runs the program and takes user inputs until user quits program via Q command.
  */
	public void run() { 
		
		Library library = new Library();
		Scanner scan = new Scanner(System.in);
		System.out.println("Library Kiosk running.");
		int serialNum = SERIAL_NUMBER;

			while(scan.hasNext()) {
				String command = scan.nextLine();
				if (command.equals("")) {
					continue;
				} 
				
				//String tokenizer is used to separate the input into tokens
				StringTokenizer tok = new StringTokenizer(command, ",");
				int tokenAmount = tok.countTokens();
				String letter = tok.nextToken(); 
				
				switch(letter) {
				
				//Handles adding book to library
				case "A": 
					if (tokenAmount != ADD_PARAMETERS) {
						System.out.println("Error: Wrong token amount:" + tokenAmount);
						break;
					}
					
					String name = tok.nextToken();
					String date = tok.nextToken();
					Date datePublished = new Date(date);
					
					//checks if the published date is valid, then proceeds to add 
					if (datePublished.isValid()) {
						boolean checkedOut = false;
						serialNum++;
						String serial = Integer.toString(serialNum);
						Book temp = new Book(serial, name, checkedOut, datePublished);
						library.add(temp);
						System.out.println(name + " added to the library.");		
					} else {
						System.out.println("Invalid Date!");
					}
					break;
				
				//user wants to remove book
				case "R" : 
					if (tokenAmount != REMOVE_CHECKOUT_RETURN_PARAM) {
						System.out.println("Error: Wrong token amount:" + tokenAmount);
						break;
					}

				    String removeNum = tok.nextToken();
				    //Locate serial number and then parse it into an int
					String getIndex = library.findSerial(removeNum);
					int parseIndex = Integer.parseInt(getIndex);
					
					//If the retrieved index is valid, then proceed with removing the book
					if (parseIndex != INVALID_INDEX) {
						Book getBook = library.getBooks()[parseIndex];
						boolean removed = library.remove(getBook);
						if(removed) {
							System.out.println("Book# " + removeNum + " removed." );
						} else {
							System.out.println("Unable to remove, the library does not have this book.");
						}
					} else {
						System.out.println("Unable to remove, the library does not have this book.");
					}
					break;
				
				//user wants to checkout book
				case "O" : 
					if (tokenAmount != REMOVE_CHECKOUT_RETURN_PARAM) {
						System.out.println("Error: Wrong token amount:" + tokenAmount);
						break;
					}

					String check = tok.nextToken();
					//Locate serial number and then parse it into an int
					String getIndexO = library.findSerial(check);
					int parseIndexO = Integer.parseInt(getIndexO);
					
					if (parseIndexO == INVALID_INDEX) {
						System.out.println("Book#" + check + " is not available.");
						break;
					}
					
					//If the retrieved index is valid, then proceed with check out procedure
					if (parseIndexO != INVALID_INDEX) {
						Book checkBook = library.getBooks()[parseIndexO];
						boolean checkedBook = library.checkOut(checkBook);
						
						if(checkedBook) { 
							System.out.println("Book#" + check + " is not available.");
						} else {
							System.out.println("You've checked out Book#" + check + ". Enjoy!");
						}
							
					} else {
						System.out.println("Book#" + check + " is not available.");
					}
					break;
					
				case "I" : //user wants to return book
					if (tokenAmount != REMOVE_CHECKOUT_RETURN_PARAM) {
						System.out.println("Error: Wrong token amount:" + tokenAmount);
						break;
					}

					String returning = tok.nextToken();
					//Locate serial number and then parse it into an int
					String getIndexI = library.findSerial(returning);
					int parseIndexI = Integer.parseInt(getIndexI);
					
					//If the retrieved index is valid, then proceed with return procedure
					if (parseIndexI != INVALID_INDEX) {
						Book returnBook = library.getBooks()[parseIndexI];
						boolean returned = library.returns(returnBook);
						
						if (returned) {
							System.out.println("Book#" + returning + " return has completed. Thanks!" );
						} else {
							System.out.println("Unable to return Book#" + returning + ".");
						}
						
					} else {
						System.out.println("Unable to return Book#" + returning + ".");
					}
					break;
					
				//Print bag in its current order 	
				case "PA" : 
					if (library.getNumBooks() != 0) {
						library.print();
					} else {
						System.out.println("Library catalog is empty!");
					}
					break;
				
				//Print bag in ascending order by date
				case "PD" :
					if (library.getNumBooks() != 0) {
						library.printByDate();
					} else {
						System.out.println("Library catalog is empty!");
					}
					break;
					
				//Print bag in ascending order by serial number
				case "PN" :
					if (library.getNumBooks() != 0) {
						library.printByNumber();
					} else {
						System.out.println("Library catalog is empty!");
					}
					break;
				
				//Terminate program
				case "Q":
					System.out.println("Kiosk session ended.");
					scan.close();
					return;
				
				default:
					System.out.println("Invalid command!");
					break;
				}
			}

			scan.close();
			
	}
	
	
}
