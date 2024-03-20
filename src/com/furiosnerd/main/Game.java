package com.furiosnerd.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.furiosnerd.entity.Entity;
import com.furiosnerd.entity.Player;
import com.furiosnerd.graphics.Spritesheet;

public class Game extends Canvas implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static JFrame frame;

	private Thread thread;
	private boolean isRunning = true;
	private final int WIDTH = 180;
	private final int HEIGHT = 120;
	private final int SCALE = 3;
	
	
	private BufferedImage image;
	
	public List<Entity> entitys;
	
	public Spritesheet spritesheet;
	
	public Game() {
		
		this.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		initFrame();
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_BGR);
		
		entitys = new ArrayList<Entity>();
		
		spritesheet = new Spritesheet("/spritesheet.png");
		
		entitys.add(new Player(0, 0, 16, 16, spritesheet.getSprite(32, 0, 16, 16)));
	}

	private void initFrame() {
		frame = new JFrame("Game do Matus");
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

	public synchronized void start() {
		thread = new Thread(this);
		isRunning = true;
		thread.start();

	}

	public synchronized void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}

	public void spin() {
		
		for(int i= 0; i<entitys.size(); i++) {
			Entity e = entitys.get(i);
			e.spin();
		}

	}

	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics graphics = image.getGraphics();
		graphics.setColor(new Color(0,250,5));
		graphics.fillRect(0, 0, WIDTH, HEIGHT);
		
		for(int i= 0; i<entitys.size(); i++) {
			Entity e = entitys.get(i);
			e.render(graphics);
		}
		
		graphics.dispose();
		graphics = bs.getDrawGraphics();
		graphics.drawImage(image, 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null);
		bs.show();
	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();
		while (isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;

			if (delta >= 1) {
				spin();
				render();
				frames++;
				delta--;
			}
			
			if (System.currentTimeMillis() - timer >= 1000) {
				System.out.println("FPS: " + frames);
				frames = 0;
				timer += 1000;
			}

		}
		
		stop();

	}

}


