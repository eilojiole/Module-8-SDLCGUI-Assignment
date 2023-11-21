/*Uchenna Ilojiole
 * CEN 3024 - Software Development 1
 * November 22, 2023
 * Library.java
 * This is the Library Class. In this class we will be able to not only direct our program to retrieve our 
 * Text File from where it is located but it will also assist us in implementing our Library Class to 
 * the rest of our program.
 * This class is also where we will store our present book collection along with adding any new 
 * book titles to our collection. Within this class we will also be able to remove and modify
 * our existing collection as well.
 */

package application;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.io.*;
import java.util.Scanner;

public class Library {
	
	// This Method Will Store Our Book Collection.

	private ArrayList<Book> books = new ArrayList<Book>();
	private int idCounter = 1;

	// This Method Will Assist Us In Loading Our Current Library That Was Previously Saved

	public String loadLibraryFromFile(String filename) {
		System.out.println("************************************");

		// This Will Assist Us In Clearing Out The Existing Books Inside Our Collection

		try {
			books.clear(); 
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String line;

			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(",");
				if (parts.length == 7) { 
					String title = parts[0];
					String author = parts[1];
					int id = Integer.parseInt(parts[2]);
					boolean available = Boolean.parseBoolean(parts[3]);
					String checkedOutDate = parts[4];
					String dueDate = parts[5];

					if (checkedOutDate.equals("N/A")) {
						checkedOutDate = null;
					}
					if (dueDate.equals("N/A")) {
						dueDate = null;
					}

					Book book = new Book(title, author, id, available, checkedOutDate, dueDate);
					books.add(book); 
					if(id < idCounter){
						idCounter=id;
					}
				}
			}
			idCounter+=1;
			reader.close();
			System.out.println("Library loaded successfully.");
			return "Library loaded successfully.";
		} catch (IOException e) {
			System.err.println("Error loading the library from the file: " + e.getMessage());
		}
		return "Error loading the library from the file";
	}

	// This Method Will Assist Us In Adding A Book To The User's Collection And Also Updating The Collection Once The Book Has Been Added.

	public void addBook(String title,String author) { // done
		Book newBook = new Book();
		newBook.setTitle(title);
		newBook.setAuthor(author);
		newBook.setId(idCounter);
		newBook.setAvailable(true);
		newBook.setDueDate(null);
		newBook.setCheckedOutDate(null);
		books.add(newBook);
		idCounter+=1;
	}
	
	public boolean addBook(int Id,String title,String author) { // done
		if(! checkIdIsPresent(Id)) {
			Book newBook = new Book();
			newBook.setTitle(title);
			newBook.setAuthor(author);
			newBook.setId(Id);
			newBook.setAvailable(true);
			newBook.setDueDate(null);
			newBook.setCheckedOutDate(null);
			books.add(newBook);
			return true;
		}
		return false;	
	}
	
	public boolean checkIdIsPresent(int id) {
		for(Book b:this.books) {
			if(b.getId()==id) {				
				return true;
			}
		}
		return false;
	}
	
	// This Method Is For Us To Remove A Book From The User's Collection By The Books ID. 

	public boolean removeBookById(int idToRemove) {
		System.out.println("_________________________________________");

		for (Book book : books) {
			if (book.getId() == idToRemove) {
				books.remove(book);
				System.out.println("Book with ID " + idToRemove + " has been removed from the library.");
				return true;
			}
		}

		System.out.println("Book with ID " + idToRemove + " not found in the library.");
		System.out.println("_________________________________________");
		return false;
	}

	// This Method Is For Us To Remove A Book By It's Title From The User's Collection. 

	public boolean removeBookByTitle(String titleToRemove) {
		System.out.println("_________________________________________");

		for (Book book : books) {
			if (book.getTitle().equalsIgnoreCase(titleToRemove)) {
				books.remove(book);
				System.out.println("Book with title '" + titleToRemove + "' has been removed from the library.");
				return true;
			}
		}

		System.out.println("Book with title '" + titleToRemove + "' not found in the library.");
		System.out.println("_________________________________________");
		return false;
	}
	
	// This Method Will Assist Us In Checking Out A Book From The Library.

	public String checkOutBook(String titleToCheckOut) {
		System.out.println("**************************************");

		for (Book book : books) {
			if (book.getTitle().equalsIgnoreCase(titleToCheckOut)) {
				if (book.isAvailable()) {
					book.checkOut();
				} else {
					System.out.println("Book with title '" + titleToCheckOut + "' is already checked out.");
					return "Book with title '" + titleToCheckOut + "' is already checked out.";
				}
				listBooks();
				return "Check out";
			}
		}

		System.out.println("Book with title '" + titleToCheckOut + "' not found in the library.");
		System.out.println("**************************************");
		return "Book with title '" + titleToCheckOut + "' not found in the library.";
	}

	// This Method Will Assist Us In Returning A Book.

	public String checkInBook(String titleToCheckIn) {
		System.out.println("**************************************");

		for (Book book : books) {
			if (book.getTitle().equalsIgnoreCase(titleToCheckIn)) {
				if (!book.isAvailable()) {
					book.checkIn();
				} else {
					System.out.println("Book with title '" + titleToCheckIn + "' is already available in the library.");
					return "Book with title '" + titleToCheckIn + "' is already available in the library.";
				}
				listBooks();
				return "Check In";
			}
		}

		System.out.println("Book with title '" + titleToCheckIn + "' not found in the library.");
		System.out.println("**************************************");
		return "Book with title '" + titleToCheckIn + "' not found in the library.";
	}

	// This Method Will Assist Us In Taking A Look At Available Books To Check Out.

	public void listBooks() {
		if (books.isEmpty()) {
			System.out.println("The library is currently empty. No books to list.");
		} else {
			System.out.println("List of books in the library:");
			for (Book book : books) {
				System.out.println(book.toString());
			}
		}
	}

	// This Method Will Assist Us In Saving, Updating, and Exiting Our Program

	public String saveLibraryToFile(String filename) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(filename));

			for (Book book : books) {
				writer.write(book.getTitle() + "," + book.getAuthor() + "," + book.getId() + "," + book.isAvailable()
						+ "," + formatNullableDate(book.getCheckedOutDate()) + ","
						+ formatNullableDate(book.getDueDate()));
				writer.newLine();
			}

			writer.close();
			System.out.println("Library saved to the file.");
			return "Library saved to the file.";
		} catch (IOException e) {
			System.err.println("Error saving the library to the file: " + e.getMessage());
		}
		return "Error saving the library to the file";
	}


	private String formatNullableDate(Date date) {
		if (date == null) {
			return "N/A";
		} else {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			return dateFormat.format(date);
		}
	}

	public ArrayList<Book> getBooks() {
		return books;
	}

	public int getIdCounter() {
		return idCounter;
	}

	public void setIdCounter(int idCounter) {
		this.idCounter = idCounter;
	}

	public void setBooks(ArrayList<Book> books) {
		this.books = books;
	}

}
