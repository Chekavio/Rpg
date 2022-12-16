package com.example.test1;

import java.util.ArrayList;
import java.util.Random;

public class Horde {

    public ArrayList<Enemy> hordeList = new ArrayList<>();

    public void addEnemy(Enemy enemy) {
        this.hordeList.add(enemy);
    }

    public Enemy getEnemy(int i){
        return this.hordeList.get(i-1);
    }

    public void printEnemyStat(InputParser inputParser){
        for(Enemy enemy : hordeList){
            inputParser.printMessage(" Nom : "+ enemy.getName()+"| attaque : "
                    + enemy.getDamage()+"| vitalité : "+enemy.getHealth());
        }

    }
    public void printEnemyList(InputParser inputParser){
        for(Enemy enemy : hordeList){
            inputParser.printMessage("("+(hordeList.indexOf(enemy) +1)+") "+ enemy.getName()+"| attaque : "
                    + enemy.getDamage()+"| vitalité : "+enemy.getHealth());
        }

    }
    public void EnemyIsDeadCheck(int i, Enemy enemy, InputParser inputParser){
        if(this.getEnemy(i).getHealth()<=0){
            this.hordeList.remove(enemy);

            inputParser.printMessage("L'ennemi est mort");

        }

    }
    public void EnemyIsDeadCheckGUI(Enemy enemy){
        if(enemy.getHealth()<=0){
            this.hordeList.remove(enemy);

        }

    }
    public void attack(Hero hero, Equipe equipe, InputParser inputParser){
        Random random = new Random();
        int h = random.nextInt(equipe.equipeList.size()) + 1;
        int a = random.nextInt(this.hordeList.size()) + 1;
        equipe.getHero(h).health = equipe.getHero(h).health - (this.getEnemy(a).damage-equipe.getHero(h).resistance);
        inputParser.printMessage("\n\nLe "+this.getEnemy(a).getName()+" a infligé "+this.getEnemy(a).getDamage()+" dégats à "+equipe.getHero(h).getName()+
                ".\nIl ne lui reste plus que "+equipe.getHero(h).getHealth()+" points de vie !");

        equipe.HeroIsDeadCheck(h, equipe.getHero(h),inputParser);
        equipe.HeroIsDeadCHeckGUI(hero);
    }



}

