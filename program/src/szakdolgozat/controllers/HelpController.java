package szakdolgozat.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class HelpController {

    @FXML
    private ListView todoListView;

    @FXML
    private TextArea itemDetailsTextArea;

    public void initialize() {
        itemDetailsTextArea.setEditable(false);
//        itemDetailsTextArea.setDisable(true);
//        itemDetailsTextArea.setStyle("-fx-opacity: 1;");
    }

    public void helpBackpack(){
        itemDetailsTextArea.setText("Hátizsák feladat: \n Hátizsák probléma megoldására.");
    }

    public void helpCoin(){
        itemDetailsTextArea.setText("Pénzváltási probléma: \n Pénzváltási probléma megoldására.");
    }

    public void helpSet1(){
        itemDetailsTextArea.setText("Halmazlefedési feladat: \n Halmazlefedési probléma megoldására.");
    }

    public void helpSet2(){
        itemDetailsTextArea.setText("Halmazfelbontási feladat: \n Halmazfelbontási probléma megoldására.");
    }

}
