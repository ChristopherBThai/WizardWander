package display.player;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.util.Sprite;

public class StatDisplay {
	
	private double attackDamage,attackSpeed,movementSpeed,criticalChance,armor,magicPower;
	private int floor,level,spellCost;
	private static Sprite sprite = new Sprite();
	private BufferedImage spell,weapon;
	private String spellName,weaponName;
	public StatDisplay(){
		attackDamage = 0;
		attackSpeed = 0;
		movementSpeed = 0;
		criticalChance = 0;
		armor = 0;
		magicPower = 0;
		floor = 0;
		spellName="No Spell";
		weaponName="No Weapon";
	}
	public void setAttackDamage(double input){
		attackDamage = input;
	}
	public void setAttackSpeed(double input){
		attackSpeed = input;
	}
	public void setMovementSpeed(double input){
		movementSpeed = input;
	}
	public void setCriticalChance(double input){
		criticalChance = input;
	}
	public void setArmor(double input){
		armor = input;
	}
	public void setMagicPower(double input){
		magicPower = input;
	}
	public void setFloor(int x){
		floor = x;
	}
	public void setWand(BufferedImage input,String name){
		weapon = sprite.resize(input, 64, 64);
		weaponName = name;
	}
	public void setSpell(BufferedImage input,String name,int manaCost){
		spell = input;
		spellName = name;
		spellCost = manaCost;
	}
	public void setLevel(int x){
		level = x;
	}
	
	public void tick(){
		
	}
	
	public void render(Graphics g){
		g.setColor(Color.WHITE);
		g.setFont(new Font("Helvetica",Font.BOLD,20));
		g.drawString("DMG: ",180,30);
		g.drawString("AS: ",180,60);
		g.drawString("MS: ",330,30);
		g.drawString("CC: ",330,60);
		g.drawString("DEF: ",480,30);
		g.drawString("MAG: ",480,60);
		g.drawString(""+(int)attackDamage,240,30);
		g.drawString(""+(int)(attackSpeed*100)/100.0,240,60);
		g.drawString(""+(int)movementSpeed,390,30);
		g.drawString(""+(int)((int)(criticalChance*10))/10.0+"%",390,60);
		g.drawString(""+(int)(armor)+"%",540,30);
		g.drawString(""+(int)magicPower,540,60);
		
		g.setColor(Color.ORANGE);
		g.setFont(new Font("Helvetica",Font.BOLD,30));
		g.drawString("Floor   "+(int)floor,620,34);
		
		g.setColor(Color.YELLOW);
		g.setFont(new Font("Helvetica",Font.BOLD,20));
		g.drawString("Level: "+(int)level,783-g.getFontMetrics().stringWidth("Level: "+level),80);
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Helvetica",Font.BOLD,20));
		g.drawString("Weapon",800,30);
		g.drawString("Spell",930,30);
		g.setColor(Color.YELLOW);
		g.drawRect(790, 10, 100, 108);
		g.drawRect(905, 10, 100, 108);
		g.drawImage(weapon,808,45,null);
		g.drawImage(spell, 923, 45, null);
		g.setFont(new Font("Helvetica",Font.PLAIN,10));
		g.drawString(weaponName,886-g.getFontMetrics().stringWidth(weaponName),113);
		g.drawString(spellName,1001-g.getFontMetrics().stringWidth(spellName),113);
		g.setColor(new Color(150,150,255));
		g.drawString("Cost: "+spellCost,1001-g.getFontMetrics().stringWidth("Cost: "+spellCost),100);
	}
}
