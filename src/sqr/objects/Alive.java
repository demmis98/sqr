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
		/*
		offX = 1;
		offY = 1;
		hitbox.width -= 2;
		hitbox.height -= 2;
		*/
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
		collisionWithOffset(0);
		if(speedY != 0) {
			if(speedX != 0) {
				collisionWithOffset(1);
			}
		}
	}
	public void collisionWithOffset(int off) {
		float tempSpeedX = speedX;
		float tempSpeedY = speedY;
		int minX = (int) getX() - off;
		int minY = (int) getY() - off;
		int maxX = (int) (getX() + getWidth() - 1);
		int maxY = (int) (getY() + getHeight() - 1);
		minX = minX / all.getAssets().getWidth();
		minY = minY /  all.getAssets().getHeight();
		maxX = maxX / all.getAssets().getWidth();
		maxY = maxY / all.getAssets().getHeight();

		boolean up = speedY < 0;
		boolean down = speedY > 0;
		boolean left = speedX < 0;
		boolean right = speedX > 0;
		
		try {
			//tiles[maxY][maxX].collide();
			//start of collision up

			if(minY > 0) {
				if(up) {
					tempSpeedY = testCollisionUp(minX, maxX, minY, maxY, tempSpeedY);
				}
				else {
					if(up) {
						tempSpeedY = 0;
					}
				}
			}
			//end of collision up
			//start of collision down
			if(maxY < tiles.length - 1) {
				if(down) {
					tempSpeedY = testCollisionDown(minX, maxX, minY, maxY, tempSpeedY);
				}
			}
			else {
				if(down) {
					tempSpeedY = 0;
				}
			}
			//end of collision down
			//start of collision left
			if(minX > 0) {
				if(left) {
					tempSpeedX = testCollisionLeft(minX, maxX, minY, maxY, tempSpeedX);
				}
			}
			else {
				if(left) {
					tempSpeedX = 0;
				}
			}
			//end of collision left
			//start of collision right
			if(maxX < tiles.length - 1) {
				if(right) {
					tempSpeedX = testCollisionRight(minX, maxX, minY, maxY, tempSpeedX);
				}
			}
			else {
				if(right) {
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

		//System.out.println("x: " + minX + " " + maxX);
		//System.out.println("y: " + minY + " " + maxY);
	}
	private float testCollisionUp(int minX, int maxX, int minY, int maxY, float speedY) {
		float tempSpeedY = speedY;
			minY--;
			for(int i = minX; i <= maxX; i++) {
				//System.out.println((minY-1)+" "+i + " "+maxX);
				int testedX = i;
				int testedY = minY;

				if(tiles[testedY][testedX].isCollidable()) {
					int test = (int) (tiles[testedY][testedX].getY() + tiles[testedY][testedX].getHeight() - getY());
					boolean hit = testOverlapLine((int) (getX() + speedX), (int) (getX() + getWidth() + speedX)
							, (int) tiles[testedY][testedX].getX()
							, (int) tiles[testedY][testedX].getX() + tiles[testedY][testedX].getWidth());
					if(hit) {
						if(test > speedY) {
							if(tiles[testedY][testedX].isSolid()) {
								if(test > tempSpeedY) {
									tempSpeedY = test;
									collisionUp();
								}
							}
							tiles[testedY][testedX].collide();
							//System.out.println("boom");
						}
					}
				}
			}
			minY++;
		return tempSpeedY;
	}
	private float testCollisionDown(int minX, int maxX, int minY, int maxY, float speedY) {
		float tempSpeedY = speedY;
		maxY++;
		for(int i = minX; i <= maxX; i++) {
			int testedX = i;
			int testedY = maxY;

			if(tiles[testedY][testedX].isCollidable()) {
				int test = (int) (getY() + getHeight() - tiles[testedY][testedX].getY()) * -1;
				if(test < speedY) {
					if(tiles[testedY][testedX].isSolid()) {
						if(test < speedY) {
							tempSpeedY = test;
							collisionDown();
						}
					}
					tiles[testedY][testedX].collide();
				}
			}
		}
		maxY--;
		return tempSpeedY;
	}
	private float testCollisionLeft(int minX, int maxX, int minY, int maxY, float speedX) {
		float tempSpeedX = speedX;
		minX--;
		for(int i = minY; i <= maxY; i++) {
			int testedX = minX;
			int testedY = i;

			if(tiles[testedY][testedX].isCollidable()) {
				int test = (int) (tiles[testedY][testedX].getX() + tiles[testedY][testedX].getWidth() - getX());
				if(test > speedX) {
					if(tiles[testedY][testedX].isSolid()) {
						if(test > tempSpeedX) {
							tempSpeedX = test;
							collisionUp();
						}
					}
					tiles[testedY][testedX].collide();
				}
			}
		}
		minX++;
		return tempSpeedX;
	}
	private float testCollisionRight(int minX, int maxX, int minY, int maxY, float speedX) {
		float tempSpeedX = speedX;
		maxX++;
		for(int i = minY; i <= maxY; i++) {
			int testedX = maxX;
			int testedY = i;

			if(tiles[testedY][testedX].isCollidable()) {
				int test = (int) (getX() + getWidth() - tiles[testedY][testedX].getX()) * -1;
				if(test < speedX) {
					if(tiles[testedY][testedX].isSolid()) {
						if(test < speedX) {
							tempSpeedX = test;
							collisionDown();
						}
					}
					tiles[testedY][testedX].collide();
				}
			}
		}
		maxX--;
		return tempSpeedX;
	}
	private boolean testOverlapLine(int h1, int h2, int x1, int x2) {
		boolean firstCorner = h1 < x2;
		boolean secondCorner = h2 > x1;
		return firstCorner || secondCorner;
	}

	public void step() {
		float tempSpeedX = speedX;
		float tempSpeedY = speedY;
		int minX = (int) getX();
		int minY = (int) getY();
		int maxX = (int) (getX() + getWidth() - 1);
		int maxY = (int) (getY() + getHeight() - 1);
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
						if(tiles[testedY][testedX].isStepable()) {
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

						if(tiles[testedY][testedX].isStepable()) {
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
						int testedX = minX;
						int testedY = i;
						
						if(tiles[testedY][testedX].isStepable()) {
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

						if(tiles[testedY][testedX].isStepable()) {
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
		//System.out.println("up");
		}
	public void collisionDown() {
		//System.out.println("dwn");
		}
	public void collisionLeft() {
		//System.out.println("eft");
		}
	public void collisionRight() {
		//System.out.println("gth");
		}
}
