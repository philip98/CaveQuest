package de.philip.net.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class Packet {

	public Packet() {
	}

	public void receive(InputStream data) throws IOException {

	}

	public void send(OutputStream data) {

	}

	public static Packet getPacket(byte prefix) {
		switch (prefix) {
			case 0x00:
				return null;
			case 0x01:
				return new PacketWorld();
			// Add more here
		}
		return null;
	}

}