/*Uchenna Ilojiole
 * CEN 3024 - Software Development 1
 * November 22, 2023
 * ListBookController.java
 *This will be the main class for the functions within our List Books class.
 *
 */

package application;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.*;

import java.util.ArrayList;
import application.*;

public class ListBookController {
    @FXML
    private TableView<Book> bookGrid;
    private Library library;

    @FXML
    public void initialize(Library library) {
        this.library=library;
        updateTable();
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
