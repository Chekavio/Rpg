package com.example.test1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControllerChoiceHero extends Controller{
    Game game;
    GUIParser parser;

    @FXML
    public TextField classField;
    @FXML
    public TextField nameField;
    @FXML
    public Button button;
    @FXML
    public Label myLabel;
    @FXML
    public TableColumn<Hero,String> classType;
    @FXML
    public TableColumn<Hero,String> name;
    @FXML
    public TableColumn<Hero,String> health;
    @FXML
    public TableColumn<Hero,String> damage;
    @FXML
    public TableColumn<Hero,String> restistance;

    @FXML
    public TableView<Hero> tableView;

    int herocompteur = 0;


    @Override
    public void initialize(GUIParser parser) {
        this.game = parser.getGame();
        this.parser = parser;
        button.setOnAction(actionEvent -> {

            try {
                heroParameters();
                makeTable();

                if(game.getNombreHero()==game.getEquipe().equipeList.size()){
                    parser.fight();
                }

            }
           catch (NumberFormatException e){
                myLabel.setText("Il y a une erreur, réessayez correctement");


            }catch (Exception e) {
                e.printStackTrace();
                myLabel.setText("Il y a une erreur, réessayez correctement");

            }

        });
    }
    public ObservableList<Hero> heroListMaker(){
        Equipe equipe = this.parser.game.getEquipe();
        ObservableList<Hero> list = FXCollections.observableArrayList();
        list.addAll(equipe.equipeList);

       return list;

    }

    public void makeTable(){
        classType.setCellValueFactory(new PropertyValueFactory<Hero, String>("classType"));
        name.setCellValueFactory(new PropertyValueFactory<Hero, String>("name"));
        health.setCellValueFactory(new PropertyValueFactory<Hero, String>("health"));
        damage.setCellValueFactory(new PropertyValueFactory<Hero, String>("damage"));
        restistance.setCellValueFactory(new PropertyValueFactory<Hero, String>("resistance"));

        tableView.setItems(heroListMaker());



    }


    public void heroParameters(){
        int classe = Integer.parseInt(classField.getText());
        String name = nameField.getText();
        if(classe <= 4 && classe>=1) {
            game.setClasse(classe);
            game.setName(name);
            game.heroCreation(classe, name);

        }
        else {
            myLabel.setText("Selectionnez un chiffre entre 1 et 4");
        }

    }


}




