package entity.item.consumable;

import java.awt.Graphics;

import entity.item.ItemEntity;
import entity.player.Player;

public class ChestOpened extends ItemEntity{

	public ChestOpened(double x, double y){
		super(64,64);
		this.setLocation((int)x, (int)y);
		this.setItemID(1);
	}

	@Override
	public void tick() {
		this.move(0,0);
		this.move();
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(sprite.getChestOpen(), (int)getEntity().getX(),(int)getEntity().getY()-(int)hoverCurrentDistance, null);
		
	}

	@Override
	public boolean pickup(Player p) {
		return false;
	}
}
