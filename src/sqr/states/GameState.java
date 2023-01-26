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
		name = "game state";
		all.getAssets().semi3D = false;
		key = new Key();
		all.getFrame().addKeyListener(key);
		world = new World(all, "test");
		player = new Player(all, this, world.getSpawnX(), world.getSpawnY(), key);
		all.addThing(player);
	}
	
	@Override
	public void tick() {
		key.tick();
		world.tick();
		for(int i = 0; i < all.getThings().size(); i++) {
			all.getThings().get(i).tick();
		}
		float newOff = (player.getY() / world.getWidth()) * 7;
		newOff -= 3;
		getAll().getAssets().defWallHeight = (byte) (newOff);
		
		if(key.b) {
			if(all.dev) {
				all.getGame().setState((byte) 2);
			}
		}
	}

	@Override
	public void render(Graphics g) {
		world.renderNotSolid(g);
		for(int i = 0; i < all.getThings().size(); i++) {
			all.getThings().get(i).render(g);
		}
		world.renderSolid(g);
	}

	public World getWorld() {
		return world;
	}
}