package display;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import main.util.Audio;
import main.util.Sprite;

public class MenuDisplay {
	private int location;
	final int selectionAmount = 3;
	final int xMid=507,yMid=392;
	static Audio a;
	
	boolean started = false;
	private double r,g=(Math.PI)/3,b=(2*Math.PI)/3;
	
	private double hover,hoverSpeed,rotate;
	
	private BufferedImage background,title,titleShadow,titleOutline,titleStart,titleAbout,titleHelp;
	
	private Sprite sprite = new Sprite();
	
	private int page; //0=about, 1=menu, 2=help1, 3=help2
	private double page0Offset,page1Offset,page2Offset,page3Offset,offset;
	private int transition;
	
	private boolean start,help,about;
	Random random = new Random();
	
	public MenuDisplay(){
		page0Offset = -xMid*2;
		page1Offset=0;
		page2Offset=xMid*2;
		page3Offset=xMid*2*2;
		a = new Audio();
		hoverSpeed = 1;
		rotate = .01;
		page = 1;
		titleStart = sprite.getTitleStart();
		titleAbout = sprite.getTitleAbout();
		titleHelp = sprite.getTitleHelp();
		background = sprite.getTitleBackground();
		title = sprite.getTitle();
		titleShadow = sprite.resize(sprite.getTitleBlur(),(int)(title.getWidth()*1.5),(int)(title.getHeight()*1.5));	
		titleOutline = sprite.recolor(sprite.getTitleOutline(), new Color((int)(Math.abs(Math.sin(r))*255),(int)(Math.abs(Math.sin(g))*255),(int)(Math.abs(Math.sin(b))*255)));
	}
	
	public void tick(){
		tickP0();
		tickP1();
		tickP2();
		tickP3();
		if(transition>0){
			if(offset>-xMid*2){
				offset-=60;
				page0Offset-=60;
				page1Offset-=60;
				page2Offset-=60;
				page3Offset-=60;
			}else{
				transition--;
				offset+=xMid*2;
			}
		}
		if(transition<0){
			if(offset<xMid*2){
				offset+=60;
				page0Offset+=60;
				page1Offset+=60;
				page2Offset+=60;
				page3Offset+=60;
			}else{
				transition++;
				offset-=xMid*2;
			}
		}
	}
	public void render(Graphics g){
		g.drawImage(background,
				(int)(xMid-background.getWidth()/2),
				(int)(yMid-background.getHeight()/2),
				null);
		
		
		renderP0(g);
		renderP1(g);
		renderP2(g);
		renderP3(g);		
	}
	public void startBgm(){
		if(!started){
			started = true;
			a.playTitlebgm();
			a.stopBmg1();
			a.stopBossbgm();
			a.stopGameOverbgm();
		}
	}
	public void moveUp(){
		if(page==1){
			location--;
			a.playSelect();
			if(location<0)
				location += selectionAmount;
		}
	}
	public void moveDown(){
		if(page==1){
			location++;
			a.playSelect();
			location%=selectionAmount;
		}
	}
	public void select(){
		if(page==1&&transition==0){
			if(location==0){	//Start Game
				start = true;
				help = false;
				about = false;
			}
			if(location==1){	
				help = true;	//Help Menu
				start = false;
				about = false;
				page=2;
				transition = 1;
			}
			if(location==2){	//About Page
				about = true;
				start = false;
				help = false;
				page=0;
				transition = -1;
			}
		}
		if(page==0&&transition==0){
			page++;
			transition=1;
		}
		if(page==2&&transition==0){
			page++;
			transition=1;
		}
		if(page==3&&transition==0){
			page=1;
			transition=-2;
		}
		
	}
	public boolean start(){
		return start;
	}
	public void reset(){
		start = false;
		help = false;
		about = false;
		location = 0;
		page = 1;
		page0Offset = -xMid*2;
		page1Offset=0;
		page2Offset=xMid*2;
		page3Offset=xMid*2*2;
		transition=0;
		started = true;
		a.playTitlebgm();
		a.stopBmg1();
		a.stopBossbgm();
		a.stopGameOverbgm();
	}
	
	private void tickP0(){
		titleOutline = sprite.recolor(titleOutline, new Color((int)(Math.abs(Math.sin(r))*255),(int)(Math.abs(Math.sin(g))*255),(int)(Math.abs(Math.sin(b))*255)));
		titleShadow = sprite.recolor(titleShadow, new Color((int)(Math.abs(Math.sin(r))*50),(int)(Math.abs(Math.sin(g))*50),(int)(Math.abs(Math.sin(b))*50)));
		r+=0.01;
		g+=0.01;
		b+=0.01;
		hover+=hoverSpeed*(Math.abs(Math.abs((int)hover)-10.5)/10);
		if(Math.abs(hover)>10){
			hover = 10*(Math.abs(hoverSpeed)/hoverSpeed);
			hoverSpeed*=-1;
		}
	}
	private void tickP1(){
		
	}
	private void tickP2(){
	
	}
	private void tickP3(){
	
	}
	
	
	private void renderP0(Graphics g){
			g.setColor(Color.WHITE);
			g.setFont(new Font("Helvetica",Font.BOLD,20));
			g.drawString("Created by Christopher Thai",(int)(200+page0Offset),100);
			g.drawString("and Cameron Lee",(int)(200+page0Offset),200);
			g.drawString("Group 503",(int)(200+page0Offset),300);
	}
	private void renderP1(Graphics g){
		g.drawImage(titleShadow,
				(int)(xMid-titleShadow.getWidth()/2-20+page1Offset),
				(int)(yMid-titleShadow.getHeight()/2-230),
				null);
		g.drawImage(title,
				(int)(xMid-title.getWidth()/2+page1Offset),
				(int)(yMid-title.getHeight()/2-250+hover),
				null);
		
		g.drawImage(titleOutline,
				(int)(xMid-titleOutline.getWidth()/2+page1Offset),
				(int)(yMid-titleOutline.getHeight()/2-250+hover),
				null);
		
		
		Graphics2D g2 = (Graphics2D) g;
		Composite c;
		if(location==0)
			c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f);
		else
			c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,.5f);
		g2.setComposite(c);
		
		g.drawImage(titleStart,
				(int)(xMid-titleStart.getWidth()/2+page1Offset),
				(int)(320),
				null);
		if(location==1)
			c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f);
		else
			c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,.5f);
		g2.setComposite(c);
		
		g.drawImage(titleHelp,
				(int)(xMid-titleHelp.getWidth()/2+page1Offset),
				(int)(450),
				null);
		if(location==2)
			c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f);
		else
			c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,.5f);
		g2.setComposite(c);
		
		g.drawImage(titleAbout,
				(int)(xMid-titleAbout.getWidth()/2+page1Offset),
				(int)(580),
				null);
		c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f);
		g2.setComposite(c);
	}
	
	BufferedImage player = sprite.resize(sprite.getPlayer(), sprite.getPlayer().getWidth()*2, sprite.getPlayer().getHeight()*2);
	
	private void renderP2(Graphics g){
		g.drawImage(sprite.getTutorial()[0], (int)(100+page2Offset), 250,null);
		g.drawImage(sprite.getFireballSpellbook(), (int)(110+page2Offset), 100,null);
		g.drawImage(sprite.getLaserSpellbook(), (int)(210+page2Offset), 100,null);
		g.drawImage(sprite.getBlackholeSpellbook(), (int)(310+page2Offset), 100,null);
		
		g.drawImage(sprite.getTutorial()[1], (int)(670+page2Offset), 250,null);
		g.drawImage(player, (int)(735+page2Offset), 70,null);
		
		g.drawImage(sprite.getTutorial()[2], (int)(150+page2Offset), 600,null);
		
		g.drawImage(sprite.getTutorial()[3], (int)(610+page2Offset), 600,null);
	}
	
	BufferedImage key = sprite.resize(sprite.getKey(), 110, 110);
	BufferedImage skeleton = sprite.resize(sprite.getSkeleton()[0][0], sprite.getSkeleton()[0][0].getWidth()*2, sprite.getSkeleton()[0][0].getHeight()*2);
	BufferedImage chest = sprite.resize(sprite.getChest(), sprite.getChest().getWidth()*2, sprite.getChest().getHeight()*2);
	BufferedImage slime = sprite.resize(sprite.getSlime()[0], sprite.getSlime()[0].getWidth()*5, sprite.getSlime()[0].getHeight()*5);
	BufferedImage heart = sprite.resize(sprite.getHealth(), sprite.getHealth().getWidth()*5, sprite.getHealth().getHeight()*5);
	
	private void renderP3(Graphics g){
		g.drawImage(sprite.getTutorial()[4], (int)(30+page3Offset), 210,null);
		g.drawImage(heart, (int)(200+page3Offset), 50,null);
		
		g.drawImage(sprite.getTutorial()[5], (int)(600+page3Offset), 210,null);
		g.drawImage(skeleton, (int)(780+page3Offset), 50,null);
		
		g.drawImage(sprite.getTutorial()[6], (int)(100+page3Offset), 560,null);
		g.drawImage(slime, (int)(210+page3Offset), 400,null);
		
		g.drawImage(sprite.getTutorial()[7], (int)(630+page3Offset), 560,null);
		g.drawImage(key, (int)(645+page3Offset), 405,null);
		g.drawImage(chest, (int)(800+page3Offset), 400,null);
	}
}
