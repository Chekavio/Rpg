package com.example.test1;

public class Warrior extends Hero {


    public Warrior(String name, double damage, double health, double resistance, Equipe equipe) {
        this.name = name;
        this.damage = damage;
        this.health = health;
        this.resistance = resistance;
        equipe.addHero(this);
        this.classType = "Warrior";
    }

    public void attack(int a, Horde horde, Equipe equipe, InputParser inputParser){
        horde.getEnemy(a).health = horde.getEnemy(a).health - this.damage;
        inputParser.printMessage("Votre "+this.getName()+" a infligé "+this.getDamage()+
                " dégats "+"à "+horde.getEnemy(a).getName()+
                ", il lui reste "+horde.getEnemy(a).getHealth()+" points de vie");
        horde.EnemyIsDeadCheck(a, horde.getEnemy(a), inputParser);


    }

    @Override
    public void attackGUI(Enemy enemy, Horde horde, Equipe equipe) {

    }

    @Override
    public void printHero(Equipe equipe, InputParser inputParser) {
        inputParser.printMessage("("+(equipe.equipeList.indexOf(this) +1)+") "+this.classType+" Nom : "+ this.getName()+"| attaque : "
                + this.getDamage()+"| vitalité : "+this.getHealth() +"| résistance : "+this.resistance);
    }

    @Override
    public void printHeroStat(Equipe equipe, InputParser inputParser) {
        inputParser.printMessage(this.classType+"| attaque : "
                + this.getDamage()+"| vitalité : "+this.getHealth() +"| résistance : "+this.resistance);
    }

}
