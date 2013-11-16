package de.philip.entity;

import java.awt.image.BufferedImage;

public class Sprite {
	
	private int id;
	private BufferedImage image;
	
	public Sprite(int id, BufferedImage image) {
		this.id = id;
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
	
}
