package water_simulation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MyPanel extends JPanel implements ActionListener{
	
	private Timer timer;
	private Water w = new Water(0, 0);
	private Image slider;
	private Image tab;
	private int gridWidth = 160;
	private int gridHight = 90;
	private int pixelSize = 8;
	
	MyPanel(){
		this.setBackground(Color.cyan);
		tab = new ImageIcon("Assets/Tab.png").getImage();
		slider = new ImageIcon("Assets/Slider.png").getImage();
		timer = new Timer(50, this);
		timer.start();
		
		
	}
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2D = (Graphics2D) g;
		g2D.drawImage(slider, 0, 0, 100, 20, null);
		for (int i=0;i<gridWidth;i++) {
			for (int i2=0;i2<gridHight;i2++) {
				switch(w.GetPixel(i,i2)) {
					case 0: g2D.setColor(Color.cyan); break;
					case 1: g2D.setColor(Color.blue); break;
					case 2: g2D.setColor(Color.green); break;
					default: g2D.setColor(Color.white);
				}
				g2D.setColor(Color.green);
				g2D.fillRect(i*pixelSize, i2*pixelSize, pixelSize, pixelSize);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
		
	}

}

