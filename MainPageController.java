/*Uchenna Ilojiole
 * CEN 3024 - Software Development 1
 * November 22, 2023
 * MainPageController.java
 *This will be the main class for the functions within the main page of our Library Management System.
 *
 */

package application;

import java.io.IOException;
//import application.Controller.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainPageController {
    @FXML
    AnchorPane childPane;
    Library library = new Library();

    @FXML
    void checkIn(ActionEvent event) {

    }

    @FXML
    void checkin_out(ActionEvent event) {
        String fxmlPath="CheckIn_Out.fxml";
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent content = loader.load();
            CheckInOutController checkOutController = loader.getController();
            checkOutController.initialize(library);
            // Set layout constraints to stretch the content
            AnchorPane.setTopAnchor(content, 0.0);
            AnchorPane.setBottomAnchor(content, 0.0);
            AnchorPane.setLeftAnchor(content, 0.0);
            AnchorPane.setRightAnchor(content, 0.0);

            // Clear existing content and set the loaded content
            if(childPane.getChildren() != null){
                childPane.getChildren().clear();
            }
            childPane.getChildren().add(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void close(ActionEvent event) {
        Stage s = (Stage) childPane.getScene().getWindow();
        s.close();
    }

    @FXML
    void listBook(ActionEvent event) {
        String fxmlPath="ListBooks.fxml";
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent content = loader.load();
            ListBookController lstBookController = loader.getController();
            lstBookController.initialize(library);
            // Set layout constraints to stretch the content
            AnchorPane.setTopAnchor(content, 0.0);
            AnchorPane.setBottomAnchor(content, 0.0);
            AnchorPane.setLeftAnchor(content, 0.0);
            AnchorPane.setRightAnchor(content, 0.0);

            // Clear existing content and set the loaded content
            if(childPane.getChildren() != null){
                childPane.getChildren().clear();
            }
            childPane.getChildren().add(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void manageBook(ActionEvent event) {
        String fxmlPath="AddBook.fxml";
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent content = loader.load();
            AddBookController addBookController = loader.getController();
            addBookController.initialize(library);
            // Set layout constraints to stretch the content
            AnchorPane.setTopAnchor(content, 0.0);
            AnchorPane.setBottomAnchor(content, 0.0);
            AnchorPane.setLeftAnchor(content, 0.0);
            AnchorPane.setRightAnchor(content, 0.0);

            // Clear existing content and set the loaded content
            if(childPane.getChildren() != null){
                childPane.getChildren().clear();
            }
            childPane.getChildren().add(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void manageFile(ActionEvent event) {
        String fxmlPath="load_store.fxml";
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent content = loader.load();
            FileManageController fileManageController = loader.getController();
            fileManageController.initialize(library);
            // Set layout constraints to stretch the content
            AnchorPane.setTopAnchor(content, 0.0);
            AnchorPane.setBottomAnchor(content, 0.0);
            AnchorPane.setLeftAnchor(content, 0.0);
            AnchorPane.setRightAnchor(content, 0.0);

            // Clear existing content and set the loaded content
            if(childPane.getChildren() != null){
                childPane.getChildren().clear();
            }
            childPane.getChildren().add(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void removeBook(ActionEvent event) {
        String fxmlPath="RemoveBook.fxml";
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent content = loader.load();
            RemoveBookController rmBookController = loader.getController();
            rmBookController.initialize(library);
            // Set layout constraints to stretch the content
            AnchorPane.setTopAnchor(content, 0.0);
            AnchorPane.setBottomAnchor(content, 0.0);
            AnchorPane.setLeftAnchor(content, 0.0);
            AnchorPane.setRightAnchor(content, 0.0);

            // Clear existing content and set the loaded content
            if(childPane.getChildren() != null){
                childPane.getChildren().clear();
            }
            childPane.getChildren().add(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadContentIntoChildPane(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent content = loader.load();
            AddBookController addBookController = loader.getController();
            addBookController.initialize(library);
            // Set layout constraints to stretch the content
            AnchorPane.setTopAnchor(content, 0.0);
            AnchorPane.setBottomAnchor(content, 0.0);
            AnchorPane.setLeftAnchor(content, 0.0);
            AnchorPane.setRightAnchor(content, 0.0);

            // Clear existing content and set the loaded content
            if(childPane.getChildren() != null){
                childPane.getChildren().clear();
            }
            childPane.getChildren().add(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
