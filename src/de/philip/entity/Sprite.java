package de.philip.entity;

import java.awt.image.BufferedImage;

public class Sprite {

	private int id;
	private int color;
	private BufferedImage image;

	public Sprite(int id, int color, BufferedImage image) {
		this.id = id;
		this.color = color;
		this.image = image;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

}
