package com.furiosnerd.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.furiosnerd.main.Game;

public class Entity {

	public static BufferedImage LIFEPACK_EN = Game.spritesheet.getSprite(0, 16, 16, 16);
	public static BufferedImage WEAPON_EN = Game.spritesheet.getSprite(16, 16, 16, 16);
	public static BufferedImage BULLET_EN = Game.spritesheet.getSprite(96, 0, 16, 16);
	public static BufferedImage ENEMY_EN = Game.spritesheet.getSprite(112, 0, 16, 16);

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

	public void render(Graphics graphics) {
		graphics.drawImage(sprite, this.getX(), this.getY(), null);
	}

}
