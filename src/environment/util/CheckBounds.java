package environment.util;

import java.awt.Rectangle;

import main.util.EntityHolder;

public class CheckBounds {
	
	static Rectangle bounds;
	
	static RoomCoordinate RC = new RoomCoordinate();
	static EntityHolder EH = new EntityHolder();
	
	public CheckBounds(){
		bounds = new Rectangle(RC.getX(1),RC.getY(1),64*14,64*8);
	}
	public void tick(){
		for(int i = 0;i<EH.getE().size();i++){
			if(EH.getE().get(i).getX()<bounds.getX()){
				EH.getE().get(i).setLocation((int)bounds.getX(), EH.getE().get(i).getY());
			}else if(EH.getE().get(i).getX()+EH.getE().get(i).getWidth()>bounds.getX()+bounds.getWidth()){
				EH.getE().get(i).setLocation((int)(bounds.getX()+bounds.getWidth()-EH.getE().get(i).getWidth()), EH.getE().get(i).getY());
			}
			
			if(EH.getE().get(i).getY()<bounds.getY()){
				EH.getE().get(i).setLocation(EH.getE().get(i).getX(),(int)bounds.getY());
			}else if(EH.getE().get(i).getY()+EH.getE().get(i).getHeight()>bounds.getY()+bounds.getHeight()){
				EH.getE().get(i).setLocation((int)(EH.getE().get(i).getX()),(int)(bounds.getY()+bounds.getHeight()-EH.getE().get(i).getHeight()));
			}
			
		}
			
		for(int i = 0;i<EH.getS().size();i++){
			if(EH.getS().get(i).getX()<bounds.getX()){
				EH.getS().get(i).setLocation((int)bounds.getX(), EH.getS().get(i).getY());
			}else if(EH.getS().get(i).getX()+EH.getS().get(i).getWidth()>bounds.getX()+bounds.getWidth()){
				EH.getS().get(i).setLocation((int)(bounds.getX()+bounds.getWidth()-EH.getS().get(i).getWidth()), EH.getS().get(i).getY());
			}
			
			if(EH.getS().get(i).getY()<bounds.getY()){
				EH.getS().get(i).setLocation(EH.getS().get(i).getX(),(int)bounds.getY());
			}else if(EH.getS().get(i).getY()+EH.getS().get(i).getHeight()>bounds.getY()+bounds.getHeight()){
				EH.getS().get(i).setLocation((int)(EH.getS().get(i).getX()),(int)(bounds.getY()+bounds.getHeight()-EH.getS().get(i).getHeight()));
			}
			
		}
	}
}
