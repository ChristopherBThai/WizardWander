package environment.room;

import java.util.ArrayList;
import java.util.Random;

import main.util.Audio;
import entity.hostile.HostileEntity;
import entity.hostile.KittyKannon;
import entity.hostile.Skeleton;
import entity.hostile.Slime;
import entity.hostile.Skeletons.Skeleton0;
import entity.hostile.Skeletons.Skeleton1;
import entity.hostile.Skeletons.Skeleton2;
import entity.hostile.Skeletons.Skeleton3;
import entity.hostile.Skeletons.Skeleton4;
import entity.hostile.Skeletons.Skeleton5;
import entity.hostile.Skeletons.Skeleton6;
import entity.hostile.Skeletons.Skeleton7;
import entity.hostile.Skeletons.Skeleton8;
import entity.item.ItemEntity;
import entity.item.consumable.Chest;
import entity.item.consumable.Esc;
import entity.item.consumable.Key;
import entity.util.EntityShake;
import environment.block.AirBlock;
import environment.block.Block;
import environment.block.BossDoor;
import environment.block.Door;
import environment.block.SolidBlock;
import environment.block.TreasureDoor;
import environment.util.RoomCoordinate;


public class Room {
	RoomCoordinate RC = new RoomCoordinate();
	EntityShake ES = new EntityShake();
	ArrayList<HostileEntity> e;
	ArrayList<Door> d;
	ArrayList<Block> b;
	ArrayList<ItemEntity> s;
	Audio a = new Audio();
	Random rand;
	int level;
	boolean doors;
	boolean coord[][];
	boolean block[][];
	boolean door[];
	boolean locked,treasure,boss;
	int floor;
	public Room(boolean exists,int floor){
		coord = new boolean[16][10];
		block = new boolean[coord.length][coord[0].length];
		door = new boolean[4];
		this.floor = floor;
		b = new ArrayList<Block>();
		d = new ArrayList<Door>();
		e = new ArrayList<HostileEntity>();
		s = new ArrayList<ItemEntity>();
		rand = new Random();
		for(int i=0;i<coord.length;i++){
			for(int j=0;j<coord[i].length;j++){
				coord[i][j]=false;
				block[i][j]=false;
			}
		}
		if(exists){
			RoomOutline();
			RoomChooser();
			setFloor();
			
		}
		
	}
	
	public void setStartingRoom(){
		coord = new boolean[16][10];
		block = new boolean[coord.length][coord[0].length];
		door = new boolean[4];
		d = new ArrayList<Door>();
		b = new ArrayList<Block>();
		e.clear();
		s.clear();
		RoomOutline();
		setFloor();
		s.add(new Esc(760,780));
		/*
		addItem(1,1,new HealthPotion(0,0));
		addItem(1,2,new ManaPotion(0,0));
		addItem(1,3,new ExperiencePotion(0,0));
		addItem(1,4,new Key(0,0));
		addItem(3,1,new Fireball(0,0));
		addItem(3,2,new Laser(0,0));
		addItem(3,3,new Blackhole(0,0));
		addItem(3,4,new Dash(0,0));
		addItem(5,1,new BasicWand(0,0));
		addItem(5,2,new WaterWand(0,0));
		addItem(5,3,new RapidFireWand(0,0));
		addItem(5,4,new Fruitzooka(0,0));
		addItem(5,5,new LaserWand(0,0));
		addItem(7,1,new Chest(0,0));
		*/
	}
	public void setLevel(int lvl){
		level = lvl;
	}
	public void setTreasureRoom(){
		coord = new boolean[16][10];
		block = new boolean[coord.length][coord[0].length];
		door = new boolean[4];
		b = new ArrayList<Block>();
		e.clear();
		s.clear();
		RoomOutline();
		setFloor();
		locked = true;
		addItem(6,3,new Chest(0,0));
		addItem(9,3,new Chest(0,0));
		treasure = true;
		
	}
	public void finishBoss(){
		addItem(6,3,new Chest(0,0));
		addItem(9,3,new Key(0,0));
	}
	public void setBossRoom(){
		coord = new boolean[16][10];
		block = new boolean[coord.length][coord[0].length];
		door = new boolean[4];
		b = new ArrayList<Block>();
		e.clear();
		s.clear();
		RoomOutline();
		setFloor();
		boss = true;
		int random = rand.nextInt(2);
		if(random==0){
			HostileEntity ent =  new Slime(0,0,10+floor*30);
			ent.setMaxX(1.5);
			ent.setMaxY(1.5);
			ent.setDamage(5+floor*3);
			ent.setXpWorth(10*floor);
			this.addHostileEntity(8, 5,ent);
		}
		if(random==1){
			HostileEntity ent =  new KittyKannon(0,0,50+floor*40);
			ent.setDamage(4+floor*4);
			ent.setXpWorth(120*floor);
			this.addHostileEntity(8, 5,ent);
		}
	}
	public void addKey(){
		
		int x=(int)(Math.random()*coord.length-2)+1;
		int y=(int)(Math.random()*coord[0].length-2)+1;
		while(block[x][y]){
			x=(int)(Math.random()*coord.length-2)+1;
			y=(int)(Math.random()*coord[0].length-2)+1;
		}
		addItem(x,y,new Key(0,0));
		//System.out.println("key"+x+":"+y);
		if(rand.nextInt(2)==1)
			this.addHostileEntity(x, y, new Slime(0,0,20+floor*5,2));
	}
	
	public void closeTop(){
		block[coord.length/2][0]=true;
		addBlock(coord.length/2,0);
		block[coord.length/2-1][0]=true;
		addBlock(coord.length/2-1,0);
	}
	public void closeRight(){
		block[coord.length-1][coord[0].length/2]=true;
		addBlock(coord.length-1,coord[0].length/2);
		block[coord.length-1][coord[0].length/2-1]=true;
		addBlock(coord.length-1,coord[0].length/2-1);
	}
	public void closeBottom(){
		block[coord.length/2][coord[0].length-1]=true;
		addBlock(coord.length/2,coord[0].length-1);
		block[coord.length/2-1][coord[0].length-1]=true;
		addBlock(coord.length/2-1,coord[0].length-1);
	}
	public void closeLeft(){
		block[0][coord[0].length/2]=true;
		addBlock(0,coord[0].length/2);
		block[0][coord[0].length/2-1]=true;
		addBlock(0,coord[0].length/2-1);
	}
	public void initDoors(){
		if(!block[coord.length/2][0]){
			door[0] = true;
		}
		if(!block[coord.length-1][coord[0].length/2]){
			door[1] = true;
		}
		if(!block[coord.length/2][coord[0].length-1]){
			door[2] = true;
		}
		if(!block[0][coord[0].length/2]){
			door[3] = true;
		}
	}
	public void addDoor(){
		if(door[0]){
			addDoor(coord.length/2-1,0,0);
		}
		if(door[1]){
			addDoor(coord.length-1,coord[0].length/2-1,1);
		}
		if(door[2]){
			addDoor(coord.length/2-1,coord[0].length-1,2);
		}
		if(door[3]){
			addDoor(0,coord[0].length/2-1,3);
		}
		
	}
	public void addTreasureDoor(int pos){
		if(pos==0){
			addTreasureDoor(coord.length/2-1,0,0);
		}
		if(pos==1){
			addTreasureDoor(coord.length-1,coord[0].length/2-1,1);
		}
		if(pos==2){
			addTreasureDoor(coord.length/2-1,coord[0].length-1,2);
		}
		if(pos==3){
			addTreasureDoor(0,coord[0].length/2-1,3);
		}
	}
	public void addBossDoor(int pos){
		if(pos==0){
			addBossDoor(coord.length/2-1,0,0);
		}
		if(pos==1){
			addBossDoor(coord.length-1,coord[0].length/2-1,1);
		}
		if(pos==2){
			addBossDoor(coord.length/2-1,coord[0].length-1,2);
		}
		if(pos==3){
			addBossDoor(0,coord[0].length/2-1,3);
		}
	}
	public void closeDoors(){
		for(int i = 0; i<d.size();i++){
			d.get(i).close();
		}
		doors = true;
	}
	public void openDoors(){
		for(int i = 0; i<d.size();i++){
			d.get(i).open();
		}
		if(this.boss){
			a.playVictory();
		}
		doors = false;
	}
	public boolean getDoorStatus(){
		return doors;
	}
	
	public ArrayList<HostileEntity> getE(){
		return e;
	}
	
	public ArrayList<Block> getB(){
		return b;
	}
	public boolean[][] getRoomLayout(){
		return block;
	}
	public ArrayList<Door> getDoors(){
		return d;
	}
	public ArrayList<ItemEntity> getS(){
		return s;
	}
	
	private void RoomOutline(){
		for(int i=0;i<coord.length;i++){
			for(int j=0;j<coord[i].length;j++){
				if(i==0||i==coord.length-1||j==0||j==coord[i].length-1){
					if(i!=coord.length/2&&i!=coord.length/2-1&&j!=coord[i].length/2&&j!=coord[i].length/2-1){
						coord[i][j]=true;
						block[i][j]=true;
						int x = RC.getX(i);
						int y = RC.getY(j);
						b.add(new SolidBlock(x,y,64,64));
					}
				}
			}
		}
	}
	
	private void RoomChooser(){
		int random = rand.nextInt(1);
		if(random==0){RoomRandomizer();};
		if(random==1){MazeRoom();};
	}
	
	private void RoomRandomizer(){
		for(int i=1;i<coord.length-1;i++){
			for(int j=1;j<coord[i].length-1;j++){
				int temp = rand.nextInt(10);
				if(temp==0&&!coord[i][j]){
					int temp2 = rand.nextInt(3);
					if(!(((j==1||j==8)&&(i==7||i==8))||((i==1||i==14)&&(j==5||j==4)))){
						if(temp2==0&&i!=1&&i!=2&&i!=14&&i!=13&&j!=1&&j!=2&&j!=8&&j!=7){
							int temp3 = rand.nextInt(floor);
							for(int k = 0;k<=temp3;k++)
								addHostile(i,j);
						}else{
							addBlock(i,j);
						}
					}
				}
			}
		}
	}
	private void MazeRoom(){
		for(int i=1;i<coord.length-1;i++){
			for(int j=1;j<coord[i].length-1;j++){
				if(i%2==0&&i<7){
					if((i==2&&j==1)){}
					else if((i==4&&j==8)){}
					else if((i==6&&j==1)){}
					else addBlock(i,j);
				}
				if(i%2==1&&i>7){
					if(i==9&&j==8){}	
					else if(i==11&&j==1){}
					else if(i==13&&j==8){}
					else{addBlock(i,j);}
						
				}
			}
		}
	}
	
	private void setFloor(){
		for(int i=1;i<block.length-1;i++){
			for(int j=1;j<block[i].length-1;j++){
				if(!block[i][j]){
					addFloor(i,j);
				}
			}
		}
	}
	private void addFloor(int x, int y){
		x = RC.getX(x);
		y = RC.getY(y);
		b.add(new AirBlock(x,y,64,64));
		
	}
	private void addBlock(int x, int y){
		block[x][y]=true;
		coord[x][y]=true;
		x = RC.getX(x);
		y = RC.getY(y);
		b.add(new SolidBlock(x,y,64,64));
		
	}
	private void addDoor(int x, int y,int pos){
		if(this.treasure){
			this.addTreasureDoor(x,y,pos);
		}else if(this.boss){
			this.addBossDoor(x,y,pos);
		}else{
			block[x][y]=true;
			x = RC.getX(x);
			y = RC.getY(y);
			if(pos==0)
				d.add(new Door(x,y,128,64,pos));
			if(pos==1)
				d.add(new Door(x,y,64,128,pos));
			if(pos==2)
				d.add(new Door(x,y,128,64,pos));
			if(pos==3)
				d.add(new Door(x,y,64,128,pos));
		}
		
	}
	private void addBossDoor(int x, int y,int pos){
		block[x][y]=true;
		x = RC.getX(x);
		y = RC.getY(y);
		if(pos==0){
			block[coord.length/2][0]=true;
			block[coord.length/2-1][0]=true;
			d.add(new BossDoor(x,y,128,64,pos));
		}
		if(pos==1){
			block[coord.length-1][coord[0].length/2]=true;
			block[coord.length-1][coord[0].length/2-1]=true;
			d.add(new BossDoor(x,y,64,128,pos));
		}
		if(pos==2){
			block[coord.length/2][coord[0].length-1]=true;
			block[coord.length/2-1][coord[0].length-1]=true;
			d.add(new BossDoor(x,y,128,64,pos));
		}
		if(pos==3){
			block[0][coord[0].length/2]=true;
			block[0][coord[0].length/2-1]=true;
			d.add(new BossDoor(x,y,64,128,pos));
		}
	}
	private void addTreasureDoor(int x, int y,int pos){
		block[x][y]=true;
		x = RC.getX(x);
		y = RC.getY(y);
		if(pos==0){
			block[coord.length/2][0]=true;
			block[coord.length/2-1][0]=true;
			d.add(new TreasureDoor(x,y,128,64,pos));
		}
		if(pos==1){
			block[coord.length-1][coord[0].length/2]=true;
			block[coord.length-1][coord[0].length/2-1]=true;
			d.add(new TreasureDoor(x,y,64,128,pos));
		}
		if(pos==2){
			block[coord.length/2][coord[0].length-1]=true;
			block[coord.length/2-1][coord[0].length-1]=true;
			d.add(new TreasureDoor(x,y,128,64,pos));
		}
		if(pos==3){
			block[0][coord[0].length/2]=true;
			block[0][coord[0].length/2-1]=true;
			d.add(new TreasureDoor(x,y,64,128,pos));
		}
	}
	private void addHostile(int x, int y){
		coord[x][y]=true;
		int choose = rand.nextInt(9);
		if(choose == 0){
			Skeleton ent = new Skeleton0(0,0,10+floor*8);
			x = RC.getX(x,ent.getWidth());
			y = RC.getY(y,ent.getHeight());
			ent.setDamage(2+floor*2);
			ent.setLocation(x, y);
			ent.setXpWorth((int)(10+floor*5+Math.random()*floor*5));
			e.add(ent);
		}else if(choose == 1){
			Skeleton ent = new Skeleton1(0,0,10+floor*10);
			x = RC.getX(x,ent.getWidth());
			y = RC.getY(y,ent.getHeight());
			ent.setDamage(2+floor*2);
			ent.setAcceleration(3);
			ent.setLocation(x, y);
			ent.setXpWorth((int)(10+floor*5+Math.random()*floor*5));
			e.add(ent);
		}else if(choose ==2){
			Skeleton ent = new Skeleton2(0,0,10+floor*9);
			x = RC.getX(x,ent.getWidth());
			y = RC.getY(y,ent.getHeight());
			ent.setDamage(1+floor*2);
			ent.setLocation(x, y);
			ent.setAcceleration(4);
			ent.setXpWorth((int)(10+floor*5+Math.random()*floor*5));
			e.add(ent);
		}else if(choose ==3){
			Skeleton ent = new Skeleton3(0,0,10+floor*10);
			x = RC.getX(x,ent.getWidth());
			y = RC.getY(y,ent.getHeight());
			ent.setDamage(2+floor*3);
			ent.setLocation(x, y);
			ent.setAcceleration(3);
			ent.setXpWorth((int)(10+floor*5+Math.random()*floor*5));
			e.add(ent);
		}else if(choose ==4){
			Skeleton ent = new Skeleton4(0,0,10+floor*9);
			x = RC.getX(x,ent.getWidth());
			y = RC.getY(y,ent.getHeight());
			ent.setDamage(2+floor*2);
			ent.setLocation(x, y);
			ent.setXpWorth((int)(10+floor*5+Math.random()*floor*5));
			e.add(ent);
		}else if(choose ==5){
			Skeleton ent = new Skeleton5(0,0,10+floor*8);
			x = RC.getX(x,ent.getWidth());
			y = RC.getY(y,ent.getHeight());
			ent.setDamage(2+floor*3);
			ent.setLocation(x, y);
			ent.setXpWorth((int)(10+floor*5+Math.random()*floor*5));
			e.add(ent);
		}else if(choose ==6){
			Skeleton ent = new Skeleton6(0,0,10+floor*8);
			x = RC.getX(x,ent.getWidth());
			y = RC.getY(y,ent.getHeight());
			ent.setDamage(2+floor*4);
			ent.setLocation(x, y);
			ent.setXpWorth((int)(10+floor*5+Math.random()*floor*5));
			e.add(ent);
		}else if(choose ==7){
			Skeleton ent = new Skeleton7(0,0,10+floor*10);
			x = RC.getX(x,ent.getWidth());
			y = RC.getY(y,ent.getHeight());
			ent.setDamage(2+floor*4);
			ent.setAcceleration(3);
			ent.setLocation(x, y);
			ent.setXpWorth((int)(10+floor*5+Math.random()*floor*5));
			e.add(ent);
		}else if(choose ==8){
			Skeleton ent = new Skeleton8(0,0,10+floor*11);
			x = RC.getX(x,ent.getWidth());
			y = RC.getY(y,ent.getHeight());
			ent.setDamage(1+floor*2);
			ent.setLocation(x, y);
			ent.setAcceleration(2.5);
			ent.setXpWorth((int)(10+floor*5+Math.random()*floor*5));
			e.add(ent);
		}
	}
	private void addHostileEntity(int x, int y, HostileEntity he){
		x = RC.getX(x,he.getWidth());
		y = RC.getY(y,he.getHeight());
		he.setLocation(x, y);
		e.add(he);
	}
	private void addItem(int x, int y,ItemEntity item){
		coord[x][y]=true;
		x = RC.getX(x,item.getWidth());
		y = RC.getY(y,item.getHeight());
		item.setLocation(x, y);
		s.add(item);
	}
	public boolean isTreasure(){
		return treasure;
	}
	public boolean isLocked(){
		return locked;
	}
	public boolean isBoss(){
		return boss;
	}
}
