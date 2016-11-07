package entity.item.weapon;

import java.awt.Color;
import java.awt.Graphics;

import entity.hostile.HostileEntity;
import entity.player.Player;
import entity.projectile.AbsProjectile;
import entity.projectile.LaserProjectile;
import entity.projectile.Projectile;
import entity.util.Entity;

public class LaserWand extends WeaponItem{
	double xvel,yvel;
	int width,length;
	double projectile_cooldown,projectile_cooldown_reset;
	double AttackPerSecond;
	double projDmg;
	
	double wandDamage;
	double entityPercentDamage;
	
	double wandAttackSpeed;
	double entityPercentAttackSpeed;
	
	boolean player;

	public LaserWand(int x, int y) {
		super(x, y);
		xvel = 7;
		yvel = 7;
		projectile_cooldown = 0;
		this.setKnockback(0);
		width = 9;
		length = 100;
		AttackPerSecond = 0;
		this.setName("Laser Wand");
		this.setSubText("Pew Pew");
		this.setSprite(sprite.getLaserWand());
		// TODO Auto-generated constructor stub
	}
	public void setSize(int width, int length){
		this.width = width;
		this.length = length;
	}

	@Override
	public void render(Graphics win) {
		win.drawImage(Sprite,(int)this.getEntity().getX(),(int)this.getEntity().getY()-(int)hoverCurrentDistance,null);
		//win.setColor(Color.RED);
		//win.fillRect((int)this.getEntity().getX(), (int)this.getEntity().getY()-(int)hoverCurrentDistance, (int)this.getEntity().getWidth(), (int)this.getEntity().getHeight());		
	}
	public void projectileTick(Player p){
		if(projectile_cooldown>0)
			projectile_cooldown-=AttackPerSecond;
		if(projectile_cooldown <= 0)
			shoot(p);
		projectile_cooldown_reset = p.getAttackCooldown();
		projDmg = p.getDamage();
		AttackPerSecond = p.getAttackSpeed();
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
			int yCord = (int) (e.getY() + (e.getHeight() / 2) - width/2);
			this.addP(new LaserProjectile(xCord, yCord,width,length,projDmg,1));
		}
	}
	public void shootLeft(Entity e) {
		if (projectile_cooldown <= 0) {
			this.setCriticalChance(e.getCriticalChance());
			projectile_cooldown += projectile_cooldown_reset;
			int xCord = (int) (e.getX());
			int yCord = (int) (e.getY() + (e.getHeight() / 2) - width/2);
			this.addP(new LaserProjectile(xCord, yCord,width,length,projDmg,3));
		}
	}
	public void shootUp(Entity e) {
		if (projectile_cooldown <= 0) {
			this.setCriticalChance(e.getCriticalChance());
			projectile_cooldown += projectile_cooldown_reset;
			int xCord = (int) (e.getX() + (e.getWidth() / 2) - width/2);
			int yCord = (int) (e.getY());
			this.addP(new LaserProjectile(xCord, yCord,width,length,projDmg,0));
		}
	}
	public void shootDown(Entity e) {
		if (projectile_cooldown <= 0) {
			this.setCriticalChance(e.getCriticalChance());
			projectile_cooldown += projectile_cooldown_reset;
			int xCord = (int) (e.getX() + (e.getWidth() / 2) - width/2);
			int yCord = (int) (e.getY() + e.getHeight());
			this.addP(new LaserProjectile(xCord, yCord,width,length,projDmg,2));
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
		if(player){
			EH.addP(p);
		}else{
			EH.addEP(p);
		}
	}
}
