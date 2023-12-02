/*Uchenna Ilojiole
 * CEN 3024 - Software Development 1
 * November 30, 2023
 * Book.java
 * This is our Book Class. In this class we will be defining the book attributes that is within this program. 
 * In this class we will be able to retrieve the ID, the Authors Name, and Book Title. 
 */

package application;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.beans.property.*;

public class Book {
	
	// These Methods Will Assist Us In Defining Our Book Attributes
	
	
    private StringProperty title;
    private StringProperty author;
    private IntegerProperty Barcode;
    private BooleanProperty available;
    private StringProperty checkedOutDate;
    private StringProperty dueDate;

    public Book(String title, String author, int Barcode, Boolean available, String checkedOutString, String dueDate) {
        this.title = new SimpleStringProperty(title);
        this.author = new SimpleStringProperty(author);
        this.Barcode = new SimpleIntegerProperty(Barcode);
        this.available = new SimpleBooleanProperty(available);
        this.checkedOutDate = new SimpleStringProperty(checkedOutString);
        this.dueDate = new SimpleStringProperty(dueDate);
    }

    public Book(String title, String author, int Barcode) {
        this.title = new SimpleStringProperty(title);
        this.author = new SimpleStringProperty(author);
        this.Barcode = new SimpleIntegerProperty(Barcode);
        this.available = new SimpleBooleanProperty(true);
    }

	// This Method Will Assist Us In Retrieving The Book By It's Title 

    
    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

	// This Method Will Assist Us In Setting The Books Title 
    
    
    public void setTitle(String title) {
        this.title.set(title);
    }
    
	// This Method Will Assist Us In Retrieving The Book By The Name of It's Author

    
    public String getAuthor() {
        return author.get();
    }

    public StringProperty authorProperty() {
        return author;
    }
    
    
	// This Method Will Assist Us In Setting The Authors Name


    public void setAuthor(String author) {
        this.author.set(author);
    }

    
	// This Method Will Assist Us In Retrieving The Book By It's ID or Barcode
    

    public int getBarcode() {
        return Barcode.get();
    }

    public IntegerProperty barcodeProperty() {
        return Barcode;
    }

    
	// This Method Will Assist Us In Setting The Book's ID or Barcode


    public void setBarcode(int Barcode) {
        this.Barcode.set(Barcode);
    }
    
	// These Methods Will Assist Us In Checking If The Book Is Available For Check Out
    
    
    public boolean isAvailable() {
        return available.get();
    }

    public BooleanProperty availableProperty() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available.set(available);
    }
    
    
	// These Methods Will Assist Us In Establishing The Book's Checkout Date

  
    public String getCheckedOutDate() {
        return checkedOutDate.get();
    }

    public StringProperty checkedOutDateProperty() {
        return checkedOutDate;
    }

    public void setCheckedOutDate(String checkedOutDate) {
        this.checkedOutDate.set(checkedOutDate);
    }

    
	// These Methods Will Assist Us In Obtaining The Book's Due Date


    public String getDueDate() {
        return dueDate.get();
    }

    public StringProperty dueDateProperty() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate.set(dueDate);
    }

    
	// This Method Will Override The toString Method In Our Program 
    

    @Override
    public String toString() {
        return "Book Barcode: " + getBarcode() + "\nTitle: " + getTitle() + "\nAuthor: " + getAuthor() + "\nAvailable: " + isAvailable()
                + "\nChecked Out Date: " + getCheckedOutDate() + "\nDue Date: " + getDueDate();
    }
}
