package de.philip;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.net.Socket;

import javax.swing.JFrame;

import de.philip.entity.Player;
import de.philip.entity.World;
import de.philip.graphics.GameMode;
import de.philip.graphics.IngameRenderEngine;
import de.philip.graphics.MenuRenderEngine;
import de.philip.input.InputListener;
import de.philip.net.client.ClientThreadReceive;
import de.philip.net.server.Server;
import de.philip.util.Logger;

public class CaveQuest extends Canvas implements Runnable {

	private static CaveQuest instance;

	private static final long serialVersionUID = 1L;

	public static String title = "CaveQuest";
	public static double version = 0.1;
	public static int width = 900;
	public static int height = 600;

	private Thread thread;
	private JFrame frame;
	private boolean running = false;
	
	private InputListener inputListener;
	private GameMode gameMode;
	private MenuRenderEngine menuRenderer;
	private IngameRenderEngine gameRenderer;
	private World world;
	private Player player;
	private ClientThreadReceive threadReceive;
	private Socket serverConnected;
	private String name;

	public CaveQuest() {
		Dimension size = new Dimension(width, height);
		setPreferredSize(size);

		frame = new JFrame();
		inputListener = new InputListener();
		gameMode = GameMode.MENU;
		menuRenderer = new MenuRenderEngine();
		gameRenderer = new IngameRenderEngine();
		threadReceive = new ClientThreadReceive();
		setPlayer(new Player(50,50));
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
				frame.setTitle(title + " v" + version + " | " + updates + " ups, " + frames + " fps");
				updates = 0;
				frames = 0;
			}
		}
	}

	public void update() {
		player.update();
	}

	/**
	 * Renders the game according to the game mode.
	 */
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		switch (gameMode) {
			case MENU:
				menuRenderer.render(g);
				break;
			case INGAME:
				gameRenderer.render(g);
				break;
		}

		g.dispose();
		bs.show();
	}

	public static CaveQuest getInstance() {
		return instance;
	}

	public static void main(String[] args) {

		Logger.log("Starting ..");

		boolean flagServer = false;
		int port = Server.DEFAULT_PORT;

		for (int i = 0; i < args.length; i++) {
			if (args[i].equalsIgnoreCase("--server")) {
				flagServer = true;
				if (i + 1 > args.length) {
					port = Integer.parseInt(args[i + 1]);
				}
			}
		}

		if (flagServer) {
			Server.setPort(port);
			Server.start();
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
		instance.addKeyListener(instance.inputListener);
		instance.addMouseListener(instance.inputListener);

		Logger.log("Started!");

		instance.start();
	}

	public void connect(String ip, String name) {
		Logger.log("Connecting to " + ip + " as " + name);
		this.name = name;
		try {
			serverConnected = new Socket(ip, Server.DEFAULT_PORT);
			
			Logger.log("Connected!");

			threadReceive.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public GameMode getGameMode() {
		return gameMode;
	}

	public void setGameMode(GameMode gameMode) {
		this.gameMode = gameMode;
	}

	public MenuRenderEngine getMenuRenderer() {
		return menuRenderer;
	}

	public void setMenuRenderer(MenuRenderEngine menuRenderer) {
		this.menuRenderer = menuRenderer;
	}

	public IngameRenderEngine getGameRenderer() {
		return gameRenderer;
	}

	public void setGameRenderer(IngameRenderEngine gameRenderer) {
		this.gameRenderer = gameRenderer;
	}

	public InputListener getInputListener() {
		return inputListener;
	}

	public void setInputListener(InputListener inputListener) {
		this.inputListener = inputListener;
	}

	public Socket getServerConnected() {
		return serverConnected;
	}

	public void setServerConnected(Socket serverConnected) {
		this.serverConnected = serverConnected;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
}
