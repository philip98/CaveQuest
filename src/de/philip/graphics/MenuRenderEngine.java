package de.philip.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import de.philip.CaveQuest;

public class MenuRenderEngine extends RenderEngine {

	public MenuRenderEngine() {
		super();
	}
	
	public void render(Graphics g) {
		int h = CaveQuest.getInstance().getHeight();
		int w = CaveQuest.width;
		
		g.setColor(Color.decode("0xCCCCFF"));
		g.fillRect(0, 0, w, h);
		g.setColor(Color.decode("0x8888FF"));
		g.fillRect(w / 8, 3 * h / 5, 3 * w / 4, h / 5);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Roman", Font.BOLD, 48));
		g.drawString("Quit", w / 2 - 50, 7 * h / 10 + 10);
	}
}
