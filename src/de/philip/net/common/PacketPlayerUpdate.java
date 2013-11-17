package de.philip.net.common;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import de.philip.CaveQuest;
import de.philip.entity.Player;

public class PacketPlayerUpdate extends Packet {
	public PacketPlayerUpdate() {
		super();
	}
	
	public void send(int id, Player p, DataOutputStream data) throws IOException {
		data.writeByte(0x04);
		data.writeInt(id);
		data.writeInt(p.getX());
		data.writeInt(p.getY());
		data.writeInt(p.getDir());
		
		data.flush();
	}
	
	public void receive(DataInputStream data) throws IOException {
		int id = data.readInt();
		Player p;
		
		p = CaveQuest.getInstance().pl.get(id);
		p.setX(data.readInt());
		p.setY(data.readInt());
		p.setDir(data.readInt());
		CaveQuest.getInstance().pl.put(id, p);
	}
}
