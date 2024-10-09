package svemir;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.*;
import java.io.IOException;

import javax.imageio.ImageIO;
public class KometaMala extends NebeskoTelo {
	
	public KometaMala(int x, int y, Color boja, double r) {
		super(x, y, boja, r);
		kometaSlika();
	}
	public BufferedImage kometaMala;
	void kometaSlika() {
		try {
			kometaMala=ImageIO.read(getClass().getResourceAsStream("/kometeSlike/asteroid2.png"));
		} catch (IOException e) { }
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(kometaMala, x, y, 70, 57, null);
	}
}
