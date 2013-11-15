package de.philip;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class CaveQuest extends Canvas implements Runnable {

	private static CaveQuest instance;

	private static final long serialVersionUID = 1L;

	public static String title = "CaveQuest";
	public static int width = 900;
	public static int height = 600;

	private Thread thread;
	private JFrame frame;
	private boolean running = false;

	private GameMode gameMode;
	private MenuRenderEngine menuRenderer;
	private GameRenderEngine gameRenderer;

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
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				update();
				updates++;
				delta--;
			}
			render();
			frames++;
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frame.setTitle(title + " | " + updates + " ups, " + frames + " fps");
				updates = 0;
				frames = 0;
			}
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

		g.dispose();
		bs.show();

	}

	public static void main(String[] args) {

		boolean flagServer = false;
		int port = Server.DEFAULT_PORT;

		for (int i = 0; i < args.length; i++) {
			if (args[i].equalsIgnoreCase("--server")) {
				flagServer = true;
				if (args[i + 1] != null) {
					port = Integer.parseInt(args[i + 1]);
				}
			}
		}

		if (flagServer) {
			Server server = new Server(port);
			server.start();
			return;
		}

		instance = new CaveQuest();

		instance.frame.setResizable(false);
		instance.frame.setTitle(title);
		instance.frame.add(instance);
		instance.frame.pack();
		instance.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		instance.frame.setLocationRelativeTo(null);
		instance.frame.setVisible(true);

		instance.start();
	}

}