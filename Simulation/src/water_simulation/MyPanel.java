package water_simulation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class MyPanel extends JPanel implements ActionListener, MouseListener{
	
	private Timer timer;
	private Water w = new Water(0, 0);
	private Image slider;
	private Image tab;
	private int slider1, slider2;
	private int gridWidth = 160;
	private int gridHight = 90;
	private int pixelSize = 8;
	
	MyPanel(){
		this.setBackground(Color.cyan);
		tab = new ImageIcon("Assets/Tab.png").getImage();
		slider = new ImageIcon("Assets/Sliders.png").getImage();
		timer = new Timer(50, this);
		timer.start();
		
		
	}
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2D = (Graphics2D) g;
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
		g2D.drawImage(slider, 0, 0, 1000, 200, null);
		g2D.drawImage(tab, slider1-30, 10, 60, 80, null);
		g2D.drawImage(tab, slider2-30, 110, 60, 80, null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getY() > 0 && e.getY() < 100 && e.getX() < 1000) {
			slider1 = e.getX();
		}else if(e.getY() > 100 && e.getY() < 200 && e.getX() < 1000){
			slider2 = e.getX();
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}

