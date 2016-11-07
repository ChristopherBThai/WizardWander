package display;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class LoadDisplay {
	
	double backgroundOpacity,textOpacity;
	double rate=.01;
	boolean done;
	boolean halfDone;
	final int xMid=507,yMid=392;
	int floor;
	public void reset(){
		done = false;
		halfDone = false;
		backgroundOpacity = 0;
		textOpacity = 0;
		rate=Math.abs(rate);
	}
	public void forceEnd(){
		done = true;
		halfDone = true;
	}
	public boolean isDone(){
		return done;
	}
	public boolean halfDone(){
		return halfDone;
	}
	public void setFloor(int x){
		floor = x;
	}
	public void tick(){
		if(backgroundOpacity>=1){
			backgroundOpacity=1;
			textOpacity+=rate;
			if(textOpacity>=1.5){
				//textOpacity=1;
				rate*=-1;
				halfDone = true;
			}
			if(textOpacity<=0){
				textOpacity=0;
				done = true;
				halfDone = false;
			}
		}else{
			backgroundOpacity+=.01;
		}
	}
	public void render(Graphics g){
		double textopac = textOpacity;
		if(textopac>=1){
			textopac = 1;
		}
		
		g.setColor(Color.BLACK);
		Graphics2D g2 = (Graphics2D) g;
		Composite c;
		if(rate<0)
			c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,(float)textopac);
		else
			c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,(float)backgroundOpacity);
		g2.setComposite(c);
		g.fillRect(0, 0, 1100, 900);
		
		g.setColor(Color.WHITE);
		c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,(float)textopac);
		g2.setComposite(c);
		String display = "Floor " + floor;
		g.setFont(new Font("Helvetica",Font.BOLD,150));
		int width = g.getFontMetrics().stringWidth(display);
		g.drawString(display, xMid-width/2, yMid);
		
		c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f);
		g2.setComposite(c);
	}
}
