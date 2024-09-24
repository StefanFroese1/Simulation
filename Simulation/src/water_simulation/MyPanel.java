package water_simulation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class MyPanel extends JPanel implements ActionListener, MouseListener, KeyListener{
	
	private Timer timer;
	private Water w = new Water();
	private Image slider;
	private Image tab;
	private int slider1, slider2, slider3;
	private int gridWidth = 160;
	private int gridHight = 90;
	private int pixelSize = 10;
	private boolean running;
	private boolean leftMouseDown;
	
	MyPanel(){
		this.setBackground(Color.cyan);
		running = false;
		leftMouseDown = false;
		slider1 = 600;
		slider2 = 600;
		slider3 = 600;
		tab = new ImageIcon("Assets/Tab.png").getImage();
		slider = new ImageIcon("Assets/Sliders3.png").getImage();
		timer = new Timer(50, this);
		timer.start();
	}
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2D = (Graphics2D) g;
		for (int i=0;i<gridWidth;i++) {
			for (int i2=gridHight-1;i2>0;i2--) {
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
		g2D.drawImage(slider, 0, 0, 1535, 150, null);
		g2D.drawImage(tab, slider1-15, 5, 30, 40, null);
		g2D.drawImage(tab, slider2-15, 55, 30, 40, null);
		g2D.drawImage(tab, slider3-15, 105, 30, 40, null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(getMousePosition() != null && leftMouseDown) {
			if(getMousePosition().getY() > 0 && getMousePosition().getY() < 50 && getMousePosition().getX() > 25 && getMousePosition().getX() < 1280) {
				slider1 = (int) getMousePosition().getX();
			}else if(getMousePosition().getY() > 50 && getMousePosition().getY() < 100 && getMousePosition().getX() > 25 && getMousePosition().getX() < 1280){
				slider2 = (int) getMousePosition().getX();
			}else if(getMousePosition().getY() > 100 && getMousePosition().getY() < 150 && getMousePosition().getX() > 25 && getMousePosition().getX() < 1280){
				slider3 = (int) getMousePosition().getX();
			}
		}
		if(running) {
			w.SetTide(slider1/15);
			w.SetWave(slider2/130);
			//w.doTick
		}
		repaint();
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		//
	}
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) {
			leftMouseDown = true;
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) {
			leftMouseDown = false;
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == 32) {
			if(running) {
				running = false;
			}else {
				running = true;
			}
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}

