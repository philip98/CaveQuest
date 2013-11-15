package de.philip.net.server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import de.philip.net.common.PacketWorld;

public class ServerThreadListen extends Thread {

	public ServerThreadListen() {

	}

	@Override
	public void run() {
		while (true) {
			try {
				Socket socket = Server.serverSocket.accept();
				Server.connections.add(socket);

				// Sent World Packet
				PacketWorld packet = new PacketWorld();
				packet.send(Server.world, new DataOutputStream(socket.getOutputStream()));

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}