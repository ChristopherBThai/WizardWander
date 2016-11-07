package entity.item.weapon;

import java.awt.Color;
import java.awt.Graphics;

import entity.hostile.HostileEntity;
import entity.player.Player;
import entity.projectile.Projectile;
import entity.util.Entity;

public class BasicWand extends WeaponItem{
	
	double xvel,yvel;
	int radius;
	double projectile_cooldown,projectile_cooldown_reset;
	double AttackPerSecond;
	double projDmg;
	
	double wandDamage;
	double entityPercentDamage;
	
	double wandAttackSpeed;
	double entityPercentAttackSpeed;
	
	boolean player;

	public BasicWand(int x, int y) {
		super(x, y);
		xvel = 7;
		yvel = 7;
		projectile_cooldown = 0;
		this.setKnockback(.8);
		radius = (int)(new Projectile()).getRadius();
		AttackPerSecond = 0;
		this.setName("Wand");
		this.setSubText("It's just a normal Wand.");
		this.setSprite(sprite.getBasicWand());
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(Graphics win) {
		win.drawImage(Sprite,(int)this.getEntity().getX(),(int)this.getEntity().getY()-(int)hoverCurrentDistance,null);
		//win.setColor(Color.MAGENTA);
		//win.fillRect((int)this.getEntity().getX(), (int)this.getEntity().getY()-(int)hoverCurrentDistance, (int)this.getEntity().getWidth(), (int)this.getEntity().getHeight());		
	}
	public void projectileTick(Player p){
		if(projectile_cooldown>0)
			projectile_cooldown-=AttackPerSecond;
		if(projectile_cooldown <= 0)
			shoot(p);
		projectile_cooldown_reset = p.getAttackCooldown();
		projDmg = p.getDamage();
		AttackPerSecond = p.getAttackSpeed()*1.2;
		player = true;
	}
	public void projectileTick(HostileEntity e){
		if(projectile_cooldown>0)
			projectile_cooldown-=AttackPerSecond;
		projectile_cooldown_reset = e.getAttackCooldown();
		projDmg = e.getDamage();
		AttackPerSecond = e.getAttackSpeed();
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
			double yveltemp = (pyvel / 2-yvel);
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
			double yveltemp = (pyvel / 2+yvel);
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
		a.playBasicWand();
		if(player){
			EH.addP(p);
		}else{
			EH.addEP(p);
		}
	}


}
