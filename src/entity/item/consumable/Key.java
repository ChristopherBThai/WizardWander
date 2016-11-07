package entity.item.consumable;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import main.util.EntityHolder;
import entity.item.ItemEntity;
import entity.item.active.ActiveItem;
import entity.item.weapon.Fruitzooka;
import entity.item.weapon.RapidFireWand;
import entity.item.weapon.WaterWand;
import entity.player.Player;

public class Key extends ItemEntity{
	
	public Key(double x, double y) {
		super(32,32);
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
		//g.setColor(Color.YELLOW);
		g.drawImage(sprite.getKey(), (int)getEntity().getX(),(int)getEntity().getY()-(int)hoverCurrentDistance, null);
		//g.fillRect((int)this.getEntity().getX(), (int)this.getEntity().getY()-(int)hoverCurrentDistance, (int)this.getEntity().getWidth(), (int)this.getEntity().getHeight());
	}

	@Override
	public boolean pickup(Player p) {
		p.giveKey();
		return true;
	
	}
}
