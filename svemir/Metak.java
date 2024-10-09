package svemir;

import java.awt.Color;
import java.awt.Graphics;

public class Metak 
{
	private int x, y, r;
	private Color boja;
	
	public Metak(int x, int y, int r, Color boja) {
		this.boja = boja;
		this.x = x;
		this.y = y;
		this.r = r;
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

	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}

	public Color getBoja() {
		return boja;
	}

	public void setBoja(Color boja) {
		this.boja = boja;
	}
	
	
	public void crtaj(Graphics g) {
		g.setColor(boja);
		g.fillOval(x,y,r,r);

	}
	
}
