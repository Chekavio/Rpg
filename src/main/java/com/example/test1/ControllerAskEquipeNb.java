package com.example.test1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.Background;

import java.util.function.UnaryOperator;

public class ControllerAskEquipeNb extends Controller{

    Game game;
    @FXML
    public Label nbHeroLabel;
    @FXML
    public TextField nbHeroField;
    @FXML
    public Label myLabel;
    @FXML
    public Button button;


    @Override
    public void initialize(GUIParser parser) {
        this.game = parser.getGame();

        button.setOnAction(actionEvent -> {

            try {
                getHeroNumber(parser);
            }
            catch (NumberFormatException e){
                myLabel.setText("enter only numbers please");
            }
            catch (Exception e) {
                e.printStackTrace();
            }

        });


    }
    public void getHeroNumber(GUIParser parser){
        int nombreHero = Integer.parseInt(nbHeroField.getText());
        if(nombreHero <= 4 && nombreHero>=1) {
            game.setNbHero(nombreHero);
            parser.choiceHero();



        }
        else {
            myLabel.setText("Votre équipe doit être composée de 1 à 4 héros");
        }
    }


}


