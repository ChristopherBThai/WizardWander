package environment.block;

import java.awt.Graphics;

import main.util.Sprite;
import environment.util.RoomCoordinate;

public class Floor{
	Sprite sprite = new Sprite();
	RoomCoordinate RC = new RoomCoordinate();
	public void render(Graphics g){
		g.drawImage(sprite.getGameBackground(),RC.getX(1),RC.getY(1),null);
	}
}
