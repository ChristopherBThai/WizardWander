package display.player;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

public class HealthDisplay {
	Rectangle hp,hpDecay;
	String health;
	public HealthDisplay(){
		hp = new Rectangle(180,90,600,10);
		hpDecay = new Rectangle(180,90,6,10);
		health = "0/0";
	}
	public void tick(double maxHealth,double currentHealth){
		if(hp.getWidth()*((double)currentHealth/maxHealth)<hpDecay.getWidth())
			hpDecay.setSize((int)hpDecay.getWidth()-1, (int)hpDecay.getHeight());
		else
			hpDecay.setSize((int)(hp.getWidth()*((double)currentHealth/maxHealth)),(int)hp.getHeight());
	}
	public void render(Graphics win,double maxHealth, double currentHealth){
		health = ""+(int)currentHealth+"/"+(int)maxHealth;
		win.setColor(Color.DARK_GRAY);
		win.fillRect((int)hp.getX()-2,(int)hp.getY()-2,(int)hp.getWidth()+4,(int)hp.getHeight()+4);
		win.setColor(Color.BLACK);
		win.fillRect((int)hp.getX(),(int)hp.getY(),(int)hp.getWidth(),(int)hp.getHeight());
		win.setColor(new Color(153,0,0));
		win.fillRect((int)hp.getX(),(int)hpDecay.getY(),(int)(hpDecay.getWidth()),(int)hpDecay.getHeight());
		win.setColor(Color.RED);
		win.fillRect((int)hp.getX(),(int)hp.getY(),(int)(hp.getWidth()*((double)currentHealth/maxHealth)),(int)hp.getHeight());
		win.setColor(Color.WHITE);
		win.setFont(new Font("TEST", Font.PLAIN,10));
		win.drawString(health,(int)(hp.getX()+hp.getWidth()/2)-win.getFontMetrics().stringWidth(health)/2,(int)(hp.getY()+hp.getHeight()-2));
	}
}
