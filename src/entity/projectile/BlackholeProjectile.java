package entity.projectile;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import main.util.Audio;
import entity.hostile.HostileEntity;
import entity.item.ItemEntity;
import entity.item.active.Blackhole;
import entity.particle.BlackHoleParticle;
import entity.util.Entity;
import environment.block.Block;

public class BlackholeProjectile extends AbsProjectile{

	Blackhole spell;
	Audio a = new Audio();
	Ellipse2D.Double blackhole;
	
	private int SMALL_DIAMETER,BIG_DIAMETER;
	
	int tick = -50;
	int suckTime = 4;
	int suckRadius = 600;
	
	public BlackholeProjectile(double x, double y, double xvel, double yvel,double dmg,Blackhole spell) {
		super(x, y, xvel, yvel, dmg, 0, 0);
		this.spell = spell;
		SMALL_DIAMETER = 10;
		BIG_DIAMETER = 80;
		blackhole = new Ellipse2D.Double(x,y,SMALL_DIAMETER,SMALL_DIAMETER);
	}

	@Override
	public void tick() {
		if(tick==-1){
			tick = suckTime*60;
			a.playBlackhole();
		}
		if(tick<0){
			tick++;
			x+=xvel;
			y+=yvel;
			blackhole.setFrame((int)x, (int)y, blackhole.getWidth(), blackhole.getHeight());
		}else if(tick>0){
			if(blackhole.getWidth()<=BIG_DIAMETER){
				x-=4;
				y-=4;
				blackhole.setFrame((int)x, (int)y, blackhole.getWidth()+8, blackhole.getHeight()+8);
			}
			
			EH.addPA(new BlackHoleParticle((int)(this.x+blackhole.getWidth()/2),(int)(this.y+blackhole.getHeight()/2),3,3,150));
			
			ArrayList<HostileEntity> E = EH.getE();
			ArrayList<ItemEntity> S = EH.getS();
			
			for(int i = 0;i<E.size();i++){
				double xDiff = E.get(i).getXCenter()-(blackhole.getX()+blackhole.getWidth()/2);
				double yDiff = E.get(i).getYCenter()-(blackhole.getY()+blackhole.getHeight()/2);
				double radius  = Math.sqrt((xDiff*xDiff)+(yDiff*yDiff));
				if(radius<=this.suckRadius&&radius>15&&xDiff!=0&&yDiff!=0){
					E.get(i).collide(-xDiff/(Math.abs(xDiff)*2), -yDiff/(Math.abs(yDiff)*2));
				}
			}
			for(int i = 0;i<S.size();i++){
				double xDiff = S.get(i).getXCenter()-(blackhole.getX()+blackhole.getWidth()/2);
				double yDiff = S.get(i).getYCenter()-(blackhole.getY()+blackhole.getHeight()/2);
				double radius  = Math.sqrt((xDiff*xDiff)+(yDiff*yDiff));
				if(radius<=this.suckRadius&&radius>15){
					if(xDiff==0)
						S.get(i).collide(0, -yDiff/(Math.abs(yDiff)*2));
					else if(yDiff==0)
						S.get(i).collide(-xDiff/(Math.abs(xDiff)*2), 0);
					else
						S.get(i).collide(-xDiff/(Math.abs(xDiff)*2), -yDiff/(Math.abs(yDiff)*2));
				}
			}
			
			tick--;
		}
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Composite c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,.9f);
		g2.setComposite(c);
		g.setColor(Color.BLACK);
		g.fillOval((int)blackhole.getX(), (int)blackhole.getY(), (int)blackhole.getWidth(), (int)blackhole.getHeight());
		c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f);
		g2.setComposite(c);
		
	}

	@Override
	public boolean collide(Entity e) {
		if(tick<0&&blackhole.intersects(e.getEntity())){
			tick = suckTime*60;
		}
		if(tick>0){
			
		}
		return false;
	}

	@Override
	public boolean collide(Block b) {
		if(tick<0&&blackhole.intersects(b.getBlock())){
			tick = suckTime*60;
		}
		if(tick==0)
			return true;
		return false;
	}

	@Override
	public void renderHitbox(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawOval((int)blackhole.getX(), (int)blackhole.getY(), (int)blackhole.getWidth(), (int)blackhole.getHeight());
	}

}
