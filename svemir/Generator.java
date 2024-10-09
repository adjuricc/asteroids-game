package svemir;

import java.awt.Color;
import java.util.ArrayList;

public class Generator extends Thread {
	double verovatnocaVelika;
	double verovatnocaMala;
	int sleepTime;
	ArrayList<Nivo> nivoi = new ArrayList<>();
	{
		nivoi.add(new Nivo(1, 0.5, 0.2, 1000));
		
	}
	ArrayList<NebeskoTelo> ntD;
	public Generator() {
		ntD = new ArrayList<>();
	}
	public synchronized void pokreni() {
		start();
	}
	
	public synchronized void zavrsi() {
		interrupt();
	}
	

	@Override
	public void run() {
			while (!isInterrupted()) {
				int i = 0;
				double slucajniBroj = Math.random();
				synchronized (ntD) {
					
					if (slucajniBroj>nivoi.get(i).getVerovatnocaMala()) ntD.add(new KometaMala((int) (Math.random() * 600), 0, Color.GRAY, 35));
					if (slucajniBroj>nivoi.get(i).getVerovatnocaMala() && slucajniBroj<=nivoi.get(i).getVerovatnocaVelika()) ntD.add(new KometaSrednja((int) (Math.random() * 600), 0, Color.GRAY, 55));
					if (slucajniBroj<=nivoi.get(i).getVerovatnocaVelika()) ntD.add(new KometaVelika((int) (Math.random() * 600), 0, Color.GRAY, 65));
					}
				
					try {
						sleep(nivoi.get(i).getSleepTime());
					} catch (InterruptedException e) { }
			}
		}

}
	