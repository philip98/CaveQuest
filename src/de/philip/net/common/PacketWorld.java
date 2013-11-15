package de.philip.net.common;

import java.io.DataInputStream;
import java.io.IOException;

import de.philip.entity.World;

public class PacketWorld extends Packet {
	
	/*
	 * Packet Behavior:
	 * Server sends width:Integer and height:Integer
	 * Server sends Integer, which contains the lines that follow
	 * Client reach that lines in format
	 * [id:int] [x:int] [y:int] [width:int] [height:int] [solid:boolean] [image:Base64 String]
	 */
	
	public PacketWorld() {
		super();
	}
	
	public void process(DataInputStream data) throws IOException {
		World world = new World();
		int w = data.readInt();
		int h = data.readInt();
		
		
		
	}
	
}