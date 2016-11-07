package entity.hostile.movement;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import entity.hostile.HostileEntity;
import entity.util.Entity;
import environment.room.Map;
import environment.util.RoomCoordinate;

public abstract class SmartFollow extends HostileEntity{
	private static boolean[][] map;
	private static RoomCoordinate RoomC;
	private int tick;
	private Node Next;
	private Node Goal;
	private ArrayList<Node> open;
	private ArrayList<Node> close;
	private Node Current;
	protected boolean debug;
	protected Rectangle nextgoal;
	
	protected int face;
	
	protected Entity FollowingEntity;
	
	public SmartFollow(double inputVerticleSpeed, double inputHorizontalSpeed,int entityWidth, int entityHeight, int maxhealth) {
		super(inputVerticleSpeed, inputHorizontalSpeed, entityWidth, entityHeight,maxhealth);
		open = new ArrayList<Node>();
		close = new ArrayList<Node>();
		RoomC = new RoomCoordinate();
		tick = 31;
		Next = getNode(this.getEntity());
		debug = false;
		Current = getNode(this.getEntity());
	}
	public void follow(Entity e){
		
		FollowingEntity = e;
		
		if(tick>2){


			if(!(new Rectangle(RoomC.getX(Current.getX()),RoomC.getY(Current.getY()),64,64)).contains(getEntity())){
				Current.setX(RoomC.getXCord((int)(getEntity().getWidth()/2+getEntity().getX())));
				Current.setY(RoomC.getYCord((int)(getEntity().getHeight()/2+getEntity().getY())));
			}
			
			
			Goal = getNode(e.getEntity());
			tick = 0;
			Current.setF(getF(Current));
			//System.out.println(findPath(Current));
			Next = findPath(Current);
			
		}
		
		if(!followPath(Next)){
			followEntity(e);
		}
		tick++;
		//System.out.println(this.getCurrentY());
		
	}
	
	private Node findPath(Node entityNode){
		open.clear();
		close.clear();
		
		map = (new Map()).getRoom().getRoomLayout();
		
		open.add(entityNode);
		
		while(open.size()>0){
			Node currentNode = getLowestF(open);
			
			if(debug){
					System.out.print("open:");
				for(Node temp:open){
					System.out.print(temp);
				}
				System.out.print("\nclose:");
				for(Node temp:close){
					System.out.print(temp);
				}
				System.out.println("\ncurrent:"+currentNode);
			}
			
			
			
			if(currentNode.equalsNode(Goal))
				return currentNode;
			
			open.remove(currentNode);
			close.add(currentNode);
			
			getNeighborNodes(currentNode);
			if(debug)
				System.out.println("\n\n");
		}
		return null;
	}
	private boolean followPath(Node next){
		if(next==null){
			//System.out.println("no path");
			return false;
		}else if(next.equalsNode(Current)){
			return false;
		}else{	
			while(!next.getParent().equalsNode(Current)){
				next = next.getParent();
			}
			nextgoal = (new Rectangle(RoomC.getX(next.getX()),RoomC.getY(next.getY()),64,64));
			double xtenacity=1,ytenacity=1;
			if(Math.abs(nextgoal.getX()-this.getEntity().getX())<20){
				//xtenacity = Math.abs((nextgoal.getX()-this.getEntity().getX()))/20;
			}
			if(Math.abs(nextgoal.getY()-this.getEntity().getY())<20){
				//ytenacity = (Math.abs(nextgoal.getY()-this.getEntity().getY()))/20;
			}
			if(!nextgoal.contains(this.getEntity())){
				if(this.getXCenter()-RoomC.getXCordMid(next.getX())>this.getCurrentX()){
					this.moveX(-this.getMaxX()*xtenacity);
					face = 1;
					if(Math.abs(this.getXCenter()-RoomC.getXCordMid(next.getX()))<this.getCurrentX()){
						this.setLocation(RoomC.getXCordMid(next.getX())-this.getWidth()/2, this.getY());
						this.setCurrentX(0);
					}
				}
				if(this.getXCenter()-RoomC.getXCordMid(next.getX())<-this.getCurrentX()){
					this.moveX(this.getMaxX()*xtenacity);
					face = 0;
					if(Math.abs(this.getXCenter()-RoomC.getXCordMid(next.getX()))<this.getCurrentX()){
						this.setLocation(RoomC.getXCordMid(next.getX())-this.getWidth()/2, this.getY());
						this.setCurrentX(0);
					}
				}
				if(this.getYCenter()-RoomC.getYCordMid(next.getY())>this.getCurrentY()){
					this.moveY(-this.getMaxY()*ytenacity);
					if(Math.abs(this.getYCenter()-RoomC.getYCordMid(next.getY()))<this.getCurrentY()){
						this.setLocation(this.getX(),RoomC.getYCordMid(next.getY())-this.getHeight()/2);
						this.setCurrentY(0);
					}
				}
				if(this.getYCenter()-RoomC.getYCordMid(next.getY())<-this.getCurrentY()){
					this.moveY(this.getMaxY()*ytenacity);
					if(Math.abs(this.getYCenter()-RoomC.getYCordMid(next.getY()))<this.getCurrentY()){
						this.setLocation(this.getX(),RoomC.getYCordMid(next.getY())-this.getHeight()/2);
						this.setCurrentY(0);
					}
				}
			}
			return true;
		}
	}
	
	private void followEntity(Entity e){
		if(getXCenter()>e.getXCenter()){
			super.moveX(this.getLeft());
			face = 1;
		}else if(getXCenter()<e.getXCenter()){
			super.moveX(this.getRight());
			face = 0;
		}else
			super.moveX(0);
			
			
		if(this.getYCenter()>e.getYCenter())
			super.moveY(this.getUp());
		else if(this.getYCenter()<e.getYCenter())
			super.moveY(this.getDown());
		else
			super.moveY(0);
	}
	private boolean containsNode(ArrayList<Node> list,Node input){
		boolean result = false;
		for(Node compare:list){
			if(compare.equalsNode(input)){
				result = true;
			}
		}
		return result;
	}
	private void getNeighborNodes(Node input){
		if(debug)
			System.out.print("added:");
		for(int i=-1;i<=1;i++){
			for(int j=-1;j<=1;j++){
				if(j==0&&i==0){
					
				}else{
					int x = input.getX()+i;
					int y = input.getY()+j;
					
					if(i!=0&&j!=0){
						if(x-i>=0&&y-j>=0&&x-i<map.length&&y-j<map[0].length&&
								x>=0&&y>=0&&x<map.length&&y<map[0].length){
							if(map[x-i][y]||map[x][y-j]){
								x = -1;
							}
						}
						
					}
					
					
					if(x>=0&&y>=0&&x<map.length&&y<map[0].length&&!map[x][y]){
						Node temp;
						if(i==0||j==0)
							temp = new Node(input,x,y,input.getTraveled()+1);
						else
							temp = new Node(input,x,y,input.getTraveled()+Math.sqrt(2));
						
						temp.setF(getF(temp));
						
						if(!containsNode(close,temp)&&!containsNode(open,temp)){
							open.add(temp);
							if(debug)
								System.out.print(""+temp);
						}
							
					}
				}
			}
		}
	}
	private Node getLowestF(ArrayList<Node> list){
		Node lowest = list.get(0);
		for(int i = 1;i<list.size();i++){
			lowest = lowest.getCloserNode(list.get(i));
		}
		return lowest;
	}
	
	
	private Node getNode(Rectangle r){
		return new Node(null,
				RoomC.getXCord((int)(r.getWidth()/2+r.getX())),
				RoomC.getYCord((int)(r.getWidth()/2+r.getY())),0);
	}
	private double getF(Node current){
		double distanceTraveled = current.getTraveled()+Math.sqrt(
				Math.pow(current.getX()-Goal.getX(), 2)+Math.pow(current.getY()-Goal.getY(), 2));
		return distanceTraveled;
	}
	
	public int getFace(){
		return face;
	}
	
	public abstract void tick();
	public abstract void render(Graphics win);
	
	private static class Node{
		private int x,y;
		private double traveled,F;
		private Node parent;
		public Node(Node parent,int x, int y,double traveled){
			this.parent = parent;
			this.traveled = traveled;
			this.F = 10000;
			this.x = x;
			this.y = y;
		}
		public boolean equalsNode(Node input){
			if(this.x == input.getX()&&this.y == input.getY())
				return true;
			else 
				return false;
		}
		public Node getCloserNode(Node input){
			if(this.getF()>input.getF())
				return input;
			else
				return this;
		}
		
		public Node getParent(){return parent;}
		public int getX(){return x;}
		public int getY(){return y;}
		public double getF(){return F;}
		public double getTraveled(){return traveled;}
		public void setX(int x){this.x = x;}
		public double setF(double input){
			F = input;
			return F;
		}
		public void setY(int y){this.y = y;}
		public String toString(){
			String result = "["+x+","+y+"]";// f:"+this.getF();
			return result;
		}
	}
}
