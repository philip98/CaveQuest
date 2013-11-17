package de.philip.net.server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import de.philip.entity.Player;
import de.philip.net.common.PacketWorld;
import de.philip.util.Logger;
import de.philip.util.UniqueIdentifier;

public class ServerThreadListen extends Thread {

	public ServerThreadListen() {

	}

	@Override
	public void run() {
		while (true) {
			try {
				Logger.log("Waiting for Connection ..");
				Socket socket = Server.serverSocket.accept();
				int uid = UniqueIdentifier.getIdentifier();
				Server.connections.put(uid, new ServerClient(socket, "STD-NAME"));
				Logger.log("Got Connection (uid=" + uid + ")! Now sending World!");
				Server.players.put(uid, new Player(50, 50));
				
				try {
					sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				// Sent World Packet
				PacketWorld packet = new PacketWorld();
				packet.send(Server.world, uid, new DataOutputStream(socket.getOutputStream()));

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}