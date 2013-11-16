package de.philip.net.common;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashSet;

import de.philip.CaveQuest;
import de.philip.entity.World;
import de.philip.entity.WorldObject;
import de.philip.util.Logger;
import de.philip.util.Util;

public class PacketWorld extends Packet {

	/*
	 * Packet Behavior: Server sends width:Integer and height:Integer Server
	 * sends all World Objects with format [id:int] [x:int] [y:int] [width:int]
	 * [height:int] [solid:boolean] [image:Base64 String]
	 */

	public PacketWorld() {
		super();
	}

	public void send(World world, DataOutputStream data) throws IOException {
		Logger.log("Starting World send ..");
		data.flush();
		data.writeInt(world.getWidth());
		data.writeInt(world.getHeight());
		for(WorldObject obj : world.getObjects()) {
			data.writeInt(obj.getId());
			data.writeInt(obj.getX());
			data.writeInt(obj.getWidth());
			data.writeInt(obj.getHeight());
			data.writeBoolean(obj.isSolid());
			data.writeUTF(Util.encodeToString(obj.getImage()));
		}
		Logger.log("Sent world!");
	}

	public void process(DataInputStream data) throws IOException {
		Logger.log("Starting World Receive with " + data.available() + " Bytes Available..");
		int worldWidth = data.readInt();
		int worldHeight = data.readInt();
		Logger.log("Received width=" + worldWidth + " height=" + worldHeight);
		HashSet<WorldObject> objects = new HashSet<>();
		while (data.available() > 0) {
			int id = data.readInt();
			int x = data.readInt();
			int y = data.readInt();
			int width = data.readInt();
			int height = data.readInt();
			boolean solid = data.readBoolean();
			String base64 = data.readUTF();
			objects.add(new WorldObject(id, x, y, width, height, solid, base64));
			System.out.println("Read Object! Data Left: " + data.available() + " Bytes");
		}
		World world = new World(worldWidth, worldHeight, objects);
		CaveQuest.getInstance().setWorld(world);
		Logger.log("Received World! width=" + world.getWidth() + ", height=" + world.getHeight() + ", objects=" + objects.size());
	}

}