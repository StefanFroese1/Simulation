package water_simulation;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MyPanel extends JPanel implements ActionListener{
	
	private Timer timer;
	
	MyPanel(){
		this.setBackground(Color.cyan);
		timer = new Timer(50, this);
		timer.start();
		//test
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}

