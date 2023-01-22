package sqr.sfx;

import java.awt.image.BufferedImage;
import java.io.File;

public class Assets {
	
	private static final int width = 16, height = 16;
	private static final String imgPath = "res/img/";
	
	public static BufferedImage defThing, defAlive;
	
	public static BufferedImage[] player;

	public void init(){
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage(imgPath + "sprites.png"));

		defThing = sheet.crop(0, 0, width, height);
		defAlive = sheet.crop(width, 0, width, height);
		
		player = new BufferedImage[12];
		player[0] = sheet.crop(0, height, width, height);
		player[1] = sheet.crop(width, height, width, height);
		player[2] = sheet.crop(width*2, height, width, height);
		player[3] = sheet.crop(width*3, height, width, height);
		player[4] = sheet.crop(0, height*2, width, height);
		player[5] = sheet.crop(width, height*2, width, height);
		player[6] = sheet.crop(width*2, height*2, width, height);
		player[7] = sheet.crop(width*3, height*2, width, height);
		player[8] = sheet.crop(0, height*3, width, height);
		player[9] = sheet.crop(width, height*3, width, height);
		player[10] = sheet.crop(width*2, height*3, width, height);
		player[11] = sheet.crop(width*3, height*3, width, height);
	}
	
}