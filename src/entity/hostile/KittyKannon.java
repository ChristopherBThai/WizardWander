package entity.hostile;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;

import entity.hostile.movement.SmartFollow;
import entity.item.weapon.LaserWand;
import entity.item.weapon.WeaponItem;
import entity.particle.BlackHoleParticle;
import entity.particle.Particle;

public class KittyKannon extends SmartFollow{

	WeaponItem weapon;
	
	int shootDelay,shootDelayMax,shootCooldown;
	boolean right,left,up,down;
	
	public KittyKannon(int x, int y, int health) {
		super(2.3, 2.3, 32, 18,
				health);
		this.setLocation(x, y);
		weapon = new LaserWand(0,0);
		weapon.setSize(30, 100);
		this.setAttackSpeed(1);
		this.setDamage(2);
		weapon.setKnockback(.1);
		shootDelayMax=40;
		shootCooldown=100;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		
		
		this.healthTick();
		if(shootDelay>shootCooldown){
			this.setCurrentX(0);
			this.setCurrentY(0);
			this.move(0,0);
		}
		if(shootDelay>0){
			shootDelay--;
		}
		if(shootDelay>shootDelayMax/2+shootCooldown){
			BlackHoleParticle charge = new BlackHoleParticle((int)this.getXCenter(),(int)this.getYCenter(),3,3,100);
			charge.setColor((Color.RED));
			charge.setAccel(1);
			EH.addPA(charge);
		}
		this.move();
		this.shoot();
		//this.meleeTick();
	}
	private void shoot(){
		if(this.FollowingEntity!=null){
			double xdiff = this.FollowingEntity.getXCenter()-this.getXCenter();
			double ydiff = this.FollowingEntity.getYCenter()-this.getYCenter();
			if(Math.abs(xdiff)>Math.abs(ydiff)&&Math.abs(this.getYCenter()-this.FollowingEntity.getYCenter())<=this.getHeight()/2+this.FollowingEntity.getHeight()/2){
				
				if(xdiff>0){
					right=true;left=false;up=false;down=false;
				}else{
					right=false;left=true;up=false;down=false;
				}
				
				if(shootDelay<=0){
					shootDelay=shootDelayMax+shootCooldown;
				}
				
				
			}else if(Math.abs(xdiff)<Math.abs(ydiff)&&Math.abs(this.getXCenter()-this.FollowingEntity.getXCenter())<=this.getWidth()/2+this.FollowingEntity.getWidth()/2){
				
				if(ydiff>0){
					right=false;left=false;up=false;down=true;
				}else{
					right=false;left=false;up=true;down=false;
				}
				
				if(shootDelay<=0){
					shootDelay=shootDelayMax+shootCooldown;
				}
			}
			if(shootDelay==shootCooldown){
				if(right){
					weapon.shootRight(this);
				}else if(left){
					weapon.shootLeft(this);
				}else if(down){
					weapon.shootDown(this);
				}else if(up){
					weapon.shootUp(this);
				}
			}
		}
		weapon.projectileTick(this);
	}

	@Override
	public void render(Graphics win) {
		win.drawImage(sprite.getCat()[this.getFace()], (int)getEntity().getX()-14,(int)getEntity().getY()-(21-17)-43, null);	
		this.healthRender(win);
		this.renderDamaged(win);
	}

	@Override
	public void drop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void renderDamaged(Graphics g) {
		if(damageFrame>0){
			damageFrame--;
			Graphics2D g2 = (Graphics2D) g;
			Composite c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,.6f);
			g2.setComposite(c);g.drawImage(sprite.damaged(sprite.getCat()[this.getFace()]),(int)getEntity().getX()-14,(int)getEntity().getY()-(21-17)-43, null);
			c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f);
			g2.setComposite(c);
		}
		
	}


}
