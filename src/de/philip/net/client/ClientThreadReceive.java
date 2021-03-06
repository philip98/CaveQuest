package de.philip.net.client;

import java.io.DataInputStream;
import java.io.IOException;

import de.philip.CaveQuest;
import de.philip.net.common.Packet;
import de.philip.net.common.PacketPlayer;
import de.philip.net.common.PacketWorld;

public class ClientThreadReceive extends Thread {

	public ClientThreadReceive() {
		
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				if(CaveQuest.getInstance().getServerConnected() == null)
					continue;
				DataInputStream data = new DataInputStream(CaveQuest.getInstance().getServerConnected().getInputStream());
				if (data.available() > 0) {
					byte prefix = data.readByte();
					Packet p = Packet.getPacket(prefix);
					if (p instanceof PacketWorld) {
						((PacketWorld) p).receive(data);
						CaveQuest.getInstance().threadSend.start();
					} else if (p instanceof PacketPlayer) {
						((PacketPlayer) p).receive(data);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}