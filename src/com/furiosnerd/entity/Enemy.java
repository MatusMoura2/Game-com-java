package com.furiosnerd.entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.furiosnerd.main.Game;
import com.furiosnerd.map.Camera;
import com.furiosnerd.map.Map;

public class Enemy extends Entity {

	private double speed = 0.5;
	private int maskx = 8, masky = 8, maskw = 6, maskh = 6;
	private int frames = 0, maxFrames = 20, index = 0, maxIndex = 2;

	private BufferedImage[] spritesEnemy;

	public Enemy(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, null);
		spritesEnemy = new BufferedImage[3];
		spritesEnemy[0] = Game.spritesheet.getSprite(112, 0, 16, 16);
		spritesEnemy[1] = Game.spritesheet.getSprite(128, 0, 16, 16);
		spritesEnemy[2] = Game.spritesheet.getSprite(144, 0, 16, 16);
	}

	public void spin() {
		if (isCollidingWithPlay() == false) {
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
		} else {
			if (Game.ran.nextInt(100) < 10) {
				Game.player.life--;
				System.out.println(Game.player.life);
				Game.player.isDamaged = true;
				if (Game.player.life == 0) {
					System.exit(1);
					
				}
			}
		}
		frames++;
		if (frames == maxFrames) {
			frames = 0;
			index++;
			if (index > maxIndex) {
				index = 0;
			}
		}
	}

	public boolean isCollidingWithPlay() {
		Rectangle enemyRect = new Rectangle(this.getX() + maskx, this.getY() + masky, maskw, maskh);
		Rectangle player = new Rectangle(Game.player.getX(), Game.player.getY(), 16, 16);

		return enemyRect.intersects(player);
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

	/**
	 * para ver a dimanção da mascara de colisão basta tirar o comentario desse
	 * metodo.
	 */
	public void render(Graphics g) {
		g.drawImage(spritesEnemy[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
		/*
		 * g.setColor(Color.BLUE); g.fillRect(this.getX() + maskx - Camera.x,
		 * this.getY() + masky - Camera.y, maskw, maskh);
		 */
	}

}
