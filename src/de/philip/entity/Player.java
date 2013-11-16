package de.philip.entity;

import java.awt.event.KeyEvent;

import de.philip.CaveQuest;

public class Player {
	
	private int x;
	private int y;
	
	public Player(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void update() {
		if(CaveQuest.getInstance().getInputListener().isKeyDown(KeyEvent.VK_LEFT)) {
			x--;
		}
		if(CaveQuest.getInstance().getInputListener().isKeyDown(KeyEvent.VK_RIGHT)) {
			x++;
		}
		if(CaveQuest.getInstance().getInputListener().isKeyDown(KeyEvent.VK_UP)) {
			y--;
		}
		if(CaveQuest.getInstance().getInputListener().isKeyDown(KeyEvent.VK_DOWN)) {
			y++;
		}
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
