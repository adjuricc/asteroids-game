package svemir;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.*;
import java.io.IOException;

import javax.imageio.ImageIO;
public class KometaVelika extends NebeskoTelo {
	
	public KometaVelika(int x, int y, Color boja, double r) {
		super(x, y, boja, r);
		kometaSlika();
	}
	public BufferedImage kometaVelika;
	void kometaSlika() {
		try {
			kometaVelika=ImageIO.read(getClass().getResourceAsStream("/kometeSlike/asteroidVeliki.png"));
		} catch (IOException e) { }
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(kometaVelika, x, y, 150, 122, null);
	}
}
