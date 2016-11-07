package environment.room;

import java.util.ArrayList;
import java.util.Random;

import main.util.Audio;
import entity.hostile.HostileEntity;
import entity.item.ItemEntity;
import entity.player.Player;
import entity.util.EntityShake;
import environment.block.Block;
import environment.block.Door;

public class Map {
	static Room[][] rooms = new Room[10][10];
	static boolean[][] roomLayout = new boolean[10][10];
	static int currentX,currentY;
	static Random rand = new Random();
	static private Audio a = new Audio();
	static private EntityShake ES = new EntityShake();
	boolean bossFinished;
	
	static private int floor;
	public Map(){
		
	}
	public void setCurrents(){
		currentX=(rooms.length-1)/2+1;
		currentY=(rooms[0].length-1)/2+1;
	}
	public void nextFloor(){
		floor++;
	}
	public int getFloor(){
		return floor;
	}
	public void resetFloor(){
		floor = 0;
	}
	
	public ArrayList<HostileEntity> getE(){
		return rooms[currentX][currentY].getE();
	}
	public Room getRoom(){
		return rooms[currentX][currentY];
	}
	
	public ArrayList<Block> getB(){
		return rooms[currentX][currentY].getB();
	}
	public ArrayList<ItemEntity> getS(){
		return rooms[currentX][currentY].getS();
	}
	public boolean transition(){
		return true;
	}
	
	public void setDoors(int AmountOfHostiles,Player p){
		if(AmountOfHostiles>0&&!rooms[currentX][currentY].getDoorStatus()){
			rooms[currentX][currentY].closeDoors();
			a.playDoor();
			ES.shakeB(3);
			if(rooms[currentX][currentY].isBoss()){
				a.playBossbgm();
				a.stopBmg1();
			}
		}else if(AmountOfHostiles<=0&&rooms[currentX][currentY].getDoorStatus()){
			rooms[currentX][currentY].openDoors();
			p.regenMana();
			p.regenHealth();
			if(rooms[currentX][currentY].isBoss()){
				rooms[currentX][currentY].finishBoss();
				bossFinished = true;
				p.resetHealth();
				p.resetMana();
				a.stopBossbgm();
				//a.playBmg1();
			}
			a.playDoor();
			ES.shakeB(3);
		}
	}
	public boolean bossFinished(){
		return bossFinished;
	}
	
	public ArrayList<Door> getDoors(){
		return rooms[currentX][currentY].getDoors();
	}
	
	public void create(int numberOfRooms){
		
		rooms = new Room[10][10];
		roomLayout = new boolean[10][10];
		bossFinished = false;
		
		setCurrents();
		roomLayout[currentX][currentY] = true;
		
		Random rand = new Random();
		
		for(int i = 1;i<numberOfRooms;i++){
			int pickRandom = rand.nextInt(i*4)+1;
			if(!findRandomRoom(pickRandom)){
				i--;
			}
		}
		
		initRooms();
		initKey(numberOfRooms);
		initTreasure(numberOfRooms);
		initBoss(numberOfRooms);
		rooms[currentX][currentY].setStartingRoom();
		setDoors();
	}
	
	private void setDoors(){
		for(int i=0;i<roomLayout.length;i++){
			for(int j=0;j<roomLayout[i].length;j++){
				if(roomLayout[i][j]){
					if(i-1<0||!roomLayout[i-1][j]){
						rooms[i][j].closeLeft();
					}
					if(i+1>=roomLayout.length||!roomLayout[i+1][j]){
						rooms[i][j].closeRight();
					}
					if(j-1<0||!roomLayout[i][j-1]){
						rooms[i][j].closeTop();
					}
					if(j+1>=roomLayout[0].length||!roomLayout[i][j+1]){
						rooms[i][j].closeBottom();
					}

					
					if(i-1>=0&&rooms[i-1][j].isBoss()){
						rooms[i][j].addBossDoor(3);
					}
					if(i+1<roomLayout.length&&rooms[i+1][j].isBoss()){
						rooms[i][j].addBossDoor(1);
					}
					if(j-1>=0&&rooms[i][j-1].isBoss()){
						rooms[i][j].addBossDoor(0);
					}
					if(j+1<roomLayout[0].length&&rooms[i][j+1].isBoss()){
						rooms[i][j].addBossDoor(2);
					}
					
					
					if(i-1>=0&&rooms[i-1][j].isTreasure()){
						rooms[i][j].addTreasureDoor(3);
					}
					if(i+1<roomLayout.length&&rooms[i+1][j].isTreasure()){
						rooms[i][j].addTreasureDoor(1);
					}
					if(j-1>=0&&rooms[i][j-1].isTreasure()){
						rooms[i][j].addTreasureDoor(0);
					}
					if(j+1<roomLayout[0].length&&rooms[i][j+1].isTreasure()){
						rooms[i][j].addTreasureDoor(2);
					}
					
					
					rooms[i][j].initDoors();
					rooms[i][j].addDoor();
				}
			}
		}
	}
	private void initTreasure(int numRooms){
		int x=0;
		int y=0;
		
		for(int i=0;i<roomLayout.length;i++){
			for(int j=0;j<roomLayout[0].length;j++){
				if(!roomLayout[i][j]&&this.countSurrounded(i, j)>0&&this.countSurrounded(i, j)<2){
					
					numRooms--;
					if(!(numRooms>0)){
						x=i;
						y=j;
						j=roomLayout[0].length;
						i=roomLayout.length;
					}
				}
				if(i==roomLayout.length-1&&j==roomLayout[0].length-1){
					i=0;//(int)(Math.random()*roomLayout.length);
					j=0;//(int)(Math.random()*roomLayout[0].length);
				}
			}
		}
		roomLayout[x][y] = true;
		rooms[x][y] = new Room(true,floor);
		rooms[x][y].setTreasureRoom();
			
	}
	private void initBoss(int numRooms){
		int x=0;
		int y=0;
		
		for(int i=0;i<roomLayout.length;i++){
			for(int j=0;j<roomLayout[0].length;j++){
				if(!roomLayout[i][j]&&this.countSurrounded(i, j)>0&&this.countSurrounded(i, j)<2){
					
					numRooms--;
					if(!(numRooms>0)){
						x=i;
						y=j;
						j=roomLayout[0].length;
						i=roomLayout.length;
					}
				}
				if(i==roomLayout.length-1&&j==roomLayout[0].length-1){
					i=0;//(int)(Math.random()*roomLayout.length);
					j=0;//(int)(Math.random()*roomLayout[0].length);
				}
			}
		}
		roomLayout[x][y] = true;
		rooms[x][y] = new Room(true,floor);
		rooms[x][y].setBossRoom();
	}
	private int countSurrounded(int x, int y){
		int count = 0;
		if(x-1>=0){
			if(roomLayout[x-1][y]){
				count++;
			}
		}
		if(x+1<roomLayout.length){
			if(roomLayout[x+1][y]){
				count++;
			}
		}
		if(y-1>=0){
			if(roomLayout[x][y-1]){
				count++;
			}
		}
		if(y+1<roomLayout.length){
			if(roomLayout[x][y+1]){
				count++;
			}
		}
		return count;
	}
	private void initKey(int numberOfRooms){
		//rooms[4][5].addKey();
		int num = (int)(rand.nextInt(numberOfRooms)-1);
		//System.out.println(num);
		for(int i=0;i<roomLayout.length;i++){
			for(int j=0;j<roomLayout[0].length;j++){
				if(roomLayout[i][j]&&!(i==this.currentX&&j==this.currentY)){
					num--;
					if(num<0){
						
						rooms[i][j].addKey();
						//System.out.println(i+":"+j);
						j=roomLayout[0].length;
						i=roomLayout.length;
					}
				}
			}
				
		}
	}
	
	private void initRooms(){
		for(int i=0;i<roomLayout.length;i++){
			for(int j=0;j<roomLayout[i].length;j++){
				if(roomLayout[i][j]){
					rooms[i][j] = new Room(true,floor);
				}else{
					rooms[i][j] = new Room(false,floor);
				}
			}
		}
	}
	
	private boolean findRandomRoom(int rand){
		int x=0;
		int y=0;
		
			for(int i=0;i<roomLayout.length;i++){
				for(int j=0;j<roomLayout[0].length;j++){
					if(!roomLayout[i][j]&&this.countSurrounded(i, j)>0&&this.countSurrounded(i, j)<2){
						
						rand--;
						if(!(rand>0)){
							x=i;
							y=j;
							j=roomLayout[0].length;
							i=roomLayout.length;
						}
					}
					if(i==roomLayout.length-1&&j==roomLayout[0].length-1){
						i=0;//(int)(Math.random()*roomLayout.length);
						j=0;//(int)(Math.random()*roomLayout[0].length);
					}
				}
			}
			roomLayout[x][y]=true;
			return true;
		//return setRandomRoom(x,y);
	}
	
	public void moveRight(){
		if(currentX!=rooms.length-1)
			currentX++;
	}
	public void moveLeft(){
		if(currentX!=0)
			currentX--;
	}
	public void moveUp(){
		if(currentY!=0)
			currentY--;
	}
	public void moveDown(){
		if(currentY!=rooms[0].length-1)
			currentY++;
	}
	public String currentCoordinates(){
		return "["+currentX+","+currentY+"]";
	}
	public boolean[][] getRoomLayout(){
		return roomLayout;
	}
	public int getCurrentX(){
		return currentX;
	}
	public int getCurrentY(){
		return currentY;
	}
	
}
