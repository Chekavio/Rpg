package com.example.test1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Game {
    InputParser inputparser = new ConsoleParser(this);
    Scanner scan = new Scanner(System.in);

    private Equipe equipe= new Equipe();
    private Inventory inventory = new Inventory();
    private Stuff stuff = new Stuff();
    private Horde horde = new Horde();
    private HelloApplication app;

    public int classe;
    public int nombreHero;
    public int level;
    public int tour;
    public String name;

    public Game(){

    }
    public Equipe getEquipe() {
        return equipe;
    }
    public Horde getHorde() {
        return horde;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setClasse(int classe) {
        this.classe = classe;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public int getNombreHero() {return nombreHero;}
    public void setNbHero(int nombreHero){this.nombreHero = nombreHero;}
    public int getLevel() {return level;}
    public void setInputparser(InputParser inputparser) {
        this.inputparser = inputparser;
    }

    public Game(HelloApplication app){
        this.app = app;
    }

    public void upgrade(int i){
        this.level=level+1;
    }

    public static void welcome(){
        System.out.println("Bienvenue sur ce RPG\n");

        System.out.println("Voulez vous jouer sur la console ou avec l'interface graphique?" +
                "\n(1)Console\n(2)Interface graphique");
        Scanner sc = new Scanner(System.in);
        int gameint = sc.nextInt();
        if (gameint == 2){
            HelloApplication.lancer();

        }else{
            Game game = new Game();
            game.start2();
        }

    }

    public void start2(){
        this.inputparser.askEquipeNb();
    }

    public void choiceHero(Equipe equipe, Inventory inventory) {
        String nom;


        for(int i = 0; i<nombreHero; i++){
            StringBuilder sb = new StringBuilder(String.format("%nQuel type de hero voulez-vous pour le hero numero %d: ", i+1));
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
        inputparser.printMessage("\n\nVoici votre ??quipe : ");
        equipe.printHeroList(equipe, inputparser);
        buildHorde(nombreHero, level);
        fight(equipe, horde, tour, inventory);


    }


    public void buildHorde(int nombreHero, int level){
        for(int i=0; i<nombreHero; i++){
            switch (level){
                case 0:
                    new Demon("Diablotin",10,50,0,horde);
                    break;

                case 1:
                    new Demon("Demon",15,100,0,horde);
                    break;

                case 2:
                    new Demon("Ange d??chu",20,150,0,horde);
                    break;

                case 3:
                    new Demon("Chevalier de la mort",35,200,0,horde);
                    break;
            }

        }

    }

    public void levelUp(int level, Equipe equipe, Inventory inventory, int bonus){
        switch (level){

            case 1:
                inputparser.printMessage("Vous passez au niveau sup??rieur !\nVeuillez choisir un bonus :" +
                "\n(1) Toute votre ??quipe gagne 10 points de d??gats\n(2) Toute votre ??quipe gagne 10 points de r??duction de d??gats");
                boolean choice = false;
                while (!choice){
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
                inputparser.printMessage("Vous passez au niveau sup??rieur !\nVeuillez choisir un bonus :" +
                        "\n(1) Obtenir 5 potions de heal\n(2) Toute votre ??quipe gagne 30 points de vie");
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
                inputparser.printMessage("Vous passez au niveau sup??rieur !\nVeuillez choisir un bonus :" +
                        "\n(1) Toute votre ??quipe gagne 25 points d'attaque\n(2) Toute votre ??quipe gagne 100 points de vie");
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
    public void levelUpConsole(Equipe equipe, Inventory inventory){
        switch (level){

            case 1:
                inputparser.printMessage("Vous passez au niveau sup??rieur !\nVeuillez choisir un bonus :" +
                        "\n(1) Toute votre ??quipe gagne 10 points de d??gats\n(2) Toute votre ??quipe gagne 10 points de r??duction de d??gats");

                    int bonus = inputparser.getIntInRange(1,2);
                    switch (bonus){
                        case 1:
                            for(int h =1; h<(equipe.equipeList.size()+1); h++){
                                equipe.getHero(h).damage += 10;

                            }
                            break;
                        case 2:
                            for(int h =1; h<(equipe.equipeList.size()+1); h++){
                                equipe.getHero(h).resistance += 10;

                            }
                            break;
                    }


                break;
            case 2:
                inputparser.printMessage("Vous passez au niveau sup??rieur !\nVeuillez choisir un bonus :" +
                        "\n(1) Obtenir 5 potions de heal\n(2) Toute votre ??quipe gagne 30 points de vie");
                bonus = inputparser.getIntInRange(1,2);
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
                inputparser.printMessage("Vous passez au niveau sup??rieur !\nVeuillez choisir un bonus :" +
                        "\n(1) Toute votre ??quipe gagne 25 points d'attaque\n(2) Toute votre ??quipe gagne 100 points de vie");
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
        buildHorde(nombreHero,level);
        fight(equipe,horde,tour,inventory);
    }


    public void gameCheck(int level) {
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
            switch (choice) {
                case 1 -> {
                    int perso = random.nextInt(allCombatant.size());
                    inputparser.printMessage("C'est le tour de : " + allCombatant.get(perso).getName());
                    if (allCombatant.get(perso) instanceof Enemy) {
                        Hero hero = equipe.getHero(1);
                        horde.attack(hero, equipe, inputparser);
                        allCombatant.remove(allCombatant.remove(perso));
                        if (equipe.equipeList.size() == 0) {
                            inputparser.printMessage("Tout vos h??ros sont mort : GAME OVER");
                            System.exit(1);
                        }

                    } else {
                        ((Hero) allCombatant.get(perso)).printHeroStat(equipe, inputparser);
                        inputparser.printMessage("Qui voulez-vous attaquer?");
                        horde.printEnemyList(inputparser);
                        int target = inputparser.getIntInRange(1, horde.hordeList.size());

                        ((Hero) allCombatant.get(perso)).attack(target, horde, equipe, inputparser);
                        allCombatant.remove(allCombatant.get(perso));

                        if (horde.hordeList.size() == 0) {
                            gameCheck(level);
                            level++;
                            levelUpConsole(equipe, inventory);
                            fight = false;
                        }
                        equipe.reload();
                    }
                }
                case 2 -> {
                    tour--;
                        inventory.printStuffList(inputparser);
                        inputparser.printMessage("\nQuelle potion voulez-vos utiliser?\n");
                        int potion = inputparser.getIntInRange(1, inventory.stuffList.size());

                        inputparser.printMessage("\nSur qui voulez-vous l'utiliser?");
                        equipe.printHeroList(equipe, inputparser);
                        int h = inputparser.getIntInRange(1, equipe.equipeList.size());

                        inventory.useStuff(equipe, inventory.getStuff(potion), h, potion, inputparser);



                    }
                }
            }


        }



    void heroCreation(int classe, String name){
        new Potion("Potion", 20, inventory);
        switch (classe){
            case 1:
                new Warrior(name,20.0,100.0, 0, equipe);
                break;

            case 2:
                new Hunter(name,10.0,100.0,0, equipe,2.0);
                break;

            case 3:
                new Healer(name,5.0,100.0,30, 0, equipe);
                break;


            case 4:
                new Mage(name,25.0,100.0,30,0, equipe);
                break;
        }

    }
    public void dealDamage(Hero hero, Enemy enemy){
        hero.attackGUI(enemy, horde, equipe);

        if(horde.hordeList.size()>0){
            horde.attack(hero, equipe, inputparser);
        }

    }
    public void useStuff(Hero hero, Inventory inventory){
        stuff.useStuffGUI(hero, inventory);
    }
}


