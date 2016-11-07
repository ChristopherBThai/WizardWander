
package entity.projectile;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.Random;

import entity.particle.Particle;
import entity.util.Entity;
import environment.block.Block;

public class BubbleProjectile extends AbsProjectile{
	protected Ellipse2D.Double proj;
	
	static Random rand = new Random();
	
	Color bubble;
	int colorChange;
	
	
	public BubbleProjectile(int xcord, int ycord, double xvel, double yvel,double dmg, double critChance,double knockback){
		super(xcord,ycord,xvel,yvel,dmg,critChance,knockback);
		proj = new Ellipse2D.Double(xcord,ycord,20,20);
		x = xcord;
		y = ycord;
		this.xvel = xvel;
		this.yvel = yvel;
		this.dmg = dmg;
		criticalChance = critChance;
		this.knockback = knockback;
		bubble = Color.WHITE;
	}
	public BubbleProjectile(){
		super(-1000,-1000,0,0,0,0,0);
		proj = new Ellipse2D.Double(-1000,-1000,20,20);
	}
	
	public double getRadius(){
		return proj.width/2;
	}
	
	public void move(){
		x+=xvel;
		y+=yvel;
		proj.setFrame((int)x,(int)y,20,20);
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
		Graphics2D g2 = (Graphics2D) g;
		Composite c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,.6f);
		g2.setComposite(c);
		g.drawImage(sprite.getBubble(), (int)this.proj.getX(),(int)proj.getY(),null);
		c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,.2f);
		g2.setComposite(c);
		g.setColor(bubble);
		g.fillOval((int)x, (int)y, 20, 20);
		c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f);
		g2.setComposite(c);
		
	}
	public void tick(){
		move();
		
		if(colorChange%10==0)
			bubble = new Color((int)rand.nextInt(155)+100,(int)rand.nextInt(155)+100,(int)rand.nextInt(155)+100);
		colorChange++;
	}
	@Override
	public void renderHitbox(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawOval((int)proj.getX(), (int)proj.getY(), (int)proj.getWidth(), (int)proj.getHeight());
		
	}
}
