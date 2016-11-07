package entity.particle;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public interface Particle {
	public boolean isDone();
	public void tick();
	public void render(Graphics g);
	
}
