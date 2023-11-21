/*Uchenna Ilojiole
 * CEN 3024 - Software Development 1
 * November 22, 2023
 * CheckInOutController.java
 *In this class we will define our columns and fields
 *within our Check In and Check Out method.
 */

package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import application.*;

public class CheckInOutController {


    @FXML
    private TableView<Book> bookGrid;

    @FXML
    private Label LMSTitle;

    @FXML
    private TextField title;
    private Library library;

    @FXML
    public void initialize(Library library) {
        this.library=library;
        updateTable();
    }
    @FXML
    void checkOutAction(ActionEvent event) {

        if(title.getText().isEmpty()){
            LMSTitle.setTextFill(Paint.valueOf("Red"));
            LMSTitle.setText("Title Should not be empty");
            return;
        }
        String result=library.checkOutBook(title.getText());
        if(result.equals("Check out")){
            LMSTitle.setTextFill(Paint.valueOf("Green"));
            LMSTitle.setText(result);
            updateTable();
        }
        LMSTitle.setTextFill(Paint.valueOf("Red"));
        LMSTitle.setText(result);
    }
    @FXML
    void checkInAction(ActionEvent event) {

        if(title.getText().isEmpty()){
            LMSTitle.setTextFill(Paint.valueOf("Red"));
            LMSTitle.setText("Title Should not be empty");
            return;
        }
        String result=library.checkInBook(title.getText());
        if(result.equals("Check In")){
            LMSTitle.setTextFill(Paint.valueOf("Green"));
            LMSTitle.setText(result);
            updateTable();
        }
        LMSTitle.setTextFill(Paint.valueOf("Red"));
        LMSTitle.setText(result);
    }
    private void updateTable() {
        // Assuming library.getBooks() returns a list of Book objects
        ArrayList<Book> lst = library.getBooks();

        // Clear existing items in the table
        bookGrid.getItems().clear();
        bookGrid.getColumns().clear();

        // Create columns for the TableView
        TableColumn idColumn = new TableColumn<>("Book ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn authorColumn = new TableColumn<>("Author");
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));

        TableColumn availableColumn = new TableColumn<>("Available");
        availableColumn.setCellValueFactory(new PropertyValueFactory<>("available"));

        TableColumn checkedOutDateColumn = new TableColumn<>("Checked Out Date");
        checkedOutDateColumn.setCellValueFactory(new PropertyValueFactory<>("checkedOutDate"));

        TableColumn dueDateColumn = new TableColumn<>("Due Date");
        dueDateColumn.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        
        // Add the columns to the TableView
        bookGrid.getColumns().addAll(idColumn, titleColumn, authorColumn, availableColumn, checkedOutDateColumn, dueDateColumn);

        // Add the list of books to the TableView
        bookGrid.getItems().addAll(lst);
    }
}
