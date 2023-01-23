package sqr.sfx;

import java.awt.image.BufferedImage;
import java.io.File;

public class Assets {
	
	private static final int width = 16, height = 16;	//sprite dimensions
	private static final String imgPath = "res/img/"; //path to sprites
	
	public static BufferedImage defThing, defAlive, defTile;	//defaults
	
	public static BufferedImage[] player;

	public void init(){
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage(imgPath + "sprites.png"));

		defThing = sheet.crop(0, 0, width, height);
		defAlive = sheet.crop(width, 0, width, height);
		defAlive = sheet.crop(width*2, 0, width, height);
		
		player = new BufferedImage[4];
		player[0] = sheet.crop(0, height, width, height);
		player[1] = sheet.crop(width, height, width, height);
		player[2] = sheet.crop(0, height*2, width, height);
		player[3] = sheet.crop(width, height*2, width, height);
	}
	
}