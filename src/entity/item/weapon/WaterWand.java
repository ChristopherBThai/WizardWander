package entity.item.weapon;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import entity.hostile.HostileEntity;
import entity.player.Player;
import entity.projectile.AbsProjectile;
import entity.projectile.BubbleProjectile;
import entity.projectile.Projectile;
import entity.util.Entity;

public class WaterWand extends WeaponItem{
	
	double xvel,yvel;
	int radius;
	double projectile_cooldown,projectile_cooldown_reset;
	double attackPerSecond;
	double projDmg;
	Random rand;
	boolean player;

	public WaterWand(int x, int y) {
		super(x, y);
		xvel = 4;
		yvel = 4;
		projectile_cooldown = 0;
		radius = (int)(new Projectile()).getRadius();
		rand = new Random();
		this.setKnockback(.3);
		this.setName("Bubble Wand");
		this.setSubText("It's so beautiful!");
		this.setSprite(sprite.getBubbleWand());
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(Graphics win) {
		win.drawImage(Sprite,(int)this.getEntity().getX(),(int)this.getEntity().getY()-(int)hoverCurrentDistance,null);
		//win.setColor(Color.BLUE);
		//win.fillRect((int)this.getEntity().getX(), (int)this.getEntity().getY()-(int)hoverCurrentDistance, (int)this.getEntity().getWidth(), (int)this.getEntity().getHeight());		
	}
	public void projectileTick(Player e){
		if(projectile_cooldown>0)
			projectile_cooldown-=attackPerSecond;
		if(projectile_cooldown <= 0)
			shoot(e);
		projectile_cooldown_reset = e.getAttackCooldown();
		projDmg = e.getDamage()/5;
		attackPerSecond = e.getAttackSpeed()*5;
		player = true;
	}
	
	public void projectileTick(HostileEntity e){
		if(projectile_cooldown>0)
			projectile_cooldown-=attackPerSecond;
		projectile_cooldown_reset = e.getAttackCooldown();
		projDmg = e.getDamage()/5;
		attackPerSecond = e.getAttackSpeed()*5;
		player = false;
	}
	
	public void shootRight(Entity e){
		if (projectile_cooldown <= 0) {
			this.setCriticalChance(e.getCriticalChance());
			projectile_cooldown += projectile_cooldown_reset;
			int xCord = (int) (e.getX() + e.getWidth());
			int yCord = (int) (e.getY() + (e.getHeight() / 2) - radius);
			double pxvel = e.getCurrentX();
			double pyvel = e.getCurrentY();
			if(Math.abs(pxvel)>=xvel)
				pxvel %= xvel;
			if(Math.abs(pyvel)>=yvel)
				pyvel %= yvel;
			double xveltemp = (pxvel/2 + xvel);
			double yveltemp = (pyvel/2)+(rand.nextInt(3001)-1500)/1000.0;
			this.addP(new BubbleProjectile(xCord, yCord,xveltemp,yveltemp,projDmg,criticalChance,knockback));
		}
	}
	public void shootLeft(Entity e) {
		if (projectile_cooldown <= 0) {
			this.setCriticalChance(e.getCriticalChance());
			projectile_cooldown += projectile_cooldown_reset;
			int xCord = (int) (e.getX() - (radius * 2));
			int yCord = (int) (e.getY() + (e.getHeight() / 2) - radius);
			double pxvel = e.getCurrentX();
			double pyvel = e.getCurrentY();
			if(Math.abs(pxvel)>=xvel)
				pxvel %= xvel;
			if(Math.abs(pyvel)>=yvel)
				pyvel %= yvel;
			double xveltemp = pxvel/2 - xvel;
			double yveltemp = pyvel/2+(rand.nextInt(3001)-1500)/1000.0;
			this.addP(new BubbleProjectile(xCord, yCord,xveltemp,yveltemp,projDmg,criticalChance,knockback));
		}
	}
	public void shootUp(Entity e) {
		if (projectile_cooldown <= 0) {
			this.setCriticalChance(e.getCriticalChance());
			projectile_cooldown += projectile_cooldown_reset;
			int xCord = (int) (e.getX() + (e.getWidth() / 2) - radius);
			int yCord = (int) (e.getY() - radius * 2);
			double pxvel = e.getCurrentX();
			double pyvel = e.getCurrentY();
			if(Math.abs(pxvel)>=xvel)
				pxvel %= xvel;
			if(Math.abs(pyvel)>=yvel)
				pyvel %= yvel;
			double xveltemp = pxvel/2 +(rand.nextInt(3001)-1500)/1000.0;
			double yveltemp = pyvel/2 - yvel;
			this.addP(new BubbleProjectile(xCord, yCord,xveltemp,yveltemp,projDmg,criticalChance,knockback));
		}
	}
	public void shootDown(Entity e) {
		if (projectile_cooldown <= 0) {
			this.setCriticalChance(e.getCriticalChance());
			projectile_cooldown += projectile_cooldown_reset;
			int xCord = (int) (e.getX() + (e.getWidth() / 2) - radius);
			int yCord = (int) (e.getY() + e.getHeight());
			double pxvel = e.getCurrentX();
			double pyvel = e.getCurrentY();
			if(Math.abs(pxvel)>=xvel)
				pxvel %= xvel;
			if(Math.abs(pyvel)>=yvel)
				pyvel %= yvel;
			double xveltemp = pxvel / 2 +(rand.nextInt(3001)-1500)/1000.0;
			double yveltemp = pyvel / 2 + yvel;
			this.addP(new BubbleProjectile(xCord, yCord, xveltemp,yveltemp,projDmg,criticalChance,knockback));
		}
	}
	private void shoot(Player e){
		if(PKH.right())
			shootRight(e);
		if(PKH.left())
			shootLeft(e);
		if(PKH.up())
			shootUp(e);
		if(PKH.down())
			shootDown(e);
	}
	private void addP(AbsProjectile p){
		a.playBubble();
		if(player){
			EH.addP(p);
		}else{
			EH.addEP(p);
		}
	}


}
