package de.philip.entity;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

	public static final String PATH = "res/sprites.png";

	public static BufferedImage sheet;
	static {
		try {
			sheet = ImageIO.read(new File(PATH));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static final Sprite[] sprites = new Sprite[] {
		new Sprite(0, 0xAB5F34, sheet.getSubimage(0, 0, 40, 40)), // Dirt
		new Sprite(1, 0x009E00, sheet.getSubimage(40, 0, 40, 40)) // Grass
	};

}
