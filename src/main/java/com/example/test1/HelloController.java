package com.example.test1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController extends Controller{
    @FXML
    public Button mainButton;
    @FXML
    private Label welcomeText;

    @FXML
    private TextField nbHero;
    private int compteur = 0;
    private Game game;
    private Equipe equipe;
    private Horde horde;
    private Inventory inventory;
    int level =0;
    int tour = 0;

    @FXML
    public void initialize(GUIParser parser) {

    }

    public void setlabelText(String text){
        welcomeText.setText(text);
    }


}