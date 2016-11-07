package entity.item.weapon;

import java.awt.Color;
import java.awt.Graphics;

import entity.hostile.HostileEntity;
import entity.player.Player;
import entity.projectile.AbsProjectile;
import entity.projectile.FruitProjectile;
import entity.projectile.Projectile;
import entity.util.Entity;

public class Fruitzooka extends WeaponItem{
	double xvel,yvel;
	int radius;
	double projectile_cooldown,projectile_cooldown_reset;
	double AttackPerSecond;
	double projDmg;
	
	double burstInterval;
	double burstCooldown;
	int burstAmount;
	
	boolean up,down,right,left;
	
	double wandDamage;
	double entityPercentDamage;
	
	double wandAttackSpeed;
	double entityPercentAttackSpeed;
	
	boolean player;

	public Fruitzooka(int x, int y) {
		super(x, y);
		xvel = 7;
		yvel = 7;
		projectile_cooldown = 60;
		this.setKnockback(.8);
		radius = (int)(new Projectile()).getRadius();
		AttackPerSecond = 0;
		burstAmount =5;
		burstInterval=4;
		this.setName("Fruit Wand");
		this.setSubText("Shoot Fruits!");
		this.setSprite(sprite.getFruitWand());
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(Graphics win) {
		win.drawImage(Sprite,(int)this.getEntity().getX(),(int)this.getEntity().getY()-(int)hoverCurrentDistance,null);
		//win.setColor(new Color(100,20,200));
		//win.fillRect((int)this.getEntity().getX(), (int)this.getEntity().getY()-(int)hoverCurrentDistance, (int)this.getEntity().getWidth(), (int)this.getEntity().getHeight());		
	}
	public void projectileTick(Player p){
		setStats(p);
		if(projectile_cooldown<projectile_cooldown_reset&&burstCooldown<=0)
			projectile_cooldown+=AttackPerSecond;
		if(projectile_cooldown >= projectile_cooldown_reset)
			shoot(p);
		if(burstCooldown>=0)
			burstCooldown--;
		burstShoot(p);
		player = true;
	}
	public void projectileTick(HostileEntity e){
		setStats(e);
		if(projectile_cooldown<projectile_cooldown_reset&&burstCooldown<=0)
			projectile_cooldown+=AttackPerSecond;
		if(burstCooldown>=0)
			burstCooldown--;
		burstShoot(e);
		player = false;
		
	}
	private void setStats(Entity e){
		projectile_cooldown_reset = e.getAttackCooldown();
		projDmg = e.getDamage()/2;
		AttackPerSecond = e.getAttackSpeed();
		burstInterval = (int)(11-e.getAttackCooldown()/10);
	}
	
	public void burstShoot(Entity e){
		if(burstCooldown>0){
			for(int i = 0;i<burstAmount;i++){
				if(burstCooldown==burstInterval*i){
					this.setCriticalChance(e.getCriticalChance());
					if(right){
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
						this.addP(new FruitProjectile(xCord, yCord,xveltemp,yveltemp,projDmg,criticalChance,knockback));
					}else if(left){
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
						this.addP(new FruitProjectile(xCord, yCord,xveltemp,yveltemp,projDmg,criticalChance,knockback));
					}else if(up){
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
						this.addP(new FruitProjectile(xCord, yCord,xveltemp,yveltemp,projDmg,criticalChance,knockback));
					}else if(down){
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
						this.addP(new FruitProjectile(xCord, yCord, xveltemp,yveltemp,projDmg,criticalChance,knockback));
					}
				}
			}
		}
	}
	
	public void shootRight(Entity e){
		if (projectile_cooldown >= projectile_cooldown_reset) {
			burstCooldown = burstInterval*burstAmount;
			projectile_cooldown -= projectile_cooldown_reset;
			right = true;
			left = false;
			up = false;
			down = false;
		}
	}
	public void shootLeft(Entity e) {
		if (projectile_cooldown >= projectile_cooldown_reset) {
			burstCooldown = burstInterval*burstAmount;
			projectile_cooldown -= projectile_cooldown_reset;
			right = false;
			left = true;
			up = false;
			down = false;
		}
	}
	public void shootUp(Entity e) {
		if (projectile_cooldown >= projectile_cooldown_reset) {
			burstCooldown = burstInterval*burstAmount;
			projectile_cooldown -= projectile_cooldown_reset;
			right = false;
			left = false;
			up = true;
			down = false;
		}
	}
	public void shootDown(Entity e) {
		if (projectile_cooldown >= projectile_cooldown_reset) {
			burstCooldown = burstInterval*burstAmount;
			projectile_cooldown -= projectile_cooldown_reset;
			right = false;
			left = false;
			up = false;
			down = true;
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
		a.playFruit();
		if(player){
			EH.addP(p);
		}else{
			EH.addEP(p);
		}
	}

}
