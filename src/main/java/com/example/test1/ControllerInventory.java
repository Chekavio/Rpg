package com.example.test1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControllerInventory extends Controller{
    GUIParser parser;
    Game game;
    @FXML
    public TableColumn<Hero,String> classtype;
    @FXML
    public TableColumn<Hero,String> heroName;
    @FXML
    public TableColumn<Hero,String> heroHealth;
    @FXML
    public TableColumn<Hero,String> heroDamage;
    @FXML
    public TableColumn<Hero,String> heroResistance;
    @FXML
    public TableColumn<Inventory,String> stuffName;
    @FXML
    public TableColumn<Inventory,String> stuffEffect;
    @FXML
    public Button button;
    @FXML
    public Button buttonExit;
    @FXML
    public TableView<Hero> tableViewHero;
    @FXML
    public Label label;
    @FXML
    public TableView<Inventory> tableViewInventory;
    @Override
    public void initialize(GUIParser parser) {
        this.game = parser.getGame();
        this.parser = parser;
        makeTableStuff();
        makeTableHero();
        button.setOnAction(actionEvent -> {
            try{
                Hero hero = tableViewHero.getSelectionModel().getSelectedItem();
                Inventory inventory = tableViewInventory.getSelectionModel().getSelectedItem();
                game.useStuff(hero, inventory);
                tableViewInventory.refresh();
                tableViewHero.refresh();
                parser.fight();
            }catch (Exception e){
                e.printStackTrace();
                label.setText("Il y a une erreur, réessayez correctement");
            }

        });
        buttonExit.setOnAction(actionEvent -> {

            parser.fight();
        });

    }


    public ObservableList<Hero> heroListMaker(){
        Equipe equipe = this.parser.game.getEquipe();
        ObservableList<Hero> listHero = FXCollections.observableArrayList();
        listHero.addAll(equipe.equipeList);

        return listHero;
    }

    public ObservableList<Inventory> stuffListMaker(){
        Inventory inventory = this.parser.game.getInventory();
        ObservableList<Inventory> listStuff = FXCollections.observableArrayList();
        listStuff.addAll(inventory.stuffList);

        return listStuff;
    }

    public void makeTableHero() {
        classtype.setCellValueFactory(new PropertyValueFactory<Hero, String>("classType"));
        heroName.setCellValueFactory(new PropertyValueFactory<Hero, String>("name"));
        heroHealth.setCellValueFactory(new PropertyValueFactory<Hero, String>("health"));
        heroDamage.setCellValueFactory(new PropertyValueFactory<Hero, String>("damage"));
        heroResistance.setCellValueFactory(new PropertyValueFactory<Hero, String>("resistance"));

        tableViewHero.setItems(heroListMaker());
    }

    public void makeTableStuff() {
        stuffName.setCellValueFactory(new PropertyValueFactory<Inventory, String>("name"));
        stuffEffect.setCellValueFactory(new PropertyValueFactory<Inventory, String>("value"));

        tableViewInventory.setItems(stuffListMaker());
    }

}
