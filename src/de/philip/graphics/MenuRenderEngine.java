package de.philip.graphics;

import java.awt.Color;
import java.awt.Graphics;

import de.philip.CaveQuest;

public class MenuRenderEngine extends RenderEngine {

	public MenuRenderEngine(Graphics g) {
		super(g);
	}
	
	public void render() {
		Graphics g = super.getGraphics();
		g.setColor(Color.cyan);
		g.fillRect(0, 0, CaveQuest.getInstance().getWidth(), CaveQuest.getInstance().getHeight());
	}

}
