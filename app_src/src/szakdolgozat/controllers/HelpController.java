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
        itemDetailsTextArea.setText("Hátizsák probléma megoldására. Az algoritmus csak akkor indítható, ha van már hozzáadott tárgy.\n " +
                "Tárgy csak akkor adható hozzá, ha: \n " +
                "  - a 'Tárgy neve' mező nem üres \n" +
                "  - a tárgy súlya pozitív szám\n" +
                "  - a tárgy hasznossága egy és tíz közé eső egész szám");
    }

    public void helpCoin(){
        itemDetailsTextArea.setText("Pénzváltási probléma megoldására. Adott összeg kifizetése minimális címlettel (Forint) \n" +
                "Az algoritmus csak helyes input megadása esetén indítható, a: \n" +
                "  - mimimum: 3,\n" +
                "  - maximum: 2 147 483 647,\n" +
                "közé eső egész számok esetén.");
    }

    public void helpSet1(){
        itemDetailsTextArea.setText("Halmazlefedési probléma megoldására. Az algoritmusok csak akkor indíthatóak, ha van már hozzáadott halmaz.\n" +
                "Halmaz csak akkor adható hozzá, ha: \n" +
                "  - a részhalmazok vesszővel elválasztva vannak megadva,\n" +
                "  - ki van választva egy algoritmus");
    }

    public void helpSet2(){
        itemDetailsTextArea.setText("Halmazfelbontási probléma megoldására. Az algoritmus csak akkor indítható, ha van már hozzáadott halmaz.\n" +
                "Halmaz csak akkor adható hozzá, ha: \n" +
                "  - a részhalmazok vesszővel elválasztva vannak megadva");
    }

}
