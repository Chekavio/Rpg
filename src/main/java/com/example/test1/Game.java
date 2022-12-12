package com.example.test1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Game {
    InputParser inputparser = new ConsoleParser();

    Scanner scan = new Scanner(System.in);


    public int askEquipeNb() {
        inputparser.printMessage("Combien de heros voulez-vous dans votre equipe ?");
        int nombreHero = inputparser.getIntInRange(1,10);
        return nombreHero;

    }


    public void choiceHero(Equipe equipe, Inventory inventory, int nombreHero) {
        String nom;

        for(int i = 0; i<nombreHero; i++){
            StringBuilder sb = new StringBuilder(String.format("%nQuel type de hero voulez vous pour le hero numero %d: ", i+1));
            List<String> heroes = List.of("Warrior", "Hunter", "Healer", "Mage");
            for (int j = 0; j < heroes.size(); j++) {
                sb.append(String.format("(%d) %s ", j+1, heroes.get(j)));
            }
            inputparser.printStringBuilder(sb);
            int choix = inputparser.getIntInRange(1,4);
            inputparser.printMessage("Quel nom voulez-vous pour votre hero");
            nom = scan.nextLine();
            new Potion("Potion de soin", 20, inventory);
            switch (choix){
                case 1:
                    new Warrior(nom,20.0,100.0, 0, equipe);
                    break;

                case 2:
                    new Hunter(nom,10.0,100.0,0, equipe,2.0);
                    break;

                case 3:
                    new Healer(nom,5.0,100.0,30, 0, equipe);
                    break;


                case 4:
                    new Mage(nom,25.0,100.0,30,0, equipe);
                    break;

            }

        }
    }

    public void buildHorde(Horde horde, int nombreHero, int level){
        for(int i=0; i<nombreHero; i++){
            switch (level){
                case 0:
                    new Demon("Diablotin",10,50,0,horde);
                    break;

                case 1:
                    new Demon("Demon",15,100,0,horde);
                    break;

                case 2:
                    new Demon("Ange déchu",20,150,0,horde);
                    break;

                case 3:
                    new Demon("Chevalier de la mort",35,200,0,horde);
                    break;
            }

        }

    }



    public void startBattle(Horde horde){

        inputparser.printMessage("""

                \n\nUne horde de monstre arrive ! ...
                Voulez-vous l'affronter?
                
                (1) Affronter la horde
                (2) Fuir !""");

        int battle = inputparser.getInt();
        if (battle == 1) {
            inputparser.printMessage("La horde vous attaque !");
            horde.printEnemyStat(inputparser);

        } else {
            inputparser.printMessage("Le jeu prend fin");
            System.exit(1);
        }
    }

    public void levelUp(int level, Equipe equipe, Inventory inventory){
        switch (level){

            case 1:
                inputparser.printMessage("Vous passez au niveau supérieur !\nVeuillez choisir un bonus :" +
                "\n(1) Toute votre équipe gagne 10 points de dégats\n(2) Toute votre équipe gagne 10 points de réduction de dégats");
                boolean choice = false;
                while (!choice){
                    int bonus = inputparser.getIntInRange(1,2);
                    switch (bonus){
                        case 1:
                            for(int h =1; h<(equipe.equipeList.size()+1); h++){
                                equipe.getHero(h).damage += 10;
                                choice = true;
                            }
                            break;
                        case 2:
                            for(int h =1; h<(equipe.equipeList.size()+1); h++){
                                equipe.getHero(h).resistance += 10;
                                choice = true;
                            }
                            break;
                    }
                }

                break;
            case 2:
                inputparser.printMessage("Vous passez au niveau supérieur !\nVeuillez choisir un bonus :" +
                        "\n(1) Obtenir 5 potions de heal\n(2) Toute votre équipe gagne 30 points de vie");
                int bonus = inputparser.getIntInRange(1,2);
                switch (bonus){
                    case 1:
                        for(int h=0; h<5; h++){
                            new Potion("Potion de soin", 20, inventory);
                        }
                        break;
                    case 2:
                        for(int h =1; h<(equipe.equipeList.size()+1); h++){
                            equipe.getHero(h).health += 30;
                        }
                        break;
                }
                break;
            case 3:
                inputparser.printMessage("Vous passez au niveau supérieur !\nVeuillez choisir un bonus :" +
                        "\n(1) Toute votre équipe gagne 25 points d'attaque\n(2) Toute votre équipe gagne 100 points de vie");
                bonus = inputparser.getIntInRange(1,2);
                switch (bonus){
                    case 1:
                        for(int h =1; h<(equipe.equipeList.size()+1); h++){
                            equipe.getHero(h).damage += 25;
                        }
                        break;
                    case 2:
                        for(int h =1; h<(equipe.equipeList.size()+1); h++){
                            equipe.getHero(h).health += 100;
                        }
                        break;
                }
                break;
        }
    }


    public void endGame(int level) {
        if(level == 4){
            inputparser.printMessage("Bravo vous avez vaincu toutes les hordes de monstre !\nLe jeu prend fin");
            System.exit (1);
        }

    }



    public void fight(Equipe equipe, Horde horde, int tour, Inventory inventory) {
        ArrayList<Combatant> allCombatant = new ArrayList<>();
        Random random = new Random();
        boolean fight = true;
        while(fight){

            if(allCombatant.size()==0){
                for(int i=1;i<equipe.equipeList.size()+1;i++){
                    allCombatant.add(equipe.getHero(i));
                }
                for(int i=1;i<horde.hordeList.size()+1;i++){
                    allCombatant.add(horde.getEnemy(i));
                }
            }

            tour = tour + 1;
            inputparser.printMessage("\nTour numero : "+tour);
            inputparser.printMessage("\n(1) Attaquer | (2) Voir son inventaire");



            int choice = inputparser.getIntInRange(1,2);
            switch (choice){
                case 1 :

                    int perso = random.nextInt(allCombatant.size());
                    inputparser.printMessage("C'est le tour de : "+allCombatant.get(perso).getName());

                    if(allCombatant.get(perso) instanceof Enemy){
                        horde.attack(equipe, inputparser);
                        allCombatant.remove(allCombatant.remove(perso));
                        if(equipe.equipeList.size()==0){
                            inputparser.printMessage("Tout vos héros sont mort : GAME OVER");
                            System.exit(1);
                        }

                    }
                    else {
                        ((Hero) allCombatant.get(perso)).printHeroStat(equipe, inputparser);
                        inputparser.printMessage("Qui voulez-vous attaquer?");
                        horde.printEnemyList(inputparser);
                        int target = inputparser.getIntInRange(1,horde.hordeList.size());

                        ((Hero) allCombatant.get(perso)).attack(target, horde, equipe, inputparser);
                        allCombatant.remove(allCombatant.get(perso));

                        if(horde.hordeList.size()==0){
                            fight = false;
                        }
                        equipe.reload();
                    }


                    break;
                case 2:
                    tour--;
                    inventory.printStuffList(inputparser);
                    inputparser.printMessage("\n(1) Utiliser une potion | (2) Revenir en arrière");
                    choice = inputparser.getIntInRange(1,2);
                    if (choice == 1) {
                        inventory.printStuffList(inputparser);
                        inputparser.printMessage("\nQuelle potion voulez-vos utiliser?\n");
                        int potion = inputparser.getIntInRange(1,inventory.stuffList.size());

                        inputparser.printMessage("\nSur qui voulez-vous l'utiliser?");
                        equipe.printHeroList(equipe, inputparser);
                        int h = inputparser.getIntInRange(1, equipe.equipeList.size());

                        inventory.useStuff(equipe, inventory.getStuff(potion), h, potion, inputparser);
                        break;


                    } else {
                        break;
                    }
            }


        }

    }



    public  void start(){

        inputparser.printMessage("Bienvenue sur ce RPG\n");
        Scanner scan = new Scanner(System.in);

        inputparser.printMessage("Voulez vous jouer sur la console ou avec l'interface graphique?" +
                "\n(1)Console\n(2)Interface graphique");
        int gameint = inputparser.getIntInRange(1,2);
        if (gameint == 2){
            inputparser = new GUIParser();

        }


        int nombreHero = askEquipeNb();

        Equipe equipe = new Equipe();
        Horde horde = new Horde();
        Inventory inventory = new Inventory();
        Hero hero = new Hero() {


            @Override
            public void attack(int a, Horde horde, Equipe equipe, InputParser inputParser) {

            }

            @Override
            public void printHero(Equipe equipe, InputParser inputParser) {

            }

            @Override
            public void printHeroStat(Equipe equipe, InputParser inputParser) {

            }


        };

        //Initialition
        int level =0;
        int tour = 0;

        choiceHero(equipe, inventory, nombreHero);
        inputparser.printMessage("\n\nVoici votre équipe : ");
        equipe.printHeroList(equipe, inputparser);


        for (int currentLevel = 0; currentLevel <= 3; currentLevel++) {

                buildHorde(horde, nombreHero, level);
                startBattle(horde);
                fight(equipe, horde, tour, inventory);
                level ++;
                endGame(level);
                levelUp(level, equipe, inventory);


        }



    }


}
