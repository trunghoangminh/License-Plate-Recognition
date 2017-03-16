package org.it.tdt.edu.vn.guicomponent;

import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import org.it.tdt.edu.vn.openandclose.OpenAndClose;
import org.it.tdt.edu.vn.preprocessor.GrayImage;
import org.it.tdt.edu.vn.processfile.ParseData;
import org.opencv.core.Core;
import org.opencv.core.Mat;

/**
 * 
 * @author hmtrung This class use show result image after process like: gray,
 *         threshold, binary,...
 *
 */
public class ImageResult {

	private Mat matResult;
	private int typeByte;
	private String windowName;

	public ImageResult(Mat matResult, int typeByte, String windowName) {
		this.matResult = matResult;
		this.typeByte = typeByte;
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
		ParseData parseData = new ParseData(matResult, typeByte);
		BufferedImage destinationImage = parseData.convertMatToBufferedImage();
		ImagePanel imagePanel = new ImagePanel(destinationImage);
		window.setSize(destinationImage.getWidth(),
				destinationImage.getHeight());
		window.add(imagePanel);
		window.setVisible(true);

	}

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat mat = GrayImage.createThresholdImage();
		OpenAndClose openAndClose = new OpenAndClose(mat);
		openAndClose.openBeforeCloseAfter();
		ImageResult imageResult1 = new ImageResult(mat,
				BufferedImage.TYPE_BYTE_GRAY, "Threslhold");
		ImageResult imageResult2 = new ImageResult(
				openAndClose.getThresholdMat(), BufferedImage.TYPE_BYTE_GRAY,
				"OpenAndClose");
		imageResult1.showResultImage();
		imageResult2.showResultImage();
	}
}
