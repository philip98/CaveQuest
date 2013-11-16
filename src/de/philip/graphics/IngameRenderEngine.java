package de.philip.graphics;

import java.awt.Graphics;

import de.philip.CaveQuest;
import de.philip.entity.Sprite;
import de.philip.entity.World;

public class IngameRenderEngine extends RenderEngine {

	public IngameRenderEngine() {
		super();
	}
	
	public void render(Graphics g) {
		
		World w = CaveQuest.getInstance().getWorld();
		for(int y = 0; y < w.getHeight(); y++) {
			for(int x = 0; x < w.getWidth(); x++) {
				Sprite s = w.getSprites()[x][y];
				g.drawImage(s.getImage(), 40*x, 40*y, null);
			}
		}
		
	}

}
