package szakdolgozat.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.text.NumberFormat;
import java.util.Locale;

public class CoinController {

    private static final int[] BILLS = {20000,10000,5000,2000,1000,500,200,100,50,20,10,5};
    @FXML
    private static ObservableList<Bill> bills = FXCollections.observableArrayList();
    @FXML
    private Label currencyLabel;
    @FXML
    private Label quantityLabel;
    @FXML
    private Button startCalculate;
    @FXML
    private TextField coinTextfieldInput;
    @FXML
    private TableView<Bill> calculatedItemTable;

    @FXML
    public void initialize() {
        startCalculate.setDisable(true);
    }

    @FXML
    private void checkValues(){
        quantityLabel.setText("");
        boolean setStartDisableButton = true;
        if (BackpackController.isNumericInt(coinTextfieldInput.getText())){
            int currency = Integer.parseInt(coinTextfieldInput.getText());
            setStartDisableButton = (currency<3 || currency>2147483647);
            currencyLabel.setText(NumberFormat.getCurrencyInstance(new Locale("hu", "HU")).format(currency));
        }else if(coinTextfieldInput.getText().isEmpty()){
            currencyLabel.setText("0 Ft");
        }
        startCalculate.setDisable(setStartDisableButton);
    }

    @FXML
    private void startCalculateButtonClicked(){
        bills.clear();
        int currency = Integer.parseInt(coinTextfieldInput.getText());
        calculateBills(BILLS,Round(currency));
        calculatedItemTable.setItems(bills);
        int quantity = sumQuantity(bills);

        coinTextfieldInput.setText("");
        checkValues();
        currencyLabel.setText(String.valueOf(NumberFormat.getCurrencyInstance(new Locale("hu", "HU")).format(currency)));
        quantityLabel.setText(String.valueOf(quantity + " db"));
    }

    private static void calculateBills(int[] BILLS, int currency){
        int amount=0;
        int quantity = 0;
        while(amount!=currency){
            for (int i = 0; i < BILLS.length;i++){
                while (BILLS[i]+amount <= currency){
                    amount+=BILLS[i];
                    quantity++;
                }
                if (quantity!=0){
                    Bill bill = new Bill(BILLS[i],quantity);
                    bills.add(bill);
                }
                quantity=0;
            }
        }
    }

    private static int Round (int currency){
        int out;
        int temp = currency%5;
        if (temp < 3){
            out=currency-temp;
        } else{
            out=currency+(5-temp);
        }
        return out;
    }

    private static int sumQuantity(ObservableList<Bill> bills){
        int quantity = 0;
        for (int i = 0; i < bills.size(); i++) {
            quantity += bills.get(i).getQuantity();
        }
        return quantity;
    }

//    ----------------ITEM INNER CLASS--------------------
    public static class Bill {
        private int currency;
        private int quantity;

        public Bill(int currency, int quantity) {
            this.currency = currency;
            this.quantity = quantity;
        }

        public int getCurrency() {
            return currency;
        }

        public int getQuantity() {
            return quantity;
        }

    }



}
