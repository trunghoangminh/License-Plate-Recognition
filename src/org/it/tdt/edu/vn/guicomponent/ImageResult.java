package org.it.tdt.edu.vn.guicomponent;

import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import org.it.tdt.edu.vn.preprocessor.ImageProcessors;
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
	private String imgUrl;

	public ImageResult(Mat matResult, int typeByte, String windowName,
			String imgUrl) {
		this.matResult = matResult;
		this.typeByte = typeByte;
		this.windowName = windowName;
		this.imgUrl = imgUrl;
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

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
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
		
		ParseData parseData = new ParseData("/home/hmtrung/Pictures/biensoxe.jpg");
		ImageProcessors imageProcessors = new ImageProcessors(parseData.convertBufferedImageToMat());
		ImageResult imageResult = new ImageResult(imageProcessors.createBinaryImage(),
				BufferedImage.TYPE_BYTE_GRAY, "Gray-Image", "");
		imageResult.showResultImage();
	}
}
