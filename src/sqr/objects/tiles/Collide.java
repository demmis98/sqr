package sqr.objects.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import sqr.All;

public class Collide extends Tile {

	public Collide(All all, BufferedImage texture, BufferedImage colideTex, int x, int y) {
		super(all, texture, x, y);
		defTimer = 3;
		collidable = true;
		collisionWallTex = all.getAssets().defTileColWall;
	}
	public Collide(All all, BufferedImage texture, int x, int y) {
		this(all, texture, all.getAssets().defTileCol, x, y);
	}
	public Collide(All all, int x, int y) {
		this(all, all.getAssets().defTile, x, y);
	}

}
