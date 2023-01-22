package sqr.objects;

import java.awt.image.BufferedImage;

public abstract class Alive extends Thing{
	private int health;

	public Alive(BufferedImage texture, int x, int y, int health) {
		super(texture, x, y);
		this.health = health;
	}

	public Alive(BufferedImage texture, int x, int y) {
		super(texture, x, y);
		this.health = 1;
	}
}
