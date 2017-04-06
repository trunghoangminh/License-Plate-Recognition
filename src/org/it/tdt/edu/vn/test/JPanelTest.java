package org.it.tdt.edu.vn.test;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class JPanelTest extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8668473380071346072L;
	private BufferedImage image;

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public JPanelTest(BufferedImage bufferedImage) {
		this.image = bufferedImage;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
		repaint();
	}

	public static void main(String[] args) {

		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat source = Imgcodecs.imread("/home/hmtrung/Pictures/hot_girl.jpg",
				Imgcodecs.CV_LOAD_IMAGE_COLOR);

		/*Mat destination1 = new Mat(source.rows() * 2, source.cols() * 2,
				source.type());
		destination1 = source;

		Imgproc.pyrUp(source, destination1,
				new Size(source.cols() * 2, source.rows() * 2));
		Highgui.imwrite("pyrUp.jpg", destination1);

		source = Highgui.imread("digital_image_processing.jpg",
				Highgui.CV_LOAD_IMAGE_COLOR);*/

		Mat destination = new Mat(source.rows() / 2, source.cols() / 2,
				source.type());
		destination = source;
		Imgproc.pyrDown(source, destination,
				new Size(source.cols() / 2, source.rows() / 2));
		Imgcodecs.imwrite("/home/hmtrung/Pictures/hot_girl_pyrDown.jpg",
				destination);
	}

}
