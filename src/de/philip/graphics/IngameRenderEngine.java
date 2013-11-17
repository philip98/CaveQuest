package de.philip.graphics;

import java.awt.Graphics;

import de.philip.CaveQuest;
import de.philip.entity.Player;
import de.philip.entity.Sprite;
import de.philip.entity.SpriteSheet;
import de.philip.entity.World;
import de.philip.util.Logger;

public class IngameRenderEngine extends RenderEngine {

	private int renderDistance = 13;

	public IngameRenderEngine() {
		super();
	}

	public void render(Graphics g) {

		World w = CaveQuest.getInstance().getWorld();
		Player p = CaveQuest.getInstance().getPlayer();

		for (int y = p.getY() / 40 - renderDistance; y < p.getY() / 40 + renderDistance; y++) {
			for (int x = p.getX() / 40 - renderDistance; x < p.getX() / 40 + renderDistance; x++) {
				if (w.getSprites().length > y && w.getSprites()[0].length > x && x > 0 && y > 0) {
					g.drawImage(w.getSprites()[x][y].getImage(), 40 * x - p.getX() + CaveQuest.getInstance().getWidth() / 2, 40 * y - p.getY()
							+ CaveQuest.getInstance().getHeight() / 2, null);
				}
			}
		}
		Sprite s = SpriteSheet.sprites[p.getDir() + 2];
		g.drawImage(s.getImage(), CaveQuest.getInstance().getWidth() / 2, CaveQuest.getInstance().getHeight() / 2, null);
	}

}
