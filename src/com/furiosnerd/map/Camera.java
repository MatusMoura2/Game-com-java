package com.furiosnerd.map;

public class Camera {
	public static int x;
	public static int y;

	public static int clamp(int xAtual, int xMin, int xMax) {
		if (xAtual < xMin) {
			xAtual = xMin;
		}

		if (xAtual > xMax) {
			xAtual = xMax;
		}
		return xAtual;
	}

}
