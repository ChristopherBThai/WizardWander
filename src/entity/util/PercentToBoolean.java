package entity.util;

import java.util.Random;

public class PercentToBoolean {
	static private Random rand;
	public PercentToBoolean(){
		rand = new Random();
	}
	
	public boolean roll(double percent){
		
		int temp = rand.nextInt(100)+1;
		if(temp<=percent){
			return true;
		}else
			return false;
	}
}
