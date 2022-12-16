package com.example.test1;

public abstract class Combatant {




    protected String classType;
    protected String name;
    protected double damage;
    protected double health;
    protected double mana;

    protected double arrow;

    protected double resistance;


    public double getHealth(){
        return health;
    }
    public double getDamage(){
        return damage;
    }

    public String getName(){
        return name;
    }

    public String getClassType() {
        return classType;
    }

    public double getResistance() {
        return resistance;
    }

    public void setHealth(double health) {
        this.health = health;
    }
}

