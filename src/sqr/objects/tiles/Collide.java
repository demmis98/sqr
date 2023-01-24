package sqr.objects.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import sqr.All;

public class Collide extends Tile {
	protected BufferedImage colideTex;

	public Collide(All all, BufferedImage texture, int x, int y) {
		this(all, texture, all.getAssets().defTileCol, x, y);
	}
	public Collide(All all, BufferedImage texture, BufferedImage colideTex, int x, int y) {
		super(all, texture, x, y);
		this.colideTex = colideTex;
	}
	public Collide(All all, int x, int y) {
		this(all, all.getAssets().defTile, x, y);
	}

	public void collide(float speed) {
		super.collide();
		timer = (byte) (speed * 10);
	}
	public void collide() {
		collide(3);
	}
	
	public void render(Graphics g) {
		if(timer == 0) {
			super.render(g);
		}
		else {
			super.render(g, colideTex);
		}
	}
}
