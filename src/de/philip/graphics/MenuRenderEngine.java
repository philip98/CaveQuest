package de.philip.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import de.philip.CaveQuest;

public class MenuRenderEngine extends RenderEngine {

	public MenuRenderEngine(Graphics g) {
		super(g);
	}
	
	public void render() {
		int h = CaveQuest.height;
		int w = CaveQuest.width;
		Graphics g = super.getGraphics();
		
		g.setColor(Color.decode("0xCCCCFF"));
		g.fillRect(0, 0, w, h);
		g.setColor(Color.decode("0x8888FF"));
		g.fillRect(100, 300, 700, 100);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Roman", Font.BOLD, 48));
		g.drawString("Quit", 180, 362);
	}

}
