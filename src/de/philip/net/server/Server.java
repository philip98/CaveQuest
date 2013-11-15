package de.philip.net.server;

import java.net.ServerSocket;
import java.net.Socket;

import de.philip.util.Logger;

public class Server {

	ServerSocket sock;
	Socket cliSock;

	public static final int DEFAULT_PORT = 33576;

	private int port;

	public Server(int port) {
		this.port = port;
		try {
			sock = new ServerSocket(port);
		} catch (Exception e) {
			Logger.err(e.getMessage());
		}
	}

	public void start() {
		Logger.log("Starting Server on port " + port + " ...");
		Logger.log("Listening on port " + port + "...");
		cliSock = sock.accept();
		
	}

	public int getPort() {
		return port;
	}

}
