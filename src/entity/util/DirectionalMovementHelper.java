package entity.util;

import java.util.Stack;

public class DirectionalMovementHelper {
	static Stack<String> stack ;
	static boolean right,left,up,down;
	
	public DirectionalMovementHelper(){
		if(stack == null){
			stack = new Stack<String>();
			stack.add("");
		}
	}
	
	public void right(boolean status) {
		if(status!=right){
			if(status)
				stack.add("right");
			else
				stack.removeElement("right");
			right = status;
		}
	}
	public void left(boolean status){
		if(status!=left){
			if(status)
				stack.add("left");
			else
				stack.removeElement("left");
			left = status;
		}
	}
	public void up(boolean status){
		if(status!=up){
			if(status)
				stack.add("up");
			else
				stack.removeElement("up");
			up = status;
		}
	}	
	public void down(boolean status){
		if(status!=down){
			if(status)
				stack.add("down");
			else
				stack.removeElement("down");
			down = status;
		}
	}
	
	public boolean right(){
		return (stack.peek().equals("right"));
	}
	public boolean left(){
		return (stack.peek().equals("left"));
	}
	public boolean up(){
		return (stack.peek().equals("up"));
	}
	public boolean down(){
		return (stack.peek().equals("down"));
	}
	
	
	public void reset(){
		stack.clear();
	}
}
