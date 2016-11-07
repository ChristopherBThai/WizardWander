package entity.hostile;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import entity.hostile.movement.Follow;
import entity.item.ItemEntity;
import entity.item.consumable.ExperiencePotion;
import entity.item.consumable.HealthPotion;
import entity.item.consumable.ManaPotion;

public class Slime extends Follow{ //34x21
	
	int scale = 5;
	final int width = 34, height = 21, hitboxHeight = 17;
	
	double animationWidth, animationHeight,animationScale;
	
	BufferedImage slimeSprite[];
	
	ItemEntity hold;
	
	public Slime(int x, int y, int health) {
		super(2, 2, 34, 17, health);
		setLocation(x,y);
		animationWidth = width;
		animationHeight = height;
		animationScale = 0.04;
		this.setKnockbackResistance(30);
		setScale(scale);
	}
	public Slime(int x, int y, int health,int scale) {
		super(2, 2, 34, 17, health);
		setLocation(x,y);
		animationWidth = width;
		animationHeight = height;
		animationScale = 0.04;
		this.setKnockbackResistance(30);
		setScale(scale);
	}
	public void setScale(int scale){
		this.scale = scale;
		this.setSize(width*scale, hitboxHeight*scale);
		slimeSprite = new BufferedImage[2];
		slimeSprite[0] = sprite.resize(sprite.getSlime()[0],(int)(animationWidth*scale),(int)(animationHeight*scale));
		slimeSprite[1] = sprite.resize(sprite.getSlime()[1],(int)(animationWidth*scale),(int)(animationHeight*scale));
	}
	
	public void knockback(double x, double y){
		
		
	}
	

	@Override
	public void tick() {
		healthTick();
		meleeTick();
		move();
		pickupItem();
		moveItem();
		//walkAnimation();
		if(hold!=null)
			hold.tick();
	}
	public void walkAnimation(){
		if(animationWidth>width||animationWidth<(width*9)/10){
			animationScale*=-1;
		}
		animationWidth+=animationScale*2;
		this.setWidth((int)animationWidth*scale);
		this.setLocation((int)(this.getX()-animationScale*scale), (int)this.getY());
	}
	public void pickupItem(){
		if(hold == null&&scale>=2){
			for(int i=0;i<EH.getS().size();i++){
				if(this.getEntity().intersects(EH.getS().get(i).getEntity())){
					hold = EH.getS().remove(i);
					i=EH.getS().size();
				}
			}
		}
	}
	public void moveItem(){
		if(hold!=null){
			hold.setLocation(this.getX()+this.getWidth()/2-hold.getWidth()/2, this.getY()+this.getHeight()/2-hold.getHeight()/2);
		}
	}
	@Override
	public void render(Graphics win) {
		if(hold!=null)
			hold.render(win);
		Graphics2D g2 = (Graphics2D) win;
		Composite c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,.5f);
		g2.setComposite(c);
		win.drawImage(slimeSprite[this.getFace()], (int)getEntity().getX(),(int)getEntity().getY()-(height-hitboxHeight)*scale, null);
		c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f);
		g2.setComposite(c);
		healthRender(win);
		this.renderDamaged(win);
		
		
	}

	@Override
	public void drop() {
		if(scale>1){
			Slime slime1 = new Slime(this.getX(),this.getY(),(int) ((this.maxHealth*2)/3 + 1));
			slime1.setScale(this.scale-1);
			slime1.collide(rand.nextDouble()*10-5, rand.nextDouble()*10-5);
			slime1.setDamage((this.getDamage()*2)/3 +1);
			slime1.setMaxX((this.getMaxX()*6)/5);
			slime1.setMaxY((this.getMaxY()*6)/5);
			Slime slime2 = new Slime(this.getX(),this.getY(),(int) ((this.maxHealth*2)/3 + 1));
			slime2.setScale(this.scale-1);
			slime2.collide(rand.nextDouble()*10-5, rand.nextDouble()*10-5);
			slime2.setDamage((this.getDamage()*2)/3 +1);
			slime2.setMaxX((this.getMaxX()*6)/5);
			slime2.setMaxY((this.getMaxY()*6)/5);
			EH.addE(slime1);
			EH.addE(slime2);
		}
		
		
		if(PtB.roll(10)){
			int roll = rand.nextInt(100)+1;
			if(roll<=33){
				EH.addS(new HealthPotion(this.getXCenter(),this.getYCenter()));
			}else if(roll>33&&roll<=66){
				EH.addS(new ManaPotion(this.getXCenter(),this.getYCenter()));
			}else if(roll>66){
				EH.addS(new ExperiencePotion(this.getXCenter(),this.getYCenter()));
			}
		}
		if(hold!=null){
			EH.addS(hold);
			hold=null;
		}
		
	}
	@Override
	public void renderDamaged(Graphics g) {
		if(damageFrame>0){
			damageFrame--;
			Graphics2D g2 = (Graphics2D) g;
			Composite c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,.3f);
			g2.setComposite(c);g.drawImage(sprite.damaged(slimeSprite[this.getFace()]), (int)getEntity().getX(),(int)getEntity().getY()-(height-hitboxHeight)*scale, null);
			c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f);
			g2.setComposite(c);
		}
		
	}
}
