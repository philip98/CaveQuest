package de.philip.net.server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import de.philip.net.common.PacketWorld;
import de.philip.util.Logger;

public class ServerThreadListen extends Thread {

	public ServerThreadListen() {

	}

	@Override
	public void run() {
		while (true) {
			try {
				Logger.log("Waiting for Connection ..");
				Socket socket = Server.serverSocket.accept();
				Server.connections.add(socket);
				Logger.log("Got Connection! Now sending World!");
				
				try {
					sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				// Sent World Packet
				PacketWorld packet = new PacketWorld();
				packet.send(Server.world, new DataOutputStream(socket.getOutputStream()));

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}