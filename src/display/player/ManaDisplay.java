package display.player;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

public class ManaDisplay {
	Rectangle mp,manaDecay;
	String mana;
	public ManaDisplay(){
		mp = new Rectangle(180,110,600,7);
		manaDecay = new Rectangle(180,110,6,7);
		mana = "0/0";
	}
	public void tick(double maxHealth,double currentHealth){
		if(mp.getWidth()*((double)currentHealth/maxHealth)<manaDecay.getWidth())
			manaDecay.setSize((int)manaDecay.getWidth()-1, (int)manaDecay.getHeight());
		else
			manaDecay.setSize((int)(mp.getWidth()*((double)currentHealth/maxHealth)),(int)mp.getHeight());
	}
	public void render(Graphics win,double maxMana, double currentMana){
		mana = ""+(int)currentMana+"/"+(int)maxMana;
		win.setColor(Color.DARK_GRAY);
		win.fillRect((int)mp.getX()-2,(int)mp.getY()-2,(int)mp.getWidth()+4,(int)mp.getHeight()+4);
		win.setColor(Color.BLACK);
		win.fillRect((int)mp.getX(),(int)mp.getY(),(int)mp.getWidth(),(int)mp.getHeight());
		win.setColor(new Color(0,0,80));
		win.fillRect((int)mp.getX(),(int)manaDecay.getY(),(int)(manaDecay.getWidth()),(int)manaDecay.getHeight());
		win.setColor(Color.BLUE);
		win.fillRect((int)mp.getX(),(int)mp.getY(),(int)(mp.getWidth()*((double)currentMana/maxMana)),(int)mp.getHeight());
		win.setColor(Color.WHITE);
		win.setFont(new Font("TEST", Font.PLAIN,10));
		win.drawString(mana,(int)(mp.getX()+mp.getWidth()/2)-win.getFontMetrics().stringWidth(mana)/2,(int)(mp.getY()+mp.getHeight()));
	}
}
