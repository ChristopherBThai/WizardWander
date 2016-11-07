package environment.block;

import java.awt.Color;
import java.awt.Graphics;

public class BossDoor extends Door{

	public BossDoor(int x, int y, int width, int height, int pos) {
		super(x, y, width, height, pos);
		// TODO Auto-generated constructor stub
	}
	public void render(Graphics win) {
		win.setColor(Color.RED);
		if(!opened){
			win.fillRect((int)this.getBlock().getX(), (int)this.getBlock().getY(), (int)this.getBlock().getWidth(), (int)this.getBlock().getHeight());
		//win.drawImage(sprite.getDoorBlock(),(int)block.getX(),(int)block.getY(),null);
		}else{
			win.drawRect((int)this.getBlock().getX(), (int)this.getBlock().getY(), (int)this.getBlock().getWidth(), (int)this.getBlock().getHeight());
		}
		
	}
}
