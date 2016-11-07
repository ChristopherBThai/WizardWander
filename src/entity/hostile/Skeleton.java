package entity.hostile;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;

import entity.hostile.movement.SmartFollow;
import entity.item.consumable.HealthPotion;
import entity.item.consumable.ManaPotion;

public abstract class Skeleton extends SmartFollow{ //20x62
	
	protected int type;
	
	public Skeleton(int x, int y, int health) {
		super(3.5, 3.5, 20, 41, health);
		setLocation(x,y);
		type = 0;
	}
	public void setType(int x){
		type = x;
	}

	@Override
	public void tick() {
		healthTick();
		meleeTick();
		move();
	}


	@Override
	public void drop() {
		if(PtB.roll(20)){
			int roll = rand.nextInt(100)+1;
			if(roll<=50){
				EH.addS(new HealthPotion(this.getXCenter(),this.getYCenter()));
			}else if(roll>50){
				EH.addS(new ManaPotion(this.getXCenter(),this.getYCenter()));
			}
		}
		
	}
	public abstract void render(Graphics win);
	public abstract void renderDamaged(Graphics g);
}
