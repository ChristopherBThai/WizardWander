package entity.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.util.Audio;
import main.util.EntityHolder;
import main.util.Sprite;
import display.NotificationIndicator;

public abstract class Entity{
	
	protected Rectangle entity;
	double maxY,maxX;
	double currentY, currentX;
	double collideX,collideY;
	protected double accelx;
	protected double accely;
	double x, y;
	
	protected double currentHealth,maxHealth,regenHealth;
	protected short damageFrame;
	
	protected final double attackCooldown;
	protected double attackCurrentCooldown;
	protected double attackPerSecond;
	protected double criticalChance;
	protected double damage;
	protected double armor;
	protected double knockbackResistance;
	
	protected Sprite sprite = new Sprite();
	protected BufferedImage Sprite;
	
	protected static NotificationIndicator notifInd = new NotificationIndicator();
	protected static ProjectileKeyHelper PKH = new ProjectileKeyHelper();
	protected static Audio a = new Audio();
	protected static EntityHolder EH = new EntityHolder();
	
	
	public Entity(double inputVerticleSpeed, double inputHorizontalSpeed,int entityWidth, int entityHeight){
		
		entity = new Rectangle(entityWidth,entityHeight);
		maxY = inputVerticleSpeed; maxX = inputHorizontalSpeed;
		accelx = .4; accely = .4;
		
		criticalChance = 0;
		damage = 1;
		
		attackPerSecond = 1;
		
		attackCooldown = 60;
		attackCurrentCooldown = 0;
	}
	public BufferedImage getSprite(){
		return Sprite;
	}
	public void setSprite(BufferedImage sprite){
		this.Sprite = sprite;
	}
	public void setWidth(int width){
		entity.setSize(width, this.getHeight());
	}
	public void setHeight(int height){
		entity.setSize(this.getWidth(),height);
	}
	public void setSize(int width, int height){
		entity.setSize(width, height);
	}
	public void setLocation(int x, int y){
		this.x = x;
		this.y = y;
		this.entity.setLocation((int)(this.x), (int)(this.y));
	}
	public void move(){
		x += currentX+collideX;
		y += currentY+collideY;
		if(Math.abs(collideY)<=accely)
			collideY=0;
		else if(collideY<0)
			collideY+=accely;
		else if(collideY>0)
			collideY-=accely;
		
		if(Math.abs(collideX)<=accelx)
			collideX=0;
		else if(collideX<0)
			collideX+=accelx;
		else if(collideX>0)
			collideX-=accelx;
		
		this.entity.setLocation((int)(this.x), (int)(this.y));
	}
	public void move(double Xvelocity, double Yvelocity){
		if(Math.abs(currentY-Yvelocity)<=accely)
			currentY=Yvelocity;
		else if(currentY<Yvelocity)
			currentY+=accely;
		else if(currentY>Yvelocity)
			currentY-=accely;
		
		if(Math.abs(currentX-Xvelocity)<=accelx)
			currentX=Xvelocity;
		else if(currentX<Xvelocity)
			currentX+=accelx;
		else if(currentX>Xvelocity)
			currentX-=accelx;
	}
	public void moveX(double Xvalue){
		if(Math.abs(currentX-Xvalue)<=accelx)
			currentX=Xvalue;
		else if(currentX<Xvalue)
			currentX+=accelx;
		else if(currentX>Xvalue)
			currentX-=accelx;
	}
	public void moveY(double Yvalue){
		if(Math.abs(currentY-Yvalue)<=accely)
			currentY=Yvalue;
		else if(currentY<Yvalue)
			currentY+=accely;
		else if(currentY>Yvalue)
			currentY-=accely;
	}
	public void setCurrentX(double x){
		currentX = x;
	}
	public void setCurrentY(double y){
		currentY = y;
	}
	public void changeAcceleration(double vel){
		accelx = vel;
		accely = vel;
	}
	public void setKnockbackResistance(double knockback){
		knockbackResistance = knockback;
	}
	public void knockback(double x, double y){
		
		if(Math.abs(currentX)<30){
			currentX += x;
		}
		if(Math.abs(currentY)<30){
			currentY += y;
		}
	}
	public void setKnockbackX(double x){
		collideX = x;
	}
	public void setKnockbackY(double y){
		collideY = y;
	}
	public void collide(double x, double y){
		if(Math.abs(collideX)<10){
			collideX += x;
		}
		if(Math.abs(collideY)<10){
			collideY += y;
		}
	}
	public double giveHealth(double health){
		currentHealth += health;
		notifInd.addHeal((int)(this.getX()+this.getWidth()/2), (int)(this.getY()+10), health);
		if(currentHealth>maxHealth){
			currentHealth = maxHealth;
		}
		return health;
	}
	
	public double regenHealth(){
		return giveHealth(regenHealth);
	}

	public double resetHealth(){
		this.giveHealth(maxHealth);
		return currentHealth;
	}
	public boolean dead(){
		return currentHealth<=0;
	}
	public void resetAttackCooldown(){
		attackCurrentCooldown = attackCooldown;
	}
	public double melee(){
		if(this.getCurrentAttackCooldown()==0){
			this.resetAttackCooldown();
			return getDamage();
		}else{
			return 0;
		}
		
	}
	public double damage(double amount){
		
		if(currentHealth>0&&amount>0){
			amount -= amount*(armor/100.0);
			notifInd.addDamage((int)(this.getX()+this.getWidth()/2), (int)(this.getY()+10), amount);
			currentHealth-=amount;
			a.playPlayerHurt();
			return amount;
		}else{
			return -1;
		}
	}
	
	public double getXCenter(){
		return entity.getX()+entity.getWidth()/2;
	}
	public double getYCenter(){
		return entity.getY()+entity.getHeight()/2;
	}
	
	public Rectangle getEntity(){
		return entity;
	}	
	public double getMaxX(){
		return this.maxX;
	}
	public double getMaxY(){
		return this.maxY;
	}
	public int getUp(){
		return (int)-maxY;
	}
	public int getDown(){
		return (int)maxY;
	}
	public int getRight(){
		return (int)maxX;
	}
	public int getLeft(){
		return (int)-maxX;
	}
	
	public int getCurrentY(){
		return (int)currentY;
	}
	public int getCurrentX(){
		return (int)currentX;
	}
	public double getCurrentAttackCooldown(){
		return this.attackCurrentCooldown;
	}
	public double getAttackCooldown(){
		return this.attackCooldown;
	}
	public double getAttackSpeed(){
		return this.attackPerSecond;
	}
	public double getCriticalChance(){
		return this.criticalChance;
	}
	public int getWidth(){
		return (int)entity.getWidth();
	}
	public int getHeight(){
		return (int)entity.getHeight();
	}
	public int getX(){
		return (int)x;
	}
	public int getY(){
		return (int)y;
	}
	public double getDamage(){
		return damage;
	}
	public double getCurrentHealth(){
		return currentHealth;
	}

	public double getMaxHealth(){
		return maxHealth;
	}
	public double getArmor(){
		return armor;
	}
	public void setAcceleration(double acc){
		this.accelx = acc;
		this.accely = acc;
	}
	public double setCriticalChance(double crit){
		criticalChance = crit;
		return criticalChance;
	}
	public void setMeleeCurrentCooldown(int cooldown){
		attackCurrentCooldown = cooldown;
	}
	public void setMaxX(double x){
		this.maxX=x;
	}
	public void setMaxY(double x){
		this.maxY=x;
	}
	public void setDamage(double x){
		damage = x;
	}
	public void addHP(double x){
		this.maxHealth += x;
		this.currentHealth+=x;
	}
	public void addMaxX(double x){
		this.maxX+=x;
	}
	public void addMaxY(double x){
		this.maxY+=x;
	}
	public void addDamage(double x){
		this.damage+=x;
	}
	public void addArmor(double x){
		if(armor<90)
			this.armor += x;
	}
	public void setAttackSpeed(double x){
		this.attackPerSecond = x;
	}
	public void addMovementSpeed(double x){
		this.addMaxX(x);
		this.addMaxY(x);
	}
	public void addCriticalChance(double x){
		criticalChance += x;
	}
	public void addAttackSpeed(double x){
		attackPerSecond += x;
	}
	public abstract void render(Graphics win);
	public void renderShadow(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		Composite c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,.25f);
		g2.setComposite(c);
		g.setColor(Color.BLACK);
		g.fillOval((int)entity.getX()-2,(int)(entity.getY()+entity.getHeight()-(entity.getHeight()/4)),(int)entity.getWidth()+4, (int)entity.getHeight()/2);
		c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f);
		g2.setComposite(c);
	}
	
	public void tick(){
		
	}
	
	public void renderHitbox(Graphics g){
		g.setColor(Color.WHITE);
		g.drawRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
	}
	public void meleeTick(){
		if(attackCurrentCooldown>0)
			attackCurrentCooldown--;
	}
	
}
