package entity.item.active;

import java.awt.Color;
import java.awt.Graphics;

import entity.player.Player;
import entity.projectile.DashProjectile;
import entity.util.DirectionalMovementHelper;

public class Dash extends ActiveItem{

	Color c = new Color(20,20,200);
	double knockbackDistance = 10;
	int effect;
	
	static private DirectionalMovementHelper DMH = new DirectionalMovementHelper();
	
	public Dash(int x, int y) {
		super(x, y);
		this.setItemID(51);
		this.setManaCost(10);
		Sprite = sprite.getDashSpellBook();
		this.setName("Dash Spell");
		this.setSubText("Vroom Vroom");
	}
	

	@Override
	public void render(Graphics win) {
		win.setColor(c);
		win.drawImage(Sprite, (int)getEntity().getX(),(int)getEntity().getY()-(int)hoverCurrentDistance, null);
		//win.fillRect((int)this.getEntity().getX(), (int)this.getEntity().getY()-(int)hoverCurrentDistance, (int)this.getEntity().getWidth(), (int)this.getEntity().getHeight());
		
	}

	@Override
	public double getDamage() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean activate(Player p) {
		if(p.getCurrentMana()>=manaCost){
			p.giveMana(-manaCost);
			EH.addP(new DashProjectile(p));
			double knockback = knockbackDistance + this.level;
			p.setCurrentX(0);
			p.setCurrentY(0);
			a.playDash();
			if(DMH.up()){
				p.knockback(0, -knockback);
			}else if(DMH.right()){
				p.knockback(knockback, 0);
			}else if(DMH.down()){
				p.knockback(0, knockback);
			}else if(DMH.left()){
				p.knockback(-knockback, 0);
			}else{
				if(PKH.up()){
					p.knockback(0, -knockback);
				}else if(PKH.right()){
					p.knockback(knockback, 0);
				}else if(PKH.down()){
					p.knockback(0, knockback);
				}else if(PKH.left()){
					p.knockback(-knockback, 0);
				}else{
					if(p.getFace()==0){
						p.knockback(0, -knockback);
					}else if(p.getFace()==1){
						p.knockback(knockback, 0);
					}else if(p.getFace()==2){
						p.knockback(0, knockback);
					}else if(p.getFace()==3){
						p.knockback(-knockback, 0);
					}
				}
			}
			this.addXp(1);
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

}
