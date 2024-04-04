package com.furiosnerd.entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.furiosnerd.main.Game;
import com.furiosnerd.map.Map;

public class Enemy extends Entity {

	private double speed = 0.5;

	public Enemy(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
	}

	public void spin() {
		if ((int)x < Game.player.getX() && Map.isFree((int) (x + speed), this.getY())
				&& !isColliding((int) (x + speed), this.getY())) {
			x += speed;
		} else if ((int)x > Game.player.getX() && Map.isFree((int) (x - speed), this.getY())
				&& !isColliding((int) (x - speed), this.getY())) {
			x -= speed;
		}

		if ((int)y < Game.player.getY() && Map.isFree(this.getX(), (int) (y + speed))
				&& !isColliding(this.getX(), (int) (y + speed))) {
			y += speed;
		} else if ((int)y > Game.player.getY() && Map.isFree(this.getX(), (int)(y-speed))
				&& !isColliding(this.getX(), (int)(y-speed))) {
			y -= speed;
		}
	}
	
	public boolean isColliding(int xnext, int ynext) {
		Rectangle enemyRect = new Rectangle(xnext,ynext, Map.TILE_SIZE,Map.TILE_SIZE);
		for(int i = 0; i < Game.enemys.size(); i++) {
			Enemy e = Game.enemys.get(i);
			if(e == this) {
				continue;
			}
			Rectangle targetEnemy = new Rectangle(e.getX(),e.getY(), Map.TILE_SIZE,Map.TILE_SIZE);
			if(enemyRect.intersects(targetEnemy)) {
				return true;
			
			}
		}
		return false;
	}

}
