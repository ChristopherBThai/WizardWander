package entity.hostile;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import main.util.EntityHolder;
import entity.util.Entity;
import entity.util.PercentToBoolean;

public abstract class HostileEntity extends Entity{

	Rectangle healthBar;
	
	int xpWorth;
	
	
	protected static EntityHolder EH = new EntityHolder();
	protected static PercentToBoolean  PtB = new PercentToBoolean();
	protected static Random rand = new Random();
	
	public HostileEntity(double inputVerticleSpeed,double inputHorizontalSpeed, int entityWidth, int entityHeight, int maxhealth) {
		super(inputVerticleSpeed, inputHorizontalSpeed, entityWidth, entityHeight);
		this.maxHealth = maxhealth;
		this.currentHealth = maxHealth;
		healthBar = new Rectangle(50,3);
		damage = 1;
		xpWorth = 20;
	}
	
	public void healthTick(){
		healthBar.setLocation((int)(getXCenter()-healthBar.getWidth()/2),(int)(this.entity.getY()-10-(this.entity.getHeight()/2)));
	}
	
	
	public int setXpWorth(int xp){
		xpWorth = xp;
		return xpWorth;
	}
	
	public int getXpWorth(){
		return xpWorth;
	}


	public void healthRender(Graphics win){
		win.setColor(Color.BLACK);
		win.fillRect((int)healthBar.getX(), (int)healthBar.getY(), (int)healthBar.getWidth(), (int)healthBar.getHeight());
		win.setColor(Color.RED);
		win.fillRect((int)healthBar.getX(), (int)healthBar.getY(), (int)(healthBar.getWidth()*((double)currentHealth/maxHealth)), (int)healthBar.getHeight());
	}
	
	public abstract int getFace();
	
	public abstract void follow(Entity e);
	
	public abstract void drop();
	
	public abstract void renderDamaged(Graphics g);

}
