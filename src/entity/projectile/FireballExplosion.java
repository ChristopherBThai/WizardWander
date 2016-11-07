package entity.projectile;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Ellipse2D;

import main.util.Audio;
import entity.item.active.Fireball;
import entity.particle.OnFire;
import entity.util.Entity;
import environment.block.Block;

public class FireballExplosion extends AbsProjectile{
	
	protected Ellipse2D.Double boom;
	protected int timer = 150;
	protected Fireball spell;
	
	public FireballExplosion(Ellipse2D.Double input,Fireball spell,double dmg){
		super(input.getX(), input.getY(), 0, 0, dmg, 0, 0);
		this.spell = spell;
		this.boom = input;
		this.dmg = dmg;
		
		a.playFireballExplosion();
		boom.setFrame((int)boom.getX()-5, (int)boom.getY()-5, (int)boom.getWidth()+10, (int)boom.getHeight()+10);
	}
	public void tick(){
		timer--;
		if(boom.getWidth()>0)
			boom.setFrame((int)boom.getX()+3, (int)boom.getY()+3, (int)boom.getWidth()-6, (int)boom.getHeight()-6);
	}
	public boolean collide(Entity e){
		if(timer == 149&&boom.intersects(e.getEntity())){
			e.damage(dmg);
			OF.addFire(e, (dmg)/10, 5);
			spell.addXp(1);		
		}
		if(timer<=0){
			return true;
		}
		return false;
	}
	public boolean collide(Block b){
		return false;
	}
	public void render(Graphics win){
		win.setColor(Color.RED);
		win.fillOval((int)boom.getX(), (int)boom.getY(), (int)boom.getWidth(), (int)boom.getHeight());
	}
	@Override
	public void renderHitbox(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillOval((int)boom.getX(), (int)boom.getY(), (int)boom.getWidth(), (int)boom.getHeight());
		
	}
}
