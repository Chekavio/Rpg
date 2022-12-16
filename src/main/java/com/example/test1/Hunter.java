package com.example.test1;

public class Hunter extends Hero {
    public Hunter(String name, double damage, double health, double resistance, Equipe equipe, double arrow) {
        this.name = name;
        this.damage = damage;
        this.health = health;
        this.resistance = resistance;
        equipe.addHero(this);

        this.classType = "Hunter";
        this.arrow = arrow;
    }


    @Override
    public void attack(int a, Horde horde, Equipe equipe, InputParser inputParser) {

        if(this.arrow>=1){
            horde.getEnemy(a).health = horde.getEnemy(a).health - this.damage;
            this.arrow = this.arrow-1;
            inputParser.printMessage("Votre "+this.getName()+" a infligé "+this.getDamage()+
                    " dégats "+"à "+horde.getEnemy(a).getName()+
                    ", il lui reste "+horde.getEnemy(a).getHealth()+" points de vie");
            horde.EnemyIsDeadCheck(a, horde.getEnemy(a), inputParser);


        }else{
            inputParser.printMessage("\nVous n'avez plus fleches !\nSelectionnez un autre héro");
        }


    }
    @Override
    public void attackGUI(Enemy enemy, Horde horde, Equipe equipe) {

        if(this.arrow>=1){
            enemy.health = enemy.getHealth() - this.damage;
            this.arrow = this.arrow-1;
            equipe.reload();
            horde.EnemyIsDeadCheckGUI(enemy);

        }else{
            System.out.println("Plus de flêches");
        }
    }

    @Override
    public void printHero(Equipe equipe, InputParser inputParser) {
        inputParser.printMessage("("+(equipe.equipeList.indexOf(this) +1)+") "+this.classType+" Nom : "+ this.getName()+"| attaque : "
                + this.getDamage()+"| vitalité : "+this.getHealth() +"| résistance : "+this.resistance+ "| flêches : "+this.arrow);

    }




    public void printHeroStat(Equipe equipe, InputParser inputParser) {
        inputParser.printMessage(this.classType+ "| attaque : "+ this.getDamage()+"| vitalité : "+this.getHealth() +
                "| résistance : "+this.resistance+ "| flêches : "+this.arrow);
    }

}
