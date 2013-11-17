package de.philip.entity;

import java.awt.event.KeyEvent;

import de.philip.CaveQuest;

public class Player {
	
	private int x;
	private int y;
	private int dir; //0 - North; 1 - East; 2 - South; 3 - West
	
	public Player(int x, int y) {
		this.x = x;
		this.y = y;
		this.dir = 0;
	}
	
	public void update() {
		if(CaveQuest.getInstance().getInputListener().isKeyDown(KeyEvent.VK_LEFT)) {
			x--;
			dir = 3;
		}
		if(CaveQuest.getInstance().getInputListener().isKeyDown(KeyEvent.VK_RIGHT)) {
			x++;
			dir = 1;
		}
		if(CaveQuest.getInstance().getInputListener().isKeyDown(KeyEvent.VK_UP)) {
			y--;
			dir = 0;
		}
		if(CaveQuest.getInstance().getInputListener().isKeyDown(KeyEvent.VK_DOWN)) {
			y++;
			dir = 2;
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
	
	public int getDir() {
		return dir;
	}
	
	public void setDir(int dir) {
		this.dir = dir;
	}
}
