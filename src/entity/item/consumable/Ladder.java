package entity.item.consumable;

import java.awt.Color;
import java.awt.Graphics;

import entity.item.ItemEntity;
import entity.player.Player;

public class Ladder extends ItemEntity{
	public Ladder(double x, double y) {
		super(32,32);
		this.setLocation((int)x, (int)y);
		this.setItemID(1);
	}

	@Override
	public void tick() {
		//this.move(0,0);
		//this.move();
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		//g.drawImage(sprite.getKey(), (int)getEntity().getX(),(int)getEntity().getY()-(int)hoverCurrentDistance, null);
		g.fillRect((int)this.getEntity().getX(), (int)this.getEntity().getY()-(int)hoverCurrentDistance, (int)this.getEntity().getWidth(), (int)this.getEntity().getHeight());
	}

	@Override
	public boolean pickup(Player p) {
		p.nextFloor(true);
		return false;
	
	}
}
