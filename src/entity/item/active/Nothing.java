package entity.item.active;

import java.awt.Graphics;

import entity.player.Player;

public class Nothing extends ActiveItem{

	public Nothing(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		this.setItemID(50);
	}

	@Override
	public void render(Graphics win) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean activate(Player p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void playerTick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void playerRender(Graphics win) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getDamage() {
		// TODO Auto-generated method stub
		return 0;
	}

}
