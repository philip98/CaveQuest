package de.philip.net.common;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import de.philip.net.server.Server;
import de.philip.util.Logger;

public class PacketName extends Packet {

	public PacketName() {
		super();
	}

	public void send(DataOutputStream data, String name, int uid) throws IOException {
		Logger.log("Starting Name send ..");
		data.writeByte(0x02);
		data.writeInt(uid);
		data.writeUTF(name);
		Logger.log("Sent name!");
	}

	public void receive(DataInputStream data, int uid) throws IOException {
		Logger.log("Starting Name Receive ..");
		String name = data.readUTF();
		int id = data.readInt();
		if (id == uid)
			Logger.log("Client got correct ID");
		Server.connections.get(uid).setName(name);
		Logger.log("Received Name '" + name + "' !");
	}

}