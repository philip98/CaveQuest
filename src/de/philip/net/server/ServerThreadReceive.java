package de.philip.net.server;

import java.io.DataInputStream;
import java.io.IOException;

import de.philip.net.common.Packet;
import de.philip.net.common.PacketName;
import de.philip.net.common.PacketPlayerUpdate;
import de.philip.util.Logger;

public class ServerThreadReceive extends Thread {

	public ServerThreadReceive() {

	}

	@Override
	public void run() {
		while (true) {
			for (int uid : Server.connections.keySet()) {
				ServerClient client = Server.connections.get(uid);
				try {
					if (client.getSocket().getInputStream().available() > 0) {
						DataInputStream data = new DataInputStream(client.getSocket().getInputStream());
						byte prefix = data.readByte();
						Packet p = Packet.getPacket(prefix);
						Logger.log("Received Packet prefix=" + prefix);
						if (p instanceof PacketName) {
							((PacketName) p).receive(data, uid);
						}
						if (p instanceof PacketPlayerUpdate) {
							((PacketPlayerUpdate) p).receive(data);
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
