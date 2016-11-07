package environment.util;

public class RoomCoordinate {
	public int getX(int row){
		return (row*64);
	}
	public int getY(int column){
		return 128+(column*64);
	}
	public int getX(int row,int width){
		int temp = (64-width)/2;
		return getX(row)+temp;
	}
	public int getY(int column, int height){
		int temp = (64-height)/2;
		return getY(column)+temp;
	}
	public int getXCordMid(int x){
		return getX(x)+32;
	}
	public int getYCordMid(int y){
		return getY(y)+32;
	}
	public int getXCord(int x){
		return x/64;
	}
	public int getYCord(int y){
		return (y-128)/64;
	}
	
	public int getYMid(){
		return getX(7)+32;
	}
	public int getXMid(){
		return getY(4)+32;
	}
}
