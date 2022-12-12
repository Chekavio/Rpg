package com.example.test1;

public class GUIParser implements InputParser {
    HelloApplication app;
    HelloController controller;

    public GUIParser(){
        this.app = new HelloApplication();
        app.lancer();

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


}
