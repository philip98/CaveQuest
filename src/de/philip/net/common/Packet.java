package de.philip.net.common;

import java.io.DataInputStream;
import java.io.IOException;

public abstract class Packet {
	
	public Packet() {
	}
	
	public void process(DataInputStream data) throws IOException {
		
	}
	
	public static Packet getPacket(byte prefix) {
		switch(prefix) {
			case 0x00:
				return null;
			case 0x01:
				return new PacketWorld();
			// Add more here
		}
		return null;
	}
	
}