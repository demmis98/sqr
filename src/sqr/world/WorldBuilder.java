package sqr.world;

import sqr.All;
import sqr.input.Key;

public class WorldBuilder{
	protected All all;
	protected Key key;
	protected World world;
	
	public WorldBuilder(All all, Key key, World world) {
		this.all = all;
		this.key = key;
		this.world = world;
	}
	
	public void saveWorld() {
		String save = "";
		System.out.println("Saving world...");
		save += world.getWidthInTiles();
		save += all.getChar(world.mark);
		save += world.getHeightInTiles();
		save += all.getChar(world.mark);
		String codeName = "";
		byte id = 0;
		for(int y = 0; y < world.getTiles().length; y++) {
			for(int x = 0; x < world.getTiles()[y].length; x++) {
				codeName = world.getTiles()[y][x].toString();
				codeName = codeName.split("@")[0];
				codeName = codeName.split(".tiles.")[1];
				switch(codeName) {
					case "Test":
						if(world.getTiles()[y][x].isSolid()) {
							id = 1;
						}
						else {
							id = 2;
						}
						break;
					case "Collide":
						id = 3;
						break;
					case "Dirt":
						id = 4;
						break;
					case "Cute":
						id = 5;
						break;
					case "Sign":
						id = world.getTiles()[y][x].num;
						if(world.getTiles()[y][x].isSolid()) {
							id = (byte) (id * -1);
						}
						break;
					default:
						id = 1; 
						break;
				}
				save += all.getChar(id);
			}
		}
		String add = "";
		for(int i = 0; i < all.getThings().size(); i++) {
			add = "";
			add += all.getChar(world.mark);
			codeName = all.getThings().get(i).toString();
			codeName = codeName.split("@")[0];
			codeName = codeName.split(".objects.")[1];
			add += codeName;
			add += all.getChar(world.subMark);
			switch(codeName) {
				default:
					break;
			}
			add += (int) all.getThings().get(i).getX();
			add += all.getChar(world.subMark);
			add += (int) all.getThings().get(i).getY();
			save += add;
		}
		all.getAssets().saveFile(all.getAssets().mapPath + "save.map", save);
	}
	
	public void tick() {
		if(key.ctrl) {
			if(key.s) {
				saveWorld();
			}
		}
	}

}