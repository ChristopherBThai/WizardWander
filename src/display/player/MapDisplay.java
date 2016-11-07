package display.player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class MapDisplay {
	private final int x = 20;
	private final int y = 14;
	private final int width = 150;
	private final int height = 100;
	private final int borderWidth=2;
	private boolean[][] roomLayout;
	
	private int xStart, xEnd, yStart, yEnd;
	private int mapWidth,mapHeight;
	
	private double boxWidth,boxHeight;
	private double mapGapWidth,mapGapHeight;
	
	private int treasureX,treasureY;
	private int bossX,bossY;
	
	private double gap = 0.2;
	
	private ArrayList<RoomBox>	roomDisplay;
	private RoomBox player;
	
	public MapDisplay(){
		roomDisplay = new ArrayList<RoomBox>();
	}
	
	public void setRoomLayout(boolean[][] roomLayout){
		roomDisplay.clear();
		this.roomLayout = roomLayout;
		setStartEnd();
		setBoxSize();
		createMapDisplay();
		
	}
	
	public void currentPosition(int x,int y,boolean isTreasure,boolean isBoss){
		player = new RoomBox(x-xStart,y-yStart);
		player.setColor(Color.WHITE);
		RoomBox visited = new RoomBox(x-xStart,y-yStart);
		if(isTreasure){
			visited.setColor(Color.YELLOW);
		}else if(isBoss){
			visited.setColor(Color.RED);
		}else{
			visited.setColor(Color.GRAY);
		}
		roomDisplay.add(visited);
	}
	
	private void setStartEnd(){
		
		xStart = -1;
		yStart = -1;
		xEnd = -1;
		yEnd = -1;
		
		for(int i=0;i<roomLayout.length;i++){
			for(int j=0;j<roomLayout[i].length;j++){
				if(roomLayout[i][j]&&xStart==-1){
					xStart = i;
				}
				if(roomLayout[i][j]){
					xEnd = i;
				}
			}
		}
		
		for(int i=0;i<roomLayout[0].length;i++){
			for(int j=0;j<roomLayout.length;j++){
				if(roomLayout[j][i]&&yStart==-1){
					yStart = i;
				}
				if(roomLayout[j][i]){
					yEnd = i;
				}
			}
		}
		
		mapWidth = xEnd+1-xStart;
		mapHeight = yEnd+1-yStart;
		
		
	}
	
	private void setBoxSize(){
		boxWidth = (double)width/((gap+1)*mapWidth+gap);
		boxHeight = (double)height/((gap+1)*mapHeight+gap);
				
		mapGapWidth = boxWidth*gap;
		mapGapHeight = boxHeight*gap;
	}
	
	private void createMapDisplay(){
		for(int i=0;i<roomLayout.length;i++){
			for(int j=0;j<roomLayout[i].length;j++){
				if(roomLayout[i][j]){
					roomDisplay.add(new RoomBox(i-xStart,j-yStart));
				}
			}
		}
		
	}
	
	public void tick(){
		
	}
	
	public void render(Graphics g){
		g.setColor(Color.BLACK);
		g.fillRect(x+borderWidth, y+borderWidth, width-borderWidth, height-borderWidth);
		g.setColor(Color.GRAY);
		g.fillRect(x+width,y,borderWidth,height);
		g.fillRect(x,y+height,width,borderWidth);
		g.setColor(Color.WHITE);
		g.fillRect(x,y-borderWidth,width,borderWidth);
		g.fillRect(x-borderWidth,y,borderWidth,height);
		
		for(RoomBox temp:roomDisplay){
			temp.render(g);
		}
		player.renderOutline(g);
	}
	
	public class RoomBox{
		
		Color color;
		Rectangle box;
		
		public RoomBox(int xCord, int yCord){
			color = Color.DARK_GRAY;
			box = new Rectangle((int)(x+mapGapWidth+((mapGapWidth+boxWidth)*xCord)),
					(int)(y+mapGapHeight+((mapGapHeight+boxHeight)*yCord)),
					(int)boxWidth,(int)boxHeight);
		}
		public void setColor(Color c){
			color = c;
		}
		public void render(Graphics g){
			g.setColor(color);
			g.fillRect((int)box.getX(), (int)box.getY(), (int)box.getWidth(), (int)box.getHeight());
		}
		public void renderOutline(Graphics g){
			g.setColor(color);
			g.drawRect((int)box.getX(), (int)box.getY(), (int)box.getWidth(), (int)box.getHeight());
		}
	}
}
