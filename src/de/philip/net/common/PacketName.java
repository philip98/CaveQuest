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

	public void send(DataOutputStream data, String name) throws IOException {
		Logger.log("Starting Name send ..");
		data.writeByte(0x02);
		data.writeUTF(name);
		Logger.log("Sent name!");
	}

	public void receive(DataInputStream data, int uid) throws IOException {
		Logger.log("Starting Name Receive ..");
		String name = data.readUTF();
		Server.connections.get(uid).setName(name);
		Logger.log("Received Name '" + name + "' !");
	}

}