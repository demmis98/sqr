package sqr;
//btw thenks to CodeNMore for a lot of the code :3

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import sqr.sfx.Assets;
import sqr.states.GameState;
import sqr.states.State;

public class Game {
	Canvas canvas;
	BufferStrategy bs;
	Graphics g;
	Assets assets;
	All all;
	JFrame frame;
	State state;
	boolean running;
	int screen_width, screen_height;
	
	
	public Game(int width, int height, String title) {
		screen_width = width;
		screen_height = height;
		
		//set frame
		frame = new JFrame(title);      
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(width, height);
        frame.setResizable(false);
        frame.setVisible(true);
        
        //set canvas
        canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setFocusable(false);
		
		frame.add(canvas);
		frame.pack();
        
		init();
	}
	
	private void init() {
		all = new All();
		all.setFrame(frame);
		all.setGraphics(g);
		assets = new Assets();
		assets.init();
		all.setAssets(assets);
	}
	
	public void run() {
		state = new GameState(all);
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
        running = true;
        while(running){
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1){
				tick();
				render();
				ticks++;
				delta--;
			}
			
			if(timer >= 1000000000){
				System.out.println("fps: " + ticks);
				ticks = 0;
				timer = 0;
			}
		}
        stop();
	}
	
	private void tick() {
		if(state != null) {
			state.tick();
		}
	}
	
	private void render() {
		bs = canvas.getBufferStrategy();
		if(bs == null){
			canvas.createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		//Clear Screen
		g.clearRect(0, 0, screen_width, screen_height);
		//se aceptan Komi-siones
		if(state != null)
			state.render(g);
		
		//se cierran comisiones
		bs.show();
		g.dispose();
	}
	
	public void stop(){
		running = false;
	}
}