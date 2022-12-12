package com.example.test1;

public class Demon extends Enemy {
    public Demon(String name, double damage, double health, double mana, Horde horde){
        this.name = name;
        this.damage = damage;
        this.health = health;
        this.mana = mana;
        horde.addEnemy(this);


        this.classType = "Monstre";
    }
}





