package de.philip.entity;

import java.awt.image.BufferedImage;

import de.philip.util.Util;

public class WorldObject {
	
	private int id;
	private int x;
	private int y;
	private int width;
	private int height;
	private boolean solid;
	private BufferedImage image;
	
	public WorldObject(int id, int x, int y, int width, int height, boolean solid, String base64) {
		this.setId(id);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.solid = solid;
		this.image = Util.decodeToImage(base64);
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isSolid() {
		return solid;
	}

	public void setSolid(boolean solid) {
		this.solid = solid;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}