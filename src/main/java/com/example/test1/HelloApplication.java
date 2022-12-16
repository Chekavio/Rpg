package com.example.test1;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.Group;
import java.io.IOException;
import javafx.scene.Parent;



public class HelloApplication extends Application {

    FXMLLoader fxmlLoader;
    Controller controller;
    Stage stage;


    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        this.fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        stage.setTitle("RPG");
        Image icon = new Image("iconSword.png");
        stage.getIcons().add(icon);

        Game game = new Game(this);
        GUIParser parser = new GUIParser(this,game);
        game.setInputparser(parser);
        Controller controller = this.fxmlLoader.getController();
        controller.initialize(parser);


        stage.setX(50);
        stage.setY(50);
        stage.setWidth(710);
        stage.setHeight(480);
        stage.setResizable(false);
        stage.setFullScreen(false);
        stage.setScene(scene);
        stage.show();

    }

    public void changeScene(String fxmlFile, GUIParser parser) throws IOException {
        this.fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxmlFile));
        Parent root = this.fxmlLoader.load();
        this.controller = this.fxmlLoader.getController();

        controller.initialize(parser);

        Scene scene = new Scene(root, 320, 240);
        this.stage.setScene(scene);
         this.stage.show();
    }
    public static void lancer() {
        launch();
    }

    public static void main(String[] args) {
        Game.welcome();

    }
    public FXMLLoader getFxmlLoader() {
        return fxmlLoader;
    }

}