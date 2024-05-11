package com.furiosnerd.graphics;

import java.awt.Color;
import java.awt.Graphics;

import com.furiosnerd.entity.Player;

public class UI {

	public void render(Graphics graphics) {
		graphics.setColor(Color.gray);
		graphics.fillRect(3, 4,50, 7);
		graphics.setColor(Color.red);
		graphics.fillRect(3, 4, (int)((Player.life/Player.maxLife)*50), 7);
		
	}
}
