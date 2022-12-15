package com.example.test1;

import java.io.IOException;

public class GUIParser implements InputParser {
    HelloApplication app;
    Game game;

    public GUIParser(HelloApplication app, Game game){
        this.app = app;
        this.game = game;


    }


    @Override
    public int getInt() {
        return 0;
    }



    @Override
    public int getIntInRange(int min, int max) {

        return 1;
    }


    @Override
    public void printMessage(String string) {

    }

    @Override
    public void printStringBuilder(StringBuilder stringBuilder) {

    }

    @Override
    public void askEquipeNb() {
        try {
            this.app.changeScene("scene-two.fxml", this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void choiceHero(){
        try {
            this.app.changeScene("scene-three.fxml", this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public HelloApplication getApp() {
        return app;
    }

    public Game getGame() {
        return game;
    }
}
