package com.example.test1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControllerChoiceHero extends Controller{
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
    private TableView<Hero> tableView;

    GUIParser parser;


    @Override
    public void initialize(GUIParser parser) {
        this.parser = parser;
        makeTable();
        //Hero hero = tableView.getSelectionModel().getSelectedItem()
    }
    public ObservableList<Hero> heroListMaker(){
        Equipe equipe = this.parser.game.getEquipe();
        new Warrior("Guyguy",1.0,1.0,1.0,equipe);
        ObservableList<Hero> list = FXCollections.observableArrayList();
        for(Combatant combatant : equipe.equipeList){
          list.add((Hero) combatant);
        }


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
}
