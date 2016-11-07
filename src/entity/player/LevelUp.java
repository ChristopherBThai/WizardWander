package entity.player;

import java.awt.Color;

import display.NotificationIndicator;

public class LevelUp {
	private static int points;
	private static double AS;
	private static double MS;
	private static double ATT;
	private static double CC;
	private static double DEF;
	private static double MAG;
	private static NotificationIndicator notifInd;
	public LevelUp(){
		notifInd = new NotificationIndicator();
		AS = .1;
		MS = 4;
		ATT = 1;
		CC = 0.5;
		DEF = 0.5;
		MAG = 5;
	}
	
	public void levelUp(Player p){
		points++;
		p.addAttackSpeed(0.02);
		//p.addMovementSpeed(1);
		p.addDamage(1);
		p.addCriticalChance(0.5);
		p.addArmor(0.3);
		p.addMAG(3);
		p.addMP(5);
		p.addHP(7);
		p.giveHealth(7);
		p.giveMana(5);
		notifInd.string("LEVEL UP", (int)p.getXCenter(), (int)p.getYCenter(), Color.YELLOW, 200);
	}
	public double getAS(){
		if(points>0){
			points--;
			return AS;
		}else{
			return 0;
		}
	}
	public double getMS(){
		if(points>0){
			points--;
			return MS/10;
		}else{
			return 0;
		}
	}
	public double getATT(){
		if(points>0){
			points--;
			return ATT;
		}else{
			return 0;
		}
	}
	public double getCC(){
		if(points>0){
			points--;
			return CC;
		}else{
			return 0;
		}
	}
	public double getDEF(){
		if(points>0){
			points--;
			return DEF;
		}else{
			return 0;
		}
	}
	public double getMAG(){
		if(points>0){
			points--;
			return MAG;
		}else{
			return 0;
		}
	}
}
