/*Uchenna Ilojiole
 * CEN 3024 - Software Development 1
 * November 22, 2023
 * Book.java
 * This is our Book Class. In this class we will be defining the book attributes that is within this program. 
 * In this class we will be able to retrieve the ID, the Authors Name, and Book Title. 
 */

package application;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;


public class Book {
	
	// These Methods Will Assist Us In Defining Our Book Attributes

	private String title;
	private String author;
	private int id;
	private boolean available;
	private Date checkedOutDate;
	private Date dueDate;
	
	public Book(String title, String author, int id, boolean available, Date checkedOutDate, Date dueDate) {
		
		this.title = title;
		this.author = author;
		this.id = id;
		this.available = available;
		this.checkedOutDate = checkedOutDate;
		this.dueDate = dueDate;
		
	}
	
	public Book(String title, String author, int id, boolean available, String checkedOutDateStr, String dueDateStr) {
		
		this.title = title;
		this.author = author;
		this.id = id;
		this.available = available;
		this.checkedOutDate = parseDate(checkedOutDateStr);
		this.dueDate = parseDate(dueDateStr);
	}
	
	public Book() {
		super();
	}
	
	// This Method Will Assist Us In Retrieving The Book By It's Title 

	public String getTitle() {
		return title;
	}
	
	// This Method Will Assist Us In Setting The Books Title 

	public void setTitle(String title) {
		this.title = title;
	}
	
	// This Method Will Assist Us In Retrieving The Book By The Name of It's Author

	public String getAuthor() {
		return author;
	}
	
	// This Method Will Assist Us In Setting The Authors Name

	public void setAuthor(String author) {
		this.author = author;
	}
	
	// This Method Will Assist Us In Retrieving The Book By It's ID

	public int getId() {
		return id;
	}
	
	// This Method Will Assist Us In Setting The Book's ID

	public void setId(int id) {
		this.id = id;
	}
	
	// These Methods Will Assist Us In Checking If The Book Is Available For Check Out

	public boolean isAvailable() {
		return available;
	}
	
	public void setAvailable(boolean available) {
		this.available = available;
	}
	
	// These Methods Will Assist Us In Establishing The Book's Checkout Date
	
	public Date getCheckedOutDate() {
		return checkedOutDate;
	}
	
	public void setCheckedOutDate(Date checkedOutDate) {
		this.checkedOutDate = checkedOutDate;
	}
	
	// These Methods Will Assist Us In Obtaining The Book's Due Date

	public Date getDueDate() {
		return dueDate;
	}
	
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	
	// This Method Will Let Us To Know Whether We Are Able To Check This Book Out Or Not

	public void checkOut() {
		if (!available) {
			System.out.println("This Book Is Already Checked Out. You Are Able To Try Another One");
			
		} else {
			checkedOutDate = new Date();
			
			// This Will Assist Us In Pushing The Due Date To Four Weeks Away From The Time The Book Was Check Out
			dueDate = new Date(checkedOutDate.getTime() + (1000 * 60 * 60 * 24 * 24)); 
			setAvailable(false);
			System.out.println("Book With Title " + title + " Has Been Checked Out ");
			System.out.println("Due Date: " + formatDate(dueDate));
			
		}
	}
	
	// This Method Will Let Us To Know Whether We Are Able To Check This Book In Or Not

	public void checkIn() {
		if (available) {
			System.out.println("This Book Is Already Available ");
			
		} else {
			checkedOutDate = null;
			dueDate = null;
			setAvailable(true);
			System.out.println("Book With Title " + title + " You Book Has Now Been Checked In And Returned Back To The Library ");
			
		}
	}
	
	// This Method Is To Format Our Date

	private String formatDate(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		return (date != null) ? dateFormat.format(date) : "N/A";	
	}
	
	// This Method Will Override The toString Method In Our Program 

	@Override
	public String toString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		String checkedOutDateString = checkedOutDate != null ? dateFormat.format(checkedOutDate) : "Null";
		String dueDateString = dueDate != null ? dateFormat.format(dueDate) : "Null";
		
		return "Book ID: " + id + "\nTitle: " + title + "\nAuthor: " + author + "\nAvailable: " + available
				+ "\nChecked Out Date: " + checkedOutDateString + "\nDue Date: " + dueDateString;
		
	}
	
	private Date parseDate(String dateStr) {
		if (dateStr == null || dateStr.isEmpty() || dateStr.equalsIgnoreCase("N/A")) {
			return null;
			
		}
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
			return dateFormat.parse(dateStr);
			
		} catch (ParseException e) {
			
			return null;
		}
	}
	
}
