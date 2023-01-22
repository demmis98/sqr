package sqr.objects;

import java.awt.image.BufferedImage;

import sqr.All;

public abstract class Alive extends Thing{
	private int health;

	public Alive(All all, BufferedImage texture, int x, int y, int health) {
		super(all, texture, x, y);
		this.health = health;
	}

	public Alive(All all, BufferedImage texture, int x, int y) {
		this(all, texture, x, y, 1);
	}
	
	public Alive(All all, int x, int y, int health) {
		this(all, all.getAssets().defAlive, x, y, health);
	}
	
	public Alive(All all, int x, int y) {
		this(all, all.getAssets().defAlive, x, y);
	}
	public void render() {
		
	}
}
