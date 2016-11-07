package entity.projectile;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import main.util.Sprite;
import entity.util.Entity;
import entity.util.PercentToBoolean;
import environment.block.Block;

public class Projectile extends AbsProjectile{
	protected Rectangle proj;
	
	
	public Projectile(int xcord, int ycord, double xvel, double yvel,double dmg, double critChance,double knockback){
		super(xcord,ycord,xvel,yvel,dmg,critChance,knockback);
		proj = new Rectangle(xcord, ycord, 20, 20);
		x = xcord;
		y = ycord;
		this.xvel = xvel;
		this.yvel = yvel;
		this.dmg = dmg;
		criticalChance = critChance;
		this.knockback = knockback;
		this.setHover(17);
	}
	public Projectile(){
		super(-1000,-1000,0,0,0,0,0);
		proj = new Rectangle(-1000,-1000,20,20);
	}
	
	public double getRadius(){
		return proj.width/2;
	}
	
	public void move(){
		x+=xvel;
		y+=yvel;
		proj.setLocation((int)x,(int)y);
	}
	public boolean collide(Entity e){
		if(this.proj.intersects(e.getEntity())){
			if(PTB.roll(criticalChance)){
			e.damage(dmg +dmg*.5);
		}else
			e.damage(dmg);
		return true;
		}	
		return false;
	}
	public boolean collide(Block b){
		if(this.proj.intersects(b.getBlock()))
			return true;
		return false;
	}
	
	public void render(Graphics g){
		this.renderShadow(this.proj.getX(), this.proj.getY(), this.proj.getWidth(), this.proj.getHeight(), g);
		Graphics2D g2 = (Graphics2D) g;
		Composite c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,.3f);
		g2.setComposite(c);
		g.drawImage(sprite.getProjectile(), (int)this.proj.getX(),(int)proj.getY(),null);
		c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f);
		g2.setComposite(c);
		
	}
	public void tick(){
		move();
	}
	@Override
	public void renderHitbox(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawRect((int)proj.getX(), (int)proj.getY(), (int)proj.getWidth(), (int)proj.getHeight());
		
	}
}
