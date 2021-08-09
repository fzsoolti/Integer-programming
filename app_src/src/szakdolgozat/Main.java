package szakdolgozat;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("fxml/main.fxml"));

        primaryStage.setTitle("Egészértékű programozási feladatok");
        primaryStage.getIcons().add(new Image("/szakdolgozat/images/icon.png"));
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.setMinWidth(880);
        primaryStage.setMinHeight(620);
        primaryStage.setMaxHeight(900);
        primaryStage.setMaxWidth(1400);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
