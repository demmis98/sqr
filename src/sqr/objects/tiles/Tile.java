package sqr.objects.tiles;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import sqr.All;
import sqr.objects.Thing;

public class Tile extends Thing {
	protected boolean solid = true;
	protected boolean stepable = false, collidable = false;
	protected byte defTimer = 3;
	protected BufferedImage collisionTex, stepTex, collisionWallTex, defWallTex;
	protected byte timerCol = 0, timerStep = 0;

	public Tile(All all, BufferedImage texture, int x, int y) {
		super(all, texture, x, y);
		collisionTex = all.getAssets().defTileCol;
		stepTex = all.getAssets().defTileStep;
		defWallTex = all.getAssets().defTileWall;
		wallTex = defWallTex;
		collisionWallTex = all.getAssets().defTileColWall;
	}

	public Tile(All all, int x, int y) {
		this(all, all.getAssets().defTile, x, y);
		texture = all.getAssets().defTile;
	}
	
	protected void setWalls() {
		defWallTex = all.getAssets().getScaledImage(texture, getWidth(), all.getAssets().defWallHeight);
		wallTex = defWallTex;
		collisionWallTex = all.getAssets().getScaledImage(collisionTex, getWidth(), all.getAssets().defWallHeight);
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
		if(all.getAssets().semi3D) {
			if(solid) {
				wallHeight = all.getAssets().defWallHeight;
				wallOff = (byte) (getHeight() - wallHeight); 
			}
		}
		else {
			wallHeight = 0;
		}
		if(timerCol == 1) {
			wallTex = defWallTex;
		}
	}
	public void collide(float speed) {
		timerCol = (byte) (speed * 10);
		wallTex = collisionWallTex;
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