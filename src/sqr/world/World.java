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
	All all;
	
	public World(All all,String name) {
		this.all = all;
		init(name);
	}
	private void init(String name) {
		load(all.getAssets().mapPath + name + ".txt", true);
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
        split = map.split("\n");
        try {
	        if(split.length>0) {
	            x = Integer.parseInt(split[0]);
	        }
	        if(split.length>1) {
	            y = Integer.parseInt(split[1]);
	        }
	        if(split.length>2) {
	            spawnX = Integer.parseInt(split[2]);
	        }
	        if(split.length>3) {
	            spawnY = Integer.parseInt(split[3]);
	        }
	        tileIDs = new byte[y][x];
	        int h = 0;
	        int v = 0;
	        int t = 0;
	        try {
		        for(int i = 4; i < split .length; i++) {
		        	String line = split[i];
		        	for(int c = 0; c < line.length(); c++) {
		        		if(h >= x) {
		        			h = 0;
		        			v++;
		        		}
		        		tileIDs[v][h] = (byte) line.charAt(c);
		        		h++;
		        		t++;
		        		if(t >= x*y) {
		        			break;
		        		}
		        		//System.out.println(line.charAt(c));
		        		//System.out.println(h+" "+v+" "+t);
		        	}
	        		if(t >= x*y) {
	        			break;
	        		}
		        }
	        }
	        catch(ArrayIndexOutOfBoundsException e) {
	        	
	        }
	        tiles = new Tile[y][x];
	        for(v = 0; v < tileIDs.length; v++) {
	        	for(h = 0; h < tileIDs[v].length; h++) {
	        		//System.out.println(tileIDs[v][h]);
	        		switch(tileIDs[v][h]) {
	        			case 1:
	        				tiles[v][h] = new Test(all, 0, 0, true);
	        				break;
	        			case 2:
	        				tiles[v][h] = new Test(all, 0, 0, false);
	        				break;
	        			case 3:
	        				tiles[v][h] = new Collide(all, 0, 0);
	        				break;
	        			case 4:
	        				tiles[v][h] = new Dirt(all, 0, 0);
	        				break;
	        			case 5:
	        				tiles[v][h] = new Cute(all, 0, 0);
	        				break;
	        			default:
	        				tiles[v][h] = new Tile(all, 0, 0);
	        				break;
	        		}
	        		//tile with a letter
	        		if(all.getAssets().getFontStart() <= tileIDs[v][h]
	        				&& tileIDs[v][h] < all.getAssets().getFontEnd()) {
	        			tiles[v][h] = new Sign(all, 0, 0, tileIDs[v][h]);
	        		}
	        		tiles[v][h].setX(h*all.getAssets().getWidth());
	        		tiles[v][h].setY(v*all.getAssets().getHeight());
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
	public Tile[][] getTiles() {
		return tiles;
	}
	public int getSpawnX() {
		return spawnX * all.getAssets().getWidth();
	}
	public int getSpawnY() {
		return spawnY * all.getAssets().getHeight();
	}
	public int getWidth() {
		return tiles[0].length * all.getAssets().getWidth();
	}
	public int getHeight() {
		return tiles.length * all.getAssets().getWidth();
	}
}