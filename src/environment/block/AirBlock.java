package environment.block;

import java.awt.Graphics;

public class AirBlock extends Block{

	public AirBlock(int x, int y, int width, int height) {
		super(x, y, width, height);
		setCollision(false);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(Graphics win) {
		//win.drawImage(sprite.getFloor(),(int)block.getX(),(int)block.getY(),null);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

}
