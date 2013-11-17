package de.philip.net.common;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import de.philip.CaveQuest;
import de.philip.entity.Player;
import de.philip.entity.PlayerList;

public class PacketPlayer extends Packet {
	public PacketPlayer() {
		super();
	}
	
	public void send(int id, PlayerList pl, DataOutputStream stream) throws IOException {
		stream.writeByte(0x03);
		stream.writeInt(id);
		stream.writeInt(pl.getList().size());
		for (int i = 0; i < pl.getList().size(); ++i) {
			stream.writeInt(pl.get(i).getX());
			stream.writeInt(pl.get(i).getY());
			stream.writeInt(pl.get(i).getDir());
		}
		stream.flush();
	}
	
	public void receive(DataInputStream stream) throws IOException{
		PlayerList pl = CaveQuest.getInstance().pl;
		int i;
		Player p;
		
		CaveQuest.getInstance().setPlayerID(stream.readInt());
		i = stream.readInt();
		
		for (int a = 0; a < i; ++a) {
			p = new Player(stream.readInt(), stream.readInt());
			p.setDir(stream.readInt());
			pl.put(a,  p);
		}
	}
}
