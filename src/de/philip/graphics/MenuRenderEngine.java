package de.philip.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import de.philip.CaveQuest;

public class MenuRenderEngine extends RenderEngine {

	int selection = 0;
	int minSelection = 0;
	int maxSelection = 3;
	int screen = 0;
	String ip = "";
	String name = "";

	public MenuRenderEngine() {
		super();
	}

	public void render(Graphics g) {
		int w = CaveQuest.getInstance().getWidth();
		int h = CaveQuest.getInstance().getHeight();
		GradientPaint gp = new GradientPaint(w/2, 0, new Color(0x8B5A2B), w/2, h, new Color(0x404040));
		((Graphics2D)g).setPaint(gp);
		((Graphics2D)g).fillRect(0, 0, w, h);
		if (screen == 0) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("Serif", Font.BOLD, 128));
			g.drawString("CaveQuest", 50, 120);
			g.setColor(Color.GRAY);
			g.setFont(new Font("Serif", Font.BOLD, 28));
			g.drawString("No, there are no Caves!", 60,150);
			g.setFont(new Font("Serif", Font.BOLD, 36));
			g.setColor(Color.WHITE);
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
			g.drawString("Enter Name: " + name, w - 500, h - 100);
		} else if (screen == 4) {
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
				screen = 4;
			} else if (screen == 4) {
				CaveQuest.getInstance().connect(ip, name);
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
			name = name.replaceAll("\n", "").trim();
			if (key == KeyEvent.VK_BACK_SPACE) {
				if (name.length() > 0) {
					name = name.substring(0, name.length() - 1);
				}
			} else
				name += e.getKeyChar();
		}
		if (screen == 4) {
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
