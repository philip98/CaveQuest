package de.philip.net.common;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashSet;

import de.philip.CaveQuest;
import de.philip.entity.Sprite;
import de.philip.entity.SpriteSheet;
import de.philip.entity.World;
import de.philip.entity.WorldObject;
import de.philip.util.Logger;

public class PacketWorld extends Packet {

	public PacketWorld() {
		super();
	}

	public void send(World world, DataOutputStream data) throws IOException {
		Logger.log("Starting World send ..");
		data.writeByte(0x01);
		data.writeInt(world.getWidth());
		data.writeInt(world.getHeight());
		data.writeInt(world.getObjects().size());
		for (WorldObject obj : world.getObjects()) {
			data.writeInt(obj.getId());
			data.writeInt(obj.getX());
			data.writeInt(obj.getY());
			data.writeInt(obj.getWidth());
			data.writeInt(obj.getHeight());
			data.writeBoolean(obj.isSolid());
			data.writeInt(obj.getSprite().getId());
		}
		for (int y = 0; y < world.getHeight(); y++) {
			for (int x = 0; x < world.getHeight(); x++) {
				data.writeInt(x);
				data.writeInt(y);
				data.writeInt(world.getTiles()[x][y]);
			}
		}
		data.flush();
		Logger.log("Sent world! data size=" + data.size() + " Bytes");
	}

	public void receive(DataInputStream data) throws IOException {
		Logger.log("Starting World Receive with " + data.available() + " Bytes Available..");
		int worldWidth = data.readInt();
		int worldHeight = data.readInt();
		Logger.log("Received width=" + worldWidth + " height=" + worldHeight);
		int numObjects = data.readInt();
		HashSet<WorldObject> objects = new HashSet<>();
		for (int i = 0; i < numObjects; i++) {
			int id = data.readInt();
			int x = data.readInt();
			int y = data.readInt();
			int width = data.readInt();
			int height = data.readInt();
			boolean solid = data.readBoolean();
			Sprite sprite = SpriteSheet.sprites[data.readInt()];
			objects.add(new WorldObject(id, x, y, width, height, solid, sprite));
			Logger.log("Read Object! Data Left: " + data.available() + " Bytes");
		}
		Logger.log("Now Receiving Tiles!");
		int[][] tiles = new int[worldWidth][worldHeight];
		while (data.available() > 0) {
			int x = data.readInt();
			int y = data.readInt();
			int col = data.readInt();
			tiles[x][y] = col;
		}
		World world = new World(worldWidth, worldHeight, objects, tiles);
		CaveQuest.getInstance().setWorld(world);
		Logger.log("Received World! width=" + world.getWidth() + ", height=" + world.getHeight() + ", objects=" + objects.size() + " tiles="
				+ tiles.length);
	}

}