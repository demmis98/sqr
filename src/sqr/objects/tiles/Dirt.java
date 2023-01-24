package sqr.objects.tiles;

import sqr.All;

public class Dirt extends Tile{

	public Dirt(All all, int x, int y) {
		super(all, all.getAssets().dirt, x, y);
		solid = false;
	}

}