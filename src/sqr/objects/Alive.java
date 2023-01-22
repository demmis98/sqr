package sqr.objects;

import java.awt.image.BufferedImage;

import sqr.All;

public abstract class Alive extends Thing{
	private int health;
	protected float root2=(float) 0.7;
	protected float speedY;
	protected float speedX;

	public Alive(All all, BufferedImage texture, int x, int y, int health) {
		super(all, texture, x, y);
		this.health = health;
		speedY = 0; 
		speedX = 0;
	}

	protected void moveX() {
		x += speedX;
	}
	protected void moveY() {
		y += speedY;
	}
	public void moveX(float v) {
		x += v;
	}
	public void moveY(float h) {
		y += h;
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
	public void tick() {
		moveX();
		moveY();
	}
}
