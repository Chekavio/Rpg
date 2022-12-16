package com.example.test1;

public abstract class Hero extends Combatant {

public abstract void attack(int a, Horde horde, Equipe equipe, InputParser inputParser);
public abstract void attackGUI(Enemy enemy, Horde horde ,Equipe equipe);
public abstract void printHero(Equipe equipe, InputParser inputParser);

public abstract void printHeroStat(Equipe equipe, InputParser inputParser);
}




