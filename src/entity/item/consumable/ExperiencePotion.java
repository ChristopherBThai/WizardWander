package entity.item.consumable;

import java.awt.Color;
import java.awt.Graphics;

import entity.item.ItemEntity;
import entity.particle.PotionParticle;
import entity.player.Player;

public class ExperiencePotion extends ItemEntity{
	
	int particletick;
	Color particleColor;
	
	public ExperiencePotion(double x, double y) {
		super(32, 32);
		this.setLocation((int)x, (int)y);
		this.setItemID(1);
		hoverDistance = 10;
		hoverCurrentDistance = 0;
		hoverAcceleration = .3;
		particleColor = new Color(204,255,109);
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
		g.setColor(Color.YELLOW);
		g.drawImage(sprite.getExp(), (int)getEntity().getX(),(int)getEntity().getY()-(int)hoverCurrentDistance, null);
		//g.fillRect((int)this.getEntity().getX(), (int)this.getEntity().getY()-(int)hoverCurrentDistance, (int)this.getEntity().getWidth(), (int)this.getEntity().getHeight());
	}

	@Override
	public boolean pickup(Player p) {
			p.giveXP((int)(p.getMaxXp()*.05));
			return true;
	}
		
}
