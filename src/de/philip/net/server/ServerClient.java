package de.philip.net.server;

import java.net.Socket;

public class ServerClient {
	
	private Socket socket;
	private String name;
	
	public ServerClient(Socket socket, String name) {
		this.socket = socket;
		this.name = name;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}