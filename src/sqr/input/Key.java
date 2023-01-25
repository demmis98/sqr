package sqr.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Key implements KeyListener {
	
	public boolean wasd = false;
	private boolean[] keys;
	public boolean up, down, left, right;
	public boolean b, s;
	public boolean ctrl;
	
	public Key(){
		keys = new boolean[256];
	}
	
	public void tick(){
		if(wasd) {
			up = keys[KeyEvent.VK_W];
			down = keys[KeyEvent.VK_S];
			left = keys[KeyEvent.VK_A];
			right = keys[KeyEvent.VK_D];
		}
		else {
			up = keys[KeyEvent.VK_UP];
			down = keys[KeyEvent.VK_DOWN];
			left = keys[KeyEvent.VK_LEFT];
			right = keys[KeyEvent.VK_RIGHT];
		}

		b = keys[KeyEvent.VK_B];
		s = keys[KeyEvent.VK_S];

		ctrl = keys[KeyEvent.VK_CONTROL];
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}