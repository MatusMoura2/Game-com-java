package com.furiosnerd.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.furiosnerd.main.Game;
import com.furiosnerd.map.Camera;
import com.furiosnerd.map.Map;

public class Enemy extends Entity {

	private double speed = 0.5;
	private int maskx = 8, masky = 8, maskw = 6, maskh = 6;

	public Enemy(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
	}

	public void spin() {
		if ((int) x < Game.player.getX() && Map.isFree((int) (x + speed), this.getY())
				&& !isColliding((int) (x + speed), this.getY())) {
			x += speed;
		} else if ((int) x > Game.player.getX() && Map.isFree((int) (x - speed), this.getY())
				&& !isColliding((int) (x - speed), this.getY())) {
			x -= speed;
		}

		if ((int) y < Game.player.getY() && Map.isFree(this.getX(), (int) (y + speed))
				&& !isColliding(this.getX(), (int) (y + speed))) {
			y += speed;
		} else if ((int) y > Game.player.getY() && Map.isFree(this.getX(), (int) (y - speed))
				&& !isColliding(this.getX(), (int) (y - speed))) {
			y -= speed;
		}
	}

	public boolean isColliding(int xnext, int ynext) {
		Rectangle enemyRect = new Rectangle(xnext + maskx, ynext + masky, maskw, maskh);
		for (int i = 0; i < Game.enemys.size(); i++) {
			Enemy e = Game.enemys.get(i);
			if (e == this) {
				continue;
			}
			Rectangle targetEnemy = new Rectangle(e.getX() + maskx, e.getY() + masky, maskw, maskh);
			if (enemyRect.intersects(targetEnemy)) {
				return true;

			}
		}
		return false;
	}

	//para ver a dimanção do
	public void render(Graphics g) {
		super.render(g);
		g.setColor(Color.BLUE);
		g.fillRect(this.getX() + maskx - Camera.x, this.getY() + masky - Camera.y, maskw, maskh);
	}

}
