package sqr;

import java.awt.Canvas;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;

import sqr.objects.Thing;
import sqr.sfx.Assets;

public class All {
	private JFrame frame;
	private Graphics graphics;
	private Assets assets;
	private ArrayList<Thing> things;
	private Game game;
	public final boolean dev = true;
	public All(Game game) {
		this.game = game;
	}
	
	public static final char EXTENDED[] = { 0x00C7, 0x00FC, 0x00E9, 0x00E2,
            0x00E4, 0x00E0, 0x00E5, 0x00E7, 0x00EA, 0x00EB, 0x00E8, 0x00EF,
            0x00EE, 0x00EC, 0x00C4, 0x00C5, 0x00C9, 0x00E6, 0x00C6, 0x00F4,
            0x00F6, 0x00F2, 0x00FB, 0x00F9, 0x00FF, 0x00D6, 0x00DC, 0x00A2,
            0x00A3, 0x00A5, 0x20A7, 0x0192, 0x00E1, 0x00ED, 0x00F3, 0x00FA,
            0x00F1, 0x00D1, 0x00AA, 0x00BA, 0x00BF, 0x2310, 0x00AC, 0x00BD,
            0x00BC, 0x00A1, 0x00AB, 0x00BB, 0x2591, 0x2592, 0x2593, 0x2502,
            0x2524, 0x2561, 0x2562, 0x2556, 0x2555, 0x2563, 0x2551, 0x2557,
            0x255D, 0x255C, 0x255B, 0x2510, 0x2514, 0x2534, 0x252C, 0x251C,
            0x2500, 0x253C, 0x255E, 0x255F, 0x255A, 0x2554, 0x2569, 0x2566,
            0x2560, 0x2550, 0x256C, 0x2567, 0x2568, 0x2564, 0x2565, 0x2559,
            0x2558, 0x2552, 0x2553, 0x256B, 0x256A, 0x2518, 0x250C, 0x2588,
            0x2584, 0x258C, 0x2590, 0x2580, 0x03B1, 0x00DF, 0x0393, 0x03C0,
            0x03A3, 0x03C3, 0x00B5, 0x03C4, 0x03A6, 0x0398, 0x03A9, 0x03B4,
            0x221E, 0x03C6, 0x03B5, 0x2229, 0x2261, 0x00B1, 0x2265, 0x2264,
            0x2320, 0x2321, 0x00F7, 0x2248, 0x00B0, 0x2219, 0x00B7, 0x221A,
            0x207F, 0x00B2, 0x25A0, 0x00A0 };
	
	public char getChar(byte value) {
		char resp = ' ';
		if(value >= 0) {
			resp = (char) value;
		}
		else {
			resp = EXTENDED[(value * -1) - 1];
		}
		return resp;
	}
	public byte getByte(char value) {
		byte resp = 0;
		for(int i = 0; i < EXTENDED.length; i++) {
			if(value == EXTENDED[i]) {
				resp = (byte) ((i + 1) * -1);
			}
		}
		if(resp == 0) {
			resp = (byte) value;
		}
		return resp;
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
	
	public void addThing(Thing thing) {
		things.add(thing);
	}
	
	public void removeThing(Thing thing) {
		things.remove(thing);
	}

	public Game getGame() {
		return game;
	}
	
	public Canvas getCanvas() {
		return game.getCanvas();
	}
}