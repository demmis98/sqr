package sqr.objects;

import java.awt.Graphics;

import sqr.All;

public class Player extends Alive{

	public Player(All all, int x, int y) {
		super(all, all.getAssets().player[0], x, y);
		this.texture = all.getAssets().player[0];
	}
	
	@Override
	public void tick() {
		
	}
	public void render(Graphics g) {
		super.render(g);
	}
}
