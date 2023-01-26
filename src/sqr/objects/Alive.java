package sqr.objects;

import java.awt.image.BufferedImage;

import sqr.All;
import sqr.objects.tiles.Tile;
import sqr.states.GameState;

public abstract class Alive extends Thing{
	private int health;
	boolean up = false, down = false, left = false, right = false;
	boolean idleX = false, idleY = false;
	protected float root2=(float) 0.7;
	protected float speedY;
	protected float speedX;
	protected boolean ghost = false;
	protected GameState gameState;

	public Alive(All all, GameState gameState, BufferedImage texture, int x, int y, int health) {
		super(all, texture, x, y);
		this.health = health;
		this.gameState = gameState;
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
	public Alive(All all, GameState gameState, BufferedImage texture, int x, int y) {
		this(all, gameState, texture, x, y, 1);
	}
	
	public Alive(All all, GameState gameState, int x, int y, int health) {
		this(all, gameState, all.getAssets().defAlive, x, y, health);
	}
	
	public Alive(All all, GameState gameState, int x, int y) {
		this(all, gameState, all.getAssets().defAlive, x, y);
	}
	public void tick() {
		up = speedY < 0;
		down = speedY > 0;
		left = speedX < 0;
		right = speedX > 0;
		
		idleY = speedY == 0;
		idleX = speedX == 0;
		
		if(gameState != null) {
			if(!ghost) {
				if(gameState.getWorld().getTiles() != null) {
					try {
						if(gameState.getWorld().getTiles().length > 0) {
							if(gameState.getWorld().getTiles()[0].length > 0) {
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
		}
		moveX();
		moveY();
		
		super.tick();
	}
	
	public void collision() {
		try {
			int minX = (int) (getX() / all.getAssets().getWidth());
			int minY = (int) (getY() / all.getAssets().getHeight());
			int maxX = (int) ((getX() + getWidth()) / all.getAssets().getWidth());
			int maxY = (int) ((getY() + getHeight()) / all.getAssets().getHeight());
			
			Tile tiles[][] = gameState.getWorld().getTiles();
			
			if(up) {
				minY--;
				//maxY--;
			}
			if(down) {
				minY++;
				maxY++;
			}
			if(left) {
				minX--;
				maxX--;
			}
			if(right) {
				minX++;
				maxX++;
			}
			
			if(minX < 0) {
				minX = 0;
			}
			if(minY < 0) {
				minY = 0;
			}
			maxX++;
			if(maxX > tiles[minY].length) {
				maxX = tiles[minY].length;
			}
			maxY++;
			if(maxY > tiles.length) {
				maxY = tiles.length;
			}

			float tempSpeedY = speedY;
			float tempSpeedX = speedX;
			for(int v = minY; v < maxY; v++) {
				for(int h = minX; h < maxX; h++) {
					if(up) {
						float temp = collisionUp(tiles[v][h]);
						if(tiles[v][h].isSolid()){
							if(0 >= temp && temp > tempSpeedY) {
								tempSpeedY = temp;
							}
						}
						if(tiles[v][h].isCollidable()) {
							if(temp >= speedY) {
								gameState.getWorld().getTiles()[v][h].collide();
							}
						}
					}
					if(down) {
						float temp = collisionDown(tiles[v][h]);
						if(tiles[v][h].isSolid()){
							if(0 <= temp && temp < tempSpeedY) {
								tempSpeedY = temp;
							}
						}
						if(tiles[v][h].isCollidable()) {
							if(temp <= speedY) {
								gameState.getWorld().getTiles()[v][h].collide();
							}
						}
					}
					if(left) {
						float temp = collisionLeft(tiles[v][h]);
						if(tiles[v][h].isSolid()){
							if(0 >= temp && temp > tempSpeedX) {
								tempSpeedX = temp;
							}
						}
						if(tiles[v][h].isCollidable()) {
							if(temp >= speedX) {
								gameState.getWorld().getTiles()[v][h].collide();
							}
						}
					}
					if(right) {
						float temp = collisionRight(tiles[v][h]);
						if(tiles[v][h].isSolid()){
							if(0 >= temp && temp > tempSpeedX) {
								tempSpeedX = temp;
							}
						}
						if(tiles[v][h].isCollidable()) {
							if(temp <= speedX) {
								gameState.getWorld().getTiles()[v][h].collide();
							}
						}
					}
				}
			}

			speedY = tempSpeedY;
			speedX = tempSpeedX;
			//System.out.println("x: " + minX + " " + maxX);
			//System.out.println("y: " + minY + " " + maxY);
		
		}catch (Exception e) {
			System.out.println("Collision error");
			e.printStackTrace();
		}
	}
	public float collisionUp(Thing thing) {
		float resp = speedY;
		resp--;
		boolean overlap = all.linesOverlap(getX(), getWidth(), thing.getX(), thing.getWidth());
		if(overlap) {
			resp = thing.getY() + thing.getHeight() - getY();
			resp = resp * -1;
		}
		return resp;
	}
	public float collisionDown(Thing thing) {
		float resp = speedY;
		resp++;
		boolean overlap = all.linesOverlap(getX(), getWidth(), thing.getX(), thing.getWidth());
		if(overlap) {
			resp = thing.getY() - getHeight() - getY();
		}
		return resp;
	}
	public float collisionLeft(Thing thing) {
		float resp = speedY;
		resp--;
		boolean overlap = all.linesOverlap(getY(), getHeight(), thing.getY(), thing.getHeight());
		if(overlap) {
			resp = thing.getX() + thing.getWidth() - getX();
			resp = resp * -1;
		}
		return resp;
	}
	public float collisionRight(Thing thing) {
		float resp = speedY;
		resp++;
		boolean overlap = all.linesOverlap(getY(), getHeight(), thing.getY(), thing.getHeight());
		if(overlap) {
			resp = thing.getX() - getWidth() - getX();
		}
		return resp;
	}

	public void step() {
		Tile tiles[][] = gameState.getWorld().getTiles();
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
}
