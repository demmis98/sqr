package sqr.objects;

import java.awt.image.BufferedImage;

import sqr.All;

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
				int minX = (int) (getX() / all.getAssets().getWidth());
				int minY = (int) (getY() / all.getAssets().getHeight());
				int maxX = (int) ((getX() + getWidth()) / all.getAssets().getWidth());
				int maxY = (int) ((getY() + getHeight()) / all.getAssets().getHeight());
				try {
					if(tiles.length>0) {
						if(minY > 0) {
							if(speedY<0) {
								for(int i = minX; i <= maxX; i++) {
									//System.out.println((minY-1)+" "+i + " "+maxX);
									if(tiles[i][minY-1].isSolid()) {
										if(tiles[i][minY-1].getY()+tiles[i][minY-1].getHeight() - getY() > speedY) {
											speedY = tiles[i][minY-1].getY()+tiles[i][minY-1].getHeight() - getY();
											System.out.println("boom");
										}
									}
								}
							}
						}
						else {
							if(speedY < 0) {
								speedY = 0;
							}
						}
					}
				} catch (Exception e) {
					System.out.println("Collition error");
					e.printStackTrace();
				}
			}
		}
		moveX();
		moveY();
	}
}
