package com.furiosnerd.entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.furiosnerd.main.Game;
import com.furiosnerd.map.Camera;

public class Entity {

	public static BufferedImage LIFEPACK_EN = Game.spritesheet.getSprite(0, 16, 16, 16);
	public static BufferedImage WEAPON_EN = Game.spritesheet.getSprite(16, 16, 16, 16);
	public static BufferedImage BULLET_EN = Game.spritesheet.getSprite(96, 0, 16, 16);
	public static BufferedImage ENEMY_EN = Game.spritesheet.getSprite(112, 0, 16, 16);

	private int maskX, maskY, maskW, maskH;

	protected double x;
	protected double y;
	protected int width;
	protected int height;

	private BufferedImage sprite;

	public Entity(int x, int y, int width, int height, BufferedImage sprite) {

		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sprite = sprite;

		this.maskX = 0;
		this.maskY = 0;
		this.maskW = width;
		this.maskH = height;

	}

	public void setMask(int maskX, int maskY, int maskW, int maskH) {
		this.maskX = maskX;
		this.maskY = maskY;
		this.maskW = maskW;
		this.maskY = maskY;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public int getX() {
		return (int) x;
	}

	public int getY() {
		return (int) y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void spin() {

	}

	public static boolean isColidding(Entity e1, Entity e2) {
		Rectangle e1Mask = new Rectangle(e1.getX() + e1.maskX, e1.getY() + e1.maskY, e1.maskW, e1.maskH);
		Rectangle e2Mask = new Rectangle(e2.getX() + e2.maskX, e2.getY() + e2.maskY, e2.maskW, e2.maskH);

		return e1Mask.intersects(e2Mask);
	}

	public void render(Graphics graphics) {
		graphics.drawImage(sprite, this.getX() - Camera.x, this.getY() - Camera.y, null);
		/**
		 * verificando tamanho da mascara
		 * 
		 * graphics.setColor(Color.YELLOW);
		 *graphics.fillRect(this.getX() - Camera.x + maskX, this.getY() - Camera.y + maskY, width, height);
		*/
	}

}
