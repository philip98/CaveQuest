package de.philip.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import de.philip.CaveQuest;

public class MenuRenderEngine extends RenderEngine {

	int selection = 0;
	int minSelection = 0;
	int maxSelection = 3;
	int screen = 0;
	String ip = "";

	public MenuRenderEngine() {
		super();
	}

	public void render(Graphics g) {
		int w = CaveQuest.getInstance().getWidth();
		int h = CaveQuest.getInstance().getHeight();
		if (screen == 0) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("Serif", Font.BOLD, 128));
			g.drawString("CaveQuest", 50, 120);
			g.setFont(new Font("Serif", Font.BOLD, 36));
			g.drawString(selection == 0 ? "> START" : "  START", w - 200, h - 250);
			g.drawString(selection == 1 ? "> HELP" : "  HELP", w - 200, h - 200);
			g.drawString(selection == 2 ? "> ABOUT" : "  ABOUT", w - 200, h - 150);
			g.drawString(selection == 3 ? "> EXIT" : "  EXIT", w - 200, h - 100);
		} else if (screen == 1) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("Serif", Font.BOLD, 48));
			g.drawString("Not yet implemented!", 10, 10);
		} else if (screen == 2) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("Serif", Font.BOLD, 64));
			g.drawString("Not yet implemented!", 50, 120);
		} else if (screen == 3) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("Serif", Font.BOLD, 128));
			g.drawString("CaveQuest", 50, 120);
			g.setFont(new Font("Serif", Font.BOLD, 36));
			g.drawString("Enter IP: " + ip, w - 500, h - 100);
		}
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_ENTER) {
			if (screen == 0) {
				maxSelection = 3;
				if (selection == 0) {
					screen = 3;
					selection = 0;
					maxSelection = 1;
				} else if (selection == 1) {
					screen = 1;
				} else if (selection == 2) {
					screen = 2;
				} else if (selection == 3) {
					CaveQuest.getInstance().stop();
				}
			} else if (screen == 3) {
				CaveQuest.getInstance().connect(ip);
			}
		}
		if (key == KeyEvent.VK_ESCAPE) {
			if (screen == 0) {
				CaveQuest.getInstance().stop();
			} else {
				screen = 0;
				maxSelection = 3;
			}
		}
		if (screen == 3) {
			ip = ip.replaceAll("\n", "").trim();
			if (key == KeyEvent.VK_BACK_SPACE) {
				if (ip.length() > 0) {
					ip = ip.substring(0, ip.length() - 1);
				}
			} else
				ip += e.getKeyChar();
		}

		if (key == KeyEvent.VK_DOWN) {
			if (selection < maxSelection) {
				selection++;
			}
		}
		if (key == KeyEvent.VK_UP) {
			if (selection > minSelection) {
				selection--;
			}
		}
	}
}
