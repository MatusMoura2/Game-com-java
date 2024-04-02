package com.furiosnerd.entity;

import java.awt.image.BufferedImage;

import com.furiosnerd.main.Game;
import com.furiosnerd.map.Map;

public class Enemy extends Entity {

	private double speed = 0.5;

	public Enemy(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
	}

	public void spin() {
		if (x < Game.player.getX() && Map.isFree((int) (x + speed), this.getY())) {
			x += speed;
		} else if (x > Game.player.getX() && Map.isFree((int) (x - speed), this.getY())) {
			x -= speed;
		}

		if (y < Game.player.getY() && Map.isFree(this.getY(), (int) (y + speed))) {
			y += speed;
		} else if (y > Game.player.getY() && Map.isFree(this.getY(), (int)(y-speed))) {
			y -= speed;
		}
	}

}
