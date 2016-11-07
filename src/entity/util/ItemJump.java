package entity.util;

import java.util.ArrayList;

public class ItemJump {
	
	private static ArrayList<IsJumping>	e = new ArrayList<IsJumping>();
	
	public void jump(Entity e){
		this.e.add(new IsJumping(e));
	}
	public void tick(){
		for(int i=0;i<e.size();i++){
			this.e.get(i).tick();
			if(this.e.get(i).isDone()){
				e.remove(i);
				i--;
			}
		}
	}
	
	private class IsJumping{
		Entity e;
		double hoverDistance;
		double maxDistance;
		public IsJumping(Entity e){
			this.e = e;
			hoverDistance = 1;
			maxDistance = 60;
		}
		public void tick(){
			
		}
		public boolean isDone(){
			if(hoverDistance<=0)
				return true;
			else
				return false;
		}
	}
}
