package com.furiosnerd.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.furiosnerd.graphics.Spritesheet;
import com.furiosnerd.main.Game;
import com.furiosnerd.map.Camera;
import com.furiosnerd.map.Map;

public class Player extends Entity {

	public int rightDir = 0, leftDir = 1;
	public int dir = rightDir;

	public double life = 100, maxLife = 100;

	public boolean right, up, left, down;
	public double speed = 1.2;

	private int frames = 0, maxFrames = 5, index = 0, maxIndex = 3;
	private boolean moved = false;

	private BufferedImage[] rightPlayer;
	private BufferedImage[] leftPlayer;

	public static int ammunition = 0;

	private BufferedImage sufferingPlayer;

	private boolean hasGun = false;

	public boolean isDamaged = false;
	private int damageFrames = 0;

	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);

		rightPlayer = new BufferedImage[4];
		leftPlayer = new BufferedImage[4];
		sufferingPlayer = Game.spritesheet.getSprite(16, 32, 16, 16);

		for (int i = 0; i < 4; i++) {
			rightPlayer[i] = Game.spritesheet.getSprite(32 + (i * 16), 0, 16, 16);
		}
		for (int i = 0; i < 4; i++) {
			leftPlayer[i] = Game.spritesheet.getSprite(32 + (i * 16), 16, 16, 16);
		}
	}

	public void spin() {
		moved = false;
		if (right && Map.isFree((int) (x + speed), this.getY())) {
			moved = true;
			dir = rightDir;
			x += speed;
		} else if (left && Map.isFree((int) (x - (speed)), this.getY())) {
			moved = true;
			dir = leftDir;
			x -= speed;
		}
		if (up && Map.isFree(this.getX(), (int) (y - speed))) {
			moved = true;
			y -= speed;
		} else if (down && Map.isFree(this.getX(), (int) (y + speed))) {
			moved = true;
			y += speed;
		}
		if (moved) {
			frames++;
			if (frames == maxFrames) {
				frames = 0;
				index++;
				if (index > maxIndex) {
					index = 0;
				}
			}

			this.checkCollisionLifePack();
			this.ammunitionCollision();
			this.gunCollision();

			if (isDamaged) {
				this.damageFrames++;
				if (this.damageFrames == 15) {
					this.damageFrames = 0;
					isDamaged = false;
				}
			}
			if (life <= 0) {
				Game.entitys = new ArrayList<Entity>();
				Game.enemys = new ArrayList<Enemy>();

				Game.spritesheet = new Spritesheet("/spritesheet.png");

				Game.player = new Player(0, 0, 16, 16, Game.spritesheet.getSprite(32, 0, 16, 16));

				Game.entitys.add(Game.player);

				Game.map = new Map("/map.png");

				return;
			}

			Camera.x = Camera.clamp(this.getX() - (Game.WIDTH / 2), 0, Map.WIDTH * 16 - Game.WIDTH);
			Camera.y = Camera.clamp(this.getY() - (Game.HEIGHT / 2), 0, Map.HEIGHT * 16 - Game.HEIGHT);
		}
	}

	public void ammunitionCollision() {
		for (int i = 0; i < Game.entitys.size(); i++) {
			Entity current = Game.entitys.get(i);
			if (current instanceof Weapon) {
				if (Entity.isColidding(this, current)) {
					hasGun = true;

					System.out.println("AAAA");
					Game.entitys.remove(current);
				}
			}
		}
	}

	public void gunCollision() {
		for (int i = 0; i < Game.entitys.size(); i++) {
			Entity current = Game.entitys.get(i);
			if (current instanceof Bullet) {
				if (Entity.isColidding(this, current)) {
					ammunition++;
					Game.entitys.remove(current);
				}
			}
		}
	}

	public void checkCollisionLifePack() {
		for (int i = 0; i < Game.entitys.size(); i++) {
			Entity current = Game.entitys.get(i);
			if (current instanceof LifePack) {
				if (Entity.isColidding(this, current)) {
					life += 15;
					if (life > 100) {
						life = 100;
					}
					Game.entitys.remove(current);
				}
			}
		}
	}

	public void render(Graphics graphics) {
		if (!isDamaged) {
			if (dir == rightDir) {
				graphics.drawImage(rightPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
				if (hasGun) {// arma para direita
					graphics.drawImage(Entity.GUN_RIGHT, (this.getX() + 10) - Camera.x, this.getY() - Camera.y, null);
				}

			} else if (dir == leftDir) {
				graphics.drawImage(leftPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
				if (hasGun) {// arma para a esquerda
					graphics.drawImage(Entity.GUN_LEFT, (this.getX() - 10) - Camera.x, this.getY() - Camera.y, null);
				}
			}

		} else {
			graphics.drawImage(sufferingPlayer, this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
	}

}
