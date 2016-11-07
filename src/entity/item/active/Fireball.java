package entity.item.active;

import java.awt.Color;
import java.awt.Graphics;

import main.util.EntityHolder;
import entity.particle.OnFire;
import entity.player.Player;
import entity.projectile.FireballProjectile;

public class Fireball extends ActiveItem{

	
	OnFire onFire;
	
	protected static double damage,magicPercent,additionalDamage;
	protected static int level,currentXp,maxXp,xpIncreaseRate;
	
	public Fireball(int x, int y) {
		super(x, y);
		this.setItemID(51);
		this.setManaCost(40);
		onFire = new OnFire();
		
		if(damage==0){
			damage = 10;
			magicPercent = .5;
			level = 1;
			currentXp = 0;
			maxXp = 5;
			xpIncreaseRate = 5;
			additionalDamage = 0;
		}
		Sprite = sprite.getFireballSpellbook();
		this.setName("Fireball Spell");
		this.setSubText("Shoot Balls of Fire");
	}
	public void tick(){
		super.tick();
		/*
		if(currentXp>maxXp){
			currentXp -= maxXp;
			maxXp += xpIncreaseRate;
			xpIncreaseRate += xpIncreaseRate;
			level++;
			magicPercent += .05;
			damage += level;
			this.setManaCost(this.getManaCost()+4);
		}
		*/
	}
	public void playerTick(){
		/*
		if(currentXp>maxXp){
			currentXp -= maxXp;
			maxXp += xpIncreaseRate;
			xpIncreaseRate += xpIncreaseRate;
			level++;
			magicPercent += .05;
			damage += level+5;
			this.setManaCost(this.getManaCost()+4);
		}
		*/
	}

	@Override
	public void render(Graphics win) {
		win.setColor(Color.GREEN);
		win.drawImage(Sprite, (int)getEntity().getX(),(int)getEntity().getY()-(int)hoverCurrentDistance, null);
		//win.fillRect((int)this.getEntity().getX(), (int)this.getEntity().getY()-(int)hoverCurrentDistance, (int)this.getEntity().getWidth(), (int)this.getEntity().getHeight());
		
		
	}
	
	public void playerRender(Graphics win){
		
	}

	@Override
	public boolean activate(Player p) {
		
		if(p.getCurrentMana()>=manaCost){
			p.giveMana(-manaCost);
			int fireballSpeed = 7;
			FireballProjectile temp = new FireballProjectile(0,0,0,0,0,null);
			double width = temp.getWidth();
			double xvel = p.getCurrentX()/2;
			double yvel = p.getCurrentY()/2;
			if(xvel>=fireballSpeed)
				xvel -= xvel-fireballSpeed;
			if(xvel<=-fireballSpeed)
				xvel -= xvel+fireballSpeed;
			if(yvel>=fireballSpeed)
				yvel -= yvel-fireballSpeed;
			if(yvel<=-fireballSpeed)
				yvel -= yvel+fireballSpeed;
			if(p.getFace()==0){
				EH.addP(new FireballProjectile(p.getX()+p.getWidth()/2,p.getY()-width/2+fireballSpeed,xvel,-fireballSpeed+yvel,this.checkDamage(p),this));
			}
			if(p.getFace()==1){
				EH.addP(new FireballProjectile(p.getX()+p.getWidth()+width/2-fireballSpeed,p.getY()+p.getHeight()/2,fireballSpeed+xvel,yvel,this.checkDamage(p),this));
			}
			if(p.getFace()==2){
				EH.addP(new FireballProjectile(p.getX()+p.getWidth()/2,p.getY()+p.getHeight()+width/2-fireballSpeed,xvel,fireballSpeed+yvel,this.checkDamage(p),this));
			}
			if(p.getFace()==3){
				EH.addP(new FireballProjectile(p.getX()-width/2+fireballSpeed,p.getY()+p.getHeight()/2,-fireballSpeed+xvel,yvel,this.checkDamage(p),this));
			}
			return true;
		}else{
			p.oom();
			return false;
		}
	}
	@Override
	public double getDamage(){
		return this.damage;
	}
}