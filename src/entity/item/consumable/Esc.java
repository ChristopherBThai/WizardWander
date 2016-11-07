package entity.item.consumable;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import entity.item.ItemEntity;
import entity.particle.PotionParticle;
import entity.player.Player;

public class Esc extends ItemEntity{
	public Esc(double x, double y) {
		super(32, 32);
		this.setLocation((int)x, (int)y);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("Helvetica",Font.PLAIN,15));
		g.drawString("Press ESC for more help",(int)this.getX(),this.getY());
	}

	@Override
	public boolean pickup(Player p) {
		return false;
	}
}
