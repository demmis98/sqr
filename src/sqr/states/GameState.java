package sqr.states;

import java.awt.Color;
import java.awt.Graphics;

import sqr.All;
import sqr.input.Key;

public class GameState extends State {
	Key key;

	public GameState(All all) {
		super(all);
		key = new Key();
		all.getFrame().addKeyListener(key);
	}
	@Override
	public void tick() {
		key.tick();
		if(key.up) {
			System.out.println("up");
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.pink);
		g.fillRect(3, 3, 23, 23);
	}
}