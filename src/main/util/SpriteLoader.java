package main.util;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class SpriteLoader {
	
	static BufferedImage spriteSheet=null;
	static BufferedImage chest=null,chestOpen,key,spellbook=null;
	static BufferedImage fireballSpell,laserSpell,dashSpell,blackholeSpell;
	static BufferedImage spriteSheet2=null,spriteSheet3=null,spriteSheet4=null,cat=null,bubble=null,exp=null,laser1,laser2;
	static BufferedImage[] fireball;
	static BufferedImage[] fruits;
	static BufferedImage[] tutorial;
	static BufferedImage title,titleBlur,titleBackground,titleOutline,titleStart,titleAbout,titleHelp;
	static BufferedImage gameBackground;
	static BufferedImage glow;
	public SpriteLoader(){
		if(spriteSheet==null){
			BufferedImageLoader loader = new BufferedImageLoader();
			try{
				spriteSheet2 = loader.loadImage("/Item/health_mana.png");
				spriteSheet = loader.loadImage("/sprite_sheet.png");
				spriteSheet3 = loader.loadImage("/Item/health_heart.png");
				spriteSheet4 = loader.loadImage("/Block/block.png");
				cat = loader.loadImage("/HostileEntity/cat_cannon.png");
				exp = loader.loadImage("/Item/exp.png");
				bubble = loader.loadImage("/Projectile/bubble.png");
				chest = loader.loadImage("/Item/Chest.png");
				spellbook = loader.loadImage("/Item/spells/spellbook.png");
				title = loader.loadImage("/Title/Title.png");
				titleBackground = loader.loadImage("/Title/Background.png");
				titleBlur = loader.loadImage("/Title/Title_Blur.png");
				titleOutline = loader.loadImage("/Title/Title_Outline.png");
				glow = loader.loadImage("/Particle/Glow_Particle.png");
				titleStart = loader.loadImage("/Title/Start.png");
				titleHelp = loader.loadImage("/Title/Help.png");
				titleAbout = loader.loadImage("/Title/About.png");
				fireballSpell = loader.loadImage("/Item/spells/spellbook_fire.png");
				key = loader.loadImage("/Item/key.png");
				chestOpen = loader.loadImage("/Item/Chest_open.png");
				laserSpell = loader.loadImage("/Item/spells/spellbook_laser.png");
				dashSpell = loader.loadImage("/Item/spells/spellbook_dash.png");
				blackholeSpell = loader.loadImage("/Item/spells/spellbook_blackhole.png");
				gameBackground = loader.loadImage("/Block/Background2.png");
				laser1 = loader.loadImage("/Projectile/laser1.png");
				laser2 = loader.loadImage("/Projectile/laser2.png");
				
				fireball = new BufferedImage[6];
				for(int i = 0;i<fireball.length;i++){
					String loc = "/Projectile/fireball/fireball"+i+".png";
					fireball[i] = loader.loadImage(loc);
				}
				
				
				fruits = new BufferedImage[5];
				fruits[0] = loader.loadImage("/Projectile/grape.png");
				fruits[1] = loader.loadImage("/Projectile/apple.png");
				fruits[2] = loader.loadImage("/Projectile/melon.png");
				fruits[3] = loader.loadImage("/Projectile/banana.png");
				fruits[4] = loader.loadImage("/Projectile/orange.png");
				
				tutorial = new BufferedImage[8];
				tutorial[0] = loader.loadImage("/Tutorial/tut1.png");
				tutorial[1] = loader.loadImage("/Tutorial/tut2.png");
				tutorial[2] = loader.loadImage("/Tutorial/tut3.png");
				tutorial[3] = loader.loadImage("/Tutorial/tut4.png");
				tutorial[4] = loader.loadImage("/Tutorial/tut5.png");
				tutorial[5] = loader.loadImage("/Tutorial/tut6.png");
				tutorial[6] = loader.loadImage("/Tutorial/tut7.png");
				tutorial[7] = loader.loadImage("/Tutorial/tut8.png");
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		
		
	}
	public BufferedImage[]	getTutorial(){
		return tutorial;
	}
	public BufferedImage getLaserH(){
		return laser1;
	}
	public BufferedImage getLaserV(){
		return laser2;
	}
	public BufferedImage getGameBackground(){
		return gameBackground;
	}
	public BufferedImage getChestOpen(){
		return this.chestOpen;
	}
	public BufferedImage getLaserSpellbook(){
		return this.laserSpell;
	}
	public BufferedImage getDashSpellbook(){
		return this.dashSpell;
	}
	public BufferedImage getBlackholeSpellbook(){
		return this.blackholeSpell;
	}
	public BufferedImage getKey(){
		return key;
	}
	public BufferedImage getFireballSpellbook(){
		return this.fireballSpell;
	}
	public BufferedImage getTitleHelp(){
		return titleHelp;
	}
	public BufferedImage getTitleAbout(){
		return titleAbout;
	}
	public BufferedImage getTitleStart(){
		return titleStart;
	}
	public BufferedImage getGlow(){
		return glow;
	}
	public BufferedImage getTitleOutline(){
		return titleOutline;
	}
	public BufferedImage getTitle(){
		return title;
	}
	public BufferedImage getTitleBlur(){
		return titleBlur;
	}
	public BufferedImage getTitleBackground(){
		return titleBackground;
	}
	public SpriteSheet getSS(){
		SpriteSheet ss = new SpriteSheet(spriteSheet);
		return ss;
	}
	public SpriteSheet getSS2(){
		SpriteSheet ss = new SpriteSheet(spriteSheet2);
		return ss;
	}
	public SpriteSheet getSS3(){
		SpriteSheet ss = new SpriteSheet(spriteSheet3);
		return ss;
	}
	public SpriteSheet getSS4(){
		SpriteSheet ss = new SpriteSheet(spriteSheet4);
		return ss;
	}
	public SpriteSheet getCat(){
		SpriteSheet ss = new SpriteSheet(cat);
		return ss;
	}
	public BufferedImage[] getFruit(){
		return fruits;
	}
	public SpriteSheet getExp(){
		SpriteSheet ss = new SpriteSheet(exp);
		return ss;
	}
	public SpriteSheet getBubble(){
		SpriteSheet ss = new SpriteSheet(bubble);
		return ss;
	}
	public BufferedImage[] getFireball(){
		return fireball;
	}
	public BufferedImage getChest(){
		return chest;
	}
	public BufferedImage getSpellbook(){
		return spellbook;
	}


}
