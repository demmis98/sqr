package sqr;

import java.awt.Graphics;

import javax.swing.JFrame;

import sqr.objects.Tile;
import sqr.sfx.Assets;

public class All {
	private JFrame frame;
	private Graphics graphics;
	private Assets assets;
	private Tile tiles[];
	public All() {
	}
	
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public Graphics getGraphics() {
		return graphics;
	}

	public void setGraphics(Graphics graphics) {
		this.graphics = graphics;
	}

	public Assets getAssets() {
		return assets;
	}

	public void setAssets(Assets assets) {
		this.assets = assets;
	}

	public Tile[] getTiles() {
		return tiles;
	}

	public void setTiles(Tile tiles[]) {
		this.tiles = tiles;
	}
	
}