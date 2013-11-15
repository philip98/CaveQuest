package de.philip.net.common;

import java.io.DataInputStream;

public class PacketWorld extends Packet {
	
	/*
	 * Packet Behavior:
	 * Server sends width:Integer and height:Integer
	 * Server sends Integer, which contains the lines that follow
	 * Client reach that lines in format
	 * [x:int] [y:int] [width:int] [height:int] [image:Base64 String]
	 */
	
	public PacketWorld() {
		super();
	}
	
	public void process(DataInputStream data) {
		
	}
	
}