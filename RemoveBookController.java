/*Uchenna Ilojiole
 * CEN 3024 - Software Development 1
 * November 22, 2023
 * RemoveBookController.java
 *This will be the main class for the functions within Remove Book class.
 *
 */

package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;

import java.util.ArrayList;

import application.*;

public class RemoveBookController {

    @FXML
    private TableView<Book> bookGrid;

    @FXML
    private CheckBox byID;

    @FXML
    private CheckBox byTitle;

    @FXML
    private Label LMSTitle;

    @FXML
    private TextField text;
    private Library library;

    @FXML
    public void initialize(Library library) {
        this.library=library;
        updateTable();
    }
    @FXML
    void byIDClick(MouseEvent event) {
        this.byTitle.setSelected(false);
    }

    @FXML
    void byTitleClick(MouseEvent event) {
        this.byID.setSelected(false);
    }

    @FXML
    void delAction(ActionEvent event) {
        if (!( byID.isSelected() || byTitle.isSelected())){
            LMSTitle.setTextFill(Paint.valueOf("Red"));
            LMSTitle.setText("Select any one Category");
            return;
        }
        if(text.getText().isEmpty()){
            LMSTitle.setTextFill(Paint.valueOf("Red"));
            LMSTitle.setText("Text should not be empty");
            return;
        }

        if(byID.isSelected()){
            try{
                int intValue = Integer.parseInt(text.getText());
            }
            catch (Exception e){
                LMSTitle.setTextFill(Paint.valueOf("Red"));
                LMSTitle.setText("Text should not in Integer type");
            }
        }
        boolean o = byID.isSelected() ? library.removeBookById(Integer.parseInt(text.getText())) : library.removeBookByTitle(text.getText());
        if(o){
            LMSTitle.setTextFill(Paint.valueOf("Green"));
            LMSTitle.setText("Deleted Successfully");
            updateTable();
            text.setText("");
            return;
        }
        LMSTitle.setTextFill(Paint.valueOf("Red"));
        LMSTitle.setText("Not Found any match !");

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
