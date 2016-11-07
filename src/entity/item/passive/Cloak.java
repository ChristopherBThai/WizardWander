package entity.item.passive;

import java.awt.Graphics;

import display.NotificationIndicator;
import entity.item.ItemEntity;
import entity.player.Player;

public class Cloak extends ItemEntity{
	NotificationIndicator notifInd = new NotificationIndicator();
	
	public Cloak(int entityWidth, int entityHeight) {
		super(32, 32);
		this.setLocation(entityWidth, entityHeight);
		this.setName("Cloak");
		this.setSubText("New outfit!");
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
	public void render(Graphics win) {
		win.drawImage(sprite.getCloak(), (int)getEntity().getX(),(int)getEntity().getY()-(int)hoverCurrentDistance, null);
		
	}

	@Override
	public boolean pickup(Player p) {
		if(pickupCooldown>0){
			return false;
		}else{
		p.addArmor(7);
		p.addMAG(10);
		p.addHP(16);
		p.addMP(10);
		this.notifInd.ItemNotif(this.getName(), this.getSubText());
		return true;
		}
	}
}
