package sqr.objects;

import java.awt.image.BufferedImage;

import sqr.All;

public class Tile extends Thing {
	protected boolean solid = true;

	public Tile(All all, BufferedImage texture, int x, int y) {
		super(all, texture, x, y);
	}

	public Tile(All all, int x, int y) {
		this(all, all.getAssets().defTile, x, y);
	}
}