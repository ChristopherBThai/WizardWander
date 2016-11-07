package entity.util;

import main.util.EntityHolder;

public class EntityShake {
	
	static EntityHolder EH = new EntityHolder();
	static int shakeB;
	static int dy;
	static int tick;
	public EntityShake(){
		
	}
	public void shakeB(int intensity){
		shakeB = intensity;
	}
	public void tick(){
		tick++;
		//if(tick%1==0){
			if((int)Math.abs(dy)>=(int)Math.abs(shakeB)&&shakeB!=0){
				shakeB*=-1;
				dy = 0;
				if(shakeB>0)
					shakeB-= (int)Math.abs(shakeB)/shakeB;
			}

			if(shakeB>0){
				dy++;
				moveB(3);
			}
			if(shakeB<0){
				dy--;
				moveB(-3);
			}
	//	}
	}
	public void moveB(int y){
		for(int i=0;i<EH.getB().size();i++){
			EH.getB().get(i).move(0, y);
		}
	}
}
