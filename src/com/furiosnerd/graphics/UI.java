package com.furiosnerd.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.furiosnerd.main.Game;

public class UI {

	public void render(Graphics graphics) {
		graphics.setColor(Color.gray);
		graphics.fillRect(3, 4, 50, 7);
		graphics.setColor(Color.red);
		graphics.fillRect(3, 4, (int) ((Game.player.life / Game.player.maxLife) * 50), 7);

		graphics.setColor(Color.orange);
		graphics.setFont(new Font("arial", Font.PLAIN, 8));
		graphics.drawString((int) Game.player.life + " / " + (int) Game.player.maxLife, 18, 10);

	}
}
