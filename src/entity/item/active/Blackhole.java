package entity.item.active;

import java.awt.Color;
import java.awt.Graphics;

import entity.player.Player;
import entity.projectile.BlackholeProjectile;
import entity.projectile.LaserProjectile;

public class Blackhole extends ActiveItem{

	public Blackhole(int x, int y) {
		super(x, y);
		this.setItemID(51);
		this.setManaCost(50);
		Sprite = sprite.getBlackholeSpellbook();
		this.setName("Blackhole Spell");
		this.setSubText("Eternal Darkness");
	}

	@Override
	public void render(Graphics win) {
		win.setColor(Color.BLACK);
		win.drawImage(Sprite, (int)getEntity().getX(),(int)getEntity().getY()-(int)hoverCurrentDistance, null);
		//win.fillRect((int)this.getEntity().getX(), (int)this.getEntity().getY()-(int)hoverCurrentDistance, (int)this.getEntity().getWidth(), (int)this.getEntity().getHeight());
	}

	@Override
	public double getDamage() {
		return this.damage;
	}

	@Override
	public boolean activate(Player p) {
		this.checkDamage(p);
		if(p.getCurrentMana()>=manaCost){
			double projSpeed = 5;
			p.giveMana(-manaCost);
			int width = (new LaserProjectile()).getWidth();
			if(p.getFace()==0){
				EH.addP(new BlackholeProjectile(p.getX()+p.getWidth()/2-width/2,p.getY(),p.getCurrentX()/2,p.getCurrentY()-projSpeed,this.getDamage(),this));
			}
			if(p.getFace()==1){
				EH.addP(new BlackholeProjectile(p.getX()+p.getWidth(),p.getY()+p.getHeight()/2-width/2,p.getCurrentX()/2+projSpeed,p.getCurrentY(),this.getDamage(),this));
			}
			if(p.getFace()==2){
				EH.addP(new BlackholeProjectile(p.getX()+p.getWidth()/2-width/2,p.getY()+p.getHeight(),p.getCurrentX()/2,p.getCurrentY()+projSpeed,this.getDamage(),this));
			}
			if(p.getFace()==3){
				EH.addP(new BlackholeProjectile(p.getX(),p.getY()+p.getHeight()/2-width/2,p.getCurrentX()/2-projSpeed,p.getCurrentY(),this.getDamage(),this));
			}
			this.addXp(3);
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
		
	}

}
