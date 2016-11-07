package entity.projectile;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import entity.item.active.Fireball;
import entity.particle.OnFire;
import entity.particle.TrailParticle;
import entity.util.Entity;
import environment.block.Block;

public class FireballProjectile extends AbsProjectile{
	Ellipse2D.Double boom = new Ellipse2D.Double(-1000, -1000, 200, 200);
	
	Polygon fireball;
	double x1,x2,x3,x4,y1,y2,y3,y4; //center
	final double width, height;
	
	static Color particle[];
	
	protected static OnFire OF = new OnFire();
	
	Fireball spell;
	
	int x,y,shadowWidth,shadowHeight;
	
	BufferedImage[] fireballSprite;
	BufferedImage shadow;
	boolean init;
	short fireballFrame; 
	double rotate;
	
	double scale;
	
	public FireballProjectile(double x,double y,double xvel,double yvel,double damage,Fireball spell){
		super((int)x, (int)y, xvel, yvel,damage,0,0);
		this.spell = spell;
		
		particle = new Color[5];
		particle[0] = Color.RED;
		particle[1] = Color.YELLOW;
		particle[2] = Color.YELLOW;
		particle[3] = Color.YELLOW;
		particle[4] = Color.RED;
		scale = 3;
		width = 60;//(int)(sprite.getFireball()[0].getWidth()*scale);
		height = 60;//(int)(sprite.getFireball()[0].getHeight()*scale);
		
		x1 = x+width/2;
		y1 = y+height/2;
		x2 = x-width/2;
		y2 = y+height/2;
		x3 = x-width/2;
		y3 = y-height/2;
		x4 = x+width/2;
		y4 = y-height/2;
		
		double slope = -yvel/xvel;
		double xpos = Math.sqrt(1/(1+Math.pow(slope, 2)));
		if(xvel<0)
			xpos = -xpos;
		double ypos = Math.sqrt(1-Math.pow(xpos, 2));
		if(yvel>0)
			ypos = -ypos;
		double radiant = Math.atan2(ypos,xpos);
		double radius = Math.sqrt(Math.pow(width/2.0, 2)+Math.pow(height/2.0, 2));
		
		rotate = radiant;

		
		double radiant1 = Math.atan2(y1-y, x-x1);
		radiant1 += radiant;
		x1 = x-Math.cos(radiant1)*radius;
		y1 = y+Math.sin(radiant1)*radius;
		
		radiant1 = Math.atan2(y2-y, x-x2);
		radiant1 += radiant;
		x2 = x-Math.cos(radiant1)*radius;
		y2 = y+Math.sin(radiant1)*radius;
		
		radiant1 = Math.atan2(y3-y, x-x3);
		radiant1 += radiant;
		x3 = x-Math.cos(radiant1)*radius;
		y3 = y+Math.sin(radiant1)*radius;
		
		radiant1 = Math.atan2(y4-y, x-x4);
		radiant1 += radiant;
		x4 = x-Math.cos(radiant1)*radius;
		y4 = y+Math.sin(radiant1)*radius;
		
		int xA[] = {(int)x1,(int)x2,(int)x3,(int)x4};
		int yA[] = {(int)y1,(int)y2,(int)y3,(int)y4};
		fireball = new Polygon(xA,yA,4);
		
		double big,small;
		
		small = Math.min(x1, x2);
		small = Math.min(x3, small);
		small = Math.min(x4, small);
		
		if(small==x1)
			this.x = 0;
		if(small==x2)
			this.x = 1;
		if(small==x3)
			this.x = 2;
		if(small==x4)
			this.x = 3;
		
		big = Math.max(x1, x2);
		big = Math.max(x3, big);
		big = Math.max(x4, big);
		/*
		System.out.println("0:"+x1);
		System.out.println("1:"+x2);
		System.out.println("2:"+x3);
		System.out.println("3:"+x4);
		System.out.println("Big:"+ big);
		System.out.println("Small:"+small);
		System.out.println("x:"+x);
		*/
		shadowWidth = (int)Math.abs(small-big);
		
		small = Math.min(y1, y2);
		small = Math.min(y3, small);
		small = Math.min(y4, small);
		
		big = Math.max(y1, y2);
		big = Math.max(y3, big);
		big = Math.max(y4, big);
		
		if(small==y1)
			this.y = 0;
		if(small==y2)
			this.y = 1;
		if(small==y3)
			this.y = 2;
		if(small==y4)
			this.y = 3;
		
		shadowHeight = (int)Math.abs(small-big);
		
		
		
		this.setHover(17);
		
	}
	public void initImage(){
		fireballSprite = new BufferedImage[sprite.getFireball().length];
		for(int i = 0; i<fireballSprite.length;i++){
			AffineTransform identity = new AffineTransform();

			AffineTransform trans = new AffineTransform();
		
			BufferedImage temp = sprite.resize(sprite.getFireball()[i],(int)(width),(int)(height));
			
			trans.setTransform(identity);
			trans.translate(temp.getHeight() / 2,temp.getWidth() / 2);
			trans.rotate(-rotate);
			trans.translate(-temp.getWidth() / 2,-temp.getHeight() / 2);
			
			AffineTransformOp op = new AffineTransformOp(trans, AffineTransformOp.TYPE_BILINEAR);
			BufferedImage test =new BufferedImage(temp.getHeight(), temp.getWidth(), temp.getType());
			op.filter(temp, test);
			fireballSprite[i] = test;
		}
		init = true;
				

		shadow = sprite.getGlow();
		shadow = sprite.resize(shadow, shadowWidth+4, shadowHeight/2);
		shadow = sprite.recolor(shadow, Color.RED);
		
	}
	
	public void move(){
		x1 += xvel;
		y1 += yvel;
		x2 += xvel;
		y2 += yvel;
		x3 += xvel;
		y3 += yvel;
		x4 += xvel;
		y4 += yvel;
		int xA[] = {(int)x1,(int)x2,(int)x3,(int)x4};
		int yA[] = {(int)y1,(int)y2,(int)y3,(int)y4};
		fireball.xpoints = xA;
		fireball.ypoints = yA;
	}
	
	
	public void render(Graphics win){
		if(!init)
			this.initImage();
		this.renderShadow(fireball.xpoints[x], fireball.ypoints[y], shadowWidth, shadowHeight, win);
		

		win.drawImage(fireballSprite[this.fireballFrame],(int)(fireball.xpoints[x]),(int)(fireball.ypoints[y]), null);
		
	}
	public void renderShadow(double x, double y, double width,double height,Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		Composite c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,.5f);
		g2.setComposite(c);
		g.setColor(Color.RED);
		
		//g.fillOval((int)x-2,(int)(y+height-(height/4))+hover,(int)width+4, (int)height/2);
		g.drawImage(shadow,(int)x-2,(int)(y+height-(height/4))+hover,null);
		c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f);
		g2.setComposite(c);
	}
	
	public boolean collide(Entity e){
		if((new Area(fireball)).intersects(e.getEntity())){
			boom.setFrame((int)(fireball.xpoints[0]-(fireball.xpoints[0]-fireball.xpoints[2])/2.0-boom.getWidth()/2),(int)(fireball.ypoints[0]-(fireball.ypoints[0]-fireball.ypoints[2])/2.0-boom.getHeight()/2),boom.getWidth(),boom.getHeight());
			EH.addP(new FireballExplosion(boom,spell,dmg));
			return true;
		}
		return false;
	}
	public boolean collide(Block b){
		/*
		if((new Area(fireball)).intersects(b.getBlock())){
			boom.setFrame((int)(fireball.xpoints[0]-(fireball.xpoints[0]-fireball.xpoints[2])/2.0-boom.getWidth()/2),(int)(fireball.ypoints[0]-(fireball.ypoints[0]-fireball.ypoints[2])/2.0-boom.getHeight()/2),boom.getWidth(),boom.getHeight());
			EH.addP(new FireballExplosion(boom,spell,dmg));
			return true;
		}
		*/
		return false;
	}
	private void particle(){
		double x = x1-(x1-x3)/2+(Math.random()*height-height/2);
		double y = y1-(y1-y3)/2+(Math.random()*height-height/2);
		int width = 5;
		int height = 5;
		EH.addPA(new TrailParticle(x,y,width,height,xvel,yvel,particle[(int)(Math.random()*particle.length)]));
	}
	public void tick(){
		move();
		particle();
		fireballFrame++;
		fireballFrame %= sprite.getFireball().length;
	}
	public double getWidth(){
		return width;
	}
	public double getHeight(){
		return height;
	}

	@Override
	public void renderHitbox(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawPolygon(fireball);
		
	}
		
}

