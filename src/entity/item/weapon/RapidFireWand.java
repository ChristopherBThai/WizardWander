package entity.item.weapon;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import entity.hostile.HostileEntity;
import entity.player.Player;
import entity.projectile.Projectile;
import entity.util.Entity;

public class RapidFireWand extends WeaponItem{

	double xvel,yvel;
	int radius;
	double projectile_cooldown,projectile_cooldown_reset;
	double attackPerSecond;
	double projDmg;
	Random rand;
	boolean player;

	public RapidFireWand(int x, int y) {
		super(x, y);
		xvel = 32;
		yvel = 32;
		projectile_cooldown = 0;
		radius = (int)(new Projectile()).getRadius();
		rand = new Random();
		this.setKnockback(.1);
		this.setName("Ice Shard Wand");
		this.setSubText("Careful, it's sharp");
		this.setSprite(sprite.getRapidWand());
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(Graphics win) {
		win.drawImage(Sprite,(int)this.getEntity().getX(),(int)this.getEntity().getY()-(int)hoverCurrentDistance,null);
		//win.setColor(new Color(255,230,129));
		//win.fillRect((int)this.getEntity().getX(), (int)this.getEntity().getY()-(int)hoverCurrentDistance, (int)this.getEntity().getWidth(), (int)this.getEntity().getHeight());		
	}
	public void projectileTick(Player e){
		if(projectile_cooldown>0)
			projectile_cooldown-=attackPerSecond;
		if(projectile_cooldown <= 0)
			shoot(e);
		projectile_cooldown_reset = e.getAttackCooldown();
		projDmg = e.getDamage()/4;
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
			double xveltemp = (pxvel / 2 + xvel);
			double yveltemp = (pyvel / 2);
			this.addP(new Projectile(xCord, yCord,xveltemp,yveltemp,projDmg,criticalChance,knockback));
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
			double xveltemp = (pxvel / 2 - xvel);
			double yveltemp = (pyvel / 2);
			this.addP(new Projectile(xCord, yCord,xveltemp,yveltemp,projDmg,criticalChance,knockback));
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
			double xveltemp = (pxvel / 2);
			double yveltemp = (pyvel / 2 - yvel);
			this.addP(new Projectile(xCord, yCord,xveltemp,yveltemp,projDmg,criticalChance,knockback));
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
			double xveltemp = (pxvel / 2);
			double yveltemp = (pyvel / 2 + yvel);
			this.addP(new Projectile(xCord, yCord, xveltemp,yveltemp,projDmg,criticalChance,knockback));
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
	private void addP(Projectile p){
		a.playRapidWand();
		if(player){
			EH.addP(p);
		}else{
			EH.addEP(p);
		}
	}


}
