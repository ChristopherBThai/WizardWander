package entity.item.passive;

import java.awt.Graphics;

import display.NotificationIndicator;
import entity.item.ItemEntity;
import entity.player.Player;

public class GoldenEgg extends ItemEntity{
NotificationIndicator notifInd = new NotificationIndicator();
	
	public GoldenEgg(int entityWidth, int entityHeight) {
		super(32, 32);
		this.setLocation(entityWidth, entityHeight);
		this.setName("GoldenEgg");
		this.setSubText("Mmm... breakfast!");
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
		g.drawImage(sprite.getGoldenEgg(), (int)getEntity().getX(),(int)getEntity().getY()-(int)hoverCurrentDistance, null);
		
	}

	@Override
	public boolean pickup(Player p) {
		if(pickupCooldown>0){
			return false;
		}else{
		p.addCriticalChance(1);
		p.addMP(10);
		p.addDamage(1);
		p.addMAG(5);
		this.notifInd.ItemNotif(this.getName(), this.getSubText());
		return true;
		}
	}
}
