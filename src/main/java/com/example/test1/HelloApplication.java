package com.example.test1;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Group;

import java.io.IOException;



public class HelloApplication extends Application {


    HelloController controller;

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        this.controller = fxmlLoader.getController();
        this.controller.initialize();
        //Group root = new Group();
        //Scene scene = new Scene(root, Color.GREEN);

        //stage.setTitle("RPG");
        Image icon = new Image("iconSword.png");
        stage.getIcons().add(icon);
        stage.setX(50);
        stage.setY(50);
        stage.setWidth(640);
        stage.setHeight(480);
        stage.setResizable(false);
        stage.setFullScreen(false);
        stage.setScene(scene);
        stage.show();
    }

    public void lancer() {
        launch();
    }


}