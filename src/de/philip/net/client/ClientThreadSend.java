package de.philip.net.client;

import java.io.DataOutputStream;
import java.io.IOException;

import de.philip.CaveQuest;
import de.philip.net.common.PacketPlayerUpdate;
import de.philip.util.Logger;

public class ClientThreadSend extends Thread {
	public ClientThreadSend() {
		
	}
	
	@Override
	public void run() {
		while (true) {
			if (CaveQuest.getInstance().getServerConnected() == null || CaveQuest.getInstance().pl == null 
					||CaveQuest.getInstance().pl.getList().size() == 0) {
				continue;
			}
			try {
				DataOutputStream data = new DataOutputStream(CaveQuest.getInstance().getServerConnected().getOutputStream());
				PacketPlayerUpdate p = new PacketPlayerUpdate();
				p.send(CaveQuest.getInstance().getPlayerID(), CaveQuest.getInstance().pl.get(CaveQuest.getInstance().getPlayerID()), data);
			} catch (IOException e) {
				Logger.err(e.getMessage());
			}
		}
	}
}
