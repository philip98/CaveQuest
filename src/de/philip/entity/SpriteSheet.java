package de.philip.entity;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import de.philip.util.Logger;

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
	public static final Sprite[] sprites = new Sprite[] { new Sprite(0, 0xAB5F34, sheet.getSubimage(0, 0, 40, 40)), // Dirt
			new Sprite(1, 0x009E00, sheet.getSubimage(40, 0, 40, 40)) // Grass
	};

	public static Sprite getSprite(int col) {
		col = col ^ 0xFF000000;
		for (int i = 0; i < sprites.length; i++) {
			if (sprites[i].getColor() == col) {
				return sprites[i];
			}
		}
		Logger.log("Sprite Color " + Integer.toHexString(col).toUpperCase() + " returned null!");
		return null;
	}

	public static Sprite[][] convertTiles(int[][] tiles) {
		Sprite[][] s = new Sprite[tiles.length][tiles[0].length];
		for (int y = 0; y < tiles[0].length; y++) {
			for (int x = 0; x < tiles[0].length; x++) {
				s[x][y] = getSprite(tiles[x][y]);
			}
		}
		return s;
	}

}
