package com.example.test1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private Button button;

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
    protected void initialize() {
       // button.setOnMouseClicked(mouseEvent -> {
         //   welcomeText.setText(String.valueOf(compteur));
           // compteur++;}
       // );

  //  button.setOnAction(game.fight(equipe,horde,tour, inventory));
    }

    public void setlabelText(String text){
        welcomeText.setText(text);
    }


}