package sqr.world;

import java.awt.Graphics;
import java.io.File;
import java.util.Scanner;

import sqr.All;
import sqr.objects.tiles.Collide;
import sqr.objects.tiles.Cute;
import sqr.objects.tiles.Dirt;
import sqr.objects.tiles.Sign;
import sqr.objects.tiles.Test;
import sqr.objects.tiles.Tile;

public class World {
	Tile tiles[][];
	byte tileIDs[][];
	private int spawnX, spawnY;
	public final byte mark = -128, subMark = -127;
	All all;
	
	public World(All all,String name) {
		this.all = all;
		init(name);
	}
	private void init(String name) {
		load(all.getAssets().mapPath + name + ".map", true);
	}
	
	private void load(String txt, boolean path_not_inject) {
		String map = "";
		String split[];
		int x = 0, y = 0;
		if(path_not_inject) {
		    try {
		    	File file = new File(txt);
		        Scanner scanner = new Scanner(file);
		        if(scanner.hasNextLine()) {
		        	map += scanner.nextLine();
		        }
		        while (scanner.hasNextLine()) {
		        	map += '\n';
		        	map += scanner.nextLine();
		        }
		        scanner.close();
		    }
		    catch (Exception e) {
		        System.out.println("Error at loading World");
		        e.printStackTrace();
		    }
		}
		else {
			map = txt;
		}
        //System.out.println("Raw map: " + map);
        split = map.split(String.valueOf(all.getChar(mark)));
        try {
	        if(split.length > 0) {
	            x = Integer.parseInt(split[0]);
	        }
	        if(split.length > 1) {
	            y = Integer.parseInt(split[1]);
	        }
	        if(split.length > 2) {
		        tileIDs = new byte[y][x];
		        int h = 0;
		        int v = 0;
		        int t = 0;
		        try {
		        	for(int c = 0; c < split[2].length(); c++) {
		        		if(h >= x) {
		        			h = 0;
		        			v++;
		        		}
		        		tileIDs[v][h] = (byte) split[2].charAt(c);
		        		h++;
		        		t++;
		        		if(t >= x*y) {
		        			break;
		        		}
		        		//System.out.println(line.charAt(c));
		        		//System.out.println(h+" "+v+" "+t);
		        	}
		        }
		        catch(ArrayIndexOutOfBoundsException e) {
		        	
		        }
		        tiles = new Tile[y][x];
		        for(v = 0; v < tileIDs.length; v++) {
		        	for(h = 0; h < tileIDs[v].length; h++) {
		        		//System.out.println(tileIDs[v][h]);
		        		tiles[v][h] = tile(tileIDs[v][h], h, v);
		        	}
		        }
	        }
	        for(int i = 3; i < split.length; i++) {
	        	String values[] = split[i].split(String.valueOf(all.getChar(subMark)));
	        	switch(values[0]) {
	        		case "Player":
	        			spawnX = Integer.parseInt(values[1]);
	        			spawnY = Integer.parseInt(values[2]);
	        			break;
	        		default:
	        			break;
	        	}
	        }
        }
	    catch (Exception e) {
	        System.out.println("Error at creating World");
	        e.printStackTrace();
	    }
	}

	public void tick() {
        for(int y = 0; y < tiles.length; y++) {
        	for(int x = 0; x < tiles[y].length; x++) {
        		tiles[y][x].tick();
        	}
        }
	}
	public void render(Graphics g) {
        for(int y = 0; y < tiles.length; y++) {
        	for(int x = 0; x < tiles[y].length; x++) {
    			tiles[y][x].render(g);
        	}
        }
	}
	public void renderNotSolid(Graphics g) {
        for(int y = 0; y < tiles.length; y++) {
        	for(int x = 0; x < tiles[y].length; x++) {
        		if(!tiles[y][x].isSolid()) {
        			tiles[y][x].render(g);
        		}
        	}
        }
	}
	public void renderSolid(Graphics g) {
        for(int y = 0; y < tiles.length; y++) {
        	for(int x = 0; x < tiles[y].length; x++) {
        		int renderY = 0;
    			if(all.getAssets().defWallHeight > 0) {
    				renderY = y;
    			}
    			else {
    				renderY = tiles.length - y - 1;
    			}
        		if(tiles[renderY][x].isSolid()) {
        				tiles[renderY][x].render(g);
        		}
        	}
        }
	}
	
	public Tile tile(byte id, int x, int y) {
		Tile resp = null;
		switch(id) {
			case 1:
				resp = new Test(all, x * all.getAssets().getWidth(), y * all.getAssets().getHeight(), true);
				break;
			case 2:
				resp = new Test(all, x * all.getAssets().getWidth(), y * all.getAssets().getHeight(), false);
				break;
			case 3:
				resp = new Collide(all, x * all.getAssets().getWidth(), y * all.getAssets().getHeight());
				break;
			case 4:
				resp = new Dirt(all, x * all.getAssets().getWidth(), y * all.getAssets().getHeight());
				break;
			case 5:
				resp = new Cute(all, x * all.getAssets().getWidth(), y * all.getAssets().getHeight());
				break;
			default:
				resp = new Tile(all, x * all.getAssets().getWidth(), y * all.getAssets().getHeight());
				break;
		}
		//tile with a letter
		if(all.getAssets().getFontStart() <= id
				&& id < all.getAssets().getFontEnd()) {
			resp = new Sign(all, x * all.getAssets().getWidth(), y * all.getAssets().getHeight(), id);
		}
		if(all.getAssets().getFontStart() <= id * -1
				&& id * -1 < all.getAssets().getFontEnd()) {
			resp = new Sign(all, true, x * all.getAssets().getWidth(), y * all.getAssets().getHeight(), id);
		}
		return resp;
	}
	public Tile tile(byte id) {
		return tile(id, 0, 0);
	}
	
	public Tile[][] getTiles() {
		return tiles;
	}
	public int getSpawnX() {
		return spawnX;
	}
	public int getSpawnY() {
		return spawnY;
	}
	public int getWidth() {
		return tiles[0].length * all.getAssets().getWidth();
	}
	public int getHeight() {
		return tiles.length * all.getAssets().getWidth();
	}
	public int getWidthInTiles() {
		return tiles[0].length;
	}
	public int getHeightInTiles() {
		return tiles.length;
	}
}