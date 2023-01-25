package sqr.states;

import java.awt.Graphics;
import java.util.ArrayList;

import sqr.All;
import sqr.objects.Thing;

public abstract class State {
	private All all;
	public State(All all) {
		this.all = all;
		all.setThings(new ArrayList<Thing>());
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);

	public All getAll() {
		return all;
	}
}
