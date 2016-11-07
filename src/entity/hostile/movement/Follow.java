package entity.hostile.movement;

import java.awt.Graphics;
import java.awt.Rectangle;

import entity.hostile.HostileEntity;
import entity.util.Entity;

public abstract class Follow extends HostileEntity{
	int face;

	public Follow(double inputVerticleSpeed, double inputHorizontalSpeed,int entityWidth, int entityHeight, int maxhealth) {
		super(inputVerticleSpeed, inputHorizontalSpeed, entityWidth, entityHeight, maxhealth);
		changeAcceleration(.2);
	}
	
	public void follow(Entity e){
		if(getXCenter()>e.getXCenter()){
			super.moveX(this.getLeft());
			face = 1;
		}else if(getXCenter()<e.getXCenter()){
			super.moveX(this.getRight());
			face = 0;
		}else
			super.moveX(0);
		
		
		if(getYCenter()>e.getYCenter())
			super.moveY(this.getUp());
		else if(this.getYCenter()<e.getYCenter())
			super.moveY(this.getDown());
		else
			super.moveY(0);
	}
	public int getFace(){
		return face;
	}
	

	@Override
	public abstract void tick();

	@Override
	public abstract void render(Graphics win);

}
