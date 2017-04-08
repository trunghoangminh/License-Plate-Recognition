package org.it.tdt.edu.vn.guiwindows;

import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import org.it.tdt.edu.vn.detection.RectangleDetection;
import org.it.tdt.edu.vn.io.OriginalImage;
import org.it.tdt.edu.vn.io.ParseData;
import org.it.tdt.edu.vn.preprocessor.GrayMat;
import org.it.tdt.edu.vn.preprocessor.Morphology;
import org.it.tdt.edu.vn.preprocessor.OriginalMat;
import org.it.tdt.edu.vn.utils.Utils;
import org.opencv.core.Core;
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
		ImagePanel imagePanel = new ImagePanel(destinationImage);
		window.setSize(destinationImage.getWidth(),
				destinationImage.getHeight());
		window.add(imagePanel);
		window.setVisible(true);

	}

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		OriginalImage originalImage = new OriginalImage(Utils.PATH_IMAGE);

		OriginalMat originalMat = new OriginalMat(
				originalImage.getImageFromResourcesDirectory());

		Mat grayMat = originalMat.createGrayImage();
		Mat gauss = GrayMat.createGaussianBlur(grayMat);

		Mat mor = Morphology.open(gauss, 1, 1);

		Mat threshold1 = GrayMat.createThresholdImage(mor);
		Mat threshold = GrayMat.createAdaptiveThresholdImage(threshold1);
		Mat mor2 = Morphology.dilate(threshold, 3);
		Mat binaryMat = GrayMat.createBinaryImage(mor2);

		RectangleDetection rect = new RectangleDetection();
		rect.setMat(binaryMat);
		rect.executeRectangleDetection();

		ImageResult gray = new ImageResult(mor2, "Gray Image");

		gray.showResultImage();
	}
}
