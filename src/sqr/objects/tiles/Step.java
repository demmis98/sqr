package sqr.objects.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import sqr.All;

public class Step extends Tile {

	public Step(All all, BufferedImage texture, int x, int y) {
		this(all, texture, all.getAssets().defTileCol, x, y);
		defTimer = 3;
		solid = false;
		collidable = false;
		stepable = true;
	}
	public Step(All all, BufferedImage texture, BufferedImage colideTex, int x, int y) {
		super(all, texture, x, y);
	}
	public Step(All all, int x, int y) {
		this(all, all.getAssets().defTile, x, y);
	}
}
