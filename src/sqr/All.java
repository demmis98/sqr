package sqr;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;

import javax.swing.JFrame;

import sqr.objects.Thing;
import sqr.sfx.Assets;

public class All {
	private JFrame frame;
	private Graphics graphics;
	private Assets assets;
	private Thing things[];
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

	public Thing[] getThings() {
		return things;
	}

	public void setTiles(Thing things[]) {
		this.things = things;
	}
	
}