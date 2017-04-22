package org.it.tdt.edu.vn.preprocessor;

import java.awt.image.BufferedImage;
import org.it.tdt.edu.vn.io.ParseData;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

/**
 * 
 * @author hmtrung
 *         <p>
 *         This class use get mat original and create gray image.
 *         <p>
 *
 */
public class OriginalMat {

	private BufferedImage bufferedImage;

	public BufferedImage getBufferedImage() {
		return bufferedImage;
	}

	public void setBufferedImage(BufferedImage bufferedImage) {
		this.bufferedImage = bufferedImage;
	}

	public OriginalMat(BufferedImage bufferedImage) {
		this.bufferedImage = bufferedImage;
	}

	/**
	 * 
	 * @return matOriginal
	 *         <p>
	 *         Convert buffered to mat
	 *         <p>
	 */
	private Mat getImage() {
		if (bufferedImage != null) {
			ParseData parseData = new ParseData(bufferedImage);
			Mat matOriginal = parseData.convertBufferedImageToMat();
			return matOriginal;
		}
		System.err.println("BufferedImage is empty!");
		return null;
	}

	/**
	 * 
	 * @return destinationMat
	 *         <p>
	 *         Create Mat equilibrium image.
	 *         </p>
	 */
	public Mat createGrayImage() {
		if (getImage() != null) {
			Mat originalMat = getImage();
			Mat destinationMat = new Mat(originalMat.cols(),
					originalMat.rows(), CvType.CV_8UC1);
			Imgproc.cvtColor(originalMat, destinationMat,
					Imgproc.COLOR_RGB2GRAY);
			byte[] dataDestination = new byte[destinationMat.rows()
					* destinationMat.cols() * (int) (destinationMat.elemSize())];
			destinationMat.get(0, 0, dataDestination);
			return destinationMat;
		}
		System.err.println("Mat original is empty!");
		return null;
	}
}
