package entity.util;

import java.awt.Rectangle;
import java.util.ArrayList;

import entity.hostile.HostileEntity;
import entity.item.ItemEntity;
import entity.player.Player;
import entity.projectile.AbsProjectile;
import environment.block.Block;

public class EntityCollision {
	PercentToBoolean PTB = new PercentToBoolean();
	public EntityCollision(){
	}
	
	public boolean collideStop(Entity e,Rectangle b){
		if(e.getEntity().intersects(b)){
			
			double xdiff = e.getX()+e.getWidth()/2.0-(b.getX()+b.getWidth()/2.0);
			double ydiff = e.getY()+e.getHeight()/2.0-(b.getY()+b.getHeight()/2.0);
			
			if(e.getWidth()-e.getHeight()>0){		// -
				if(ydiff==0)
					ydiff=0;
				else
					ydiff += (ydiff/Math.abs(ydiff))*(e.getWidth()-e.getHeight())/2.0;
			}else if(e.getWidth()-e.getHeight()<0){ // |
				if(xdiff==0)
					xdiff=0;
				else
					xdiff+= (xdiff/Math.abs(xdiff))*(e.getHeight()-e.getWidth())/2.0;
			}
			
			if(Math.abs(xdiff)>Math.abs(ydiff)){	 //Right Left
				if(xdiff<0)
					e.setLocation((int)(b.getX()-e.getWidth()),(int)e.getY());
				else
					e.setLocation((int)(b.getX()+b.getWidth()),(int)e.getY());
				e.setCurrentX(0);
				e.setKnockbackX(0);
			}else if(Math.abs(xdiff)<Math.abs(ydiff)){									//Up Down
				if(ydiff<0)
					e.setLocation((int)e.getX(),(int)(b.getY()-e.getHeight()));
				else
					e.setLocation((int)e.getX(),(int)(b.getY()+b.getHeight()));
				e.setCurrentY(0);
				e.setKnockbackY(0);
			}
			return true;
		}else
			return false;
	}
	public boolean collideBounce(Entity e1,Entity e2){
		if(e1.getEntity().intersects(e2.getEntity())){
			
			double xtenacity = (e1.getEntity().getWidth()/2+e2.getEntity().getWidth()/2)/(e1.getEntity().getX()+e1.getEntity().getWidth()/2-(e2.getEntity().getX()+e2.getEntity().getWidth()/2));
			double ytenacity =(e1.getEntity().getHeight()/2+e2.getEntity().getHeight()/2)/(e1.getEntity().getY()+e1.getEntity().getHeight()/2-(e2.getEntity().getY()+e2.getEntity().getHeight()/2));
			double xdiff = e1.getEntity().getX()+e1.getEntity().getWidth()/2-(e2.getEntity().getX()+e2.getEntity().getWidth()/2);
			double ydiff = e1.getEntity().getY()+e1.getEntity().getHeight()/2-(e2.getEntity().getY()+e2.getEntity().getHeight()/2);
			if(!(xtenacity>=0||xtenacity<0)){
				System.out.println(xtenacity);
			}
			if(Math.abs(xdiff)>Math.abs(ydiff)){
				if(Math.abs(xdiff)<5){
				}else{
					e1.collide((int)(xtenacity/2),0);
					e2.collide((int)-(xtenacity/2),0);
				}
				
			}else{
				if(Math.abs(ydiff)<5){
				}else{
					e1.collide(0,(int)(ytenacity/2));
					e2.collide(0,(int)-(ytenacity/2));
				}
				
			}
			
			return true;
		}else
			return false;
	}
	public boolean collidePlayerBounce(Entity e1,Player e2){
		if(e1.getEntity().intersects(e2.getEntity())){
			
			double xtenacity = (e1.getEntity().getWidth()/2+e2.getEntity().getWidth()/2)/
					(e1.getEntity().getX()+e1.getEntity().getWidth()/2-
							(e2.getEntity().getX()+e2.getEntity().getWidth()/2));
			double ytenacity =(e1.getEntity().getHeight()/2+e2.getEntity().getHeight()/2)/
					(e1.getEntity().getY()+e1.getEntity().getHeight()/2-
							(e2.getEntity().getY()+e2.getEntity().getHeight()/2));
			double xdiff = e1.getEntity().getX()+e1.getEntity().getWidth()/2-(e2.getEntity().getX()+e2.getEntity().getWidth()/2);
			double ydiff = e1.getEntity().getY()+e1.getEntity().getHeight()/2-(e2.getEntity().getY()+e2.getEntity().getHeight()/2);
			
			if(Math.abs(xdiff)>Math.abs(ydiff)){
				if(Math.abs(xdiff)<5){
				}else{
					e1.collide((int)(xtenacity),0);
				}
				
			}else{
				if(Math.abs(ydiff)<5){
				}else{
					e1.collide(0,(int)(ytenacity));
				}
				
			}
			
			return true;
		}else
			return false;
	}
	public void collide(Entity e,Block b){
		if(b.getCollision())
			collideStop(e,b.getBlock());
	}
	
	
	public void collide(Entity e1, Entity e2){
		collideBounce(e2,e1);
		
	}
	
	public void collide(Player p, Entity e){
		if(collidePlayerBounce(e,p)){
			p.damage(e.melee());
			
		}
	}
	
	public void collide(Player p, ArrayList<ItemEntity> s){
		for(int i=0;i<s.size();i++){
			if(collidePlayerBounce(s.get(i),p)){
				
				if(s.get(i).pickup(p)){
					s.remove(i);
					i=s.size();
				}
			}
		}
	}
	
	public void collide(ArrayList<AbsProjectile> p, ArrayList<HostileEntity> e,ArrayList<ItemEntity> s){
		for(int i=0;i<p.size();i++){
			for(int j=0;j<e.size();j++){
				if(p.get(i).collide(e.get(j))){
					e.get(j).knockback(p.get(i).getXKnockback(), p.get(i).getYKnockback());
					p.remove(i);
					j=e.size();
					i--;
				}
			}
			
		}
	}
	public void collide(ArrayList<AbsProjectile> s,Player p){
		for(int i=0;i<s.size();i++){
			if(s.get(i).collide(p)){
				p.collide(s.get(i).getXKnockback(), s.get(i).getYKnockback());
				s.remove(i);
				i--;
				
			}
			
		}

	}
	public void collide(ArrayList<AbsProjectile> p, Block b){
		for(int i=0;i<p.size();i++){
			if(p.get(i).getX()<-100||p.get(i).getX()>1500||p.get(i).getY()<-100||p.get(i).getY()>1500){
				p.remove(i);
				i--;
			}else if(b.getCollision()){
				if(p.get(i).collide(b)){
					p.remove(i);
					i--;
				}
			}
			
		}
	}
}
