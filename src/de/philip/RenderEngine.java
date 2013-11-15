package de.philip;

import java.awt.Graphics;

public abstract class RenderEngine {
	private Graphics g;
	
	public RenderEngine(Graphics g) {
		this.g = g;
	}

	public Graphics getGraphics() {
		return g;
	}

	public void setGraphics(Graphics g) {
		this.g = g;
	}
	
	public void render() {
		
	}
}
