package sqr.objects;

import java.awt.image.BufferedImage;

import sqr.All;
import sqr.objects.tiles.Tile;

public abstract class Alive extends Thing{
	private int health;
	protected float root2=(float) 0.7;
	protected float speedY;
	protected float speedX;
	protected boolean ghost = false;
	protected Tile tiles[][];

	public Alive(All all, Tile[][] tiles, BufferedImage texture, int x, int y, int health) {
		super(all, texture, x, y);
		this.health = health;
		this.tiles = tiles;
		speedY = 0; 
		speedX = 0;

		offX = 1;
		offY = 1;
		hitbox.width -= 2;
		hitbox.height -= 2;
	}

	protected void moveX() {
		x += speedX;
	}
	protected void moveY() {
		y += speedY;
	}
	public void moveX(float v) {
		x += v;
	}
	public void moveY(float h) {
		y += h;
	}

	public Alive(All all, BufferedImage texture, int x, int y) {
		this(all, null, texture, x, y, 1);
	}
	
	public Alive(All all, int x, int y, int health) {
		this(all, null, all.getAssets().defAlive, x, y, health);
	}
	
	public Alive(All all, int x, int y) {
		this(all, all.getAssets().defAlive, x, y);
	}
	public Alive(All all, Tile[][] tiles, BufferedImage texture, int x, int y) {
		this(all, tiles, texture, x, y, 1);
	}
	
	public Alive(All all, Tile[][] tiles, int x, int y, int health) {
		this(all, tiles, all.getAssets().defAlive, x, y, health);
	}
	
	public Alive(All all, Tile[][] tiles, int x, int y) {
		this(all, tiles, all.getAssets().defAlive, x, y);
	}
	public void tick() {
		super.tick();
		if(!ghost) {
			if(tiles != null) {
				try {
					if(tiles.length > 0) {
						if(tiles[0].length > 0) {
							collision();
							step();
						}
					}
				}
				catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("Out of this World!");
					e.printStackTrace();
				}
			}
		}
		moveX();
		moveY();
	}
	public void collision() {
		float tempSpeedX = speedX;
		float tempSpeedY = speedY;
		int minX = (int) getX();
		int minY = (int) getY();
		int maxX = (int) (getX() + getWidth());
		int maxY = (int) (getY() + getHeight());
		minX = minX / all.getAssets().getWidth();
		minY = minY /  all.getAssets().getHeight();
		maxX = maxX / all.getAssets().getWidth();
		maxY = maxY / all.getAssets().getHeight();

		//expand the testing zone acoording to the direction
		if(tempSpeedY < 0) {	//up
			maxY--;
		}
		if(tempSpeedY > 0) {	//down
			if(minY < tiles[minX].length -1) {
				minY++;
			}
		}
		if(tempSpeedX < 0) {	//left
			maxX--;
		}
		if(tempSpeedX > 0) {	//right
			if(minX < tiles.length -1) {
				minX++;
			}
		}
		
		
		try {
			//tiles[maxY][maxX].collide();
			//start of collision up
			if(minY > 0) {
				if(speedY < 0) {
					for(int i = minX; i <= maxX; i++) {
						//System.out.println((minY-1)+" "+i + " "+maxX);
						int testedX = i;
						int testedY = minY;

						if(tiles[testedY][testedX].isSolid()) {
							int test = (int) (tiles[testedY][testedX].getY() + tiles[testedY][testedX].getHeight() - getY());
							System.out.println(test);
							if(test < speedY) {
								if(test < tempSpeedY) {
									tempSpeedY = test;
									collisionUp();
								}
								tiles[testedY][testedX].collide();
								//System.out.println("boom");
							}
						}
					}
				}
			}
			else {
				if(speedY < 0) {
					tempSpeedY = 0;
				}
			}
			//end of collision up
			//start of collision down
			if(maxY < tiles.length - 1) {
				if(speedY > 0) {
					for(int i = minX; i <= maxX; i++) {
						int testedX = i;
						int testedY = maxY;

						if(tiles[testedY][testedX].isSolid()) {
							int test = (int) (getY() + getHeight() - tiles[testedY][testedX].getY());
							if(test < speedY) {
								if(test < speedY) {
									tempSpeedY = test;
									collisionDown();
								}
								tiles[testedY][testedX].collide();
							}
						}
					}
				}
			}
			else {
				if(speedY > 0) {
					tempSpeedY = 0;
				}
			}
			//end of collision down
			//start of collision left
			if(minX > 0) {
				if(speedX < 0) {
					for(int i = minY; i <= maxY; i++) {
						int testedX = minX;
						int testedY = i;

						if(tiles[testedY][testedX].isSolid()) {
							int test = (int) (tiles[testedY][testedX].getX() + tiles[testedY][testedX].getWidth() - getX());
							if(test > speedX) {
								if(test > tempSpeedX) {
									tempSpeedX = test;
									collisionLeft();
								}
								tiles[testedY][testedX].collide();
							}
						}
					}
				}
			}
			else {
				if(speedX < 0) {
					tempSpeedX = 0;
				}
			}
			//end of collision left
			//start of collision right
			if(maxX < tiles[minY].length - 1) {
				if(speedX > 0) {
					for(int i = minY; i <= maxY; i++) {
						int testedX = maxX;
						int testedY = i;
						if(tiles[testedY][testedX].isSolid()) {
							collisionRight();
							int test = (int) (getX() + getWidth() + speedX - tiles[testedY][testedX].getX());
							if( test < speedX) {
								if( test < tempSpeedX) {
									tempSpeedX = test;
									collisionRight();
								}
								tiles[testedY][testedX].collide();
							}
						}
					}
				}
			}
			else {
				if(speedX > 0) {
					tempSpeedX = 0;
				}
			}
			//end of collision right
		} catch (Exception e) {
			System.out.println("Collition error");
			e.printStackTrace();
		}
		speedY = tempSpeedY;
		speedX = tempSpeedX;

		System.out.println("x: " + minX + " " + maxX);
		System.out.println("y: " + minY + " " + maxY);
	}
	public void step() {
		float tempSpeedX = speedX;
		float tempSpeedY = speedY;
		int minX = (int) getX();
		int minY = (int) getY();
		int maxX = (int) (getX() + getWidth());
		int maxY = (int) (getY() + getHeight());
		minX = minX / all.getAssets().getWidth();
		minY = minY /  all.getAssets().getHeight();
		maxX = maxX / all.getAssets().getWidth();
		maxY = maxY / all.getAssets().getHeight();
		try {
			//start of step up
			if(minY > 0) {
				if(speedY < 0) {
					for(int i = minX; i <= maxX; i++) {
						int testedX = i;
						int testedY = minY;
						if(tiles[testedY][testedX].isDynamic()) {
							tiles[testedY][testedX].step();
						}
					}
				}
			}
			//end of step up
			//start of step down
			if(maxY < tiles.length - 1) {
				if(speedY > 0) {
					for(int i = minX; i <= maxX; i++) {
						int testedX = i;
						int testedY = maxY;

						if(tiles[testedY][testedX].isDynamic()) {
							tiles[testedY][testedX].step();
						}
					}
				}
			}
			//end of step down
			//start of step left
			if(minX > 0) {
				if(speedX < 0) {
					for(int i = minY; i <= maxY; i++) {
						int testedX = maxX;
						int testedY = i;
						
						if(tiles[testedY][testedX].isDynamic()) {
							tiles[testedY][testedX].step();
						}
					}
				}
			}
			//end of step left
			//start of step right
			if(maxX < tiles[minX].length - 1) {
				if(speedX > 0) {
					for(int i = minY; i <= maxY; i++) {
						int testedX = maxX;
						int testedY = i;

						if(tiles[testedY][testedX].isDynamic()) {
							tiles[testedY][testedX].step();
						}
					}
				}
			}
			//end of step right
		} catch (Exception e) {
			System.out.println("Steping in Tiles error");
			e.printStackTrace();
		}
	}
	public void collisionUp() {
		System.out.println("up");
	}
	public void collisionDown() {
		System.out.println("dwn");}
	public void collisionLeft() {
		System.out.println("eft");}
	public void collisionRight() {
		System.out.println("gth");}
}
