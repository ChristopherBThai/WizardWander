package entity.item.consumable;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import entity.item.ItemEntity;
import entity.particle.PotionParticle;
import entity.player.Player;

public class ManaPotion extends ItemEntity{
	int rotate = 0;
	int particletick;
	Color particle;
	public ManaPotion(double x, double y) {
		super(32,32);
		this.setLocation((int)x, (int)y);
		this.setItemID(2);
		hoverDistance = 10;
		hoverCurrentDistance = 0;
		hoverAcceleration = .3;
		particle = new Color(0,255,255);
	}

	@Override
	public void tick() {
		this.move(0,0);
		this.move();
		this.hover();
		particletick++;
		if(particletick%15==0)
			EH.addPA(new PotionParticle((int)(this.getX()+Math.random()*this.getWidth()),(int)(this.getY()+Math.random()*this.getHeight()),3,3,particle));
	}

	@Override
	public void render(Graphics g) {
		
		g.drawImage(sprite.getMana(), (int)getEntity().getX(),(int)getEntity().getY()-(int)hoverCurrentDistance, null);
		/*
		rotate++;
		AffineTransform identity = new AffineTransform();

		Graphics2D g2d = (Graphics2D)g;
		AffineTransform trans = new AffineTransform();
		
		trans.setTransform(identity);
		trans.translate(sprite.getMana().getHeight() / 2,sprite.getMana().getWidth() / 2);
		trans.rotate( Math.toRadians(rotate) );
		trans.translate(-sprite.getMana().getWidth() / 2,-sprite.getMana().getHeight() / 2);
		
		AffineTransformOp op = new AffineTransformOp(trans, AffineTransformOp.TYPE_BILINEAR);
		BufferedImage test =new BufferedImage(sprite.getMana().getHeight(), sprite.getMana().getWidth(), sprite.getMana().getType());
		op.filter(sprite.getMana(), test);

		g.drawImage(test, (int)getEntity().getX(),(int)getEntity().getY()-(int)hoverCurrentDistance, null);
	*/
	}

	@Override
	public boolean pickup(Player p) {
		if(p.getMaxMana()<=p.getCurrentMana()){
			return false;
		}else{
			p.giveMana(p.getMaxMana()*.1);
			return true;
		}
		
	}
}
