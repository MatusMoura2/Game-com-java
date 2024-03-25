package com.furiosnerd.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.furiosnerd.main.Game;

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
		if (right) {
			moved = true;
			dir = rightDir;
			x += speed;
		} else if (left) {
			moved = true;
			dir = leftDir;
			x -= speed;
		}
		if (up) {
			moved = true;
			y -= speed;
		} else if (down) {
			moved = true;
			y += speed;
		}
		if(moved) {
			frames ++;
			if(frames == maxFrames) {
				frames = 0;
				index++;
				if(index > maxIndex) {
					index = 0;
				}
			}
		}
	}

	public void render(Graphics graphics) {
		if (dir == rightDir) {
			graphics.drawImage(rightPlayer[index], this.getX(), this.getY(), null);

		} else if (dir == leftDir) {
			graphics.drawImage(leftPlayer[index], this.getX(), this.getY(), null);

		} else {

		}
	}

}
