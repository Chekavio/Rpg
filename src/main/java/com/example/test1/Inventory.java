package com.example.test1;

import java.util.ArrayList;

public class Inventory {

    ArrayList<Stuff> stuffList = new ArrayList<>();

    public void addStuff(Stuff stuff){this.stuffList.add(stuff);}

    public Stuff getStuff(int i){return this.stuffList.get(i-1);}

    public void printStuffList(InputParser inputParser){
        if(stuffList.size()==0){
            inputParser.printMessage("Vous n'avez aucun équipement");
        }else{
            inputParser.printMessage("\n\nVoici votre inventaire :\n");
            for(Stuff stuff : stuffList){
                inputParser.printMessage("("+(stuffList.indexOf(stuff) +1)+") "+ stuff.name+"| Effet : vitalité + "+ stuff.value);
        }

        }
    }
    public void useStuff(Equipe equipe, Stuff stuff, int h, int potion, InputParser inputParser){
        equipe.getHero(h).health = equipe.getHero(h).health + this.getStuff(potion).value;
        inputParser.printMessage("Votre "+equipe.getHero(h).getName()+" a gagné "+this.getStuff(potion).value+
                " points de vie !\nPoints de vie de votre héro : "+equipe.getHero(h).health);
        this.stuffList.remove(stuff);
    }
}
