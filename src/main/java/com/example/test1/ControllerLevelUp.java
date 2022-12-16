package com.example.test1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ControllerLevelUp extends Controller{
    @FXML
    public Button button;
    @FXML
    public Button buttonOne;
    @FXML
    public Label labelOne;
    @FXML
    public Label labelTwo;
    @FXML
    public Button buttonTwo;
    Game game;
    GUIParser parser;

    @Override
    public void initialize(GUIParser parser) {
        this.game = parser.getGame();
        this.parser = parser;
        upgradeSetText();
        buttonOne.setOnAction(actionEvent -> {
        int bonus =1;
            game.levelUp(game.getLevel(),game.getEquipe(), game.getInventory(), bonus);
            parser.builHorde();
        });
        buttonTwo.setOnAction(actionEvent -> {
            int bonus =2;
            game.levelUp(game.getLevel(),game.getEquipe(), game.getInventory(), bonus);
            parser.builHorde();
        });

    }
    public void upgradeSetText(){
        switch (game.getLevel()){

            case 1:
                labelOne.setText("Toute votre équipe gagne 10 points de dégats");
                labelTwo.setText("Toute votre équipe gagne 10 points de réduction de dégats");
                break;

            case 2:
                labelOne.setText("Obtenir 5 potions de heal");
                labelTwo.setText("Toute votre équipe gagne 30 points de vie");
                break;
            case 3:
                labelOne.setText("Toute votre équipe gagne 25 points d'attaque");
                labelTwo.setText("Toute votre équipe gagne 100 points de vie");
                break;
        }
    }
}


