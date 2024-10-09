package svemir;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Objekat {
	protected int x, y;
	protected Color boja;
	Objekat(int x, int y, Color boja){
		this.x=x;
		this.y=y;
		this.boja=boja;
	}
	public int dohvX() {
		return x;
	}

	public void izmeniX(int x) {
		this.x += x;
	}

	public int dohvY() {
		return y;
	}

	public void izmeniY(int y) {
		this.y +=y;
	}
	
	public abstract void paint(Graphics g);
}
