package sqr.objects;

import java.awt.Color;
import java.awt.Graphics;

import sqr.All;
import sqr.input.Key;
import sqr.states.GameState;

public class Player extends Alive{
	Key key;
	private byte vert, horz;	//texture number
	private byte speed;
	private boolean blink;
	private byte tBlink; //timers
	private byte max;
	private boolean idle, idleX, idleY;
	
	private Eyes eyes;

	public Player(All all, GameState gameState, int x, int y, Key key) {
		super(all, gameState, all.getAssets().player[2], x, y);
		this.key = key;
		blink = false;
		vert = 0;
		horz = 0;
		speed = 3;
		tBlink = 0;
		idle = true;
		max = 3;
		eyes = new Eyes(all, x, y);
	}
	public Player(All all, int x, int y, Key key) {
		this(all, null, x, y, key);
	}
	
	@Override
	public void tick() {
		super.tick();
		speedX = 0;
		speedY = 0;
		idleX = true;
		idleY = true;
		eyes.tick();
		eyes.setX(x);
		eyes.setY(y);
		if(key.up) {
			vert -- ;
			idleY = false;
		}
		if(key.down) {
			vert ++ ;
			idleY = false;
		}
		if(key.left) {
			horz -- ;
			idleX = false;
		}
		if(key.right) {
			horz ++ ;
			idleX = false;
		}
		if(horz>max) {
			horz --;
		}
		if(horz<-max) {
			horz ++;
		}
		if(vert>max) {
			vert --;
		}
		if(vert<-max) {
			vert ++;
		}
		if(idleY) {
			speedX = (horz/max)*speed;	
		}
		else {
			speedX = (horz/max)*speed*root2;
		}
		if(idleX) {
			speedY = (vert/max)*speed;
		}
		else {
			speedY = (vert/max)*speed*root2;
		}
		//speedY = (vert/max)*speed;
		idle = idleX && idleY;
		if(idle) {
			blink();
		}
		else {
			blink = false;
			eyes.moveX(horz);
			eyes.moveY(vert);
		}
		eyes.blink = blink;
		if(idleX) {
			horz = 0;
		}
		if(idleY) {
			vert = 0;
		}
	}
	private void blink() {
		tBlink++;
		if(tBlink>10) {
			if(Math.random()*30 > 29) {
				tBlink=-3;
			}
		}
		if(tBlink>160) {
			tBlink = 0;
		}
		blink = tBlink <= 0;
		
	}
	
	public void render(Graphics g) {
		super.render(g);
		eyes.render(g);
		if(all.dev) {
			g.setColor(Color.RED);
			g.fillRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
		}
	}
}
