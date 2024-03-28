package com.furiosnerd.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.furiosnerd.main.Game;
import com.furiosnerd.map.Camera;
import com.furiosnerd.map.Map;

public class Player extends Entity {

	public int rightDir = 0, leftDir = 1;
	public int dir = rightDir;

	public boolean right, up, left, down;
	public double speed = 1.2;

	private int frames = 0, maxFrames = 5, index = 0, maxIndex = 3;
	private boolean moved = false;

	private BufferedImage[] rightPlayer;
	private BufferedImage[] leftPlayer;

	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);

		rightPlayer = new BufferedImage[4];
		leftPlayer = new BufferedImage[4];

		for (int i = 0; i < 4; i++) {
			rightPlayer[i] = Game.spritesheet.getSprite(32 + (i * 16), 0, 16, 16);
		}
		for (int i = 0; i < 4; i++) {
			leftPlayer[i] = Game.spritesheet.getSprite(32 + (i * 16), 16, 16, 16);
		}
	}

	public void spin() {
		moved = false;
		if (right && Map.isFree((int) (x+speed), this.getY())) {
			moved = true;
			dir = rightDir;
			x += speed;
		} else if (left && Map.isFree((int)(x-(speed)), this.getY())) {
			moved = true;
			dir = leftDir;
			x -= speed;
		}
		if (up && Map.isFree(this.getX(), (int)(y- speed))) {
			moved = true;
			y -= speed;
		} else if (down && Map.isFree(this.getX(), (int)(y+ speed))) {
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

			Camera.x = Camera.clamp(this.getX() - (Game.WIDTH / 2), 0, Map.WIDTH * 16 - Game.WIDTH);
			Camera.y = Camera.clamp(this.getY() - (Game.HEIGHT / 2), 0, Map.HEIGHT * 16 - Game.HEIGHT);
		}
	}

	public void render(Graphics graphics) {
		if (dir == rightDir) {
			graphics.drawImage(rightPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);

		} else if (dir == leftDir) {
			graphics.drawImage(leftPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);

		} else {

		}
	}

}
