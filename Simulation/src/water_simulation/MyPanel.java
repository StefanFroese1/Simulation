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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class MyPanel extends JPanel implements ActionListener, MouseListener, KeyListener {

	private Timer timer;
	private Water w;
	private Buildings[] b;
	private Image slider;
	private Image tab;
	private File text;
	private BufferedImage img;
	private BufferedImage[] textChars = new BufferedImage[11];
	private int tick, tickStep;
	private int slider1, slider2, slider3;
	private int gridWidth = 160;
	private int gridHight = 90;
	private int pixelSize = 10;
	private int price, digit;
	private boolean running;
	private boolean leftMouseDown;

	MyPanel() {
		this.setBackground(Color.gray);
		// create building array
		// xlocation ylocation xsize ysize strength
		b = new Buildings[3];
		b[0] = new Buildings(3, 42, 7, 31, 20);
		b[1] = new Buildings(21, 39, 9, 19, 20);
		b[2] = new Buildings(45, 30, 7, 7, 20);
		// object constructor
		w = new Water();
		timer = new Timer(5, this);
		// variable constructor
		running = false;
		leftMouseDown = false;
		slider1 = 550;
		slider2 = 300;
		slider3 = 800;
		tick = 0;
		price = 0;
		digit = 0;
		text = new File("Assets/Text4.png");
		tab = new ImageIcon("Assets/Tab.png").getImage();
		slider = new ImageIcon("Assets/Sliders6.png").getImage();
		img = null;
		// Image array constructor
		try {
			img = ImageIO.read(text);
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < 11; i++) {
			textChars[i] = img.getSubimage(i * 4, 0, 4, 7);
		}
		timer.start();
	}
	// draws everything on the panel
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2D = (Graphics2D) g;
		// draw grid of pixels
		for (int i = 0; i < gridWidth; i++) {
			for (int i2 = gridHight - 1; i2 > 0; i2--) {
				switch (w.getPixel(i, i2)) {
				case 0:
					g2D.setColor(Color.cyan);
					break;
				case 1:
					g2D.setColor(Color.blue);
					break;
				case 2:
					g2D.setColor(Color.green);
					break;
				default:
					g2D.setColor(Color.white);
				}
				g2D.fillRect(i * pixelSize, i2 * pixelSize, pixelSize, pixelSize);
			}
		}
		price = 0;
		// draw buildings
		for (int i = 0; i < b.length; i++) {
			if (b[i] != null) {
				price += b[i].getBroken();
				// draw one building
				for (int ix = 0; ix < b[i].getWidth(); ix++) {
					for (int iy = b[i].getHeight() - 1; iy > -1; iy--) {
						// draw building pixel
						g2D.setColor(Color.gray);
						if ((b[i].getLocation() + ix) % 2 == 0 && (90 - (b[i].getElevation() + iy)) % 3 != 0) {
							g2D.setColor(Color.yellow);
						}
						if (b[i].getDamage(ix, iy) > 0) {
							g2D.fillRect((b[i].getLocation() + ix) * pixelSize,
									(90 - (b[i].getElevation() + iy)) * pixelSize, pixelSize, pixelSize);
						}
					}
				}
			}
		}
		// draw sliders
		g2D.drawImage(slider, 0, 0, 1535, 200, null);
		g2D.drawImage(tab, slider1 - 15, 5, 30, 40, null);
		g2D.drawImage(tab, slider2 - 15, 55, 30, 40, null);
		g2D.drawImage(tab, slider3 - 15, 105, 30, 40, null);
		// draw damages cost
		g2D.drawImage(textChars[10], 1100, 160, 25, 35, null);
		for (int i = 0; i < String.valueOf(price).length(); i++) {
			digit = Integer.parseUnsignedInt(String.valueOf(price).substring(i, i + 1));
			g2D.drawImage(textChars[digit], i * 25 + 1125, 160, 25, 35, null);
		}
		if (price != 0) {
			for (int i = 0; i < 6; i++) {
				g2D.drawImage(textChars[0], (i + 1 + String.valueOf(price).length()) * 25 + 1100, 160, 25, 35, null);
			}
		}
	}

	@Override
	//method activates every 5ms, controls most logic
	public void actionPerformed(ActionEvent e) {
		// slider hitboxes
		if (getMousePosition() != null && leftMouseDown) {
			if (getMousePosition().getY() > 0 && getMousePosition().getY() < 50 && getMousePosition().getX() > 25
					&& getMousePosition().getX() < 1220) {
				slider1 = (int) getMousePosition().getX();
			} else if (getMousePosition().getY() > 50 && getMousePosition().getY() < 100
					&& getMousePosition().getX() > 25 && getMousePosition().getX() < 1220) {
				slider2 = (int) getMousePosition().getX();
			} else if (getMousePosition().getY() > 100 && getMousePosition().getY() < 150
					&& getMousePosition().getX() > 25 && getMousePosition().getX() < 1220) {
				slider3 = (int) getMousePosition().getX();
			}
		}
		// calculate simulation speed
		if (running) {
			tickStep++;
			if (tickStep >= Math.pow(1220 - slider3, 0.7) / 10) {
				tickStep = 0;
			}
			if (tickStep == 0) {
				// do water physics
				w.setTide((1220 - slider1) / 10);
				w.setWave(slider2 / 50);
				w.waveTick(tick);
				for (int i = 0; i < b.length; i++) {
					b[i].doGravity();
					w = b[i].doDamage(w);
				}
				tick++;
			}
		}
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// detect when left mouse pressed
		if (e.getButton() == MouseEvent.BUTTON1) {
			leftMouseDown = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// detect when left mouse released
		if (e.getButton() == MouseEvent.BUTTON1) {
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
	// detects any key inputs
	public void keyPressed(KeyEvent e) {
		// detect if space is down
		if (e.getKeyCode() == 32) {
			if (running) {
				running = false;
			} else {
				running = true;
			}
		}
		// detect if r is down to reset
		if (e.getKeyCode() == 82) {
			for (int i = 0; i < b.length; i++) {
				b[i].reset();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
