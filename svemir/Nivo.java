package svemir;

public class Nivo {
	int broj;
	double verovatnocaVelika;
	double verovatnocaMala;
	int sleepTime;
	
	public Nivo(int br, double vk,  double vm, int st) {
		verovatnocaVelika = vk;
		verovatnocaMala = vm;
		sleepTime = st;
		br = broj;
	}

	public int getBroj() {
		return broj;
	}

	public void setBroj(int broj) {
		this.broj = broj;
	}

	public double getVerovatnocaVelika() {
		return verovatnocaVelika;
	}

	public void setVerovatnocaVelika(double verovatnocaVelika) {
		this.verovatnocaVelika = verovatnocaVelika;
	}

	public double getVerovatnocaMala() {
		return verovatnocaMala;
	}

	public void setVerovatnocaMala(double verovatnocaMala) {
		this.verovatnocaMala = verovatnocaMala;
	}

	public int getSleepTime() {
		return sleepTime;
	}

	public void setSleepTime(int sleepTime) {
		this.sleepTime = sleepTime;
	}

}
