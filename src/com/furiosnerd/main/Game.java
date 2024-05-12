package com.furiosnerd.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

import com.furiosnerd.entity.Enemy;
import com.furiosnerd.entity.Entity;
import com.furiosnerd.entity.Player;
import com.furiosnerd.graphics.Spritesheet;
import com.furiosnerd.graphics.UI;
import com.furiosnerd.map.Map;

public class Game extends Canvas implements Runnable, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static JFrame frame;

	private Thread thread;
	private boolean isRunning = true;
	public static final int WIDTH = 260;
	public static final int HEIGHT = 140;
	private final int SCALE = 3;

	private BufferedImage image;

	public static List<Entity> entitys;
	public static List<Enemy> enemys;

	public static Spritesheet spritesheet;
	public static Map map;

	public static Player player;

	public static Random ran;
	
	public UI ui;

	public Game() {

		ran = new Random();

		addKeyListener(this);

		this.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		initFrame();
		
		ui = new UI();

		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_BGR);

		entitys = new ArrayList<Entity>();
		enemys = new ArrayList<Enemy>();

		spritesheet = new Spritesheet("/spritesheet.png");

		player = new Player(0, 0, 16, 16, spritesheet.getSprite(32, 0, 16, 16));

		entitys.add(player);

		map = new Map("/map.png");

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

		//if (Game.ran.nextInt(100) < 50) {

			for (int i = 0; i < entitys.size(); i++) {
				Entity e = entitys.get(i);
				e.spin();
			}

		}
	//}

	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics graphics = image.getGraphics();
		graphics.setColor(new Color(0, 255, 0));
		graphics.fillRect(0, 0, WIDTH, HEIGHT);

		map.render(graphics);

		for (int i = 0; i < entitys.size(); i++) {
			Entity e = entitys.get(i);
			e.render(graphics);
		}
		
		ui.render(graphics);

		graphics.dispose();
		graphics = bs.getDrawGraphics();
		graphics.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
		
		
		graphics.setFont(new Font("arial",Font.BOLD,25));
		graphics.setColor(Color.LIGHT_GRAY);
		graphics.drawString("Munião: " + Player.ammunition,600,30);
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
		requestFocus();
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

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			player.right = true;

		} else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			player.left = true;

		}
		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
			player.up = true;

		} else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
			player.down = true;

		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			player.right = false;

		} else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			player.left = false;

		}
		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
			player.up = false;

		} else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
			player.down = false;

		}

	}

}
