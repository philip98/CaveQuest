package de.philip.entity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashSet;

import de.philip.util.Logger;

public class World {

	private int width;
	private int height;
	private HashSet<WorldObject> objects;

	public World(int width, int height, HashSet<WorldObject> objects) {
		this.width = width;
		this.height = height;
		this.objects = objects;
	}

	public static World load(File f) throws Exception {
		Logger.log("Starting World Parsing file " + f.getAbsolutePath());
		BufferedReader br = new BufferedReader(new FileReader(f));
		int worldWidth = 0;
		int worldHeight = 0;
		HashSet<WorldObject> objects = new HashSet<>();
		String line;
		while ((line = br.readLine()) != null) {
			Logger.log("Parsing Line: " + line);
			if (line.startsWith("width:")) {
				int value = Integer.parseInt(line.replaceFirst("width:", ""));
				worldWidth = value;
			} else if (line.startsWith("height:")) {
				int value = Integer.parseInt(line.replaceFirst("height:", ""));
				worldHeight = value;
			} else if (line.startsWith("object:")) {
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
		br.close();
		return new World(worldWidth, worldHeight, objects);
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

	public HashSet<WorldObject> getObjects() {
		return objects;
	}

	public void setObjects(HashSet<WorldObject> objects) {
		this.objects = objects;
	}

}