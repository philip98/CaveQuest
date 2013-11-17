package de.philip.graphics;

import java.awt.Graphics;

import de.philip.CaveQuest;
import de.philip.entity.Player;
import de.philip.entity.Sprite;
import de.philip.entity.SpriteSheet;
import de.philip.entity.World;

public class IngameRenderEngine extends RenderEngine {

	public IngameRenderEngine() {
		super();
	}
	
	public void render(Graphics g) {
		if (CaveQuest.getInstance().pl != null && CaveQuest.getInstance().pl.getList().size() != 0) {
			Player p = CaveQuest.getInstance().pl.get(CaveQuest.getInstance().getPlayerID());
			World w = CaveQuest.getInstance().getWorld();
			for(int y = 0; y < w.getHeight(); y++) {
				for(int x = 0; x < w.getWidth(); x++) {
					Sprite s = w.getSprites()[x][y];
					g.drawImage(s.getImage(), 40*x - p.getX() + CaveQuest.getInstance().getWidth() / 2, 40*y - 
							p.getY() + CaveQuest.getInstance().getHeight() / 2, null); //World rendering
				}
			}
			Sprite s = SpriteSheet.sprites[p.getDir() + 2];
			g.drawImage(s.getImage(), CaveQuest.getInstance().getWidth() / 2, CaveQuest.getInstance().getHeight() / 2, null); //Player rendering
		}
	}
}
