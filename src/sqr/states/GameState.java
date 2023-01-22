package sqr.states;

import java.awt.Color;
import java.awt.Graphics;

import sqr.All;
import sqr.input.Key;
import sqr.objects.Player;
import sqr.objects.Thing;

public class GameState extends State {
	Key key;
	Player player;

	public GameState(All all) {
		super(all);
		key = new Key();
		all.getFrame().addKeyListener(key);
		player = new Player(all,10,10,key);
	}
	@Override
	public void tick() {
		key.tick();
		player.tick();
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.pink);
		g.fillRect(3, 3, 23, 23);
		player.render(g);
	}
}