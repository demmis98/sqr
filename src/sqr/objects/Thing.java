package sqr.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import sqr.All;

public abstract class Thing {
	protected BufferedImage texture;
	int width, height;
	protected Rectangle hitbox;
	protected float x, y;
	All all;
	public Thing(All all, BufferedImage texture, int x, int y) {
		this.all = all;
		this.x = x;
		this.y = y;
		if(texture != null) {
			width = texture.getWidth();
			height = texture.getHeight();
		}
		else {
			width=0;
			height=0;
		}
		hitbox = new Rectangle(x, y, width, height);
	}
	public Thing(All all, int x, int y) {
		this(all, all.getAssets().defThing, x, y);
	}
	public void tick() {
		hitbox.x = (int) x;
		hitbox.y = (int) y;
	};
	
	
	public void render() {
		render(all.getGraphics());
	}
	public void render(Graphics g) {
		if(g != null) {
			if(texture != null) {
				g.drawImage(texture, (int) x, (int) y, width, height, null);
			}
			else {
				System.out.println("Texture is NULL");
			}
		}
		else {
			System.out.println("Graphics are NULL");
		}
	}
	
	public float getX() {
		return hitbox.x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return hitbox.y;
	}
	public void setY(float y) {
		this.y = y;
	};
	public int getWidth() {
		return hitbox.width;
	}
	public int getHeight() {
		return hitbox.height;
	}
	
}