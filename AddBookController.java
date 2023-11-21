/*Uchenna Ilojiole
 * CEN 3024 - Software Development 1
 * November 22, 2023
 * AddBookController.java
 *In this class we will define our labels and fields
 *within our AddBook method.
 */

package application;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;

import java.net.URL;
import java.util.ResourceBundle;

import application.*;

public class AddBookController{
    @FXML
    private TextField authorName;
	@FXML
	private TextField idTxt;
    @FXML
    private Label LMSTitle;

    @FXML
    private TextField titleName;


    private Library library;
    @FXML
    public void initialize(Library library) {
        this.library=library;
    }
    @FXML
    void saveAction(ActionEvent event) {
    	int id=0;
    	if(idTxt.getText().isEmpty()){
            LMSTitle.setTextFill(Paint.valueOf("Red"));
            LMSTitle.setText("Id should not be empty");
            return;
        }else {
        	try {
    			id = Integer.parseInt(idTxt.getText());
    		}
    		catch(Exception e) {
                LMSTitle.setTextFill(Paint.valueOf("Red"));
                LMSTitle.setText("Id should not in numbers");
                return;
    		}
        }
    	if(authorName.getText().isEmpty() || titleName.getText().isEmpty()){
            LMSTitle.setTextFill(Paint.valueOf("Red"));
            LMSTitle.setText(authorName.getText().isEmpty()==true ? "Author Name should not be empty":"Title should not be empty");
            return;
        }
        
    	boolean result=library.addBook(id,titleName.getText(), authorName.getText());
    	if(result) {
        LMSTitle.setTextFill(Paint.valueOf("Green"));
        LMSTitle.setText("Save Successfully");
        authorName.setText("");
        titleName.setText("");}
    	else {
            LMSTitle.setTextFill(Paint.valueOf("Red"));
            LMSTitle.setText("Id Already present");
    	}
    }

}
