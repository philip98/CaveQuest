package de.philip.net.server;

import java.io.DataOutputStream;
import java.io.IOException;

import de.philip.net.common.PacketPlayer;
import de.philip.util.Logger;

public class ServerThreadSend extends Thread {
	public ServerThreadSend ()
	{
		
	}
	
	@Override
	public void run() {
		while (true) {
			for (int uid : Server.connections.keySet()) {
				ServerClient client = Server.connections.get(uid);
				try {
					DataOutputStream data = new DataOutputStream(client.getSocket().getOutputStream());
					PacketPlayer packet = new PacketPlayer();
					packet.send(uid, Server.players, data);
				} catch (IOException e) {
					Logger.err(e.getMessage());
				}
			}
			try {
				sleep(500);
			} catch(InterruptedException e) {
				Logger.err(e.getMessage());
			}
		}
	}
}
