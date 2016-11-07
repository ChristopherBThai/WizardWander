package entity.item.weapon;

import java.awt.Graphics;

import main.util.EntityHolder;
import entity.hostile.HostileEntity;
import entity.item.ItemEntity;
import entity.player.Player;
import entity.util.Entity;
import entity.util.ProjectileKeyHelper;

public abstract class WeaponItem extends ItemEntity{
	
	double knockback;
	
	protected static EntityHolder EH;
	protected static ProjectileKeyHelper PKH;
	
	
	public WeaponItem(int x, int y) {
		super(32, 32);
		this.setLocation((int)x, (int)y);
		this.setItemID(100);
		this.setAcceleration(.4);
		PKH = new ProjectileKeyHelper();
		EH = new EntityHolder();
	}
	

	@Override
	public void tick() {
		this.tickPickup();
		
		this.move(0,0);
		this.move();
		this.hover();
		
	}
	public void setKnockback(double x){
		knockback = x;
	}
	public double getKnockback(){
		return knockback;
	}

	@Override
	public abstract void render(Graphics win);
	public abstract void projectileTick(Player e);
	public abstract void projectileTick(HostileEntity e);
	public abstract void shootRight(Entity e);
	public abstract void shootLeft(Entity e);
	public abstract void shootUp(Entity e);
	public abstract void shootDown(Entity e);

	@Override
	public boolean pickup(Player p){
		if(pickupCooldown>0){
			return false;
		}else{
			p.dropWeapon();
			p.setWeapon(this);
			return true;
		}
		
	}
	
}
