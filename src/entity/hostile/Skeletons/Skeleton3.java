package entity.hostile.Skeletons;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;

import entity.hostile.Skeleton;
import entity.item.weapon.BasicWand;
import entity.item.weapon.WeaponItem;

public class Skeleton3 extends Skeleton{//22x64 - 35x64
	WeaponItem weapon;
	
	public Skeleton3(int x, int y, int health) {
		super(x, y, health);
		this.setType(3);
		this.setSize(22, 41);
		weapon = new BasicWand(0,0);
		this.setAttackSpeed(.6);
		this.setDamage(2);
		weapon.setKnockback(.1);
	}
	

	@Override
	public void tick() {
		
		
		this.healthTick();
		this.move();
		this.shoot();
		this.meleeTick();
	}
	private void shoot(){
		if(this.FollowingEntity!=null){
			double xdiff = this.FollowingEntity.getXCenter()-this.getXCenter();
			double ydiff = this.FollowingEntity.getYCenter()-this.getYCenter();
			if(Math.abs(xdiff)>Math.abs(ydiff)&&Math.abs(this.getYCenter()-this.FollowingEntity.getYCenter())<=this.getHeight()/2+this.FollowingEntity.getHeight()/2){
				
				if(xdiff>0){
					weapon.shootRight(this);
				}else{
					weapon.shootLeft(this);
				}
				
				
			}else if(Math.abs(xdiff)<Math.abs(ydiff)&&Math.abs(this.getXCenter()-this.FollowingEntity.getXCenter())<=this.getWidth()/2+this.FollowingEntity.getWidth()/2){
				
				if(ydiff>0){
					weapon.shootDown(this);
				}else{
					weapon.shootUp(this);
				}
				
			}
		}
		weapon.projectileTick(this);
	}
	
	@Override
	public void render(Graphics win) {
		if(this.getFace()==0)
			win.drawImage(sprite.getSkeleton()[type][this.getFace()], (int)getEntity().getX(),(int)getEntity().getY()-(62-41), null);
		else if(this.getFace()==1)
			win.drawImage(sprite.getSkeleton()[type][this.getFace()], (int)getEntity().getX()-13,(int)getEntity().getY()-(62-41), null);
		healthRender(win);
		this.renderDamaged(win);
		
	}
	@Override
	public void renderDamaged(Graphics g) {
		if(damageFrame>0){
			damageFrame--;
			Graphics2D g2 = (Graphics2D) g;
			Composite c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,.6f);
			g2.setComposite(c);
			if(this.getFace()==0)
				g.drawImage(sprite.damaged(sprite.getSkeleton()[this.type][this.getFace()]), (int)getEntity().getX(),(int)getEntity().getY()-(62-41), null);
			else if(this.getFace()==1)
				g.drawImage(sprite.damaged(sprite.getSkeleton()[this.type][this.getFace()]), (int)getEntity().getX()-13,(int)getEntity().getY()-(62-41), null);
			c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f);
			g2.setComposite(c);
		}		
	}
}
