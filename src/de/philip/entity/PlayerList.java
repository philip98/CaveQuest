package de.philip.entity;

import java.util.HashMap;

public class PlayerList {
	private HashMap<Integer, Player> players;
	
	public PlayerList() {
		players = new HashMap<>();
	}
	
	public void put(int i, Player p) {
		players.put(i, p);
	}
	
	public Player get(int i) {
		return players.get(i);
	}
	
	public HashMap<Integer, Player> getList() {
		return players;
	}
}
