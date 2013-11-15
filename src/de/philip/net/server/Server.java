package de.philip.net.server;

import java.net.InetAddress;
import java.net.Socket;

import de.philip.util.Logger;

public class Server {

	Socket sock;

	public static final int DEFAULT_PORT = 33576;

	private int port;

	public Server(int port) {
		this.port = port;
		try {
			sock = new Socket(InetAddress.getLocalHost(), port);
		} catch (Exception e) {
			Logger.err(e.getMessage());
		}
	}

	public void start() {
		Logger.log("Starting Server on port " + port + " ...");
		
		
	}

	public int getPort() {
		return port;
	}

}
