package entity.item.passive;

import java.awt.Graphics;

import display.NotificationIndicator;
import entity.item.ItemEntity;
import entity.player.Player;

public class Necklace extends ItemEntity{
	NotificationIndicator notifInd = new NotificationIndicator();
	
	public Necklace(int entityWidth, int entityHeight) {
		super(32, 32);
		this.setLocation(entityWidth, entityHeight);
		this.setName("Necklace");
		this.setSubText("I feel pretty!");
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
		win.drawImage(sprite.getNecklace(), (int)getEntity().getX(),(int)getEntity().getY()-(int)hoverCurrentDistance, null);
		
	}

	@Override
	public boolean pickup(Player p) {
		if(pickupCooldown>0){
			return false;
		}else{
		p.addDamage(1);
		p.addMAG(8);
		p.addArmor(5);
		p.addAttackSpeed(.3);
		this.notifInd.ItemNotif(this.getName(), this.getSubText());
		return true;
		}
	}
}
