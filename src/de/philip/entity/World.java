package de.philip.entity;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import de.philip.util.Logger;

public class World {

	private int width;
	private int height;
	private int[][] tiles;
	private Sprite[][] sprites;
	private ArrayList<WorldObject> objects;
	
	public World(int width, int height, ArrayList<WorldObject> objects, int[][] tiles) {
		this.width = width;
		this.height = height;
		this.objects = objects;
		this.tiles = tiles;
		this.sprites = SpriteSheet.convertTiles(tiles);
		
		BufferedImage img;
		int col;
		for (int i = 2; i < 6; ++i) {
			img = SpriteSheet.sprites[i].getImage();
			for (int x = 0; x < 40; ++x) {
				for (int y = 0; y < 40; ++y) {
					col = img.getRGB(x, y);
					if ((col & 0xFF00FF) == 0xFF00FF) 
						col = 0x00000000;
					img.setRGB(x, y, col);
				}
			}
			SpriteSheet.sprites[i].setImage(img);
		}
	}

	public static World load(File fTxt, File fPng) throws Exception {
		Logger.log("Starting World Parsing file " + fTxt.getAbsolutePath());
		BufferedReader br = new BufferedReader(new FileReader(fTxt));
		ArrayList<WorldObject> objects = new ArrayList<>();
		String line;
		while ((line = br.readLine()) != null) {
			if (line.startsWith("object:")) {
				String[] split = line.replaceFirst("object:", "").split(" ");
				int id = Integer.valueOf(split[0]);
				int x = Integer.valueOf(split[1]);
				int y = Integer.valueOf(split[2]);
				int width = Integer.valueOf(split[3]);
				int height = Integer.valueOf(split[4]);
				boolean solid = (split[5].equalsIgnoreCase("true") || split[5].equalsIgnoreCase("1")) ? true : false;
				Sprite sprite = SpriteSheet.sprites[Integer.valueOf(split[6])];
				objects.add(new WorldObject(id, x, y, width, height, solid, sprite));
			}
		}
		Logger.log("Starting World Parsing file " + fPng.getAbsolutePath());
		BufferedImage worldImage = ImageIO.read(fPng);
		int[][] tiles = new int[worldImage.getWidth()][worldImage.getHeight()];
		for (int y = 0; y < worldImage.getHeight(); y++) {
			for (int x = 0; x < worldImage.getHeight(); x++) {
				int col = worldImage.getRGB(x, y);
				tiles[x][y] = col;
			}
		}
		br.close();
		Logger.log("Finished Parsing! World width=" +worldImage.getWidth() + " height=" + worldImage.getHeight() + " objects=" + objects.size() + " tiles=" + tiles.length);
		return new World(worldImage.getWidth(), worldImage.getHeight(), objects, tiles);
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

	public ArrayList<WorldObject> getObjects() {
		return objects;
	}

	public void setObjects(ArrayList<WorldObject> objects) {
		this.objects = objects;
	}

	public int[][] getTiles() {
		return tiles;
	}

	public void setTiles(int[][] tiles) {
		this.tiles = tiles;
	}

	public Sprite[][] getSprites() {
		return sprites;
	}

	public void setSprites(Sprite[][] sprites) {
		this.sprites = sprites;
	}

}