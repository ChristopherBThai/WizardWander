package display.player;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

public class XpDisplay {
	Rectangle xp;
	String experiencePoints;
	public XpDisplay(){
		xp = new Rectangle(180,103,600,4);
		experiencePoints = "0/0";
	}
	
	
	public void render(Graphics win,double maxMana, double currentMana){
		experiencePoints = ""+(int)currentMana+"/"+(int)maxMana;
		win.setColor(Color.DARK_GRAY);
		win.fillRect((int)xp.getX()-2,(int)xp.getY()-2,(int)xp.getWidth()+4,(int)xp.getHeight()+4);
		win.setColor(Color.BLACK);
		win.fillRect((int)xp.getX(),(int)xp.getY(),(int)xp.getWidth(),(int)xp.getHeight());
		win.setColor(Color.YELLOW);
		win.fillRect((int)xp.getX(),(int)xp.getY(),(int)(xp.getWidth()*((double)currentMana/maxMana)),(int)xp.getHeight());
		win.setColor(Color.BLACK);
		win.setFont(new Font("TEST", Font.PLAIN,9));
		win.drawString(experiencePoints,(int)(xp.getX()+xp.getWidth()/2)-win.getFontMetrics().stringWidth(experiencePoints)/2,(int)(xp.getY()+xp.getHeight())+1);
		win.setColor(Color.WHITE);
		win.drawString(experiencePoints,(int)(xp.getX()+xp.getWidth()/2)-win.getFontMetrics().stringWidth(experiencePoints)/2,(int)(xp.getY()+xp.getHeight())+2);
	}
}
