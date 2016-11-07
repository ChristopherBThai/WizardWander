package entity.projectile;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;

import main.util.Audio;
import main.util.EntityHolder;
import main.util.Sprite;
import entity.particle.OnFire;
import entity.util.Entity;
import entity.util.PercentToBoolean;
import environment.block.Block;

public abstract class AbsProjectile {
	protected double dmg;
	protected double criticalChance;
	protected double knockback;
	
	protected double xvel, yvel,vel;
	protected double x,y;
	
	int hover;
	
	protected static Sprite sprite = new Sprite();
	protected static Audio a = new Audio();
	protected static PercentToBoolean PTB = new PercentToBoolean();
	protected static EntityHolder EH = new EntityHolder();
	protected static OnFire OF = new OnFire();
	
	public AbsProjectile(double x, double y, double xvel, double yvel,double dmg, double crit, double knock){
		this.dmg = dmg;
		this.criticalChance = crit;
		this.knockback = knock;
		
		this.xvel = xvel;
		this.yvel = yvel;
		this.x = x;
		this.y = y;
	}
	
	public void setHover(int hover){
		this.hover = hover;
	}
	
	public double getX(){
		return x;
	}
	public double getY(){
		return y;
	}
	public double getDamage(){
		return dmg;
	}
	public double getCriticalChance(){
		return criticalChance;
	}
	public double getXKnockback(){
		return xvel * knockback;
	}
	public double getYKnockback(){
		return yvel * knockback;
	}
	public void renderShadow(double x, double y, double width,double height,Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		Composite c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,.15f);
		g2.setComposite(c);
		g.setColor(Color.BLACK);
		g.fillOval((int)x-2,(int)(y+height-(height/4))+hover,(int)width+4, (int)height/2);
		c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f);
		g2.setComposite(c);
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract boolean collide(Entity e);
	public abstract boolean collide(Block b);
	public abstract void renderHitbox(Graphics g);
}
