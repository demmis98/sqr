package sqr.objects.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import sqr.All;
import sqr.objects.Thing;

public class Tile extends Thing {
	protected boolean solid = true;
	protected boolean stepable = false, collidable = false;
	protected byte defTimer = 3;
	protected BufferedImage collisionTex, stepTex;
	protected byte timerCol = 0, timerStep = 0;

	public Tile(All all, BufferedImage texture, int x, int y) {
		super(all, texture, x, y);
		collisionTex = all.getAssets().defTileCol;
		stepTex = all.getAssets().defTileStep;
	}

	public Tile(All all, int x, int y) {
		super(all, all.getAssets().defTile, x, y);
		texture = all.getAssets().defTile;
	}

	public void tick() {
		super.tick();
		if(timerCol != 0) {
			if(timerCol > 0) {
				timerCol--;
			}
			else {
				timerCol++;
			}
		}
		if(timerStep != 0) {
			if(timerStep > 0) {
				timerStep--;
			}
			else {
				timerStep++;
			}
		}
	}
	public void collide(float speed) {
		timerCol = (byte) (speed * 10);
	}
	public void collide() {
		collide(defTimer);
	}
	
	public void step(float speed) {
		timerStep = (byte) (speed * 5);
	}
	public void step() {
		step(defTimer);
	}
	
	
	public boolean isSolid() {
		return solid;
	}

	public boolean isStepable() {
		return stepable;
	}
	public boolean isCollidable() {
		return collidable;
	}

	public void render(Graphics g) {
		if(timerCol != 0) {
			super.render(g, collisionTex);
		}
		else {
			if(timerStep != 0) {
				super.render(g, stepTex);
			}
			else {
				super.render(g);
			}
		}
	}
}