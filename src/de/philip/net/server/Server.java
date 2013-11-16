package de.philip.net.server;

import java.io.File;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;

import de.philip.entity.World;
import de.philip.util.Logger;

public class Server {

	public static final int DEFAULT_PORT = 33576;

	public static ServerThreadListen threadListen;
	public static ServerSocket serverSocket;
	public static HashSet<Socket> connections = new HashSet<Socket>();
	public static World world;

	public static int port = DEFAULT_PORT;

	public static void start() {
		Logger.log("Loading World .."); // world.txt
		try {
			world = World.load(new File("res/world.txt"));
			Logger.log("Starting Server on port " + port + " ...");
			serverSocket = new ServerSocket(port);
		} catch (Exception e) {
			e.printStackTrace();
		}
		threadListen = new ServerThreadListen();
		threadListen.start();
		Logger.log("Server is ready!");
	}

	public static void setPort(int port) {
		Server.port = port;
	}

}
