package entity.item.consumable;

import java.awt.Color;
import java.awt.Graphics;

import entity.item.ItemEntity;
import entity.particle.PotionParticle;
import entity.player.Player;

public class HealthPotion extends ItemEntity{
	int particletick;
	Color particleColor;
	public HealthPotion(double x, double y) {
		super(32, 32);
		this.setLocation((int)x, (int)y);
		this.setItemID(1);
		particleColor = new Color(255,60,60);
	}

	@Override
	public void tick() {
		this.move(0,0);
		this.move();
		this.hover();
		particletick++;
		if(particletick%15==0)
			EH.addPA(new PotionParticle((int)(this.getX()+Math.random()*this.getWidth()),(int)(this.getY()+Math.random()*this.getHeight()),3,3,particleColor));
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.drawImage(sprite.getHealth(), (int)getEntity().getX(),(int)getEntity().getY()-(int)hoverCurrentDistance, null);
		//g.fillRect((int)this.getEntity().getX(), (int)this.getEntity().getY(), (int)this.getEntity().getWidth(), (int)this.getEntity().getHeight());
	}

	@Override
	public boolean pickup(Player p) {
		if(p.getMaxHealth()<=p.getCurrentHealth()){
			return false;
		}else{
			p.giveHealth(p.getMaxHealth()*.1);
			return true;
		}
		
	}

}
