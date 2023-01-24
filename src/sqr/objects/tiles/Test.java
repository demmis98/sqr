package sqr.objects.tiles;

import sqr.All;

public class Test extends Tile{
	
	public Test(All all, int x, int y, boolean solid) {
		super(all, x, y);
		this.solid = solid;
		stepable = true;
		collidable = true;
		defTimer = 1;
		if(solid) {
			texture = all.getAssets().test0;
			collisionTex = all.getAssets().test1;
			stepTex = all.getAssets().test2;
		}
		else {
			texture = all.getAssets().test4;
			collisionTex = all.getAssets().test5;
			stepTex = all.getAssets().test7;
		}
	}
}