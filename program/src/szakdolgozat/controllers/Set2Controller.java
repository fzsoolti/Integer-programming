package szakdolgozat.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.Arrays;

public class Set2Controller {
    @FXML
    private TextField subSetField;
    @FXML
    private Button addSet;
    @FXML
    private Button startCalculate;
    @FXML
    private Button clearAll;
    @FXML
    private Label unionLabel;
    @FXML
    private Label errorLabel;
    @FXML
    private static ObservableList<Set> subSets = FXCollections.observableArrayList();
    @FXML
    private static ObservableList<Set> output = FXCollections.observableArrayList();
    @FXML
    private static ArrayList<String> union = new ArrayList<>();
    @FXML
    private TableView<Set> subSetTable;
    @FXML
    private TableView<Set> calculatedSubs;



    @FXML
    public void initialize() {
        addSet.setDisable(true);
        startCalculate.setDisable(true);
        unionLabel.setWrapText(true);
        unionLabel.setMaxWidth(1080);
    }

    @FXML
    private void checkValues(){
        if (subSetField.getText().isEmpty() || subSetField.getText().trim().isEmpty()){
            addSet.setDisable(true);
        } else {
            addSet.setDisable(false);
        }

        if (subSets.isEmpty()){
            startCalculate.setDisable(true);
        }else {
            startCalculate.setDisable(false);
        }
    }

    @FXML
    private void addButtonClicked(){
        String subSetStr = subSetField.getText().replaceAll("\\s+","");
        ArrayList<String> elements = new ArrayList<>(Arrays.asList(subSetStr.split(",")));
        subSets.add(new Set(elements));
        subSets.get((subSets.size()-1)).setSubSetName("S"+subSets.size());
        subSetTable.setItems(subSets);

        calculateUnion(subSets);
        unionLabel.setText(String.valueOf(union));

//        CLEAR
        subSetField.setText("");
        errorLabel.setText("");
        checkValues();
    }

    private void calculateUnion(ObservableList<Set> sets){
        for (int i=0;i<sets.size();i++){
            for (int k=0;k<sets.get(i).getElements().size();k++){
                boolean isIn = false;
                if (union.isEmpty()){
                    union.add(sets.get(i).getElements().get(k));
                    isIn=true;
                }else{
                    for (int j=0;j<union.size();j++){
                        if (union.get(j).equals(sets.get(i).getElements().get(k))){
                            isIn=true;
                        }
                    }
                }
                if (!isIn){
                    union.add(sets.get(i).getElements().get(k));
                }
            }
        }
    }

    @FXML
    private void startButtonClicked(){
        output.clear();
        calculateMinSubs();
        calculatedSubs.setItems(output);
        startCalculate.setDisable(true);
        if (output.isEmpty()){
            errorLabel.setText("Az alaphalmazt nem lehet lefedni.");
        }
    }

    private static void calculateMinSubs(){
        ArrayList<String> tempList;
        ArrayList<Set> temp = new ArrayList<>();
        int minSet = 10000;

        for (int k=0;k<subSets.size();k++){
            int db=0;
            for (int j=k;j<subSets.size();j++){
                tempList =  (ArrayList<String>) union.clone();
                int l=k;
                db++;
                int setNum = 0;
                while(l<subSets.size()){
                    boolean duplicate = false;
                    for (int p=0;p<subSets.get(l).getElements().size();p++){
                        if (!duplicate){
                            for (int i = 0;i<tempList.size();i++){
                                if (subSets.get(l).getElements().get(p).equals(tempList.get(i))){
                                    duplicate = false;
                                    break;
                                } else{
                                    duplicate = true;
                                }
                            }
                        }
                    }

                    if (!duplicate){
                        tempList.removeAll(subSets.get(l).getElements());
                        temp.add(subSets.get(l));
                        setNum++;
                    }
                    
                    if (tempList.isEmpty()){
                        if (setNum<minSet){
                            minSet = setNum;
                            output = FXCollections.observableArrayList(temp);
                        }
                        temp.clear();
                        break;
                    }
                    if (l==k){
                        if (j==0){ l++; }else{ l+=db; }
                    } else{
                        l++;
                    }
                }
                temp.clear();
            }
        }
    }


    @FXML
    private void clearButtonClicked() {
        subSets.clear();
        output.clear();
        union.clear();
        checkValues();
        unionLabel.setText("");
        errorLabel.setText("");
    }


    //    ----------------SET INNER CLASS--------------------
    public static class Set {
        private String subSetName;
        private ArrayList<String> elements;

        public Set(ArrayList<String> elements) { this.elements = elements; }

        public ArrayList<String> getElements() {
            return elements;
        }

        public String getSubSetName() { return subSetName; }

        public void setSubSetName(String subSetName) {
            this.subSetName = subSetName;
        }

    }

}
