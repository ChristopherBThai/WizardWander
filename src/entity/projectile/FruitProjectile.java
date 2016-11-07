package entity.projectile;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import entity.util.Entity;
import environment.block.Block;

public class FruitProjectile extends AbsProjectile{
	protected Rectangle proj;
	
	short fruit;
	double rotate;
	
	BufferedImage Sprite;
	
	
	public FruitProjectile(int xcord, int ycord, double xvel, double yvel,double dmg, double critChance,double knockback){
		super(xcord,ycord,xvel,yvel,dmg,critChance,knockback);
		proj = new Rectangle(xcord, ycord, 20, 20);
		x = xcord;
		y = ycord;
		this.xvel = xvel;
		this.yvel = yvel;
		this.dmg = dmg;
		criticalChance = critChance;
		this.knockback = knockback;
		fruit = (short) ((short)Math.random()*0);
		rotate = Math.random()*(2*Math.PI*100);
		this.setHover(17);
		Sprite = sprite.getFruits()[(int) (Math.random()*sprite.getFruits().length)];
	}
	public FruitProjectile(){
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
		
		AffineTransform identity = new AffineTransform();

		Graphics2D g2d = (Graphics2D)g;
		AffineTransform trans = new AffineTransform();
		
		trans.setTransform(identity);
		trans.translate(Sprite.getHeight() / 2,Sprite.getWidth() / 2);
		trans.rotate( Math.toRadians(rotate) );
		trans.translate(-Sprite.getWidth() / 2,-Sprite.getHeight() / 2);
		
		AffineTransformOp op = new AffineTransformOp(trans, AffineTransformOp.TYPE_BILINEAR);
		BufferedImage test =new BufferedImage(Sprite.getHeight(), Sprite.getWidth(), Sprite.getType());
		op.filter(Sprite, test);

		g.drawImage(test,  (int)this.proj.getX(),(int)proj.getY(), null);
		test.flush();
	}
	public void tick(){
		move();
		rotate+=6;
	}
	@Override
	public void renderHitbox(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawRect((int)proj.getX(), (int)proj.getY(), (int)proj.getWidth(), (int)proj.getHeight());
		
		
	}
}
