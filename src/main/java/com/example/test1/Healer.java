package com.example.test1;

import java.util.Random;
public class Healer extends SpellCaster {

    public Healer(String name, double damage, double health, double mana, double resistance, Equipe equipe){
        this.name = name;
        this.damage = damage;
        this.health = health;
        this.mana = mana;
        this.resistance = resistance;
        equipe.addHero(this);

        this.classType = "Healer";
    }



    @Override
    public boolean hasMana() {

        return true;
    }

    @Override
    public void attack(int a, Horde horde, Equipe equipe, InputParser inputParser) {
        hasMana();
        Random random = new Random();
        if(hasMana()){
            horde.getEnemy(a).health = horde.getEnemy(a).health - this.damage;
            int heal = random.nextInt(equipe.equipeList.size()) + 1;
            equipe.getHero(heal).health = equipe.getHero(heal).health + 4*this.damage;
            this.mana = this.mana-10;
            inputParser.printMessage("Votre "+this.getName()+" a infligé "+this.getDamage()+
                    " dégats "+"à "+horde.getEnemy(a).getName()+
                    ", il lui reste "+horde.getEnemy(a).getHealth()+" points de vie\n" +
                    "De plus il a soigné "+4*this.damage+" points de vie à "+equipe.getHero(heal).getName()+" il passe à "+equipe.getHero(heal).health+"pv");
            horde.EnemyIsDeadCheck(a, horde.getEnemy(a), inputParser);


        }else{
            inputParser.printMessage("\nVous n'avez plus de mana !\nSelectionnez un autre héro");
        }


        }

    @Override

    public void printHero(Equipe equipe, InputParser inputParser){
        inputParser.printMessage("("+(equipe.equipeList.indexOf(this) +1)+") "+this.classType+" Nom : "+ this.getName()+"| attaque : "
                + this.getDamage()+"| vitalité : "+this.getHealth() +"| résistance : "+this.resistance+"| mana : "+this.mana);

    }

    @Override
    public void printHeroStat(Equipe equipe, InputParser inputParser) {
        inputParser.printMessage(this.classType+"| attaque : "+ this.getDamage()+"| vitalité : "+this.getHealth() +
                "| résistance : "+this.resistance+"| mana : "+this.mana);

    }

}



