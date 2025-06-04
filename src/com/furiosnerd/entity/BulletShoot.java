package com.furiosnerd.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.furiosnerd.map.Camera;

public class BulletShoot extends Entity {

	private int directionX;
	private int directionY;
	private double speed = 4;

	public BulletShoot(int x, int y, int width, int height, BufferedImage sprite, int dX, int dY) {
		super(x, y, width, height, sprite);
		this.directionX = dX;
		this.directionY = dY;
	}

	public void tick() {
		x += directionX * speed;
		x += directionY * speed;
	}

	public void render(Graphics graphics) {
		graphics.setColor(Color.ORANGE);
		graphics.fillOval(this.getX() - Camera.x, this.getY() - Camera.y, width, height);
	}
}
