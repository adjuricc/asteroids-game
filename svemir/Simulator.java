package svemir;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Simulator extends JFrame{
	private Panel bottomPanel;
	public Font newFont;
	private Generator generator = new Generator();
	private Svemir svemir = new Svemir(generator, this);
	JPanel topPanel=new JPanel();
	JLabel zivoti=new JLabel(), poeni=new JLabel(), nivo=new JLabel();
	Label vreme=new Label("");
	Button pokreni;
	Timer timer = new Timer(vreme);
	
	public Simulator() {
		setBounds(300, 400, 600, 800);
		setResizable(false);
		setTitle("Svemir");
		napunitiWindow();
		addWindowListener(new WindowAdapter(){
				@Override
				public void windowClosing(WindowEvent e) {
					generator.zavrsi();
					svemir.zavrsi();
					dispose();
				}
		});
		
		setVisible(true);
		
	}
	
	private void napunitiWindow() {
		newFont = new Font("Arial", Font.BOLD, 20);
		try {
			InputStream is = getClass().getResourceAsStream("/fonts/minecraft_font.ttf");
			newFont = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException e) {
			
		} catch (IOException e) {}
		
		add(svemir, BorderLayout.CENTER);
		pokreni = new Button("Pokreni!");
		pokreni.addActionListener((ae)->{
			generator.pokreni();
			svemir.pokreni();
			timer.start();
			timer.go();
			pokreni.setEnabled(false);
		});
		bottomPanel = new Panel();
		bottomPanel.add(pokreni);
		bottomPanel.add(vreme);
		
		add(bottomPanel, BorderLayout.SOUTH);
		
		topPanel.setBackground(Color.black);
		JLabel labelZ= new JLabel("Zivoti: ");
		
		labelZ.setFont(newFont.deriveFont(20f));
		labelZ.setForeground(Color.white);
		System.out.println(labelZ.getFont().getFamily());
		
		topPanel.add(labelZ);
		zivoti.setText("100              ");
		zivoti.setForeground(Color.green);
		zivoti.setFont(newFont.deriveFont(20f));
		topPanel.add(zivoti);
		
		JLabel labelP= new JLabel("Poeni: ");
		labelP.setFont(newFont.deriveFont(20f));
		labelP.setForeground(Color.white);
		topPanel.add(labelP);
		poeni.setForeground(Color.white);
		poeni.setText("0             ");
		poeni.setFont(newFont.deriveFont(20f));
		topPanel.add(poeni);
		
		nivo.setText("1");
		nivo.setFont(newFont.deriveFont(20f));
		JLabel labelN= new JLabel("Nivo: ");
		labelN.setForeground(Color.white);
		labelN.setFont(newFont.deriveFont(20f));
		topPanel.add(labelN);
		
		nivo.setForeground(Color.white);
		topPanel.add(nivo);
		add(topPanel, BorderLayout.NORTH);
	}

	public static void main(String[] args) {
		new Simulator();
	}

}
