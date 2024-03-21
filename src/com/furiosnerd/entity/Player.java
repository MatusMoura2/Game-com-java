package com.furiosnerd.entity;

import java.awt.image.BufferedImage;

public class Player  extends Entity{
	
	public boolean right, up, left, down;
	public double speed = 1.2;

	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
	}
	
	public void spin() {
		if(right) {
			x+=speed;
		}else if(left) {
			x-=speed;
		}
		if(up) {
			y-=speed;
		}else if(down) {
			y+=speed;
		}
	}

}
