package svemir;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.*;
import java.io.IOException;

import javax.imageio.ImageIO;
public class KometaSrednja extends NebeskoTelo {
	
	public KometaSrednja(int x, int y, Color boja, double r) {
		super(x, y, boja, r);
		kometaSlika();
	}
	public BufferedImage kometaSrednja;
	void kometaSlika() {
		try {
			kometaSrednja=ImageIO.read(getClass().getResourceAsStream("/kometeSlike/asteroidSrednji.png"));
		} catch (IOException e) { }
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(kometaSrednja, x, y, 110, 90, null);
	}
}
