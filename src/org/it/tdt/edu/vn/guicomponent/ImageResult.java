package org.it.tdt.edu.vn.guicomponent;

import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import org.it.tdt.edu.vn.openandclose.Contour;
import org.it.tdt.edu.vn.openandclose.OpenAndClose;
import org.it.tdt.edu.vn.preprocessor.GrayImage;
import org.it.tdt.edu.vn.preprocessor.OriginalImage;
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
		openAndClose.closeBeforeOpeneAfter();

		ImageResult originalResult = new ImageResult(
				OriginalImage.getOriginalImage(), BufferedImage.TYPE_3BYTE_BGR,
				"Original");
		ImageResult grayResult = new ImageResult(
				OriginalImage.createGrayImage(), BufferedImage.TYPE_BYTE_GRAY,
				"Gray");
		ImageResult thresholdResult = new ImageResult(
				GrayImage.createThresholdImage(), BufferedImage.TYPE_BYTE_GRAY,
				"Threshold");
		
		ImageResult openAndCloseResult = new ImageResult(
				openAndClose.getThresholdMat(), BufferedImage.TYPE_BYTE_GRAY,
				"OpenAndClose");
		Contour contour = new Contour(mat);
		Contour drawContour = new Contour(mat);
		/*ImageResult contourResult = new ImageResult(
				contour.createContour(), BufferedImage.TYPE_BYTE_GRAY,
				"Contour");*/
		ImageResult drawContourResult = new ImageResult(
				drawContour.drawContour(), BufferedImage.TYPE_BYTE_GRAY,
				"Draw Contour");
		 
		 originalResult.showResultImage();
		 grayResult.showResultImage();
		 thresholdResult.showResultImage();
		 openAndCloseResult.showResultImage();
		 //contourResult.showResultImage();
		 drawContourResult.showResultImage();
		 
	}
}
