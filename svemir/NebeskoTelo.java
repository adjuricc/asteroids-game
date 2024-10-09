package svemir;

import java.awt.Color;

public abstract class NebeskoTelo extends Objekat{
	double r;
	public NebeskoTelo(int x, int y, Color boja, double r) 
	{
		super(x, y, boja);
		this.r = r;
	}

	
	public int getX()
	{
		return this.x;
	}
	
	public int getY()
	{
		return this.y;
	}
	
	public int setY()
	{
		return this.y;
	}
	
	public int setX()
	{
		return this.x;
	}
}
