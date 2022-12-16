package com.example.test1;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class ControllerFight extends Controller {
    Game game;
    GUIParser parser;


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
    public TableColumn<Enemy,String> enemyName;
    @FXML
    public TableColumn<Enemy,String> enemyHealth;
    @FXML
    public TableColumn<Enemy,String> enemyDamage;
    @FXML
    public Button buttonFight;
    @FXML
    public Button buttonStuff;
    @FXML
    public Label labelRound;
    @FXML
    public Label labelAction;
    @FXML
    public TableView<Hero> tableViewHero;
    @FXML
    public TableView<Enemy> tableViewEnemy;

    @Override
    public void initialize(GUIParser parser) {
        this.game = parser.getGame();
        this.parser = parser;
        makeTableHero();
        makeTableEnemy();
        buttonFight.setOnAction(actionEvent -> {
            try {
                Hero hero = tableViewHero.getSelectionModel().getSelectedItem();
                Enemy enemy = tableViewEnemy.getSelectionModel().getSelectedItem();
                game.dealDamage(hero,enemy);
                tableViewEnemy.setItems(enemyListMaker());
                tableViewHero.setItems(heroListMaker());
                tableViewEnemy.refresh();
                tableViewHero.refresh();
                labelAction.setText("");

                if(game.getHorde().hordeList.size()==0){
                    game.upgrade(1);
                    if(game.getLevel()==4){
                        parser.win();
                    }else{
                        parser.levelUp();
                    }
                }
                if(game.getEquipe().equipeList.size()==0){
                    parser.loose();
                }

            }catch (Exception e) {
                e.printStackTrace();
                labelAction.setText("Il y a une erreur, réessayez correctement");

            }



    });
        buttonStuff.setOnAction(actionEvent -> {
            parser.Inventory();
        });

    }


    public ObservableList<Hero> heroListMaker(){
        Equipe equipe = this.parser.game.getEquipe();
        ObservableList<Hero> listHero = FXCollections.observableArrayList();
        listHero.addAll(equipe.equipeList);

        return listHero;
    }
    public ObservableList<Enemy> enemyListMaker(){
        Horde horde = this.parser.game.getHorde();
        ObservableList<Enemy> listEnemy = FXCollections.observableArrayList();
        listEnemy.addAll(horde.hordeList);

        return listEnemy;
    }

    public void makeTableHero() {
        classtype.setCellValueFactory(new PropertyValueFactory<Hero, String>("classType"));
        heroName.setCellValueFactory(new PropertyValueFactory<Hero, String>("name"));
        heroHealth.setCellValueFactory(new PropertyValueFactory<Hero, String>("health"));
        heroDamage.setCellValueFactory(new PropertyValueFactory<Hero, String>("damage"));
        heroResistance.setCellValueFactory(new PropertyValueFactory<Hero, String>("resistance"));

        tableViewHero.setItems(heroListMaker());


    }
    public void makeTableEnemy() {

        enemyName.setCellValueFactory(new PropertyValueFactory<Enemy, String>("name"));
        enemyHealth.setCellValueFactory(new PropertyValueFactory<Enemy, String>("health"));
        enemyDamage.setCellValueFactory(new PropertyValueFactory<Enemy, String>("damage"));


        tableViewEnemy.setItems(enemyListMaker());


    }

}





