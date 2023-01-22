package sqr.objects;

import java.awt.Graphics;

import sqr.All;

public class Eyes extends Alive {
	public boolean blink;

	public Eyes(All all, int x, int y) {
		super(all, x, y);
		texture = all.getAssets().player[1];
		blink = false;
	}
	
	public void tick() {
		super.tick();
		if(blink) {
			texture = all.getAssets().player[3];
		}
		else {
			texture = all.getAssets().player[1];
		}
	}
}