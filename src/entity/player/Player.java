package entity.player;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;

import main.util.EntityHolder;
import display.player.StatDisplay;
import entity.item.active.ActiveItem;
import entity.item.active.Fireball;
import entity.item.active.Nothing;
import entity.item.weapon.BasicWand;
import entity.item.weapon.WeaponItem;
import entity.util.DirectionalMovementHelper;
import entity.util.Entity;
import environment.util.RoomCoordinate;


public class Player extends Entity{
	
	boolean movingUp,movingDown;
	boolean movingRight,movingLeft;
	int speedX, speedY;
	
	short face;
	short direction;
	boolean nextFloor;
	
	static double currentMana,maxMana,regenMana;
	static double magicPower;
	
	static int level;
	static int xp,mxp,xpGrowth;
	static LevelUp levelUp;
	
	ActiveItem spell;
	WeaponItem weapon;
	int key;
	
	RoomCoordinate RC;
	EntityHolder EH;
	DirectionalMovementHelper DMH;
	StatDisplay SD;
	
	public Player(){
		super(6,6,48,128/3);
		
		RC = new RoomCoordinate();
		EH = new EntityHolder();
		DMH = new DirectionalMovementHelper();
		
		maxHealth = 100;
		currentHealth = maxHealth;
		regenHealth = 5;
		
		maxMana = 100;
		currentMana = 100;
		regenMana = 8;
		
		armor = 0;
		magicPower = 0;
		
		spell = new Fireball(0,0);
		weapon = new BasicWand(0,0);
		key = 0;
		
		xp = 0;
		mxp = 100;
		xpGrowth = 100;
		level = 1;
		levelUp = new LevelUp();
		
		
		
		movingUp = false; movingDown = false; movingLeft = false; movingRight = false;
		speedX = 0; speedY = 0;
		
		this.setDamage(5);
		//this.setProjectileSpeed(5);
		//this.addCC(50);
	}
	public void setDisplay(StatDisplay stat){
		SD = stat;
	}
	private void setDisplayWeapon(){
		SD.setWand(weapon.getSprite(),weapon.getName());
	}
	private void setDisplaySpell(){
		SD.setSpell(spell.getSprite(),spell.getName(),spell.getManaCost());
	}
	public boolean nextFloor(boolean nextfloor){
		nextFloor = nextfloor;
		return nextFloor;
	}
	public boolean newFloor(){
		return nextFloor;
	}
	public void reset(){
		this.setMaxX(5);
		this.setMaxX(5);
		accelx = .4; 
		accely = .4;
		
		criticalChance = 0;
		damage = 1;
		
		attackPerSecond = 1;
		
		attackCurrentCooldown = 0;
		maxHealth = 100;
		currentHealth = maxHealth;
		regenHealth = 5;
		
		maxMana = 100;
		currentMana = 100;
		regenMana = 8;
		
		armor = 0;
		magicPower = 0;
		
		spell = new Fireball(0,0);
		weapon = new BasicWand(0,0);
		key = 0;
		
		xp = 0;
		mxp = 100;
		xpGrowth = 100;
		level = 1;
		levelUp = new LevelUp();
		
		
		movingUp = false; movingDown = false; movingLeft = false; movingRight = false;
		speedX = 0; speedY = 0;
		
		this.setDamage(5);
		
		this.setDisplayWeapon();
		this.setDisplaySpell();
	}
	
	public void oom(){
		this.notifInd.oom((int)this.getXCenter(),this.getY());
		a.playNoMana();
	}
	
	public short checkPosition(int WIDTH, int HEIGHT){
		if(entity.getX()+32<RC.getX(0))
			return 4;
		else if(entity.getX()+32>WIDTH)
			return 2;
		else if(entity.getY()+32<RC.getY(0))
			return 1;
		else if(entity.getY()+32>HEIGHT)
			return 3;
		else
			return -1;
	}
	public int checkLevel(){
		if(xp>=mxp){
			xp -= mxp;
			level++;
			mxp += level*xpGrowth;
			levelUp.levelUp(this);
		}
		return level;
	}
	
	public boolean isDead(){
		return currentHealth<=0;
	}
	
	public double resetMana(){
		currentMana = maxMana;
		return currentMana;
	}

	public void movementControl1(){
		if(movingUp&&!movingDown){
			speedY=super.getUp();
			//sprite.playerFaceUp();
			face = 0;
		}else if(
			movingDown&&!movingUp){
			speedY=super.getDown();
			//sprite.playerFaceDown();
			face = 2;
		}else{this.stopVerticle();}
		
		if(movingRight&&!movingLeft){
			speedX=super.getRight();
			sprite.playerFaceRight();
			face = 1;
		}else if(movingLeft&&!movingRight){
			speedX=super.getLeft();
			sprite.playerFaceLeft();
			face = 3;
		}else{this.stopHorizontal();}
	}
	public void movementControl2(){
		if((this.movingUp&&PKH.down())||(this.movingDown&&PKH.up())){
			this.speedY = ((this.speedY*2)/3);
		}
		if((this.movingRight&&PKH.left())||(this.movingLeft&&PKH.right())){
			this.speedX = ((this.speedX*2)/3);
		}
	}
	
	private void shootingUtil(){
		if(PKH.up()){
			//sprite.playerFaceUp();
		}
		if(PKH.down()){
			sprite.playerFaceDown();
		}
		if(PKH.right()){
			sprite.playerFaceRight();
		}
		if(PKH.left()){
			sprite.playerFaceLeft();
		}
	}
	
	public void moveUp(boolean moving){
		if(!movingUp&&moving)
			direction = 0;
		movingUp=moving;
	}

	public void moveDown(boolean moving){
		if(!movingDown&&moving)
			direction = 2;
		movingDown=moving;
	}

	public void moveRight(boolean moving){
		if(!movingRight&&moving)
			direction = 1;
		movingRight=moving;
	}

	public void moveLeft(boolean moving){
		if(!movingLeft&&moving)
			direction = 3;
		movingLeft=moving;
	}

	public void stopVerticle(){speedY=0;}

	public void stopHorizontal(){speedX=0;}
	
	
	
	public void addMAG(double x){
		magicPower += x;
	}
	
	public void addMP(double x){
		this.maxMana += x;
		this.currentMana += x;
	}
	
	
	public void giveKey(){
		key++;
	}
	public void setKey(int hasKey){
		key = hasKey;
	}
	
	public double giveMana(double mana){
		currentMana += mana;
		notifInd.addMana((int)(this.getX()+this.getWidth()/2), (int)(this.getY()+10), mana);
		if(currentMana>maxMana){
			currentMana = maxMana;
		}
		return mana;
	}
	
	public double regenMana(){
		if(currentMana<maxMana)
			return giveMana(regenMana);
		else
			return 0;
	}
	public double regenHealth(){
		if(currentHealth<maxHealth)
			return giveHealth(regenHealth);
		else
			return 0;
	}
	
	
	
	public int giveXP(int amount){
		notifInd.addExp((int)(this.getX()+this.getWidth()/2), (int)(this.getY()+10), amount);
		xp += amount;
		return amount;
	}

	public void setTransitionLocation(int position){
		if(position==1)
			setLocation((int)entity.getX(), RC.getY(8));
		else if(position==3)
			setLocation((int)entity.getX(), RC.getY(1));
		else if(position==2)
			setLocation(RC.getX(1),(int)entity.getY());
		else if(position==4)
			setLocation(RC.getX(14),(int)entity.getY());
	}
	public void setWeapon(WeaponItem wep){
		this.weapon = wep;
		this.setDisplayWeapon();
		this.notifInd.ItemNotif(wep.getName(), wep.getSubText());
	}
	public void setSpell(ActiveItem spell){
		this.spell = spell;
		this.setDisplaySpell();
		this.notifInd.ItemNotif(spell.getName(),spell.getSubText());
	}
	public void dropWeapon(){
		if(weapon!=null){
			weapon.setLocation((int)(this.getXCenter()-weapon.getWidth()/2), (int)(this.getYCenter()-weapon.getHeight()/2));
			weapon.pickupCooldown();
			EH.addS(weapon);
			weapon = null;
		}
	}
	public void dropSpellBook(){
		if(spell.getItemID()!=50){
			spell.setLocation((int)(this.getXCenter()-spell.getWidth()/2), (int)(this.getYCenter()-spell.getHeight()/2));
			spell.pickupCooldown();
			EH.addS(spell);
		}
		
	}
	
	public short getFace(){
		return face;
	}
	public short getDirection(){
		return direction;
	}

	public boolean getMovingUp(){return movingUp;}

	public boolean getMovingDown(){return movingDown;}

	public boolean getMovingRight(){return movingRight;}

	public boolean getMovingLeft(){return movingLeft;}

	
	
	public double getCurrentMana(){
		return currentMana;
	}
	public double getMaxMana(){
		return maxMana;
	}
	public double getMagicPower(){
		return magicPower;
	}
	public int getCurrentXp(){
		return xp;
	}
	public int getMaxXp(){
		return mxp;
	}
	public int getLevel(){
		return level;
	}
	public ActiveItem getSpell(){
		return spell;
	}
	public WeaponItem getWeapon(){
		return weapon;
	}
	public boolean hasKey(){
		return key>0;
	}
	public boolean useKey(){
		if(key>0){
			key--;
			return true;
		}
		return false;
	}

	@Override
	public double melee() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	private void face(){
		if(PKH.right())
			face = 1;
		if(PKH.left())
			face = 3;
		if(PKH.up())
			face = 0;
		if(PKH.down())
			face = 2;
	}

	public void tick(){
		movementControl1();
		shootingUtil();
		checkLevel();
		face();
		movementControl2();
		super.move(speedX,speedY);
		super.move();
		if(weapon!=null)
			weapon.projectileTick(this);
		spell.playerTick();
		
	}
	public void renderDamaged(Graphics g){
		if(damageFrame>0){
			damageFrame--;
			Graphics2D g2 = (Graphics2D) g;
			Composite c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,.4f);
			g2.setComposite(c);g.drawImage(sprite.damaged(sprite.getPlayer()), (int)getEntity().getX(), (int)(getEntity().getY()-(64-getEntity().getHeight())), null);
			c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f);
			g2.setComposite(c);
		}
	}

	public void render(Graphics g){
		
		g.drawImage(sprite.getPlayer(), (int)getEntity().getX(), (int)(getEntity().getY()-(64-getEntity().getHeight())), null);
		this.renderDamaged(g);
		spell.playerRender(g);
		
	}
}
