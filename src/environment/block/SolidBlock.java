package environment.block;

import java.awt.Graphics;

public class SolidBlock extends Block{

	public SolidBlock(int x, int y, int width, int height) {
		super(x, y, width, height);
		setCollision(true);
	}
	
	public void render(Graphics win){
		win.drawImage(sprite.getFloor(),(int)block.getX(),(int)block.getY(),null);
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

}
