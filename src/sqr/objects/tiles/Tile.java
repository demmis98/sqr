package sqr.objects.tiles;

import java.awt.image.BufferedImage;

import sqr.All;
import sqr.objects.Thing;

public class Tile extends Thing {
	protected boolean solid = true;
	protected boolean dynamic = false;
	protected byte timer = 0;

	public Tile(All all, BufferedImage texture, int x, int y) {
		super(all, texture, x, y);
	}

	public Tile(All all, int x, int y) {
		super(all, all.getAssets().defTile, x, y);
		texture = all.getAssets().defTile;
	}

	public void tick() {
		super.tick();
		if(timer != 0) {
			if(timer > 0) {
				timer--;
			}
			else {
				timer++;
			}
		}
	}
	
	public void collide() {}
	public void step() {}
	
	public boolean isSolid() {
		return solid;
	}

	public boolean isDynamic() {
		return dynamic;
	}
}