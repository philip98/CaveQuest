package de.philip;

public class Server {
	
	public static final int DEFAULT_PORT = 33576;
	
	private int port;
	
	public Server(int port) {
		this.port = port;
	}
	
	public void start() {
		Logger.log("Starting Server on port " + port + " ...");
	}

	public int getPort() {
		return port;
	}

}
