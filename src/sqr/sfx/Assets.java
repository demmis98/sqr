package sqr.sfx;

import java.awt.image.BufferedImage;
import java.io.File;

public class Assets {
	
	private static final int width = 16, height = 16;	//sprite dimensions
	public static final String path = "res/";	//resources path
	private static final String imgPath = path+"img/"; //path to sprites
	public static final String mapPath = path+"map/"; //path to maps
	
	public static BufferedImage defThing, defAlive, defTile, defTileCol, defTileStep;	//defaults
	
	public static BufferedImage[] player;
	
	public static BufferedImage dirt, cuteTile, cuteTileCol;
	public static BufferedImage[] grass;

	public static BufferedImage test0, test1, test2, test3;
	public static BufferedImage test4, test5, test6, test7;

	public void init(){
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage(imgPath + "sprites.png"));

		defThing = sheet.crop(0, 0, width, height);
		defAlive = sheet.crop(width, 0, width, height);
		defTile = sheet.crop(width*2, 0, width, height);
		defTileCol = sheet.crop(width*2, height, width, height);
		defTileStep = sheet.crop(width*2, height*2, width, height);
		
		player = new BufferedImage[4];
		player[0] = sheet.crop(0, height, width, height);
		player[1] = sheet.crop(width, height, width, height);
		player[2] = sheet.crop(0, height*2, width, height);
		player[3] = sheet.crop(width, height*2, width, height);
		
		dirt = sheet.crop(width*2, height*3, width, height);
		
		grass = new BufferedImage[3];
		grass [0] = sheet.crop(width*3, 0, width, height);
		grass [1] = sheet.crop(width*3, height, width, height);
		grass [2] = sheet.crop(width*3, height*2, width, height);

		cuteTile = sheet.crop(0, height*3, width, height);
		cuteTileCol = sheet.crop(width, height*3, width, height);

		test0 = sheet.crop(width*4, 0, width, height);
		test1 = sheet.crop(width*4, height, width, height);
		test2 = sheet.crop(width*4, height*2, width, height);
		test3 = sheet.crop(width*4, height*3, width, height);
		test4 = sheet.crop(width*5, 0, width, height);
		test5 = sheet.crop(width*5, height, width, height);
		test6 = sheet.crop(width*5, height*2, width, height);
		test7 = sheet.crop(width*5, height*3, width, height);
	}

	public static int getWidth() {
		return width;
	}

	public static int getHeight() {
		return height;
	}
	
}