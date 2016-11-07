package entity.projectile;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import entity.player.Player;
import entity.util.Entity;
import environment.block.Block;

public class DashProjectile extends AbsProjectile{

	private int amountOfEffect = 12;
	private int delay = 2;
	private int tick;
	
	protected short face;
	
	private static Player p;
	
	private ArrayList<Effect> effect = new ArrayList<Effect>();
	
	public DashProjectile(Player p) {
		super(p.getX(), p.getY()-(64-p.getHeight()), 0, 0, 0, 0, 0);
		this.p = p;
		face = p.getFace();
		tick = delay*amountOfEffect;
		tick--;
		effect.add(new Effect(p.getX(),p.getY()-(64-p.getHeight())));
	}

	@Override
	public void tick() {
		if(tick%delay==0&&tick>0)
			effect.add(new Effect(p.getX(),p.getY()-(64-p.getHeight())));
		for(int i=0;i<effect.size();i++){
			effect.get(i).tick();
			if(effect.get(i).isDone()){
				effect.remove(i);
				i--;
			}
		}
		tick--;
	}

	@Override
	public void render(Graphics g) {
		for(int i=0;i<effect.size();i++){
			effect.get(i).render(g);
		}
	}

	@Override
	public boolean collide(Entity e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean collide(Block b) {
		if(effect.size()==0){
			return true;
		}
		return false;
	}
	public class Effect{
		int stayTime = 17;
		int totalStayTime = stayTime;
		int xEffect,yEffect;
		public Effect(int x, int y){
			xEffect = x;
			yEffect = y;
		}
		public void tick(){
			stayTime--;
		}
		public void render(Graphics g){
			Graphics2D g2 = (Graphics2D) g;
			Composite c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,(float)(((double)stayTime/3)/totalStayTime));
			g2.setComposite(c);
			g.drawImage(sprite.getPlayerFace(face), (int)xEffect, (int)yEffect, null);
			c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f);
			g2.setComposite(c);
		}
		public boolean isDone(){
			if(stayTime<=0)
				return true;
			return false;
		}
	}
	@Override
	public void renderHitbox(Graphics g) {
		// TODO Auto-generated method stub
		
	}

}
