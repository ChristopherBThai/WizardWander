package entity.particle;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import main.util.Audio;

public class BlackHoleParticle implements Particle{

	Rectangle blackhole;
	Color c;
	
	
	int xCenter,yCenter;
	
	double x,y;
	
	double xvel,yvel,vel;
	double accel = 0.1;
	
	public BlackHoleParticle(int xCenter, int yCenter, int width, int height,int radius){
		this.xCenter = xCenter;
		this.yCenter = yCenter;
		c = Color.BLACK;
		double radians = Math.random()*(2*Math.PI);
		this.x = xCenter + Math.cos(radians)*radius;
		this.y = yCenter + Math.sin(radians)*radius;
		blackhole = new Rectangle((int)this.x,(int)this.y,width,height);
		vel = 0;
	}
	public void setColor(Color c){
		this.c = c;
	}
	public void setAccel(double accel){
		this.accel = accel;
	}
	
	@Override
	public boolean isDone() {
		if(Math.abs(this.getXDiff())<Math.abs(xvel)&&Math.abs(this.getYDiff())<Math.abs(yvel)){
			return true;
		}
		return false;
	}

	@Override
	public void tick() {
		vel += accel;
		xvel = vel*Math.cos(this.getTheta());
		yvel = vel*Math.sin(this.getTheta());
		x+=xvel;
		y+=yvel;
		blackhole.setLocation((int)x, (int)y);
	}
	private double getDistance(){
		double xdiff = xCenter - x;
		double ydiff = yCenter - y;
		double distance = Math.sqrt(xdiff*xdiff + ydiff*ydiff);
		return distance;
	}
	private double getTheta(){
		return Math.atan2(this.getYDiff(), this.getXDiff());
	}
	private double getXDiff(){
		double xdiff = xCenter - x;
		return xdiff;
	}
	private double getYDiff(){
		double ydiff = yCenter - y;
		return ydiff;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(c);
		g.fillRect((int)blackhole.getX(), (int)blackhole.getY(), (int)blackhole.getWidth(), (int)blackhole.getHeight());
		
	}

}
