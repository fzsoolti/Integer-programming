package szakdolgozat.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    @FXML
    private Button btnBackPack;
    @FXML
    private Button btnCoin;
    @FXML
    private Button btnSet_1;
    @FXML
    private Button btnSet_2;
    @FXML
    private Button btnHelp;

    @FXML
    private void handleButtonClicks(javafx.event.ActionEvent mouseEvent) {
        if (mouseEvent.getSource() == btnBackPack) {
            loadStage("/szakdolgozat/fxml/backpack.fxml");
        } else if(mouseEvent.getSource() == btnCoin){
            loadStage("/szakdolgozat/fxml/coin.fxml");
        } else if(mouseEvent.getSource() == btnSet_1){
            loadStage("/szakdolgozat/fxml/set1.fxml");
        } else if(mouseEvent.getSource() == btnSet_2){
            loadStage("/szakdolgozat/fxml/set2.fxml");
        } else if(mouseEvent.getSource() == btnHelp){
            loadStage("/szakdolgozat/fxml/help.fxml");
        }
    }

    private void loadStage(String fxml) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxml));
            Stage stage = new Stage();
            stage.setScene(new Scene(root,1280, 720));
            stage.getIcons().add(new Image("/szakdolgozat/images/icon.png"));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setMinWidth(880);
            stage.setMinHeight(620);
            stage.setMaxHeight(900);
            stage.setMaxWidth(1400);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
