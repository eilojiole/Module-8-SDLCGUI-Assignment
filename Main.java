/*Uchenna Ilojiole
 * CEN 3024 - Software Development 1
 * November 30, 2023
 * Main.java
 * This is our Main System Class. In this class we will be able to define the users options.
 * In This class we will also be able to retrieve our users input as well. This is also the class
 * where we will be able to save and update our library file based on the previous selections made
 * by our users.
 */

package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.DateStringConverter;

public class Main extends Application {
	
	// This Method Serves To Enable The Table View Within Our Application
	
	
	private TableView<Book> tableView = new TableView<>();
	private Library library = new Library();
	private Stage addBookStage, removeByBarcodeStage, removeByTitleStage;
	private ObservableList<Book> bookList;
	private File selectedFile;

	// This Method Serves As The Construction Of Our Primary Stage.
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("Library Management System");

			Label lmsLabel = new Label("LMS");
			lmsLabel.setStyle("-fx-font-size: 48px; -fx-font-weight: bold; -fx-underline: true; -fx-text-fill: white;");
			lmsLabel.setPrefHeight(30);

			Button manageBookButton = new Button("Manage Books");
			manageBookButton.setPrefWidth(180);
			manageBookButton.setStyle(
					"-fx-text-fill: white; -fx-background-color: #1068C9; -fx-border-color: white; -fx-border-width: 1px;");

			Button removeButton = new Button("Remove a book by Barcode");
			removeButton.setPrefWidth(180);
			removeButton.setStyle(
					"-fx-text-fill: white; -fx-background-color: #1068C9; -fx-border-color: white; -fx-border-width: 1px;");

			Button listAllButton = new Button("List All Books");
			listAllButton.setPrefWidth(180);
			listAllButton.setStyle(
					"-fx-text-fill: white; -fx-background-color: #1068C9; -fx-border-color: white; -fx-border-width: 1px;");
			Button checkButton = new Button("Checkout/Checkin");
			checkButton.setPrefWidth(180);
			checkButton.setStyle(
					"-fx-text-fill: white; -fx-background-color: #1068C9; -fx-border-color: white; -fx-border-width: 1px;");
			Button filesButton = new Button("Manage Files");
			filesButton.setPrefWidth(180);
			filesButton.setStyle(
					"-fx-text-fill: white; -fx-background-color: #1068C9; -fx-border-color: white; -fx-border-width: 1px;");

			Button quitButton = new Button("Quit");
			quitButton.setPrefWidth(180);
			quitButton.setStyle(
					"-fx-text-fill: white; -fx-background-color: #1068C9; -fx-border-color: white; -fx-border-width: 1px;");

			VBox leftArea = new VBox(10);
			VBox rightArea = new VBox(10, tableView);
			leftArea.setPrefWidth(200);
			leftArea.setStyle("-fx-background-color: #1068C9;");
			rightArea.setPadding(new Insets(30));

			HBox mainPageLayout = new HBox(leftArea, rightArea);
			HBox.setHgrow(rightArea, Priority.ALWAYS);
			loadLibraryOnTable();

			listAllButton.setOnAction(e -> {
				rightArea.getChildren().clear();
				loadLibraryOnTable();
				rightArea.getChildren().add(tableView);
			});

			manageBookButton.setOnAction(e -> {
				rightArea.getChildren().clear();

				Label barcodeLabel = new Label("Barcode:");
				TextField barcodeField = new TextField();

				barcodeField.setPromptText("Barcode");
				barcodeField.setPrefHeight(30);
				barcodeField.setPrefWidth(300);
				barcodeField.setMaxWidth(300);

				Label titleLabel = new Label("Title:");
				TextField titleField = new TextField();
				titleField.setPromptText("Title");
				titleField.setPrefHeight(30);
				titleField.setPrefWidth(300);
				titleField.setMaxWidth(300);

				Label authorLabel = new Label("Author:");
				TextField authorField = new TextField();
				authorField.setPromptText("Author");
				authorField.setPrefHeight(30);
				authorField.setPrefWidth(300);
				authorField.setMaxWidth(300);

				Button addButton = new Button("Save");
				addButton.setPrefWidth(130);
				addButton.setOnAction(ev -> {
					int barcode = Integer.valueOf(barcodeField.getText());
					String title = titleField.getText();
					String author = authorField.getText();

					Book newBook = new Book(title, author, barcode, true, null, null);

					library.addBook(newBook);
					loadLibraryOnTable();
				});

				VBox formLayout = new VBox(10);
				formLayout.getChildren().addAll(barcodeLabel, barcodeField, titleLabel, titleField, authorLabel,
						authorField, addButton);
				formLayout.setMaxSize(300, 300);

				rightArea.getChildren().add(formLayout);

			});

			removeButton.setOnAction(e -> {
				rightArea.getChildren().clear();
				loadLibraryOnTable();

				ToggleGroup toggleGroup = new ToggleGroup();
				Label barcodeLabel = new Label("Barcode:");
				TextField barcodeField = new TextField();
				barcodeField.setPromptText("Barcode");
				barcodeField.setPrefHeight(30);
				barcodeField.setPrefWidth(300);
				barcodeField.setMaxWidth(300);
				RadioButton byBarcodeRadio = new RadioButton("By Barcode");
				byBarcodeRadio.setToggleGroup(toggleGroup);
				byBarcodeRadio.setOnAction(eve -> {
					barcodeLabel.setText("Barcode:");
					barcodeField.setPromptText("Barcode");
				});

				RadioButton byTitleRadio = new RadioButton("By Title");
				byTitleRadio.setToggleGroup(toggleGroup);
				byTitleRadio.setOnAction(eve -> {
					barcodeLabel.setText("Title:");
					barcodeField.setPromptText("Title");
				});

				rightArea.getChildren().clear();

				Button remove = new Button("Remove");
				remove.setOnAction(ev -> {
					String barcodeText = barcodeField.getText();
					if (!barcodeText.isEmpty()) {
						if (byBarcodeRadio.isSelected()) {
							int barcode = Integer.parseInt(barcodeText);
							library.removeBookByBarcode(barcode);
						} else if (byTitleRadio.isSelected()) {
							String title = barcodeText;
							library.removeBookByTitle(title);
						}
						loadLibraryOnTable();
					}
				});

				HBox horizontal = new HBox(20);
				horizontal.setPadding(new Insets(50));
				horizontal.setAlignment(javafx.geometry.Pos.CENTER);
				horizontal.getChildren().addAll(barcodeLabel, barcodeField, byBarcodeRadio, byTitleRadio, remove,
						tableView);
				rightArea.getChildren().addAll(horizontal, tableView);
			});

			checkButton.setOnAction(e -> {
				rightArea.getChildren().clear();
				loadLibraryOnTable();

				Label titleLabel = new Label("Title:");
				TextField titleField = new TextField();
				titleField.setPromptText("Enter Book Title");
				titleField.setMaxWidth(300);
				titleField.setPrefWidth(300);
				Button checkOutButton = new Button("CheckOut");
				checkOutButton.setPrefWidth(130);
				checkOutButton.setOnAction(ev -> {
					String title = titleField.getText();
					try {
						library.checkOutBook(title);
					} catch (ParseException e1) {
					}
					loadLibraryOnTable();
				});
				Button checkInButton = new Button("CheckIn");
				checkInButton.setPrefWidth(130);
				checkInButton.setOnAction(ev -> {
					String title = titleField.getText();
					try {
						library.checkInBook(title);
					} catch (ParseException e1) {
					}
					loadLibraryOnTable();
				});
				HBox horizontal = new HBox(20);
				horizontal.setPadding(new Insets(50));
				horizontal.setAlignment(javafx.geometry.Pos.CENTER);
				horizontal.getChildren().addAll(titleLabel, titleField, checkOutButton, checkInButton);

				rightArea.getChildren().addAll(horizontal, tableView);
			});

			filesButton.setOnAction(e -> {
				rightArea.getChildren().clear();
				Button loadButton = new Button("Load Data");
				loadButton.setPrefWidth(130);
				loadButton.setOnAction(ev -> {
					loadTxt();
				});
				Button saveButton = new Button("Save Data");
				saveButton.setPrefWidth(130);
				saveButton.setOnAction(ev -> {
					export();
				});
				rightArea.getChildren().addAll(loadButton, saveButton);

			});

			quitButton.setOnAction(e -> {
				System.exit(0);
			});

			leftArea.getChildren().addAll(lmsLabel);
			VBox buttons = new VBox(10);
			buttons.getChildren().addAll(lmsLabel, manageBookButton, removeButton, listAllButton, checkButton,
					filesButton, quitButton);
			leftArea.getChildren().addAll(buttons);
			leftArea.setSpacing(10);
			leftArea.setPadding(new Insets(10));
			leftArea.setAlignment(javafx.geometry.Pos.TOP_CENTER);
			rightArea.setAlignment(javafx.geometry.Pos.CENTER);

			TableColumn<Book, Number> barcodeColumn = new TableColumn<>("Barcode");
			barcodeColumn.setCellValueFactory(cellData -> cellData.getValue().barcodeProperty());

			TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
			titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());

			TableColumn<Book, String> authorColumn = new TableColumn<>("Author");
			authorColumn.setCellValueFactory(cellData -> cellData.getValue().authorProperty());

			TableColumn<Book, Boolean> availableColumn = new TableColumn<>("Available");
			availableColumn.setCellValueFactory(cellData -> cellData.getValue().availableProperty());
			availableColumn.setCellFactory(tc -> new CheckBoxTableCell<>());
			
			TableColumn<Book, String> checkedOutColumn = new TableColumn<>("Check Out Date");
			checkedOutColumn.setCellValueFactory(cellData -> cellData.getValue().checkedOutDateProperty());

			TableColumn<Book, String> dueColumn = new TableColumn<>("Due Date");
			dueColumn.setCellValueFactory(cellData -> cellData.getValue().dueDateProperty());

			titleColumn.setPrefWidth(150);
			authorColumn.setPrefWidth(150);
			barcodeColumn.setPrefWidth(100);
			availableColumn.setPrefWidth(130);
			checkedOutColumn.setPrefWidth(150);
			dueColumn.setPrefWidth(150);

			tableView.getColumns().addAll(barcodeColumn, titleColumn, authorColumn, availableColumn, checkedOutColumn
					,dueColumn);

			Scene mainPageScene = new Scene(mainPageLayout, 1200, 700);
			primaryStage.setScene(mainPageScene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// This Method Will Assist Us In Loading Our Text File
	
	
	private void loadTxt() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open TXT File");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

		selectedFile = fileChooser.showOpenDialog(new Stage());
		if (selectedFile != null) {
			library.importBooks(selectedFile);
		}
		loadLibraryOnTable();
	}

	// This Method Will Assist Us In Exporting Our File Into Text Format
	
	
	private void export() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save Data to Text File");
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

		File file = fileChooser.showSaveDialog(new Stage());

		if (file != null) {
			try (PrintWriter writer = new PrintWriter(file)) {
				ObservableList<Book> items = tableView.getItems();

				for (Book book : items) {
					writer.println(book.getTitle() + "," + book.getAuthor() + "," + book.getBarcode() + "," 
							+ book.isAvailable() + "," + book.getCheckedOutDate() + "," + book.getDueDate());
				}

				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Export Successful");
				alert.setHeaderText(null);
				alert.setContentText("Data has been exported to " + file.getAbsolutePath());
				alert.show();
			} catch (FileNotFoundException e) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Export Error");
				alert.setHeaderText(null);
				alert.setContentText("Error occurred while exporting data.");
				alert.show();
			}
		}
	}
	
	// This Method Will Assist Us In Loading The Library

	
	private void loadLibraryOnTable() {
		ArrayList<Book> retrieved = new ArrayList<>();
		retrieved.clear();
		retrieved.addAll(library.getBooks());
		bookList = FXCollections.observableArrayList(retrieved);
		tableView.setItems(bookList);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
