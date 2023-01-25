package sqr;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.JFrame;

import sqr.objects.Thing;
import sqr.sfx.Assets;

public class All {
	private JFrame frame;
	private Graphics graphics;
	private Assets assets;
	private ArrayList<Thing> things;
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

	public ArrayList<Thing> getThings() {
		return things;
	}

	public void setThings(ArrayList<Thing> things) {
		this.things = things;
	}
	
}