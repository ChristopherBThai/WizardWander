package entity.particle;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class PotionParticle implements Particle{
	
	Color c;
	Rectangle particle;
	
	float transparency;
	
	double x,y;
	double xvel,yvel;
	double vel;
	double accel;
	
	public PotionParticle(int x, int y, int width, int height,Color c){
		this.x = x;
		this.y = y;
		this.c = c;
		particle = new Rectangle(x,y,width,height);
		yvel = 0;
		xvel = Math.random()*3-1.5;
		vel = 1.5;
		accel = 0.05;
		transparency = 1;
	}
	
	@Override
	public boolean isDone() {
		if(transparency<=0)
			return true;
		return false;
	}

	@Override
	public void tick(){
		x+=xvel;
		y+=yvel;
		if(Math.abs(xvel)<accel){
			xvel = 0;
		}else if(xvel>0){
			xvel-=accel;
		}else if(xvel<0){
			xvel+=accel;
		}
		if(yvel<vel){
			yvel-=accel;
		}
		transparency -= 0.015;
		if(transparency<=0){
			transparency = 0;
		}
		
		particle.setLocation((int)x,(int)y);
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Composite c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,(float)transparency);
		g2.setComposite(c);
		g.setColor(this.c);
		g.drawRect((int)particle.getX(),(int)particle.getY(), (int)particle.getWidth(), (int)particle.getHeight());
		c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f);
		g2.setComposite(c);
		
		
	}

}
