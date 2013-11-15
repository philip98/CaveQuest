package de.philip;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class CaveQuest extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 1L;

	public static String title = "CaveQuest";
	public static int width = 900;
	public static int height = 600;

	private Thread thread;
	private JFrame frame;
	private boolean running = false;
	
	public CaveQuest() {
		Dimension size = new Dimension(width, height);
		setPreferredSize(size);

		frame = new JFrame();
	}
	
	public synchronized void start() {
		running = true;
		thread = new Thread(this, "CaveQuest");
		thread.start();
	}

	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		while (running) {
			update();
			render();
		}
	}

	public void update() {
		
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		// TODO: RenderEngine Class to render
		g.dispose();
		bs.show();
		
	}

	public static void main(String[] args) {
		CaveQuest game = new CaveQuest();
		game.frame.setResizable(false);
		game.frame.setTitle(title);
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);

		game.start();
	}
	
}