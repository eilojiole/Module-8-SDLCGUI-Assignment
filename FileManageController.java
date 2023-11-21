/*Uchenna Ilojiole
 * CEN 3024 - Software Development 1
 * November 22, 2023
 * FileManagerController.java
 *This will be the main class for the functions within our File manager.
 *
 */

package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import application.*;


public class FileManageController {
    @FXML
    private Label LMSTitle;
    private Library library;

    @FXML
    public void initialize(Library library) {
        this.library=library;
    }
    @FXML
    void loadAction(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload File Path");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("TEXT", "*.txt")
        );


        File file = fileChooser.showOpenDialog(this.LMSTitle.getScene().getWindow());
        int before=library.getBooks().size();
        String result= library.loadLibraryFromFile(file.getPath());
        if(! result.isEmpty()){
            LMSTitle.setTextFill(Paint.valueOf("Green"));
            LMSTitle.setText("Save at "+file.getPath());
            return;
        }
        LMSTitle.setTextFill(Paint.valueOf("Red"));
        LMSTitle.setText("Error found while loading from file");
    }

    @FXML
    void saveData(ActionEvent event) {
        DirectoryChooser directoryChooser=new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(this.LMSTitle.getScene().getWindow());
        Date date=new Date();
        DateFormat df=new SimpleDateFormat("dd_MM_yy_hh_mm_ss");
        String result=library.saveLibraryToFile(selectedDirectory.getPath()+"libarayData"+df.format(date)+".txt");
        if(! result.isEmpty()){
            LMSTitle.setTextFill(Paint.valueOf("Green"));
            LMSTitle.setText(result);
            return;
        }
        LMSTitle.setTextFill(Paint.valueOf("Red"));
        LMSTitle.setText("Found Error while saving data");
    }
}
