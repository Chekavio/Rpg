package com.example.test1;

public class Stuff extends Inventory{

    public void useStuffGUI(Hero hero, Inventory inventory){
        hero.health = hero.health + this.value;

        inventory.stuffList.remove(inventory);
    }

}

