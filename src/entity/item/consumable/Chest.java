package entity.item.consumable;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import main.util.EntityHolder;
import entity.item.ItemEntity;
import entity.item.active.Blackhole;
import entity.item.active.Dash;
import entity.item.active.Fireball;
import entity.item.active.Laser;
import entity.item.passive.Bow;
import entity.item.passive.Chalice;
import entity.item.passive.Cheese;
import entity.item.passive.Cloak;
import entity.item.passive.Cookie;
import entity.item.passive.Gem;
import entity.item.passive.GoldenEgg;
import entity.item.passive.MaxHealthPotion;
import entity.item.passive.MaxManaPotion;
import entity.item.passive.Necklace;
import entity.item.passive.Ring;
import entity.item.passive.Scroll;
import entity.item.passive.Shield;
import entity.item.passive.Sword;
import entity.item.weapon.BasicWand;
import entity.item.weapon.Fruitzooka;
import entity.item.weapon.LaserWand;
import entity.item.weapon.RapidFireWand;
import entity.item.weapon.WaterWand;
import entity.player.Player;

public class Chest extends ItemEntity{
	
	static private Random rand;
	static private EntityHolder EH;
	
	static private ArrayList<ItemEntity> chestHolds=null;
	
	public Chest(double x, double y) {
		super(64,64);
		this.setLocation((int)x, (int)y);
		this.setItemID(1);
		rand = new Random();
		EH = new EntityHolder();
		this.setPickupCooldownReset(20);
	}
	public void initChestItems(){
		chestHolds = new ArrayList<ItemEntity>();
		//ACTIVES
		chestHolds.add(new Blackhole(0,0));
		chestHolds.add(new Dash(0,0));
		chestHolds.add(new Fireball(0,0));
		chestHolds.add(new Laser(0,0));
		//WEAPONS
		chestHolds.add(new Fruitzooka(0,0));
		chestHolds.add(new LaserWand(0,0));
		chestHolds.add(new RapidFireWand(0,0));
		chestHolds.add(new WaterWand(0,0));
		//PASSIVES
		chestHolds.add(new Bow(0,0));
		chestHolds.add(new Chalice(0,0));
		chestHolds.add(new Cheese(0,0));
		chestHolds.add(new Cloak(0,0));
		chestHolds.add(new Cookie(0,0));
		chestHolds.add(new Gem(0,0));
		chestHolds.add(new GoldenEgg(0,0));
		chestHolds.add(new MaxHealthPotion(0,0));
		chestHolds.add(new MaxManaPotion(0,0));
		chestHolds.add(new Necklace(0,0));
		chestHolds.add(new Ring(0,0));
		chestHolds.add(new Scroll(0,0));
		chestHolds.add(new Shield(0,0));
		chestHolds.add(new Sword(0,0));
	}

	@Override
	public void tick() {
		this.move(0,0);
		this.move();
		this.tickPickup();
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.YELLOW);
		g.drawImage(sprite.getChest(), (int)getEntity().getX(),(int)getEntity().getY()-(int)hoverCurrentDistance, null);
		//g.fillRect((int)this.getEntity().getX(), (int)this.getEntity().getY()-(int)hoverCurrentDistance, (int)this.getEntity().getWidth(), (int)this.getEntity().getHeight());
	}

	@Override
	public boolean pickup(Player p) {
		if(pickupCooldown>0&&p.useKey()){
			ItemEntity s;
			if(chestHolds.size()>0){
				int i = rand.nextInt(chestHolds.size());
				s = chestHolds.get(i);
			}else{
				s = new BasicWand(0,0);
			}
			s.setPickupCooldown(20);
			s.knockback(rand.nextDouble()*20-5, rand.nextDouble()*20-10);
			s.setLocation((int)(this.getXCenter()-s.getWidth()/2), (int)(this.getYCenter()-s.getHeight()/2));
			EH.addS(s);
			EH.addS(new ChestOpened(this.getX(),this.getY()));
			a.playChest();
			this.pickupCooldown();
			return true;
		}else if(pickupCooldown<=0){
			this.pickupCooldown();
			notifInd.string("Not Enough Keys",(int)p.getXCenter(), p.getY()+10);
			return false;
		}else{
			return false;
		}
		
	
	}
}
