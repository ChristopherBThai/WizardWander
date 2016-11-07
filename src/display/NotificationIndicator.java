package display;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

public class NotificationIndicator {
	private static ArrayList<Display> list;
	public NotificationIndicator(){
		if(list==null){
			list = new ArrayList<Display>();
		}
	}
	public void ItemNotif(String Name,String subText){
		Display d = new Display(Color.WHITE,507,200,Name);
		d.setSize(300);
		d.isTitle(true);
		d.addBackground(true);
		d.setX(507);
		d.setY(260);
		d.setMaxTick(200);
		list.add(d);
		Display d2 = new Display(Color.WHITE,507,300,subText);
		d2.setSize(200);
		d2.isTitle(true);
		d2.setX(507);
		d2.setY(300);
		d2.setMaxTick(200);
		list.add(d2);
	}
	public void addDamage(int x, int y,double amount){
		if(amount>=0)
			list.add(new Display(Color.RED,x,y,amount));
	}
	public void addHeal(int x, int y,double amount){
		if(amount>=0)
			list.add(new Display(Color.GREEN,x,y,amount));
	}
	public void addExp(int x, int y, double amount){
		if(amount>=0)
			list.add(new Display(Color.YELLOW,x,y,""+(int)amount+ " xp"));
	}
	public void oom(int x, int y){
		list.add(new Display(Color.WHITE,x,y,"Not Enough Mana"));
	}
	public void string(String input,int x, int y){
		list.add(new Display(Color.WHITE,x,y,input));
	}
	public void string(String input,int x, int y,Color c){
		list.add(new Display(c,x,y,input));
	}
	public void string(String input,int x, int y,Color c,int size){
		Display d = new Display(c,x,y,input);
		d.setSize(size);
		this.list.add(d);
	}
	public void string(String input,int x, int y,Color c,int size,int tick){
		Display d = new Display(c,x,y,input);
		d.setSize(size);
		d.setMaxTick(tick);
		list.add(d);
	}
	public void addMana(int x, int y, double amount){
		list.add(new Display(Color.BLUE,x,y,amount));
	}
	public void tick(){
		for(int i=0;i<list.size();i++){
			if(list.get(i).tick()){
				
				list.remove(i);
				i--;
			}
		}
	}
	public void render(Graphics win){
		
		
		Graphics2D g2 = (Graphics2D) win;
		for(int i=0;i<list.size();i++){
			Composite c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,list.get(i).percent());
			g2.setComposite(c);
			
			win.setFont(new Font("Helvetica", Font.BOLD,(int)(10+list.get(i).getSize()/10)));
			
			win.setColor(Color.BLACK);
			if(list.get(i).isBackground()){
				win.fillRect((int)(507-150), 220, 300, 100);
			}
			win.drawString(""+(list.get(i).getDisplay()), (int)(list.get(i).getx()-win.getFontMetrics().stringWidth(list.get(i).getDisplay())/2)+1,list.get(i).gety()+1);
			win.setColor(list.get(i).getColor());
			win.drawString((list.get(i).getDisplay()), (int)(list.get(i).getx()-win.getFontMetrics().stringWidth(list.get(i).getDisplay())/2),list.get(i).gety());
		}
		Composite c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f);
		g2.setComposite(c);
		
	}
	public static class Display{
		double x,y;
		double amount;
		String string;
		boolean isTitle;
		
		int size;
		boolean stringOnly,background;
		
		Color c;
		
		static Random rand;
		
		int maxTick,currentTick;
		
		public Display(Color c,int x, int y, double amount){
			rand = new Random();
			this.x = x+rand.nextInt(20)-10;
			this.y = y+rand.nextInt(14)-7;
			this.amount = amount;
			currentTick = 1;
			maxTick = 40;
			string = "";
			this.c = c;
		}
		public Display(Color c,int x, int y, double amount, String input){
			rand = new Random();
			this.x = x+rand.nextInt(20)-10;
			this.y = y+rand.nextInt(14)-7;
			this.amount = amount;
			currentTick = 1;
			maxTick = 40;
			string = input;
			this.c = c;
			stringOnly = false;
		}
		public Display(Color c,int x, int y, String input){
			rand = new Random();
			this.x = x+rand.nextInt(20)-10;
			this.y = y+rand.nextInt(14)-7;
			this.amount = -1;
			currentTick = 1;
			maxTick = 40;
			string = input;
			this.c = c;
			stringOnly = true;
		}
		public void addBackground(boolean x){
			background = x;
		}
		public boolean isBackground(){
			return background;
		}
		public boolean isTitle(){
			return isTitle;
		}
		public void isTitle(boolean input){
			isTitle = input;
		}
		public void setX(int x){
			this.x = x;
		}
		public void setY(int y){
			this.y = y;
		}
		public void setMaxTick(int x){
			maxTick = x;
		}
		public void setSize(int x){
			amount = x;
		}
		public int getSize(){
			if(amount<0)
				return 11;
			else if(amount>70&&!stringOnly)
				return 70;
			else
				return (int) amount;
		}
		public int getx(){
			return (int)x;
		}
		public int gety(){
			return (int)y;
		}
		public String getDisplay(){
			if(stringOnly||this.amount<0){
				return string;
			}else if(amount>0&&amount<1){
				return ((int)(amount*100))/100.0 + " " + string;
			}else{
				return (int)amount + " " + string;
			}
		}
		public boolean tick(){
			if(currentTick<maxTick){
				if(!isTitle)
					this.y-=(int)((maxTick/currentTick)*.3)+1;
					currentTick++;
				return false;
			}else
				return true;
		}
		public float percent(){
			return ((float)(maxTick-currentTick)/maxTick);
		}
		public Color getColor(){
			return c;
		}
	}
}

