package water_simulation;

import java.awt.Frame;

import javax.swing.JFrame;

public class MyFrame extends JFrame {
	MyPanel panel;

	MyFrame() {
		// create a panel
		panel = new MyPanel();

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
		this.add(panel);
		this.pack();
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setLocationRelativeTo(null);
		this.addMouseListener(panel);
		this.addKeyListener(panel);
		this.setVisible(true);
	}

}