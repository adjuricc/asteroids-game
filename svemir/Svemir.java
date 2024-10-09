package svemir;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;
public class Svemir extends JPanel implements Runnable{
	private Thread nit;
	private Generator generator;
	ArrayList<NebeskoTelo> nebeskaTela = new ArrayList<>();
	
	private Igrac igrac;
	private Metak metak;
	private int kliknutoX, kliknutoY;
	private float igracSpeed;
	private float igracAccel;
	private int step = 10;
	private Simulator simulator;
	private ArrayList<NebeskoTelo> brisani = new ArrayList<>();
	private boolean inputLevo, inputDesno;
	private boolean izgubio = false, udaren = false, udario = false;
	public Svemir(Generator g, Simulator simulator) {
		this.simulator=simulator;
		setBackground(Color.BLACK);
		setDoubleBuffered(true);
		setFocusable(true);
		generator = g;
		igrac = new Igrac(600 / 2 - 30, 800 - 150, 60, 60);	

		this.addKeyListener(new KeyAdapter() 
		{
			
			public void keyReleased(KeyEvent e)
			{
				char dugme = Character.toUpperCase(e.getKeyChar());
				int novoX;
				getGraphics().clearRect(igrac.getX(), igrac.getY(), 60, 60);
				switch(dugme)
				{
					case KeyEvent.VK_A:
						inputLevo = false;
						
						//novoX = igrac.getX() - 10;
						//if (novoX >= 0) igrac.setX(novoX);
						break;
					case KeyEvent.VK_D:
						inputDesno = false;
						
						//novoX = igrac.getX() + 10;
						//if (novoX <= 600 - 60) igrac.setX(novoX);
						break;
					default:
						break;
				}
			}
			
			public void keyPressed(KeyEvent e) 
			{
				char dugme = Character.toUpperCase(e.getKeyChar());
				int novoX;
				getGraphics().clearRect(igrac.getX(), igrac.getY(), 60, 60);
				switch(dugme)
				{
					case KeyEvent.VK_A:
						inputLevo = true;
						
						break;
					case KeyEvent.VK_D:
						inputDesno = true;
						
						break;
					default:
						break;
				}
				
				igrac.crtaj(getGraphics());
			}
		});
		addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				metak = new Metak(igrac.getX() + 30, 800 - 150, 10, Color.RED);
				metak.crtaj(getGraphics());
				kliknutoX = e.getX();
				kliknutoY = e.getY();
			}
		});	
	}
	public synchronized void prodrmaj() {
		for (NebeskoTelo nt : nebeskaTela) {
			nt.izmeniX(20);
		}
		repaint();
		try {
			Thread.sleep(33);
		} catch (InterruptedException e) {}
		for (NebeskoTelo nt : nebeskaTela) {
			nt.izmeniX(-20);
		}
		repaint();
		try {
			Thread.sleep(33);
		} catch (InterruptedException e) {}
		for (NebeskoTelo nt : nebeskaTela) {
			nt.izmeniX(-20);
		}
		repaint();
		try {
			Thread.sleep(33);
		} catch (InterruptedException e) {}
		for (NebeskoTelo nt : nebeskaTela) {
			nt.izmeniX(20);
		}
		repaint();
		try {
			Thread.sleep(33);
		} catch (InterruptedException e) {}
	}
	
	public synchronized void pokreni() {
		nit = new Thread(this);
		nit.start();
	}
	@Override
	public void paint(Graphics g) {
		float inputubrzanje = 2;
		
		if (inputLevo)
		{
			igracAccel = -inputubrzanje;
			//igrac.setX(igrac.getX()-10);
			//if (igrac.getX()<0) igrac.setX(0);
		}
		else if (inputDesno)
		{
			igracAccel = inputubrzanje;
			//igrac.setX(igrac.getX()+10);
			//if (igrac.getX()>600-60) igrac.setX(600-60);
		}
		else igracAccel = 0;
		
		igracSpeed += igracAccel;
		igrac.setX((int)(igrac.getX()+igracSpeed));
		
		if (igrac.getX()<0) 
		{ 
			igrac.setX(0); 
			igracSpeed = 0; 
		}
		if (igrac.getX()>600-60) 
		{ 
			igrac.setX(600-60); 
			igracSpeed = 0; 
		}
		
		igracSpeed *= .8;
		if (igracSpeed > -0.1 && igracSpeed < 0.1)
			igracSpeed = 0;
		
		super.paint(g);
		synchronized (nebeskaTela) {
			
		for (NebeskoTelo nt : nebeskaTela ) {
			nt.paint(g);
			nt.izmeniY(5);
			igrac.crtaj(g);
			if (udaren || udario) continue;
			double rastojanje=Math.sqrt(((nt.x+nt.r)-(igrac.getX()+igrac.r))*((nt.x+nt.r)-(igrac.getX()+igrac.r))+((nt.y+nt.r)-(igrac.getY()+igrac.r))*((nt.y+nt.r)-(igrac.getY()+igrac.r)));
			long prviTren = System.currentTimeMillis();
			if(rastojanje<=igrac.r+nt.r) {
				brisani.add(nt);
				setBackground(Color.RED);
				validate();
				udaren = true;
				
				
				if(nt instanceof KometaMala) {
					igrac.zivoti-=5;
				}
				else if(nt instanceof KometaSrednja) {
					igrac.zivoti-=10;
				}
				else {
					igrac.zivoti-=15;
				}
				 
				if (igrac.zivoti<70) simulator.zivoti.setForeground(Color.YELLOW);
				if (igrac.zivoti<30) simulator.zivoti.setForeground(Color.RED);
				
			
				simulator.zivoti.setText(Integer.toString(igrac.zivoti)+ "               ");		
			}
			if (igrac.zivoti <= 0) 
			{	
				g.setColor(Color.BLACK);
				g.fillRect(0, 0, 600, 800);
				g.setColor(Color.WHITE);
				g.setFont(simulator.newFont.deriveFont(30f));
				g.drawString("Kraj igre! :(", 185, 310);
				validate();
				izgubio=true;
				return;
			}
			
			
			if(nt.dohvY()==800) 
			{
				if(nt instanceof KometaMala) {
					igrac.poeni-=5;
				}
				else if(nt instanceof KometaSrednja) {
					igrac.poeni-=10;
				}
				else {
					igrac.poeni-=15;
				}
				simulator.poeni.setText(Integer.toString(igrac.poeni)+ "               ");	
			}
				if (igrac.poeni < 0) 
				{	
					
					g.setColor(Color.BLACK);
					g.fillRect(0, 0, 600, 800);
					g.setColor(Color.WHITE);
					g.setFont(simulator.newFont.deriveFont(30f));
					g.drawString("Kraj igre! :(", 185, 310);
					validate();
					izgubio=true;
					return;
					
				}
			
		}
		
		for (NebeskoTelo nt : brisani) {
			nebeskaTela.remove(nt);
		}
		if (metak != null)
		{
			int xInkrement = (kliknutoX - metak.getX())/10;
			int yInkrement = (kliknutoY - metak.getY())/10;
			while (metak != null && metak.getX()<=600 && metak.getX()>=0 && metak.getY()>=0) 
			{
				metak.setX(metak.getX() + xInkrement);
				metak.setY(metak.getY() + yInkrement);
				metak.crtaj(g);
				
				if (!udario)
				{
					for (int i = 0; i < nebeskaTela.size(); i++)
					{
						int centarMetkaX = metak.getX() + metak.getR();
						int centarMetkaY = metak.getY() + metak.getR();
						int centarTelaX = nebeskaTela.get(i).x + (int)nebeskaTela.get(i).r;
						int centarTelaY = nebeskaTela.get(i).y + (int)nebeskaTela.get(i).r;
						double sumaPoluprecnika = metak.getR() + nebeskaTela.get(i).r;
						
						double rastojanjePoluprecnika = Math.sqrt((Math.pow((centarMetkaX - centarTelaX), 2.0) + Math.pow((centarMetkaY - centarTelaY), 2)));
						
						if(rastojanjePoluprecnika<sumaPoluprecnika) 
						{
							
							if (nebeskaTela.get(i) instanceof KometaVelika)
							{
								nebeskaTela.add(new KometaMala(nebeskaTela.get(i).x - 35, nebeskaTela.get(i).y + 10, null, 35));
								nebeskaTela.add(new KometaMala(nebeskaTela.get(i).x + 35, nebeskaTela.get(i).y + 10, null, 35));
								igrac.dodajPoene(30);
							}
							else if (nebeskaTela.get(i) instanceof KometaSrednja)
							{
								nebeskaTela.add(new KometaMala(nebeskaTela.get(i).x, nebeskaTela.get(i).y, null, 35));
								igrac.dodajPoene(20);
							}
							else
							{
								igrac.dodajPoene(10);
							}
							
							nebeskaTela.remove(nebeskaTela.get(i));
							setBackground(Color.WHITE);
							udario = true;
							
							simulator.poeni.setText(Integer.toString(igrac.poeni)+ "               ");	
						}
					}
				}
				else
				{
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {}
					metak = null;
					break;	
					}		
				}
			}
		}
	}
	
	public synchronized void zavrsi() {
		if(nit != null) {
			nit.interrupt();
		}
		while (nit != null) {
			try {
				wait();
			} catch (InterruptedException e) {}
		}
	}
	
	@Override
	public void run() {
		while (!nit.isInterrupted()) {
			unapredi();
			repaint();
			if (udaren || udario) {
				prodrmaj();
				setBackground(Color.BLACK);
				if (udaren) udaren = false;
				if (udario) udario = false;
			}
			if (izgubio) {
				zavrsi();
			}
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {}
		}
	}

	private synchronized void unapredi() {
		synchronized (nebeskaTela) {
			nebeskaTela = generator.ntD;
		}
		
			
	}

}
