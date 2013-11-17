package de.philip.net.server;

import java.io.File;
import java.net.ServerSocket;
import java.util.HashMap;

import de.philip.entity.PlayerList;
import de.philip.entity.World;
import de.philip.util.Logger;

public class Server {

	public static final int DEFAULT_PORT = 33576;

	public static ServerThreadListen threadListen;
	public static ServerThreadReceive threadReceive;
	public static ServerThreadSend threadSend;
	public static ServerSocket serverSocket;
	public static HashMap<Integer, ServerClient> connections = new HashMap<>();
	public static PlayerList players = new PlayerList();
	public static World world;

	public static int port = DEFAULT_PORT;

	public static void start() {
		Logger.log("Loading World .."); // world.txt and world.png
		try {
			world = World.load(new File("res/world.txt"), new File("res/world.png"));
			Logger.log("Starting Server on port " + port + " ...");
			serverSocket = new ServerSocket(port);
		} catch (Exception e) {
			e.printStackTrace();
		}
		threadListen = new ServerThreadListen();
		threadListen.start();
		threadReceive = new ServerThreadReceive();
		threadReceive.start();
		threadSend = new ServerThreadSend();
		threadSend.start();
		Logger.log("Server is ready!");
	}

	public static void setPort(int port) {
		Server.port = port;
	}

}
