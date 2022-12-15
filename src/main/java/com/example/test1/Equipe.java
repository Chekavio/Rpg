package com.example.test1;

import java.util.ArrayList;

public class Equipe {

    public ArrayList<Hero> equipeList = new ArrayList<>();


    public void addHero(Hero hero) {
        this.equipeList.add(hero);
    }

    public Hero getHero(int i){
        return this.equipeList.get(i-1);
    }


    public void printHeroList(Equipe equipe, InputParser inputParser){
        for(Hero hero : equipeList){
            hero.printHero(equipe, inputParser);
        }
    }
    public void HeroIsDeadCheck(int i, Hero hero, InputParser inputParser){
        if(this.getHero(i).getHealth()<0){
            this.equipeList.remove(hero);
            inputParser.printMessage("Votre hero est mort");

        }

    }




    public void reload(){
        for(Hero hero : this.equipeList){
            if(hero instanceof Hunter){
                hero.arrow += 0.5;
            }
            if(hero instanceof Mage || hero instanceof Healer){
                hero.mana += 5;
            }
        }


}


}




