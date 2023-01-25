package sqr.sfx;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.FileWriter;
import java.io.IOException;

public class Assets {
	
	private static final int width = 16, height = 16;	//sprite dimensions
	public static final String path = "res/";	//resources path
	private static final String imgPath = path+"img/"; //path to sprites
	public static final String mapPath = path+"map/"; //path to maps
	
	public static BufferedImage[] font;
	private int fontStart = 65;
	private int fontEnd;
	
	public static BufferedImage defThing, defAlive, defTile, defTileCol, defTileStep, defTileWall, defTileColWall;	//defaults
	public byte defWallHeight = 7;
	
	public static BufferedImage[] player;
	
	public static BufferedImage dirt, cuteTile, cuteTileCol;
	public static BufferedImage[] grass;

	public static BufferedImage test0, test1, test2, test3;
	public static BufferedImage test4, test5, test6, test7;
	
	public boolean semi3D = false;

	public void init(){
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage(imgPath + "sprites.png"));

		defThing = sheet.crop(0, 0, width, height);
		defAlive = sheet.crop(width, 0, width, height);
		defTile = sheet.crop(width*2, 0, width, height);
		defTileCol = sheet.crop(width*2, height, width, height);
		defTileStep = sheet.crop(width*2, height*2, width, height);
		defTileWall = sheet.crop(0, height*5 - defWallHeight, width, defWallHeight);
		defTileColWall = sheet.crop(width, height*5 - defWallHeight, width, defWallHeight);
		
		player = new BufferedImage[4];
		player [0] = sheet.crop(0, height, width, height);
		player [1] = sheet.crop(width, height, width, height);
		player [2] = sheet.crop(0, height*2, width, height);
		player [3] = sheet.crop(width, height*2, width, height);
		
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

		int maxX = 16;
		int maxY = 2;
		SpriteSheet fontSheet = new SpriteSheet(ImageLoader.loadImage(imgPath + "font.png"));
		font = new BufferedImage[maxX * maxY];
		for(int y = 0; y < maxY; y++) {
			for(int x = 0; x < maxX; x++) {
				font [x + (y * maxX)] = fontSheet.crop(width * x, height * y, width, height);
			}
		}
		fontEnd = fontStart + (maxX * maxY);
	}
	
	public BufferedImage getScaledImage(BufferedImage srcImg, int w, int h){

	    //Create a new image with good size that contains or might contain arbitrary alpha values between and including 0.0 and 1.0.
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TRANSLUCENT);

	    //Create a device-independant object to draw the resized image
	    Graphics2D g2 = resizedImg.createGraphics();

	    //This could be changed, Cf. http://stackoverflow.com/documentation/java/5482/creating-images-programmatically/19498/specifying-image-rendering-quality
	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);

	    //Finally draw the source image in the Graphics2D with the desired size.
	    g2.drawImage(srcImg, 0, 0, w, h, null);

	    //Disposes of this graphics context and releases any system resources that it is using
	    g2.dispose();

	    //Return the image used to create the Graphics2D 
	    return resizedImg;
	}

	public void saveFile(String name, String data) {
		try {
	      FileWriter myWriter = new FileWriter(name);
	      myWriter.write(data);
	      myWriter.close();
	      System.out.println("Successfully wrote to the file.");
	    }
		catch (IOException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	    }
	}
	
	public static int getWidth() {
		return width;
	}

	public static int getHeight() {
		return height;
	}
	
	public int getFontStart() {
		return fontStart;
	}

	public int getFontEnd() {
		return fontEnd;
	}
	
}