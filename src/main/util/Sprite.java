package main.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class Sprite {
	static protected SpriteLoader sL = new SpriteLoader();
	static protected BufferedImage normalBlock,floor,gameBackground;;
	static protected BufferedImage doorBlock;
	static protected BufferedImage player;
	static protected BufferedImage[] playerFace = new BufferedImage[4];
	static protected BufferedImage projectile,bubble,laser1,laser2;
	static protected BufferedImage[] slime,cat;
	static protected BufferedImage[][] skeleton;
	static protected BufferedImage mana,health,exp;
	static protected BufferedImage[] fireball,fruits;
	static protected BufferedImage[] tutorial;
	static protected BufferedImage spellbook,chest,chestOpen,key,goldenEgg,chalice,healthPotion,manaPotion,shield,boots,gem,scroll,sword,ring,cookie,candy,bow,cloak,necklace,cheese;
	static protected BufferedImage title, titleBackground, titleBlur,titleOutline,titleStart,titleHelp,titleAbout;
	static protected BufferedImage glow;
	static protected BufferedImage fireballSpellbook,blackholeSpellbook,dashSpellbook,laserSpellbook;
	static protected BufferedImage basicWand,fruitWand,laserWand,rapidWand,bubbleWand;
	
	public Sprite(){
		sL = new SpriteLoader();
		goldenEgg = sL.getSS().grabImage(8, 1, 32, 32);
		chalice = sL.getSS().grabImage(8, 2, 32, 32);
		healthPotion = sL.getSS().grabImage(8, 3, 32, 32);
		manaPotion = sL.getSS().grabImage(8, 4, 32, 32);
		shield = sL.getSS().grabImage(8, 5, 32, 32);
		boots = sL.getSS().grabImage(8, 6, 32, 32);
		gem = sL.getSS().grabImage(8, 7, 32, 32);
		scroll = sL.getSS().grabImage(8, 8, 32, 32);
		sword = sL.getSS().grabImage(8, 9, 32, 32);
		ring = sL.getSS().grabImage(8, 10, 32, 32);
		necklace = sL.getSS().grabImage(7, 6, 32, 32);
		cloak = sL.getSS().grabImage(7, 7, 32, 32);
		bow = sL.getSS().grabImage(7, 8, 32, 32);
		candy = sL.getSS().grabImage(7, 9, 32, 32);
		cookie = sL.getSS().grabImage(7, 10, 32, 32);
		cheese = sL.getSS().grabImage(6, 5, 32, 32);
		glow = sL.getGlow();
		cat = new BufferedImage[2];
		slime = new BufferedImage[2];
		skeleton = new BufferedImage[9][2];
		cat[0] = sL.getSS().grabImage(5, 2, 64, 64);
		cat[1] = sL.getSS().grabImage(6, 2, 64, 64);
		slime[0] = sL.getSS().grabImage(5, 1, 34, 21);
		slime[1] = sL.getSS().grabImage(6, 1, 34, 21);
		basicWand = sL.getSS().grabImage(7, 1, 32, 32);
		bubbleWand = sL.getSS().grabImage(7, 2, 32, 32);
		fruitWand = sL.getSS().grabImage(7, 3, 32, 32);
		laserWand = sL.getSS().grabImage(7, 4, 32, 32);
		rapidWand = sL.getSS().grabImage(7, 5, 32, 32);
		skeleton[0][0] = sL.getSS().grabImage(3, 1, 20, 63);
		skeleton[0][1] = sL.getSS().grabImage(4, 1, 20, 63);
		skeleton[1][0] = sL.getSS().grabImage(3, 2, 22, 64);
		skeleton[1][1] = sL.getSS().grabImage(4, 2, 22, 64);
		skeleton[2][0] = sL.getSS().grabImage(3, 3, 20, 61);
		skeleton[2][1] = sL.getSS().grabImage(4, 3, 20, 61);
		skeleton[3][0] = sL.getSS().grabImage(3, 4, 35, 64);
		skeleton[3][1] = sL.getSS().grabImage(4, 4, 35, 64);
		skeleton[4][0] = sL.getSS().grabImage(3, 5, 34, 61);
		skeleton[4][1] = sL.getSS().grabImage(4, 5, 34, 61);
		skeleton[5][0] = sL.getSS().grabImage(3, 6, 33, 63);
		skeleton[5][1] = sL.getSS().grabImage(4, 6, 33, 63);
		skeleton[6][0] = sL.getSS().grabImage(3, 7, 33, 63);
		skeleton[6][1] = sL.getSS().grabImage(4, 7, 33, 63);
		skeleton[7][0] = sL.getSS().grabImage(3, 8, 34, 64);
		skeleton[7][1] = sL.getSS().grabImage(4, 8, 34, 64);
		skeleton[8][0] = sL.getSS().grabImage(3, 9, 28, 64);
		skeleton[8][1] = sL.getSS().grabImage(4, 9, 28, 64);
		normalBlock = sL.getSS().grabImage(2,2,64,64);
		doorBlock = sL.getSS().grabImage(2,3,64,64);
		playerFace[0] = sL.getSS().grabImage(1,4,48,64);
		playerFace[1] = sL.getSS().grabImage(1,1,48,64);
		playerFace[2] = sL.getSS().grabImage(1,3,48,64);
		playerFace[3] = sL.getSS().grabImage(1,2,48,64);
		player = playerFace[1];
		projectile = sL.getSS().grabImage(2,1,20,20);
		mana = sL.getSS2().grabImage(1, 1, 32, 32);
		health = sL.getSS3().grabImage(1, 1, 32, 32);
		floor = sL.getSS4().grabImage(1, 1, 64, 64);
		fruits = sL.getFruit();
		bubble = sL.getBubble().grabImage(1, 1, 20, 20);
		exp = sL.getExp().grabImage(1, 1, 32, 32);
		fireball = sL.getFireball();
		spellbook = sL.getSpellbook();
		chest = sL.getChest();
		title = sL.getTitle();
		titleBlur = sL.getTitleBlur();
		titleBackground = sL.getTitleBackground();
		titleOutline = sL.getTitleOutline();
		titleStart = sL.getTitleStart();
		titleAbout = sL.getTitleAbout();
		titleHelp = sL.getTitleHelp();
		fireballSpellbook = sL.getFireballSpellbook();
		key = sL.getKey();
		chestOpen = sL.getChestOpen();
		blackholeSpellbook = sL.getBlackholeSpellbook();
		dashSpellbook = sL.getDashSpellbook();
		laserSpellbook = sL.getLaserSpellbook();
		gameBackground = sL.getGameBackground();
		laser1 = sL.getLaserH();
		laser2 = sL.getLaserV();
		tutorial = sL.getTutorial();
	}
	public BufferedImage getGoldenEgg(){
		return goldenEgg;
	}
	public BufferedImage getChalice(){
		return chalice;
	}
	public BufferedImage getHealthPot(){
		return healthPotion;
	}
	public BufferedImage getManaPot(){
		return manaPotion;
	}
	public BufferedImage getShield(){
		return shield;
	}
	public BufferedImage getBoots(){
		return boots;
	}
	public BufferedImage getGem(){
		return gem;
	}
	public BufferedImage getScroll(){
		return scroll;
	}
	public BufferedImage getSword(){
		return sword;
	}
	public BufferedImage getRing(){
		return ring;
	}
	public BufferedImage getNecklace(){
		return necklace;
	}
	public BufferedImage getCloak(){
		return cloak;
	}
	public BufferedImage getBow(){
		return bow;
	}
	public BufferedImage getCandy(){
		return candy;
	}
	public BufferedImage getCookie(){
		return cookie;
	}public BufferedImage getCheese(){
		return cheese;
	}
	public BufferedImage getBasicWand(){
		return basicWand;
	}
	public BufferedImage getBubbleWand(){
		return bubbleWand;
	}
	public BufferedImage getFruitWand(){
		return fruitWand;
	}
	public BufferedImage getLaserWand(){
		return laserWand;
	}
	public BufferedImage getRapidWand(){
		return rapidWand;
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
	public BufferedImage getBlackholeSpellbook(){
		return this.blackholeSpellbook;
	}
	public BufferedImage getDashSpellBook(){
		return this.dashSpellbook;
	}
	public BufferedImage getLaserSpellbook(){
		return this.laserSpellbook;
	}
	public BufferedImage getKey(){
		return key;
	}
	public BufferedImage getFireballSpellbook(){
		return fireballSpellbook;
	}
	public BufferedImage getTitleStart(){
		return titleStart;
	}
	public BufferedImage getTitleHelp(){
		return titleHelp;
	}
	public BufferedImage getTitleAbout(){
		return titleAbout;
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
	public BufferedImage getChest(){
		return chest;
	}
	public BufferedImage getSpellbook(){
		return spellbook;
	}
	public BufferedImage getBubble(){
		return bubble;
	}
	public BufferedImage getExp(){
		return exp;
	}
	public BufferedImage[] getFruits(){
		return fruits;
	}
	public BufferedImage getFloor(){
		return floor;
	}
	public BufferedImage[] getCat(){
		return cat;
	}
	public BufferedImage getMana(){
		return mana;
	}
	public BufferedImage getHealth(){
		return health;
	}
	public BufferedImage getNormalBlock(){
		return normalBlock;
	}
	public BufferedImage getPlayer(){
		return player;
	}
	public BufferedImage getPlayerFace(short x){
		return playerFace[x];
	}
	public BufferedImage[] getFireball(){
		return fireball;
	}
	public void playerFaceUp(){
		player = playerFace[0];
	}
	public void playerFaceDown(){
		player = playerFace[2];
		}
	public void playerFaceRight(){
		player = playerFace[1];
	}
	public void playerFaceLeft(){
		player = playerFace[3];
	}
	public BufferedImage getProjectile(){
		return projectile;
	}
	public BufferedImage[][] getSkeleton(){
		return skeleton;
	}
	public BufferedImage[] getSlime(){
		return slime;
	}

	public Image getDoorBlock() {
		return doorBlock;
	}
	public static BufferedImage resize(BufferedImage img, int newW, int newH) { 
	    Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
	    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2d = dimg.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    g2d.dispose();

	    return dimg;
	} 
	
	public BufferedImage recolor(BufferedImage image, Color c) {
		int width = image.getWidth();
		int height = image.getHeight();
		BufferedImage redVersion = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D) redVersion.getGraphics();
		g.setColor(c);
		g.fillRect(0, 0, width, height);
		g.setComposite(AlphaComposite.DstIn);
		g.drawImage(image, 0, 0, width, height, 0, 0, width, height, null);
		return redVersion;
	}
	public BufferedImage damaged(BufferedImage image){
		int width = image.getWidth();
		int height = image.getHeight();
		BufferedImage redVersion = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D) redVersion.getGraphics();
		g.setColor(Color.red);
		g.fillRect(0, 0, width, height);
		g.setComposite(AlphaComposite.DstIn);
		g.drawImage(image, 0, 0, width, height, 0, 0, width, height, null);
		return redVersion;
	}
	public BufferedImage rotate(BufferedImage sprite,double rotation){
		AffineTransform trans = new AffineTransform();
		AffineTransform identity = new AffineTransform();
		
		trans.setTransform(identity);
		trans.translate(sprite.getHeight() / 2,sprite.getWidth() / 2);
		trans.rotate(-rotation);
		trans.translate(-sprite.getWidth() / 2,-sprite.getHeight() / 2);
		
		AffineTransformOp op = new AffineTransformOp(trans, AffineTransformOp.TYPE_BILINEAR);
		BufferedImage test =new BufferedImage(sprite.getHeight(), sprite.getWidth(), sprite.getType());
		op.filter(sprite, test);
		return test;
	}
	
}
