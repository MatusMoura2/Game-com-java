package com.furiosnerd.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.furiosnerd.main.Game;
import com.furiosnerd.map.Camera;

public class BulletShoot extends Entity {

	private int directionX;
	private int directionY;
	private double speed = 4;
	private int life = 40, curLife = 0;

	public BulletShoot(int x, int y, int width, int height, BufferedImage sprite, int dX, int dY) {
		super(x, y, width, height, sprite);
		this.directionX = dX;
		this.directionY = dY;
	}

	public void tick() {
		x += directionX * speed;
		x += directionY * speed;
		curLife ++;
		if(curLife == life) {
			Game.bulletShoots.remove(this);
			return;
		}
	}

	public void render(Graphics graphics) {
		graphics.setColor(Color.ORANGE);
		graphics.fillOval(this.getX() - Camera.x, this.getY() - Camera.y, width, height);
	}
}
