package sqr.states;

import java.awt.Graphics;

import sqr.All;
import sqr.input.Key;
import sqr.objects.Player;
import sqr.world.World;

public class BuildState extends State {
	Key key;
	Player player;
	World world;

	public BuildState(All all) {
		super(all);
		all.getAssets().semi3D = true;
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
		float newOff = (player.getY() / world.getWidth()) * 7;
		newOff -= 3;
		getAll().getAssets().defWallHeight = (byte) (newOff);
	}

	@Override
	public void render(Graphics g) {
		world.renderNotSolid(g);
		player.render(g);
		world.renderSolid(g);
	}
}