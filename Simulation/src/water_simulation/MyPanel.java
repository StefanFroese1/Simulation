package water_simulation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MyPanel extends JPanel implements ActionListener{
	
	private Timer timer;
	
	MyPanel(){
		this.setBackground(Color.cyan);
		timer = new Timer(50, this);
		timer.start();
		
		
	}
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2D = (Graphics2D) g;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
		
	}

}

