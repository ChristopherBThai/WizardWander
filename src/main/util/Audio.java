package main.util;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;
import java.util.ArrayList;

public class Audio {
	static private ArrayList<sound> sounds;
	static private sound playerHurt,death;
	static private sound fireballExplosion;
	static private sound dash,laser,blackhole;
	static private sound noMana,chest;
	static private sound bgm1,bossbgm,titlebgm,victorybgm;
	static private sound gameoverbgm;
	static private sound fruit,bubble,basicWand,rapidWand;
	static private sound select;
	static private sound doorMovement;
	public Audio(){
		if(sounds == null){
			playerHurt = new sound(Audio.class.getResource("/Audio/PlayerHurt.wav"),3);
			basicWand = new sound(Audio.class.getResource("/Audio/basicWand.wav"),3);
			rapidWand = new sound(Audio.class.getResource("/Audio/RapidWand.wav"),3);
			death = new sound(Audio.class.getResource("/Audio/Death.wav"),3);
			fireballExplosion = new sound(Audio.class.getResource("/Audio/FireballExplosion.wav"),3);
			dash = new sound(Audio.class.getResource("/Audio/Dash.wav"),3);
			noMana = new sound(Audio.class.getResource("/Audio/NoMana.wav"),3);
			fruit = new sound(Audio.class.getResource("/Audio/fruit.wav"),3);
			select = new sound(Audio.class.getResource("/Audio/select.wav"),3);
			laser = new sound(Audio.class.getResource("/Audio/laser.wav"),3);
			bubble = new sound(Audio.class.getResource("/Audio/bubble.wav"),4);
			doorMovement = new sound(Audio.class.getResource("/Audio/door_open.wav"),1);
			blackhole = new sound(Audio.class.getResource("/Audio/black_hole.wav"),3);
			sounds = new ArrayList<sound>();
			bgm1 = new sound(Audio.class.getResource("/Audio/tree.wav"),1);
			bossbgm = new sound(Audio.class.getResource("/Audio/boss.wav"),1);
			chest = new sound(Audio.class.getResource("/Audio/chest.wav"),1);
			gameoverbgm = new sound(Audio.class.getResource("/Audio/game_over_loop.wav"),1);
			titlebgm = new sound(Audio.class.getResource("/Audio/title_theme.wav"),1);
			victorybgm = new sound(Audio.class.getResource("/Audio/victory_jingle.wav"),1);
		}
	}
	public void tick(){
	}
	public void playRapidWand(){
		rapidWand.play();
	}
	public void playBasicWand(){
		basicWand.play();
	}
	public void playDeath(){
		death.play();
	}
	public void playChest(){
		chest.play();
	}
	public void playVictory(){
		victorybgm.play();
	}
	public void playBlackhole(){
		blackhole.play();
	}
	public void playBubble(){
		bubble.play();
	}
	public void playDoor(){
		doorMovement.play();
	}
	public void playLaser(){
		laser.play();
	}
	public void playSelect(){
		select.play();
	}
	public void playFruit(){
		fruit.play();
	}
	public void playFireballExplosion(){
		fireballExplosion.play();
		
	}
	public void playPlayerHurt(){
		playerHurt.play();
	}
	public void playDash(){
		dash.play();
	}
	public void playNoMana(){
		noMana.play();
	}
	public void playBmg1(){
		bgm1.loop();
	}
	public void stopBmg1(){
		bgm1.stop();
	}
	public void playBossbgm(){
		bossbgm.loop();
	}
	public void stopBossbgm(){
		bossbgm.stop();
	}
	public void playTitlebgm(){
		titlebgm.loop();
	}
	public void stopTitlebgm(){
		titlebgm.stop();
	}
	public void playGameOverbgm(){
		gameoverbgm.loop();
	}
	public void stopGameOverbgm(){
		gameoverbgm.stop();
	}
	private class sound{
		int count;
		URL url;
		AudioClip[] temp;
		public sound(URL url,int amount){
			this.url = url;
			count = 0;
			temp = new AudioClip[amount];
			for(int i=0;i<temp.length;i++){
				temp[i] = Applet.newAudioClip(url);
			}
			
		}
		public boolean isDone(){
			count--;
			if(count<=0)
				return true;
			return false;
		}
		public void play(){
			temp[count].play();
			count++;
			if(count==temp.length){
				count=0;
			}
		}
		public void loop(){
			temp[count].loop();
		}
		public void stop(){
			temp[count].stop();
		}
	}
}
