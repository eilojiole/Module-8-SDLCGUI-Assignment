/*Uchenna Ilojiole
 * CEN 3024 - Software Development 1
 * November 30, 2023
 * Library.java
 * This is the Library Class. In this class we will be able to not only direct our program to retrieve our 
 * Text File from where it is located but it will also assist us in implementing our Library Class to 
 * the rest of our program.
 * This class is also where we will store our present book collection along with adding any new 
 * book titles to our collection. Within this class we will also be able to remove and modify
 * our existing collection as well.
 */

package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.scene.control.Alert;

public class Library {
	
	private ArrayList<Book> books = new ArrayList<>();

	File f = new File("books.txt");

	public Library() {
		readBooks(f);
	}

	// This Method Will Assist Us In Adding Books To Our User's System
	
	public void addBook(Book book) {
		try {
			java.io.FileWriter writer = new java.io.FileWriter(f, true);

			String bookData = book.getTitle() + "," + book.getAuthor() + "," + book.getBarcode() + "," + true + ",,,\n";
			books.add(new Book(book.getTitle(),book.getAuthor(),book.getBarcode(),true,"",""));
			writer.write(bookData);
			writer.close();

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText("Success");
			alert.setContentText("Book has been saved successfully");
			alert.showAndWait();
		} catch (Exception e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Error");
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		}
	}

	// This Method Will Assist The User In Removing A Book By Its Barcode
	
	
	public void removeBookByBarcode(int barcodeToRemove) {
		boolean found = false;
		for (Book book : books) {
			if (book.getBarcode() == barcodeToRemove) {
				books.remove(book);
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText("Success");
				alert.setContentText("Book with Barcode " + barcodeToRemove + " removed successfully");
				alert.showAndWait();
				found = true;
				return;
			}
		}
		if (!found) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Error");
			alert.setContentText("Book with Barcode " + barcodeToRemove + " not found.");
			alert.showAndWait();
		}
	}
	
	// This Method Will Assist Us In Removing A Book By Its Title

	
	public void removeBookByTitle(String titleToRemove) {
		boolean found = false;
		for (Book book : books) {
			if (book.getTitle() == titleToRemove) {
				books.remove(book);
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText("Success");
				alert.setContentText("Book with Barcode " + titleToRemove + " removed successfully");
				alert.showAndWait();
				found = true;
				return;
			}
		}
		if (!found) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Error");
			alert.setContentText("Book with Barcode " + titleToRemove + " not found.");
			alert.showAndWait();
		}
	}


	// This Method Will Assist The User In Checking Out A Book Which Will Be Returned in Exactly 4 Weeks.
	
	public void checkOutBook(String titleToCheckOut) throws ParseException {
	    boolean found = false;
	    for (Book book : books) {
	        if (book.getTitle().equalsIgnoreCase(titleToCheckOut)) {
	            if (book.isAvailable()) {
	                book.setAvailable(false);
	                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	                Date currentDate = new Date();
	                String formattedCurrentDate = formatter.format(currentDate);
	                book.setCheckedOutDate(formattedCurrentDate);
	                Date dueDate = new Date();
	                dueDate.setTime(currentDate.getTime() + (28L * 24 * 60 * 60 * 1000)); 
	                String formattedDueDate = formatter.format(dueDate);
	                book.setDueDate(formattedDueDate);
	                found = true;
	                break; 
	            }
	        }
	    }
	    if (!found) {
	        Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setTitle("Error Dialog");
	        alert.setHeaderText("Error");
	        alert.setContentText("Book with Title '" + titleToCheckOut + "' not found or already checked out.");
	        alert.showAndWait();
	    }
	}


	// This Method Will Assist The User In Checking In A Book Which Will Then Set The Availability To True
	
	
	public void checkInBook(String titleToCheckIn) throws ParseException {
		    boolean found = false;
		    for (Book book : books) {
		        if (book.getTitle().equalsIgnoreCase(titleToCheckIn)) {
		            if (!book.isAvailable()) {
		                book.setAvailable(true);
		                book.setCheckedOutDate(null);
		                book.setDueDate(null);
		                found = true;
		                break; 
		            }
		        }
		    }
		    if (!found) {
		        Alert alert = new Alert(Alert.AlertType.ERROR);
		        alert.setTitle("Error Dialog");
		        alert.setHeaderText("Error");
		        alert.setContentText("Book with Title '" + titleToCheckIn + "' not found.");
		        alert.showAndWait();
		    }
		
	}

	public ArrayList<Book> getBooks() {
		return books;
	}

	// This Method Will Assist Us In Getting The Specific Book Title From Our List
	
	
	public void importBooks(File filePath) {
		ArrayList<Book> importedbooks = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = reader.readLine()) != null) {

				String[] data = line.split(",");

				String title = data[0].trim();
				String author = data[1].trim();
				int barcode = Integer.parseInt(data[2].trim());
				String available = data[3].trim();

				Book book = new Book(title, author, barcode, Boolean.valueOf(available), null, null);
				importedbooks.add(book);
			}
			for (Book book : importedbooks) {
				books.add(book);
			}

		} catch (Exception e) {
			e.printStackTrace();
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Error");
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		}
	}

	public void readBooks(File filePath) {
		ArrayList<Book> importedbooks = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = reader.readLine()) != null) {

				String[] data = line.split(",");

				String title = data[0].trim();
				String author = data[1].trim();
				int barcode = Integer.parseInt(data[2].trim());
				String available = data[3].trim();

				Book book = new Book(title, author, barcode, Boolean.valueOf(available), null, null);
				importedbooks.add(book);
			}

			books.clear();

			for (Book book : importedbooks) {
				books.add(book);
			}

		} catch (Exception e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Error");
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		}
	}

}
