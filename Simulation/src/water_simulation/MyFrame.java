package water_simulation;

import javax.swing.JFrame;

public class MyFrame extends JFrame{
	MyPanel panel;
	
	MyFrame(){
		panel = new MyPanel();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setUndecorated(true);
		this.add(panel);
		this.pack();
		this.setVisible(true);
	}

}