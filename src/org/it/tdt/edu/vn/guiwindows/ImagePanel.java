package org.it.tdt.edu.vn.guiwindows;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 * 
 * @author TrungHoang
 *         <p>
 *         This is class used to show image into panel.
 *         <p>
 *
 */
public class ImagePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private BufferedImage image;

	/**
	 * This is constructor non-parameter use to init BufferedImage.
	 * 
	 */
	public ImagePanel(BufferedImage image) {
		super();
		this.image = image;
	}

	public BufferedImage getBufferedImage() {
		return image;
	}

	/**
	 * This is a method use to draw image into panel.
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
		repaint();
	}
}
