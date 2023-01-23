package sqr.world;

import java.awt.Graphics;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import sqr.All;
import sqr.objects.Tile;

public class World {
	Tile tiles[][];
	byte tileIDs[][];
	int spawnX, spawnY;
	All all;
	Map<Integer, Tile> fromID;
	
	public World(All all,String name) {
		this.all = all;
		init(name);
	}
	private void init(String name) {
		fromID = new HashMap<Integer, Tile>();
		load(all.getAssets().mapPath + name + ".txt", true);
	}
	
	private void load(String txt, boolean path_not_inject) {
		String map = "";
		String split[];
		int x = 0, y = 0, spawnX = 0, spawnY = 0;
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
	        tileIDs = new byte[x][y];
	        int h = 0;
	        int v = 0;
	        int t = 0;
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
	        tiles = new Tile[x][y];
	        for(v = 0; v < tileIDs.length; v++) {
	        	for(h = 0; h < tileIDs[v].length; h++) {
	        		//System.out.println(tileIDs[v][h]);
	        		if(fromID.containsKey(tileIDs[v][h])) {
	        			tiles[v][h] = fromID.get(tileIDs[v][h]);
	        		}
	        		else {
	        			tiles[v][h] = new Tile(all, 0, 0);
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
        for(int x = 0; x < tiles.length; x++) {
        	for(int y = 0; y < tiles[x].length; y++) {
        		tiles[y][x].tick();
        	}
        }
	}
	public void render(Graphics g) {
        for(int x = 0; x < tiles.length; x++) {
        	for(int y = 0; y < tiles[x].length; y++) {
        		tiles[y][x].render(g);
        	}
        }
	}
	public Tile[][] getTiles() {
		return tiles;
	}
}