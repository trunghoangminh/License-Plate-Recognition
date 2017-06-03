package org.it.tdt.edu.vn.guiwindows;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import org.it.tdt.edu.vn.io.ParseData;
import org.opencv.core.Mat;

/**
 * 
 * @author hmtrung
 *         <p>
 *         This class use show result image after process like: gray, threshold,
 *         binary,...
 *         <p>
 *
 */
public class ImageResult {

	private Mat matResult;
	private int typeByte;
	private String windowName;

	public ImageResult(Mat matResult, String windowName) {
		this.matResult = matResult;
		this.windowName = windowName;
	}

	public Mat getMatResult() {
		return matResult;
	}

	public void setMatResult(Mat matResult) {
		this.matResult = matResult;
	}

	public String getWindowName() {
		return windowName;
	}

	public void setWindowName(String windowName) {
		this.windowName = windowName;
	}

	public int getTypeByte() {
		return typeByte;
	}

	public void setTypeByte(int typeByte) {
		this.typeByte = typeByte;
	}

	public void showResultImage() {
		JFrame window = new JFrame(windowName);
		ParseData parseData = new ParseData(matResult);
		BufferedImage destinationImage = parseData.convertMatToBufferedImage();
		File output = new File("D:\\Text\\abc.png");
		try {
			ImageIO.write(destinationImage, "png", output);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ImagePanel imagePanel = new ImagePanel(destinationImage);
		window.setSize(destinationImage.getWidth(),
				destinationImage.getHeight() + 40);
		window.add(imagePanel);
		window.setResizable(false);
		window.setVisible(true);

	}
}
