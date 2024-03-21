package com.furiosnerd.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Entity {

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

	public int getX() {
		return(int)x;
	}

	public int getY() {
		return (int)y;
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
		graphics.drawImage(sprite, this.getX(),this.getY(), null);
	}
	
	

}
