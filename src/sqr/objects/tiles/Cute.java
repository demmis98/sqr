package sqr.objects.tiles;

import sqr.All;

public class Cute extends Step{

	public Cute(All all, int x, int y) {
		super(all, x, y);
		texture = all.getAssets().cuteTile;
		stepTex = all.getAssets().cuteTileCol;
	}

}