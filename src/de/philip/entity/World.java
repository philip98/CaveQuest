package de.philip.entity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashSet;

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
		BufferedReader br = new BufferedReader(new FileReader(f));
		// TODO: Parse world file
		return null;
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