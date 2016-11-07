package entity.hostile.Skeletons;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;

import entity.hostile.Skeleton;

public class Skeleton8 extends Skeleton{ //22x64 - 28x64
	public Skeleton8(int x, int y, int health) {
		super(x, y, health);
		this.setType(8);
		this.setSize(22, 41);
	}
	
	@Override
	public void render(Graphics win) {
		if(this.getFace()==0)
			win.drawImage(sprite.getSkeleton()[type][this.getFace()], (int)getEntity().getX(),(int)getEntity().getY()-(62-41), null);
		else if(this.getFace()==1)
			win.drawImage(sprite.getSkeleton()[type][this.getFace()], (int)getEntity().getX()-6,(int)getEntity().getY()-(62-41), null);
		healthRender(win);
		this.renderDamaged(win);
		
	}
	@Override
	public void renderDamaged(Graphics g) {
		if(damageFrame>0){
			damageFrame--;
			Graphics2D g2 = (Graphics2D) g;
			Composite c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,.6f);
			g2.setComposite(c);
			if(this.getFace()==0)
				g.drawImage(sprite.damaged(sprite.getSkeleton()[this.type][this.getFace()]), (int)getEntity().getX(),(int)getEntity().getY()-(62-41), null);
			else if(this.getFace()==1)
				g.drawImage(sprite.damaged(sprite.getSkeleton()[this.type][this.getFace()]), (int)getEntity().getX()-6,(int)getEntity().getY()-(62-41), null);
			c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f);
			g2.setComposite(c);
		}		
	}
}
