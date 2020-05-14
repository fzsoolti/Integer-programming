package szakdolgozat.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class BackpackController {
    @FXML
    private TextField itemNameField;
    @FXML
    private TextField itemWeightField;
    @FXML
    private TextField itemUsefulnessField;
    @FXML
    private TextField backpackCapacityField;
    @FXML
    private Button addItem;
    @FXML
    private Button startCalculate;
    @FXML
    private Label capacityLabel;
    @FXML
    private Label sumWeightLabel;
    @FXML
    private Label usedCapacityLabel;
    @FXML
    private TableView<Item> itemTable;
    @FXML
    private TableView<Item> calculatedItemTable;
    @FXML
    private ObservableList<Item> items = FXCollections.observableArrayList();
    @FXML
    private static ObservableList<Item> calculatedItems = FXCollections.observableArrayList();


    @FXML
    public void initialize() {
        addItem.setDisable(true);
        startCalculate.setDisable(true);
    }


//    ----------------Add new items--------------------
    @FXML
    private void addButtonClicked() {
        String itemName = itemNameField.getText();
        float itemWeight = Float.parseFloat(itemWeightField.getText());
        int itemUsefulness = Integer.parseInt(itemUsefulnessField.getText());
        Item newItem = new Item(itemName, itemWeight, itemUsefulness);
        items.add(newItem);
        itemTable.setItems(items);
        float sumWeight=sumWeight(items);
        sumWeightLabel.setText(String.valueOf(sumWeight)+" kg");

        //  CLEAR TEXTFIELDS--------------------
        itemNameField.setText("");
        itemWeightField.setText("");
        itemUsefulnessField.setText("");
        ButtonsValueCheck();
    }


//    ----------------Calculate the best items--------------------
    @FXML
    private void startButtonClicked() {
        calculatedItems.clear();
        int capacity = Integer.parseInt(backpackCapacityField.getText());
        capacityLabel.setText(String.valueOf(capacity)+" kg");
        calculateBestItems(items,capacity);
        calculatedItemTable.setItems(calculatedItems);
        float sumWeight=sumWeight(calculatedItems);
        usedCapacityLabel.setText(String.valueOf(sumWeight)+" kg");
        backpackCapacityField.setText("");
        startCalculate.setDisable(true);
    }

    //    ----------------Add new items--------------------
    @FXML
    private void clearButtonClicked() {
        items.clear();
        calculatedItems.clear();
        ButtonsValueCheck();
        capacityLabel.setText("");
        sumWeightLabel.setText("");
        usedCapacityLabel.setText("");
    }

//    ----------------Check the values--------------------
    @FXML
    private void ButtonsValueCheck(){
        boolean setAddDisableButton = true;
        boolean setStartDisableButton = true;
        if (isNumericFloat(itemWeightField.getText()) && isNumericInt(itemUsefulnessField.getText())){
            float itemWeight = Float.parseFloat(itemWeightField.getText());
            int itemUsefulness = Integer.parseInt(itemUsefulnessField.getText());
            String itemName = itemNameField.getText();
            setAddDisableButton = ((itemName.isEmpty() || itemName.trim().isEmpty())
                    || (itemWeight<0.1)
                    || (itemUsefulness<1 || itemUsefulness>10) );
        }

        if ( isNumericInt(backpackCapacityField.getText())){
            int backpackCapacity = Integer.parseInt(backpackCapacityField.getText());
            if ((!(items.isEmpty())) && backpackCapacity>=1){
                setStartDisableButton = false;
            }
        }

        startCalculate.setDisable(setStartDisableButton);
        addItem.setDisable(setAddDisableButton);
    }

    private static float sumWeight(ObservableList<Item> items){
        float sum = 0;
        for (int i = 0; i < items.size(); i++) {
            sum += items.get(i).getItemWeight();
        }
        return sum;
    }

    private static void calculateBestItems(ObservableList<Item> items, int capacity){
//        Algorithm
        int NB_ITEMS = items.size();
        int[][] matrix = new int[NB_ITEMS + 1][capacity + 1];

        for (int i = 0; i <= capacity; i++)
            matrix[0][i] = 0;

        for (int i = 1; i <= NB_ITEMS; i++) {
            for (int j = 0; j <= capacity; j++) {
                if (items.get(i - 1).getItemWeight() > j)
                    matrix[i][j] = matrix[i-1][j];
                else
                    matrix[i][j] = Math.max(matrix[i-1][j], matrix[i-1][(int) (j - items.get(i - 1).getItemWeight())]
                            + items.get(i - 1).getItemUsefulness());
            }
        }

//        Find items
        int res = matrix[NB_ITEMS][capacity];
        int w = capacity;

        for (int i = NB_ITEMS; i > 0  &&  res > 0; i--) {
            if (res != matrix[i-1][w]) {
                calculatedItems.add(items.get(i - 1));
                res -= items.get(i - 1).getItemUsefulness();
                w -= items.get(i - 1).getItemWeight();
            }
        }
    }

    //    ----------------Check float value--------------------
    public static boolean isNumericFloat(final String str) {

        if (str == null || str.length() == 0) {
            return false;
        }
        try {
            Float.parseFloat(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    //    ----------------Check int value--------------------
    public static boolean isNumericInt(final String str) {

        if (str == null || str.length() == 0) {
            return false;
        }
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

//    ----------------ITEM INNER CLASS--------------------
    public static class Item {
        private String itemName;
        private float itemWeight;
        private int itemUsefulness;

        public Item(String itemName, float itemWeight, int itemUsefulness) {
            this.itemName = itemName;
            this.itemWeight = itemWeight;
            this.itemUsefulness = itemUsefulness;
        }

        public float getItemWeight() {
            return itemWeight;
        }

        public int getItemUsefulness() {
            return itemUsefulness;
        }

        public String getItemName() {
            return itemName;
        }

    }

}
