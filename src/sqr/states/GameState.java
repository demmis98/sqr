package sqr.states;

import java.awt.Color;
import java.awt.Graphics;

import sqr.All;
import sqr.input.Key;
import sqr.objects.Player;
import sqr.world.World;

public class GameState extends State {
	Key key;
	Player player;
	World world;

	public GameState(All all) {
		super(all);
		key = new Key();
		all.getFrame().addKeyListener(key);
		world = new World(all, "test");
		player = new Player(all, world.getTiles(), world.getSpawnX(), world.getSpawnY(), key);
	}
	@Override
	public void tick() {
		key.tick();
		world.tick();
		player.tick();
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.pink);
		g.fillRect(3, 3, 23, 23);
		world.render(g);
		player.render(g);
	}
}