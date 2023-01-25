package sqr.states;

import java.awt.Graphics;

import sqr.All;
import sqr.input.Key;
import sqr.objects.Player;
import sqr.world.World;
import sqr.world.WorldBuilder;

public class BuildState extends State {
	Key key;
	Player player;
	World world;
	WorldBuilder worldBuilder;

	public BuildState(All all) {
		super(all);
		name = "build state";
		all.getAssets().semi3D = true;
		key = new Key();
		all.getFrame().addKeyListener(key);
		world = new World(all, "test");
		worldBuilder = new WorldBuilder(all, key, world);
		player = new Player(all, world.getTiles(), world.getSpawnX(), world.getSpawnY(), key);
		all.addThing(player);
	}
	
	@Override
	public void tick() {
		key.tick();
		world.tick();
		worldBuilder.tick();
		for(int i = 0; i < all.getThings().size(); i++) {
			all.getThings().get(i).tick();
		}
		float newOff = (player.getY() / world.getWidth()) * 7;
		newOff -= 3;
		getAll().getAssets().defWallHeight = (byte) (newOff);
	}

	@Override
	public void render(Graphics g) {
		world.renderNotSolid(g);
		for(int i = 0; i < all.getThings().size(); i++) {
			all.getThings().get(i).render(g);
		}
		world.renderSolid(g);
	}
}