package svemir;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Igrac  {
	private int x, y, sirina, visina;
	public int zivoti=100;
	private Color boja;
	public int poeni = 0;
	public double r=21;
	public Igrac(int x, int y, int sirina, int visina)
	{
		this.x = x;
		this.y = y;
		this.sirina = sirina;
		this.visina = visina;
		this.zivoti = zivoti;
		this.boja = boja;
		igracSlika();
	}
	
	public BufferedImage igracSlika;
	void igracSlika() {
		try {
			igracSlika=ImageIO.read(getClass().getResourceAsStream("/kometeSlike/spaceshipKonacni.png"));
		} catch (IOException e) { }
	}

	public void crtaj(Graphics g) 
	{
		g.drawImage(igracSlika, x, y, 60, 42, null);
	}


	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}


	public int getSirina() {
		return sirina;
	}


	public void setSirina(int sirina) {
		this.sirina = sirina;
	}


	public int getVisina() {
		return visina;
	}


	public void setVisina(int visina) {
		this.visina = visina;
	}


	public int getZivoti() {
		return zivoti;
	}


	public void setZivoti(int zivoti) {
		this.zivoti = zivoti;
	}


	public Color getBoja() {
		return boja;
	}


	public void setBoja(Color boja) {
		this.boja = boja;
	}
	
	public void dodajPoene(int br)
	{
		poeni += br;
	}
	
	
}
