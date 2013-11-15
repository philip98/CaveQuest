package de.philip.net.client;

import de.philip.CaveQuest;

public class ThreadReceive extends Thread {
	
	public ThreadReceive() {
		
	}
	
	@Override
	public void run() {
		while(CaveQuest.getInstance().isRunning()) {
			
		}
	}
	
}