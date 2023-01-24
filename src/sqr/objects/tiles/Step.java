package sqr.objects.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import sqr.All;

public class Step extends Tile {
	protected BufferedImage colideTex;

	public Step(All all, BufferedImage texture, int x, int y) {
		this(all, texture, all.getAssets().defTileCol, x, y);
		solid = false;
		dynamic = true;
	}
	public Step(All all, BufferedImage texture, BufferedImage colideTex, int x, int y) {
		super(all, texture, x, y);
		this.colideTex = colideTex;
	}
	public Step(All all, int x, int y) {
		this(all, all.getAssets().defTile, x, y);
	}

	public void step(float speed) {
		super.step();
		timer = (byte) (speed * 5);
	}
	public void step() {
		step(3);
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
