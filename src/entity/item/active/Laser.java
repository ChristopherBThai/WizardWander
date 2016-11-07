package entity.item.active;

import java.awt.Color;
import java.awt.Graphics;

import entity.player.Player;
import entity.projectile.LaserProjectile;

public class Laser extends ActiveItem{
	
	int width,height;
	
	public Laser(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		this.setItemID(51);
		this.setManaCost(50);
		width = 40;
		height = 100;
		Sprite = sprite.getLaserSpellbook();
	}

	@Override
	public void render(Graphics win) {
		win.setColor(Color.RED);
		win.drawImage(Sprite, (int)getEntity().getX(),(int)getEntity().getY()-(int)hoverCurrentDistance, null);
		this.setName("Laser Wand");
		this.setSubText("Bigger Pew.");
		//win.fillRect((int)this.getEntity().getX(), (int)this.getEntity().getY()-(int)hoverCurrentDistance, (int)this.getEntity().getWidth(), (int)this.getEntity().getHeight());

	}

	@Override
	public boolean activate(Player p) {
		this.checkDamage(p);
		if(p.getCurrentMana()>=manaCost){
			a.playLaser();
			p.giveMana(-manaCost);
			if(p.getFace()==0){
				EH.addP(new LaserProjectile(p.getX()+p.getWidth()/2-width/2,p.getY(),width,height,this.getDamage(),p.getFace(),this));
			}
			if(p.getFace()==1){
				EH.addP(new LaserProjectile(p.getX()+p.getWidth(),p.getY()+p.getHeight()/2-width/2,width,height,this.getDamage(),p.getFace(),this));
			}
			if(p.getFace()==2){
				EH.addP(new LaserProjectile(p.getX()+p.getWidth()/2-width/2,p.getY()+p.getHeight(),width,height,this.getDamage(),p.getFace(),this));
			}
			if(p.getFace()==3){
				EH.addP(new LaserProjectile(p.getX(),p.getY()+p.getHeight()/2-width/2,width,height,this.getDamage(),p.getFace(),this));
			}
			return true;
		}else{
			p.oom();
			return false;
		}
	}

	@Override
	public void playerTick() {
		this.xpCheck();
	}

	@Override
	public void playerRender(Graphics win) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getDamage() {
		return this.damage;
	}
}
