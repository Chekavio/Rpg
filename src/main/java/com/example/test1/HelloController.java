package com.example.test1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HelloController extends Controller{
    @FXML
    public Button mainButton;
    @FXML
    private Label welcomeText;



    @Override
    public void initialize(GUIParser parser) {
        mainButton.setOnAction(actionEvent -> {

            parser.askEquipeNb();
        });

    }

    public void setlabelText(String text){
        welcomeText.setText(text);
    }


}