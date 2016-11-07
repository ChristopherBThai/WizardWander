package entity.item;

import java.awt.Graphics;
import java.util.Random;

import entity.player.Player;
import entity.util.Entity;

public abstract class ItemEntity extends Entity{
	
	int itemID;
	protected int pickupCooldown,pickupReset;

	protected double hoverDistance,hoverCurrentDistance;
	protected double hoverAcceleration;
	
	String subText,name;
	
	public ItemEntity(int entityWidth, int entityHeight) {
		super(0, 0, entityWidth, entityHeight);
		this.setAcceleration(.4);


		hoverDistance = 10;
		hoverCurrentDistance = 0;
		hoverAcceleration = .3;
		
		pickupCooldown = 0;
		pickupReset = 100;
		
		Random rand = new Random();
		int x = rand.nextInt(21)-10;
		int sign = rand.nextInt(2);
		if(sign==0)
			sign=-1;
		int y = (10-Math.abs(x))*sign;
		//this.knockback(x,y);
	}
	protected void hover(){
		if(hoverDistance==0){
			hoverCurrentDistance-=hoverAcceleration;
			if(hoverCurrentDistance<hoverDistance){
				hoverDistance = 10;
			}
		}else{
			hoverCurrentDistance+=hoverAcceleration;
			if(hoverCurrentDistance>hoverDistance){
				hoverDistance = 0;
				
			}
		}
	}
	public double setCurrentHover(double hover){
		hoverCurrentDistance = hover;
		return hoverCurrentDistance;
	}
	
	protected void setItemID(int ID){
		itemID=ID;
	}
	
	public int getItemID(){
		return itemID;
	}
	
	@Override
	public double melee() {
		// TODO Auto-generated method stub
		return 0;
	}
	public int pickupCooldown(){
		pickupCooldown = pickupReset;
		return pickupCooldown;
	}
	public void setPickupCooldown(int x){
		pickupCooldown = x;
	}
	public void tickPickup(){
		if(pickupCooldown>0)
			pickupCooldown--;
	}
	public void setPickupCooldownReset(int cooldownReset){
		pickupReset = cooldownReset;
	}
	public void setName(String name){
		this.name = name;
	}
	public void setSubText(String sub){
		this.subText = sub;
	}
	public String getName(){
		return name;
	}
	public String getSubText(){
		return subText;
	}

	@Override
	public abstract void tick();
	public abstract void render(Graphics win);
	public abstract boolean pickup(Player p);

}
