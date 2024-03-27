package com.furiosnerd.map;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.furiosnerd.entity.Bullet;
import com.furiosnerd.entity.Enemy;
import com.furiosnerd.entity.Entity;
import com.furiosnerd.entity.LifePack;
import com.furiosnerd.entity.Weapon;
import com.furiosnerd.main.Game;

public class Map {

	private Tile[] tiles;

	public static int WIDTH, HEIGHT;

	public Map(String path) {
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			int[] pixels = new int[map.getWidth() * map.getHeight()];

			WIDTH = map.getWidth();
			HEIGHT = map.getHeight();

			tiles = new Tile[map.getWidth() * map.getHeight()];
			map.getRGB(0, 0, map.getWidth(), map.getHeight(), pixels, 0, map.getWidth());

			for (int x = 0; x < map.getWidth(); x++) {
				for (int y = 0; y < map.getHeight(); y++) {
					int pixelHere = pixels[x + (y * map.getWidth())];
					tiles[x + (y * WIDTH)] = new FloorTile(x * 16, y * 16, Tile.TILE_FLOOR);
					if (pixelHere == 0xFF000000) {
						tiles[x + (y * WIDTH)] = new FloorTile(x * 16, y * 16, Tile.TILE_FLOOR);

					} else if (pixelHere == 0xFFFFFFFF) {
						tiles[x + (y * WIDTH)] = new FloorTile(x * 16, y * 16, Tile.TILE_WALL);

					} else if (pixelHere == 0xFF0026FF) {// Player

						Game.player.setX(x * 16);
						Game.player.setY(y * 16);

					} else if (pixelHere == 0xFFFF0000) {// Enemy

						Game.entitys.add(new Enemy(x * 16, y * 16, 16, 16, Entity.ENEMY_EN));

					} else if (pixelHere == 0xFFFF00DC) {// Weapon

						Game.entitys.add(new Weapon(x * 16, y * 16, 16, 16, Entity.WEAPON_EN));

					} else if (pixelHere == 0xFF00FF04) {// Life Pack

						Game.entitys.add(new LifePack(x * 16, y * 16, 16, 16, Entity.LIFEPACK_EN));

					} else if (pixelHere == 0xFFF2FF00) {// Bullet

						Game.entitys.add(new Bullet(x * 16, y * 16, 16, 16, Entity.BULLET_EN));

					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void render(Graphics g) {

		int camPositionX = Camera.x >> 4;
		int camPositionY = Camera.y >> 4;

		int camPositionFinalX = camPositionX + (Game.WIDTH >> 4);
		int camPositionFinalY = camPositionY + (Game.HEIGHT >> 4);

		for (int x = camPositionX; x <= camPositionFinalX; x++) {
			for (int y = camPositionY; y <= camPositionFinalY; y++) {
				if(x < 0|| y<0||x>= WIDTH || y>= HEIGHT)
					continue;
				Tile tile = tiles[x + (y * WIDTH)];
				tile.render(g);
			}
		}
	}

}
