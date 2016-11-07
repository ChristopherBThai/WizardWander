package entity.item.passive;

import java.awt.Graphics;

import display.NotificationIndicator;
import entity.item.ItemEntity;
import entity.player.Player;

public class MaxManaPotion extends ItemEntity{
NotificationIndicator notifInd = new NotificationIndicator();
	
	public MaxManaPotion(int entityWidth, int entityHeight) {
		super(32, 32);
		this.setLocation(entityWidth, entityHeight);
		this.setName("Mana Potion");
		this.setSubText("You feel more energy");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		this.move(0,0);
		this.move();
		this.hover();
		if(pickupCooldown>0)
			pickupCooldown--;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(sprite.getManaPot(), (int)getEntity().getX(),(int)getEntity().getY()-(int)hoverCurrentDistance, null);
		
	}

	@Override
	public boolean pickup(Player p) {
		if(pickupCooldown>0){
			return false;
		}else{
		p.addMP(30);
		this.notifInd.ItemNotif(this.getName(), this.getSubText());
		return true;
		}
	}
}
