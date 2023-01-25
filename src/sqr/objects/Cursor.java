package sqr.objects;

import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import sqr.All;
import sqr.input.Mouse;

public class Cursor extends Thing{
	Mouse mouse;
	public boolean leftClick = false, rightClick = false
			, leftClickI = false,  rightClickI = false, leftClickH = false,  rightClickH = false;
	
	public Cursor(All all, int x, int y) {
		super(all, x, y);
		texture = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		// Create a new blank cursor.
		java.awt.Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
		    all.getAssets().mouse, new Point(0, 0), "blank cursor");

		// Set the blank cursor to the JFrame.
		all.getFrame().getContentPane().setCursor(blankCursor);
		mouse = new Mouse();
		
		all.getFrame().addMouseListener(mouse);
		all.getFrame().addMouseMotionListener(mouse);
		all.getCanvas().addMouseListener(mouse);
		all.getCanvas().addMouseMotionListener(mouse);
	}
	
	public void tick() {
		super.tick();
		x = mouse.getMouseX();
		y = mouse.getMouseY();
		leftClick = mouse.isLeftPressed();
		rightClick = mouse.isRightPressed();
		leftClickI = !leftClickH && leftClick;
		rightClickI = !rightClickH && rightClick;
		leftClickH = leftClick;
		rightClickH = rightClick;
	}
}