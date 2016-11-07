package entity.projectile;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entity.item.active.Laser;
import entity.util.Entity;
import environment.block.Block;

public class LaserProjectile extends AbsProjectile{
	
	static ArrayList<Entity> hit;
	
	
	Laser spell;
	Rectangle laser;
	int direction;
	
	Double dmg;
	
	boolean done;
	
	final int WIDTH,HEIGHT;

	public LaserProjectile(double x, double y,int width,int length,double dmg,int direction,Laser spell) {
		super(x, y, 0, 0, dmg, 0, 0);
		this.spell = spell;
		this.direction = direction;
		done = false;
		WIDTH=width;
		HEIGHT=length;
		
		this.dmg = dmg;
		
		hit = new ArrayList<Entity>();
		
		if(direction == 0){
			laser = new Rectangle((int)x,(int)y,WIDTH,0);
		}else if(direction == 1){
			laser = new Rectangle((int)x,(int)y,0,WIDTH);
		}else if(direction ==2){
			laser = new Rectangle((int)x,(int)y,WIDTH,0);
		}else{
			laser = new Rectangle((int)x,(int)y,0,WIDTH);
		}
		
	}
	public LaserProjectile(double x, double y,int width,int length,double dmg,int direction) {
		super(x, y, 0, 0, dmg, 0, 0);
		this.spell = null;
		this.direction = direction;
		done = false;
		WIDTH=width;
		HEIGHT=length;
		
		this.dmg = dmg;
		
		hit = new ArrayList<Entity>();
		
		if(direction == 0){
			laser = new Rectangle((int)x,(int)y,WIDTH,0);
		}else if(direction == 1){
			laser = new Rectangle((int)x,(int)y,0,WIDTH);
		}else if(direction ==2){
			laser = new Rectangle((int)x,(int)y,WIDTH,0);
		}else{
			laser = new Rectangle((int)x,(int)y,0,WIDTH);
		}
		
	}
	public LaserProjectile(){
		super(0,0,0,0,0,0,0);
		HEIGHT = 0;
		WIDTH = 0;
	}

	@Override
	public void tick() {
		if(!done){
			for(int i=0;i<HEIGHT;i++){
				if(direction == 0){
					laser.setSize((int)laser.getWidth(), (int)laser.getHeight()+1);
					laser.setLocation((int)laser.getX(),(int)laser.getY()-1);
				}else if(direction == 1){
					laser.setSize((int)laser.getWidth()+1, (int)laser.getHeight());
				}else if(direction ==2){
					laser.setSize((int)laser.getWidth(), (int)laser.getHeight()+1);
				}else{
					laser.setSize((int)laser.getWidth()+1, (int)laser.getHeight());
					laser.setLocation((int)laser.getX()-1,(int)laser.getY());
				}
			}
		}else if(laser.getWidth()>0&&laser.getHeight()>0){
			if(direction == 0){
				laser.setSize((int)laser.getWidth()-4, (int)laser.getHeight());
				laser.translate(2, 0);
			}else if(direction == 1){
				laser.setSize((int)laser.getWidth(), (int)laser.getHeight()-4);
				laser.translate(0, 2);
			}else if(direction ==2){
				laser.setSize((int)laser.getWidth()-4, (int)laser.getHeight());
				laser.translate(2, 0);
			}else{
				laser.setSize((int)laser.getWidth(), (int)laser.getHeight()-4);
				laser.translate(0, 2);
			}
			
		}
		if(laser.getWidth()<0){
			laser.setSize(0, (int)laser.getHeight());
		}
		if(laser.getHeight()<0){
			laser.setSize((int)laser.getWidth(), 0);
		}
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Composite c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,.9f);
		g2.setComposite(c);
		//g.setColor(Color.RED);
		//g.fillRect((int)this.laser.getX(), (int)this.laser.getY(), (int)this.laser.getWidth(), (int)this.laser.getHeight());
		int width=(int)this.laser.getWidth();
		int height=(int)this.laser.getHeight();
		if(width<=0)
			width = 1;
		if(height<=0)
			height = 1;
		BufferedImage laserImg;
		if(direction == 1||direction == 3)
			laserImg = sprite.resize(sprite.getLaserH(),width , height);
		else
			laserImg = sprite.resize(sprite.getLaserV(),width , height);
		g.drawImage(laserImg,(int)this.laser.getX(), (int)this.laser.getY(),null);
		laserImg.flush();
		c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f);
		g2.setComposite(c);
	}

	@Override
	public boolean collide(Entity e) {
		if(laser.intersects(e.getEntity())&&!hit.contains(e)){
			e.damage(dmg);
			OF.addFire(e, dmg/5, 5);
			if(spell!=null)
				spell.addXp(1);
			hit.add(e);
		}
		return false;
	}

	@Override
	public boolean collide(Block b) {
		if(b.getBlock().intersects(laser)||laser.getWidth()*laser.getHeight()>=WIDTH*1000){
			for(int i=0;i<HEIGHT;i++){
				if(direction == 0){
					laser.setSize((int)laser.getWidth(), (int)laser.getHeight()-1);
					laser.setLocation((int)laser.getX(),(int)laser.getY()+1);
				}else if(direction == 1){
					laser.setSize((int)laser.getWidth()-1, (int)laser.getHeight());
				}else if(direction ==2){
					laser.setSize((int)laser.getWidth(), (int)laser.getHeight()-1);
				}else{
					laser.setSize((int)laser.getWidth()-1, (int)laser.getHeight());
					laser.setLocation((int)laser.getX()+1,(int)laser.getY());
				}
				if(!b.getBlock().intersects(laser)){
					i = HEIGHT;
				}
			}
			done = true;
		}
		if(done&&(laser.getWidth()<=0||laser.getHeight()<=0)){
			hit.clear();
			return true;
		}
		return false;
	}
	public int getWidth(){
		return WIDTH;
	}
	@Override
	public void renderHitbox(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawRect((int)laser.getX(), (int)laser.getY(), (int)laser.getWidth(), (int)laser.getHeight());
		
		
	}

}
