package com.example.test1;

public class Potion extends Stuff{
    public Potion(String name, double value, Inventory inventory) {
        this.name = name;
        this.value = value;
        inventory.addStuff(this);
    }
}
