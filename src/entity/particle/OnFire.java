package entity.particle;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import main.util.EntityHolder;
import main.util.Sprite;
import entity.util.Entity;

public class OnFire {
	static ArrayList<OnFireEntity> fire;
	protected static ArrayList<FireParticle> particle;
	protected static Random rand;
	protected static EntityHolder EH;
	protected Sprite sprite = new Sprite();
	public OnFire(){
		if(fire == null){
			fire = new ArrayList<OnFireEntity>();
			particle = new ArrayList<FireParticle>();
			rand = new Random();
			EH = new EntityHolder();
		}
	}
	public void tick(){
		boolean doRender=true;;
		for(int i = 0;i<fire.size();i++){
			fire.get(i).tick();
			for(int j = i-1;j>=0;j--){
				if(fire.get(i).getEntity().equals(fire.get(j).getEntity())){
					doRender=false;
				}
			}
			if(doRender){
				fire.get(i).render();
			}
			if(fire.get(i).isDone()){
				fire.remove(fire.get(i));
				i--;
			}else if(fire.get(i).e.dead()){
				fire.remove(fire.get(i));
				i--;
			}
		}
	}
	public void render(Graphics g){
		
	}
	
	public void addFire(Entity entity, double damage, int times){
		fire.add(new OnFireEntity(entity,damage,times));
	}
	
	private class OnFireEntity{
		Entity e;
		double damage;
		int duration;
		final int ticksPerDamage = 50;
		Color c[];
		public OnFireEntity(Entity entity, double damage, int times){
			this.e = entity;
			this.damage = damage;
			this.duration = times*ticksPerDamage;
			c = new Color[6];
			c[0]=Color.BLACK;
			c[1]=Color.RED;
			c[2]=Color.ORANGE;
			c[3]=Color.ORANGE;
			c[4]=Color.YELLOW;
			c[5]=Color.YELLOW;
		}
		public Entity getEntity(){
			return e;
		}
		public boolean isDone(){
			if(duration<ticksPerDamage){
				return true;
			}else{
				return false;
			}
		}
		public void tick(){
			if(duration%ticksPerDamage==0){
				e.damage(this.damage);
			}

			duration--;
		}
		
		public void render(){
			EH.addPA(new FireParticle(new Rectangle((int)(e.getXCenter()+rand.nextInt(e.getWidth())-e.getWidth()/2)
					,(int)(e.getYCenter()+rand.nextInt(e.getHeight())-e.getHeight()/2),5,5),c[rand.nextInt(6)]));
		}
	}
	private class FireParticle implements Particle{
		Color c;
		//BufferedImage glow;
		Rectangle particle;
		int scaleDown;
		public FireParticle(Rectangle part, Color c){
			particle = part;
			//glow = sprite.getGlow();
			////glow = sprite.resize(glow, (int)part.getWidth(), (int)part.getHeight());
			//glow = sprite.recolor(glow, c);
			this.c = c;
			scaleDown = 20;
		}
		public void tick(){
			particle.translate(rand.nextInt(7)-3, -2);
			if(scaleDown<0){
				scaleDown = 20;
				particle.setSize((int)particle.getWidth()-2, (int)particle.getHeight()-1);
			}
			scaleDown--;
		}
		public boolean isDone(){
			if(particle.getWidth()<=0){
				//glow.flush();
				return true;
			}
			return false;
		}
		public void render(Graphics g){
			
			Graphics2D g2 = (Graphics2D) g;
			Composite c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,.7f);
			g2.setComposite(c);
			g.setColor(this.c);
			g.fillRect((int)particle.getX(), (int)particle.getY(), (int)particle.getWidth(), (int)particle.getHeight());
			c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f);
			g2.setComposite(c);
			
			//g.drawImage(glow, (int)particle.getX(), (int)particle.getY(), null);
			
		}
	}
}
