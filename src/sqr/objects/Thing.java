package sqr.objects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import sqr.All;
import sqr.states.State;

public abstract class Thing {
	BufferedImage texture;
	int width, height;
	float x, y;
	All all;
	public Thing(All all, BufferedImage texture, int x, int y) {
		this.all = all;
		this.x = x;
		this.y = y;
		if(texture != null) {
			width = texture.getWidth();
			height = texture.getHeight();
		}
		else {
			width=0;
			height=0;
		}
	}
	public Thing(All all, int x, int y) {
		this(all, all.getAssets().defThing, x, y);
	}
	
	public void tick() {};
	public void render() {
		render(all.getGraphics());
	}
	public void render(Graphics g) {
		if(g != null) {
			if(texture != null) {
				g.drawImage(texture, (int) x, (int) y, width, height, null);
			}
		}
	};
}