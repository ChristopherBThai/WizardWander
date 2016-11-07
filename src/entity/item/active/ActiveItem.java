package entity.item.active;

import java.awt.Graphics;

import main.util.EntityHolder;
import entity.item.ItemEntity;
import entity.player.Player;

public abstract class ActiveItem extends ItemEntity{
	
	int manaCost;
	protected static EntityHolder EH = new EntityHolder();
	
	protected double damage,magicPercent,additionalDamage;
	protected int level,currentXp,maxXp,xpIncreaseRate;
	
	public ActiveItem(int x, int y) {
		super(64,64);
		this.setLocation((int)x, (int)y);
		this.setItemID(1);
		this.setAcceleration(.4);
		damage = 0;
		magicPercent = .5;
		level = 1;
		currentXp = 0;
		maxXp = 5;
		xpIncreaseRate = 5;
		additionalDamage = 10;
	}
	
	public int setManaCost(int x){
		manaCost = x;
		return manaCost;
	}
	public int getManaCost(){
		return manaCost;
	}
	public void addXp(int xp){
		currentXp += xp;
		this.xpCheck();
	}
	public void xpCheck(){
		if(currentXp>maxXp){
			currentXp -= maxXp;
			maxXp += xpIncreaseRate;
			xpIncreaseRate += xpIncreaseRate;
			level++;
			magicPercent += .05;
			additionalDamage += level+5;
			this.setManaCost(this.getManaCost());
		}
	}
	public double checkDamage(Player p){
		damage = (double)additionalDamage + p.getMagicPower()*magicPercent;
		return damage;
	}

	@Override
	public void tick() {
		if(pickupCooldown>0)
			pickupCooldown--;
		
		this.move(0,0);
		this.move();
		this.hover();
		
	}

	@Override
	public boolean pickup(Player p){
		if(pickupCooldown>0){
			return false;
		}else{
			p.dropSpellBook();
			p.setSpell(this);
			return true;
		}
		
	}
	
	@Override
	public abstract void render(Graphics win);
	public abstract double getDamage();
	public abstract boolean activate(Player p);
	public abstract void playerTick();
	public abstract void playerRender(Graphics win);

}
