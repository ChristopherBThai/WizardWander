package entity.particle;

import java.awt.Color;
import java.awt.Graphics;

public class TrailParticle implements Particle{

	double x, y;
	double width, height;
	double xvel, yvel;
	Color c;
	int interval = 20,tick;
	
	
	public TrailParticle(double x, double y,int width, int height,double xvel, double yvel,Color c){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.xvel = xvel;
		this.yvel = yvel;
		this.c = c;
		tick = interval * Math.max(width, height);
	}
	
	@Override
	public boolean isDone() {
		if(tick<0||width<=0||height<=0)
			return true;
		return false;
	}

	@Override
	public void tick() {
		if(tick%interval == 0){
			width-=2;
			height-=2;
			x++;
			y++;
		}
		tick--;
		x+=xvel/3;
		y+=yvel/3;
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(c);
		g.fillRect((int)x, (int)y, (int)width, (int)height);
		
		
	}

}
