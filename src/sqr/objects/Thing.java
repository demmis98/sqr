package sqr.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import sqr.All;

public abstract class Thing {
	protected BufferedImage texture, wallTex;
	int width, height;
	protected Rectangle hitbox;
	protected byte wallHeight = 0, wallOff = 0;
	protected float x, y, offX, offY;
	protected All all;
	public Thing(All all, BufferedImage texture, int x, int y) {
		this.all = all;
		this.x = x;
		this.y = y;
		if(texture != null) {
			this.texture = texture;
			width = texture.getWidth();
			height = texture.getHeight();
		}
		else {
			width=0;
			height=0;
		}
		hitbox = new Rectangle(x, y, width, height);
		offX = 0;
		offY = 0;
	}
	public Thing(All all, int x, int y) {
		this(all, all.getAssets().defThing, x, y);
	}
	public void tick() {
		hitbox.x = (int) (x + offX);
		hitbox.y = (int) (y + offY);
	};
	
	public void render() {
		render(all.getGraphics());
	}
	public void render(Graphics g) {
		render(g, texture);
	}
	public void render(Graphics g, BufferedImage texture) {
		if(g != null) {
			if(texture != null) {
				if(wallTex != null) {
					if(wallHeight > 0) {
						g.drawImage(wallTex, (int) x, (int) y + wallOff, width, wallHeight, null);
						g.drawImage(texture, (int) x, (int) y - wallHeight, width, height, null);
					}
					else if(wallHeight < 0) {
						g.drawImage(texture, (int) x, (int) y - wallHeight, width, height, null);
						g.drawImage(wallTex, (int) x, (int) y , width, wallHeight*-1, null);
					}
					else {
						g.drawImage(texture, (int) x, (int) y, width, height, null);
					}
				}
				else {
					g.drawImage(texture, (int) x, (int) y, width, height, null);
				}
			}
			else {
				System.out.println("Texture is NULL");
			}
		}
		else {
			System.out.println("Graphics are NULL");
		}
	}
	public void renderTop() {
		
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
	
	public float getXTiles() {
		return hitbox.x / all.getAssets().getWidth();
	}
	public float getYTiles() {
		return hitbox.y / all.getAssets().getHeight();
	}
}